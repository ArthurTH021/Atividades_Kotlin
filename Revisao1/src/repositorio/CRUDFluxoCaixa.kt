package repositorio

import sistema.pagamentos.MovimentacaoCaixa
import java.sql.SQLException
import java.sql.Timestamp

class CRUDFluxoCaixa : ConexaoPostgres() {

    fun registrarMovimentacao(movimentacao: MovimentacaoCaixa) {
        val conexao = conectar() ?: return
        val sql = "INSERT INTO fluxo_caixa (tipo, valor, pagador, recebedor, data_hora, motivo, responsavel) VALUES (?, ?, ?, ?, ?, ?, ?)"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, movimentacao.tipo)
                stmt.setBigDecimal(2, movimentacao.valor)
                stmt.setString(3, movimentacao.pagador)
                stmt.setString(4, movimentacao.recebedor)
                stmt.setTimestamp(5, Timestamp.valueOf(movimentacao.dataHora))
                stmt.setString(6, movimentacao.motivo)
                stmt.setString(7, movimentacao.responsavel)
                stmt.executeUpdate()
            }
            println("Movimentação financeira registrada com sucesso!")
        } catch (e: SQLException) {
            println("Erro ao registrar movimentação financeira: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    fun listarMovimentacoes() {
        val conexao = conectar() ?: return
        val sql = "SELECT * FROM fluxo_caixa ORDER BY data_hora DESC"

        try {
            conexao.createStatement().use { stmt ->
                val resultado = stmt.executeQuery(sql)
                println("\n=== EXTRATO DO FLUXO DE CAIXA ===")
                while (resultado.next()) {
                    println(
                        "ID: ${resultado.getInt("id")} | " +
                                "Tipo: ${resultado.getString("tipo")} | " +
                                "Valor: R$ ${resultado.getBigDecimal("valor")} | " +
                                "Pagador: ${resultado.getString("pagador")} | " +
                                "Recebedor: ${resultado.getString("recebedor")} | " +
                                "Data/Hora: ${resultado.getTimestamp("data_hora")} | " +
                                "Motivo: ${resultado.getString("motivo")} | " +
                                "Responsável: ${resultado.getString("responsavel")}"
                    )
                }
            }
        } catch (e: SQLException) {
            println("Erro ao consultar fluxo de caixa: ${e.message}")
        } finally {
            conexao.close()
        }
    }
}