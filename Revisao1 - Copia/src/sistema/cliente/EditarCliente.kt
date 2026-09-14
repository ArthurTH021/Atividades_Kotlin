package sistema.cliente

import repositorio.CRUDCliente

fun editarCliente() {
    println("\n--- EDITAR CLIENTE ---")
    print("Digite o CPF do cliente que deseja editar: ")
    val cpf = readlnOrNull() ?: ""

    if (cpf.isBlank()) {
        println("CPF inválido!")
        return
    }

    print("Digite o novo nome do cliente: ")
    val novoNome = readlnOrNull() ?: ""

    if (novoNome.isBlank()) {
        println("O nome não pode ser vazio!")
        return
    }

    try {
        val crud = CRUDCliente()
        val atualizado = crud.atualizar(cpf, novoNome)

        if (atualizado) {
            println("Cliente atualizado com sucesso!")
        } else {
            println("Cliente com CPF $cpf não encontrado.")
        }
    } catch (e: Exception) {
        println("Erro ao editar cliente: ${e.message}")
    }
}