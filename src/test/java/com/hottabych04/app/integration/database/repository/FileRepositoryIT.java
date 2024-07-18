package com.hottabych04.app.integration.database.repository;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.database.repository.FileRepository;
import com.hottabych04.app.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Transactional
@Rollback
class FileRepositoryIT extends IntegrationTestBase {

    private final FileRepository fileRepository;

    @Test
    void create(){
        File file = File.builder()
                .title("testFile")
                .description("testDescription")
                .creationDate(LocalDateTime.now())
                .data("432-0opfdjmgio34gniorngoiang30j4t")
                .build();

        assertThat(fileRepository.save(file).getId())
                .isNotNull();
    }

    @Test
    void findById(){

        Long id = saveTestFile();

        assertThat(fileRepository.findById(id))
                .isPresent();
    }

    @Test
    void pageableFindAll(){
        List<Integer> pagesNumbers = List.of(0, 1, 2);
        Sort sort = Sort.by("creationDate");

        for (Integer pageNum : pagesNumbers) {
            saveTestFile();
            saveTestFile();
        }

        for (Integer pageNum : pagesNumbers) {
            assertThat(
                    PageRequest.of(pageNum, 2, sort).getPageSize()
            )
                    .isEqualTo(2);
        }

    }

    private Long saveTestFile(){
        File file = File.builder()
                .title("testFile")
                .description("testDescription")
                .creationDate(LocalDateTime.now())
                .data("432-0opfdjmgio34gniorngoiang30j4t")
                .build();

        return fileRepository.save(file).getId();
    }

}