package com.beside.stage.mindserver.q1.application.dto;

import com.beside.stage.mindserver.q1.domain.Question;
import com.beside.stage.mindserver.q1.domain.QuestionNumber;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionDto {
    private Long questionId;

    private String questionNumber;

    private String content;

    private List<AnswerDto> answers;

    @Builder
    private QuestionDto(final Long questionId, final QuestionNumber questionNumber,
                        final String content, final List<AnswerDto> answers) {
        this.questionId = questionId;
        this.questionNumber = questionNumber.toString();
        this.content = content;
        this.answers = answers;
    }

    public static QuestionDto of(final Question question) {
        return QuestionDto.builder()
                .questionId(question.getId())
                .questionNumber(question.getQuestionNumber())
                .content(question.getContent())
                .answers(toAnswers(question))
                .build();
    }

    private static List<AnswerDto> toAnswers(final Question question) {
        return question.getAnswers()
                .stream()
                .map(AnswerDto::of)
                .collect(Collectors.toList());
    }
}
