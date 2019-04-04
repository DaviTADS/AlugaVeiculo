
package alugaveiculo.testes;

import com.mycompany.alugaveiculo.Motorista;
import static alugaveiculo.testes.GenericTest.logger;
import com.mycompany.alugaveiculo.Aluguel;
import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.PessoaJuridica;
import com.mycompany.alugaveiculo.Reputacao;
import com.mycompany.alugaveiculo.Veiculo;
import java.util.ArrayList;
import java.util.Calendar;
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
public class TestePessoa extends GenericTest {
    
    @Test
    public void persistirPessoaFisica() {
        logger.info("Executando persistirPessoa()");
        PessoaFisica pessoa = criarPessoaFisica();
        em.persist(pessoa);
        em.flush();
        assertNotNull(pessoa.getId());
        logger.info("id da pessoa inserida = "+pessoa.getId());
        
    }
    
    @Test
    public void persistirPessoaJuridica() {
        logger.info("Executando persistirJuridica()");
        PessoaJuridica empresa = criarPessoaJuridica();
        em.persist(empresa);
        em.flush();
        assertNotNull(empresa.getId());
        logger.info("id da pessoa juridica inserida = "+empresa.getId());
    }
    
    
    
    @Test
    public void atualizarPessoaFisica(){
        logger.info("Executando atualizarPessoaFisica()");
        PessoaFisica pessoaFisica = em.find(PessoaFisica.class,1L);
        pessoaFisica.setEmail("email@hotmail.com");
        pessoaFisica.setCreditos("420");
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        PessoaFisica pessoaBuscada = em.find(PessoaFisica.class,1L,properties);
        assertEquals("email@hotmail.com", pessoaBuscada.getEmail());
        assertEquals("420",pessoaBuscada.getCreditos());
        
    }
    
    @Test
    public void atualizarPessoaFisicaMerge(){
        logger.info("Executando atualizarPessoaFisicaMerge()");
        PessoaFisica pessoaFisica = em.find(PessoaFisica.class,2L);
        pessoaFisica.setNome("Marcio");
        em.clear();
        em.merge(pessoaFisica);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        PessoaFisica pessoaBuscada = em.find(PessoaFisica.class, 2L,properties);
        assertEquals("Marcio", pessoaBuscada.getNome());
    }
    
    @Test
    public void atualizarPessoaJuridica(){
        logger.info("Executando atualizarPessoaJuridica()");
        PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class,5L);
        pessoaJuridica.setEmail("email2@hotmail.com");
        pessoaJuridica.setCreditos("520");
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        PessoaJuridica pessoaJBuscada = em.find(PessoaJuridica.class,5L,properties);
        assertEquals("email2@hotmail.com", pessoaJBuscada.getEmail());
        assertEquals("520",pessoaJBuscada.getCreditos());
        
    }
    
    @Test
    public void atualizarPessoaJuridicaMerge(){
        logger.info("Executando atualizarPessoaJuridicaMerge()");
        PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class,11L);
        pessoaJuridica.setRazaosocial("Coca Cola Industrias Brasileiras Ltda");
        em.clear();
        em.merge(pessoaJuridica);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        PessoaJuridica pessoaJBuscada = em.find(PessoaJuridica.class, 11L,properties);
        assertEquals("Coca Cola Industrias Brasileiras Ltda", pessoaJBuscada.getRazaosocial());
    
    }
    
    
    
    @Test
    public void deletarPessoaFisica(){
       logger.info("Executando deletarPessoaFisica()");
       PessoaFisica pessoaFisica = em.find(PessoaFisica.class, 4L);
       em.remove(pessoaFisica);
       PessoaFisica pessoabuscada = em.find(PessoaFisica.class, 4L);
       assertNull(pessoabuscada);
    }
    
    @Test
    public void deletarPessoaJuridica(){
        logger.info("Executando deletarPessoaJuridica()");
        PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class, 6L);
        em.remove(pessoaJuridica);
        PessoaJuridica pessoajbuscada = em.find(PessoaJuridica.class, 6L);
        assertNull(pessoajbuscada);
        
    }
    
