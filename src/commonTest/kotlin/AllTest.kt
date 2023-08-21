import pers.shawxingkwok.ktutil.*
import kotlin.reflect.KProperty
import kotlin.test.Test

class AllTest {
    @Test
    fun testGetOrPutNullable(){
        var map = mutableMapOf("a" to null, "b" to 1)
        map.getOrPut("a"){ 2 } // 2
        map.let(::println)                    // {a=2, b=1}

        map = mutableMapOf("a" to null, "b" to 1)
        map.getOrPutNullable("a"){ 2 } // null
        map.let(::println)                            // {a=null, b=1}
    }

    @Test
    fun testDelegate(){}

    @Test
    fun testToOrder(){
        1.toOrder().let(::println)
        11.toOrder().let(::println)
        21.toOrder().let(::println)

        2.toOrder().let(::println)
        12.toOrder().let(::println)
        22.toOrder().let(::println)
    }
}