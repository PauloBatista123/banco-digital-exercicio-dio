import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

          Banco sicoob = new Banco("Sicoob");
          menu(sicoob);
//        Cliente joao = new Cliente("joao");
//        Cliente jose = new Cliente("jose");
//        Cliente carlos = new Cliente("carlos");
//
//        List<Conta> contas = new ArrayList<>(){{
//            add(new ContaCorrente(joao));
//            add(new ContaCorrente(jose));
//            add(new ContaPoupanca(carlos));
//            add(new ContaCorrente(jose));
//        }};
//
//        Banco sicoob = new Banco("Sicoob", contas);
//
//        sicoob.imprimirClientesDoBanco();

    }

    public static void cadastrarCliente(Banco banco){
        Scanner leitor = new Scanner(System.in);
        System.out.println("====== cadastro de clientes =======");
        System.out.println("Quantos clientes deseja cadastrar?");
        int quantidade = 0;
        while (quantidade <= 0){
            System.out.print("Quantidade:");
            quantidade = leitor.nextInt();
            if (quantidade > 0) break;
            System.out.println("Informe uma quantidade maior que 0...");
        }
        List<Cliente> listaNovosClientes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++){
            System.out.print("Digite o nome do cliente " + i + ":");
            String cliente = leitor.next();
            listaNovosClientes.add(new Cliente(cliente));
        }
        if (!listaNovosClientes.isEmpty()){
            banco.setClientes(listaNovosClientes);
        }
    }

    public static void limparConsole(){
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
            Process start = pb.inheritIO().start();
            start.waitFor();
        }catch (Exception e){
            System.err.println("erro interno " + e.getMessage());
        }
    }

    public static Cliente buscarCliente(Banco banco){
        Scanner leitor = new Scanner(System.in);
        System.out.print("Digite o numero do cliente:");
        int idCliente = leitor.nextInt();
        Cliente returnCliente = banco.getClientes().get((idCliente - 1));
        return returnCliente;
    }

    public static List<Conta> instaciaContas(Banco banco){
        if (banco.getContas() != null && !banco.getContas().isEmpty()) {
            return banco.getContas();
        }
        List<Conta> listaConta = new ArrayList<>();
        return listaConta;

    }

    public static void cadastrarContaCorrente(Banco banco) {

        System.out.println("Quantas contas deseja cadastrar?");
        int operacoes = new Scanner(System.in).nextInt();

        List<Conta> listaContas = instaciaContas(banco);

        for (int i = 1; i <= operacoes; i++){
            System.out.println("=== Cadastrar nova conta " + i + " ===");
            System.out.println("<<< Selecione o numero do cliente cadastrado >>>");
            System.out.println(banco.getClientes().toString());
            Cliente cliente = buscarCliente(banco);
            System.out.println("=== Tipos de Conta ===");
            System.out.println("1: Corrente - 2:Poupança");
            System.out.print("Opção:");
            int tipo = new Scanner(System.in).nextInt();
            if (tipo == 1) {
                ContaCorrente conta = new ContaCorrente(cliente);
                listaContas.add(conta);
            } else {
                ContaPoupanca conta = new ContaPoupanca(cliente);
                listaContas.add(conta);
            }

        }

        banco.setContas(listaContas);

    }

    public static void depositar(Banco banco){
        System.out.println("Valor do deposito:");
        double valor = new Scanner(System.in).nextDouble();
        System.out.print("Numero da conta:");
        int conta = new Scanner(System.in).nextInt();
        for (Conta cc : banco.getContas()){
            if(cc.getNumero() == conta){
                cc.depositar(valor);
                readLineConsole();
                menu(banco);
                break;
            }
        }
        System.out.println("Conta não encontrada... tente novamente!");
        readLineConsole();
        menu(banco);
    }

    public static void extrato(Banco banco){
        System.out.print("Numero da conta:");
        int conta = new Scanner(System.in).nextInt();
        for (Conta cc : banco.getContas()){
            if(cc.getNumero() == conta){
                cc.imprimirExtrato();
                readLineConsole();
                menu(banco);
                break;
            }
        }
        System.out.println("Conta não encontrada... tente novamente!");
        readLineConsole();
        menu(banco);
    }

    public static void readLineConsole(){
        System.console().readLine("Pressione enter para continuar");
    }

    public static void menu(Banco banco){
        Scanner leitor = new Scanner(System.in);

        System.out.println("Selecione a opção desejada...");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Exibir clientes");
        System.out.println("3 - Cadastrar contas");
        System.out.println("4 - Exibir contas");
        System.out.println("5 - Depositar");
        System.out.println("6 - Extrato da conta");

        System.out.print("Opção:");
        Integer opcao = leitor.nextInt();

        switch (opcao){
            case 1:
                limparConsole();
                cadastrarCliente(banco);
                limparConsole();
                menu(banco);
                break;
            case 2:
                limparConsole();
                System.out.println(banco.getClientes().toString());
                readLineConsole();
                limparConsole();
                menu(banco);
                break;
            case 3:
                limparConsole();
                cadastrarContaCorrente(banco);
                readLineConsole();
                limparConsole();
                menu(banco);
                break;
            case 4:
                limparConsole();
                banco.imprimirContas();
                readLineConsole();
                limparConsole();
                menu(banco);
                break;
            case 5:
                limparConsole();
                depositar(banco);
                readLineConsole();
                limparConsole();
                menu(banco);
                break;
            case 6:
                limparConsole();
                extrato(banco);
                readLineConsole();
                limparConsole();
                menu(banco);
                break;
            default:
                System.err.println("Selecione uma opcao valida...");
                menu(banco);
        }
    }
}
