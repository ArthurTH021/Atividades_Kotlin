package repositorio

import produto.CaixaDaAgua
import java.math.BigDecimal
import java.sql.SQLException

// Implementa o contrato da interface genérica InterfaceJPA e herda a lógica de conexão do ConexaoPostgres
class CRUDCaixaDaAgua : InterfaceJPA<CaixaDaAgua>, ConexaoPostgres() {

    override fun salvar(item: CaixaDaAgua) {
        println("Salvando...")

        // Abre a conexão com o banco; se falhar (retornar null), interrompe a execução com 'return'
        val conexao = conectar() ?: return

        // Define a query SQL de inserção usando '?' (placeholders) para evitar SQL Injection
        val sql = "INSERT INTO caixa_da_agua (marca, modelo, dimensao, cor, material, formato, preco) VALUES (?, ?, ?, ?, ?, ?, ?)"

        try {
            // Converte a lista de dimensões do Kotlin para um array de ponto flutuante compatível com o PostgreSQL ("float8")
            val doublePrecision = conexao.createArrayOf("float8", item.dimensao.toTypedArray())

            // Converte o preço de String para BigDecimal de forma segura; se falhar ou estiver vazio, define como ZERO para não quebrar a aplicação
            val precoBigDecimal = try {
                BigDecimal(item.preco.toString())
            } catch (e: Exception) {
                BigDecimal.ZERO
            }

            // Prepara a query e utiliza o bloco '.use' para garantir que o Statement feche automaticamente após o uso (evita vazamento de memória)
            conexao.prepareStatement(sql).use { stmt ->
                // Associa cada atributo do objeto CaixaDaAgua à sua respectiva interrogação (?) na ordem dos índices
                stmt.setString(1, item.marca)
                stmt.setString(2, item.modelo)
                stmt.setArray(3, doublePrecision)
                stmt.setString(4, item.cor.name)
                stmt.setString(5, item.material.name)
                stmt.setString(6, item.formato)
                stmt.setBigDecimal(7, precoBigDecimal)

                // Executa a alteração no banco de dados (INSERT)
                stmt.executeUpdate()
            }

        } catch (e: SQLException) {
            // Captura qualquer erro específico de SQL e exibe a mensagem amigável no console
            println("Erro ao salvar no banco: ${e.message}")
        } finally {
            // Bloco executado sempre (com ou sem erro) para fechar a conexão com o banco e liberar recursos
            conexao.close()
        }
    }

    override fun listar() {
        // Abre a conexão com o banco de dados
        val conexao = conectar() ?: return

        // Define a query SQL para buscar todos os registros da tabela
        val sql = "SELECT * FROM caixa_da_agua"

        try {
            // Cria um Statement simples (sem parâmetros) e garante o fechamento automático com '.use'
            conexao.createStatement().use { stmt ->
                // Executa a consulta e armazena o resultado retornado pelo banco
                val resultado = stmt.executeQuery(sql)

                // Percorre linha por linha os dados trazidos da tabela do banco
                while (resultado.next()) {
                    // Extrai os valores das colunas específicas e exibe no console (adicionada a quantidade)
                    println("ID: ${resultado.getInt("id")} | Marca:${resultado.getString("marca")} | Modelo: ${resultado.getString("modelo")} | Preço:${resultado.getString("preco")} | Qtd: ${resultado.getInt("quantidade")}")
                }
            }
        } catch (e: SQLException) {
            // Trata eventuais falhas na consulta SQL
            println("Erro ao listar do banco: ${e.message}")
        } finally {
            // Fecha a conexão com o banco de dados
            conexao.close()
        }
    }

    override fun editar(item: CaixaDaAgua, id: Int) {
        // Abre a conexão com o banco
        val conexao = conectar() ?: return

        // Define a query SQL de atualização (UPDATE) filtrando pelo ID específico do registro
        val sql = "UPDATE caixa_da_agua SET marca = ?, modelo = ?, formato = ?, preco = ? WHERE id = ?"

        try {
            // Realiza a conversão segura do preço para BigDecimal
            val precoBigDecimal = try {
                BigDecimal(item.preco.toString())
            } catch (e: Exception) {
                BigDecimal.ZERO
            }

            // Prepara a query de atualização com segurança contra SQL Injection
            conexao.prepareStatement(sql).use { stmt ->
                // Atribui os novos valores aos parâmetros da query
                stmt.setString(1, item.marca)
                stmt.setString(2, item.modelo)
                stmt.setString(3, item.formato)
                stmt.setBigDecimal(4, precoBigDecimal)
                stmt.setInt(5, id) // Define qual ID será atualizado no WHERE

                // Executa a atualização no banco
                stmt.executeUpdate()
                println("Caixa d'água editada com sucesso!")
            }
        } catch (e: SQLException) {
            // Trata erros de SQL durante o update
            println("Erro ao editar no banco: ${e.message}")
        } finally {
            // Garante o encerramento da conexão
            conexao.close()
        }
    }

    override fun excluir(id: Int) {
        // Abre a conexão com o banco
        val conexao = conectar() ?: return

        // Define a query SQL de exclusão (DELETE) baseada no ID do registro
        val sql = "DELETE FROM caixa_da_agua WHERE id = ?"

        try {
            // Prepara a query de exclusão com segurança
            conexao.prepareStatement(sql).use { stmt ->
                // Define o ID que deve ser apagado
                stmt.setInt(1, id)

                // Executa o comando de exclusão no banco de dados
                stmt.executeUpdate()
                println("Caixa d'água excluída com sucesso!")
            }
        } catch (e: SQLException) {
            // Trata falhas na remoção
            println("Erro ao excluir do banco: ${e.message}")
        } finally {
            // Encerra a conexão com o banco
            conexao.close()
        }
    }
    fun darEntradaCaixa(idCaixa: Int, quantidadeEntrada: Int) {
        val conexao = ConexaoPostgres().conectar() ?: return

        try {
            val sql = "UPDATE caixa_da_agua SET quantidade = quantidade + ? WHERE id = ?"
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, quantidadeEntrada)
                stmt.setInt(2, idCaixa)
                val linhasAfetadas = stmt.executeUpdate()
                if (linhasAfetadas > 0) {
                    println("Entrada de $quantidadeEntrada unidade(s) registrada com sucesso!")
                } else {
                    println("Caixa d'água com ID $idCaixa não encontrada!")
                }
            }
        } catch (e: SQLException) {
            println("Erro ao registrar entrada: ${e.message}")
        } finally {
            conexao.close()
        }
    }
}