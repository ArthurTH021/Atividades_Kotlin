package sistema

import sistema.caixadeagua.cadastrarNovaCaixa
import sistema.caixadeagua.editarCaixa
import sistema.caixadeagua.excluirCaixa
import sistema.caixadeagua.listarCaixa

fun menuInicial() {
    do {
        println("[0] Sair")
        println("[1] Cadastrar caixa de água")
        println("[2] Editar caixa de água")
        println("[3] Listar caixas de água")
        println("[4] Excluir caixa de água")
        println("=====================================")
        print("Digite sua Opção: ")

        //SE FOR VALOR NUMÉRICO VÁLIDO SERÁ CONVERTIDO, SE NÃO, SERÁ NULO
        //PORÉM VALORES NULOS OM O ELVIS OPERATOR (?:) TEM UM VALOR PADRÃO
        val op : Int = readln().toIntOrNull() ?: 10

            when (op) {
                1 -> cadastrarNovaCaixa()
                2 -> editarCaixa()
                3 -> listarCaixa()
                4 -> excluirCaixa()
                0 -> {
                    println("Adeus")
                    break
                }
                else -> println("Opção inválida!")
            }//FIM DO WHEN
    } while (true)//FIM DO DO-WHILE

}//FIM DA FUNÇÃO

//        MODELO DE REGEX PARA VALIDAR 1 DIGITO
//        val regex = Regex("\\d")
//
//        MODELO DE REGEX PARA VALIDAR EMAIL
//        val validaEmail = Regex("""^[a-zA-Z0-9]+.@[a-z]+(.com|.com.br)$""")
//
//        val op = readln()
//        validaEmail.find(op)//EXEMPLO MAL FEITO DE VALIDAÇÃO DE EMAIL
//
//        //Se for um dígito será VERDADEIRO
//        regex.matches(op)
//        if (regex.matches(op)) {
//        }//FIM DO IF