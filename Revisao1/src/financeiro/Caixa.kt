package financeiro

import java.math.BigDecimal

class Caixa(
    var saldo : BigDecimal//NÃO POSSO MEXER VIA CÓDIGO, SOMENTE NO BANCO
){

    fun receita(valor : BigDecimal) : BigDecimal {
        return valor

    }

    fun despesa(valor : BigDecimal) : BigDecimal {
        return valor.multiply("-1".toBigDecimal())

    }

}
