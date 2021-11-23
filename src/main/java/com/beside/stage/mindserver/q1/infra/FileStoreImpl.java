package com.beside.stage.mindserver.q1.infra;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStoreImpl implements FileStore {
    private static final String POINT = ".";

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(final String filename) {
        return fileDir + filename;
    }

    @Override
    public List<UploadFile> storeFiles(final List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            uploadFiles.add(storeFile(multipartFile));
        }
        return uploadFiles;
    }

    @Override
    public UploadFile storeFile(final MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("파일이 비었습니다.");
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String storedFileName = createStoredFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storedFileName)));
        return new UploadFile(originalFileName, storedFileName);
    }

    private String createStoredFileName(final String originalFileName) {
        String ext = extractExt(originalFileName);
        UUID uuid = UUID.randomUUID();
        return uuid + POINT + ext;
    }

    private String extractExt(final String originalFileName) {
        int pos = originalFileName.lastIndexOf(POINT);
        return originalFileName.substring(pos + 1);
    }
}
