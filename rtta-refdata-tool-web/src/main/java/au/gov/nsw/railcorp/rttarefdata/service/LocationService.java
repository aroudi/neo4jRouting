package au.gov.nsw.railcorp.rttarefdata.service;

import au.com.bytecode.opencsv.CSVReader;
import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.domain.Location;
import au.gov.nsw.railcorp.rttarefdata.manager.ILocationManager;
import au.gov.nsw.railcorp.rttarefdata.manager.INodeManager;
import au.gov.nsw.railcorp.rttarefdata.request.LocationModel;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import au.gov.nsw.railcorp.rttarefdata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by arash on 21/01/2015.
 */
@Component
public class LocationService {
    private final Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Autowired
    private ILocationManager locationManager;

    @Autowired
    private INodeManager nodeManager;
    @Autowired
    private SessionState sessionState;
    /**
     * import location from csv file.
     * @param inputStream inputStream
     * @throws Exception Exception
     */
    public void importLocationsFromInputStream(InputStream inputStream) throws Exception {
        CSVReader csvReader = null;
        try {
            ListIterator<String[]> iterator = null;
            String[] csvRow = null;
            csvReader = new CSVReader(new InputStreamReader(inputStream));
            if (csvReader == null) {
                logger.error("not able to open location inputStream");
                return;
            }
            iterator = csvReader.readAll().listIterator();
            //read header
            if (iterator.hasNext()) {
                iterator.next();
            }
            while (iterator.hasNext()) {
                csvRow = iterator.next();
                if (csvRow == null) {
                    continue;
                }
                locationManager.createLocation(csvRow[0], csvRow[1], StringUtil.strToDouble(csvRow[2]), StringUtil.strToDouble(csvRow[3]), csvRow[4], StringUtil.strToInt(csvRow[5]));
            }
        } catch (Exception e) {
            logger.error("error in importing location : ", e);
        } finally {
            if (csvReader != null) {
                csvReader.close();
            }
        }

    }
    /**
     * return list of locations.
     * @return list of locations
     */
    public List<Location> getAllLocations() {
        try {
            return locationManager.getAllLocations();
        } catch (Exception e) {
            logger.error("error in retrieving location list: ", e);
            return  null;
        }
    }

    /**
     * add new location.
     * @param locationModel locationModel
     * @return Response
     */
    public Response addLocation (LocationModel locationModel) {
        final Response response = new Response();
        try {
            if (locationModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            if (locationManager.getLocationByName(locationModel.getName()) != null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("location " + locationModel.getName() + " already exists");
                return response;
            }
            if ((locationModel.getNodeName() != null && !locationModel.getNodeName().isEmpty()) &&  nodeManager.getNodeByName(locationModel.getNodeName()) == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("node " + locationModel.getNodeName() + " not found");
                return response;
            }
            final Location location = locationManager.createLocation(locationModel.getName(), locationModel.getSystemName(), locationModel.getLongtitude(),
                    locationModel.getLatitude(), locationModel.getNodeName(), locationModel.getExcludeFringe());
            if (location == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to create location");
                return response;
            }
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setId(location.getId());
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * edit existing location.
     * @param locationModel locationModel
     * @return Response
     */
    public Response editLocation (LocationModel locationModel) {
        final Response response = new Response();
        try {
            if (locationModel == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("received object is null");
                return response;
            }
            if (locationManager.getLocationByName(locationModel.getName()) == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("location " + locationModel.getName() + " not found");
                return response;
            }
            if ((locationModel.getNodeName() != null && !locationModel.getNodeName().isEmpty()) &&  nodeManager.getNodeByName(locationModel.getNodeName()) == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("node " + locationModel.getNodeName() + " not found");
                return response;
            }
            final Location location = locationManager.createLocation(locationModel.getName(), locationModel.getSystemName(), locationModel.getLongtitude(),
                    locationModel.getLatitude(), locationModel.getNodeName(), locationModel.getExcludeFringe());
            if (location == null) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("not able to edit location");
                return response;
            }
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setId(location.getId());
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * delete location by Id.
     * @param  locationId locationId
     * @return Response
     */
    public Response deleteLocation (Long locationId) {
        final Response response = new Response();
        try {
            if (locationManager.deleteLocation(locationId) < 0) {
                response.setStatus(IConstants.RESPONSE_FAILURE);
                response.setMessage("Not able to delete location");
                return response;
            }
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            return response;
        } catch (Exception e) {
            response.setStatus(IConstants.RESPONSE_FAILURE);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    /**
     * Export Locations list into csv format and return as StreamingOutput.
     * @return Streamingoutput
     */
    public StreamingOutput exportLocationsToCsv() {
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = new StreamingOutput() {
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    writeLocationToOutputStream(output);
               }
            };
            return streamingOutput;
        } catch (Exception e) {
            logger.error("Exception in writing stations into csv: ", e);
            return null;
        }
    }
    /**
     * clone Location.
     * @param fromVersion fromVersion
     * @param toVersion toVersion
     * @return boolean
     */
    @Transactional
    public boolean cloneLocation(DataVersion fromVersion, DataVersion toVersion) {
        //temporary save the current version
        final DataVersion currentWorkingVersion = sessionState.getWorkingVersion();
        try {
            if (fromVersion == null || toVersion == null) {
                return false;
            }
            sessionState.setWorkingVersion(fromVersion);
            //fetch all current stops
            final OutputStream outputStream = new ByteArrayOutputStream();
            writeLocationToOutputStream(outputStream);
            final ByteArrayInputStream bis = new ByteArrayInputStream(outputStream.toString().getBytes());
            sessionState.setWorkingVersion(toVersion);
            importLocationsFromInputStream(bis);
            sessionState.setWorkingVersion(currentWorkingVersion);
        } catch (Exception e) {
            logger.error("Exception in clonning location : ", e);
            sessionState.setWorkingVersion(toVersion);
            return false;
        }
        return true;
    }

    /**
     * write locations to output stream.
     * @param output output
     */
    public void writeLocationToOutputStream (OutputStream output) {
        try {
            if (output == null) {
                return;
            }
            final List<Location> locationList = locationManager.getAllLocations();
            if (locationList == null) {
                return;
            }
            String line = "LOCATION_NAME, SYSTEM_NAME, LONGITUDE, LATITUDE, NODE_NAME, EXCLUDE_FRINGE ";
            output.write(line.getBytes());
            output.write(System.getProperty("line.separator").getBytes());
            for (Location location : locationList) {
                line = location.getName() + ", " + location.getSystemName() + ", " + location.getLongtitude() + ", "
                        + location.getLatitude() + ", " + location.getNodeName() + ", " + location.getExcludeFringe();
                output.write(line.getBytes());
                output.write(System.getProperty("line.separator").getBytes());
            }
            output.flush();
            output.close();
        } catch (Exception e) {
            logger.error("Exception in writing locatios to output stream: ", e);
            return;
        }
    }
}
