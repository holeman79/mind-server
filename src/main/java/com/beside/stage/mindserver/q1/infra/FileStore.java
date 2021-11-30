package com.beside.stage.mindserver.q1.infra;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStore {
    String getFullPath(String filename);

    UploadFile storeFile(MultipartFile multipartFile, String... inputAdditionalDirectories) throws IOException;

    List<UploadFile> storeFiles(List<MultipartFile> multipartFiles, String... inputAdditionalDirectories) throws IOException;

}
