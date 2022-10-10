package az.khayalsharifli.domain.exceptions

fun interface ErrorMapper {
    fun mapError(throwable: Throwable): Throwable
}