package com.beside.stage.mindserver.q1.api;

import com.beside.stage.mindserver.q1.application.QuestionService;
import com.beside.stage.mindserver.q1.application.dto.QuestionCreateDto;
import com.beside.stage.mindserver.q1.application.dto.QuestionDto;
import com.beside.stage.mindserver.q1.domain.Question;
import com.beside.stage.mindserver.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/q1/questions")
public class QuestionApi {
    private final QuestionService questionService;

    @GetMapping
    public ApiResponseDto<List<QuestionDto>> findAll() {
        List<QuestionDto> questionDtos = questionService.findAll();
        return ApiResponseDto.OK(questionDtos);
    }

    @PostMapping
    public ApiResponseDto<?> save(@ModelAttribute QuestionCreateDto questionCreateDto) throws IOException {
        Question question = questionService.save(questionCreateDto);
        return ApiResponseDto.OK(QuestionDto.of(question));
    }
}
