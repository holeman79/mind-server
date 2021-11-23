package com.beside.stage.mindserver.q1.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question {
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private QuestionNumber questionNumber;

    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ID")
    private List<Answer> answers;

    @Builder
    private Question(final QuestionNumber questionNumber, final String content, final List<Answer> answers) {
        this.questionNumber = questionNumber;
        this.content = content;
        this.answers = answers;
    }
}
