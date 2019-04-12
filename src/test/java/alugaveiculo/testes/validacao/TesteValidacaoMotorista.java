
package alugaveiculo.testes.validacao;

import alugaveiculo.testes.DbUnitUtil;
import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.Reputacao;
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
public class TesteValidacaoMotorista {
    
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
    public void CriarMotoristaInvalido() {
    
       Motorista motorista = new Motorista();
        Collection<String> telefones = new ArrayList();
        try{
        String telefone = "999956688";
        telefones.add(telefone);
        motorista.setNome("ga"); // nome com menos de 3 caracteres 
        motorista.setSobrenome("BORBA"); // sobrenome sem letras minusculas
        motorista.setSenha("15"); //senha incorreta
        motorista.setTelefones(telefones);
        motorista.setEmail("pilotinhopontocom");// email incorreto 
        motorista.setCpf("8733308001268"); // cpf com dois digito a mais
      //motorista.setReputacao(Reputacao.Boa); -> reputação nula
        String habilitacaoMoto = "Moto";
        String habilitacaoCarro = "Carro";
        List<String> habilitacoes = new ArrayList();
        habilitacoes.add(habilitacaoMoto);
        habilitacoes.add(habilitacaoCarro);
        motorista.setHabilitacoes(habilitacoes);
        em.persist(motorista);
        em.flush();
        }
        catch(ConstraintViolationException ex){
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.alugaveiculo.Motorista.senha: Senha incorreta, a senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número."),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.senha: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.nome: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.nome: tamanho deve estar entre 3 e 30"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.nome: nome inválido, nome deve contar pelo menos uma letra maiúscula e o resto minúscula"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.sobrenome: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.sobrenome: tamanho deve estar entre 3 e 30"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.sobrenome: Deve possuir uma única letra maiúscula, seguida por letras minúsculas."),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.email: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.email: Não é um endereço de e-mail"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.reputacao: não pode ser nulo"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.cpf: CPF inválido"),
                                startsWith("class com.mycompany.alugaveiculo.Motorista.telefones: tamanho deve estar entre 0 e 4")
                                
                        )
                );
            }

            assertEquals(7, constraintViolations.size());
            assertNull(motorista.getId());
            throw ex;
        
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarMotoristaInvalido() {
        TypedQuery<Motorista> query = em.createQuery("SELECT m FROM Motorista m WHERE m.cpf like :cpf", Motorista.class);
        query.setParameter("cpf", "082.009.180-41");
        Motorista pessoaf = query.getSingleResult();
        pessoaf.setSenha("jauio");

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
