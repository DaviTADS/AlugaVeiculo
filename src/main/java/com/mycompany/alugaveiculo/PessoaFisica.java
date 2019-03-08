
package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "TB_PESSOAF")
@DiscriminatorValue(value="PF")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName = "ID_PESSOA")
public class PessoaFisica extends Pessoa implements Serializable {

@Column(name = "TXT_CPF", length = 11 , nullable = false)
private String cpf;

@Column(name = "TXT_CREDITOS",nullable = true)
private String creditos;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }



 
}
