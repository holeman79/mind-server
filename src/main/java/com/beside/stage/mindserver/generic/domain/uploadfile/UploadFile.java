package com.beside.stage.mindserver.generic.domain.uploadfile;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Embeddable
public class UploadFile {
    private String fileName;
    private String storedFileName;
}
