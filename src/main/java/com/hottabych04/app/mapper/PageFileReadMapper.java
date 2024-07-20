package com.hottabych04.app.mapper;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.PageFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageFileReadMapper implements Mapper<Page<File>, PageFileDto>{

    private final ReadFileMapper readFileMapper;

    @Override
    public PageFileDto map(Page<File> object) {
        return PageFileDto.builder()
                .totalElements(object.getTotalElements())
                .totalPages(object.getTotalPages())
                .pageSize(object.getPageable().getPageSize())
                .numberOfElements(object.getNumberOfElements())
                .number(object.getNumber())
                .first(object.isFirst())
                .last(object.isLast())
                .empty(object.isEmpty())
                .content(object.getContent())
                .build();
    }
}
