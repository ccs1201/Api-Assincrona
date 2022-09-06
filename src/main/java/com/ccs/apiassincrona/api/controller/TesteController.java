package com.ccs.apiassincrona.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@RequestMapping("/testes")
public class TesteController {

    private int asyncCount = 0;
    private int syncCount = 0;

    @RequestMapping("/async")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> async() {

        asyncCount++;

        var countString = String.format("Requisição assíncrona # %s recebida em: %s | Finalizada em: ", asyncCount, LocalDateTime.now());

        //processa a requisição numa "thread separada" com um atraso de 10 segundos
        var response = supplyAsync(() ->
                countString + LocalDateTime.now(), CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS));

        //Quando o processamento assíncrono terminar loga a countString
        response.thenAccept(s ->
                Logger.getGlobal().log(Level.INFO, s));

        return response;

    }

    @RequestMapping("/sync")
    @ResponseStatus(HttpStatus.OK)
    public String sync() throws InterruptedException {

        syncCount++;

        String countString = String.format("Requisição síncrona # %s recebida em: %s", syncCount, LocalDateTime.now());

        //pausa a thread por 10 segundos
        Thread.sleep(10000);

        countString = String.format(countString + " | Finalizada em: %s", LocalDateTime.now());

        Logger.getGlobal().log(Level.INFO, countString);

        return "";
    }
}
