//package alugaveiculo.testes;
//
//import com.mycompany.alugaveiculo.Aluguel;
//import com.mycompany.alugaveiculo.PessoaFisica;
//import com.mycompany.alugaveiculo.Veiculo;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.List;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import org.junit.Test;
//
//public class TesteAluguel extends GenericTest {
//
//    @Test
//    public void persistirAluguel(){
//        logger.info("Executando persistirAluguel()");
//        Aluguel aluguel = criarAluguel();
//        em.persist(aluguel);
//        em.flush();
//        assertNotNull(aluguel.getId());
//        logger.info("id do aluguel inserido = "+aluguel.getId());
//        
//    }
//    
//    @Test
//    public void atualizarAluguel(){
//        
//    }
//    
//    @Test
//    public void atualizarAluguelMerge(){
//        
//    }
//    
//    @Test
//    public void deletarAluguel(){
//        
//    }
//    
//    private Aluguel criarAluguel(){
//        Aluguel aluguel = new Aluguel();
//        Calendar ci = Calendar.getInstance();
//        ci.set(Calendar.YEAR, 2019);
//        ci.set(Calendar.MONTH, Calendar.APRIL);
//        ci.set(Calendar.DAY_OF_MONTH, 01);
//        aluguel.setDatainicio(ci.getTime());
//        Calendar cf = Calendar.getInstance();
//        cf.set(Calendar.YEAR, 2019);
//        cf.set(Calendar.MONTH, Calendar.APRIL);
//        cf.set(Calendar.DAY_OF_MONTH, 02);
//        aluguel.setDatafinal(cf.getTime());
//        aluguel.setPreco("R$1500");
//        PessoaFisica pessoa = criarPessoaFisica();
//        aluguel.setPessoa(pessoa);
//        List<Veiculo> veiculos = new ArrayList();
//        Veiculo veiculo01 = criarVeiculo();
//        veiculos.add(veiculo01);
//        aluguel.setVeiculos(veiculos);
//        
//        
//        return aluguel;
//    }
//    
//    
//    private PessoaFisica criarPessoaFisica() {
//        PessoaFisica pessoa01 = new PessoaFisica();
//        String telefone = "985447213";
//        Collection<String> telefones = new ArrayList();
//        telefones.add(telefone);
//        pessoa01.setNome("Anselmo");
//        pessoa01.setSobrenome("Noronha");
//        pessoa01.setSenha("456");
//        pessoa01.setTelefones(telefones);
//        pessoa01.setEmail("whatever@gmail.com");
//        pessoa01.setCpf("15648923578");
//        pessoa01.setCreditos("200");
//
//        return pessoa01;
//    }
//
//    private Veiculo criarVeiculo() {
//        Veiculo veiculo = new Veiculo();
//        veiculo.setAnofabricacao("2015");
//        veiculo.setCapacidade(5);
//        veiculo.setFabricante("Cintroen");
//        veiculo.setImagem(Byte.MIN_VALUE);
//        veiculo.setModelo("C4 PALLAS");
//        veiculo.setDescricao("Carro Sedan aconchegante ideal para atender a eventos de gala.");
//        veiculo.setPorte("Medio");
//        veiculo.setTipo("Sedan");
//        List<String> placas = new ArrayList();
//        placas.add("OPK5477");
//        placas.add("OPL2369");
//        veiculo.setPlacas(placas);
//
//        return veiculo;
//    }
//    
//    
//
//}
