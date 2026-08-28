package sistema.pagamentos

import repositorio.salvarMovimentacao
import java.time.LocalDate

fun pagar(){
    println("Digite o contexto: ")
    val contexto = readln() //FAZER UM ENUM NO LUGAR DESSA VAL
    println("Digite um valor: ")
    val valor = readln().toBigDecimal()//PRECISA VALIDAR
    val data = LocalDate.now()//PEGA DIA E HORA ATUAL

    salvarMovimentacao(contexto, valor, data)

}