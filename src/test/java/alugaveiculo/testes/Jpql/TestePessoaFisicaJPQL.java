/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes.Jpql;

import alugaveiculo.testes.GenericTest;
import com.mycompany.alugaveiculo.Pessoa;
import com.mycompany.alugaveiculo.PessoaFisica;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class TestePessoaFisicaJPQL extends GenericTest {
    
    
   @Test
    public void consultaPessoaFisicaPorNome() {
        logger.info("Executando consultaPessoaFisicaPorNome()");
        TypedQuery<PessoaFisica> query = em.createQuery(
            "SELECT f FROM PessoaFisica f WHERE f.nome LIKE :nome",
            PessoaFisica.class);
        query.setParameter("nome", "C%");
        List<PessoaFisica> pessoasf = query.getResultList();

        for (PessoaFisica pessoaf : pessoasf) {
            assertTrue(pessoaf.getNome().startsWith("Celso"));
        }

        assertEquals(1, pessoasf.size());
    }  
    
    @Test
    public void PessoaFisicaPorNomeNamedQuery() {
        logger.info("Executando PessoaFisicaPorNomeNamedQuery()");
        TypedQuery<PessoaFisica> query = em.createNamedQuery("PessoaF.PorNome", PessoaFisica.class);
        query.setParameter("nome", "Ul%");
        List<PessoaFisica> pessoasf = query.getResultList();
        
        for (PessoaFisica pessoaf : pessoasf) {
            assertTrue(pessoaf.getNome().startsWith("Ulisses"));
        }

        assertEquals(1, pessoasf.size());
    }
    
    @Test
    public void PessoaFisicaPorCredito(){
        logger.info("Executando PessoaFisicaPorNomeNamedQuery()");
        TypedQuery<PessoaFisica> query = em.createQuery(
                "SELECT f FROM PessoaFisica f WHERE f.creditos LIKE :creditos",
                PessoaFisica.class);
        query.setParameter("creditos", "502");
        List <PessoaFisica> pessoasf = query.getResultList();
        
        for (PessoaFisica pessoaf : pessoasf) {
            assertTrue(pessoaf.getCreditos().startsWith("502"));
        }

        assertEquals(3, pessoasf.size());
    }
    
    
    
}
