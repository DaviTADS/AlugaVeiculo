/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes.validacao;

import alugaveiculo.testes.DbUnitUtil;
import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.PessoaJuridica;
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
public class TesteValidacaoPessoaJuridica {
    
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
    public void CriarPessoaJuridicaInvalida() {
    
        PessoaJuridica pessoaj = new PessoaJuridica();
        Collection<String> telefones = new ArrayList();
        try{
        String telefone = "999988881";
        String telefone2 = "999988876";
        String telefone3 = "999988869";
        String telefone4 = "999988854";
        String telefone5 = "999988842";
        telefones.add(telefone);
        telefones.add(telefone2);
        telefones.add(telefone3);
        telefones.add(telefone4);
        telefones.add(telefone5);// mais de 4 telefones
        pessoaj.setNome("COKE"); // nome com apenas letras maiúsculas 
        pessoaj.setRazaosocial(""); // razaosocial em branco
        pessoaj.setSenha("wrongpassword"); //senha incorreta
        pessoaj.setTelefones(telefones);
        pessoaj.setEmail("pessoajuridicacom");// email incorreto 
        pessoaj.setCnpj("468109820001884"); // cnpj com um digito a mais
        pessoaj.setCreditos(""); // creditos em branco
        em.persist(pessoaj);
        em.flush();
        }
        catch(ConstraintViolationException ex){
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.senha: Senha incorreta, a senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número."),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.senha: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.nome: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.nome: tamanho deve estar entre 3 e 30"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.nome: nome inválido, nome deve contar pelo menos uma letra maiúscula e o resto minúscula"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.razaosocial: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.razaosocial: tamanho deve estar entre 3 e 30"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.razaosocial: Deve possuir uma única letra maiúscula, seguida por letras minúsculas."),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.email: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.email: Não é um endereço de e-mail"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.creditos: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.cnpj: CNPJ inválido"),
                                startsWith("class com.mycompany.alugaveiculo.PessoaJuridica.telefones: tamanho deve estar entre 0 e 4")
                                
                        )
                );
            }

            assertEquals(7, constraintViolations.size());
            assertNull(pessoaj.getId());
            throw ex;
        
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarPessoaJuridicaInvalido() {
        TypedQuery<PessoaJuridica> query = em.createQuery("SELECT j FROM PessoaJuridica j WHERE j.cnpj like :cnpj", PessoaJuridica.class);
        query.setParameter("cnpj", "76.339.105/0001-31");
        PessoaJuridica pessoaj = query.getSingleResult();
        pessoaj.setCnpj("76.339.105/0001-310");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("CNPJ inválido", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
    
}
