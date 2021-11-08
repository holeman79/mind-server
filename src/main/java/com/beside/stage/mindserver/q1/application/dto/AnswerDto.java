package com.beside.stage.mindserver.q1.application.dto;

import com.beside.stage.mindserver.q1.domain.Answer;
import com.beside.stage.mindserver.q1.domain.AnswerNumber;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AnswerDto {
    private Long answerId;

    private String answerNumber;

    private String text;

    @Builder
    private AnswerDto(final Long answerId, final AnswerNumber answerNumber, final String text) {
        this.answerId = answerId;
        this.answerNumber = answerNumber.toString();
        this.text = text;
    }

    public static AnswerDto of(final Answer answer) {
        return AnswerDto.builder()
                .answerId(answer.getId())
                .answerNumber(answer.getAnswerNumber())
                .text(answer.getText())
                .build();
    }
}
