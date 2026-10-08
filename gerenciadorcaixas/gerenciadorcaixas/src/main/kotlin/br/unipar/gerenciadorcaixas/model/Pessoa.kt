package br.unipar.gerenciadorcaixas.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Pessoa(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id : Long = 0L,
    val cpf : Int = 0
)