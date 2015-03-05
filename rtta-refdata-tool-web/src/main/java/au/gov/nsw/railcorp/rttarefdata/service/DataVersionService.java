package au.gov.nsw.railcorp.rttarefdata.service;

import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.manager.IDataVersionManager;
import au.gov.nsw.railcorp.rttarefdata.request.DataVersionModel;
import au.gov.nsw.railcorp.rttarefdata.response.DataVersionResponse;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
import au.gov.nsw.railcorp.rttarefdata.session.SessionState;
import au.gov.nsw.railcorp.rttarefdata.util.DateUtil;
import au.gov.nsw.railcorp.rttarefdata.util.IConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by arash on 26/02/2015.
 */
@Component
public class DataVersionService {
    private final Logger logger = LoggerFactory.getLogger(DataVersionService.class);

    @Autowired
    private IDataVersionManager dataVersionManager;

    @Autowired
    private SessionState sessionState;

    /**
     * Create new data version.
     * @param dataVersionModel dataVersionModel
     * @return Response;
     */
    public DataVersionResponse createDataVersion(DataVersionModel dataVersionModel) {
        final DataVersionResponse response = new DataVersionResponse();
        // check if input object is null
        if (dataVersionModel == null) {
            response.setMessage("Received object is null");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        //check if object allready exists.
        DataVersion dataVersion = dataVersionManager.searchDataVersionPerName(dataVersionModel.getName());
        if (dataVersion != null) {
            response.setMessage("Version " + dataVersionModel.getName() + " already exists.");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        // check if commence date is valid and before current date.
        final Date currentDate = new Date();
        final Date commenceDate = DateUtil.stringToDate(dataVersionModel.getCommenceDate());
        if (commenceDate == null) {
            response.setMessage("commence date is invalid.");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        if (commenceDate.before(currentDate)) {
            response.setMessage("commence date is before current date");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        // check if commence date is allready exists.
        dataVersion = dataVersionManager.getDataVersionByCommenceDate(commenceDate);
        if (dataVersion != null) {
            response.setMessage("commence date " + dataVersionModel.getCommenceDate() + " already exists");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        if (commenceDate.equals(currentDate)) {
            dataVersionModel.setActive(true);
        } else {
            dataVersionModel.setActive(false);
        }
        dataVersion = dataVersionManager.addDataVersion(dataVersionModel.getName(), dataVersionModel.getDescription(), currentDate
                , commenceDate, dataVersionModel.isActive(), dataVersionModel.getBaseVersion());
        if (dataVersion == null) {
            response.setMessage("not able to save data version. check the log file");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        } else {
            response.setActive(dataVersionModel.isActive());
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setCreateDate(DateUtil.dateToString(dataVersion.getCreateDate()));
            response.setMessage("Version created successfully");
        }
        return response;
    }
    /**
     * Edit existing data version.
     * @param dataVersionModel dataVersionModel
     * @return Response;
     */
    public DataVersionResponse editDataVersion(DataVersionModel dataVersionModel) {
        final DataVersionResponse response = new DataVersionResponse();
        // check if input object is null
        if (dataVersionModel == null) {
            response.setMessage("Received object is null");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        //check if object allready exists.
        DataVersion dataVersion = dataVersionManager.getDataVersionById(dataVersionModel.getId());
        if (dataVersion == null) {
            response.setMessage("Id " + dataVersionModel.getId() + " dose not exists.");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        // check if commence date is valid and before current date.
        final Date currentDate = new Date();
        dataVersion = dataVersionManager.editDataVersion(dataVersionModel.getId(), dataVersionModel.getName(), dataVersionModel.getDescription(), currentDate);
        if (dataVersion == null) {
            response.setMessage("not able to save data version. check the log file");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        } else {
            response.setActive(dataVersionModel.isActive());
            response.setStatus(IConstants.RESPONSE_SUCCESS);
            response.setMessage("Version created successfully");
        }
        return response;
    }

    /**
     * remove data version per id.
     * @param id id
     * @return Response
     */
    public Response removeDataVersion(long id) {
        // check if dataversion exists.
        final Response response = new Response();
        final DataVersion dataVersion = dataVersionManager.getDataVersionById(id);
        if (dataVersion == null) {
            response.setMessage("version with id " + id + " not exists.");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        if (!dataVersionManager.removeDataVersion(id)) {
            response.setMessage("not able to remove data version");
            response.setStatus(IConstants.RESPONSE_FAILURE);
            return response;
        }
        response.setStatus(IConstants.RESPONSE_SUCCESS);
        response.setId(id);
        return response;
    }

    /**
     * return active data version.
     * @return DataVersion
     */
    public DataVersion getActiveVersion () {
        final DataVersion dataVersion = dataVersionManager.getActiveDataVersion();
        return dataVersion;
    }

    /**
     * return all data versions.
     * @return List
     */
    public List<DataVersionModel> getAllDataVersions() {
        return dataVersionManager.getAllDataVersion();
    }

    /**
     * set working version on session state object.
     * @param id id
     */
    public void setWorkingVersion(long id) {
        try {
            logger.info("setWorkingVersion id= " + id);
            final DataVersion dataVersion = dataVersionManager.getDataVersionById(id);
            logger.info("dataVersion= " + dataVersion.getName());
            if (dataVersion != null) {
                sessionState.setWorkingVersion(dataVersion);
            }
        } catch (Exception e) {
            logger.error("Exception in setting working version: ", e);
        }
    }
}
