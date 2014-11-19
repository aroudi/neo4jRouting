package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Gauge;
import au.gov.nsw.railcorp.rttarefdata.domain.PowerType;
import au.gov.nsw.railcorp.rttarefdata.domain.ServiceType;

/**
 * Created by arash on 10/11/14.
 */
public interface IDataTypeManager {
    /**
     * createServiceType.
     * @param name name
     * @param description description
     * @return ServiceType
     */
    ServiceType createServiceType(String name, String description);

    /**
     * createPowerType.
     * @param name name
     * @param description description
     * @return PowerType
     */
    PowerType createPowerType(String name, String description);

    /**
     * Search power type per name. if not exists then create it. used for importing data.
     * @param name name
     * @param description description
     * @return PowerType
     */
    PowerType addPowerType(String name, String description);

    /**
     * Search service type per name. if not exists then create it. used for importing data.
     * @param name name
     * @param description description
     * @return PowerType
     */
    ServiceType addServiceType(String name, String description);
    /**
     * add gauge.
     * @param name name
     * @param description description
     * @return Gauge
     */
    Gauge addGauge(String name, String description);

}
