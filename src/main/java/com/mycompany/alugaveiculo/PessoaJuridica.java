
package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "TB_PessoaJ")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName = "ID")
public class PessoaJuridica extends Pessoa implements Serializable {
    

@Column(name = "TXT_RAZAOSOCIAL", length = 20, nullable = false)
private String razaosocial;

@Column(name = "TXT_CNPJ", length = 20, nullable = false)
private String cnpj;

@Column(name = "TXT_CREDITOS",nullable = true)
private Integer creditos;


    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }


}
