package pers.shawxingkwok.ktutil

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Simplifies from `lazy(LazyThreadSafetyMode.NONE){ ... }` to `lazyFast{ ... }`
 * which is inexpensive though thread-unsafe, and should be more common than `lazy{ ... }`.
 */
public fun <T> lazyFast(initialize: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initialize)

/**
 * Is used like [lazy], but mutable and thread-unsafe.
 */
public inline fun <T> mutableLazy(crossinline initialize: () -> T): ReadWriteProperty<Any?, T> =
    object : ReadWriteProperty<Any?, T> {
        var _value: Any? = null
        var initialized = false

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            if (!initialized){
                initialized = true
                _value = initialize()
            }

            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            _value = value
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