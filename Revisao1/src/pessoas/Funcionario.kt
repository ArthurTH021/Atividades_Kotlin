package pessoas

import enums.Habilidade
import enums.Turno
import financeiro.Movimentacao
import java.math.BigDecimal
import java.time.LocalDate

class Funcionario (
    nome: String,
    cpf: String,
    idade: Int,
    val telefone: String, // Adicionado aqui
    val setor: String,    // Adicionado aqui
    val salario: BigDecimal = "2000".toBigDecimal(),
    val turno: Turno? = null,
    val habilidade: Habilidade? = null
) : Pessoa(nome, cpf, idade){

    override fun receberConta(valor: BigDecimal, conta: Pessoa): Movimentacao {
        return Movimentacao(
            valor = -valor,
            dataMovimentacao = LocalDate.now(),
            contexto = "Pagamento de conta",
            pessoa = conta
        )
    }
}