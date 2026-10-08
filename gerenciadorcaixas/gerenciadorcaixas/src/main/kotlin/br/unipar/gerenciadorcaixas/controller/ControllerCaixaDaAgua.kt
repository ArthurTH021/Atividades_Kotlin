package br.unipar.gerenciadorcaixas.controller

import br.unipar.gerenciadorcaixas.model.CaixaDaAgua
import br.unipar.gerenciadorcaixas.model.Cor
import br.unipar.gerenciadorcaixas.model.Material
import br.unipar.gerenciadorcaixas.repository.CaixaDaAguaRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping


@Controller
class ControllerCaixaDaAgua(

    private val repository : CaixaDaAguaRepository
){

    @GetMapping("/ola")
    fun olaMundo(){
    }

    @PostMapping("/salvar")
    fun salvar(){
        val c =CaixaDaAgua(
            id = 0,
            marca = "Qualquer coisa",
            modelo = "Qualquer outra coisa",
            dimensao = mutableListOf(12.3, 3.5, 9.0),
            cor = Cor.AZUL_FORTE,
            material = Material.FIBRA_DE_VIDRO,
            formato = "Quadrada",
            preco = "999.99".toBigDecimal(),
        )
        repository.save(c)
    }
}
