package repositorio

import produto.CaixaDaAgua
import java.sql.SQLException


class CRUDCaixaDaAgua(
    //Porta: 5432
    //User: postgres
    //Banco: caixaDaAgua
    //Senha: password


) : InterfaceJPA<CaixaDaAgua>, ConexaoPostgres() {
    override fun salvar(item: CaixaDaAgua) {
        println("Salvando...")
        try {
            conectar()//Abre a conexão com o banco
            val sql = "INSERT INTO caixa_da_agua " +
                    "(marca, modelo, dimensao, cor, material, formato, preco) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)"

            //Preparar lista para Double Precision
            val doublePrecision = c!!.createArrayOf("float8", item.dimensao.toTypedArray())
            //O typedArray() converte um Array para um tipo de dado legível para o Postgres

            val stmt = c!!.prepareStatement(sql)

            //Preparar as Variáveis para o banco
            stmt.setString(1, item.marca)
            stmt.setString(2, item.modelo)
            stmt.setArray(3, doublePrecision)
            stmt.setString(4, item.cor.name)
            stmt.setString(5, item.material.name)
            stmt.setString(6, item.formato)
            stmt.setString(7, item.preco.toString())

            stmt.executeUpdate()
            stmt.close()//Encerra o Placeholder

            c!!.close()//Encerra a conexão com o banco
        } catch (e: SQLException) {
            println("Não salvou: ${e.printStackTrace()}")
        }

    }//FIM SALVAR

    override fun listar() {
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

    override fun editar(item : CaixaDaAgua, id : Int) {
        try {
            conectar()
            val sql = "UPDATE caixa_da_agua SET preco = ?, marca = ?, modelo = ?, formato = ?, cor = ?, material = ?, dimensao = ? WHERE id = ?"
            //Continuar a lógica para os outros itens
            //Testar COR, MATERIAL e DIMENSÃO


            val stmt = c!!.prepareStatement(sql)

            stmt.setString(1, item.preco.toString())
            stmt.setString(2, item.marca)
            stmt.setString(3, item.modelo)
            stmt.setString(4, item.formato)
            stmt.setString(5, item.cor.name)
            stmt.setString(6, item.material.name)

            // 1. Converte a sua MutableList do Kotlin em um Array tradicional do Java
            val arrayJava = item.dimensao.toTypedArray()

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

    override fun excluir(id: Int) {
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