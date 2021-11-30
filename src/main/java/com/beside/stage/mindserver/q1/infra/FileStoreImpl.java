package com.beside.stage.mindserver.q1.infra;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FileStoreImpl implements FileStore {
    private static final String POINT = ".";

    private static final String SLASH = "/";

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(final String filename) {
        return fileDir + filename;
    }

    @Override
    public List<UploadFile> storeFiles(final List<MultipartFile> multipartFiles, final String... inputAdditionalDirectories) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            uploadFiles.add(storeFile(multipartFile, inputAdditionalDirectories));
        }
        return uploadFiles;
    }

    public UploadFile storeFile(final MultipartFile multipartFile, final String... inputAdditionalDirectories) throws IOException {
        if (Objects.isNull(multipartFile) || multipartFile.isEmpty()) {
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String storedFileName = createStoredFileName(originalFileName);
        String additionalDirectories = createAdditionalDirectories(inputAdditionalDirectories);
        multipartFile.transferTo(new File(getFullPath(additionalDirectories + storedFileName)));
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

    String createAdditionalDirectories(final String[] additionalDirectories) {
        return Arrays.stream(additionalDirectories)
                .map(directory -> directory + SLASH)
                .collect(Collectors.joining());
    }
}
