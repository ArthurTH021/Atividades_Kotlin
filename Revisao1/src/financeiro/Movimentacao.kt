package financeiro

import java.math.BigDecimal
import java.time.LocalDate

class Movimentacao (
    val valor : BigDecimal,
    val dataMovimentacao : LocalDate,
    val contexto : String,
    //val pessoa : Pessoa //PRECISA FAZER DEPOIS
){
    fun movimentar(valor : BigDecimal, data: String){
        //SALVAR NO BANCO
    }
}




