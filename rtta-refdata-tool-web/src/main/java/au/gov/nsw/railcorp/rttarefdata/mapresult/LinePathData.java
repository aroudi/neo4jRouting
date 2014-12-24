package au.gov.nsw.railcorp.rttarefdata.mapresult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 22/12/2014.
 */
public class LinePathData {
    private Long pathId;
    private String name;
    private String longName;
    private String lineName;
    private String lineLongName;
    private String backgroundColourHex;
    private String textColourHex;
    private List<LinePathStationData> linePathStationList;

    /**
     * add station to linePath.
     * @param linePathStation linePathStation
     */
    public void addStationToPath(LinePathStationData linePathStation) {
        if (linePathStationList == null) {
            linePathStationList = new ArrayList<LinePathStationData>();
        }
        if (linePathStation != null) {
            linePathStationList.add(linePathStation);
        }
    }

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

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLongName() {
        return lineLongName;
    }

    public void setLineLongName(String lineLongName) {
        this.lineLongName = lineLongName;
    }

    public List<LinePathStationData> getLinePathStationList() {
        return linePathStationList;
    }

    public void setLinePathStationList(List<LinePathStationData> linePathStationList) {
        this.linePathStationList = linePathStationList;
    }

    public String getBackgroundColourHex() {
        return backgroundColourHex;
    }

    public void setBackgroundColourHex(String backgroundColourHex) {
        this.backgroundColourHex = backgroundColourHex;
    }

    public String getTextColourHex() {
        return textColourHex;
    }

    public void setTextColourHex(String textColourHex) {
        this.textColourHex = textColourHex;
    }
}
