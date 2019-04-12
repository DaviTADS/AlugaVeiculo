/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes.Jpql;

import alugaveiculo.testes.GenericTest;
import com.mycompany.alugaveiculo.Aluguel;
import com.mycompany.alugaveiculo.PessoaFisica;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class TesteAluguelJPQL extends GenericTest {
    
    
    @Test
    public void AluguelporPessoaSQL() {
        logger.info("Executando AluguelporPessoaSQL()");
        TypedQuery<Aluguel> query = em.createNamedQuery("Aluguel.PorNome", Aluguel.class);
        List<Aluguel> alugueis = query.getResultList();
        assertEquals(3, alugueis.size());
    }    
}