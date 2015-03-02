package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.repositories.DataVersionRepository;
import au.gov.nsw.railcorp.rttarefdata.request.DataVersionModel;
import au.gov.nsw.railcorp.rttarefdata.util.DateUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arash on 26/02/2015.
 */
@Transactional
@Component
public class DataVersionManager implements IDataVersionManager {

    private final Logger logger = LoggerFactory.getLogger(DataVersionManager.class);
    @Autowired
    private DataVersionRepository dataVersionRepository;

    /**
     * create a new DataVersionObject.
     * @param name name
     * @param description description
     * @param createDate createDate
     * @param commenceDate commenceDate
     * @param isActive isActive
     * @param baseVersion baseVersion
     * @return DataVersion object
     */
    public DataVersion addDataVersion(String name, String description, Date createDate, Date commenceDate, boolean isActive, String baseVersion) {
        try {
            final DataVersion dataVersion = new DataVersion();
            dataVersion.setName(name);
            dataVersion.setDescription(description);
            dataVersion.setCreateDate(createDate);
            dataVersion.setCommenceDate(commenceDate);
            dataVersion.setActive(isActive);
            final DataVersion baseDataVersion = dataVersionRepository.findBySchemaPropertyValue("name", baseVersion);
            dataVersion.setBaseVersion(baseDataVersion);
            dataVersionRepository.save(dataVersion);
            return dataVersion;
        } catch (Exception e) {
            logger.error("Error in creating data version object: ", e);
            return null;
        }
    }

    /**
     * create a new DataVersionObject.
     * @param id id
     * @param name name
     * @param description description
     * @param createDate createDate
     * @return DataVersion object
     */
    public DataVersion editDataVersion(long id, String name, String description, Date createDate) {
        try {
            final DataVersion dataVersion = dataVersionRepository.findOne(id);
            if (dataVersion == null) {
                logger.debug("Not able to find Dataversion with id: ", id);
                return null;
            }
            dataVersion.setName(name);
            dataVersion.setDescription(description);
            dataVersion.setCreateDate(createDate);
            dataVersionRepository.save(dataVersion);
            return dataVersion;
        } catch (Exception e) {
            logger.error("Error in creating data version object: ", e);
            return null;
        }
    }
    /**
     * Search DataVersion per name.
     * @param name name
     * @return DataVersion
     */
    public DataVersion searchDataVersionPerName(String name) {
        try {
            DataVersion dataVersion = null;
            dataVersion = dataVersionRepository.findBySchemaPropertyValue("name" , name);
            return dataVersion;
        } catch (Exception e) {
            logger.error("Error in returning dataversion object per name : ", e);
            return  null;
        }
    }

    /**
     * get Active Data Version.
     * @return DataVersion
     */
    public DataVersion getActiveDataVersion () {
        try {
            final Date currentDate = new Date();
            final List<DataVersion> dataVersionList = dataVersionRepository.getDataVersionWhitPastCommenceDate(currentDate);
            if (dataVersionList != null && dataVersionList.size() > 0) {
                return dataVersionList.get(0);
            }
            return null;
        } catch (Exception e) {
            logger.error("Exception in returning current data version: ", e);
            return null;
        }
    }

    /**
     * Return DataVersion per commenceDate.
     * @param commenceDate commenceDate
     * @return DataVersion
     */
    public DataVersion getDataVersionByCommenceDate(Date commenceDate) {
        try {
            final DataVersion dataVersion = dataVersionRepository.findBySchemaPropertyValue("commenceDate", commenceDate);
            return dataVersion;
        } catch (Exception e) {
            logger.error("Exception in getting DataVersion per commence date: ", e);
            return null;
        }
    }
    /**
     * Return DataVersion per commenceDate.
     * @param id id
     * @return DataVersion
     */
    public DataVersion getDataVersionById(long id) {
        try {
            final DataVersion dataVersion = dataVersionRepository.findOne(id);
            return dataVersion;
        } catch (Exception e) {
            logger.error("Exception in getting DataVersion per id: ", e);
            return null;
        }
    }
    /**
     * Remove DataVersion.
     * @param id id
     * @return DataVersion
     */
    public boolean removeDataVersion(long id) {
        try {
            dataVersionRepository.delete(id);
            return true;
        } catch (Exception e) {
            logger.error("Exception in deleting data version: ", e);
            return false;
        }
    }

    /**
     * return all data versions.
     * @return List of DataVersionModel
     */
    public List<DataVersionModel> getAllDataVersion() {
        try {
            final List<DataVersionModel> dataVersionModelList = new ArrayList<DataVersionModel>();
            final DataVersion activeModel = getActiveDataVersion();
            DataVersionModel dataVersionModel;
            final Sort.Order order = new Sort.Order(Sort.Direction.DESC, "active");
            final Sort sort = new Sort(order);
            final List<DataVersion> dataVersionList = Lists.newArrayList(dataVersionRepository.findAll(sort).iterator());
            if (dataVersionList == null || dataVersionList.size() < 1) {
                return null;
            }
            for (DataVersion dataVersion: dataVersionList) {
                if (dataVersion == null) {
                    continue;
                }
                dataVersionModel = new DataVersionModel();
                dataVersionModel.setName(dataVersion.getName());
                dataVersionModel.setId(dataVersion.getId());
                dataVersionModel.setDescription(dataVersion.getDescription());
                dataVersionModel.setCommenceDate(DateUtil.dateToString(dataVersion.getCommenceDate()));
                dataVersionModel.setCreateDate(DateUtil.dateToString(dataVersion.getCreateDate()));
                if (activeModel != null && dataVersion.getName().equals(activeModel.getName())) {
                    dataVersionModel.setActive(true);
                } else {
                    dataVersionModel.setActive(false);
                }
                if (dataVersion.getBaseVersion() != null) {
                    dataVersionModel.setBaseVersion(dataVersion.getBaseVersion().getName());
                }
                dataVersionModelList.add(dataVersionModel);
            }
            return dataVersionModelList;
        } catch (Exception e) {
            logger.error("Exception in returning Data Version list : ", e);
            return null;
        }
    }
}
