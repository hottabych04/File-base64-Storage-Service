package com.hottabych04.app.integration.mapper;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.integration.IntegrationTestBase;
import com.hottabych04.app.mapper.PageFileReadMapper;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
class PageFileReadMapperIT extends IntegrationTestBase {

    private final PageFileReadMapper pageFileReadMapper;

    private static final File FILE = File.builder()
            .title("testFile")
            .description("testDescription")
            .creationDate(LocalDateTime.now())
            .data("432-0opfdjmgio34gniorngoiang30j4t")
            .build();

    @Test
    void map() {
        List<File> files = List.of(FILE, FILE);
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("creationDate"));
        Page<File> page = new PageImpl<>(files, pageRequest, 15);

        Assertions.assertThat(pageFileReadMapper.map(page).getContent())
                .isEqualTo(files);
    }
}