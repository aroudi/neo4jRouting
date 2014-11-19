package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Node;
import au.gov.nsw.railcorp.rttarefdata.domain.Platform;
import au.gov.nsw.railcorp.rttarefdata.repositories.NodeRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by arash on 10/11/14.
 */
@Transactional
@Component
public class NodeManager implements INodeManager {
    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private NodeRepository nodeRepository;

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
    public Platform createPlatform(String nodeName, String longName, int platformNo, String platformName, double latt, double longt) {

        Platform platform = platformRepository.findBySchemaPropertyValue("name", nodeName);
        if (platform == null) {
            platform = new Platform();
        }
        platform.setPlatformNo(platformNo);
        platform.setName(nodeName);
        platform.setPlatfromName(platformName);
        platform.setLongName(longName);
        platform.setLatitude(latt);
        platform.setLongitude(longt);
        platformRepository.save(platform);
        return platform;
    }

    /**
     * create Node.
     * @param nodeName nodeName
     * @param longName longName
     * @param platformName platformName
     * @param latt latt
     * @param longt longt
     * @return Node platform
     */
    public Node createNode(String nodeName, String longName, String platformName, double latt, double longt) {

        Node node = nodeRepository.findBySchemaPropertyValue("name", nodeName);
        if (node == null) {
            node = new Platform();
        }
        node.setName(nodeName);
        node.setPlatfromName(platformName);
        node.setLongName(longName);
        node.setLatitude(latt);
        node.setLongitude(longt);
        nodeRepository.save(node);
        return node;
    }
}
