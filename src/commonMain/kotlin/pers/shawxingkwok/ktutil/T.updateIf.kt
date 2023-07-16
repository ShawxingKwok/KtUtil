package pers.shawxingkwok.ktutil

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Returns the [update]d value if [this] matches [predicate], or [this] if not.
 * This is very helpful in the linked operations.
 *
 * Usage example:
 *
 * ```
 * val content = list
 *    .joinToString()
 *    .updateIf({ it.any() }){ "$it." }
 */
@OptIn(ExperimentalContracts::class)
public inline fun <T> T.updateIf(
    predicate: (T) -> Boolean,
    update: (T) -> T,
): T{
    contract {
        callsInPlace(predicate, InvocationKind.EXACTLY_ONCE)
    }

    if (predicate(this))
        return update(this)
    else
        return this
}