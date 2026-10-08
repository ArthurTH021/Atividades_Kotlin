package br.unipar.gerenciadorcaixas.model

class Cliente(
    id : Long,
    cpf : Int,
    val nome : String = ""
): Pessoa(id, cpf)