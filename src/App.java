import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    //Array para armazenar funcionarios
    private static final ArrayList<Funcionario> funcionarios = new ArrayList<>();
    static Set<String> departamentos = new HashSet<>();

    //Método para cadastrar funcionario
    private static void cadastrarFuncionario(Scanner novoFuncionario) {
        System.out.print("Nome: ");
        String nome = novoFuncionario.nextLine();

        String cpf = null;
        boolean cpfValido = false;

        while (!cpfValido) {
            boolean cpfOK = false;
            boolean cpfNovo = true;

            System.out.print("CPF: ");
            cpf = novoFuncionario.nextLine();

            if (cpf.length() == 11 && cpf.matches("[0-9]+")) {
                cpfOK = true;
            } else {
                System.out.println("CPF inválido. O CPF deve conter 11 (onze) números. Favor digitar SOMENTE os números.");
                continue;
            }

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getCpf().equals(cpf)) {
                    System.out.println("Este cpf já está cadastrado no sistema. Por favor, tente novamente.");
                    cpfNovo = false; // O CPF não é novo
                    break;
                }
            }

            if (cpfNovo) {
                cpfValido = true;
            }
        }

        String matricula = null;
        boolean matriculaNova = false;

        while (!matriculaNova) {
            System.out.println("Número da matrícula: ");
            matricula = novoFuncionario.nextLine();

            boolean matriculaExistente = false;
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getMatricula().equals(matricula)) {
                    matriculaExistente = true;
                    System.out.println("Este nº de matrícula já consta no sistema. Por favor, tente novamente.");
                    break; 
                }
            }

            if (!matriculaExistente) {
                matriculaNova = true; 
            }
        }



        System.out.println("Departamento: ");
        String departamento = novoFuncionario.nextLine();

        double salario = 0.0;
        boolean salarioValido = false;
        while (!salarioValido) {
            System.out.println("Salário: ");
            if (novoFuncionario.hasNextDouble()) {
                salario = novoFuncionario.nextDouble();
                salarioValido = true;
            } else {
                System.out.println("Valor inválido. Por favor, insira um valor válido para o salário.");
                novoFuncionario.next();
            }
        }
        Funcionario F1 = new Funcionario(nome, cpf, matricula, departamento, salario);
        funcionarios.add(F1);
    }
    public static void remover (Scanner removeFuncionario){


        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários a remover.");
        } else {

            boolean funcionarioEncontrado = false;
            while (!funcionarioEncontrado){
                ArrayList<Funcionario> copiaFuncionarios = new ArrayList<>(funcionarios);
                System.out.println("Qual o número de MATRÍCULA do funcionário que deseja remover?");
                String funcionarioRemover = removeFuncionario.nextLine();
                for (Funcionario funcionario : copiaFuncionarios) {
                    if (funcionario.getMatricula().equals(funcionarioRemover)) {
                        funcionarios.remove(funcionario);
                        funcionarioEncontrado = true;
                        System.out.println("Funcionário " + funcionario.getNome() + " removido com sucesso!");
                        break;
                    }
                }
                if (!funcionarioEncontrado) {
                    System.out.println("Funcionário não encontrado.");
                    }
                }
        }

    }


    public static void consultar (ArrayList < Funcionario > funcionarios) {

        for (Funcionario funcionario : funcionarios) {
            departamentos.add(funcionario.departamento);
        }


        for (String departamento : departamentos) {

            int cont = 0;
            ArrayList<Funcionario> f = new ArrayList<>();

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.departamento.equals(departamento)) {
                    f.add(funcionario);
                    cont += 1;
                }

            }
            if (cont > 0) {
                System.out.println("Departamento: " + departamento + "\nNº de funcionários: " + cont + "\n");
                for (Funcionario funcionario : f) {
                    System.out.println("Nome: " + funcionario.nome + "\nCPF: " + funcionario.cpf + "\nMatrícula: " + funcionario.matricula + "\nSalario: R$ " + funcionario.salario + "\n");
                }
            }

        }
    }

    public static void consultaSalario (Set < String > departamentos) {
        for (Funcionario funcionario : funcionarios) {
            departamentos.add(funcionario.departamento);
        }
        for (String departamento : departamentos) {
            double salario = 0.0;
            int n = 0;
            ArrayList<Funcionario> f1 = new ArrayList<>();


            for (Funcionario funcionario : funcionarios) {
                if (funcionario.departamento.equals(departamento)) {
                    n += 1;
                    salario += funcionario.getSalario();
                    f1.add(funcionario);


                }
            }
            if (n > 0) {
                System.out.println("Departamento: " + departamento + "\nNº de funcionários: " + n + "\n");
                for (Funcionario funcionario : f1) {
                    System.out.println(funcionario.nome + ": R$ " + funcionario.salario);
                }
                System.out.println("\nSalário total: R$ " + salario + "\n");
            }

        }
    }

    public static void atualizaSalario(Scanner atualizaSalario){

        boolean haMatricula = false;
        while(!haMatricula) {
            System.out.println("Matrícula do funcionário: ");
            String matriculaF = atualizaSalario.nextLine();
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.matricula.equals(matriculaF)) {
                    System.out.println("Novo salário: ");
                    double novoSalario = atualizaSalario.nextDouble();
                    atualizaSalario.nextLine();
                    System.out.println("Salário de " + funcionario.nome + " atualizado de R$ " + funcionario.salario + " para R$ " + novoSalario);
                    funcionario.salario = novoSalario;
                    haMatricula = true;
                    break;

                }
            }
            if (!haMatricula){
                System.out.println("Funcionário não encontrada");

            }
        }
    }

    public static void Realocar(Scanner realocaFuncionario){

        boolean matriculaEncontrada = false;
        while (!matriculaEncontrada) {
            System.out.println("Matrícula do funcionário: ");
            String matriculaF2 = realocaFuncionario.nextLine();
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.matricula.equals(matriculaF2)) {
                    System.out.println("Novo departamento: ");
                    String novoDepartamento = realocaFuncionario.nextLine();
                    //realocaFuncionario.nextLine();
                    System.out.println("O funcionário " + funcionario.nome + " foi transferido do departamento de " + funcionario.departamento + " para o de " + novoDepartamento);
                    funcionario.departamento = novoDepartamento;
                    matriculaEncontrada = true;
                    break;

                }
            }
            if (!matriculaEncontrada) {
                System.out.println("Funcionário não encontrado.");

            }
        }
    }
    

    public static void main (String[]args){

        Scanner sc = new Scanner(System.in);
        String s2;
        int n2;
        do {
            s2 = """
                        O que deseja fazer? (Digite o NÚMERO):
                        1 - Adicionar funcionários
                        2 - Consultar funcionários
                        3 - Consultar salários por departamento
                        4 - Atualizar salários 
                        5 - Realocar funcionários
                        6 - Remover funcionários
                        7 - Sair da aplicação""";
            System.out.println(s2);
            n2 = sc.nextInt();
            sc.nextLine();

            switch (n2) {
                case 1:

                    while (true) {

                        cadastrarFuncionario(sc);

                        System.out.print("Deseja adicionar outro funcionário? (s/n): ");
                        String resposta = sc.next();
                        //novaTarefa.nextLine();

                        if (!resposta.equalsIgnoreCase("s")) {
                            break;
                        }

                        sc.nextLine();
                    }

                    break;
                case 2:

                    if (funcionarios.isEmpty()) {
                        System.out.println("Não há funcionários no momento.");
                        break;
                    }

                    consultar(funcionarios);


                    break;
                case 3:
                    if (funcionarios.isEmpty()) {
                        System.out.println("Não há departamentos cadastrados no sistema.");
                        break;
                    }
                    consultaSalario(departamentos);
                    break;

                case 4:

                    if (funcionarios.isEmpty()) {
                        System.out.println("Não há funcionários no momento.");
                        break;
                    }

                    while (true) {

                        atualizaSalario(sc);

                        System.out.print("Deseja atualizar o salário de outro funcionário? (s/n): ");
                        String resposta = sc.next();
                        //novaTarefa.nextLine();

                        if (!resposta.equalsIgnoreCase("s")) {
                            break;
                        }

                        sc.nextLine();
                    }
                    break;

                case 5:

                    if (funcionarios.isEmpty()) {
                        System.out.println("Não há funcionários no momento.");
                        break;
                    }

                    while (true) {

                        Realocar(sc);

                        System.out.print("Deseja realocar outro funcionário? (s/n): ");
                        String resposta = sc.next();
                        //novaTarefa.nextLine();

                        if (!resposta.equalsIgnoreCase("s")) {
                            break;
                        }

                        sc.nextLine();
                    }
                    break;

                case 6:

                    if (funcionarios.isEmpty()) {
                        System.out.println("Não há funcionários a serem removidos.");
                        break;
                    }

                    while (true) {

                        remover(sc);

                        if (funcionarios.isEmpty()) {
                            System.out.println("Não há mais funcionários a serem removidos.");
                            break;
                        }

                        System.out.print("Deseja remover outro funcionário? (s/n): ");
                        String resposta = sc.next();
                        //novaTarefa.nextLine();

                        if (!resposta.equalsIgnoreCase("s")) {
                            break;
                        }

                        sc.nextLine();
                    }
                    break;

                case 7:
                    System.out.println("Até mais! :)");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, digite outro número.");

            }
        } while (n2 != 7);

    }
}





