package com.duymeke.finaall.mapper;

import com.duymeke.finaall.dto.WorkItemDto;
import com.duymeke.finaall.entity.WorkItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LabelMapper.class})
public interface WorkItemMapper {

    @Mapping(source = "account.id", target = "accountId")
    WorkItemDto toDto(WorkItem workItem);

    @Mapping(target = "account", ignore = true)
    WorkItem toEntity(WorkItemDto dto);

    List<WorkItemDto> toDtoList(List<WorkItem> workItems);
}