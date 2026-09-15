package sistema.cliente

import pessoas.Cliente
import repositorio.CRUDCliente
import utils.Validacoes

fun cadastrarCliente() {
    println("\n--- CADASTRAR CLIENTE ---")

    val nome = Validacoes.lerTextoObrigatorio("Digite o nome do cliente: ")
    val idade = Validacoes.lerInteiro("Digite a idade do cliente: ")

    // Validação de CPF com loop usando a mesma Regex do projeto
    var cpf = ""
    while (true) {
        val entradaCpf = Validacoes.lerTextoObrigatorio("Digite o CPF do cliente (apenas números ou formatado): ")
        if (Validacoes.validarCpf(entradaCpf)) {
            cpf = entradaCpf
            break
        }
        println("CPF inválido! Tente novamente.")
    }

    try {
        val crud = CRUDCliente()
        val cliente = Cliente(nome, idade, cpf)
        crud.inserir(cliente)

        println("Cliente '$nome' cadastrado com sucesso no banco!")
    } catch (e: Exception) {
        println("Erro ao salvar no banco: ${e.message}")
    }
}