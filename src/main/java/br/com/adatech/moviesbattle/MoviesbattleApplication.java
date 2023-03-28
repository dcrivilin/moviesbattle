package br.com.adatech.moviesbattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.adatech.moviesbattle.application.service.filme.CadastraFilmes;

@SpringBootApplication
public class MoviesbattleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MoviesbattleApplication.class, args);

		CadastraFilmes cadastraFilmes = run.getBean(CadastraFilmes.class);
		cadastraFilmes.carregarFilmes();
	}
}