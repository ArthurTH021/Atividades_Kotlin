data class Carro (val velocidade: Double,
                  val aceleracao:Double,
                  val freio: Double,
                  val consumo: Double,
                  val tanque: Double,
                  val resistencia: Double,
                  val piloto: Piloto
)
//aceleração classificada de 0(baixa) a 10(alta), freio também 0(freio ruim) a 10(freio perfeito), assim também como o tanque, e a resistencia