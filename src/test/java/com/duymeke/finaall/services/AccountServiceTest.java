package com.duymeke.finaall.services;

import com.duymeke.finaall.dto.AccountCreateDto;
import com.duymeke.finaall.dto.AccountDto;
import com.duymeke.finaall.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    void getAllTest() {
        List<AccountDto> dtos = service.getAll();

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (AccountDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getUsername());
            Assertions.assertNotNull(dto.getEmail());
        }
    }

    @Test
    void getByIdTest(){
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();

        AccountDto dto = service.getById(someIndex);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getUsername());
        Assertions.assertNotNull(dto.getEmail());
    }

    @Test
    void addTest() {
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.kz";

        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setUsername("TestUser" + System.currentTimeMillis());
        accountCreateDto.setEmail(uniqueEmail);
        accountCreateDto.setPassword("testpassword123");

        AccountDto saved = service.create(accountCreateDto);
        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getUsername());
        Assertions.assertNotNull(saved.getEmail());

        AccountDto savedTest = service.getById(saved.getId());
        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getUsername());
        Assertions.assertNotNull(savedTest.getEmail());

        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getUsername(), savedTest.getUsername());
        Assertions.assertEquals(saved.getEmail(), savedTest.getEmail());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(service.getAll().size());
        Long someIndex = service.getAll().get(randomIndex).getId();

        AccountCreateDto newAccount = new AccountCreateDto();
        newAccount.setUsername("UpdatedTestUser");
        newAccount.setEmail("updatedtest@example.kz");
        newAccount.setPassword("newpassword123");

        AccountDto updated = service.update(someIndex, newAccount);
        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getUsername());
        Assertions.assertNotNull(updated.getEmail());

        AccountDto updateTest = service.getById(someIndex);
        Assertions.assertNotNull(updateTest);
        Assertions.assertNotNull(updateTest.getId());
        Assertions.assertNotNull(updateTest.getUsername());
        Assertions.assertNotNull(updateTest.getEmail());

        Assertions.assertEquals(updated.getId(), updateTest.getId());
        Assertions.assertEquals(updated.getUsername(), updateTest.getUsername());
        Assertions.assertEquals(updated.getEmail(), updateTest.getEmail());
    }

    @Test
    void deleteTest() {
        String uniqueEmail = "deletetest" + System.currentTimeMillis() + "@example.kz";

        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setUsername("DeleteTestUser" + System.currentTimeMillis());
        accountCreateDto.setEmail(uniqueEmail);
        accountCreateDto.setPassword("testpassword123");

        AccountDto newAccount = service.create(accountCreateDto);
        Assertions.assertNotNull(newAccount);

        Long accountIdToDelete = newAccount.getId();

        boolean deleted = service.delete(accountIdToDelete);
        Assertions.assertTrue(deleted);

        AccountDto deletedTest = service.getById(accountIdToDelete);
        Assertions.assertNull(deletedTest);
    }
}
