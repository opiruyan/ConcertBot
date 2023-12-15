import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import io.github.cdimascio.dotenv.Dotenv;

public class Bot extends TelegramLongPollingBot {

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
      sendText(id, msg.getText());
  }

  public void sendText(Long who, String what) {
      SendMessage sm = SendMessage.builder()
                       .chatId(who.toString()) 
                       .text(what).build();    
      try {
          execute(sm);                        
      } catch (TelegramApiException e) {
          throw new RuntimeException(e);
      }
}

}