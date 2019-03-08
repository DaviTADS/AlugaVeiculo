
package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "TB_PessoaF")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName = "ID")
public class PessoaFisica extends Pessoa implements Serializable {

@Column(name = "TXT_CPF", length = 11 , nullable = false)
private String cpf;

@Column(name = "TXT_CREDITOS",nullable = true)
private Integer creditos;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }



 
}
