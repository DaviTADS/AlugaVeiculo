/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alugaveiculo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

@Temporal(TemporalType.DATE)
@Column(name = "DT_INICIO", nullable = false)
private Date datainicio;

@Temporal(TemporalType.DATE)
@Column(name = "DT_FINAL", nullable = false)
private Date datafinal;

@Column(name="TXT_PRECO",length = 20, nullable = false)
private String preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }



}
