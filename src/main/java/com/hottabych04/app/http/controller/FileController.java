package com.hottabych04.app.http.controller;

import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PutMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveFile(@RequestBody FileDto file){
        return fileService.saveFile(file)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.EXPECTATION_FAILED));
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FileDto findById(@PathVariable("id") Long id){
        return fileService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
