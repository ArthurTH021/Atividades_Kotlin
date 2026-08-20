package repositorio

import produto.CaixaDaAgua
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class JPA(
    //Porta: 5432
    //User: postgres
    //Banco: caixaDaAgua
    //Senha: password

    val user: String = "postgres",
    val senha: String = "password",
    val url: String = "jdbc:postgresql://localhost:5432/caixaDaAgua",
    var c: Connection? = null
) {
    fun conectar() {
        try {
            //Carregar o Driver
        Class.forName("org.postgresql.Driver")

            //Estabelecer Conexão
            c = DriverManager.getConnection(url, user, senha)
            println("A conexão foi estabelecida!!")

        } catch (e: SQLException) {
            println("Cara, não deu boa :( : ${e.printStackTrace()} ")
        }

    }
    fun salvar(a: CaixaDaAgua) {
        println("Salvando...")
        try {
            conectar()//Abre a conexão com o banco
            val sql = "INSERT INTO caixa_da_agua " +
                    "(marca, modelo, dimensao, cor, material, formato, preco) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)"

            //Preparar lista para Double Precision
            val doublePrecision = c!!.createArrayOf("float8", a.dimensao.toTypedArray())
            //O typedArray() converte um Array para um tipo de dado legível para o Postgres

            val stmt = c!!.prepareStatement(sql)

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

            c!!.close()//Encerra a conexão com o banco
        }catch (e:SQLException){
            println("Não salvou: ${e.printStackTrace()}")
        }

    }
}//FIM