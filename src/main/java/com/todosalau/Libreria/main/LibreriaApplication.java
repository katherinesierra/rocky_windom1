package com.todosalau.Libreria.main;

import com.todosalau.Libreria.Controlador.Controlador;
import com.todosalau.Libreria.Modelo.RepositorioLibreria;
import com.todosalau.Libreria.Vista.BibliotecaLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@ComponentScan("com.todosalau.Libreria.Modelo")
@EnableJdbcRepositories("com.todosalau.Libreria.Modelo")
public class LibreriaApplication {

    @Autowired
    RepositorioLibreria repositorioLibreria;

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(LibreriaApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

    }

    @Bean//se ejecuta antes de desplegar la aplicación
    ApplicationRunner applicationRunner() {
        return args -> {
            BibliotecaLibros vista = new BibliotecaLibros();
            Controlador Controlador = new Controlador(repositorioLibreria, vista);
        };
    }
}
