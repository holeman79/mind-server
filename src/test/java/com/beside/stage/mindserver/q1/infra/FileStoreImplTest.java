package com.beside.stage.mindserver.q1.infra;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileStoreImplTest {
    private FileStoreImpl fileStore = new FileStoreImpl();

    @Test
    void createAdditionalDirectories() {
        String q1 = fileStore.createAdditionalDirectories(Arrays.array("Q1", "Q2"));
        assertThat(q1).isEqualTo("Q1/Q2/");
    }
}