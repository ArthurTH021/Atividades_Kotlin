package repositorio

import java.math.BigDecimal
import java.sql.SQLException
import java.time.LocalDate

fun salvarMovimentacao(contexto : String, valor : BigDecimal, data : LocalDate) {
    val jpa = JPA()
    jpa.conectar()

    try {
        jpa.conectar()//Abre a conexão com o banco
        val sql = "INSERT INTO data_movimentacao " +
                "(valor, data_movimentacao, descricao) " +
                "VALUES (?, ?, ?)"

        val stmt = jpa.c!!.prepareStatement(sql)

        //Preparar as Variáveis para o banco
        stmt.setString(1, valor.toString())
        stmt.setString(2, data.toString())//TA ERRADO, PRECISA DA HORA
        stmt.setString(3, contexto)

        stmt.executeUpdate()
        
        stmt.close()//Encerra o Placeholder
        jpa.c!!.close()//Encerra a conexão com o banco
    } catch (e: SQLException) {
        println("Não salvou: ${e.printStackTrace()}")
    }
}