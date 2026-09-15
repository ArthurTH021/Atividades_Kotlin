package sistema.funcionario

import repositorio.CRUDFuncionario

fun listarFuncionario() {
    val crud = CRUDFuncionario()
    println("\n--- LISTA DE FUNCIONARIOS ---")
    crud.listar()
}