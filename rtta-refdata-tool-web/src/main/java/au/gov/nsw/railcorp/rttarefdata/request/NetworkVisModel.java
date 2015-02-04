package au.gov.nsw.railcorp.rttarefdata.request;


import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by arash on 4/02/2015.
 * a model for visualizing network in term of graph
 */
public class NetworkVisModel {
    private String networkName;
    private List nodes;
    @JsonIgnore
    private HashMap<String, EdgeModel> edges;
    private Collection arcs;
    /**
     * add edge to the list.
     * for avoiding duplicates edes between 2 nodes, we search for key and reverseKey.
     * @param mainKey mainKey
     * @param reverseKey reverseKey
     * @param edgeModel edgeModel
     * @param lineInfo lineInfo
     */
    public void addEdge (String mainKey, String reverseKey, EdgeModel edgeModel, LineInfo lineInfo) {
        EdgeModel currentEdgeModel;
        if (edgeModel == null || lineInfo == null || mainKey == null || reverseKey == null) {
            return;
        }
        if (edges == null) {
            edges = new HashMap<String, EdgeModel>();
        }
        // search for key1
        if (edges.containsKey(mainKey)) {
            currentEdgeModel = edges.get(mainKey);
            currentEdgeModel.addLine(lineInfo);
            return;
        }
        if (edges.containsKey(reverseKey)) {
            currentEdgeModel = edges.get(reverseKey);
            currentEdgeModel.addLine(lineInfo);
            return;
        }
        edgeModel.addLine(lineInfo);
        edges.put(mainKey, edgeModel);

    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public List getNodes() {
        return nodes;
    }

    public void setNodes(List nodes) {
        this.nodes = nodes;
    }

    public HashMap<String, EdgeModel> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<String, EdgeModel> edges) {
        this.edges = edges;
    }

    /**
     * convert edges to arcs.
     * @return Collection
     */
    public Collection getArcs() {
        if (edges != null) {
            return edges.values();
        }
        return arcs;
    }

    public void setArcs(List arcs) {
        this.arcs = arcs;
    }
}
