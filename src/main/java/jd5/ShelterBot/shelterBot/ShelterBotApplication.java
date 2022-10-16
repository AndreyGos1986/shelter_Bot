package jd5.ShelterBot.shelterBot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

//В папку resources надо добавить свой файл "custom.properties" и в нем прописать данные для базы и токен телеги, добавить его в гит игнор
@PropertySource(value = "classpath:custom.properties", encoding = "UTF-8")
@SpringBootApplication
@OpenAPIDefinition
public class ShelterBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelterBotApplication.class, args);
	}

}
