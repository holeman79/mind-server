package com.beside.stage.mindserver.generic.domain.uploadfile;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UploadFile {
    private String fileName;
    private String storedFileName;
}
