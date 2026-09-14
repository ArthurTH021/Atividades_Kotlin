package repositorio

import produto.Servico
import java.math.BigDecimal
import java.sql.SQLException

class CRUDServico : ConexaoPostgres() {

    fun cadastrar(servico: Servico): Boolean {
        val conexao = conectar() ?: return false
        val sql = "INSERT INTO servico (nome, descricao, preco) VALUES (?, ?, ?)"
        return try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, servico.nome)
                stmt.setString(2, servico.descricao)
                stmt.setBigDecimal(3, servico.preco)
                stmt.executeUpdate()
            }
            println("Serviço cadastrado com sucesso!")
            true
        } catch (e: SQLException) {
            println("Erro ao cadastrar serviço: ${e.message}")
            false
        } finally {
            conexao.close()
        }
    }

    fun listar() {
        val conexao = conectar() ?: return
        val sql = "SELECT * FROM servico"
        try {
            conexao.createStatement().use { stmt ->
                val rs = stmt.executeQuery(sql)
                println("\n=== LISTA DE SERVIÇOS ===")
                while (rs.next()) {
                    println(
                        "ID: ${rs.getInt("id")} | " +
                                "Nome: ${rs.getString("nome")} | " +
                                "Descrição: ${rs.getString("descricao")} | " +
                                "Preço: R$ ${rs.getBigDecimal("preco")}"
                    )
                }
            }
        } catch (e: SQLException) {
            println("Erro ao listar serviços: ${e.message}")
        } finally {
            conexao.close()
        }
    }
}