package repositorio

import financeiro.Movimentacao
import java.math.BigDecimal
import java.sql.SQLException
import java.time.LocalDate

class CRUDMovimentacao () : InterfaceJPA<Movimentacao>, ConexaoPostgres() {
    override fun salvar(item: Movimentacao) {

        try {
            conectar()//Abre a conexão com o banco
            val sql = "INSERT INTO data_movimentacao " +
                    "(valor, data_movimentacao, descricao) " +
                    "VALUES (?, ?, ?)"

            val stmt = c!!.prepareStatement(sql)

            //Preparar as Variáveis para o banco
            stmt.setString(1, item.valor.toString())
            stmt.setDate(2, java.sql.Date.valueOf(item.dataMovimentacao))
            stmt.setString(3, item.contexto)

            stmt.executeUpdate()

            stmt.close()//Encerra o Placeholder
            c!!.close()//Encerra a conexão com o banco
        } catch (e: SQLException) {
            println("Não salvou: ${e.printStackTrace()}")
        }
    }
    override fun editar(item: Movimentacao, id : Int){}//Não será implementado
    override fun excluir(id: Int){}//Não será implementado
    override fun listar() {
        try {
            conectar()//IMPORTANTE
            println("=====================================")//Organização
            val stmt = c!!.createStatement()

            val sql = "SELECT * FROM movimentacao "
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
        }catch (e: SQLException){
            print(e.message)
        }
    }
}