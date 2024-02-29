import java.util.ArrayList;

public class App {
    //Array para armazenar funcionarios
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    //Método para cadastrar funcionario
    private static void cadastrarFuncionario(String nome, String cpf, String matricula, String departamento,double salario) {
        Funcionario novoFuncionario = new Funcionario(nome, cpf, matricula, departamento, salario);
        funcionarios.add(novoFuncionario);
    }

    public static void consultar() {
        for(Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.nome + "CPF: " + funcionario.cpf + "Matrícula: " + funcionario.matricula + "Departamento: " + funcionario.departamento + "Salario: " + funcionario.salario)
    }
}

