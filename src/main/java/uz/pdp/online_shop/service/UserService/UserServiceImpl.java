package uz.pdp.online_shop.service.UserService;

import org.telegram.telegrambots.meta.api.objects.*;
import uz.pdp.online_shop.model.User.User;
import uz.pdp.online_shop.repository.UserRepository;
import uz.pdp.online_shop.repository.UserRepositoryImpl;
import uz.pdp.online_shop.shoppingBot.UserState;

import java.util.Objects;

public class UserServiceImpl implements UserService, UserRepository {
    UserRepositoryImpl userRepositoryImpl = UserRepositoryImpl.getInstance();



    @Override
    public boolean add(Contact contact, String bio, double balance, int age, String userName) {
        if (checkPhoneNumber(contact.getPhoneNumber())) {
            return false;
        }
        userRepositoryImpl.save(contact, balance, age, userName, bio);
        return true;
    }



    @Override
    public User getByChatId(Long chatId) {
        for (User allUser : userRepositoryImpl.getAllUser()) {
            if (Objects.equals(allUser.getChatId(), chatId)) {
                return allUser;
            }
        }
        return null;
    }

    @Override
    public void updateState(Long chatId, UserState userState) {
        userRepositoryImpl.updateState(chatId, userState);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if (Objects.equals(userRepositoryImpl.getPhoneNumber(), phoneNumber)) {
            return true;
        }
        return false;
    }

}
