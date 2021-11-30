package com.beside.stage.mindserver.q1.application.dto;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import com.beside.stage.mindserver.q1.domain.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class QuestionCreateDto {
    private int number;

    private String content;

    private CharacterImagePosition characterImagePosition;

    private List<AnswerCreateDto> answerCreateDtos;

    private List<MultipartFile> randomOptionImages;

    public Question toEntity(final List<Answer> answers, final List<UploadFile> uploadFiles) {
        QuestionNumber questionNumber = new QuestionNumber(5);
        return Question.builder()
                .questionNumber(new QuestionNumber(number))
                .content(content)
                .characterImagePosition(characterImagePosition)
                .answers(answers)
                .randomOptionImages(uploadFiles)
                .build();
    }

    @Setter
    @Getter
    public static class AnswerCreateDto {
        private int number;

        private String text;

        private String resultText;

        private boolean isRandom;

        private MultipartFile imageFile;

        public Answer toEntity(final UploadFile uploadFile) {
            return Answer.builder()
                    .answerNumber(new AnswerNumber(number))
                    .text(text)
                    .resultText(resultText)
                    .isRandom(isRandom)
                    .uploadFile(uploadFile)
                    .build();
        }
    }
}
