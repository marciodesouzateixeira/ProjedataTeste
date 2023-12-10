package app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
     * @return true se o funcionário foi inserido com sucesso, ou false se a operação falhar.
     */
    public boolean inserirFuncionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        if (nome.trim().isEmpty()) {
            System.out.println("Nome deve ser preenchido.");
            return false;
        }
        if (dataNascimento.isAfter(LocalDate.now())) {
            System.out.println("Data de nascimento deve estar no passado.");
            return false;
        }
        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Salário deve ser um valor positivo.");
            return false;
        }
        if (funcao.trim().isEmpty()) {
            System.out.println("Função deve ser preenchida.");
            return false;
        }
        return funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
    }

    /**
     * Remove um funcionário da lista com base no nome fornecido.
     *
     * @param nome O nome do funcionário a ser removido.
     * @return true se o funcionário foi removido com sucesso, ou false se o funcionário não foi encontrado.
     */
    public boolean removerFuncionarioPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido. Forneça um nome válido para remover um funcionário.");
            return false;
        }
        return funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }

    /**
     * Busca funcionários por nome.
     *
     * @param nome O nome do funcionário a ser buscado.
     * @return Uma lista de funcionários com o nome correspondente.
     */
    public List<Funcionario> buscarFuncionariosPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido. Forneça um nome válido para buscar funcionários.");
            return new ArrayList<>();
        }

        List<Funcionario> funcionariosEncontrados = funcionarios.stream()
                .filter(funcionario -> funcionario.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());

        if (funcionariosEncontrados.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado com o nome: " + nome);
        }

        return funcionariosEncontrados;
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
     * @return true se a operação foi bem-sucedida, ou false se houve um erro.
     */
    public boolean aumentarSalarios(BigDecimal percentual) {
        if (percentual.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Percentual inválido. Forneça um percentual maior que zero.");
            return false;
        }
        try {
            for (Funcionario funcionario : funcionarios) {
                funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.ONE.add(percentual))
                        .setScale(2, RoundingMode.HALF_UP));
            }
            return true;
        } catch (NullPointerException e) {
            System.out.println("Erro ao aumentar salários: Lista de funcionários está vazia.");
        } catch (ArithmeticException e) {
            System.out.println("Erro ao aumentar salários: Percentual inválido.");
        }
        return false;
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
     * @return true se a operação foi bem-sucedida, ou false se houve um erro.
     */
    public boolean imprimirFuncionariosAgrupadosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        if (funcionariosPorFuncao == null) {
            System.out.println("O mapa de funcionários por função não deve ser nulo.");
            return false;
        }
        try{
            funcionariosPorFuncao.forEach((funcao, lista) -> {
                if (funcao == null || lista == null) {
                    throw new IllegalArgumentException("Chave ou lista no mapa de funcionários por função não deve ser nula.");
                }
                System.out.println("Funcionários da função '" + funcao + "':");
                imprimirFuncionarios(lista);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Imprime os funcionários cujos aniversários correspondem aos meses fornecidos.
     *
     * @param meses Um array de meses (valores entre 1 e 12) para os quais procurar aniversariantes.
     * @return true se a operação foi bem-sucedida, ou false se houve um erro.
     */
    public boolean imprimirAniversariantes(int[] meses) {
        if (meses == null || funcionarios == null) {
            System.out.println("O array de meses e a lista de funcionários não devem ser nulos.");
            return false;
        }

        if (Arrays.stream(meses).anyMatch(mes -> mes < 1 || mes > 12)) {
            System.out.println("Valores inválidos no array de meses. Os meses devem estar entre 1 e 12.");
            return false;
        }

        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> Arrays.stream(meses).anyMatch(mes -> mes == funcionario.getDataNascimento().getMonthValue()))
                .toList();
        try {
            imprimirFuncionarios(aniversariantes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Imprime o funcionário mais velho da lista.
     *
     * @return O funcionário mais velho.
     */
    public Funcionario imprimirFuncionarioMaiorIdade() {
        Funcionario maisVelho = new Funcionario();
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para calcular a idade do mais velho.");
            return maisVelho;
        }
        try{
            maisVelho = funcionarios.stream()
                    .sorted(Comparator.comparing(Funcionario::getDataNascimento))
                    .toList().getFirst();
            System.out.println("Funcionário mais velho Nome: " + maisVelho.getNome() + " - Idade: " + calcularIdade(maisVelho.getDataNascimento()));
            return maisVelho;
        } catch (Exception e) {
            return maisVelho;
        }
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
     * @return true se a operação foi bem-sucedida, ou false se houve um erro.
     */
    public boolean imprimirFuncionariosOrdenadoPorNome() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para ordenar.");
            return false;
        }

        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        imprimirFuncionarios(funcionarios);
        return true;
    }

    /**
     * Imprime o total dos salários dos funcionários.
     *
     * @return true se a operação foi bem-sucedida, ou false se houve um erro.
     */
    public boolean imprimirTotalSalarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para somar os salários.");
            return false;
        }

        try{
            BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println("Total dos salários: " + decimal.format(totalSalarios));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Imprime quantos salários mínimos cada funcionário ganha.
     *
     * @param salarioMinimo O valor do salário mínimo.
     * @return true se a operação foi bem-sucedida, ou false se houve um erro.
     */
    public boolean imprimirSalariosMinimos(BigDecimal salarioMinimo) {
        BigDecimal salarioMinimoEquivalent;

        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para imprimir salários mínimos.");
            return false;
        }

        if (salarioMinimo == null || salarioMinimo.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("O salário mínimo deve ser um valor positivo não nulo.");
            return false;
        }

        try{
            for (Funcionario funcionario : funcionarios) {
                salarioMinimoEquivalent  = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
                System.out.println(funcionario.getNome() + " ganha " + salarioMinimoEquivalent + " salários mínimos.");
        }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
