package com.duymeke.finaall.service.impl;

import com.duymeke.finaall.dto.LabelDto;
import com.duymeke.finaall.entity.Label;
import com.duymeke.finaall.mapper.LabelMapper;
import com.duymeke.finaall.repository.LabelRepository;
import com.duymeke.finaall.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository repository;
    private final LabelMapper mapper;

    @Override
    public List<LabelDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public LabelDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public LabelDto create(LabelDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public LabelDto update(Long id, LabelDto dto) {
        Label old = repository.findById(id).orElse(null);
        if (Objects.isNull(dto) || Objects.isNull(old)) {
            return null;
        }
        old.setName(dto.getName());
        return mapper.toDto(repository.save(old));
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        LabelDto deleted = getById(id);
        return Objects.isNull(deleted);
    }
}

