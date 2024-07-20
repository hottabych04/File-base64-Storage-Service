package com.hottabych04.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class FileDto {

    @NotNull
    String title;

    String description;

    @NotNull
    LocalDateTime creationDate;

    @NotNull
    String data;
}
