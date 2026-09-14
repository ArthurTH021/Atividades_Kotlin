package sistema.caixadeagua

import repositorio.CRUDCaixaDaAgua

fun listarCaixa() {
    val CRUDCaixaDaAgua = CRUDCaixaDaAgua()
    CRUDCaixaDaAgua.listar()
}
