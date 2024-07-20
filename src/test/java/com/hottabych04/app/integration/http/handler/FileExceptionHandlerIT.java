package com.hottabych04.app.integration.http.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class FileExceptionHandlerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void invalidBody() {

        String uri = "/api/v1/files/save";

        FileDto fileDto = FileDto.builder()
                .title("test")
                .description("testDescription")
                .creationDate(LocalDateTime.now())
                .data("432-0opfdjmgio34gniorngoiang30j4t")
                .build();

        String invalidFileDto = objectMapper.writeValueAsString(fileDto).replaceAll("\"", "");

        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidFileDto))
                .andExpect(status().is4xxClientError());

    }
}