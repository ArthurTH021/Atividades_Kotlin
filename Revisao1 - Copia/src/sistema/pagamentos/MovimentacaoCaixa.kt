package financeiro // ou o pacote da sua preferência (ex: pagamentos)

import java.math.BigDecimal
import java.time.LocalDateTime

data class MovimentacaoCaixa(
    var tipo: String, // "ENTRADA" ou "SAIDA"
    var valor: BigDecimal,
    var pagador: String,
    var recebedor: String,
    var dataHora: LocalDateTime = LocalDateTime.now(),
    var motivo: String,
    var responsavel: String
)