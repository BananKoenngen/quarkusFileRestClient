package org.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.util.List;

@Path("/receiving")
@Slf4j
public class ReceivingServiceResource {

    @POST
    @Path("/uploadFiles")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFiles(@RestForm("files") List<FileUpload> fileUploads) {
        log.info("Number of files uploaded: " + (fileUploads == null ? 0 : fileUploads.size()));

        if (fileUploads != null) {
            for (FileUpload fileUpload : fileUploads) {
                log.info("Received file: " + fileUpload.fileName());
            }
        }

        return Response.ok("Files uploaded successfully").build();
    }
}

