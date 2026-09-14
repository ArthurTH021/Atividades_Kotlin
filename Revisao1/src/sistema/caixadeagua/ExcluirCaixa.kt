package sistema.caixadeagua

import repositorio.CRUDCaixaDaAgua

fun excluirCaixa() {
    val CRUDCaixaDaAgua = CRUDCaixaDaAgua()
    CRUDCaixaDaAgua.listar()
    println("Digite o ID que deseja excluir: ")
    val id = readln().toInt()

    CRUDCaixaDaAgua.excluir(id)
}