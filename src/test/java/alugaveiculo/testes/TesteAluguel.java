package alugaveiculo.testes;

import static alugaveiculo.testes.GenericTest.logger;
import com.mycompany.alugaveiculo.Aluguel;
import com.mycompany.alugaveiculo.Motorista;
import com.mycompany.alugaveiculo.PessoaFisica;
import com.mycompany.alugaveiculo.Reputacao;
import com.mycompany.alugaveiculo.Veiculo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class TesteAluguel extends GenericTest {
    
    @Test
    public void persistirAluguel(){
        logger.info("Executando persistirAluguel()");
        Aluguel aluguel = criarAluguel();
        em.persist(aluguel);
        em.flush();
        assertNotNull(aluguel.getId());
        logger.info("id do aluguel inserido = "+aluguel.getId());
        
    }
    
    @Test
    public void atualizarAluguel(){
         logger.info("Executando atualizarAluguel()");
         Aluguel aluguel = em.find(Aluguel.class,1L);
         aluguel.setPreco("R$2500");
         em.flush();
         Aluguel aluguelalterado = em.find(Aluguel.class,1L);
         assertEquals("R$2500",aluguel.getPreco());
    }
    
    @Test
    public void atualizarAluguelMerge(){
         logger.info("Executando atualizarMergeAluguel()");
         Aluguel aluguel = em.find(Aluguel.class,3L);
         aluguel.setPreco("R$3000");
         em.clear();
         em.merge(aluguel);
         Aluguel aluguelalterado = em.find(Aluguel.class,3L);
         assertEquals("R$3000",aluguel.getPreco());
    }
    
    @Test
    public void deletarAluguel(){
        logger.info("Executando atualizarMergeAluguel()");
        Aluguel aluguel = em.find(Aluguel.class, 2L);
        em.remove(aluguel);
        Aluguel aluguelexcluido = em.find(Aluguel.class, 2L);
        assertNull(aluguelexcluido);
        
    }
    
    
    private Aluguel criarAluguel(){
        Aluguel aluguel = new Aluguel();
        Calendar ci = Calendar.getInstance();
        ci.set(Calendar.YEAR, 2019);
        ci.set(Calendar.MONTH, Calendar.APRIL);
        ci.set(Calendar.DAY_OF_MONTH, 01);
        aluguel.setDatainicio(ci.getTime());
        Calendar cf = Calendar.getInstance();
        cf.set(Calendar.YEAR, 2019);
        cf.set(Calendar.MONTH, Calendar.APRIL);
        cf.set(Calendar.DAY_OF_MONTH, 02);
        aluguel.setDatafinal(cf.getTime());
        aluguel.setPreco("R$1500");
        PessoaFisica pessoa = criarPessoaFisica();
        aluguel.setPessoa(pessoa);
        List<Veiculo> veiculos = new ArrayList();
        Veiculo veiculo01 = criarVeiculo();
        veiculos.add(veiculo01);
        aluguel.setVeiculos(veiculos);
        
        
        return aluguel;
    }
    
    
    
    private PessoaFisica criarPessoaFisica() {
        PessoaFisica pessoa01 = new PessoaFisica();
        String telefone = "985447213";
        Collection<String> telefones = new ArrayList();
        telefones.add(telefone);
        pessoa01.setNome("Anselmo");
        pessoa01.setSobrenome("Noronha");
        pessoa01.setSenha("456");
        pessoa01.setTelefones(telefones);
        pessoa01.setEmail("whatever@gmail.com");
        pessoa01.setCpf("15648923578");
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
        veiculo.setTipo("Sedan");
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
