package repositorio

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

abstract class ConexaoPostgres(
    val user: String = "postgres",
    val senha: String = "postgres",
    val url: String = "jdbc:postgresql://localhost:5432/caixaDaAgua"
) {
    fun conectar(): Connection? {
        var conexao: Connection? = null
        try {
            // Carregar o Driver
            Class.forName("org.postgresql.Driver")

            // Estabelecer Conexão
            conexao = DriverManager.getConnection(url, user, senha)
            println("A conexão foi estabelecida!!")

        } catch (e: SQLException) {
            println("Cara, não deu boa :( : ${e.message}")
        } catch (e: ClassNotFoundException) {
            println("Driver do Postgres não encontrado: ${e.message}")
        }
        return conexao
    }
}