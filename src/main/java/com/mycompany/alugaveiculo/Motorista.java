package com.mycompany.alugaveiculo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author davi, davydadriel
 */
@Entity
@Table(name = "TB_Motorista")
@DiscriminatorValue(value="M")
@PrimaryKeyJoinColumn(name="ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class Motorista extends Pessoa implements Serializable {
    
    
    @ElementCollection
    @CollectionTable(name = "TB_Habilitacoes",
            joinColumns = @JoinColumn(name = "ID_Pessoa"))
    //@Basic(fetch = FetchType.LAZY)
    @Column(name = "TXT_Habilitacao", table = "TB_Habilitacoes", nullable = false)
    private List<String> habilitacoes;
    

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
