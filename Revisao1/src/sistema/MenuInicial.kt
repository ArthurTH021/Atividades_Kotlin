package sistema

import sistema.caixadeagua.cadastrarNovaCaixa
import sistema.caixadeagua.editarCaixa
import sistema.caixadeagua.excluirCaixa
import sistema.caixadeagua.listarCaixa
import sistema.cliente.cadastrarCliente
import sistema.cliente.editarCliente
import sistema.cliente.excluirCliente
import sistema.cliente.listarCliente
import sistema.instalador.cadastrarInstalador
import sistema.instalador.editarInstalador
import sistema.instalador.excluirInstalador
import sistema.instalador.listarInstalador

fun menuInicial() {
    do {
        println("[0] Sair")
        println("[1] Gerenciar Caixa de Água")
        println("[2] Gerenciar Cliente")
        println("[3] Gerenciar Instalador")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> menuCaixaDeAgua()
            2 -> menuCliente()
            3 -> menuInstalador()
            0 -> {
                println("Adeus")
                break
            }
            else -> println("Opção inválida!")
        }
    } while (true)
}

fun menuCaixaDeAgua() {
    do {
        println("\n=== MENU CAIXA DE ÁGUA ===")
        println("[0] Voltar")
        println("[1] Cadastrar caixa de água")
        println("[2] Editar caixa de água")
        println("[3] Listar caixas de água")
        println("[4] Excluir caixa de água")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> cadastrarNovaCaixa()
            2 -> editarCaixa()
            3 -> listarCaixa()
            4 -> excluirCaixa()
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}

fun menuCliente() {
    do {
        println("\n=== MENU CLIENTE ===")
        println("[0] Voltar")
        println("[1] Cadastrar cliente")
        println("[2] Editar cliente")
        println("[3] Listar clientes")
        println("[4] Excluir cliente")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> cadastrarCliente()
            2 -> editarCliente()
            3 -> listarCliente()
            4 -> excluirCliente()
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}

fun menuInstalador() {
    do {
        println("\n=== MENU INSTALADOR ===")
        println("[0] Voltar")
        println("[1] Cadastrar instalador")
        println("[2] Editar instalador")
        println("[3] Listar instaladores")
        println("[4] Excluir instalador")
        println("=====================================")
        print("Digite sua Opção: ")

        val op : Int = readln().toIntOrNull() ?: 10

        when (op) {
            1 -> cadastrarInstalador()
            2 -> editarInstalador()
            3 -> listarInstalador()
            4 -> excluirInstalador()
            0 -> break
            else -> println("Opção inválida!")
        }
    } while (true)
}