import java.util.Objects;

public class Cliente implements Comparable<Cliente> {
    private static int NUMERO_CLIENTE = 1;
    private String nome;
    private int numero;

    public Cliente(String nome) {
        this.nome = nome;
        this.numero = NUMERO_CLIENTE++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return numero + " - " +nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public int compareTo(Cliente o) {
        return this.getNome().compareTo(o.getNome());
    }
}
