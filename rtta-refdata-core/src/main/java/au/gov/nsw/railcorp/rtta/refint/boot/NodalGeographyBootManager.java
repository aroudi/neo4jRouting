package au.gov.nsw.railcorp.rtta.refint.boot;

import au.gov.nsw.railcorp.rtta.refint.generated.geography.CgGeography;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * Created by arash on 11/11/14.
 */
public class NodalGeographyBootManager {
    private String dirPath;
    private String fileName;
    private CgGeography cgGeography;

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

    public CgGeography loadNodalGeographyFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CgGeography.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            InputStream stream = getClass().getResourceAsStream(dirPath.concat(fileName));
            //File rttaStopsXml = new File("");
            cgGeography =(CgGeography) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(cgGeography, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return cgGeography;
    }

    public CgGeography loadNodalGeographyFromInputStream(InputStream stream) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CgGeography.class);
            Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
            cgGeography =(CgGeography) unmarshaller.unmarshal(stream);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(cgGeography, System.out);
        } catch (Exception e) {
            commonLogger.error("Exception in reading file:",e);
        }
        return cgGeography;
    }

}
