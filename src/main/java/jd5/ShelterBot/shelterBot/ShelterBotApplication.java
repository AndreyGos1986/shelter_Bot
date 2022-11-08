package jd5.ShelterBot.shelterBot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

//В папку resources надо добавить свой файл "custom.properties" и в нем прописать данные для базы и токен телеги, добавить его в гит игнор
@OpenAPIDefinition
@EnableScheduling
@SpringBootApplication
public class ShelterBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterBotApplication.class, args);
	}

}
