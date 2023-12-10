package app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Classe que representa a principal funcionalidade do sistema relacionada aos funcionários.
 */
public class Principal {
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private final DecimalFormat decimal = new DecimalFormat("#,##0.00");

    /**
     * Insere funcionários na lista, validando os dados antes de adicioná-lo à lista.     *
     * @param nome Nome do funcionário.
     * @param dataNascimento Data de nascimento do funcionário.
     * @param salario Salário do funcionário.
     * @param funcao Função do funcionário.
     * @throws IllegalArgumentException Se algum dado do funcionário for inválido.
     */
    public boolean inserirFuncionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        if (nome.trim().isEmpty()) {
            System.out.println("Nome deve ser preenchido.");
            throw new IllegalArgumentException("Nome deve ser preenchido.");
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            System.out.println("Data de nascimento deve estar no passado.");
            throw new IllegalArgumentException("Data de nascimento deve estar no passado.");
        }
        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Salário deve ser um valor positivo.");
            throw new IllegalArgumentException("Salário deve ser um valor positivo.");
        }
        if (funcao.trim().isEmpty()) {
            System.out.println("Função deve ser preenchida.");
            throw new IllegalArgumentException("Função deve ser preenchida.");
        }
        return funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
    }

    /**
     * Remove um funcionário da lista com base no nome fornecido.
     *
     * @param nome O nome do funcionário a ser removido.
     * @throws IllegalArgumentException Se o nome fornecido for nulo ou vazio.
     * @throws NoSuchElementException   Se o funcionário com o nome fornecido não for encontrado.
     */
    public boolean removerFuncionarioPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido. Forneça um nome válido para remover um funcionário.");
        }
        return funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Imprime os detalhes dos funcionários na lista.
     */
    public void imprimirFuncionarios() {
        Imprimir(funcionarios);
    }

    /**
     * Método auxiliar para imprimir os detalhes dos funcionários em uma lista específica.
     *
     * @param funcionarios A lista de funcionários a ser impressa.
     */
    private void imprimirFuncionarios(List<Funcionario> funcionarios) {
        Imprimir(funcionarios);
    }

    /**
     * Imprime os funcionários formatados na saída padrão.
     * @param funcionarios Lista de funcionários a serem impressos.
     */
    private void Imprimir(List<Funcionario> funcionarios){
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + decimal.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
        }
    }

    /**
     * Aumenta os salários de todos os funcionários por um percentual fornecido.
     *
     * @param percentual O percentual pelo qual os salários devem ser aumentados.
     * @throws IllegalArgumentException Se o percentual for menor ou igual a zero.
     */
    public void aumentarSalarios(BigDecimal percentual) {
        if (percentual.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Percentual inválido. Forneça um percentual maior que zero.");
            throw new IllegalArgumentException("Percentual inválido. Forneça um percentual maior que zero.");
        }
        try {
            for (Funcionario funcionario : funcionarios) {
                funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.ONE.add(percentual))
                        .setScale(2, RoundingMode.HALF_UP));
            }
        } catch (NullPointerException e) {
            System.out.println("Erro ao aumentar salários: Lista de funcionários está vazia.");
        } catch (ArithmeticException e) {
            System.out.println("Erro ao aumentar salários: Percentual inválido.");
        }
    }

    /**
     * Agrupa os funcionários por função e retorna um mapa.
     *
     * @return Um mapa onde as chaves são as funções e os valores são listas de funcionários correspondentes.
     * @throws NullPointerException Se a lista de funcionários for nula.
     */
    public Map<String, List<Funcionario>> agruparPorFuncao() {
        if (funcionarios == null) {
            System.out.println("A lista de funcionários não deve ser nula.");
            throw new NullPointerException("A lista de funcionários não deve ser nula.");
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            funcionariosPorFuncao.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        }
        return funcionariosPorFuncao;
    }

    /**
     * Imprime os funcionários agrupados por função.
     *
     * @param funcionariosPorFuncao O mapa que contém os funcionários agrupados por função.
     * @throws NullPointerException     Se o mapa de funcionários por função for nulo.
     * @throws IllegalArgumentException Se alguma chave ou lista no mapa for nula.
     */
    public void imprimirFuncionariosAgrupadosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        if (funcionariosPorFuncao == null) {
            System.out.println("O mapa de funcionários por função não deve ser nulo.");
            throw new NullPointerException("O mapa de funcionários por função não deve ser nulo.");
        }
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            if (funcao == null || lista == null) {
                throw new IllegalArgumentException("Chave ou lista no mapa de funcionários por função não deve ser nula.");
            }
            System.out.println("Funcionários da função '" + funcao + "':");
            imprimirFuncionarios(lista);
        });
    }

    /**
     * Imprime os funcionários cujos aniversários correspondem aos meses fornecidos.
     *
     * @param meses Um array de meses (valores entre 1 e 12) para os quais procurar aniversariantes.
     * @throws NullPointerException     Se o array de meses ou a lista de funcionários for nulo.
     * @throws IllegalArgumentException Se algum valor no array de meses for inválido.
     */
    public void imprimirAniversariantes(int[] meses) {
        if (meses == null || funcionarios == null) {
            System.out.println("O array de meses e a lista de funcionários não devem ser nulos.");
            throw new NullPointerException("O array de meses e a lista de funcionários não devem ser nulos.");
        }

        if (Arrays.stream(meses).anyMatch(mes -> mes < 1 || mes > 12)) {
            System.out.println("Valores inválidos no array de meses. Os meses devem estar entre 1 e 12.");
            throw new IllegalArgumentException("Valores inválidos no array de meses. Os meses devem estar entre 1 e 12.");
        }

        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> Arrays.stream(meses).anyMatch(mes -> mes == funcionario.getDataNascimento().getMonthValue()))
                .toList();
        imprimirFuncionarios(aniversariantes);
    }

    /**
     * Imprime o funcionário mais velho da lista.
     *
     * @throws IllegalArgumentException Se não houver funcionários na lista.
     */
    public void imprimirFuncionarioMaiorIdade() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para calcular a idade do mais velho.");
            throw new IllegalArgumentException("Não há funcionários para calcular a idade do mais velho.");
        }

        Funcionario maisVelho = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getDataNascimento))
                .toList().getFirst();

        System.out.println("Funcionário mais velho Nome: " + maisVelho.getNome() + " - Idade: " + calcularIdade(maisVelho.getDataNascimento()));
    }

    /**
     * Calcula a idade com base na data de nascimento fornecida.
     *
     * @param dataNascimento A data de nascimento do funcionário.
     * @return A idade calculada.
     * @throws IllegalArgumentException Se a data de nascimento for posterior à data atual.
     */
    private int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento.isAfter(LocalDate.now())) {
            System.out.println("A data de nascimento não pode ser maior que a data atual.");
            throw new IllegalArgumentException("A data de nascimento não pode ser maior que a data atual.");
        }
        return LocalDate.now().getYear() - dataNascimento.getYear();
    }

    /**
     * Imprime os funcionários ordenados por nome.
     *
     * @throws IllegalArgumentException Se não houver funcionários na lista.
     */
    public void imprimirFuncionariosOrdenadoPorNome() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para ordenar.");
            throw new IllegalArgumentException("Não há funcionários para ordenar.");
        }

        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(funcionarios);
    }

    /**
     * Imprime o total dos salários dos funcionários.
     *
     * @throws IllegalArgumentException Se não houver funcionários na lista.
     */
    public void imprimirTotalSalarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para somar os salários.");
            throw new IllegalArgumentException("Não há funcionários para somar os salários.");
        }

        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + decimal.format(totalSalarios));
    }

    /**
     * Imprime quantos salários mínimos cada funcionário ganha.
     *
     * @param salarioMinimo O valor do salário mínimo.
     * @throws IllegalArgumentException Se o salário mínimo for nulo ou menor ou igual a zero.
     */
    public void imprimirSalariosMinimos(BigDecimal salarioMinimo) {
        BigDecimal salarioMinimoEquivalent;

        if (salarioMinimo == null || salarioMinimo.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("O salário mínimo deve ser um valor positivo não nulo.");
            throw new IllegalArgumentException("O salário mínimo deve ser um valor positivo não nulo.");
        }

        for (Funcionario funcionario : funcionarios) {
            salarioMinimoEquivalent  = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salarioMinimoEquivalent + " salários mínimos.");
        }
    }
}
