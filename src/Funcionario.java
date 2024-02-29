public class Funcionario {
    String nome;
    String cpf;
    String matricula;
    String departamento
    double salario;

    public Funcionario (String nome, String cpf, String matricula,String departamento, double salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.departamento = departamento;
        this.salario = salario;
    }

    public int calcularSalario() {
        return this.salario
    }
}