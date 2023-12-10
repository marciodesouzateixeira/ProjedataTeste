package app;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa um funcionário que é uma extensão da classe Pessoa, incluindo salário e função.
 */
public class Funcionario extends Pessoa {

    /**
     * O salário do funcionário.
     */
    private BigDecimal salario;

    /**
     * A função do funcionário.
     */
    private String funcao;

    /**
     * Cria uma nova instância de Funcionario com nome, data de nascimento, salário e função.
     *
     * @param nome            O nome do funcionário.
     * @param dataNascimento A data de nascimento do funcionário.
     * @param salario         O salário do funcionário.
     * @param funcao          A função do funcionário.
     */
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    /**
     * Obtém o salário do funcionário.
     *
     * @return O salário do funcionário.
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salario O novo salário do funcionário.
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    /**
     * Obtém a função do funcionário.
     *
     * @return A função do funcionário.
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * Define a função do funcionário.
     *
     * @param funcao A nova função do funcionário.
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * Retorna uma representação em string do funcionário.
     *
     * @return Uma string que representa o funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario{" +
                "salario=" + salario +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}