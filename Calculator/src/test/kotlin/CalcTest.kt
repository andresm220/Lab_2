import kotlin.test.Test
import kotlin.test.assertEquals
class CalcTest {



        @Test
        fun testPostFixConversion() {
            assertEquals("2 3 +", Conversor.PostFixConversion("2 + 3"))
            assertEquals("2 3 4 * +", Conversor.PostFixConversion("2 + 3 * 4"))
            assertEquals("2 3 + 4 *", Conversor.PostFixConversion("(2 + 3) * 4"))
            assertEquals("2 3 4 + *", Conversor.PostFixConversion("2 * (3 + 4)"))
            assertEquals("2 3 ^", Conversor.PostFixConversion("2 ^ 3"))
            assertEquals("Error", Conversor.PostFixConversion("2 + (3 * 4"))
        }

        @Test
        fun testNotNumeric() {
            assertEquals(true, Conversor.notNumeric('+'))
            assertEquals(true, Conversor.notNumeric('-'))
            assertEquals(false, Conversor.notNumeric('1'))
            assertEquals(true, Conversor.notNumeric('('))
            assertEquals(false, Conversor.notNumeric('a'))
        }

        @Test
        fun testOperatorPrecedence() {
            assertEquals(1, Conversor.operatorPrecedence('+'))
            assertEquals(2, Conversor.operatorPrecedence('*'))
            assertEquals(3, Conversor.operatorPrecedence('^'))
            assertEquals(-1, Conversor.operatorPrecedence('a'))
        }

    



    }





