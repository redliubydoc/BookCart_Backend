package com.raj.ms.product.service;

import java.io.File;
import java.io.IOException;

public interface StorageService {

  String uploadFile(File file) throws IOException;
  byte[] downloadFile(String fileId) throws IOException;
}
