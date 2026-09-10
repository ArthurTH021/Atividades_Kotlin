package sistema.funcionario

import enums.Turno
import enums.Habilidade
import pessoas.Funcionario
import repositorio.CRUDFuncionario

fun editarFuncionario() {
    println("Digite o ID do instalador que deseja editar: ")
    val id = readln().toInt()

    println("Digite o novo nome: ")
    val nome = readln()

    println("Digite o novo CPF: ")
    val cpf = readln()

    println("Digite a nova idade: ")
    val idade = readln().toInt()

    println("Digite o novo telefone: ")
    val telefone = readln()

    println("Digite o novo setor: ")
    val setor = readln()

    println("Escolha o novo Turno: ")
    Turno.entries.forEach { turno ->
        println("[${turno.ordinal}] ${turno.name}")
    }
    println("Número do turno: ")
    val turnoIndex = readln().toInt()

    println("Escolha a nova Habilidade: ")
    Habilidade.entries.forEach { hab ->
        println("[${hab.ordinal}] ${hab.name}")
    }
    println("Número da habilidade: ")
    val habilidadeIndex = readln().toInt()

    val conexao = CRUDFuncionario()
    conexao.editar(
        Funcionario(
            nome = nome,
            cpf = cpf,
            idade = idade,
            telefone = telefone,
            setor = setor,
            turno = Turno.entries[turnoIndex],
            habilidade = Habilidade.entries[habilidadeIndex]
        ),
        id
    )
    println("Instalador editado com sucesso!")
}