package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Motorista")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName = "ID")
public class Motorista extends Pessoa implements Serializable {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_REPUTACAO", length = 20, nullable = false)
    private Reputacao reputacao;
    
    @Column(name = "TXT_CPF", length = 11 , nullable = false)
    private String cpf;
    
    
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
