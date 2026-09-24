package utils

object Validacoes {

//      Valida um CPF utilizando Expressão Regular (Regex).
//      Por que usar Regex? Garante o padrão exato de caracteres antes de ir para o banco de dados.
//
    fun validarCpf(cpf: String): Boolean {
        val regex = Regex("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$")
        return regex.matches(cpf)
    }

//      Lê um número Inteiro com segurança do console (à prova de falhas humanas).
//      Usa 'readlnOrNull()' para evitar crash se o usuário apertar Enter vazio.
//      Usa 'toIntOrNull()' (tipo nullable) para tentar converter; se falhar, retorna null em vez de quebrar o app.
//      Usa o operador Elvis '?:' para tratar o nulo caso a conversão dê errado.
//
    fun lerInteiro(mensagem: String): Int {
        while (true) { // Loop infinito que só quebra quando o usuário digitar um número válido
            print(mensagem)
            val entrada = readlnOrNull() // Lê a linha de forma segura aceitando nulos

            // toIntOrNull() converte para Int ou retorna null se o usuário digitar letras
            val numero = entrada?.toIntOrNull()

            if (numero != null && numero >= 0) {
                return numero // Retorna apenas se for um número inteiro válido e não negativo
            }
            println("Entrada inválida! Digite apenas números inteiros válidos.")
        }
    }


//      Lê um número Decimal (Double) com segurança do console.
    fun lerDouble(mensagem: String): Double {
        while (true) {
            print(mensagem)
            val entrada = readlnOrNull()?.replace(",", ".") // Tratamento amigável para vírgula
            val numero = entrada?.toDoubleOrNull() // Tenta converter para Double de forma segura

            if (numero != null && numero >= 0.0) {
                return numero
            }
            println("Entrada inválida! Digite um valor numérico válido.")
        }
    }

//      Lê um texto obrigatório garantindo que o usuário não deixe o campo vazio ou apenas com espaços.
    fun lerTextoObrigatorio(mensagem: String): String {
        while (true) {
            print(mensagem)
            val entrada = readlnOrNull()?.trim() // Remove espaços em branco nas pontas

            if (!entrada.isNullOrEmpty()) { // Valida se não é nulo nem vazio
                return entrada
            }
            println("O campo não pode ficar vazio!")
        }
    }
}