package uz.pdp.online_shop.service.UserService;

import uz.pdp.online_shop.model.User.User;
import uz.pdp.online_shop.service.BaseService;

public interface UserService extends BaseService<User> {
    boolean checkPhoneNumber(String phoneNumber);
}
