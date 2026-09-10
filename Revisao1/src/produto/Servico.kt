package produto

import enums.Habilidade
import enums.Turno
import pessoas.Funcionario // Ou Pessoas.Pessoa dependendo da sua escolha
import pessoas.Cliente
import java.math.BigDecimal
import java.time.LocalDate

data class Servico(
    var funcionario: Funcionario = Funcionario(
        nome = "",
        cpf = "",
        idade = 0,
        telefone = "",
        setor = "",
        salario = BigDecimal.ZERO,
        turno = Turno.NOTURNO,
        habilidade = Habilidade.INSTALACAO
    ),
    var preco: String = "0.0",
    var dataInstalacao: LocalDate = LocalDate.of(1970, 1, 1),
    var cliente: Cliente = Cliente(
        nomeCliente = "",
        cpfCliente = "",
        idadeCliente = 0
    ),
    var dividasAbertas: Boolean = false,
    var parcelasAPagar: MutableList<Any> = mutableListOf()
)