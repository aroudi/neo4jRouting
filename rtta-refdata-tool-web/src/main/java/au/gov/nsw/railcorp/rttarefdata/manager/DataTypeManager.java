package au.gov.nsw.railcorp.rttarefdata.manager;

import au.gov.nsw.railcorp.rttarefdata.domain.Gauge;
import au.gov.nsw.railcorp.rttarefdata.domain.PowerType;
import au.gov.nsw.railcorp.rttarefdata.domain.ServiceType;
import au.gov.nsw.railcorp.rttarefdata.repositories.GaugeRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.PowerTypeRepository;
import au.gov.nsw.railcorp.rttarefdata.repositories.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by arash on 10/11/14.
 */
@Component
@Transactional
public class DataTypeManager implements IDataTypeManager {
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private PowerTypeRepository powerTypeRepository;

    @Autowired
    private GaugeRepository gaugeRepository;

    /**
     * createServiceType.
     * @param name name
     * @param description description
     * @return ServiceType
     */
    public ServiceType createServiceType(String name, String description) {
        return serviceTypeRepository.save(new ServiceType(name, description));
    }

    /**
     * createPowerType.
     * @param name name
     * @param description description
     * @return PowerType
     */
    public PowerType createPowerType(String name, String description) {
        return powerTypeRepository.save(new PowerType(name, description));
    }


    /**
     * create new gauge.
     * @param name name
     * @param desc desc
     * @return Gauge gauge
     */
    public Gauge createGauge(String name, String desc) {
        final Gauge gauge = new Gauge(name, desc);
        gaugeRepository.save(gauge);
        return gauge;
    }

    /**
     * Search power type per name. if not exists then create it. used for importing data.
     * @param name name
     * @param description description
     * @return PowerType
     */
    public PowerType addPowerType(String name, String description) {
        PowerType powerType = powerTypeRepository.findBySchemaPropertyValue("name", name);
        if (powerType == null) {
            powerType = createPowerType(name, description);
        }
        return powerType;
    }

    /**
     * Search service type per name. if not exists then create it. used for importing data.
     * @param name name
     * @param description description
     * @return PowerType
     */
    public ServiceType addServiceType(String name, String description) {
        ServiceType serviceType = serviceTypeRepository.findBySchemaPropertyValue("name", name);
        if (serviceType == null) {
            serviceType = createServiceType(name, description);
        }
        return serviceType;
    }

    /**
     * add gauge.
     * @param name name
     * @param description description
     * @return Gauge
     */
    public Gauge addGauge(String name, String description) {
        Gauge gauge = gaugeRepository.findBySchemaPropertyValue("name", name);
        if (gauge == null) {
            gauge = createGauge(name, description);
        }
        return gauge;
    }

}
