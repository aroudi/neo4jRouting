package au.gov.nsw.railcorp.rttarefdata.rest;


import au.gov.nsw.railcorp.rttarefdata.domain.DataVersion;
import au.gov.nsw.railcorp.rttarefdata.request.DataVersionModel;
import au.gov.nsw.railcorp.rttarefdata.response.DataVersionResponse;
import au.gov.nsw.railcorp.rttarefdata.response.Response;
import au.gov.nsw.railcorp.rttarefdata.service.DataVersionService;
import au.gov.nsw.railcorp.rttarefdata.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by arash on 27/02/2015.
 */

@Component
@Path("versions")
public class VersionService {
    private final Logger logger = LoggerFactory.getLogger(VersionService.class);
    @Context
    private UriInfo uriInfo;
    @Context
    private Request request;

    @Autowired
    private DataVersionService dataVersionService;

    /**
     * Return Data Version list in Json format.
     * @return DataVersion List
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DataVersionModel> getAllDataVersions() {
        return dataVersionService.getAllDataVersions();
    }
    /**
     * adding new data version.
     * @param dataVersionModel dataVersionModel
     * @return response
     */

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DataVersionResponse addDataVersion (DataVersionModel dataVersionModel) {
        return dataVersionService.createDataVersion(dataVersionModel);
    }

    /**
     * edit data version.
     * @param dataVersionModel dataVersionModel
     * @return response
     */
    @Path("/edit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DataVersionResponse editDataVersion (DataVersionModel dataVersionModel) {
        return dataVersionService.editDataVersion(dataVersionModel);
    }

    /**
     * delete dataversion based on id.
     * @param id id
     * @return Response
     */
    @GET
    @Path("/delete/{id}")
    public Response deleteDataVersion (@PathParam("id") long id) {
        return dataVersionService.removeDataVersion(id);
    }

    /**
     * delete dataversion based on id.
     * @param id id
     */
    @GET
    @Path("/setWorkingVersion/{id}")
    public void setWorkingVersion (@PathParam("id") long id) {
        dataVersionService.setWorkingVersion(id);
    }
    /**
     * Return Active Data Version.
     * @return DataVersion
     */
    @GET
    @Path("/activeVersion")
    @Produces(MediaType.APPLICATION_JSON)
    public DataVersionModel getActiveDataVersion() {
        final DataVersion dataVersion = dataVersionService.getActiveVersion();
        if (dataVersion == null) {
            return null;
        }
        final DataVersionModel dataVersionModel = new DataVersionModel();
        dataVersionModel.setId(dataVersion.getId());
        dataVersionModel.setName(dataVersion.getName());
        dataVersionModel.setActive(true);
        if (dataVersion.getBaseVersion() != null) {
            dataVersionModel.setBaseVersion(dataVersion.getBaseVersion().getName());
        }
        dataVersionModel.setDescription(dataVersion.getDescription());
        dataVersionModel.setCreateDate(DateUtil.dateToString(dataVersion.getCreateDate()));
        dataVersionModel.setCommenceDate(DateUtil.dateToString(dataVersion.getCommenceDate()));
        return dataVersionModel;
    }
}
