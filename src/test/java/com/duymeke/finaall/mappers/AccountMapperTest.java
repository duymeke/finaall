package com.duymeke.finaall.mappers;

import com.duymeke.finaall.dto.AccountCreateDto;
import com.duymeke.finaall.dto.AccountDto;
import com.duymeke.finaall.entity.Account;
import com.duymeke.finaall.mapper.AccountMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AccountMapperTest {

    @Autowired
    private AccountMapper mapper;

    @Test
    void convertEntityToDtoTest() {
        Account entity = new Account(1L, "testuser", "test@example.com", "password123", null, null);
        AccountDto dto = mapper.toDto(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getEmail());
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getUsername(), dto.getUsername());
        Assertions.assertEquals(entity.getEmail(), dto.getEmail());
    }

    @Test
    void convertDtoToEntityTest() {
        AccountCreateDto dto = new AccountCreateDto("testuser", "test@example.com", "password123");
        Account entity = mapper.toEntity(dto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getEmail());
        Assertions.assertNotNull(entity.getPassword());
        Assertions.assertEquals(dto.getEmail(), entity.getEmail());
        Assertions.assertEquals(dto.getEmail(), entity.getUsername());
        Assertions.assertEquals(dto.getPassword(), entity.getPassword());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Account> entities = new ArrayList<>();
        entities.add(new Account(1L, "user1", "user1@example.com", "password1", null, null));
        entities.add(new Account(2L, "user2", "user2@example.com", "password2", null, null));
        entities.add(new Account(3L, "user3", "user3@example.com", "password3", null, null));

        List<AccountDto> dtos = mapper.toDtoList(entities);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(dtos.size(), 0);
        Assertions.assertEquals(dtos.size(), entities.size());

        for (int i = 0; i < dtos.size(); i++) {
            Assertions.assertNotNull(dtos.get(i));
            Assertions.assertNotNull(dtos.get(i).getId());
            Assertions.assertNotNull(dtos.get(i).getUsername());
            Assertions.assertNotNull(dtos.get(i).getEmail());
            Assertions.assertEquals(dtos.get(i).getId(), entities.get(i).getId());
            Assertions.assertEquals(dtos.get(i).getUsername(), entities.get(i).getUsername());
            Assertions.assertEquals(dtos.get(i).getEmail(), entities.get(i).getEmail());
        }
    }
}
