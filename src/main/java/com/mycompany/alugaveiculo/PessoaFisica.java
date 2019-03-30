package com.mycompany.alugaveiculo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author davi, davydadriel
 */
@Entity
@Table(name = "TB_PessoaFisica")
@DiscriminatorValue(value = "F")
@PrimaryKeyJoinColumn(name = "ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class PessoaFisica extends Pessoa implements Serializable {

    @OneToMany(mappedBy = "pessoa",fetch = FetchType.LAZY,
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluguel> alugueis; 
    
    @Column(name = "TXT_SOBRENOME", length = 60, nullable = false)
    private String sobrenome;

    @Column(name = "TXT_CPF", length = 11, nullable = false)
    private String cpf;

    @Column(name = "TXT_CREDITOS", nullable = true)
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

     public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
}
