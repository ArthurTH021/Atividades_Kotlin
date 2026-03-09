fun main() {
    colecoesMapas()
}

fun intervalos(){
    for(i in 1..10) {
        print(" $i")
    }
    //Intervalos: Para dizer que você quer ir de um número a outro basta digitar
    //O primeiro número seguido de .. (dois pontos) e depois o segundo número
    //A palavra chave IN serve para indicar que o intervalo será representado
    //pela variavel "i"

    //Contagem Regressiva
    println("")
    println("Contagem Regressiva... \n")
    val x = 5
    for(x in 10 downTo 1) {
        print(" $x")
    }
    //Para percorrer um intervalo inverso use o DownTo
    //Step são os passos que um intervalo pula

    /**
     * um bloco de código começa com { (abrir chaves)
     * e termina com } (fechar chaves)
     */

    val numeros : IntRange = 1..10
}
fun quando(){
    println("\nDigite um numero de 1 a 7")
    val dia : Int = readln().toInt() //readln() sempre volta o tipo string

    //A minha condição precisa ser do mesmo tipo(tipagem) das opções
    when (dia){
        1 -> {println("Hoje é domingo dia mais lindo!")
            println("Esse é um exemplo de WHEN com mais de uma linha")
        }
        2 -> println("Hoje é segunta dia de labuta!")
        3 -> println("Hoje é terça dia de mesa")
        4 -> println("Hoje é quarta dia de sofá")
        5 -> println("Hoje é quinta ta na mica")
        6 -> println("Hoje é sexta, sem dó da noite")
        7 -> println("Hoje é sábado, moia o gato")
    }
    //Operador Lambda ->
}
fun colecoesArrays(){
    println("Array de String: ")
    val alfabeto = arrayOf("A", "B", "C", "D", "E")

    println("Array de Inteiros: ")
    val numerosPares = arrayOf(2, 4, 6, 8, 10)

    for (i in 0 until numerosPares.size) {
        println("Letra do alfabeto : ${alfabeto[i]}; index : ${alfabeto.indexOf(alfabeto[i])}")
        if(i < numerosPares.size) {}
        print("${numerosPares[i]}")
    }
}
fun colecoesMapas(){
    //estrutura correta
    val mapa = mapOf<Int, String>(
        0 to "Zero",
        //0: chave, "Zero": valor

    )//fim do mapa
    println("Valor do mapa ${mapa[0]}")

    //exemplo com string sendo chave
    val mapa3 = mapOf<String, Int>(
        "Zero" to 0,
    )//fim do mapa
    println("Valor do segundo mapa ${mapa3["Zero"]}")

    //estrutura com 2 chaves(errado)
    val mapa2 = mapOf<Int, String>(
        0 to "Zero",
        0 to "Sero"
        //não pode ter mais de um valor por chave, pois o valor novo substitui o antigo
    )//fim do mapa
    println("Valor do mapa ${mapa2[0]}")

    //Acessar cada chave (anonimato)
    var mapaMutavel = mutableMapOf<String, Int>(
        "Zero" to 0,
        "Tres" to 3
    )
    mapaMutavel["Zero"] = 1 //mudar valor de chave ja definido
    mapaMutavel["Dois"] = 2 //criar chave com valor

    println("Adicione uma chave")//criando chave com codigo
    val novaChave = readln()
    mapaMutavel[novaChave] = 10 //adiciona uma nova chave e valor para o Mapa
    println("A nova chave tem o valor ${mapaMutavel[novaChave]}")

    println("Digite a chave que deseja remover")//remover valor da chave com codigo
    val chave = readln()
    println("Valor do mapa Removido! Valor antigo: ${mapaMutavel.remove(chave)}")

    mapaMutavel.forEach{ numeroExtenso, numeroDigito ->
        println("A chave é $numeroExtenso, O valor da chave acima é $numeroDigito")

    }
}
fun colecoesListas(){
    val frutas = listOf<String>("Banana", "Maçã", "Uva", "Abacate")
    println("As minhas frutas são: $frutas")
    if(frutas.contains("Banana")){//retorna true se tem o elemento
        println("\nTem banana")
    }
    println("\nMinha primeira fruta ${frutas.first()}")//retorna o primeiro elemento da lista
    println("\nMinha última fruta ${frutas.last()}")//retorna o último elemento da lista

    //Iterar por todos os elementos
    frutas.forEach{ fruta -> //para renomear coloque o nome seguido de lambda(->)
        println(fruta)//IT é um objeto anônimo
    }

    val numeros = mutableListOf<Double>(10.1, 4.54, 3.51, 0.007)
    numeros.add(0.00000001)//adicionar um novo elemento a lista
    numeros.remove(10.1)//remove um elemento da lista
    numeros.sort()//organiza a lista em ordem crescente ou alfabética
    print("\nOs números são $numeros")
}
