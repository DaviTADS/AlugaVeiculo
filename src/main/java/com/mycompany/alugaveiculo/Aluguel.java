/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author davi
 */
@Entity
 @Table(name = "TB_ALUGUEL")
public class Aluguel implements Serializable{
    
@Id
@Column(name = "ID_Aluguel")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "TXT_DATAINICIO",length = 8, nullable = false)
private String datainicio;

@Column(name = "TXT_DATAFINAL",length = 8, nullable = false)
private String datafinal;

@Column(name="TXT_PRECO",length = 10, nullable = false)
private String preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(String datainicio) {
        this.datainicio = datainicio;
    }

    public String getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(String datafinal) {
        this.datafinal = datafinal;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }



}
