package com.fuctura.biblioteca.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fuctura.biblioteca.services.DBService;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("dev") //mudar para dev
public class TestProfile {

    @Autowired
    private DBService dbService;

    @PostConstruct
    public void instanciaDB() {
        // Método para instanciar o banco de dados com dados de teste
        dbService.instanciaDB();
    }
}
