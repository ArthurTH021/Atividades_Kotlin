package sistema.funcionario

import pessoas.Funcionario
import repositorio.CRUDFuncionario
import utils.Validacoes
import java.math.BigDecimal

fun cadastrarFuncionario() {
    println("\n--- CADASTRAR FUNCIONÁRIO ---")

    val nome = Validacoes.lerTextoObrigatorio("Digite o nome: ")

    // Mudado para 'var' para permitir a reatribuição dentro do loop
    var cpf = ""
    while (true) {
        val entradaCpf = Validacoes.lerTextoObrigatorio("Digite o CPF (apenas números ou formatado): ")
        if (Validacoes.validarCpf(entradaCpf)) {
            cpf = entradaCpf
            break
        }
        println("CPF inválido! Tente novamente.")
    }

    val idade = Validacoes.lerInteiro("Digite a idade: ")
    val telefone = Validacoes.lerTextoObrigatorio("Digite o telefone: ")
    val setor = Validacoes.lerTextoObrigatorio("Digite o setor: ")
    val salarioDouble = Validacoes.lerDouble("Digite o salário: ")
    val salario = BigDecimal.valueOf(salarioDouble)

    val conexao = CRUDFuncionario()
    conexao.salvar(
        Funcionario(
            nome = nome,
            cpf = cpf,
            idade = idade,
            telefone = telefone,
            setor = setor,
            salario = salario,
            turno = null,     // Passando null diretamente
            habilidade = null // Passando null diretamente
        )
    )
    println("Funcionário cadastrado com sucesso!")
}