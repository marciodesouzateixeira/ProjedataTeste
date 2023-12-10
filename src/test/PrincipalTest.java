package test;

import app.Funcionario;
import app.Principal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class PrincipalTest {
    @Test
    public void testInserirFuncionario() {
        Principal principal = new Principal();

        // Insere o funcionário na lista
        Boolean retorno = principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        // Verifica se o funcionário foi inserido corretamente
        Assertions.assertTrue(retorno);
    }

    @Test
    public void testInserirFuncionarioNulo() {
        Principal principal = new Principal();
        boolean retorno = principal.inserirFuncionario("", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        // Tenta inserir um funcionário com Nome vazio na lista, deve retornar um exception
        Assertions.assertFalse(retorno);
    }

    @Test
    public void removerFuncionarioComSucesso() {
        Principal principal = new Principal();
        // Insere o funcionário na lista
        principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        boolean removido = principal.removerFuncionarioPorNome("Maria");

        Assertions.assertTrue(removido);
    }

    @Test
    public void removerFuncionarioNaoEncontrado() {
        Principal principal = new Principal();
        // Insere o funcionário na lista
        principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        boolean removido = principal.removerFuncionarioPorNome("João");

        Assertions.assertFalse(removido);
    }
    @Test
    public void removerFuncionarioNomeVazio() {
        Principal principal = new Principal();
        boolean retorno = principal.removerFuncionarioPorNome("");

        Assertions.assertFalse(retorno);
    }

    @Test
    public void aumentarSalariosComSucesso() {
        BigDecimal percentual = BigDecimal.valueOf(10);
        Principal principal = new Principal();

        boolean aumentoRealizado = principal.aumentarSalarios(percentual);

        Assertions.assertTrue(aumentoRealizado);
    }

    @Test
    public void aumentarSalariosComErro() {
        BigDecimal percentual = BigDecimal.valueOf(-10);
        Principal principal = new Principal();
        boolean retorno = principal.aumentarSalarios(percentual);

        Assertions.assertFalse(retorno);
    }

    @Test
    public void agruparPorFuncaoComSucesso() {
        Principal principal = new Principal();

        principal.inserirFuncionario("Maria", LocalDate.of(1980, 1, 18), new BigDecimal("2009.44"), "Operador");
        principal.inserirFuncionario("Antonio", LocalDate.of(1990, 2, 19), new BigDecimal("7000.00"), "Programador");
        principal.inserirFuncionario("Fabiana", LocalDate.of(2000, 3, 20), new BigDecimal("8500.50"), "Programador");
        principal.inserirFuncionario("Carlos", LocalDate.of(1970, 4, 21), new BigDecimal("12000.00"), "Gerente");

        Map<String, List<Funcionario>> funcionariosPorFuncao = principal.agruparPorFuncao();

        Assertions.assertEquals(3, funcionariosPorFuncao.size());

        List<Funcionario> programadores = funcionariosPorFuncao.get("Programador");
        Assertions.assertEquals(2, programadores.size());
        Assertions.assertEquals("Antonio", programadores.get(0).getNome());
        Assertions.assertEquals("Fabiana", programadores.get(1).getNome());

        List<Funcionario> analistas = funcionariosPorFuncao.get("Operador");
        Assertions.assertEquals(1, analistas.size());
        Assertions.assertEquals("Maria", analistas.getFirst().getNome());

        List<Funcionario> gerentes = funcionariosPorFuncao.get("Gerente");
        Assertions.assertEquals(1, gerentes.size());
        Assertions.assertEquals("Carlos", gerentes.getFirst().getNome());
    }

    @Test
    public void imprimirFuncionariosAgrupadosPorFuncaoComSucesso() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        Principal principal = new Principal();

        funcionariosPorFuncao.put("Programador", List.of(
                new Funcionario("Fulano de Tal", LocalDate.of(1980, 1, 1), BigDecimal.valueOf(1000.00), "Programador"),
                new Funcionario("Beltrano de Tal", LocalDate.of(1981, 2, 2), BigDecimal.valueOf(2000.00), "Programador")
        ));
        funcionariosPorFuncao.put("Analista", List.of(
                new Funcionario("Ciclano de Tal", LocalDate.of(1982, 3, 3), BigDecimal.valueOf(3000.00), "Analista")
        ));

        boolean impressoComSucesso = principal.imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao);

        Assertions.assertTrue(impressoComSucesso);
    }

    @Test
    public void imprimirFuncionariosAgrupadosPorFuncaoComErro() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = null;
        Principal principal = new Principal();

        Assertions.assertFalse(() -> principal.imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao));
    }

    @Test
    public void imprimirAniversariantesComSucesso() {
        List<Funcionario> funcionarios = new ArrayList<>();
         Principal principal = new Principal();
        funcionarios.add(new Funcionario("Fulano de Tal", LocalDate.of(1980, 1, 1), BigDecimal.valueOf(1000.00), "Programador"));
        funcionarios.add(new Funcionario("Beltrano de Tal", LocalDate.of(1981, 2, 2), BigDecimal.valueOf(2000.00), "Analista"));
        //funcionarios.add(new Funcionario("Ciclano de Tal", LocalDate.of(1982, 3, 3), BigDecimal.valueOf(3000.00), "Gerente"));

        int[] meses = {1, 2, 3};

        boolean impressoComSucesso = principal.imprimirAniversariantes(meses);
        Assertions.assertTrue(impressoComSucesso);

        List<Funcionario> aniversariantesEsperados = new ArrayList<>();
        aniversariantesEsperados.add(funcionarios.get(0));
        aniversariantesEsperados.add(funcionarios.get(1));

         Assertions.assertEquals(aniversariantesEsperados, funcionarios.stream()
                .filter(funcionario -> Arrays.stream(meses).anyMatch(mes -> mes == funcionario.getDataNascimento().getMonthValue()))
                .toList());
    }

    @Test
    public void imprimirAniversariantesComListaDeFuncionariosNula() {
        Principal principal = new Principal();
        boolean retorno = false;
        retorno = principal.imprimirAniversariantes(null);
        Assertions.assertFalse(retorno);
    }

    @Test
    public void imprimirAniversariantesComValoresInvalidosNoArrayDeMeses() {
        Principal principal = new Principal();
        boolean retorno = false;
        retorno = principal.imprimirAniversariantes(new int[]{13, 4, 2});
        Assertions.assertFalse(retorno);
    }

    @Test
    void testImprimirFuncionarioMaiorIdadeComFuncionarios() {
        Principal principal = new Principal();

        // Adiciona funcionários à lista
        principal.inserirFuncionario("João", LocalDate.of(1990, 1, 15), BigDecimal.valueOf(5000.00), "Desenvolvedor");
        principal.inserirFuncionario("Maria", LocalDate.of(1985, 5, 20), BigDecimal.valueOf(6000.00), "Analista");

        // Testa o método imprimirFuncionarioMaiorIdade
        Funcionario maisVelho = principal.imprimirFuncionarioMaiorIdade();
        Assertions.assertNotNull(maisVelho);
        Assertions.assertEquals("Maria", maisVelho.getNome());
    }

    @Test
    void testImprimirFuncionarioMaiorIdadeSemFuncionarios() {
        Principal principal = new Principal();

        // Testa o método imprimirFuncionarioMaiorIdade quando não há funcionários
        Funcionario maisVelho = principal.imprimirFuncionarioMaiorIdade();
        Assertions.assertNotNull(maisVelho);
        Assertions.assertNull(maisVelho.getNome()); // Supondo que o nome do funcionário maisVelho seja inicializado como vazio
    }

    @Test
    void testImprimirFuncionariosOrdenadoPorNomeListaNaoVazia() {
        Principal principal = new Principal();
        principal.inserirFuncionario("Alice", LocalDate.of(1990, 5, 15), BigDecimal.valueOf(5000), "Desenvolvedor");
        principal.inserirFuncionario("Bob", LocalDate.of(1985, 10, 20), BigDecimal.valueOf(6000), "Analista");
        Assertions.assertTrue(principal.imprimirFuncionariosOrdenadoPorNome());
    }

    @Test
    void testImprimirFuncionariosOrdenadoPorNomeListaVazia() {
        Principal principal = new Principal();
        Assertions.assertFalse(principal.imprimirFuncionariosOrdenadoPorNome());
    }

    @Test
    void testImprimirTotalSalariosListaNaoVazia() {
        Principal principal = new Principal();
        principal.inserirFuncionario("Alice", LocalDate.of(1990, 5, 15), BigDecimal.valueOf(5000), "Desenvolvedor");
        principal.inserirFuncionario("Bob", LocalDate.of(1985, 10, 20), BigDecimal.valueOf(6000), "Analista");
        Assertions.assertTrue(principal.imprimirTotalSalarios());
    }

    @Test
    void testImprimirTotalSalariosListaVazia() {
        Principal principal = new Principal();
        Assertions.assertFalse(principal.imprimirTotalSalarios());
    }

    @Test
    void testImprimirSalariosMinimosListaNaoVazia() {
        Principal principal = new Principal();
        principal.inserirFuncionario("Alice", LocalDate.of(1990, 5, 15), BigDecimal.valueOf(5000), "Desenvolvedor");
        principal.inserirFuncionario("Bob", LocalDate.of(1985, 10, 20), BigDecimal.valueOf(6000), "Analista");
        Assertions.assertTrue(principal.imprimirSalariosMinimos(BigDecimal.valueOf(1000)));
    }

    @Test
    void testImprimirSalariosMinimosListaVazia() {
        Principal principal = new Principal();
        Assertions.assertFalse(principal.imprimirSalariosMinimos(BigDecimal.valueOf(1000)));
    }

    @Test
    void testImprimirSalariosMinimosSalarioMinimoInvalido() {
        Principal principal = new Principal();
        Assertions.assertFalse(principal.imprimirSalariosMinimos(BigDecimal.valueOf(0)));
        Assertions.assertFalse(principal.imprimirSalariosMinimos(null));
    }
}
