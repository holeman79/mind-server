package com.beside.stage.mindserver.q1.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Answer {
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AnswerNumber answerNumber;

    private String text;

    private String resultText;

    @Embedded
    private CharacterImage resultImage;

    public Answer(final AnswerNumber answerNumber, final String text, final String resultText, final CharacterImage resultImage) {
        this.answerNumber = answerNumber;
        this.text = text;
        this.resultText = resultText;
        this.resultImage = resultImage;
    }
}
