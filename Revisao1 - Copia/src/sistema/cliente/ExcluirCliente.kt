package sistema.cliente

import repositorio.CRUDCliente

fun excluirCliente() {
    println("\n--- EXCLUIR CLIENTE ---")
    print("Digite o CPF do cliente que deseja excluir: ")
    val cpf = readlnOrNull() ?: ""

    if (cpf.isBlank()) {
        println("CPF inválido!")
        return
    }

    try {
        val crud = CRUDCliente()
        val excluido = crud.deletar(cpf)

        if (excluido) {
            println("Cliente excluído com sucesso!")
        } else {
            println("Cliente com CPF $cpf não encontrado.")
        }
    } catch (e: Exception) {
        println("Erro ao excluir cliente: ${e.message}")
    }
}