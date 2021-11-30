package com.beside.stage.mindserver.q1.api.document;

import com.beside.stage.mindserver.api.document.ApiDocumentationTest;
import com.beside.stage.mindserver.q1.application.QuestionService;
import com.beside.stage.mindserver.q1.application.dto.AnswerDto;
import com.beside.stage.mindserver.q1.application.dto.QuestionDto;
import com.beside.stage.mindserver.q1.domain.AnswerNumber;
import com.beside.stage.mindserver.q1.domain.QuestionNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class QuestionReadDocumentTest extends ApiDocumentationTest {
    @MockBean
    private QuestionService questionService;

    @DisplayName("질문지 전체 조회 Document 생성")
    @Test
    void findAll() throws Exception {
        //given
        List<QuestionDto> questionDtos = Arrays.asList(
                QuestionDto.builder()
                        .questionId(1L)
                        .questionNumber(new QuestionNumber(1))
                        .content("길을 걷가가 쓰러진 사람을 보았다 어떻게 할까?")
                        .answers(Arrays.asList(
                                AnswerDto.builder()
                                        .answerId(1L)
                                        .answerNumber(new AnswerNumber(1))
                                        .text("스을쩍 지나쳐 간다")
                                        .build(),
                                AnswerDto.builder()
                                        .answerId(2L)
                                        .answerNumber(new AnswerNumber(2))
                                        .text("헉! 왜그러세요! 일어나세요!")
                                        .build(),
                                AnswerDto.builder()
                                        .answerId(3L)
                                        .answerNumber(new AnswerNumber(3))
                                        .text("도와줘야되나, 말아야하나 10분째 고민중")
                                        .build(),
                                AnswerDto.builder()
                                        .answerId(4L)
                                        .answerNumber(new AnswerNumber(4))
                                        .text("너무 바쁘게 지나가느라 보지도 못했다.")
                                        .build()
                        ))
                        .build(),
                QuestionDto.builder()
                        .questionId(2L)
                        .questionNumber(new QuestionNumber(2))
                        .content("당신의 지금까지 연애 횟수는?")
                        .answers(Arrays.asList(
                                AnswerDto.builder()
                                        .answerId(5L)
                                        .answerNumber(new AnswerNumber(1))
                                        .text("있었는지 먼저 물어보세요")
                                        .build(),
                                AnswerDto.builder()
                                        .answerId(6L)
                                        .answerNumber(new AnswerNumber(2))
                                        .text("1-2회")
                                        .build(),
                                AnswerDto.builder()
                                        .answerId(7L)
                                        .answerNumber(new AnswerNumber(3))
                                        .text("2-4회")
                                        .build(),
                                AnswerDto.builder()
                                        .answerId(8L)
                                        .answerNumber(new AnswerNumber(4))
                                        .text("난 그 이상인데?")
                                        .build()
                        ))
                        .build()
        );

        given(questionService.findAll()).willReturn(questionDtos);

        //when
        String requestUrl = "/api/q1/questions";
        ResultActions result = this.mockMvc.perform(
                get(requestUrl)
        );

        //then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("data[0].questionId").value(1L))
                .andDo(
                        document("questions-find-all",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseHeaders(
                                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type 헤더(hal+json)")
                                ),
                                responseFields(beneathPath("data[]").withSubsectionId("data"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("question 아이디"),
                                        fieldWithPath("questionNumber").type(JsonFieldType.STRING).description("question 번호(ex: Q1)"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("question 내용(ex: 연애횟수는?)"),
                                        fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("응답지 리스트 answer 아이디"),
                                        fieldWithPath("answers[].answerNumber").type(JsonFieldType.STRING).description("응답지 리스트 answer 번호(ex: A1)"),
                                        fieldWithPath("answers[].text").type(JsonFieldType.STRING).description("응답지 리스트 answer 내용(ex: 모태솔로)")
                                )
                        )
                );
    }


}
