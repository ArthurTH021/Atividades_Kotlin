val pilha = mutableMapOf<Int, String>()
var nivel = 0
var aux = ""

fun main() {

    val regex = Regex("^[0-9+\\-*/() ]+$")

    println("\nDigite aqui a sua expressão matemática:")
    val expOriginal = readln()

    val exp = expOriginal.replace(" ", "")

    // Validação
    if (!expOriginal.matches(regex) || exp.length < 9) {
        println("Expressão negada. Deve conter apenas números/operadores e no mínimo 9 caracteres.")
        return
    } else {
        println("Expressão aceita.")
    }

    // Montando níveis (sua lógica)
    exp.forEach { c ->
        val nivelAtual = nivel
        prioridade(c)

        if (nivel == nivelAtual) {
            aux += c
            pilha[nivel] = aux
        } else {
            aux = ""
        }
    }

    // Mostrar pilha
    pilha.forEach { (i, s) ->
        println("Nível da pilha: $i -> $s")
    }

    // Resolver expressão
    try {
        val resultado = avaliarExpressao(exp)
        println("\nResultado final: $resultado")
    } catch (e: Exception) {
        println("Erro ao calcular expressão.")
    }
}

// Controle de nível (parênteses)
fun prioridade(c: Char): Int {
    when (c) {
        '(' -> nivel++
        ')' -> nivel--
    }
    return nivel
}

// Avaliação da expressão (Shunting Yard simplificado)
fun avaliarExpressao(exp: String): Double {

    val valores = mutableListOf<Double>()
    val operadores = mutableListOf<Char>()

    var i = 0

    while (i < exp.length) {

        val c = exp[i]

        // Número (pode ter mais de um dígito)
        if (c.isDigit()) {
            var numero = ""
            while (i < exp.length && exp[i].isDigit()) {
                numero += exp[i]
                i++
            }
            valores.add(numero.toDouble())
            i--
        }

        // Parênteses
        else if (c == '(') {
            operadores.add(c)
        } else if (c == ')') {
            while (operadores.isNotEmpty() && operadores.last() != '(') {
                aplicarOperacao(valores, operadores)
            }
            operadores.removeAt(operadores.lastIndex) // remove '('
        }

        // Operadores
        else if (c in listOf('+', '-', '*', '/')) {
            while (
                operadores.isNotEmpty() &&
                precedencia(operadores.last()) >= precedencia(c)
            ) {
                aplicarOperacao(valores, operadores)
            }
            operadores.add(c)
        }

        i++
    }

    // Finalizar
    while (operadores.isNotEmpty()) {
        aplicarOperacao(valores, operadores)
    }

    return valores.last()
}

// Define prioridade
fun precedencia(op: Char): Int {
    return when (op) {
        '+', '-' -> 1
        '*', '/' -> 2
        else -> 0
    }
}

// Aplica operação
fun aplicarOperacao(valores: MutableList<Double>, operadores: MutableList<Char>) {
    val b = valores.removeAt(valores.lastIndex)
    val a = valores.removeAt(valores.lastIndex)
    val op = operadores.removeAt(operadores.lastIndex)

    val resultado = when (op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> a / b
        else -> 0.0
    }

    valores.add(resultado)
}