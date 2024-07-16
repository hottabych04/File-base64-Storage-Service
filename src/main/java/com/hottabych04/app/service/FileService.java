package com.hottabych04.app.service;

import com.hottabych04.app.database.repository.FileRepository;
import com.hottabych04.app.dto.FileDto;
import com.hottabych04.app.mapper.ReadFileMapper;
import com.hottabych04.app.mapper.SaveFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final ReadFileMapper readFileMapper;
    private final SaveFileMapper saveFileMapper;

    public Optional<Long> saveFile(FileDto fileDto){
        return Optional.of(
                fileRepository.save(saveFileMapper.map(fileDto)).getId()
        );
    }

    public Optional<FileDto> findById(Long id) {
        return fileRepository.findById(id).map(readFileMapper::map);
    }
}
