package com.duymeke.finaall.mapper;

import com.duymeke.finaall.dto.AccountCreateDto;
import com.duymeke.finaall.dto.AccountDto;
import com.duymeke.finaall.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkItemMapper.class})
public interface AccountMapper {

    AccountDto toDto(Account account);
    Account toEntity(AccountCreateDto dto);
    List<AccountDto> toDtoList(List<Account> accounts);
}