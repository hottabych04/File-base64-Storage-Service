package com.hottabych04.app.mapper;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.FileDto;
import org.springframework.stereotype.Component;

@Component
public class ReadFileMapper implements Mapper<File, FileDto> {
    @Override
    public FileDto map(File object) {
        FileDto fileDto = FileDto.builder()
                .title(object.getTitle())
                .description(object.getDescription())
                .creationDate(object.getCreationDate())
                .data(object.getData())
                .build();
        return fileDto;
    }
}
