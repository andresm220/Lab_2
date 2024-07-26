class OperarPostfix(private val operacion:String) {
    private val caracteres = operacion.trim().replace(" ","").toList()

    fun mostrar(){
        for (i in caracteres) {
            print(i)
        }
    }


}
