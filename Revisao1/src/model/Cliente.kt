package model

import pessoas.Pessoa
import java.math.BigDecimal

class Cliente(
    val nomeCliente: String,
    val idadeCliente: Int,
    val cpfCliente: String
) : Pessoa(
    nome = nomeCliente,
    cpf = cpfCliente,
    idade = idadeCliente){

    fun receberConta(dinheiro : BigDecimal = BigDecimal.ZERO): BigDecimal {
        return dinheiro
    }
}