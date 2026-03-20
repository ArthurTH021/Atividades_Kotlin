/**
* Instanciar: Criar algo, no caso um Objeto
* Se eu instanciar uma classe, estarei criando um objeto
* */

fun main() {
    val pessoa : Pessoa = Pessoa()//Isso aqui é um objeto vazio
    pessoa.nome = "Maria"
    pessoa.altura = 1.69
    pessoa.peso = 48.2
    pessoa.cpf = "000.000.000-01"
    pessoa.imc = pessoa.peso / (pessoa.altura*2)
    pessoa.dizerOi()

    val pessoa2 : Pessoa = Pessoa()
    pessoa2.nome = "Fulaninho"

    val mustang : Carro = Carro(velocidade = 50.0, dono = pessoa.nome)
    val fusca : Carro = Carro(velocidade = 150.0, dono = "Zé")
}
