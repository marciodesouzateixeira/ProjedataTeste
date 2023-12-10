package test;

import app.Funcionario;
import app.Principal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Tenta inserir um funcionário com Nome vazio na lista, deve retornar um exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> principal.inserirFuncionario("", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),"Nome deve ser preenchido.");
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

        Assertions.assertThrows(IllegalArgumentException.class, () -> principal.removerFuncionarioPorNome(""));
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

        Assertions.assertThrows(IllegalArgumentException.class, () -> principal.aumentarSalarios(percentual));
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

        funcionariosPorFuncao.put("Programador", Arrays.asList(
                new Funcionario("Fulano de Tal", LocalDate.of(1980, 1, 1), BigDecimal.valueOf(1000.00), "Programador"),
                new Funcionario("Beltrano de Tal", LocalDate.of(1981, 2, 2), BigDecimal.valueOf(2000.00), "Programador")
        ));
        funcionariosPorFuncao.put("Analista", Arrays.asList(
                new Funcionario("Ciclano de Tal", LocalDate.of(1982, 3, 3), BigDecimal.valueOf(3000.00), "Analista")
        ));

        boolean impressoComSucesso = principal.imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao);

        Assertions.assertTrue(impressoComSucesso);
    }

    @Test
    public void imprimirFuncionariosAgrupadosPorFuncaoComErro() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = null;
        Principal principal = new Principal();

        Assertions.assertThrows(NullPointerException.class, () -> principal.imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao));
    }
}
