package com.odont.odont.transformer;

import com.odont.odont.models.telegram.TelegramUpdate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

@Component
public class UpdateToTelegramUpdateTransformer implements Transformer<Update, TelegramUpdate> {
    @Override
    public TelegramUpdate transform(Update update) {
        return TelegramUpdate.builder()
                .id(update.getUpdateId())
                .creationDate(LocalDateTime.now())
                .build();
    }
}