package au.gov.nsw.railcorp.rttarefdata.mapresult;

/**
 * Created by arash on 11/12/14.
 */
public class LineData {
    private Long lineId;
    private String name;
    private String longName;
    private String backgroundColourHex;
    private String textColourHex;

    public Long getLineId() {
        return lineId;
    }
    public void setLineId(Long lineId) {
        this.lineId = lineId;
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
