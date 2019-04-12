/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alugaveiculo.testes.validacao;

import alugaveiculo.testes.DbUnitUtil;
import com.mycompany.alugaveiculo.Aluguel;
import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.Reputacao;
import com.mycompany.alugaveiculo.Veiculo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
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
public class TesteValidacaoAluguel {
    
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
    public void CriarAluguelInvalido() {
    
       Aluguel aluguel = new Aluguel();
       Collection<String> telefones = new ArrayList();
       Calendar ci = new GregorianCalendar();
       Calendar cf = new GregorianCalendar();
        try{
             ci.set(2019, Calendar.MAY, 01);
             aluguel.setDatainicio(ci.getTime());
             cf.set(2019, Calendar.JUNE, 01);
             aluguel.setDatafinal(ci.getTime());
             aluguel.setPreco("");                      // preço em branco
             //PessoaFisica pessoa = criarPessoaFisica();
             //aluguel.setPessoa(pessoa);               // aluguel sem pessoa associada
             List<Veiculo> veiculos = new ArrayList();
             Veiculo veiculo01 = criarVeiculo();
             veiculos.add(veiculo01);
             //aluguel.setVeiculos(veiculos);           //Aluguel sem veiculo associado
             
        
        em.persist(aluguel);
        em.flush();
        }
        catch(ConstraintViolationException ex){
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.alugaveiculo.Aluguel.preco: Não pode estar em branco"),
                                startsWith("class com.mycompany.alugaveiculo.Aluguel.preco: tamanho deve estar entre 5 e 7"),
                                startsWith("class com.mycompany.alugaveiculo.Aluguel.pessoa: não pode ser nulo"),
                                startsWith("class com.mycompany.alugaveiculo.Aluguel.veiculos: não pode ser nulo")
                              
                                
                        )
                );
            }

            assertEquals(4, constraintViolations.size());
            assertNull(aluguel.getId());
            throw ex;
        
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarAluguelInvalido() {
        TypedQuery<Aluguel> query = em.createQuery("SELECT a FROM Aluguel a WHERE a.preco like :preco", Aluguel.class);
        query.setParameter("preco", "R$2600");
        Aluguel pessoaf = query.getSingleResult();
        pessoaf.setPreco("R$256653"); 

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {           
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("tamanho deve estar entre 5 e 7", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
    
    
    
    
    private PessoaFisica criarPessoaFisica() {
        PessoaFisica pessoa01 = new PessoaFisica();
        String telefone = "985447213";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone);
        pessoa01.setNome("Anselmo");
        pessoa01.setSobrenome("Noronha");
        pessoa01.setSenha("Ab8.tryo");
        pessoa01.setTelefones(telefones);
        pessoa01.setEmail("whatever@gmail.com");
        pessoa01.setCpf("938.102.030-25");
        pessoa01.setCreditos("200");

        return pessoa01;
    }

    private Veiculo criarVeiculo() {
        Veiculo veiculo = new Veiculo();
        Motorista motorista = criarMotorista();
        veiculo.setMotorista(motorista);
        veiculo.setAnofabricacao("2015");
        veiculo.setCapacidade(5);
        veiculo.setFabricante("Cintroen");
        veiculo.setImagem(Byte.MIN_VALUE);
        veiculo.setModelo("C4 PALLAS");
        veiculo.setDescricao("Carro Sedan aconchegante ideal para atender a eventos de gala.");
        veiculo.setPorte("Medio");
        veiculo.setTipo("Sedã");
        List<String> placas = new ArrayList();
        placas.add("OPK5477");
        placas.add("OPL2369");
        veiculo.setPlacas(placas);

        return veiculo;
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
        motorista.setSenha("7Rt,trea");
        motorista.setTelefones(telefones);
        motorista.setCpf("326.644.710-74");
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
