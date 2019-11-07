package com.odont.odont.models.dao;

import com.odont.odont.models.entity.TelegramChat;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TelegramChatRepository extends PagingAndSortingRepository<TelegramChat, Long> {
    Optional<TelegramChat> findByUser_Id(Integer userId);

    Optional<TelegramChat> findByUser_Person_Id(Integer personId);
}
