package au.gov.nsw.railcorp.rtta.refint.boot;

import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RttaGtfsTopology;
import au.gov.nsw.railcorp.rtta.refint.generated.nodes.RttaNodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Created by arash on 10/11/14.
 */

public class NodeBootManager {
    private String dirPath;
    private String fileName;
    private RttaNodes rttaNodes;

    private   static final Logger commonLogger = LoggerFactory.getLogger(GtfsTopologyBootManager.class);

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public RttaNodes loadNodesFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RttaNodes.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            InputStream stream = getClass().getResourceAsStream(dirPath.concat(fileName));
            //File rttaStopsXml = new File("");
            rttaNodes =(RttaNodes) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(rttaNodes, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return rttaNodes;
    }

    public RttaNodes loadNodesFromInputStream(InputStream stream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RttaNodes.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            rttaNodes =(RttaNodes) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(rttaNodes, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return rttaNodes;
    }

}
