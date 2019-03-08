package com.mycompany.alugaveiculo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author davi
 */
@Entity
@Table(name = "TB_VEICULO")
public class Veiculo implements Serializable {

    @Id
    @Column(name = "ID_Veiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    
    @Column(name = "DT_ANOF", nullable = true)
    protected String anofabricacao;
    
    @Column(name="TXT_MODELO",length = 40 , nullable = false)
    protected String modelo;
    
    @Column(name="TXT_FABRICANTE",length = 20 , nullable = false)
    protected String fabricante;
    
    @Column(name="TXT_CAPACIDADE",length = 2 , nullable = false)
    protected String capacidade;
    
    @Column(name="TXT_TIPO",length = 20 , nullable = false)
    protected String tipo;
    
    @Column(name="TXT_PORTE",length = 40 , nullable = false)
    protected String porte;
    
    @Column(name = "TXT_DESC", length = 100 , nullable = false)
    protected String descricao;
    
    protected Byte imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAno() {
        return anofabricacao;
    }

    public void setAno(String anofabricacao) {
        this.anofabricacao = anofabricacao;
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

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
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
