package com.antiprag.prag;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = "app.dev")
@SpringBootApplication
public class PragApplication {

	private Boolean development;

	public void setDevelopment(Boolean development) {
		this.development = development;
	}

	public static void main(String[] args) {
		SpringApplication.run(PragApplication.class, args);
	}

	@Bean
	public CommandLineRunner runOnStartup() {
		if (Boolean.TRUE.equals(development)) {
			return args -> {
				System.out.println(" ______   _______  __   __    __   __  _______  ______   _______ \n" + //
						"|      | |       ||  | |  |  |  |_|  ||       ||      | |       |\n" + //
						"|  _    ||    ___||  |_|  |  |       ||   _   ||  _    ||    ___|\n" + //
						"| | |   ||   |___ |       |  |       ||  | |  || | |   ||   |___ \n" + //
						"| |_|   ||    ___||       |  |       ||  |_|  || |_|   ||    ___|\n" + //
						"|       ||   |___  |     |   | ||_|| ||       ||       ||   |___ \n" + //
						"|______| |_______|  |___|    |_|   |_||_______||______| |_______|\n" + //
						"Atenção! Rotas que precisam de created_by e edited_by não funcionarão!\n");
			};
		} else {
			return args -> {
				System.out.println(" _______  ______    _______  ______     __   __  _______  ______   _______ \n" + //
										"|       ||    _ |  |       ||      |   |  |_|  ||       ||      | |       |\n" + //
										"|    _  ||   | ||  |   _   ||  _    |  |       ||   _   ||  _    ||    ___|\n" + //
										"|   |_| ||   |_||_ |  | |  || | |   |  |       ||  | |  || | |   ||   |___ \n" + //
										"|    ___||    __  ||  |_|  || |_|   |  |       ||  |_|  || |_|   ||    ___|\n" + //
										"|   |    |   |  | ||       ||       |  | ||_|| ||       ||       ||   |___ \n" + //
										"|___|    |___|  |_||_______||______|   |_|   |_||_______||______| |_______|");
			};
		}

	}

}
