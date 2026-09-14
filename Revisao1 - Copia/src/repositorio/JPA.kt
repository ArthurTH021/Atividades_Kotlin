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
        } catch (e: SQLException) {
            println("Não salvou: ${e.printStackTrace()}")
        }

    }//FIM SALVAR

    fun listar() {
        try {
            conectar()//IMPORTANTE
            println("=====================================")//Organização
            val stmt = c!!.createStatement()

            val sql = "SELECT * FROM caixa_da_agua "
            //Esses metadados vem em forma de Lista, ResultSet
            val metadados = stmt.executeQuery(sql)

            val resultado = metadados.metaData//Metadados
            val tamanhoTabela = resultado.columnCount//Tamanho da tabela em colunas

            while (metadados.next()) {
                for (i in 1..tamanhoTabela) {
                    //Nome da coluna
                    val nomeColuna = resultado.getColumnName(i)
                    //Dado que está na coluna
                    val valorColuna = metadados.getObject(i)

                    println("$nomeColuna -> $valorColuna")
                }//FIM FOR
                println("=====================================")//Organização
            }//FIM WHILE

            stmt.executeQuery(sql)
            stmt.close()
            c!!.close()
        } catch (e: SQLException) {
            println(e.printStackTrace())
        }//FIM TRY-CATCH
    }//FIM LISTAR

    fun editar(caixa : CaixaDaAgua, id : Int) {
        try {
            conectar()
            val sql = "UPDATE caixa_da_agua SET preco = ?, marca = ?, modelo = ?, formato = ?, cor = ?, material = ?, dimensao = ? WHERE id = ?"
            //Continuar a lógica para os outros itens
            //Testar COR, MATERIAL e DIMENSÃO


            val stmt = c!!.prepareStatement(sql)

            stmt.setString(1, caixa.preco.toString())
            stmt.setString(2, caixa.marca)
            stmt.setString(3, caixa.modelo)
            stmt.setString(4, caixa.formato)
            stmt.setString(5, caixa.cor.name)
            stmt.setString(6, caixa.material.name)

            // 1. Converte a sua MutableList do Kotlin em um Array tradicional do Java
            val arrayJava = caixa.dimensao.toTypedArray()

            // 2. Cria um objeto de Array que o PostgreSQL entende (tipo "float8" mapeia para double precision)
            val arrayBanco = c!!.createArrayOf("float8", arrayJava)

            // 3. Passa o array criado para o PreparedStatement na posição 7
            stmt.setArray(7, arrayBanco)

            stmt.setInt(8, id)

            stmt.executeUpdate()//Faz as alterações e manda para o banco

            c!!.close()
        }catch (e : SQLException){
            println(e.printStackTrace())
        }//FIM TRY-CATCH
    }//FIM EDITAR

    fun excluir(id: Int) {
        try {
            conectar()
            val sql = "DELETE FROM caixa_da_agua WHERE id = ?"
            val stmt = c!!.prepareStatement(sql)
            stmt.setInt(1, id)
            stmt.executeUpdate()

            stmt.close()
            c!!.close()
        }catch (e : SQLException){
            println(e.printStackTrace())
        }//FIM TRY-CATCH
    }//FIM EXCLUIR

}//FIM