package az.khayalsharifli.domain.di

import az.khayalsharifli.domain.usecase.EpicUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        EpicUseCase(epicRepository = get())
    }
}