package com.raj.ms.product.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.core.io.Resource;

@Configuration
public class GDriveConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(GDriveConfig.class);
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	@Value("classpath:gdrive_credentials.json")
	private Resource credentialFile;

	@Value("${gcp.gdrive.serviceEmail}")
	private String serviceEmail;

	@Bean
	public Drive getGDrive() throws IOException {

    LOGGER.debug(String.valueOf(credentialFile));
    LOGGER.debug(String.valueOf(serviceEmail));

		Credential credential = GoogleCredential
      .fromStream(credentialFile.getInputStream())
      .createScoped(SCOPES);

		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
      .setApplicationName("BookCart")
			.build();
	}
}
