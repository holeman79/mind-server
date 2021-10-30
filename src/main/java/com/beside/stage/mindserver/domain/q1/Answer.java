package com.beside.stage.mindserver.domain.q1;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int answerNumber;

    private String text;

    private String resultText;

    @Embedded
    private Image resultImage;
}
