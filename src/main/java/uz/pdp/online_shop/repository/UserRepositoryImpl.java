package uz.pdp.online_shop.repository;

import org.telegram.telegrambots.meta.api.objects.Contact;
import uz.pdp.online_shop.BeanUtills.BeanUtils;
import uz.pdp.online_shop.model.User.User;
import uz.pdp.online_shop.shoppingBot.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl instance;

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    private UserRepositoryImpl() {
    }

    private final Connection connection = BeanUtils.connection();

    public boolean save(Contact contact, double balance, int age, String userName, String bio) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertUser);

            preparedStatement.setString(1, String.valueOf(contact.getUserId()));
            preparedStatement.setString(2, contact.getFirstName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setDouble(4, balance);
            preparedStatement.setInt(5, age);
            preparedStatement.setString(6, userName);
            preparedStatement.setString(7, bio);
            preparedStatement.setString(8, String.valueOf(UserState.REGISTERED));
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<User> getAllUser() {

        ArrayList<User> users = new ArrayList<>();
        PreparedStatement preparedStatement;

        {
            try {
                preparedStatement = connection.prepareStatement(getAllUsers);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Long chatId = Long.valueOf(resultSet.getString("chat_id"));
                    String fullName = resultSet.getString("fullname");
                    String phoneNumber = resultSet.getString("phonenumber");
                    double balance = resultSet.getDouble("balance");
                    int age = resultSet.getInt("age");
                    String userName = resultSet.getString("username");
                    String bio = resultSet.getString("bio");
                    UserState state = UserState.valueOf(resultSet.getString("user_state"));




                    User user = new User(chatId, fullName, phoneNumber, balance, age, userName, bio, state);
                    users.add(user);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    public Object getPhoneNumber() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getPhoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String phonenumber = resultSet.getString("phonenumber");
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getByChatId() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getChatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String chatid = resultSet.getString("chatid");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateState(Long chatId, UserState userState) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateState);
            preparedStatement.setString(1, userState.toString());
            preparedStatement.setString(2, chatId.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
