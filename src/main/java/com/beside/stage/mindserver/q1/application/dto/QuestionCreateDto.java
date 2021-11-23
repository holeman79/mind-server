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

    private List<AnswerCreateDto> answerCreateDtos;

    public Question toEntity(final List<Answer> answers) {
        return Question.builder()
                .questionNumber(new QuestionNumber(number))
                .content(content)
                .answers(answers)
                .build();
    }

    @Setter
    @Getter
    public static class AnswerCreateDto {
        private int number;

        private String text;

        private String resultText;

        private CharacterImagePosition characterImagePosition;

        private MultipartFile imageFile;

        public Answer toEntity(final UploadFile uploadFile) {
            return Answer.builder()
                    .answerNumber(new AnswerNumber(number))
                    .text(text)
                    .resultText(resultText)
                    .resultImage(CharacterImage.builder()
                            .fileName(uploadFile.getFileName())
                            .storedFileName(uploadFile.getStoredFileName())
                            .characterImagePosition(characterImagePosition)
                            .build())
                    .build();
        }
    }
}
