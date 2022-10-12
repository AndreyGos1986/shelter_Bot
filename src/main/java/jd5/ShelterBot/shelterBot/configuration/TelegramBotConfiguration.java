package jd5.ShelterBot.shelterBot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//В папку resources надо добавить свой файл "custom.properties" и в нем прописать данные для базы, имя бота и токен телеги, добавить его в гит игнор
@Configuration
@Data
@PropertySource(value = "classpath:custom.properties", encoding = "UTF-8")
public class TelegramBotConfiguration {

    //Имя бота
    @Value("${bot.name}")
    private String botName;

    //Токен
    @Value("${bot.token}")
    private String token;


//Бин создания нового экземпляра бота
    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }
}
