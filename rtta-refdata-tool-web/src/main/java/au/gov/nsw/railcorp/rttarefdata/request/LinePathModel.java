package au.gov.nsw.railcorp.rttarefdata.request;

import au.gov.nsw.railcorp.rttarefdata.mapresult.LineData;

import java.util.List;

/**
 * Created by arash on 22/12/2014.
 */
public class LinePathModel {
    private Long pathId;
    private String name;
    private String longName;
    private LineData networkLine;
    private List<LinePathStationModel> linePathStationList;

    public Long getPathId() {
        return pathId;
    }

    public void setPathId(Long pathId) {
        this.pathId = pathId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public List<LinePathStationModel> getLinePathStationList() {
        return linePathStationList;
    }

    public void setLinePathStationList(List<LinePathStationModel> linePathStationList) {
        this.linePathStationList = linePathStationList;
    }

    public LineData getNetworkLine() {
        return networkLine;
    }

    public void setNetworkLine(LineData networkLine) {
        this.networkLine = networkLine;
    }
}
