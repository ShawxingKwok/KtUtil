package pers.shawxingkwok.ktutil

/**
 * Solves the nullability bug of [MutableMap.getOrPut].
 *
 * Usage example:
 *
 * ```
 * var map = mutableMapOf("a" to null, "b" to 1)
 * map.getOrPut("a"){ 2 } // 2
 * map                    // {a=2, b=1}
 *
 * map = mutableMapOf("a" to null, "b" to 1)
 * map.getOrPutNullable("a"){ 2 } // null
 * map                            // {a=null, b=1}
 */
public inline fun <T, reified V> MutableMap<T, V>.getOrPutNullable(
    key: T,
    getValue: (T) -> V,
): V =
    if (key in this)
        get(key) as V
    else
        getValue(key).also { put(key, it) }