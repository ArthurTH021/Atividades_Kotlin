package sistema.caixadeagua

import repositorio.ConexaoPostgres
import java.sql.SQLException

fun darEntradaCaixa(idCaixa: Int, quantidadeEntrada: Int) {
    val conexao = ConexaoPostgres().conectar() ?: return

    try {
        val sql = "UPDATE caixa_da_agua SET quantidade = quantidade + ? WHERE id = ?"
        conexao.prepareStatement(sql).use { stmt ->
            stmt.setInt(1, quantidadeEntrada)
            stmt.setInt(2, idCaixa)
            val linhasAfetadas = stmt.executeUpdate()
            if (linhasAfetadas > 0) {
                println("Entrada de $quantidadeEntrada unidade(s) registrada com sucesso!")
            } else {
                println("Caixa d'água com ID $idCaixa não encontrada!")
            }
        }
    } catch (e: SQLException) {
        println("Erro ao registrar entrada: ${e.message}")
    } finally {
        conexao.close()
    }
}