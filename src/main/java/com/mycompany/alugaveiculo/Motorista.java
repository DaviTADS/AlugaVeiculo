package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MOTORISTA")
@DiscriminatorValue(value="M")
@PrimaryKeyJoinColumn(name="ID_PESSOA", referencedColumnName = "ID_PESSOA")
public class Motorista extends Pessoa implements Serializable {
    
    @Column(name = "TXT_NOME", length = 20, nullable = false)
    private String nome;

    @Column(name = "TXT_SOBRENOME", length = 60, nullable = false)
    private String sobrenome;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_REPUTACAO", length = 20, nullable = false)
    private Reputacao reputacao;
    
    @Column(name = "TXT_CPF", length = 11 , nullable = false)
    private String cpf;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    public Reputacao getReputacao() {
        return reputacao;
    }

    public void setReputacao(Reputacao reputacao) {
        this.reputacao = reputacao;
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
