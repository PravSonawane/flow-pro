package core.lib.result

import io.reactivex.Observable

open class DomainError(
    val code: String,
    val message: String? = null,
    val cause: Throwable? = null
)

class ErrorThrowable(
    val code: String,
    override val message: String? = null,
    val throwable: Throwable? = null
) : Throwable(message) {
    fun toError(): DomainError {
        return DomainError(code, message, throwable)
    }

    override fun toString(): String {
        return "ErrorThrowable(code='$code', message=$message, throwable=$throwable)"
    }
}

fun DomainError.toThrowable(): ErrorThrowable {
    return ErrorThrowable(code, message, cause)
}

fun <T : Any> DomainError.toResult(): Observable<Result<T>> {
    return Observable.just(Result.OnError(this))
}

/**
 * Converts a [Throwable] to a [Result.OnError].
 */
fun <T : Any> Throwable.toResult(): Result<T> {
    return when (this) {
        is ErrorThrowable -> Result.OnError(this.toError())
        else -> Result.OnError(UnknownError(throwable = this))
    }
}

/**
 * Converts a [Result] of T to T.
 */
fun <T : Any> Result<T>.toDataObservable(): Observable<T> {
    return when (this) {
        is Result.OnSuccess -> Observable.just(this.data)
        is Result.OnError -> Observable.error(this.domainError.toThrowable())
    }
}

/**
 * Converts a [Result] of T to T.
 */
fun <T : Any> T.toResult(): Result<T> {
    return Result.OnSuccess(this)
}

fun createError(
    errorCode: String,
    errorMessage: String? = null,
    errorThrowable: Throwable? = null
): DomainError {
    return DomainError(
        errorCode,
        errorMessage,
        errorThrowable
    )
}

// unknown error
const val UNKNOWN = "99000"
class UnknownError(
    message: String? = null,
    throwable: Throwable? = null
) : DomainError(
    UNKNOWN,
    message ?: "Unknown error",
    throwable
)
