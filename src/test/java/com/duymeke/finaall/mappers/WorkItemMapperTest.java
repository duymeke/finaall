package com.duymeke.finaall.mappers;

import com.duymeke.finaall.dto.LabelDto;
import com.duymeke.finaall.dto.WorkItemDto;
import com.duymeke.finaall.entity.Account;
import com.duymeke.finaall.entity.Label;
import com.duymeke.finaall.entity.WorkItem;
import com.duymeke.finaall.mapper.WorkItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WorkItemMapperTest {

    @Autowired
    private WorkItemMapper mapper;

    @Test
    void convertEntityToDtoTest() {
        Account account = new Account(1L, "testuser", "test@example.com", "password123", null, null);
        Label label = new Label(1L, "backend", null);
        WorkItem entity = new WorkItem(1L, "Test Task", "Test Description", false, account, List.of(label));
        
        WorkItemDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getTitle());
        Assertions.assertNotNull(dto.getText());
        Assertions.assertNotNull(dto.getAccountId());
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getTitle(), dto.getTitle());
        Assertions.assertEquals(entity.getText(), dto.getText());
        Assertions.assertEquals(entity.isCompleted(), dto.isCompleted());
        Assertions.assertEquals(entity.getAccount().getId(), dto.getAccountId());
    }

    @Test
    void convertDtoToEntityTest() {
        LabelDto labelDto = new LabelDto(1L, "backend");
        WorkItemDto dto = new WorkItemDto(2L, "New Task", "New Description", true, 1L, List.of(labelDto));
        
        WorkItem entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getTitle());
        Assertions.assertNotNull(entity.getText());
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getTitle(), entity.getTitle());
        Assertions.assertEquals(dto.getText(), entity.getText());
        Assertions.assertEquals(dto.isCompleted(), entity.isCompleted());
    }

    @Test
    void convertEntityListToDtoList() {
        Account account = new Account(1L, "testuser", "test@example.com", "password123", null, null);
        Label label1 = new Label(1L, "backend", null);
        Label label2 = new Label(2L, "frontend", null);
        
        List<WorkItem> entities = new ArrayList<>();
        entities.add(new WorkItem(1L, "Task 1", "Description 1", false, account, List.of(label1)));
        entities.add(new WorkItem(2L, "Task 2", "Description 2", true, account, List.of(label2)));
        entities.add(new WorkItem(3L, "Task 3", "Description 3", false, account, List.of(label1, label2)));

        List<WorkItemDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(dtos.size(), 0);
        Assertions.assertEquals(dtos.size(), entities.size());

        for (int i = 0; i < dtos.size(); i++) {
            Assertions.assertNotNull(dtos.get(i));
            Assertions.assertNotNull(dtos.get(i).getId());
            Assertions.assertNotNull(dtos.get(i).getTitle());
            Assertions.assertNotNull(dtos.get(i).getText());
            Assertions.assertNotNull(dtos.get(i).getAccountId());
            Assertions.assertEquals(dtos.get(i).getId(), entities.get(i).getId());
            Assertions.assertEquals(dtos.get(i).getTitle(), entities.get(i).getTitle());
            Assertions.assertEquals(dtos.get(i).getText(), entities.get(i).getText());
            Assertions.assertEquals(dtos.get(i).isCompleted(), entities.get(i).isCompleted());
            Assertions.assertEquals(dtos.get(i).getAccountId(), entities.get(i).getAccount().getId());
        }
    }
}
