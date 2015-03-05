package au.gov.nsw.railcorp.rttarefdata.rest;

import au.gov.nsw.railcorp.rttarefdata.mapresult.ILineData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.IRefData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.LineData;
import au.gov.nsw.railcorp.rttarefdata.mapresult.RefData;
import au.gov.nsw.railcorp.rttarefdata.repositories.NetworkLineRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.NetworkRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.ServiceTypeRepository;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 11/12/14.
 */
@Component
@Path("refDatas")
public class RefDataService {
    private final Logger logger = LoggerFactory.getLogger(StationService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private NetworkRepository networkRepository;
    @Autowired
    private NetworkLineRepository networkLineRepository;
    @Autowired
    private SessionState sessionState;
    /**
     * Return network list in Json format.
     * @return network List
     */
    @GET
    @Path("/networksRef")
    @Produces(MediaType.APPLICATION_JSON)
    public List getNetworksAsRefData() {
        List<IRefData> refDataList;
        final List<RefData> result = new ArrayList<RefData>();
        RefData refData;
        try {
            refDataList = networkRepository.getNetworksAsRefData(sessionState.getWorkingVersion().getName());
            for (IRefData data: refDataList) {
                refData = new RefData();
                refData.setRefDataId(data.getRefDataId());
                refData.setRefDataCode(data.getRefDataCode());
                refData.setRefDataName(data.getRefDataName());
                result.add(refData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning networks as ref data ", e);
            return null;
        }
    }

    /**
     * Return Service Type list in Json format.
     * @return service type List
     */
    @GET
    @Path("/serviceTypesRef")
    @Produces(MediaType.APPLICATION_JSON)
    public List getServiceTypesAsRefData() {
        List<IRefData> refDataList;
        final List<RefData> result = new ArrayList<RefData>();
        RefData refData;
        try {
            refDataList = serviceTypeRepository.getServiceTypesAsRefData();
            for (IRefData data: refDataList) {
                refData = new RefData();
                refData.setRefDataId(data.getRefDataId());
                refData.setRefDataCode(data.getRefDataCode());
                refData.setRefDataName(data.getRefDataName());
                result.add(refData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning service type as ref data ", e);
            return null;
        }
    }
    /**
     * Return line list in Json format.
     * @return line List
     */
    @GET
    @Path("/linesRef")
    @Produces(MediaType.APPLICATION_JSON)
    public List getLinesAsRefData() {
        List<ILineData> lineDataList;
        final List<LineData> result = new ArrayList<LineData>();
        LineData lineData;
        try {
            lineDataList = networkLineRepository.getAllLinesAsRefData(sessionState.getWorkingVersion().getName());
            for (ILineData data: lineDataList) {
                lineData = new LineData();
                lineData.setLineId(data.getLineId());
                lineData.setName(data.getName());
                lineData.setLongName(data.getLongName());
                lineData.setTextColourHex(data.getTextColourHex());
                lineData.setBackgroundColourHex(data.getBackgroundColourHex());
                result.add(lineData);
            }
            return result;
        } catch (Exception e) {
            logger.error("Exception in returning lines as ref data ", e);
            return null;
        }
    }
}

