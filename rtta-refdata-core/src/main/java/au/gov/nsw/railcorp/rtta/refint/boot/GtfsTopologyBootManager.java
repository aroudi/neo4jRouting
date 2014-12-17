package au.gov.nsw.railcorp.rtta.refint.boot;

import au.gov.nsw.railcorp.rtta.refint.generated.gtfs.RttaGtfsTopology;
import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Created by arash on 10/11/14.
 */
public class GtfsTopologyBootManager {
    private String dirPath;
    private String fileName;
    private RttaGtfsTopology gtfsTopology;

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

    public RttaGtfsTopology loadTopologyFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RttaGtfsTopology.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            InputStream stream = getClass().getResourceAsStream(dirPath.concat(fileName));
            //File rttaStopsXml = new File("");
            gtfsTopology =(RttaGtfsTopology) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gtfsTopology, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return gtfsTopology;
    }

    public RttaGtfsTopology loadTopologyFromInputStream(InputStream stream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RttaGtfsTopology.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            gtfsTopology =(RttaGtfsTopology) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(gtfsTopology, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return gtfsTopology;
    }
}
