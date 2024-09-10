package org.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.services.MyService;

import java.util.List;


@Path("/sending")
@Slf4j
public class SendingServiceResource {

    @Inject
    MyService myService;

    @POST
    @Path("/uploadFiles")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFiles(@RestForm("files") List<FileUpload> fileUploads) {
        log.info("Number of files uploaded: " + fileUploads.size());

        String restClientResponse = myService.uploadFiles(fileUploads);

        return Response.ok(restClientResponse).build();
    }
}

