/* Laboratorio 2
   Programación de Plataformas Móviles
   Andrés Mazariegos
   Esteban Cárcamo
   Nicolás Concuá
*/

fun main(args: Array<String>) {
    var seguir= true
    while(seguir){

        println("""=====Calculadora========
   1.Realizar operaciones
   2.Salir
                """)
        val choice = readln()
        val seleccion = choice.toInt()
        if(seleccion==1){
            println("Ingrese la expresion infix")
            val input = readln()
            // Prueba
            val operacionPostfix = Conversor.PostFixConversion(input)
            val resultado = OperarPostfix(operacionPostfix)
            println(resultado.mostrarResultado())
        }else if(seleccion ==2){
            seguir=false
            println("Gracias por usar la calculadora")
        }

    }


}

class Conversor {
    companion object {
        // Método para convertir infix a postfix
        fun PostFixConversion(string: String): String {
            // Variable inmutable donde caera el resultado postfix
            var resultado = ""
            // Creamos la pila
            val stack = ArrayDeque<Char>()

            for (s in string) {
                if (!notNumeric(s)) {
                    resultado += s
                } else if (s == '(') {
                    stack.push(s)
                } else if (s == ')') {
                    while (stack.isNotEmpty() && stack.peek() != '(') {
                        resultado += " ${stack.pop()}"
                    }
                    if (stack.isNotEmpty()) stack.pop() // Eliminar '('
                } else {
                    while (stack.isNotEmpty() && operatorPrecedence(s) <= operatorPrecedence(stack.peek()!!)) {
                        resultado += " ${stack.pop()}"
                    }
                    stack.push(s)
                    resultado += " "
                }
            }
            // Añadimos los operadores restantes en la pila
            while (stack.isNotEmpty()) {
                if (stack.peek() == '(') return "Error"
                resultado += " ${stack.pop()}"
            }
            return resultado.trim()
        }
    }
}

// Creamos un método para validar que un caracter sea numérico
private fun notNumeric(ch: Char): Boolean = when (ch) {
    '+', '-', '*', '/', '(', ')', '^' -> true
    else -> false
}

// Creamos función para verificar la precedencia de cada carácter en el infix
private fun operatorPrecedence(ch: Char): Int = when (ch) {
    '+', '-' -> 1
    '*', '/' -> 2
    '^' -> 3
    else -> -1
}

// Funciones de extensión
// Función para agregar a la pila
fun <T> ArrayDeque<T>.push(element: T) = addLast(element)

// Función para sacar de la pila
fun <T> ArrayDeque<T>.pop() = removeLastOrNull()

// Función para obtener el último elemento de la pila sin removerlo
fun <T> ArrayDeque<T>.peek() = lastOrNull()
