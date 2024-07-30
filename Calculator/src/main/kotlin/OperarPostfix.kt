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
                if(char=='√'){ //Agregar primero el numero y luego la raiz
                    operandoA = stack.pop().toInt()
                    stack.push(raiz(operandoA).toString())
                }
                else if(char == 'e'){
                    operandoA = stack.pop().toInt()
                    stack.push(exponencial(operandoA).toString())
                }
                else {

                    operandoA = stack.pop().toInt()
                    operandoB = stack.pop().toInt()

                    when (char) {
                        '+' -> stack.push(sumar(operandoA, operandoB).toString())
                        '-' -> stack.push(restar(operandoA, operandoB).toString())
                        '*' -> stack.push(multiplicar(operandoA, operandoB).toString())
                        '/' -> stack.push(dividir(operandoA, operandoB).toString())
                        '^' -> stack.push(potencia(operandoA, operandoB).toString())

                    }
                }
            }
        }
        return stack.peek()

    }

    private fun sumar(a: Int, b: Int) : Int{return a+b}  //(3+5)
    private fun restar(a: Int, b: Int) : Int{return b-a}// (12-10)
    private fun multiplicar(a: Int, b: Int) : Int{return a*b} //(5*2)
    private fun dividir(a: Int, b: Int) : Int{return b/a} // (6/3)
    private fun potencia (a: Int, b: Int) : Int{return Math.pow(b.toDouble(), a.toDouble()).toInt()}//(6^3)
    private fun raiz (a: Int) : Int{return Math.sqrt(a.toDouble()).toInt()} // (4 √)
    private fun exponencial(a: Int) : Int{ return exp(a.toDouble()).toInt()} // (4e)
}
