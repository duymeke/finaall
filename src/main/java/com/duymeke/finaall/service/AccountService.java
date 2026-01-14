package com.duymeke.finaall.service;

import com.duymeke.finaall.dto.AccountCreateDto;
import com.duymeke.finaall.dto.AccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    List<AccountDto> getAll();
    AccountDto getById(Long id);
    AccountDto create(AccountCreateDto accountCreateDto);
    AccountDto update(Long id, AccountCreateDto accountCreateDto);

    boolean delete(Long id);
}
