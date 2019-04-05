/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes;

import static alugaveiculo.testes.GenericTest.logger;
import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.Reputacao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class TesteMotorista extends GenericTest {
    
    
    @Test
    public void persistirMotorista() {
        logger.info("Executando persistirMotorista()");
        Motorista motorista = criarMotorista();
        em.persist(motorista);
        em.flush();
        assertNotNull(motorista.getId());
        logger.info("id do motorista inserido = "+motorista.getId());
    }
    
    @Test
    public void atualizarMotorista(){
        logger.info("Executando atualizarMotorista()");
        Motorista motorista = em.find(Motorista.class,8L);
        motorista.setReputacao(Reputacao.Ruim);
        motorista.setSenha("5Laou.hy");
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Motorista motoristaBuscado = em.find(Motorista.class,8L,properties);
        assertEquals("5Laou.hy", motoristaBuscado.getSenha());
        assertEquals(Reputacao.Ruim,motorista.getReputacao());
    }
    
    @Test
    public void atualizarMotoristaMerge(){
        logger.info("Executando atualizarMotoristaMerge()");
        Motorista motorista = em.find(Motorista.class,7L);
        motorista.setNome("Lucas");
        motorista.setEmail("lucas@gmail.com");
        em.clear();
        em.merge(motorista);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Motorista motoristanovo = em.find(Motorista.class,7L,properties);
        assertEquals("Lucas",motorista.getNome());
        assertEquals("lucas@gmail.com",motorista.getEmail());
    }
    
    @Test
    public void deletarMotorista(){
        logger.info("Executando deletarMotorista()");
        Motorista motorista = em.find(Motorista.class, 9L);
        em.remove(motorista);
        Motorista motoristadeletado = em.find(Motorista.class, 9L);
        assertNull(motoristadeletado);
    
    }
    
    
    private Motorista criarMotorista(){
        Motorista motorista = new Motorista();
        String telefone1 = "32265894";
        String telefone2 = "995623148";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone1);
        telefones.add(telefone2);
        motorista.setNome("Pilotao");
        motorista.setSobrenome("Schumacher");
        motorista.setSenha("hyau8.Op");
        motorista.setTelefones(telefones);
        motorista.setCpf("337.664.370-97");
        motorista.setEmail("pilotaoa@gmail.com");
        String habilitacaoMoto = "Moto";
        String habilitacaoCarro = "Carro";
        List<String> habilitacoes = new ArrayList();
        habilitacoes.add(habilitacaoMoto);
        habilitacoes.add(habilitacaoCarro);
        motorista.setHabilitacoes(habilitacoes);
        motorista.setReputacao(Reputacao.Excelente);
        
        

        return motorista;
    }
    
}