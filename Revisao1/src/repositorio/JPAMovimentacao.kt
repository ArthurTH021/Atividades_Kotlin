package repositorio

import java.math.BigDecimal
import java.sql.SQLException

fun salvarMovimentacao(JPA : BigDecimal){
    val jpa = JPA()
    jpa.conectar()

    try {
        jpa.conectar()//Abre a conexão com o banco
        val sql = "INSERT INTO data_movimentacao " +
                "(valor, data_movimentacao, descricao) " +
                "VALUES (?, ?, ?)"

        //Preparar lista para Double Precision
        val doublePrecision = jpa.c!!.createArrayOf("float8", a.dimensao.toTypedArray())
        //O typedArray() converte um Array para um tipo de dado legível para o Postgres

        val stmt = jpa.c!!.prepareStatement(sql)

        //Preparar as Variáveis para o banco
        stmt.setString(1, a.marca)
        stmt.setString(2, a.modelo)
        stmt.setArray(3, doublePrecision)
        stmt.setString(4, a.cor.name)
        stmt.setString(5, a.material.name)
        stmt.setString(6, a.formato)
        stmt.setString(7, a.preco.toString())

        stmt.executeUpdate()
        stmt.close()//Encerra o Placeholder

        jpa.c!!.close()//Encerra a conexão com o banco
    } catch (e: SQLException) {
        println("Não salvou: ${e.printStackTrace()}")
    }
}