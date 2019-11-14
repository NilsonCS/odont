package com.odont.odont.handler;

import com.odont.odont.models.telegram.TelegramUpdate;

public interface TelegramMessageHandler {
    void handle(TelegramUpdate telegramUpdate);
}
