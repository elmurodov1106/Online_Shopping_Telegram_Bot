package uz.pdp.online_shop.service;

import org.telegram.telegrambots.meta.api.objects.*;
import uz.pdp.online_shop.shoppingBot.UserState;

public interface BaseService<T> {
    boolean add(Contact contact, String bio, double balance, int age, String userName);

    T getByChatId(Long chatId);

    void updateState(Long chatId, UserState userState);
}
