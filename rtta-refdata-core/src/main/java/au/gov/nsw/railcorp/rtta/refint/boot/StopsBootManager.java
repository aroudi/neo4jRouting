package au.gov.nsw.railcorp.rtta.refint.boot;

import au.gov.nsw.railcorp.rtta.refint.generated.stops.RttaStops;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by arash on 5/11/14.
 */

public class StopsBootManager {
    private String dirPath;
    private String fileName;
    private RttaStops rttaStops;

    private   static final Logger commonLogger = LoggerFactory.getLogger(StopsBootManager.class);

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

    public RttaStops getRttaStops() {
        return rttaStops;
    }

    public void setRttaStops(RttaStops rttaStops) {
        this.rttaStops = rttaStops;
    }

    public RttaStops loadStopsFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RttaStops.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            InputStream stream = getClass().getResourceAsStream(dirPath.concat(fileName));
            //File rttaStopsXml = new File("");
            rttaStops =(RttaStops) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(rttaStops, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return rttaStops;
    }
}
