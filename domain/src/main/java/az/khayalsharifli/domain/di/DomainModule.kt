package az.khayalsharifli.domain.di

import az.khayalsharifli.domain.exceptions.ErrorConverter
import az.khayalsharifli.domain.exceptions.ErrorConverterImpl
import az.khayalsharifli.domain.usecase.splash.SplashUseCase
import az.khayalsharifli.domain.usecase.epic.EpicObserveUseCase
import az.khayalsharifli.domain.usecase.epic.EpicSyncUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ERROR_MAPPER_NETWORK = "ERROR_MAPPER_NETWORK"
const val IO_CONTEXT = "IO_CONTEXT"

val domainModule = module {

    factory {
        SplashUseCase(
            context = get(named(IO_CONTEXT))
        )
    }

    factory {
        EpicSyncUseCase(
            context = get(named(IO_CONTEXT)),
            repository = get(),
            converter = get()
        )
    }

    factory {
        EpicObserveUseCase(
            context = get(named(IO_CONTEXT)),
            repository = get(),
            converter = get()
        )
    }

    single<ErrorConverter> {
        ErrorConverterImpl(
            setOf(
                get(named(ERROR_MAPPER_NETWORK))
            )
        )
    }
}