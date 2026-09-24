package model

import model.enums.Cor
import model.enums.Material
import java.math.BigDecimal

class CaixaDaAgua (
    /**
     * Marca, Modelo, Dimensão(altura, largura, profundidade), Enums.Cor, Material, Formato, .Pessoas.Instalador, Preço, Fornecedor
     * */
    val marca: String,
    val modelo: String,
    val dimensao: MutableList<Double>,
    val cor: Cor,
    val material: Material,
    val formato: String,
    val preco: BigDecimal,
)