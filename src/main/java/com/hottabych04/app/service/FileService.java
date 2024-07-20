package com.hottabych04.app.service;

import com.hottabych04.app.database.repository.FileRepository;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.dto.PageFileDto;
import com.hottabych04.app.mapper.PageFileReadMapper;
import com.hottabych04.app.mapper.ReadFileMapper;
import com.hottabych04.app.mapper.SaveFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {

    private final FileRepository fileRepository;
    private final ReadFileMapper readFileMapper;
    private final SaveFileMapper saveFileMapper;
    private final PageFileReadMapper pageFileReadMapper;

    @Transactional
    public Long saveFile(FileDto fileDto){
        return fileRepository.save(saveFileMapper.map(fileDto)).getId();
    }

    public Optional<FileDto> findById(Long id) {
        return fileRepository.findById(id).map(readFileMapper::map);
    }

    public PageFileDto findAll(PageRequest creationDate){
        return pageFileReadMapper.map(fileRepository.findAll(creationDate));
    }
}
