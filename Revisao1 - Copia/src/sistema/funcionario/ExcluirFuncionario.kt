package sistema.funcionario

import repositorio.CRUDFuncionario

fun excluirFuncionario() {
    val crud = CRUDFuncionario()
    println("\n--- EXCLUIR INSTALADOR ---")
    print("Digite o ID do instalador que deseja excluir: ")
    val id = readln().toIntOrNull() ?: 0

    if (id > 0) {
        crud.excluir(id)
    } else {
        println("ID inválido!")
    }
}