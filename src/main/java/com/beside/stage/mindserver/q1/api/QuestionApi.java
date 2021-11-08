package com.beside.stage.mindserver.q1.api;

import com.beside.stage.mindserver.q1.application.QuestionService;
import com.beside.stage.mindserver.q1.application.dto.QuestionDto;
import com.beside.stage.mindserver.response.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionApi {
    private final QuestionService questionService;

    @GetMapping
    public ApiResponseDto<List<QuestionDto>> findAll() {
        List<QuestionDto> questionDtos = questionService.findAll();
        return ApiResponseDto.OK(questionDtos);
    }
}
