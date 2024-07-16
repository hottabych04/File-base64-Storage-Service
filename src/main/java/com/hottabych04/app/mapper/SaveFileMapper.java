package com.hottabych04.app.mapper;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.FileDto;
import org.springframework.stereotype.Component;

@Component
public class SaveFileMapper implements Mapper<FileDto, File> {
    @Override
    public File map(FileDto object) {
        File file = File.builder()
                .title(object.getTitle())
                .description(object.getDescription())
                .creationDate(object.getCreationDate())
                .data(object.getData())
                .build();
        return file;
    }
}
