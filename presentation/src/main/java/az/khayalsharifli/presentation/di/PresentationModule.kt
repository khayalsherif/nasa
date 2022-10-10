package az.khayalsharifli.presentation.di

import az.khayalsharifli.presentation.flow.content.detail.DetailViewModel
import az.khayalsharifli.presentation.flow.content.home.HomeViewModel
import az.khayalsharifli.presentation.flow.content.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        SplashViewModel(splashUseCase = get())
    }

    viewModel {
        DetailViewModel()
    }

    viewModel {
        HomeViewModel(
            epicObserveUseCase = get(),
            epicSyncUseCase = get()
        )
    }
}