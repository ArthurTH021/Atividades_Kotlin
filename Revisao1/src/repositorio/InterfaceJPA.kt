package repositorio

//T é uma classe Genérica
//A interface é um contrato
//As funções são clausulas
//Nossas classes que herdarem essa interface precisam implementar as funções
interface InterfaceJPA<T> {
    //"item" é o meu parâmetro genérico
    fun salvar(item: T)
    fun listar()
    fun editar(item: T, id : Int)
    fun excluir(id : Int)
}