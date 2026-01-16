package com.duymeke.finaall.repository;

import com.duymeke.finaall.entity.Account;
import com.duymeke.finaall.entity.WorkItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkItemRepository extends JpaRepository<WorkItem, Long> {
    List<WorkItem> findByAccount(Account account);
}

