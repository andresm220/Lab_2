import java.util.*

class OperarPostfix(private val operacion: String) {
    private val caracteres = operacion.trim().split(" ")
    private val stack = Stack<Int>()

    fun mostrarResultado(): String {
        for (char in caracteres) {
            if (char.toIntOrNull() != null) {
                stack.push(char.toInt())
            } else {
                val operandoB = stack.pop() // Notar el cambio del orden de los operandos
                val operandoA = stack.pop()

                when (char) {
                    "+" -> stack.push(sumar(operandoA, operandoB))
                    "-" -> stack.push(restar(operandoA, operandoB))
                    "*" -> stack.push(multiplicar(operandoA, operandoB))
                    "/" -> stack.push(dividir(operandoA, operandoB))
                    "^" -> stack.push(potencia(operandoA, operandoB))
                }
            }
        }
        return stack.peek().toString()
    }

    private fun sumar(a: Int, b: Int): Int = a + b
    private fun restar(a: Int, b: Int): Int = a - b
    private fun multiplicar(a: Int, b: Int): Int = a * b
    private fun dividir(a: Int, b: Int): Int = a / b
    private fun potencia(a: Int, b: Int): Int = Math.pow(a.toDouble(), b.toDouble()).toInt()
}


