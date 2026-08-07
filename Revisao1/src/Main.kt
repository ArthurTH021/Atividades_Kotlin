import Enums.Habilidade
import Enums.Turno
import Financeiro.Banco
import Financeiro.Caixa
import Pessoas.Instalador
import Pessoas.Pessoa
import java.time.LocalDate

fun main(){
    println("Você vai mexer no banco?")
    val instalador = Instalador(
        nome = "fulano",
        cpf = "001.000.000-00",
        idade = 19,
        salario = "2000".toBigDecimal(),
        turno = Turno.MATUTINO,
        habilidade = Habilidade.INSTALACAO
    )

    val banco = Caixa(
        "5000".toBigDecimal()
    )

    println("Dinheiro atual na conta: ")
    println(banco.saldo)

    val transacao = instalador.receberConta("2000".toBigDecimal(), instalador)
    banco.saldo += transacao.dinheiro
    println("Transação feita, instalador pago!!")
    println(banco.saldo)
}