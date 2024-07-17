package com.hottabych04.app.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class FileDto {
    String title;
    String description;
    LocalDateTime creationDate;
    String data;
}
