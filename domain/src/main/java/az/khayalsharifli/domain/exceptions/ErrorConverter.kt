package az.khayalsharifli.domain.exceptions

interface ErrorConverter {
    fun convert(throwable: Throwable): Throwable
}