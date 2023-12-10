package app;

import java.time.LocalDate;

/**
 * Representa uma pessoa com nome e data de nascimento.
 */
public class Pessoa {
    /**
     * O nome da pessoa.
     */
    private String nome;

    /**
     * A data de nascimento da pessoa.
     */
    private LocalDate dataNascimento;

    /**
     * Cria uma nova instância de Pessoa com nome e data de nascimento.
     *
     * @param nome            O nome da pessoa.
     * @param dataNascimento A data de nascimento da pessoa.
     */
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {

    }

    /**
     * Obtém a data de nascimento da pessoa.
     *
     * @return A data de nascimento da pessoa.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento da pessoa.
     *
     * @param dataNascimento A nova data de nascimento da pessoa.
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Obtém o nome da pessoa.
     *
     * @return O nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome O novo nome da pessoa.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna uma representação em string da pessoa.
     *
     * @return Uma string que representa a pessoa.
     */
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
