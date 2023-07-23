import pers.shawxingkwok.ktutil.allDo
import kotlin.test.Test

class Test{
    @Test
    fun start() {
        assert(true)
        allDo(1, 2, act = ::println)
    }
}