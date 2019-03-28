package com.mycompany.alugaveiculo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author davi, davydadriel
 */
@Entity
@SecondaryTable(name = "TB_Habilitacoes",
        pkJoinColumns = {
        @PrimaryKeyJoinColumn(name = "ID_Pessoa")}
)
@Table(name = "TB_Motorista")
@DiscriminatorValue(value="M")
@PrimaryKeyJoinColumn(name="ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class Motorista extends Pessoa implements Serializable {
    
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "TXT_HABILITACOES", table = "TB_Habilitacoes", nullable = false)
    private List<String> habilitacoes;
    
    @Column(name = "TXT_NOME", length = 20, nullable = false)
    private String nome;

    @Column(name = "TXT_SOBRENOME", length = 60, nullable = false)
    private String sobrenome;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_REPUTACAO", length = 20, nullable = false)
    private Reputacao reputacao;
    
    @Column(name = "TXT_CPF", length = 11 , nullable = false)
    private String cpf;

    public List<String> getHabilitacoes() {
        return habilitacoes;
    }

    public void setHabilitacoes(List<String> habilitacoes) {
        this.habilitacoes = habilitacoes;
    }
    
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
