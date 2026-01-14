package com.duymeke.finaall.service;

import com.duymeke.finaall.dto.LabelDto;

import java.util.List;

public interface LabelService {
    List<LabelDto> getAll();
    LabelDto getById(Long id);
    LabelDto create(LabelDto dto);
    LabelDto update(Long id, LabelDto dto);
    boolean delete(Long id);
}
