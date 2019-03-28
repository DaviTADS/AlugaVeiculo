
package alugaveiculo.testes;

import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.PessoaJuridica;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    public void atualizarPessoaFisicaMerge(){
        PessoaFisica pessoaFisica = em.find(PessoaFisica.class,2L);
        pessoaFisica.setNome("AAAA");
        em.clear();
        em.merge(pessoaFisica);
        PessoaFisica pessoaBuscada = em.find(PessoaFisica.class, 2L);
        assertEquals("AAAA", pessoaBuscada.getNome());
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
        pessoaf.setCreditos(500);
        
       
        
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
        pessoaj.setRazaosocial("Coca-Cola Industrias Ltda");
        pessoaj.setSenha("123");
        pessoaj.setTelefones(telefones);
        pessoaj.setEmail("cocacola@gmail.com");
        pessoaj.setCnpj("17031667000154");
        pessoaj.setCreditos(456);
        
        
        return pessoaj;
    }

}
