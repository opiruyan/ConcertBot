import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.github.cdimascio.dotenv.Dotenv;

public class Bot extends TelegramLongPollingBot {

  private Map<Long, Integer> conversations = new HashMap<>();  

  @Override
  public String getBotUsername() {
      return "test"; // what for we do we neeed this I dunno
  }

  @Override
  public String getBotToken() {
      Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
      return dotenv.get("BOT_TOKEN");
  }

  @Override
  public void onUpdateReceived(Update update) {
      var msg = update.getMessage();
      var user = msg.getFrom();

      var id = user.getId();

      try {
        sendText(id, msg.getText());
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
  }

  public void sendText(Long who, String what) throws UnsupportedEncodingException {
      conversations.compute(who, (k, v) -> v == null ? 0 : v + 1 );

      String text = choseText(conversations.get(who));
      String value = encodeRussianString(text);
      SendMessage sm = SendMessage.builder()
                       .chatId(who.toString()) 
                       .text(value).build();    
      try {
          execute(sm);                        
      } catch (TelegramApiException e) {
          throw new RuntimeException(e);
      }

      conversations.remove(who, 3);
  }

  private String encodeRussianString(String text) throws UnsupportedEncodingException {
      byte bytes[] = text.getBytes("UTF-8"); 
      return new String(bytes, "UTF-8");
  }

  private String choseText(Integer messageNumber) {
      String text = "Или как написал копайлот:" +"\n" +"Я уверен, что твое нежелание программировать связано с" +
                    "тем, что ты не знаешь, как это делать." + 
                    " Но это не проблема, ведь я могу тебе помочь!";
      String text2 = "Я уверен, что твое нежелание программировать связано с работой в предыдущих проектах," +
                     "где удовольствие и интерес от создания были убиты неправильно выбранными размерами и сложностью задач."
                     + " А так же оторванностью от проблем бизнеса";
      String text3 = "Как следствие, дофаминовая связь между удовольствием и программированием была нарушена." +
                     " А вместе с ней и мотивация.";
      String text4 = "На сейчас всё. Мне больше нечего тебе сказать";
      switch (messageNumber) {
        case 0:
            return text2;
        case 1:
            return text3;
        case 2:
            return text4;
        case 3:
            return text;
        default:
            return text;
      }
  }

}