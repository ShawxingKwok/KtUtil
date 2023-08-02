import pers.shawxingkwok.ktutil.KReadOnlyProperty
import pers.shawxingkwok.ktutil.KReadWriteProperty
import pers.shawxingkwok.ktutil.getOrPutNullable
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
    fun testDelegate(){
        var x by object : KReadWriteProperty<Any?, String>{
            override fun onDelegate(thisRef: Any?, property: KProperty<*>) {
                println("onDelegate")
            }

            override fun getValue(thisRef: Any?, property: KProperty<*>): String {
                return "x"
            }

            override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {

            }
        }

        x = "Fp"
        println(31)
    }
}