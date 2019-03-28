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
@Table(name = "TB_PessoaJuridica")
@DiscriminatorValue(value="J")
@PrimaryKeyJoinColumn(name="ID_Pessoa", referencedColumnName = "ID_Pessoa")
public class PessoaJuridica extends Pessoa implements Serializable {
    
    @OneToMany(mappedBy = "pessoa",fetch = FetchType.LAZY,
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluguel> alugueis; 
    
    @Column(name = "TXT_RAZAOSOCIAL", length = 50, nullable = false)
    private String razaosocial;

    @Column(name = "TXT_CNPJ", length = 20, nullable = false)
    private String cnpj;

    @Column(name = "TXT_CREDITOS",nullable = true)
    private String creditos;


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

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }


}
