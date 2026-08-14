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
            c!!.createStatement().executeQuery("" +
                    "INSERT INTO caixa_da_agua " +
                    "(marca, modelo, dimensao, cor, material, formato, preco) " +
                    "VALUES (${a.marca}, ${a.modelo}, ${a.dimensao}, ${a.cor}, ${a.material}, ${a.formato}, ${a.preco})")
            c!!.close()//Encerra a conexão com o banco
        }catch (e:SQLException){
            println("Não salvou: ${e.printStackTrace()}")
        }

    }
}//FIM