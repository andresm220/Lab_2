/* Laboratorio 2
   Programación de Plataformas Móviles
   Andrés Mazariegos
   Esteban Cárcamo
   Nicolás Concuá
*/

fun main(args: Array<String>) {
    var seguir = true
    while (seguir) {
        println(
            """=====Calculadora========
   1. Realizar operaciones
   2. Salir
                """
        )
        val choice = readln()

        // Validación de entrada del menú
        val seleccion = choice.toIntOrNull()
        if (seleccion == null) {
            println("Por favor, ingrese un número válido.")
            continue
        }

        if (seleccion == 1) {
            var input: String
            while (true) {
                println("Ingrese la expresión infix")
                input = readln()
                if (input.any { !it.isDigit() && !it.isWhitespace() && !Conversor.notNumeric(it) }) {
                    println("Expresión no válida. Por favor, ingrese una expresión infix correcta.")
                } else {
                    break
                }
            }
            // Prueba
            val operacionPostfix = Conversor.PostFixConversion(input)
            if (operacionPostfix == "Error") {
                println("Expresión infix inválida.")
            } else {
                val resultado = OperarPostfix(operacionPostfix)
                println(resultado.mostrarResultado())
            }
        } else if (seleccion == 2) {
            seguir = false
            println("Gracias por usar la calculadora")
        } else {
            println("Número no válido. Por favor, seleccione una opción del menú.")
        }
    }
}

class Conversor {
    companion object {
        // Método para convertir infix a postfix
        fun PostFixConversion(string: String): String {
            var resultado = "" // Variable que almacenará el resultado en notación postfix
            val stack = ArrayDeque<Char>() // Pila para manejar operadores y paréntesis
            var i = 0 // Índice para recorrer la cadena de entrada

            while (i < string.length) {
                val s = string[i] // Carácter actual

                if (s.isDigit()) { // Si el carácter es un dígito
                    resultado += s // Agregar el dígito al resultado
                    // Manejar números de múltiples dígitos
                    while (i + 1 < string.length && string[i + 1].isDigit()) {
                        resultado += string[i + 1] // Agregar dígitos adicionales al resultado
                        i++ // Avanzar el índice
                    }
                    resultado += " " // Agregar un espacio después del número
                } else if (s == '(') { // Si el carácter es un paréntesis de apertura
                    stack.push(s) // Empujar el paréntesis en la pila
                } else if (s == ')') { // Si el carácter es un paréntesis de cierre
                    // Desapilar y agregar al resultado hasta encontrar un paréntesis de apertura
                    while (stack.isNotEmpty() && stack.peek() != '(') {
                        resultado += "${stack.pop()} "
                    }
                    if (stack.isNotEmpty()) stack.pop() // Eliminar el paréntesis de apertura
                } else if (notNumeric(s)) { // Si el carácter es un operador
                    // Desapilar y agregar al resultado mientras el operador en la cima de la pila tenga mayor o igual precedencia
                    while (stack.isNotEmpty() && operatorPrecedence(s) <= operatorPrecedence(stack.peek()!!)) {
                        resultado += "${stack.pop()} "
                    }
                    stack.push(s) // Empujar el operador en la pila
                }
                i++ // Avanzar el índice
            }

            // Desapilar y agregar al resultado todos los operadores restantes en la pila
            while (stack.isNotEmpty()) {
                if (stack.peek() == '(') return "Error" // Si queda un paréntesis de apertura, hay un error en la expresión
                resultado += "${stack.pop()} "
            }
            return resultado.trim() // Devolver el resultado sin espacios adicionales al final
        }

        // Método para verificar si un carácter no es un dígito
        fun notNumeric(ch: Char): Boolean = when (ch) {
            '+', '-', '*', '/', '(', ')', '^' -> true // Operadores y paréntesis no son numéricos
            else -> false // Cualquier otro carácter se considera numérico
        }

        // Método para determinar la precedencia de un operador
        fun operatorPrecedence(ch: Char): Int = when (ch) {
            '+', '-' -> 1 // Suma y resta tienen la precedencia más baja
            '*', '/' -> 2 // Multiplicación y división tienen precedencia intermedia
            '^' -> 3 // La exponenciación tiene la precedencia más alta
            else -> -1 // Cualquier otro carácter tiene precedencia inválida
        }

        // Funciónes de extensión
        fun <T> ArrayDeque<T>.push(element: T) = addLast(element)
        fun <T> ArrayDeque<T>.pop() = removeLastOrNull()
        fun <T> ArrayDeque<T>.peek() = lastOrNull()
    }
}
