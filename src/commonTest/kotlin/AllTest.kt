import pers.shawxingkwok.ktutil.getOrPutNullable
import kotlin.test.Test

class AllTest {
    @Test
    fun start(){
        var map = mutableMapOf("a" to null, "b" to 1)
        map.getOrPut("a"){ 2 } // 2
        map.let(::println)                    // {a=2, b=1}

        map = mutableMapOf("a" to null, "b" to 1)
        map.getOrPutNullable("a"){ 2 } // null
        map.let(::println)                            // {a=null, b=1}
    }
}