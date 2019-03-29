
package alugaveiculo.testes;

import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.PessoaJuridica;
import com.mycompany.alugaveiculo.Reputacao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public void persistirMotorista() {
        logger.info("Executando persistirMotorista()");
        Motorista motorista = criarMotorista();
        em.persist(motorista);
        em.flush();
        assertNotNull(motorista.getId());
        logger.info("id do motorista inserido = "+motorista.getId());
    }
    
    @Test
    public void atualizarPessoaFisica(){
        logger.info("Executando atualizarPessoaFisica()");
        PessoaFisica pessoaFisica = em.find(PessoaFisica.class,1L);
        pessoaFisica.setEmail("email@hotmail.com");
        pessoaFisica.setCreditos("420");
        em.flush();
        PessoaFisica pessoaBuscada = em.find(PessoaFisica.class,1L);
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
        PessoaFisica pessoaBuscada = em.find(PessoaFisica.class, 2L);
        assertEquals("Marcio", pessoaBuscada.getNome());
    }
    
    @Test
    public void atualizarPessoaJuridica(){
        logger.info("Executando atualizarPessoaJuridica()");
        PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class,5L);
        pessoaJuridica.setEmail("email2@hotmail.com");
        pessoaJuridica.setCreditos("520");
        em.flush();
        PessoaJuridica pessoaJBuscada = em.find(PessoaJuridica.class,5L);
        assertEquals("email2@hotmail.com", pessoaJBuscada.getEmail());
        assertEquals("520",pessoaJBuscada.getCreditos());
        
    }
    
    @Test
    public void atualizarPessoaJuridicaMerge(){
        logger.info("Executando atualizarPessoaJuridicaMerge()");
        PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class,9L);
        pessoaJuridica.setRazaosocial("Coca Cola Industrias Brasileiras Ltda");
        em.clear();
        em.merge(pessoaJuridica);
        PessoaJuridica pessoaJBuscada = em.find(PessoaJuridica.class, 9L);
        assertEquals("Coca Cola Industrias Brasileiras Ltda", pessoaJBuscada.getRazaosocial());
    
    }
    
//    @Test
//    public void atualizarMotorista(){
//        logger.info("Executando atualizarMotorista()");
//        Motorista motorista = em.find(Motorista.class,10L);
//        motorista.setReputacao(Reputacao.Ruim);
//        motorista.setSenha("555");
//        em.flush();
//        Motorista motoristaBuscado = em.find(Motorista.class,10L);
//        assertEquals("555", motoristaBuscado.getSenha());     
//    }
    
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

}
