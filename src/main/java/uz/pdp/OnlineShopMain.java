package uz.pdp;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.online_shop.shoppingBot.OnlineShoppingBot;

public class OnlineShopMain {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new OnlineShoppingBot());
            System.out.println("bot started");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
