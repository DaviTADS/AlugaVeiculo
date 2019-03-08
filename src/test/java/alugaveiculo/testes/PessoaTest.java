
package alugaveiculo.testes;

import com.mycompany.alugaveiculo.Pessoa;
import com.mycompany.alugaveiculo.PessoaFisica;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class PessoaTest extends GenericTest {
    
    @Test
    public void persistirPessoa() {
        logger.info("Executando persistirPaciente()");
        PessoaFisica pessoaf = (PessoaFisica) criarPessoaFisica();
        em.persist(pessoaf);
        em.flush();
        assertNotNull(pessoaf.getId());
        
    }
    
    
    public Pessoa criarPessoaFisica(){
        PessoaFisica pessoaf = new PessoaFisica();
        pessoaf.setNome("Julio");
        pessoaf.setSobrenome("Oliveira");
        pessoaf.setLogin("umai");
        pessoaf.setSenha("senha1");
        pessoaf.setEmail("emaildoido@gmail.com");
        pessoaf.setCpf("15648923578");
        pessoaf.setCreditos("456");
        return pessoaf;
    }
    
    

}
