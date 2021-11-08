package com.beside.stage.mindserver.q1.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CharacterImage {
    private final String fileName;

    private final String storedFileName;

    @Enumerated(EnumType.STRING)
    private final CharacterImagePosition characterImagePosition;

    public CharacterImage(final String fileName, final String storedFileName, final CharacterImagePosition characterImagePosition) {
        this.fileName = fileName;
        this.storedFileName = storedFileName;
        this.characterImagePosition = characterImagePosition;
    }
}
