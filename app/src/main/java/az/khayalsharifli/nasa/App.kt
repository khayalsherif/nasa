package az.khayalsharifli.nasa

import android.app.Application
import az.khayalsharifli.nasa.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = this@App)
            properties(
                mapOf("base" to "https://api.nasa.gov/")
            )
            modules(appModule)
        }
    }
}