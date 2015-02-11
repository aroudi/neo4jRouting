package au.gov.nsw.railcorp.rttarefdata.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 9/02/2015.
 */
public class TraverseModel {
    private String pathName;
    private List<NodeModel> nodes;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    /**
     * add nodeModel to nodes.
     * @param nodeModel nodeModel
     */
    public void addNode(NodeModel nodeModel) {
        if (nodes == null) {
            nodes = new ArrayList<NodeModel>();
        }
        nodes.add(nodeModel);
    }
    public List<NodeModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeModel> nodes) {
        this.nodes = nodes;
    }
}
