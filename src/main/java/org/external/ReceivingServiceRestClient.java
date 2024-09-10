package org.external;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.MultipartForm;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataOutput;

@RegisterRestClient(configKey = "receiving-service")
public interface ReceivingServiceRestClient {

    @POST
    @Path("/receiving/uploadFiles")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    Response uploadFiles(@MultipartForm MultipartFormDataOutput data);
}

