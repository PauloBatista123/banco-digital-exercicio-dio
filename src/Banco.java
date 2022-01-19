import java.util.*;

public class Banco {
    private String nome;
    private List<Conta> contas;
    private List<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return this.contas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void imprimirContas(){
        System.out.println("====== Contas de " + nome + " =====");
        System.out.println(contas.toString());
        System.out.println("=====================");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(nome, banco.nome) && Objects.equals(contas, banco.contas) && Objects.equals(clientes, banco.clientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, contas, clientes);
    }

    @Override
    public String toString() {
        return "Banco " + nome;
    }
}
