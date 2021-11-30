package com.beside.stage.mindserver.q1.domain;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "QUESTIONS")
public class Question {
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private QuestionNumber questionNumber;

    private String content;

    @Enumerated(EnumType.STRING)
    private CharacterImagePosition characterImagePosition;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ID")
    private List<Answer> answers;

    @ElementCollection
    @CollectionTable(name = "RANDOM_OPTION_IMAGES", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    private List<UploadFile> randomOptionImages = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "next_question_number"))
    })
    private QuestionNumber nextQuestionNumber;

    @Builder
    private Question(final QuestionNumber questionNumber, final String content, final CharacterImagePosition characterImagePosition,
                    final List<Answer> answers, final List<UploadFile> randomOptionImages) {
        this.questionNumber = questionNumber;
        this.content = content;
        this.characterImagePosition = characterImagePosition;
        this.answers = answers;
        this.randomOptionImages = randomOptionImages;
    }

    public void updateNextQuestionNumber(final QuestionNumber nextQuestionNumber) {
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
