package com.mycompany.alugaveiculo;

import java.util.Date;

/**
 *
 * @author davi
 */
public class Veiculo {
    
protected Long id;
    
protected Date ano;

protected String modelo;

protected String fabricante;

protected String capacidade;

protected String tipo;

protected String porte;

protected Byte imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Byte getImagem() {
        return imagem;
    }

    public void setImagem(Byte imagem) {
        this.imagem = imagem;
    }


                
}