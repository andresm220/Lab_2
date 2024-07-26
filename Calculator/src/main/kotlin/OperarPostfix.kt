import com.sun.org.apache.xalan.internal.lib.ExsltMath.power
import java.util.*

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
                    '+' -> stack.push(sumar(operandoA,operandoB))
                    //'-' -> stack.push(restar(operandoA,operandoB))
                    //'*' -> stack.push(multiplicar(operandoA,operandoB))
                    //'/' -> stack.push(dividir(operandoA,operandoB))
                    //'^' -> stack.push(potenca(operandoA,operandoB))

                }
            }
        }
        return stack.peek()

    }



}
