package au.gov.nsw.railcorp.rttarefdata.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 4/02/2015.
 */
public class EdgeModel {
    private long fromNode;
    private long toNode;
    private List lines;


    /**
     * add line to lines.
     * @param lineInfo lineInfo
     */
    public void addLine(LineInfo lineInfo) {
        if (lineInfo == null) {
            return;
        }
        if (lines == null) {
            lines = new ArrayList();
        }
        lines.add(lineInfo);
    }
    public long getFromNode() {
        return fromNode;
    }

    public void setFromNode(long fromNode) {
        this.fromNode = fromNode;
    }

    public long getToNode() {
        return toNode;
    }

    public void setToNode(long toNode) {
        this.toNode = toNode;
    }

    public List getLines() {
        return lines;
    }

    public void setLines(List lines) {
        this.lines = lines;
    }
}
