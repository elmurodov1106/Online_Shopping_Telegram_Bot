package uz.pdp.online_shop.model.User;

import lombok.*;
import lombok.experimental.Accessors;
import org.telegram.telegrambots.meta.api.objects.Location;
import uz.pdp.online_shop.shoppingBot.UserState;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User {
    private UUID id;
    private Long chatId;
    private String fullName;
    private String phoneNumber;
    private double balance;
    private int age;
    private String userName;
    private String bio;
    private Location location;
    private UserState userState;

    public User(Long chatId, String fullName, String phoneNumber, double balance, int age, String userName, String bio,
                 UserState userState) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.age = age;
        this.userName = userName;
        this.bio = bio;
        this.userState = userState;

    }
}
