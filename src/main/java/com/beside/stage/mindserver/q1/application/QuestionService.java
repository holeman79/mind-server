package com.beside.stage.mindserver.q1.application;

import com.beside.stage.mindserver.generic.domain.uploadfile.UploadFile;
import com.beside.stage.mindserver.q1.application.dto.QuestionCreateDto;
import com.beside.stage.mindserver.q1.application.dto.QuestionDto;
import com.beside.stage.mindserver.q1.domain.Answer;
import com.beside.stage.mindserver.q1.domain.Question;
import com.beside.stage.mindserver.q1.domain.QuestionNumber;
import com.beside.stage.mindserver.q1.domain.QuestionRepository;
import com.beside.stage.mindserver.q1.infra.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.beside.stage.mindserver.q1.application.dto.QuestionCreateDto.*;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final FileStore fileStore;

    @Transactional(readOnly = true)
    public List<QuestionDto> findAll() {
        return questionRepository.findAll()
                .stream()
                .map(QuestionDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Question save(final QuestionCreateDto questionCreateDto) throws IOException {
        final List<Answer> answers = new ArrayList<>();
        String questionNumber = new QuestionNumber(questionCreateDto.getNumber()).toString();
        for (AnswerCreateDto answerCreateDto : questionCreateDto.getAnswerCreateDtos()) {
            UploadFile uploadFile = fileStore.storeFile(answerCreateDto.getImageFile(), questionNumber);
            answers.add(answerCreateDto.toEntity(uploadFile));
        }
        List<UploadFile> uploadFiles = fileStore.storeFiles(questionCreateDto.getRandomOptionImages(), questionNumber);
        return questionRepository.save(questionCreateDto.toEntity(answers, uploadFiles));
    }
}
