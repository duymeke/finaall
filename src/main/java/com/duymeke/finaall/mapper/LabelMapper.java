package com.duymeke.finaall.mapper;

import com.duymeke.finaall.dto.LabelDto;
import com.duymeke.finaall.entity.Label;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LabelMapper {

    LabelDto toDto(Label label);
    
    @Mapping(target = "workItems", ignore = true)
    Label toEntity(LabelDto dto);
    
    List<LabelDto> toDtoList(List<Label> labels);
}

