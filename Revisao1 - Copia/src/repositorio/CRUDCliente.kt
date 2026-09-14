package repositorio

import pessoas.Cliente

class CRUDCliente : ConexaoPostgres() {

    fun inserir(cliente: Cliente) {
        val conexao = conectar() ?: return
        val sql = "INSERT INTO cliente (nome_cliente, idade_cliente, cpf_cliente) VALUES (?, ?, ?)"
        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, cliente.nomeCliente)
                stmt.setInt(2, cliente.idadeCliente)
                stmt.setString(3, cliente.cpfCliente)
                stmt.executeUpdate()
            }
        } finally {
            conexao.close()
        }
    }

    fun listar(): List<Cliente> {
        val lista = mutableListOf<Cliente>()
        val conexao = conectar() ?: return lista
        val sql = "SELECT * FROM cliente"
        try {
            conexao.createStatement().use { stmt ->
                val resultado = stmt.executeQuery(sql)
                while (resultado.next()) {
                    val cliente = Cliente(
                        nomeCliente = resultado.getString("nome_cliente"),
                        idadeCliente = resultado.getInt("idade_cliente"),
                        cpfCliente = resultado.getString("cpf_cliente")
                    )
                    lista.add(cliente)
                }
            }
        } finally {
            conexao.close()
        }
        return lista
    }

    fun atualizar(cpfBusca: String, novoNome: String): Boolean {
        val conexao = conectar() ?: return false
        val sql = "UPDATE cliente SET nome_cliente = ? WHERE cpf_cliente = ?"
        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, novoNome)
                stmt.setString(2, cpfBusca)
                return stmt.executeUpdate() > 0
            }
        } finally {
            conexao.close()
        }
    }

    fun deletar(cpfBusca: String): Boolean {
        val conexao = conectar() ?: return false
        val sql = "DELETE FROM cliente WHERE cpf_cliente = ?"
        try {
            conexao.prepareStatement(sql).use { stmt ->
                stmt.setString(1, cpfBusca)
                return stmt.executeUpdate() > 0
            }
        } finally {
            conexao.close()
        }
    }
}