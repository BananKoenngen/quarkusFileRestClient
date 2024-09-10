package org.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.external.ReceivingServiceRestClient;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataOutput;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.File;
import java.util.List;

@ApplicationScoped
public class MyService {

    @Inject
    @RestClient
    ReceivingServiceRestClient receivingServiceRestClient;

    public String uploadFiles(List<FileUpload> fileUploads) {
        MultipartFormDataOutput multipartFormDataOutput = new MultipartFormDataOutput();

        for (FileUpload fileUpload : fileUploads) {
            File file = fileUpload.filePath().toFile();
            multipartFormDataOutput.addFormData("files", file, MediaType.APPLICATION_OCTET_STREAM_TYPE, file.getName());
        }

        Response response = receivingServiceRestClient.uploadFiles(multipartFormDataOutput);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to upload files. Status: " + response.getStatus());
        }

        return response.readEntity(String.class);
    }
}

