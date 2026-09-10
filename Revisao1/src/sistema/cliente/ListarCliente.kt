package sistema.cliente

import repositorio.CRUDCliente

fun listarCliente() {
    println("\n--- LISTA DE CLIENTES ---")
    try {
        val crud = CRUDCliente()
        val clientes = crud.listar()

        if (clientes.isEmpty()) {
            println("Nenhum cliente cadastrado.")
        } else {
            clientes.forEach { cliente ->
                println("Nome: ${cliente.nomeCliente} | Idade: ${cliente.idadeCliente} | CPF: ${cliente.cpfCliente}")
            }
        }
    } catch (e: Exception) {
        println("Erro ao listar clientes: ${e.message}")
    }
}