package com.beside.stage.mindserver.q1.domain;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "ANSWERS")
public class Answer {
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AnswerNumber answerNumber;

    private String text;

    private String resultText;

    private boolean isRandom;

    @Embedded
    private UploadFile uploadFile;

    @Builder
    private Answer(final AnswerNumber answerNumber, final String text, final String resultText, final boolean isRandom, final UploadFile uploadFile) {
        this.answerNumber = answerNumber;
        this.text = text;
        this.resultText = resultText;
        this.isRandom = isRandom;
        this.uploadFile = uploadFile;
    }
}
