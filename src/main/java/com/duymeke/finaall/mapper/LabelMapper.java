package com.duymeke.finaall.mapper;

import com.duymeke.finaall.dto.LabelDto;
import com.duymeke.finaall.entity.Label;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LabelMapper {

    LabelDto toDto(Label label);
    Label toEntity(LabelDto dto);
    List<LabelDto> toDtoList(List<Label> labels);
}

