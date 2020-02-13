package core.lib.result

/**
 * The [Result] of a retrieval operation of a [List] of entities.
 *
 * A retrieval operation can result in one of the two results: successful update
 * ([OnSuccess]) or an error ([OnError]).
 *
 * @see OnSuccess
 * @see OnError
 */
sealed class Result<out T> {
    /** A successful result */
    data class OnSuccess<out T>(val data: T) : Result<T>()

    /** An error */
    data class OnError<out T>(val domainError: DomainError) : Result<T>()
}