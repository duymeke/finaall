package com.duymeke.finaall.mappers;

import com.duymeke.finaall.dto.LabelDto;
import com.duymeke.finaall.entity.Label;
import com.duymeke.finaall.mapper.LabelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LabelMapperTest {

    @Autowired
    private LabelMapper mapper;

    @Test
    void convertEntityToDtoTest() {
        Label entity = new Label(1L, "backend", null);
        LabelDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getName(), dto.getName());
    }

    @Test
    void convertDtoToEntityTest() {
        LabelDto dto = new LabelDto(2L, "frontend");
        Label entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getName());
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getName(), entity.getName());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Label> entities = new ArrayList<>();
        entities.add(new Label(1L, "backend", null));
        entities.add(new Label(2L, "frontend", null));
        entities.add(new Label(3L, "devops", null));

        List<LabelDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(dtos.size(), 0);
        Assertions.assertEquals(dtos.size(), entities.size());

        for (int i = 0; i < dtos.size(); i++) {
            Assertions.assertNotNull(dtos.get(i));
            Assertions.assertNotNull(dtos.get(i).getId());
            Assertions.assertNotNull(dtos.get(i).getName());
            Assertions.assertEquals(dtos.get(i).getId(), entities.get(i).getId());
            Assertions.assertEquals(dtos.get(i).getName(), entities.get(i).getName());
        }
    }
}
