package app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Classe de exemplo que demonstra a utilização da classe Principal para gerenciar informações de funcionários.
 */
public class Program {

    /**
     * O método principal que executa as operações de exemplo com a classe Principal.
     *
     * @param args Os argumentos de linha de comando (não utilizados neste exemplo).
     */
    public static void main(String[] args) {
        // Cria uma instância da classe Principal para gerenciar informações de funcionários.
        Principal principal = new Principal();

        // Adiciona funcionários à lista.
        principal.inserirFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        principal.inserirFuncionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador");
        principal.inserirFuncionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");
        principal.inserirFuncionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor");
        principal.inserirFuncionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista");
        principal.inserirFuncionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador");
        principal.inserirFuncionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador");
        principal.inserirFuncionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente");
        principal.inserirFuncionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista");
        principal.inserirFuncionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");
        System.out.println("3.1 Adicionados os registros");

        // Remove o funcionário com o nome "João" da lista.
        principal.removerFuncionarioPorNome("João");
        System.out.println("3.2 Remover João");

        // Imprime os detalhes de todos os funcionários na lista formatando a data para dd/mm/yyyy e o valor para 1.000,00.
        principal.imprimirFuncionarios();
        System.out.println("3.3 Imprimir todos os registros formatando data para dd/mm/yyyy e valor para 1.000,00");

        // Aumenta os salários de todos os funcionários em 10%.
        principal.aumentarSalarios(BigDecimal.valueOf(0.10));
        System.out.println("3.4 Aumentar salário de todos os funcionários");

        // Agrupa todos os funcionários em um mapa onde a chave é a propriedade Função.
        final Map<String, List<Funcionario>> funcionariosPorFuncao = principal.agruparPorFuncao();
        System.out.println("3.5 Agrupar todos os funcionário em um MAP e a chave será a propriedade Função");

        // Imprime os funcionários agrupados por função.
        principal.imprimirFuncionariosAgrupadosPorFuncao(funcionariosPorFuncao);
        System.out.println("3.6 Imprimir todos os funcionários agrupados por Função");

        // Imprime os aniversariantes que fazem aniversário nos meses 10 e 12.
        principal.imprimirAniversariantes(new int[]{10, 12});
        System.out.println("3.8 Imprimir todos os aniversariantes que fazem aniversários no mês 10 e 12");

        // Imprime o funcionário mais velho, incluindo nome e idade.
        principal.imprimirFuncionarioMaiorIdade();
        System.out.println("3.9 Imprimir o funcionário mais velho nome e idade");

        // Imprime os funcionários por ordem alfabética.
        principal.imprimirFuncionariosOrdenadoPorNome();
        System.out.println("3.10 Imprimir todos os funcionários por ordem alfabética");

        // Imprime o total dos salários dos funcionários.
        principal.imprimirTotalSalarios();
        System.out.println("3.11 Imprimir o total dos salários dos funcionários");

        // Imprime quantos salários mínimos cada funcionário ganha, considerando um salário mínimo de R$1.212,00.
        principal.imprimirSalariosMinimos(new BigDecimal("1212.00"));
        System.out.println("3.12 Imprimir quantos salários mínimos ganha cada funcionário considerando um salário mínimo de R$1.212,00");

        // Buscar funcionário informando um nome específico, por ora a busca é exata
        principal.buscarFuncionariosPorNome("Maria");
    }
}