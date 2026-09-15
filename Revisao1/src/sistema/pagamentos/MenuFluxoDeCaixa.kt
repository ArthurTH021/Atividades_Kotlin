package sistema.pagamentos

import utils.Validacoes
import repositorio.CRUDFluxoCaixa
import java.math.BigDecimal

fun menuFluxoCaixa() {
    val crud = CRUDFluxoCaixa()

    while (true) {
        println("\n--- MÓDULO: FLUXO DE CAIXA / PAGAMENTOS ---")
        println("[1] Ver Extrato (Listar Movimentações)")
        println("[2] Registrar Movimentação Manual (Entrada/Saída)")
        println("[0] Voltar ao Menu Principal")

        val opcao = Validacoes.lerInteiro("Escolha uma opção: ")

        when (opcao) {
            1 -> crud.listarMovimentacoes()
            2 -> {
                println("\n--- REGISTRAR MOVIMENTAÇÃO ---")
                println("[1] ENTRADA")
                println("[2] SAÍDA")
                val tipoOpcao = Validacoes.lerInteiro("Escolha o tipo: ")
                val tipo = if (tipoOpcao == 1) "ENTRADA" else "SAIDA"

                val valorDouble = Validacoes.lerDouble("Digite o valor (R$): ")
                val valor = BigDecimal.valueOf(valorDouble)
                val pagador = Validacoes.lerTextoObrigatorio("Nome do pagador: ")
                val recebedor = Validacoes.lerTextoObrigatorio("Nome do recebedor: ")
                val motivo = Validacoes.lerTextoObrigatorio("Motivo / Descrição: ")
                val responsavel = Validacoes.lerTextoObrigatorio("Responsável pela transação: ")

                val novaMovimentacao = MovimentacaoCaixa(
                    tipo = tipo,
                    valor = valor,
                    pagador = pagador,
                    recebedor = recebedor,
                    motivo = motivo,
                    responsavel = responsavel
                )
                crud.registrarMovimentacao(novaMovimentacao)
            }
            0 -> break
            else -> println("Opção inválida!")
        }
    }
}