package com.raj.ms.product.controller;

import com.raj.ms.product.service.StorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(path = "/api/prdms/file")
public class FileController {

  private final StorageService storageService;

  public FileController(StorageService storageService) {
    this.storageService = storageService;
  }

  @RequestMapping(
    method = RequestMethod.POST,
    consumes={MediaType.ALL_VALUE}
  )
  public ResponseEntity<?> getFile(
    @RequestPart MultipartFile file,
    @RequestPart String name
  ) {

    System.out.println(file);
    System.out.println(name);

    File convFile = new File(file.getOriginalFilename());

    try (FileOutputStream fos = new FileOutputStream(convFile)) {
      fos.write( file.getBytes() );
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }


    try {
      return ResponseEntity.status(200).body(storageService.uploadFile(convFile));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
