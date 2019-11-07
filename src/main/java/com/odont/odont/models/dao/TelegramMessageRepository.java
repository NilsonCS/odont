package com.odont.odont.models.dao;



import com.odont.odont.models.entity.TelegramMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TelegramMessageRepository extends PagingAndSortingRepository<TelegramMessage, Integer> {
}
