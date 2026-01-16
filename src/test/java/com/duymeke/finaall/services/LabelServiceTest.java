package com.duymeke.finaall.services;

import com.duymeke.finaall.dto.LabelDto;
import com.duymeke.finaall.service.LabelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class LabelServiceTest {

    @Autowired
    private LabelService service;

    @Test
    void getAllTest() {
        List<LabelDto> dtos = service.getAll();

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (LabelDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
        }
    }

    @Test
    void getByIdTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();

        LabelDto dto = service.getById(someIndex);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getName());
    }

    @Test
    void addTest() {
        String uniqueName = "testlabel" + System.currentTimeMillis();

        LabelDto labelDto = new LabelDto();
        labelDto.setName(uniqueName);

        LabelDto saved = service.create(labelDto);
        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getName());

        LabelDto savedTest = service.getById(saved.getId());
        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getName());

        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getName(), savedTest.getName());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();

        LabelDto newLabel = new LabelDto();
        newLabel.setName("UpdatedTestLabel");

        LabelDto updated = service.update(someIndex, newLabel);
        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());

        LabelDto updateTest = service.getById(someIndex);
        Assertions.assertNotNull(updateTest);
        Assertions.assertNotNull(updateTest.getId());
        Assertions.assertNotNull(updateTest.getName());

        Assertions.assertEquals(updated.getId(), updateTest.getId());
        Assertions.assertEquals(updated.getName(), updateTest.getName());
    }

    @Test
    void deleteTest() {
        String uniqueName = "deletetest" + System.currentTimeMillis();

        LabelDto labelDto = new LabelDto();
        labelDto.setName(uniqueName);

        LabelDto newLabel = service.create(labelDto);
        Assertions.assertNotNull(newLabel);
        Long labelIdToDelete = newLabel.getId();

        boolean deleted = service.delete(labelIdToDelete);
        Assertions.assertTrue(deleted);

        LabelDto deletedTest = service.getById(labelIdToDelete);
        Assertions.assertNull(deletedTest);
    }
}
