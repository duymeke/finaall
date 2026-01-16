package com.duymeke.finaall.services;

import com.duymeke.finaall.dto.AccountDto;
import com.duymeke.finaall.dto.WorkItemDto;
import com.duymeke.finaall.service.AccountService;
import com.duymeke.finaall.service.WorkItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class WorkItemServiceTest {

    @Autowired
    private WorkItemService service;

    @Autowired
    private AccountService accountService;

    @Test
    void getAllTest() {
        List<AccountDto> accounts = accountService.getAll();
        Assertions.assertNotEquals(0, accounts.size());

        Long accountId = accounts.get(0).getId();
        List<WorkItemDto> dtos = service.getAll(accountId);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.size());

        for (WorkItemDto dto : dtos) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
            Assertions.assertNotNull(dto.getText());
            Assertions.assertNotNull(dto.getAccountId());
        }
    }

    @Test
    void getByIdTest(){
        List<AccountDto> accounts = accountService.getAll();
        Assertions.assertNotEquals(0, accounts.size());

        Long accountId = accounts.get(0).getId();
        List<WorkItemDto> workItems = service.getAll(accountId);
        
        if (workItems.size() > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(workItems.size());
            Long someIndex = workItems.get(randomIndex).getId();

            WorkItemDto dto = service.getById(someIndex, accountId);
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getTitle());
            Assertions.assertNotNull(dto.getText());
            Assertions.assertNotNull(dto.getAccountId());
        }
    }

    @Test
    void addTest() {
        List<AccountDto> accounts = accountService.getAll();
        Assertions.assertNotEquals(0, accounts.size());

        Long accountId = accounts.get(0).getId();
        String uniqueTitle = "TestTask" + System.currentTimeMillis();

        WorkItemDto workItemDto = new WorkItemDto();
        workItemDto.setTitle(uniqueTitle);
        workItemDto.setText("Test Description");
        workItemDto.setCompleted(false);
        workItemDto.setAccountId(accountId);

        WorkItemDto saved = service.create(workItemDto);
        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertNotNull(saved.getTitle());
        Assertions.assertNotNull(saved.getText());
        Assertions.assertNotNull(saved.getAccountId());

        WorkItemDto savedTest = service.getById(saved.getId(), accountId);
        Assertions.assertNotNull(savedTest);
        Assertions.assertNotNull(savedTest.getId());
        Assertions.assertNotNull(savedTest.getTitle());
        Assertions.assertNotNull(savedTest.getText());
        Assertions.assertNotNull(savedTest.getAccountId());

        Assertions.assertEquals(saved.getId(), savedTest.getId());
        Assertions.assertEquals(saved.getTitle(), savedTest.getTitle());
        Assertions.assertEquals(saved.getText(), savedTest.getText());
        Assertions.assertEquals(saved.getAccountId(), savedTest.getAccountId());
    }

    @Test
    void updateTest() {
        List<AccountDto> accounts = accountService.getAll();
        Assertions.assertNotEquals(0, accounts.size());

        Long accountId = accounts.get(0).getId();
        List<WorkItemDto> workItems = service.getAll(accountId);
        
        if (workItems.size() > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(workItems.size());
            Long someIndex = workItems.get(randomIndex).getId();

            WorkItemDto newWorkItem = new WorkItemDto();
            newWorkItem.setTitle("UpdatedTestTask");
            newWorkItem.setText("Updated Description");
            newWorkItem.setCompleted(true);
            newWorkItem.setAccountId(accountId);

            WorkItemDto updated = service.update(someIndex, newWorkItem, accountId);
            Assertions.assertNotNull(updated);
            Assertions.assertNotNull(updated.getId());
            Assertions.assertNotNull(updated.getTitle());
            Assertions.assertNotNull(updated.getText());
            Assertions.assertNotNull(updated.getAccountId());

            WorkItemDto updateTest = service.getById(someIndex, accountId);
            Assertions.assertNotNull(updateTest);
            Assertions.assertNotNull(updateTest.getId());
            Assertions.assertNotNull(updateTest.getTitle());
            Assertions.assertNotNull(updateTest.getText());
            Assertions.assertNotNull(updateTest.getAccountId());

            Assertions.assertEquals(updated.getId(), updateTest.getId());
            Assertions.assertEquals(updated.getTitle(), updateTest.getTitle());
            Assertions.assertEquals(updated.getText(), updateTest.getText());
            Assertions.assertEquals(updated.getAccountId(), updateTest.getAccountId());
        }
    }

    @Test
    void deleteTest() {
        List<AccountDto> accounts = accountService.getAll();
        Assertions.assertNotEquals(0, accounts.size());

        Long accountId = accounts.get(0).getId();

        String uniqueTitle = "deletetest" + System.currentTimeMillis();

        WorkItemDto workItemDto = new WorkItemDto();
        workItemDto.setTitle(uniqueTitle);
        workItemDto.setText("Delete Test Description");
        workItemDto.setCompleted(false);
        workItemDto.setAccountId(accountId);

        WorkItemDto newWorkItem = service.create(workItemDto);
        Assertions.assertNotNull(newWorkItem);

        Long workItemIdToDelete = newWorkItem.getId();

        boolean deleted = service.delete(workItemIdToDelete, accountId);
        Assertions.assertTrue(deleted);

        WorkItemDto deletedTest = service.getById(workItemIdToDelete, accountId);
        Assertions.assertNull(deletedTest);
    }
}
