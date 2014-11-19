package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStop;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RefStopLink;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import au.gov.nsw.railcorp.rttarefdata.manager.IStopManager;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 7/11/14.
 */
@Component
public class StopService {
    @Autowired
    private IStopManager stopManager;

    /**
     * import rtta stops.
     * @param rttaStops rttaStops object resulted from xml file or from input stream.
     */
    public void importRttaStops(RttaStops rttaStops) {
        importStations(rttaStops.getStopsV01().getStops().getStop());
        importPlatforms(rttaStops.getStopsV01().getStops().getStop());
        importStopLinks(rttaStops.getStopsV01().getStopLinks().getLink());
    }

    /**
     * import platfroms.
     * @param stops list of platforms
     */
    public void importPlatforms(List<RefStop> stops) {
        if (stops == null) {
            return;
        }
        for (RefStop refStop : stops) {
            if (refStop == null || refStop.isStationStop()) {
                continue;
            }
            stopManager.createPlatform(
                    StringUtil.strToInt(refStop.getParentStopId()),
                    StringUtil.strToInt(refStop.getStopId()),
                    refStop.getName(),
                    refStop.getLongName(),
                    StringUtil.extractPlatformNoFromNodeName(refStop.getName()),
                    null,
                    refStop.getLatitude(),
                    refStop.getLongitude());
        }
    }

    /**
     * import Stations.
     * @param stops list of station
     */
    public void importStations(List<RefStop> stops) {
        if (stops == null) {
            return;
        }
        for (RefStop refStop : stops) {
            if (refStop == null || !refStop.isStationStop()) {
                continue;
            }
            stopManager.createStation(refStop.getName(), refStop.getLongName(), StringUtil.strToInt(refStop.getStopId()), refStop.getLatitude(), refStop.getLongitude());
        }
    }

    /**
     * import stop links.
     * @param stopLinks stopLinks
     */
    public void importStopLinks(List<RefStopLink> stopLinks) {
        List<String> linkPowers;
        if (stopLinks == null) {
            return;
        }
        for (RefStopLink stopLink: stopLinks) {
            if (stopLink == null) {
                continue;
            }
            linkPowers = new ArrayList<String>();
            if (stopLink.isDiesel()) {
                linkPowers.add(IConstants.POWER_TYPE_DIESEL);
            }
            if (stopLink.isElectric()) {
                linkPowers.add(IConstants.POWER_TYPE_ELECTRIC);
            }
            stopManager.createTriplet(StringUtil.strToInt(stopLink.getInStopId()),
                    StringUtil.strToInt(stopLink.getStopId()),
                    StringUtil.strToInt(stopLink.getOutStopId()),
                    stopLink.isReversible(),
                    linkPowers);
        }
    }

}
