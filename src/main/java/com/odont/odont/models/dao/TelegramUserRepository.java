package com.odont.odont.models.dao;



import com.odont.odont.models.entity.TelegramUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TelegramUserRepository extends PagingAndSortingRepository<TelegramUser, Integer> {
}
