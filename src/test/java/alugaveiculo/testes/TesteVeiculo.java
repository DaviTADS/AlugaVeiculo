//package alugaveiculo.testes;
//
//import static alugaveiculo.testes.GenericTest.logger;
//import com.mycompany.alugaveiculo.Veiculo;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.Assert.assertNotNull;
//import org.junit.Test;
//
//
///**
// *
// * @author davi, davydadriel
// */
//public class TesteVeiculo extends GenericTest{
//    @Test
//    public void persistVeiculo() {
//        logger.info("Executando persistVeiculo()");
//        Veiculo veiculo = criarVeiculo();
//        em.persist(veiculo);
//        em.flush();
//        assertNotNull(veiculo.getId());
//        
//    }
//    
//    
//    public Veiculo criarVeiculo(){
//        Veiculo veiculo = new Veiculo();
//        veiculo.setAnofabricacao("2000");
//        veiculo.setCapacidade(6);
//        veiculo.setFabricante("Honda");
//        veiculo.setImagem(Byte.MIN_VALUE);
//        veiculo.setModelo("F156");
//        veiculo.setPorte("Grande");
//        veiculo.setTipo("Esportivo");
//        veiculo.setDescricao("Descrição...");
//        List<String> placas = new ArrayList();
//        placas.add("AXM52");
//        placas.add("V5C0D3");
//        veiculo.setPlacas(placas);
//        
//        
//        return veiculo;
//    }    
//}
