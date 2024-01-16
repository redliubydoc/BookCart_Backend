package com.raj.ms.product.service;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class GdriveStorageService implements StorageService {

  private final Drive gDrive;
  private final String serviceFolder;

  @Autowired
  public GdriveStorageService(Drive gDrive, @Value("${gcp.gdrive.serviceFolder}") String serviceFolder) {
    this.gDrive = gDrive;
    this.serviceFolder = serviceFolder;
  }

  @Override
  public String uploadFile(File file) throws IOException {

    com.google.api.services.drive.model.File gDriveFile = new com.google.api.services.drive.model.File();
    gDriveFile.setName(file.getName());
    gDriveFile.setParents(List.of(serviceFolder));

    String fileId = gDrive.files()
      .create(gDriveFile, new FileContent(MediaType.ALL_VALUE, file))
      .execute()
      .getWebContentLink();

    return fileId;
  }

  public byte[] downloadFile(String fileId) throws IOException {

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    gDrive.files()
      .get(fileId)
      .executeMediaAndDownloadTo(bos);

    return bos.toByteArray();
  }
}
