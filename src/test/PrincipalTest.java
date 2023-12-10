package test;

import app.Principal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        Boolean retorno = principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");


        boolean removido = principal.removerFuncionarioPorNome("Maria");

        Assertions.assertTrue(removido);
    }

    @Test
    public void removerFuncionarioNaoEncontrado() {
        Principal principal = new Principal();
        // Insere o funcionário na lista
        Boolean retorno = principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");

        boolean removido = principal.removerFuncionarioPorNome("João");

        Assertions.assertFalse(removido);
    }
    @Test
    public void removerFuncionarioNomeVazio() {
        Principal principal = new Principal();

        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> principal.removerFuncionarioPorNome(""));
    }
}
