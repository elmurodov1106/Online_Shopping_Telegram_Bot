package uz.pdp.online_shop.shoppingBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.online_shop.model.User.User;
import uz.pdp.online_shop.service.UserService.UserServiceImpl;
import uz.pdp.online_shop.shoppingBot.service.ShoppingBotService;

import java.io.File;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static uz.pdp.online_shop.shoppingBot.UserState.*;

public class OnlineShoppingBot extends TelegramLongPollingBot {


    UserServiceImpl userService = new UserServiceImpl();


    ShoppingBotService shoppingBotService = new ShoppingBotService();
    static ExecutorService executorService = Executors.newCachedThreadPool();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {


            if (update.hasMessage()) {
                Message message = update.getMessage();
                String text = message.getText();
                Long chatId = message.getChatId();
                User currentUser = userService.getByChatId(chatId);


                if (currentUser == null) {
                    if (message.hasContact()) {
                        Chat chat = message.getChat();
                        Contact contact = message.getContact();
                        Location location = message.getLocation();
                        userService.add(contact, chat.getBio(), 0.0, 0, chat.getUserName());
                        execute(shoppingBotService.welcomeMessage(chatId.toString()));

                    } else {
                        execute(shoppingBotService.requestContactMessage(chatId.toString()));
                      if(message.getLocation() == null) {
                        execute(shoppingBotService.requestLocationMessage(chatId.toString()));
                    }
                    }

                }

                SendMessage sendMessage5 = null;
                if (Objects.requireNonNull(currentUser).getUserState().equals(REGISTERED)) {
                    sendMessage5 = shoppingBotService.navigateUser(chatId, text);
                    execute(sendMessage5);
                }
                switch (Objects.requireNonNull(currentUser).getUserState()) {
//                case REGISTERED -> {
//                    execute(sendMessage5);
//                }
                    case COSMETICA -> {
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            execute(sendMessage1);
                        }if (message.getText().equals("\uD83C\uDFCB️\u200D♂️Gantellar")) {
                            execute(shoppingBotService.sendGantel(chatId.toString()));
                        }
                        else if (text.equals("Mishoklar")){
                            execute(shoppingBotService.sendMishok(chatId.toString()));
                        }


                    }
                    case PHONES -> {
                        SendMessage sendMessage = shoppingBotService.menuPhones(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), " menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                            execute(sendMessage1);
                        } else if (message.getText().equals("\uD83D\uDCF1 Telefonlar")) {
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Apple_iPhone_Ayfon_Bozor_Savdo/9179"));
                            sendImage.add(new InputFile("https://t.me/Apple_iPhone_Ayfon_Bozor_Savdo/9177"));
                            sendImage.add(new InputFile("https://t.me/Apple_iPhone_Ayfon_Bozor_Savdo/9176"));
                            sendImage.add(new InputFile("https://t.me/Apple_iPhone_Ayfon_Bozor_Savdo/9174"));
                            sendImage.add(new InputFile("https://t.me/telegon_sotiladi/179"));
                            sendImage.add(new InputFile("https://t.me/MARGILON_TELEGON_BOZOR_FARGONA/71985"));
                            sendImage.add(new InputFile("https://t.me/MARGILON_TELEGON_BOZOR_FARGONA/71992"));
                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }}
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday \uD83D\uDCF1 Telefonlar  kerak bo'lsa,\n \uD83D\uDCE5 Buyurtma uchun 👇:\n @Elmurodov_1106");
                            execute(sendMessage1);
                            }else if (message.getText().equals("\uD83E\uDDF0 Aksessuvarlar")) {
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/chelak_aksessuar_telefon/6853"));
                            sendImage.add(new InputFile("https://t.me/chelak_aksessuar_telefon/6892"));
                            sendImage.add(new InputFile("https://t.me/chelak_aksessuar_telefon/6937"));
                            sendImage.add(new InputFile("https://t.me/aksessuari_telefon/7202"));
                            sendImage.add(new InputFile("https://t.me/aksessuari_telefon/7223"));
                            sendImage.add(new InputFile("https://t.me/vodiy_aksessuar_telefon_bozor/206"));
                            sendImage.add(new InputFile("https://t.me/vodiy_aksessuar_telefon_bozor/212"));
                            sendImage.add(new InputFile("https://t.me/vodiy_aksessuar_telefon_bozor/220"));
                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }}
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday \uD83E\uDDF0 Aksessuvarlar  kerak bo'lsa,\n \uD83D\uDCE5 Buyurtma uchun 👇:\n @Elmurodov_1106");
                            execute(sendMessage1);
                        }

                    }
                    case HOUSES -> {
                        SendMessage sendMessage = shoppingBotService.menuHouses(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            execute(sendMessage1);
                        } else if (text.equals("\uD83C\uDFE0 Turar joylar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831260"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831261"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831265"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831264"));
                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(),"Зангота тумани \n" +
                                    "Назарбек. Харакат \n" +
                                    "3 сотих ва 2,5 сотихлик \n" +
                                    "6 хона \n" +
                                    "2 санузел \n" +
                                    "Под ключ холда топширилади \n" +
                                    "Сув свет газ бор \n" +
                                    "Ремонт ишлари хали тугамади \n" +
                                    "Бориб танлаб олиш имкони бор\n" +
                                    "  \n" +
                                    " Нархи.  120000 дан хар бири\n" +
                                    "\n" +
                                    "Тел : +998932841106\n" +
                                    "\n" +
                                    "Уй кидириб юрган кадирдонларга юборамиз");
                            execute(sendMessage1);

                        }else if (text.equals("\uD83C\uDFE0 Kvartira")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831300"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831301"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831303"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831305"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831306"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Sotiladgan_Ijara_Uylar/831307"));
                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(),"❗️❗️❗️СРОЧНО \n" +
                                    "Аренда \n" +
                                    "#Город -Ташкент\n" +
                                    "#Район -шайхонтохир дружба \n" +
                                    "#Комнат -2\n" +
                                    "#Этаж-3\n" +
                                    "#Этажность -9\n" +
                                    "#Цена -800$\n" +
                                    "#Тел +998932841106");
                            execute(sendMessage1);
                        }
                    }
                    case AIRPODS -> {
                        SendMessage sendMessage = shoppingBotService.menuAirpods(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            execute(sendMessage1);
                        }
                        else if (text.equals("⌚️Qo'l soatlar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/smart_watch_iwatch/5"));
                            sendImage.add(new InputFile("https://t.me/smart_watch_iwatch/7"));
                            sendImage.add(new InputFile("https://t.me/iWatch_6/3"));
                            sendImage.add(new InputFile("https://t.me/SOATLAR_QOL_SOATLAR/601"));
                            sendImage.add(new InputFile("https://t.me/SOATLAR_QOL_SOATLAR/602"));
                            sendImage.add(new InputFile("https://t.me/soat_namangan/131998"));
                            sendImage.add(new InputFile("https://t.me/soat_namangan/131997"));
                            sendImage.add(new InputFile("https://t.me/soat_namangan/132444"));
                            sendImage.add(new InputFile("https://t.me/Soatlar_osma_va_qol_soat/1097"));

                            for (InputFile file : sendImage) {
                                try {
                                   SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday ⌚️Qo'l soatlar  kerak bo'lsa,\n murojat qlovurasla👇:" +
                                    "\n @Elmurodov_1106 ");
                            execute(sendMessage1);
                        } else if (text.equals("\uD83D\uDD51Uy soatlar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Soatlar_osma_va_qol_soat/1157"));


                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday \uD83D\uDD51 Uy soatlar  kerak bo'lsa,\n murojat qlovurasla👇:" +
                                    "\n @Elmurodov_1106 ");
                            execute(sendMessage1);
                        }
                        else if (text.equals("Simsiz naushniklar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/olmaliq_naushniklar/172"));
                            sendImage.add(new InputFile("https://t.me/olmaliq_naushniklar/181"));
                            sendImage.add(new InputFile("https://t.me/olmaliq_naushniklar/188"));
                            sendImage.add(new InputFile("https://t.me/olmaliq_naushniklar/195"));
                            sendImage.add(new InputFile("https://t.me/olmaliq_naushniklar/199"));
                            sendImage.add(new InputFile("https://t.me/olmaliq_naushniklar/214"));
                            sendImage.add(new InputFile("https://t.me/BluetoothEarphoneNiecomelNoeadry/7"));
                            sendImage.add(new InputFile("https://t.me/BluetoothEarphoneNiecomelNoeadry/5"));

                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday Simsiz naushniklar  kerak bo'lsa,\n \uD83D\uDCE5 Buyurtma uchun 👇:\n @Elmurodov_1106");
                            execute(sendMessage1);
                        }
                        else if (text.equals("Simli naushniklar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/bizdashop/1575"));
                            sendImage.add(new InputFile("https://t.me/bizdashop/1537"));
                            sendImage.add(new InputFile("https://t.me/Naushniklar_optom_quloqchinlar/58"));

                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday Simli naushniklar soatlar  kerak bo'lsa,\n \uD83D\uDCE5 Buyurtma uchun:\n @Elmurodov_1106");
                            execute(sendMessage1);
                        }

                    }
                    case TOYS -> {
                        SendMessage sendMessage = shoppingBotService.menuToys(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            execute(sendMessage1);
                        }else if (text.equals("\uD83D\uDE97O'yinchoq mashinalar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/StarToysUz/3209"));
                            sendImage.add(new InputFile("https://t.me/StarToysUz/3217"));
                            sendImage.add(new InputFile("https://t.me/StarToysUz/3218"));
                            sendImage.add(new InputFile("https://t.me/StarToysUz/3227"));
                            sendImage.add(new InputFile("https://t.me/StarToysUz/3232"));

                            for (InputFile file : sendImage) {
                                try {
                                   SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday o'yinchoqlar kerak bo'lsa,\n murojat uchun tel👇:" +
                                    "\n +998932841106 ");
                            execute(sendMessage1);
                        } else if (text.equals("\uD83E\uDDF8 Yumshoq o'yinchiqlar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48805"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48803"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48800"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48185"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48207"));

                            for (InputFile file : sendImage) {
                                try {
                                   SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday  yumshoq o'yinchoqlar kerak bo'lsa,\n murojat uchun tel👇:" +
                                    "\n +998932841106 ");
                            execute(sendMessage1);
                        }

                    }
                    case CARS -> {
                        SendMessage sendMessage = shoppingBotService.menuCars(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            execute(sendMessage1);
                        }
                        if (message.getText().equals("\uD83D\uDE98 Moshinalar")) {
                            execute(shoppingBotService.carphoto(chatId.toString()));
                        } else if (message.getText().equals("\uD83D\uDD27Aksesuvarlar")) {
                            execute(shoppingBotService.carphotoAcsessuvar(chatId.toString()));
                        }

                    }
                    case PRICES -> {
                        SendMessage sendMessage = shoppingBotService.menuPrises(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                            execute(sendMessage1);
                        } else if (text.equals("\uD83E\uDDF8 Ayiqcha")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48189?single"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48196"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48197"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48202"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48200"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48204"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48173"));
                            sendImage.add(new InputFile("https://t.me/Myaxkiii/48193"));
                            for (InputFile file : sendImage) {
                                try {
                                    SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimlarga Shunday Ayiqchalar kerak bo'lsa,\n murojat uchun tel👇:" +
                                    "\n +998932841106 ");
                            execute(sendMessage1);
                        }  else if (text.equals("\uD83C\uDF39 Gul")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/Toshkent_Gullar_Gular/1283"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Gullar_Gular/1278"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Gullar_Gular/1263"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Gullar_Gular/1258"));
                            sendImage.add(new InputFile("https://t.me/Toshkent_Gullar_Gular/1246"));

                            for (InputFile file : sendImage) {
                                try {
                                    SendVideo sendPhoto = new SendVideo();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setChatId(chatId);
                                    sendPhoto.setVideo(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday gullar kerak bo'lsa,\n murojat uchun tel👇:" +
                                    "\n +998932841106 ");
                            execute(sendMessage1);
                        } else if (text.equals("\uD83C\uDF6BShikolad")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/nestle1290/583"));
                            sendImage.add(new InputFile("https://t.me/nestle1290/594"));
                            sendImage.add(new InputFile("https://t.me/nestle1290/595"));
                            sendImage.add(new InputFile("https://t.me/craferskatalogandijon/340"));
                            sendImage.add(new InputFile("https://t.me/craferskatalogandijon/363"));
                            for (InputFile file : sendImage) {
                                try {
                                   SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }

                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday \uD83C\uDF6BShikoladlar  kerak bo'lsa,\n murojat uchun tel👇:" +
                                    "\n +998932841106 ");
                            execute(sendMessage1);
                        }
                        else if (text.equals("Mushkambar")){
                            List<InputFile> sendImage = new ArrayList<>();
                            sendImage.add(new InputFile("https://t.me/mushk706/2094"));
                            sendImage.add(new InputFile("https://t.me/mushk706/2095"));
                            sendImage.add(new InputFile("https://t.me/toshkent_mushkanbar_online_savdo/2186"));
                            sendImage.add(new InputFile("https://t.me/toshkent_mushkanbar_online_savdo/2204"));
                            sendImage.add(new InputFile("https://t.me/mushk706/2089"));

                            for (InputFile file : sendImage) {
                                try {
                                   SendPhoto sendPhoto = new SendPhoto();
                                    sendPhoto.setChatId(message.getChatId());
                                    sendPhoto.setChatId(chatId);
                                    sendPhoto.setPhoto(file);
                                    execute(sendPhoto);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }

                            SendMessage sendMessage1 = new SendMessage(chatId.toString()," Kimga shunday Mushkambarlar kerak bo'lsa,\n murojat uchun tel👇:" +
                                    "\n +998932841106 ");
                            execute(sendMessage1);
                        }
                    }
                    case BOOKS -> {
                        SendMessage sendMessage = shoppingBotService.menuBooks(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);

                            execute(sendMessage1);
                        }
                        if (message.getText().equals("\uD83D\uDCD7 Badiy kitoblar")) {
                            List<InputFile> filesToSend = new ArrayList<>();
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3986"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3966"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3983"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3964"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3955"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3954"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3950"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3945"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3939"));
                            filesToSend.add(new InputFile("https://t.me/badiiykitoblarolami/3919"));
                            InputBookFile(message, filesToSend);
                        } else if (message.getText().equals("\uD83D\uDCD8 Ilmiy kitoblar")) {
                            List<InputFile> filesToSend = new ArrayList<>();
                            filesToSend.add(new InputFile(new File("C:\\\\Users\\\\Msi Laptop\\\\Documents\\\\Abduxoliq_Abdurasul_o'g'li_Qadimgi.pdf")));
                            filesToSend.add(new InputFile(new File("C:\\\\Users\\\\Msi Laptop\\\\Documents\\\\Custom Office Templates\\\\Islom_Karimov_Asarlar_10_jild_O zbekiston.pdf (2).pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\Custom Office Templates\\Ozod_Sharafiddinov_Mustafo_Cho'qay.pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\Custom Office Templates\\Prosper Merime. Karl IX saltanatining yilnomasi (roman).pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\Custom Office Templates\\Q. Usmonov MSodiqov Burxonov.pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\Custom Office Templates\\temuriylar_davri_tarixshunosligi.pdf")));
                            InputBookFile(message, filesToSend);
                        }else if (message.getText().equals("\uD83D\uDCD7 Dasturlash")) {
                            List<InputFile> filesToSend = new ArrayList<>();
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\GTA Vice City User Files\\@iBooks_Bot java_tilida_qiymat_q.pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\GTA Vice City User Files\\Data Structures with Java ( PDFDrive ).pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\GTA Vice City User Files\\Java Programming Language Handbook ( PDFDrive ).pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\GTA Vice City User Files\\JavaScript® For Kids For Dummies ( PDFDrive ).pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\GTA Vice City User Files\\THE_Java™_Programming_Language,_Fourth_Edition_PDFDrive_.pdf")));
                            filesToSend.add(new InputFile(new File("C:\\Users\\Msi Laptop\\Documents\\GTA Vice City User Files\\Робин_Никсон_PHP,_MySQL,_JavaScript@bzd_channel.pdf")));
                            InputBookFile(message, filesToSend);
                        } else if (message.getText().equals("\uD83D\uDCD8 Diniy kitoblar")) {
                            List<InputFile> filesToSend = new ArrayList<>();
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/251"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/250"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/248"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/246"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/230"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/223"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/222"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/221"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/220"));
                            filesToSend.add(new InputFile("https://t.me/diniykitoblar_pdf/219"));
                            InputBookFile(message, filesToSend);
                        }
                    }
                    case BOGLANISH -> {
                        SendMessage sendMessage = shoppingBotService.menuBooks(chatId);
                        execute(sendMessage);
                        if (message.getText().equals("⬅️Back")) {
                            userService.updateState(chatId, REGISTERED);
                            SendMessage sendMessage1 = new SendMessage(chatId.toString(), "menu");
                            ReplyKeyboardMarkup replyKeyboardMarkup = shoppingBotService.menuButtons();
                            sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                            execute(sendMessage1);
                        }

                    }

                }
            }
    }



    private void InputBookFile(Message message, List<InputFile> filesToSend) {
        for (InputFile file : filesToSend) {
            try {
                SendDocument sendDocument = new SendDocument();
                sendDocument.setChatId(message.getChatId());
                sendDocument.setDocument(file);
                execute(sendDocument);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public String getBotUsername() {
        return "MuSiQaizlovchobot";
    }

    @Override
    public String getBotToken() {
        return "5971363351:AAH78IyqzcYJfYXV0rXnm8pMk_AtagSarcg";
    }
}



