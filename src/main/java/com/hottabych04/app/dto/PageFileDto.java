package com.hottabych04.app.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PageFileDto {
    Long totalElements;
    Integer totalPages;
    Integer number;
    Integer pageSize;
    Integer numberOfElements;
    Boolean first, last, empty;
    List<FileDto> content;
}
