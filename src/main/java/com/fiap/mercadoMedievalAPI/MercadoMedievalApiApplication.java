package com.fiap.mercadoMedievalAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "FinMoney API", version = "v1", description = "API do SaaS FinMoney")
)
@EnableCaching
public class MercadoMedievalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoMedievalApiApplication.class, args);
	}

}
