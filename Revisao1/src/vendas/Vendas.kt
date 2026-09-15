package vendas

import repositorio.ConexaoPostgres
import java.math.BigDecimal
import java.sql.SQLException
import java.time.LocalDateTime
import java.sql.Timestamp


fun registrarVenda(
    idProduto: Int,
    quantidadeVendida: Int,
    pagador: String,
    recebedor: String,
    responsavel: String
) {
    val conexao = ConexaoPostgres().conectar() ?: return

    try {
        // 1. Consulta o item na tabela caixa_da_agua
        val sqlConsulta = "SELECT modelo, preco, quantidade FROM caixa_da_agua WHERE id = ?"
        var precoUnitario = BigDecimal.ZERO
        var estoqueAtual = 0
        var nomeProduto = ""

        conexao.prepareStatement(sqlConsulta).use { stmt ->
            stmt.setInt(1, idProduto)
            val rs = stmt.executeQuery()
            if (rs.next()) {
                nomeProduto = rs.getString("modelo") // usando o modelo como nome do produto
                // Converte o texto do preco para BigDecimal (ex: "150.00" -> BigDecimal)
                val precoStr = rs.getString("preco") ?: "0"
                precoUnitario = precoStr.toBigDecimalOrNull() ?: BigDecimal.ZERO
                estoqueAtual = rs.getInt("quantidade")
            } else {
                println("Caixa d'água não encontrada!")
                return
            }
        }

        // Valida se tem estoque suficiente
        if (estoqueAtual < quantidadeVendida) {
            println("Estoque insuficiente! Estoque atual: $estoqueAtual")
            return
        }

        // 2. Calcula o valor total da venda
        val valorTotal = precoUnitario.multiply(BigDecimal(quantidadeVendida))

        // 3. Atualiza o estoque (baixa)
        val sqlUpdateEstoque = "UPDATE caixa_da_agua SET quantidade = quantidade - ? WHERE id = ?"
        conexao.prepareStatement(sqlUpdateEstoque).use { stmt ->
            stmt.setInt(1, quantidadeVendida)
            stmt.setInt(2, idProduto)
            stmt.executeUpdate()
        }

        val dataHoraAtual = Timestamp.valueOf(LocalDateTime.now())
        val motivo = "Venda de $quantidadeVendida $nomeProduto"

        val sqlCaixa = """
        INSERT INTO fluxo_caixa (tipo, valor, pagador, recebedor, data_hora, motivo, responsavel)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """

        conexao.prepareStatement(sqlCaixa).use { stmt ->
            stmt.setString(1, "entrada")
            stmt.setBigDecimal(2, valorTotal)
            stmt.setString(3, pagador)
            stmt.setString(4, recebedor)
            stmt.setTimestamp(5, dataHoraAtual)
            stmt.setString(6, motivo)
            stmt.setString(7, responsavel)
            stmt.executeUpdate()
        }

        println("Venda realizada com sucesso! Total: R$ $valorTotal")

    } catch (e: SQLException) {
        println("Erro ao processar a venda: ${e.message}")
    } finally {
        conexao.close()
    }
}