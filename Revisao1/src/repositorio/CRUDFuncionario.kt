package repositorio

import pessoas.Funcionario
import java.sql.SQLException

class CRUDFuncionario : InterfaceJPA<Funcionario>, ConexaoPostgres() {

    override fun salvar(item: Funcionario) {
        val conexao = conectar() ?: return
        val sql = "INSERT INTO funcionario (nome_funcionario, telefone_funcionario, setor, cpf_funcionario, idade, salario, turno, habilidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, item.nome)
                stmt.setString(2, item.telefone)
                stmt.setString(3, item.setor)
                stmt.setString(4, item.cpf)
                stmt.setInt(5, item.idade)
                stmt.setBigDecimal(6, item.salario)

                // Tratamento seguro para campos nulos usando ?.name ou setNull
                if (item.turno != null) {
                    stmt.setString(7, item.turno?.name)
                } else {
                    stmt.setNull(7, java.sql.Types.VARCHAR)
                }

                if (item.habilidade != null) {
                    stmt.setString(8, item.habilidade?.name)
                } else {
                    stmt.setNull(8, java.sql.Types.VARCHAR)
                }

                stmt.executeUpdate()
            }
            println("Funcionário cadastrado com sucesso!")
        } catch (e: SQLException) {
            println("Erro ao cadastrar funcionário: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    override fun listar() {
        val conexao = conectar() ?: return
        val sql = "SELECT * FROM funcionario"

        try {
            conexao.createStatement().use { stmt ->
                val resultado = stmt.executeQuery(sql)
                while (resultado.next()) {
                    println(
                        "ID: ${resultado.getInt("id")} | " +
                                "Nome: ${resultado.getString("nome_funcionario")} | " +
                                "Tel: ${resultado.getString("telefone_funcionario")} | " +
                                "Setor: ${resultado.getString("setor")} | " +
                                "CPF: ${resultado.getString("cpf_funcionario")} | " +
                                "Idade: ${resultado.getInt("idade")} | " +
                                "Salário: R$ ${resultado.getBigDecimal("salario")} | " +
                                "Turno: ${resultado.getString("turno") ?: "Não informado"} | " +
                                "Habilidade: ${resultado.getString("habilidade") ?: "Não informada"}"
                    )
                }
            }
        } catch (e: SQLException) {
            println("Erro ao listar funcionários: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    override fun editar(item: Funcionario, id: Int) {
        val conexao = conectar() ?: return
        val sql = "UPDATE funcionario SET nome_funcionario = ?, telefone_funcionario = ?, setor = ?, cpf_funcionario = ?, idade = ?, salario = ?, turno = ?, habilidade = ? WHERE id = ?"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, item.nome)
                stmt.setString(2, item.telefone)
                stmt.setString(3, item.setor)
                stmt.setString(4, item.cpf)
                stmt.setInt(5, item.idade)
                stmt.setBigDecimal(6, item.salario)

                if (item.turno != null) {
                    stmt.setString(7, item.turno?.name)
                } else {
                    stmt.setNull(7, java.sql.Types.VARCHAR)
                }

                if (item.habilidade != null) {
                    stmt.setString(8, item.habilidade?.name)
                } else {
                    stmt.setNull(8, java.sql.Types.VARCHAR)
                }

                stmt.setInt(9, id)
                stmt.executeUpdate()
                println("Funcionário editado com sucesso!")
            }
        } catch (e: SQLException) {
            println("Erro ao editar funcionário: ${e.message}")
        } finally {
            conexao.close()
        }
    }

    override fun excluir(id: Int) {
        val conexao = conectar() ?: return
        val sql = "DELETE FROM funcionario WHERE id = ?"

        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeUpdate()
                println("Funcionário excluído com sucesso!")
            }
        } catch (e: SQLException) {
            println("Erro ao excluir funcionário: ${e.message}")
        } finally {
            conexao.close()
        }
    }
}