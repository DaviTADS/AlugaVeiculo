package com.mycompany.alugaveiculo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author davi, davydadriel
 */
@Entity
@Table(name = "TB_Veiculo")
public class Veiculo implements Serializable {


    
    @Id
    @Column(name = "ID_Veiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ElementCollection
    @CollectionTable(name = "TB_Placa",
            joinColumns = @JoinColumn(name = "ID_Veiculo"))
    @Column(name = "TXT_PLACA", table = "TB_Placa", nullable = false)
    private Collection<String> placas;
    
    
    @Column(name = "DT_ANOF", nullable = true)
    protected String anofabricacao;
    
    @Column(name="TXT_MODELO",length = 40 , nullable = false)
    protected String modelo;
    
    @Column(name="TXT_FABRICANTE",length = 20 , nullable = false)
    protected String fabricante;
    
    @Column(name="NUM_CAPACIDADE", nullable = false)
    protected int capacidade;
    
    @Column(name="TXT_TIPO",length = 20 , nullable = false)
    protected String tipo;
    
    @Column(name="TXT_PORTE",length = 40 , nullable = false)
    protected String porte;
    
    @Column(name = "TXT_DESC", length = 100 , nullable = false)
    protected String descricao;

    public Collection<String> getPlacas() {
        return placas;
    }

    public void setPlacas(Collection<String> placas) {
        this.placas = placas;
    }

    public String getAnofabricacao() {
        return anofabricacao;
    }

    public void setAnofabricacao(String anofabricacao) {
        this.anofabricacao = anofabricacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    protected Byte imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Byte getImagem() {
        return imagem;
    }

    public void setImagem(Byte imagem) {
        this.imagem = imagem;
    }

}
