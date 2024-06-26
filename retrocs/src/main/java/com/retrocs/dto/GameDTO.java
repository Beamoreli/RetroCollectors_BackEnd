package com.retrocs.dto;

import com.retrocs.model.Console;
import com.retrocs.model.Distribuidora;
import com.retrocs.model.Genero;

import java.util.Set;

public class GameDTO {

    private Integer id;
    private String nome;
    private String imagem;
    private Integer anoLancamento;
    private Console console;
    private Distribuidora distribuidora;
    private Set<Genero> generos;

    public GameDTO() {
    }

    public GameDTO(String nome, String imagem, Integer anoLancamento, Console console, Distribuidora distribuidora, Set<Genero> generos) {
        this.nome = nome;
        this.imagem = imagem;
        this.anoLancamento = anoLancamento;
        this.console = console;
        this.distribuidora = distribuidora;
        this.generos = generos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Distribuidora getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidora distribuidora) {
        this.distribuidora = distribuidora;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
