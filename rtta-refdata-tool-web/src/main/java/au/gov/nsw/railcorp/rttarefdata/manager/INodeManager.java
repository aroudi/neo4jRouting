package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.domain.Platform;

import java.util.List;

/**
 * Created by arash on 10/11/14.
 */
public interface INodeManager {


    /**
     * create platform.
     * @param nodeName nodeName
     * @param longName longName
     * @param platformNo platformNo
     * @param platformName platformName
     * @param latt latt
     * @param longt longt
     * @return Platform platform
     */
    Platform createPlatform(String nodeName, String longName, int platformNo, String platformName, double latt, double longt);

    /**
     * create Node.
     * @param nodeName nodeName
     * @param longName longName
     * @param platformName platformName
     * @param latt latt
     * @param longt longt
     * @return Node platform
     */
     Node createNode(String nodeName, String longName, String platformName, double latt, double longt);

        /**
         * return all nodes.
         * @return list of nodes
         */
    List getAllNodes();
}
