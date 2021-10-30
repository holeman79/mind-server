package com.beside.stage.mindserver.domain.q1;

import javax.persistence.Embeddable;

@Embeddable
public class Image {
    private final String fileName;

    private final String storedFileName;

    public Image(final String fileName, final String storedFileName) {
        this.fileName = fileName;
        this.storedFileName = storedFileName;
    }
}
