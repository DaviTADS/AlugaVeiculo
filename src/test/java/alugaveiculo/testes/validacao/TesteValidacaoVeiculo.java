/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes.validacao;

import alugaveiculo.testes.DbUnitUtil;
import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.PessoaJuridica;
import com.mycompany.alugaveiculo.Reputacao;
import com.mycompany.alugaveiculo.Veiculo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class TesteValidacaoVeiculo {
    
    
    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.OFF);
        emf = Persistence.createEntityManagerFactory("alugaveiculo");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());

            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
            em = null;
            et = null;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void CriarVeiculoInvalido() {
    
        Veiculo veiculo = new Veiculo(); 
        List<String> placas = new ArrayList();
        try{
        veiculo.setAnofabricacao("20000"); //ano de fabricação inválido
        veiculo.setCapacidade(6);
        veiculo.setFabricante("Tesla"); // fabricante não cadastrada ou inexistente
        veiculo.setImagem(Byte.MIN_VALUE);
        veiculo.setModelo("Model X");
        veiculo.setPorte("Grande");
        veiculo.setTipo("Elétrico"); // tipo não cadastrado ou inexistente
        veiculo.setDescricao("Descrição legal para um carro");
        placas.add("AXM52");
        placas.add("V5C0D3");
        veiculo.setPlacas(placas);
        em.persist(veiculo);
        em.flush();
        }
        catch(ConstraintViolationException ex){
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.capacidade: não pode ser nulo."),
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.anofabricacao: tamanho deve estar entre 4 e 4"),
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.modelo: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.porte: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.descricao: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.tipo: Tipo desconhecido ou ainda não cadastrado no sistema, favor inserir outro tipo."),
                                startsWith("class com.mycompany.alugaveiculo.Veiculo.fabricante: Fabricante desconhecida ou ainda não cadastrada no sistema, favor inserir outra fabricante.")
                                
                                
                        )
                );
            }

            assertEquals(3, constraintViolations.size());
            assertNull(veiculo.getId());
            throw ex;
        
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarVeiculoInvalido() {
        TypedQuery<Veiculo> query = em.createQuery("SELECT v FROM Veiculo v WHERE v.modelo like :modelo", Veiculo.class);
        query.setParameter("modelo", "Fiat Linux");
        Veiculo veiculo = query.getSingleResult();
        veiculo.setPorte("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Não pode estar em branco", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
    
    
}
