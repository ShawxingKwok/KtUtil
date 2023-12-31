package pers.shawxingkwok.ktutil

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Simplifies from `lazy(LazyThreadSafetyMode.NONE){ ... }`, and is mutable.
 */
public inline fun <T> fastLazy(crossinline initialize: () -> T): ReadWriteProperty<Any?, T> =
    object : ReadWriteProperty<Any?, T> {
        var value: Any? = null
        var isInitialized = false

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            if (!isInitialized){
                isInitialized = true
                value = initialize()
            }

            @Suppress("UNCHECKED_CAST")
            return value as T
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            isInitialized = true
            this.value = value
        }
    }

/**
 * Passes the receiver of [T] to initialize a [lazy] value easily with [mode].
 *
 * Usage example:
 *
 * ```
 * val Context.database by receivedLazy{ context -> DatabaseBuilder.build(context, ...) }
 */
public inline fun <T: Any, V> receivedLazy(
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    crossinline initialize: (T) -> V,
)
: ReadOnlyProperty<T, V> {
    lateinit var t: T
    val v by lazy(mode){ initialize(t) }

    return ReadOnlyProperty { thisRef, _ ->
        t = thisRef
        v
    }
}

/**
 * Passes the receiver of [T] to initialize a [lazy] value easily with [lock].
 *
 * Usage example:
 *
 * ```
 * val Context.database by receivedLazy{ context -> DatabaseBuilder.build(context, ...) }
 */
public inline fun <T: Any, V> receivedLazy(
    lock: Any,
    crossinline initialize: (T) -> V,
)
: ReadOnlyProperty<T, V> {
    lateinit var t: T
    val v by lazy(lock){ initialize(t) }

    return ReadOnlyProperty { thisRef, _ ->
        t = thisRef
        v
    }
}