package com.beside.stage.mindserver.q1.domain;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterImage {
    @Enumerated(EnumType.STRING)
    private CharacterImagePosition characterImagePosition;

    @Embedded
    private UploadFile uploadFile;

    @Builder
    public CharacterImage(final String fileName, final String storedFileName, final CharacterImagePosition characterImagePosition) {
        this.uploadFile = new UploadFile(fileName, storedFileName);
        this.characterImagePosition = characterImagePosition;
    }
}
