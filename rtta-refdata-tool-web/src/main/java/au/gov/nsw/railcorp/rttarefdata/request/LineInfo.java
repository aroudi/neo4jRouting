package au.gov.nsw.railcorp.rttarefdata.request;

/**
 * Created by arash on 4/02/2015.
 */
public class LineInfo {
    private String name;
    private String longName;
    private String backgroundColourHex;
    private String textColourHex;

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
