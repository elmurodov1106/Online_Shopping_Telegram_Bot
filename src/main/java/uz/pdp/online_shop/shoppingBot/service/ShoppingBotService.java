package uz.pdp.online_shop.shoppingBot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.pdp.online_shop.service.UserService.UserServiceImpl;
import uz.pdp.online_shop.shoppingBot.UserState;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBotService {
    UserServiceImpl userService = new UserServiceImpl();

    public SendMessage requestContactMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Please Enter Your Phone Number");
        sendMessage.setReplyMarkup(requestContactButton());
        return sendMessage;
    }

    public SendMessage requestLocationMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Please Share Location");
        sendMessage.setReplyMarkup(requestLocationButton());
        return sendMessage;
    }

    public SendMessage welcomeMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "Welcome to the Online Shopping");
        sendMessage.setReplyMarkup(menuButtons());
        return sendMessage;
    }

    public SendMessage navigateUser(Long chatId, String text) {
        UserState userState = null;
   SendMessage sendMessage = new SendMessage();
        switch (text) {
            case "\uD83C\uDFCB️\u200D♂️Sport" -> {
                userState = UserState.COSMETICA;

                sendMessage = menuCosmetica(chatId);
            }
            case "\uD83D\uDCF1Telefon va aksesuarlar" -> {
                userState = UserState.PHONES;
                sendMessage = menuPhones(chatId);
            }
            case "\uD83C\uDFE1Uy va hovli uchun" -> {
                userState = UserState.HOUSES;
                sendMessage = menuHouses(chatId);
            }
            case "\uD83D\uDE98Avtomobil uchun" -> {
                userState = UserState.CARS;
                sendMessage = menuCars(chatId);
            }
            case "\uD83C\uDF81Sovg'a uchun" -> {
                userState = UserState.PRICES;
                sendMessage = menuPrises(chatId);
            }
            case "\uD83E\uDDF8Bolajonlar uchun" -> {
                userState = UserState.TOYS;
                sendMessage = menuToys(chatId);
            }
            case "\uD83D\uDCDAKitoblar" -> {
                userState = UserState.BOOKS;
                sendMessage = menuBooks(chatId);
            }
            case "⏱Soatlar va quloqchinlar" -> {
                userState = UserState.AIRPODS;
                sendMessage = menuAirpods(chatId);
            }
            case "\uD83D\uDCDE BOG'LANISH" -> {
                userState = UserState.BOGLANISH;
                sendMessage = new SendMessage(chatId.toString(),"\uD83D\uDCDE Bog'lanish uchun👇\n \uD83D\uDC64 @Elmurodov_1106");
            }
            case "\uD83C\uDFCB️\u200D♂️Gantellar"->{
                userState = UserState.ERKALAR_UCHUN;
                sendMessage = inlineCosMenu(chatId.toString());
            }case "\uD83D\uDCD7Badiy kitoblar" ->{
               sendMessage .setText("Badiiy kitoblar hali qo'shilmagan 😉");
            }
            default -> {
                userState = UserState.REGISTERED;
                sendMessage.setReplyMarkup(menuButtons());
                sendMessage.setText("menu");

            }

        }
        userService.updateState(chatId, userState);
            return sendMessage;
    }



    public ReplyKeyboardMarkup requestContactButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton(" \uD83D\uDCF2Share Phone Number");
        button.setRequestContact(true);
        row.add(button);

        replyKeyboardMarkup.setKeyboard(List.of(row));
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup requestLocationButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton(" \uD83D\uDCCDShare Location");
        button.setRequestLocation(true);
        row.add(button);

        replyKeyboardMarkup.setKeyboard(List.of(row));
        return replyKeyboardMarkup;
    }



    public SendMessage menuCosmetica(Long chatId){


        SendMessage sendMessage = new SendMessage(chatId.toString(),"Tanlang. ");


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("\uD83C\uDFCB️\u200D♂️Gantellar");
        row.add("Mishoklar");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");

        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;

    }

    public ReplyKeyboardMarkup menuButtons() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("🏋️‍♂️Sport");
        row.add("\uD83D\uDCF1Telefon va aksesuarlar");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("\uD83C\uDFE1Uy va hovli uchun");
        row1.add("\uD83D\uDE98Avtomobil uchun");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("\uD83C\uDF81Sovg'a uchun");
        row2.add("\uD83E\uDDF8Bolajonlar uchun");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("\uD83D\uDCDAKitoblar");
        row3.add("⏱Soatlar va quloqchinlar");

        KeyboardRow row5 = new KeyboardRow();
        row5.add("⬅️Back");

        KeyboardRow row4 = new KeyboardRow();
        row4.add("\uD83D\uDCDE BOG'LANISH");


        replyKeyboardMarkup.setKeyboard(List.of(row, row1,row2,row3,row5,row4));
        return replyKeyboardMarkup;
    }

    public SendMessage inlineCosMenu(String chatId) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        SendMessage sendMessage = new SendMessage(chatId,"Choose menu 😎");

        KeyboardRow row = new KeyboardRow();
       row.add(" Shanel");
       row.add("Vasmoy element");

      KeyboardRow row1 = new KeyboardRow();
       row1.add("Arabiski");
        row1.add("Mushkambar");

      KeyboardRow row2 = new KeyboardRow();
       row2.add("Versache");
       row2.add("Allure");

     KeyboardRow  row3 = new KeyboardRow();
       row3.add("Chanel");
       row3.add("Sauvage");


      KeyboardRow row4 = new KeyboardRow();
        row4.add("⬅️Back");

       replyKeyboardMarkup.setKeyboard(List.of(row,row1,row2,row3,row4));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
    public SendMessage menuPhones(Long chatId){
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Tanlang. ");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("📱 Telefonlar");
        row.add("🧰 Aksessuvarlar");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");


        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;

    }
    public SendMessage menuHouses(Long chatId){
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Tanlang.");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("🏠 Turar joylar");
        row.add("🏠 Kvartira ");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");


        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }

    public SendMessage menuCars(Long chatId){
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Tanlang.");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("🔧Aksesuvarlar");
        row.add("\uD83D\uDE98 Moshinalar");

        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");

        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
    public SendMessage menuBooks(Long chatId){
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Tanlang.");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();


        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        row.add("\uD83D\uDCD8 Ilmiy kitoblar");
        row.add("\uD83D\uDCD8 Diniy kitoblar");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("\uD83D\uDCD7 Badiy kitoblar");
        row2.add("\uD83D\uDCD7 Dasturlash");


        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");


        replyKeyboardMarkup.setKeyboard(List.of(row,row2,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }


public SendPhoto phoneSend(String chatId) {

    SendPhoto sendPhoto = new SendPhoto();
    sendPhoto.setChatId(chatId);
    sendPhoto.setPhoto(new InputFile("https://t.me/Apple_iPhone_Ayfon_Bozor_Savdo/9174"));
    sendPhoto.setCaption(" nomi:Phone 14 Pro\n Narxi: 1400$ \n Holati: yangi  \n Murojat uchun tel: +998932841106");
    return sendPhoto;
}

public SendPhoto sendGantel(String chatId){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile());
        sendPhoto.setPhoto(new InputFile("https://t.me/gantelbuyurtmauz/226"));
        sendPhoto.setCaption("Gantellar sotiladi. \n" +
                "6+6 kg.\n" +
                "Parasi 200 ming soʻm.\nMurojat uchun tel 👇:\n@Elmurodov_1106");
        return sendPhoto;

}
    public SendPhoto sendMishok(String chatId){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("https://frankfurt.apollo.olxcdn.com/v1/files/93k1dwyjhtlw1-UZ/image;s=644x461"));
        sendPhoto.setCaption("Mishoklar sotiladi. \n" +
                "Donasi 200 ming soʻm.\nMurojat uchun tel 👇:\n@Elmurodov_1106");
        return sendPhoto;

    }

    public SendPhoto sendPhotoS21(String chatid)  {
        SendPhoto sendPhoto = new SendPhoto();

        sendPhoto.setChatId(chatid);
    sendPhoto.setPhoto(new InputFile("https://t.me/Samsung_Galaxy_S21_Ultra/114"));
        sendPhoto.setCaption("model: Samsung S21 Ultra \n narxi: 15000$ \n Holati: yangi\n" +
                "Murojat uchun tel \uD83D\uDC47:\n" +
                "@Elmurodov_1106");
        return sendPhoto;
    }

    public SendPhoto carphoto(String chatid){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatid);
        sendPhoto.setPhoto(new InputFile(new File("C:\\Users\\Msi Laptop\\Music\\photo_2023-04-12_22-56-57.jpg")));
        sendPhoto.setCaption(" Holati: Yangi \n" +
                "Murojat uchun tel \uD83D\uDC47:\n" +
                "@Elmurodov_1106");
        return sendPhoto;
    }
    public SendPhoto carphotoAcsessuvar(String chatid){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatid);
        sendPhoto.setPhoto(new InputFile(new File("C:\\Users\\Msi Laptop\\Pictures\\download.jfif")));
        sendPhoto.setCaption(" Holati: Yangi \n Rangi: Qizil\n Narxi: 100 $\n" +
                "Murojat uchun tel \uD83D\uDC47:\n" +
                "@Elmurodov_1106");
        return sendPhoto;
    }

    public SendPhoto phoneSendAcsesuar(String chatId) {

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(new InputFile("data:image/jpeg;base64,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"));

        return sendPhoto;
    }

    public SendDocument booksSendBadiy(String chatId) {

        SendDocument sendDocument = new SendDocument();

        sendDocument.setChatId(chatId);

            sendDocument.setDocument(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\Downloads\\Anton Chexov. Buqalamun [@uz_baza] (2).pdf")));

        return sendDocument;
    }







    public SendMessage menuPrises(Long chatId){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

            SendMessage sendMessage = new SendMessage(chatId.toString(),"Choose menu 😎");
        KeyboardRow row = new KeyboardRow();
        row.add("\uD83E\uDDF8 Ayiqcha");
        row.add("🌹 Gul");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("🍫Shikolad");
        row2.add("Mushkambar");


        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");

        replyKeyboardMarkup.setKeyboard(List.of(row,row2,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
            return sendMessage;
    }


    public SendMessage menuToys(Long chatId){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        SendMessage sendMessage = new SendMessage(chatId.toString(),"Choose menu 😎");
        KeyboardRow row = new KeyboardRow();
        row.add("🚗O'yinchoq mashinalar");
        row.add("\uD83E\uDDF8 Yumshoq o'yinchiqlar");



        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");

        replyKeyboardMarkup.setKeyboard(List.of(row,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage menuAirpods(Long chatId){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        SendMessage sendMessage = new SendMessage(chatId.toString(),"Choose menu 😎");
        KeyboardRow row = new KeyboardRow();
        row.add("⌚️Qo'l soatlar");
        row.add("🕑Uy soatlar");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Simsiz naushniklar");
        row2.add("Simli naushniklar");




        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");

        replyKeyboardMarkup.setKeyboard(List.of(row,row2,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }


    public SendMessage menuPhone(Long chatId){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        SendMessage sendMessage = new SendMessage(chatId.toString(),"Choose menu 😎");
        KeyboardRow row = new KeyboardRow();
        row.add("Iphone 14");
        row.add("Samsung Galaxy S21 Ultra");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("\uD83D\uDCF1Samsung A52");
        row2.add("\uD83D\uDCF1Samsung A72");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("\uD83D\uDCF1Iphone 10 Pro");
        row3.add("\uD83D\uDCF1Redmi 11");


        KeyboardRow row1 = new KeyboardRow();
        row1.add("⬅️Back");

        replyKeyboardMarkup.setKeyboard(List.of(row,row2,row3,row1));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }



    public ReplyKeyboardRemove removeReplyKeyboard() {
        ReplyKeyboardRemove remove = new ReplyKeyboardRemove();
        remove.setRemoveKeyboard(true);
        return remove;
    }


}
