package br.unipar.gerenciadorcaixas.model

import java.math.BigDecimal

class Funcionario (
    id : Long,
    cpf : Int,
    val salario : BigDecimal = BigDecimal.ZERO,
): Pessoa(id, cpf)