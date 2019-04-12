/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes.validacao;

import alugaveiculo.testes.DbUnitUtil;
import com.mycompany.alugaveiculo.PessoaFisica;
import java.util.ArrayList;
import java.util.Collection;
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
public class TesteValidacaoPessoaFisica {
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
    public void CriarPessoaFisicaInvalida() {
    
        PessoaFisica pessoaf = new PessoaFisica();
        Collection<String> telefones = new ArrayList();
        try{
        String telefone = "99998888";
        telefones.add(telefone);
        pessoaf.setNome("Jp"); // nome com menos de 3 caracteres 
        pessoaf.setSobrenome("silva"); // sobrenome sem letra maiúscula
        pessoaf.setSenha("hue"); //senha incorreta
        pessoaf.setTelefones(telefones);
        pessoaf.setEmail("pessoafisicacom");// email incorreto 
        pessoaf.setCpf("763330800126"); // cpf com um digito a mais
        pessoaf.setCreditos(""); // creditos em branco
        em.persist(pessoaf);
        em.flush();
        
        }
        catch(ConstraintViolationException ex){
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.senha: Senha incorreta, a senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número."),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.senha: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.nome: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.nome: tamanho deve estar entre 3 e 30"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.nome: nome inválido, nome deve contar pelo menos uma letra maiúscula e o resto minúscula"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.sobrenome: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.sobrenome: tamanho deve estar entre 3 e 30"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.sobrenome: Deve possuir uma única letra maiúscula, seguida por letras minúsculas."),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.email: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.email: Não é um endereço de e-mail"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.creditos: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaFisica.cpf: CPF inválido")
                                
                        )
                );
            }

            assertEquals(6, constraintViolations.size());
            assertNull(pessoaf.getId());
            throw ex;
        
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarPessoaFisicaInvalido() {
        TypedQuery<PessoaFisica> query = em.createQuery("SELECT f FROM PessoaFisica f WHERE f.cpf like :cpf", PessoaFisica.class);
        query.setParameter("cpf", "515.153.640-03");
        PessoaFisica pessoaf = query.getSingleResult();
        pessoaf.setSenha("testando1234");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("Senha incorreta, a senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número.", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
}
