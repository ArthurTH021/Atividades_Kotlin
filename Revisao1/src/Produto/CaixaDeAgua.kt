package Produto

import Enums.Cor
import Enums.Habilidade
import Enums.Turno
import Pessoas.Instalador
import java.math.BigDecimal

class CaixaDaAgua {
    /**
     * Marca, Modelo, Dimensão(altura, largura, profundidade), Enums.Cor, Material, Formato, .Pessoas.Instalador, Preço, Fornecedor
     * */
    var marca : String = "nome da marca"
    var modelo : String = "nome da modelo"
    var dimensao : MutableList<Double> = mutableListOf(0.0, 0.0, 0.0)
    var cor : Cor = Cor.BRANCO
    var material : String = "nome da material"
    var formato : String = "tipo do formato"
    var instalador : Instalador = Instalador(
        nome = "",
        cpf = "",
        idade = 0,
        salario = BigDecimal.ZERO,
        turno = Turno.NOTURNO,
        habilidade = Habilidade.INSTALACAO
    )
    var fornecedor : String = "nome do fornecedor"
    var preco : BigDecimal = BigDecimal.ZERO
}