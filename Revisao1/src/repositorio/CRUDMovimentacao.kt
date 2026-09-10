package repositorio

import financeiro.Movimentacao
import java.sql.Date
import java.sql.SQLException

class CRUDMovimentacao : InterfaceJPA<Movimentacao>, ConexaoPostgres() {

    override fun salvar(item: Movimentacao) {
        println("Registrando movimentação financeira...")
        val conexao = conectar() ?: return
        val sql = "INSERT INTO movimentacao (valor, data_movimentacao, contexto) VALUES (?, ?, ?)"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setBigDecimal(1, item.valor)
                stmt.setDate(2, Date.valueOf(item.dataMovimentacao))
                stmt.setString(3, item.contexto)
                stmt.executeUpdate()
            }
            println("Movimentação registrada com sucesso no banco!")
        } catch (e: SQLException) {
            println("Erro ao salvar movimentação: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    override fun listar() {
        val conexao = conectar() ?: return
        val sql = "SELECT * FROM movimentacao"

        try {
            conexao.createStatement().use { stmt ->
                val resultado = stmt.executeQuery(sql)
                while (resultado.next()) {
                    println("ID: ${resultado.getInt("id")} | Valor: R$ ${resultado.getBigDecimal("valor")} | Data: ${resultado.getDate("data_movimentacao")} | Contexto: ${resultado.getString("contexto")}")
                }
            }
        } catch (e: SQLException) {
            println("Erro ao listar movimentações: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    override fun editar(item: Movimentacao, id: Int) {
        val conexao = conectar() ?: return
        val sql = "UPDATE movimentacao SET valor = ?, contexto = ? WHERE id = ?"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setBigDecimal(1, item.valor)
                stmt.setString(2, item.contexto)
                stmt.setInt(3, id)
                stmt.executeUpdate()
                println("Movimentação atualizada com sucesso!")
            }
        } catch (e: SQLException) {
            println("Erro ao editar movimentação: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    override fun excluir(id: Int) {
        val conexao = conectar() ?: return
        val sql = "DELETE FROM movimentacao WHERE id = ?"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
                println("Movimentação excluída com sucesso!")
            }
        } catch (e: SQLException) {
            println("Erro ao excluir movimentação: ${e.message}")
        } finally {
            conexao.close()
        }
    }
}