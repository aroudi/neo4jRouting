package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.repositories.StationTripletRepository;
import au.gov.nsw.railcorp.rttarefdata.request.DataVersionModel;
import au.gov.nsw.railcorp.rttarefdata.response.DataVersionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by arash on 26/02/2015.
 */
public class VersionTest {
    private static final Logger logger = LoggerFactory.getLogger(VersionTest.class);

    public static DataVersionService dataVersionService;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("rttaDataRefContext.xml");
        dataVersionService = context.getBean(DataVersionService.class);
        createDataVersion();
        returnActiveDataVersion();
    }
    public static void createDataVersion() {
        //create a version

        DataVersionResponse dataVersionResponse;
        DataVersionModel dataVersionModel = new DataVersionModel();
        dataVersionModel.setName("2014-08-23-RN03(5.03)");
        dataVersionModel.setDescription("Release of Geography revision 4");
        dataVersionModel.setCommenceDate("26-02-2015 05:09PM");

        dataVersionResponse = dataVersionService.createDataVersion(dataVersionModel);
        logger.info("response for " + dataVersionModel.getName() + " = " +dataVersionResponse.getStatus() + " - " + dataVersionResponse.getMessage() );

        dataVersionModel = new DataVersionModel();
        dataVersionModel.setName("2014-08-23-RN03(5.04)");
        dataVersionModel.setDescription("Release of Geography revision 4");
        dataVersionModel.setCommenceDate("26-02-2015 05:12PM");

        dataVersionResponse = dataVersionService.createDataVersion(dataVersionModel);
        logger.info("response for " + dataVersionModel.getName() + " = " +dataVersionResponse.getStatus() + " - " + dataVersionResponse.getMessage() );
    }

    public static void returnActiveDataVersion()
    {
        DataVersion dataVersion = dataVersionService.getActiveVersion();
        if (dataVersion == null) {
            logger.info("Active Data Version not found");
            return;
        }
        logger.info("Active Data Version = " + dataVersion.getName());
    }
}
