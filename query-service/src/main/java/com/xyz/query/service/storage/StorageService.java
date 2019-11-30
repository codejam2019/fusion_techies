package com.xyz.query.service.storage;

import java.io.File;

public interface StorageService {

  void delete(String fileName);

  void upload(File file);
}
