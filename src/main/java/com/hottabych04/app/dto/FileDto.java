package com.hottabych04.app.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class FileDto {
    String title;
    String description;
    LocalDate creationDate;
    String data;
}
