import java.util.*
import kotlin.concurrent.thread

fun main() {
    val filaDeEspera: Queue<String> = LinkedList()
    val historicoPassagem: Stack<String> = Stack()
    var semaforoAberto = false

    println("")

    thread {
        var contadorCarros = 1
        while (true) {
            Thread.sleep((500..2000).random().toLong())
            val nomeCarro = "Carro #$contadorCarros"
            synchronized(filaDeEspera) {
                filaDeEspera.add(nomeCarro)
            }
            println("$nomeCarro chegou e entrou na fila.")
            contadorCarros++
        }
    }

    thread {
        while (true) {
            semaforoAberto = true
            println("\nSemáforo Aberto!\n")

            val tempoAberto = System.currentTimeMillis()
            while (System.currentTimeMillis() - tempoAberto < 5000) {
                synchronized(filaDeEspera) {
                    if (filaDeEspera.isNotEmpty()) {
                        val carroQuePassou = filaDeEspera.poll()
                        historicoPassagem.push(carroQuePassou)
                        println("$carroQuePassou passou pelo cruzamento.")
                    }
                }
                Thread.sleep(800)//tempo pro carro atravesssar
            }
            semaforoAberto = false
            println("\nSemáforo fechado!")
            println("Carros aguardando: ${filaDeEspera.size}")
            Thread.sleep(10000)
        }
    }
}