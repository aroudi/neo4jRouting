package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.request.DataVersionModel;

import java.util.Date;
import java.util.List;

/**
 * Created by arash on 26/02/2015.
 */
public interface IDataVersionManager {
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
    DataVersion addDataVersion(String name, String description, Date createDate, Date commenceDate, boolean isActive, String baseVersion);

    /**
     * create a new DataVersionObject.
     * @param id id
     * @param name name
     * @param description description
     * @param createDate createDate
     * @return DataVersion object
     */
    DataVersion editDataVersion(long id, String name, String description, Date createDate);

    /**
     * Search DataVersion per name.
     * @param name name
     * @return DataVersion
     */
    DataVersion searchDataVersionPerName(String name);

    /**
     * get Active Data Version.
     * @return DataVersion
     */
    DataVersion getActiveDataVersion ();

    /**
     * Return DataVersion per commenceDate.
     * @param commenceDate commenceDate
     * @return DataVersion
     */
    DataVersion getDataVersionByCommenceDate(Date commenceDate);

    /**
     * Return DataVersion per commenceDate.
     * @param id id
     * @return DataVersion
     */
    DataVersion getDataVersionById(long id);
    /**
     * Remove DataVersion.
     * @param id id
     * @return DataVersion
     */
    boolean removeDataVersion(long id);

    /**
     * return all data versions.
     * @return List of DataVersionModel
     */
    List<DataVersionModel> getAllDataVersion();
}
