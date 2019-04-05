package alugaveiculo.testes;

import static alugaveiculo.testes.GenericTest.logger;
import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.Reputacao;
import com.mycompany.alugaveiculo.Veiculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CacheRetrieveMode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;


/**
 *
 * @author davi, davydadriel
 */
public class TesteVeiculo extends GenericTest{
    
     
     
    
    @Test
    public void persistVeiculo() {
        logger.info("Executando persistVeiculo()");
        Veiculo veiculo = criarVeiculo();
        em.persist(veiculo);
        em.flush();
        logger.info("Verificando id = "+veiculo.getId());
        assertNotNull(veiculo.getId());
        
    }

    @Test
    public void atualizarVeiculo(){
        logger.info("Executando atualizarVeiculo()");
        Veiculo veiculo = em.find(Veiculo.class,1L);
        veiculo.setAnofabricacao("2011");
        veiculo.setModelo("Fiat Uno Eco");
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Veiculo veiculoBuscado = em.find(Veiculo.class,1L,properties);
        assertEquals("2011", veiculoBuscado.getAnofabricacao());
        assertEquals("Fiat Uno Eco",veiculoBuscado.getModelo());
    }

    @Test
    public void atualizarVeiculoMerge(){
        logger.info("Executando atualizarVeiculoMarge()");
        Veiculo veiculo = em.find(Veiculo.class,2L);
        veiculo.setAnofabricacao("1993");
        em.clear();
        em.merge(veiculo);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Veiculo veiculoBuscado = em.find(Veiculo.class, 2L,properties);
        assertEquals("1993", veiculoBuscado.getAnofabricacao());
        assertNotEquals("1992",veiculoBuscado.getAnofabricacao());
    
    }    

    @Test
    public void deletarVeiculo(){
       logger.info("Executando deletarVeiculo()");
       Veiculo veiculo = em.find(Veiculo.class, 3L);
       em.remove(veiculo);
       Veiculo veiculoBuscado = em.find(Veiculo.class, 3L);
       assertNull(veiculoBuscado);
    }
    
    public Veiculo criarVeiculo(){
        Veiculo veiculo = new Veiculo();
        Motorista motorista = criarMotorista();
        veiculo.setMotorista(motorista);
        veiculo.setAnofabricacao("2000");
        veiculo.setCapacidade(6);
        veiculo.setFabricante("Honda");
        veiculo.setImagem(Byte.MIN_VALUE);
        veiculo.setModelo("F156");
        veiculo.setPorte("Grande");
        veiculo.setTipo("Esportivo");
        veiculo.setDescricao("Descrição legal para um carro");
        List<String> placas = new ArrayList();
        placas.add("AXM52");
        placas.add("V5C0D3");
        veiculo.setPlacas(placas);
        
        
        return veiculo;
    }  
    
    private Motorista criarMotorista(){
        Motorista motorista = new Motorista();
        String telefone1 = "999666886";
        String telefone2 = "977773214";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone1);
        telefones.add(telefone2);
        motorista.setNome("Barrigrelo");
        motorista.setSobrenome("Lotovisk");
        motorista.setSenha("2gtde.Lo");
        motorista.setTelefones(telefones);
        motorista.setCpf("109.755.680-86");
        motorista.setEmail("pilotinho54@gmail.com");
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
