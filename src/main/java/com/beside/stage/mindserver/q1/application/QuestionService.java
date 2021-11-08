package com.beside.stage.mindserver.q1.application;

import com.beside.stage.mindserver.q1.application.dto.QuestionDto;
import com.beside.stage.mindserver.q1.domain.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public List<QuestionDto> findAll() {
        return questionRepository.findAll()
                .stream()
                .map(QuestionDto::of)
                .collect(Collectors.toList());
    }
}
