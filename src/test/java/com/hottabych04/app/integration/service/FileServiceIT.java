package com.hottabych04.app.integration.service;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.database.repository.FileRepository;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.dto.PageFileDto;
import com.hottabych04.app.integration.IntegrationTestBase;
import com.hottabych04.app.mapper.PageFileReadMapper;
import com.hottabych04.app.mapper.ReadFileMapper;
import com.hottabych04.app.mapper.SaveFileMapper;
import com.hottabych04.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class FileServiceIT extends IntegrationTestBase{

    private static final Long ID = 1L;

    private static final FileDto FILE_DTO = FileDto.builder()
            .title("testFile")
            .description("testDescription")
            .creationDate(LocalDateTime.now())
            .data("432-0opfdjmgio34gniorngoiang30j4t")
            .build();

    private static final File FILE = File.builder()
            .id(ID)
            .title("testFile")
            .description("testDescription")
            .creationDate(LocalDateTime.now())
            .data("432-0opfdjmgio34gniorngoiang30j4t")
            .build();

    @Autowired
    private FileService fileService;
    @MockBean
    private FileRepository fileRepository;
    @MockBean
    private ReadFileMapper readFileMapper;
    @MockBean
    private SaveFileMapper saveFileMapper;
    @MockBean
    private PageFileReadMapper pageFileReadMapper;


    @Test
    void saveFile() {
        Mockito.when(saveFileMapper.map(FILE_DTO)).thenReturn(FILE);
        Mockito.when(fileRepository.save(FILE)).thenReturn(FILE);

        Long id = fileService.saveFile(FILE_DTO);

        assertThat(id).isEqualTo(ID);
    }

    @Test
    void findById() {

        Mockito.when(fileRepository.findById(ID)).thenReturn(Optional.ofNullable(FILE));
        Mockito.when(readFileMapper.map(FILE)).thenReturn(FILE_DTO);

        Optional<FileDto> optionalFileDto = fileService.findById(ID);

        Assertions.assertTrue(optionalFileDto.isPresent());
        assertThat(optionalFileDto.get()).isEqualTo(FILE_DTO);
    }

    @Test
    void findAll() {
        List<File> files = List.of(FILE, FILE);
        List<FileDto> filesDto = List.of(FILE_DTO, FILE_DTO);
        Page<File> page = new PageImpl<>(files);
        PageFileDto pageFileDto = PageFileDto.builder()
                .content(filesDto)
                .build();

        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("creationDate"));

        Mockito.when(fileRepository.findAll(pageRequest)).thenReturn(page);
        Mockito.when(pageFileReadMapper.map(page)).thenReturn(pageFileDto);

        assertThat(fileService.findAll(pageRequest).getContent().size())
                .isEqualTo(files.size());

    }
}