import com.sun.org.apache.xalan.internal.lib.ExsltMath.power
import java.util.*
import  kotlin.math.*
class OperarPostfix(private val operacion:String) {
    private val caracteres = operacion.trim().replace(" ","").toList()
    private val stack = Stack<String>()
    private var operandoA = 0
    private var operandoB = 0

    fun mostrarResultado(): String{

        //validacion si es un numero o no
        for(char in caracteres){
            if(char.digitToIntOrNull() != null){
                stack.push(char.toString())
            }else{
                operandoA = stack.pop().toInt()
                operandoB = stack.pop().toInt()

                when (char){
                    '+' -> stack.push(sumar(operandoA,operandoB).toString())
                    '-' -> stack.push(restar(operandoA,operandoB).toString())
                    '*' -> stack.push(multiplicar(operandoA,operandoB).toString())
                    '/' -> stack.push(dividir(operandoA,operandoB).toString())
                    '^' -> stack.push(potencia(operandoA,operandoB).toString())

                }
            }
        }
        return stack.peek()

    }

    private fun sumar(a: Int, b: Int) : Int{return a+b}
    private fun restar(a: Int, b: Int) : Int{return b-a}
    private fun multiplicar(a: Int, b: Int) : Int{return a*b}
    private fun dividir(a: Int, b: Int) : Int{return b/a}
    private fun potencia (a: Int, b: Int) : Int{return Math.pow(b.toDouble(), a.toDouble()).toInt()}

}
