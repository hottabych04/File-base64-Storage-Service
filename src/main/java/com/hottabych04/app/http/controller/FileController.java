package com.hottabych04.app.http.controller;

import com.hottabych04.app.database.entity.File;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.dto.PageFileDto;
import com.hottabych04.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PutMapping(value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveFile(@RequestBody
                             @Validated FileDto file){
        return fileService.saveFile(file);
    }

    @GetMapping(value = "/get/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public FileDto findById(@PathVariable("id") Long id){
        return fileService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/get/page/{page}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PageFileDto findAll(@PathVariable("page") Integer page){
        return fileService.findAll(
                PageRequest.of(page, 5, Sort.by("creationDate").descending())
        );
    }

}
