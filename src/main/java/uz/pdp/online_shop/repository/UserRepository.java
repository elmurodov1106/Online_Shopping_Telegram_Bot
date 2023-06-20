package uz.pdp.online_shop.repository;

public interface UserRepository {
    String insertUser = """
            insert into users_data (chat_id, fullname, phonenumber, balance, age, username,  bio, user_state)
            values (?,?,?,?,?,?,?,?);
            """;

    String getAllUsers = """
            select * from users_data;
            """;

    String getPhoneNumber = """
            select phonenumber from users_data;
            """;

    String updateState = """
            update users_data set user_state = ? where chat_id = ?;
            """;

    String getChatId = """
            select chat_id from users_data;
            """;
}
