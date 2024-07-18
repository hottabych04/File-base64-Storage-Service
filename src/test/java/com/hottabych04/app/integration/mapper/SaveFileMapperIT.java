package com.hottabych04.app.integration.mapper;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.integration.IntegrationTestBase;
import com.hottabych04.app.integration.annotation.IT;
import com.hottabych04.app.mapper.SaveFileMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class SaveFileMapperIT extends IntegrationTestBase {

    private final SaveFileMapper saveFileMapper;

    @Test
    void map(){
        FileDto fileDto = FileDto.builder()
                .title("testFile")
                .description("testDescription")
                .creationDate(LocalDateTime.now())
                .data("432-0opfdjmgio34gniorngoiang30j4t")
                .build();

        File file = saveFileMapper.map(fileDto);

        assertAll(
                () -> assertThat(fileDto.getTitle()).isEqualTo(file.getTitle()),
                () -> assertThat(fileDto.getDescription()).isEqualTo(file.getDescription()),
                () -> assertThat(fileDto.getCreationDate()).isEqualTo(file.getCreationDate()),
                () -> assertThat(fileDto.getData()).isEqualTo(file.getData())
        );
    }
}