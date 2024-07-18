package com.hottabych04.app.integration.http.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class FileControllerIT extends IntegrationTestBase {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private static final FileDto FILE_DTO = FileDto.builder()
            .title("testFile")
            .description("testDescription")
            .creationDate(LocalDateTime.now())
            .data("432-0opfdjmgio34gniorngoiang30j4t")
            .build();

    private static final File FILE = File.builder()
            .title("testFile")
            .description("testDescription")
            .creationDate(LocalDateTime.now())
            .data("432-0opfdjmgio34gniorngoiang30j4t")
            .build();

    @SneakyThrows
    @Test
    void saveFile(){
        String uri = "/api/v1/files/save";

        MvcResult mvcResult = mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(FILE_DTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
    }

    @SneakyThrows
    @Test
    void findById(){
        String uri = "/api/v1/files/get/{id}";


        for (int i = 1; i < 16; i++) {
            MvcResult mvcResult = mockMvc.perform(get(uri, i))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
        }

    }

    @SneakyThrows
    @Test
    void pageableFindAll(){
        String uri = "/api/v1/files/get/page/{page}";

        for (int i = 1; i < 4; i++) {
            MvcResult mvcResult = mockMvc.perform(get(uri, i))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());

            List<FileDto> list = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<FileDto>>(){});

            assertThat(list.size()).isEqualTo(5);
        }

    }

}