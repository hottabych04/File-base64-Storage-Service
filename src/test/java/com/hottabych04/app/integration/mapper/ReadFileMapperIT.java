package com.hottabych04.app.integration.mapper;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.integration.IntegrationTestBase;
import com.hottabych04.app.mapper.ReadFileMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class ReadFileMapperIT extends IntegrationTestBase {

    private final ReadFileMapper readFileMapper;

    @Test
    void map() {
        File file = File.builder()
                .title("testFile")
                .description("testDescription")
                .creationDate(LocalDateTime.now())
                .data("432-0opfdjmgio34gniorngoiang30j4t")
                .build();

        FileDto fileDto = readFileMapper.map(file);

        assertAll(
                () -> assertThat(file.getTitle()).isEqualTo(fileDto.getTitle()),
                () -> assertThat(file.getDescription()).isEqualTo(fileDto.getDescription()),
                () -> assertThat(file.getCreationDate()).isEqualTo(fileDto.getCreationDate()),
                () -> assertThat(file.getData()).isEqualTo(fileDto.getData())
        );
    }
}