//    @Test
//    public void persistVeiculo() {
//        logger.info("Executando persistVeiculo()");
//        Motorista motorista = em.find(Motorista.class,8L);
//        Veiculo veiculo = criarVeiculo2();
//        veiculo.setMotorista(motorista);
//        em.persist(veiculo);
//        em.flush();
//        assertNotNull(veiculo.getId());
//        
//    }
    
    
    private PessoaFisica criarPessoaFisica(){
        PessoaFisica pessoaf = new PessoaFisica();
        String telefone = "99998888";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone);
        pessoaf.setNome("Julio");
        pessoaf.setSobrenome("Silva");
        pessoaf.setSenha("123");
        pessoaf.setTelefones(telefones);
        pessoaf.setEmail("piloto@gmail.com");
        pessoaf.setCpf("15648923578");
        pessoaf.setCreditos("500");
        
       
        
        return pessoaf;
    }
    
    private PessoaJuridica criarPessoaJuridica(){
        PessoaJuridica pessoaj = new PessoaJuridica();
        String telefone1 = "999988886";
        String telefone2 = "985663214";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone1);
        telefones.add(telefone2);
        pessoaj.setNome("Coca Cola");
        pessoaj.setRazaosocial("Coca Cola Industrias Ltda");
        pessoaj.setSenha("123");
        pessoaj.setTelefones(telefones);
        pessoaj.setEmail("cocacola@gmail.com");
        pessoaj.setCnpj("17031667000154");
        pessoaj.setCreditos("456");
        
        
        return pessoaj;
    }
    
    private Motorista criarMotorista(){
        Motorista motorista = new Motorista();
        String telefone1 = "999666886";
        String telefone2 = "977773214";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone1);
        telefones.add(telefone2);
        motorista.setNome("Pilotinho");
        motorista.setSobrenome("Lotovisk");
        motorista.setSenha("123");
        motorista.setTelefones(telefones);
        motorista.setCpf("63940255009");
        motorista.setEmail("pilotinho@gmail.com");
        String habilitacaoMoto = "Moto";
        String habilitacaoCarro = "Carro";
        List<String> habilitacoes = new ArrayList();
        habilitacoes.add(habilitacaoMoto);
        habilitacoes.add(habilitacaoCarro);
        motorista.setHabilitacoes(habilitacoes);
        motorista.setReputacao(Reputacao.Excelente);
        
        

        return motorista;
    }
    
    public Veiculo criarVeiculo2(){
        Veiculo veiculo = new Veiculo();
        veiculo.setAnofabricacao("2000");
        veiculo.setCapacidade(6);
        veiculo.setFabricante("Honda");
        veiculo.setImagem(Byte.MIN_VALUE);
        veiculo.setModelo("F156");
        veiculo.setPorte("Grande");
        veiculo.setTipo("Esportivo");
        veiculo.setDescricao("Descrição...");
        List<String> placas = new ArrayList();
        placas.add("AXM52");
        placas.add("V5C0D3");
        veiculo.setPlacas(placas);
        
        
        return veiculo;
    }    
    
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
//        PessoaFisica pessoa = criarPessoaFisica02();
//        aluguel.setPessoa(pessoa);
//        List<Veiculo> veiculos = new ArrayList();
//        Veiculo veiculo01 = criarVeiculo();
//        veiculos.add(veiculo01);
//        aluguel.setVeiculos(veiculos);
//        
//        
//        return aluguel;
//    }
    
    
    private PessoaFisica criarPessoaFisica02() {
        PessoaFisica pessoa01 = new PessoaFisica();
        String telefone = "985447213";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone);
        pessoa01.setNome("Anselmo");
        pessoa01.setSobrenome("Noronha");
        pessoa01.setSenha("456");
        pessoa01.setTelefones(telefones);
        pessoa01.setEmail("whatever@gmail.com");
        pessoa01.setCpf("15648923578");
        pessoa01.setCreditos("200");

        return pessoa01;
    }

    private Veiculo criarVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setAnofabricacao("2015");
        veiculo.setCapacidade(5);
        veiculo.setFabricante("Cintroen");
        veiculo.setImagem(Byte.MIN_VALUE);
        veiculo.setModelo("C4 PALLAS");
        veiculo.setDescricao("Carro Sedan aconchegante ideal para atender a eventos de gala.");
        veiculo.setPorte("Medio");
        veiculo.setTipo("Sedan");
        List<String> placas = new ArrayList();
        placas.add("OPK5477");
        placas.add("OPL2369");
        veiculo.setPlacas(placas);

        return veiculo;
    }
}
