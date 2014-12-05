package au.gov.nsw.railcorp.rttarefdata.mapresult;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

/**
 * Created by arash on 5/12/14.
 */
@MapResult
public interface IRefData {
    /**
     * RefDataId.
     * @return id
     */
    @ResultColumn("refDataId")
    Long getRefDataId();

    /**
     * RefDataCode.
     * @return RefDataCode
     */
    @ResultColumn("refDataCode")
    String getRefDataCode();

    /**
     * RefDataName.
     * @return refDataName
     */
    @ResultColumn("refDataName")
    String getRefDataName();
}
