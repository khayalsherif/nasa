package az.khayalsharifli.data.di

import androidx.room.Room
import androidx.room.RoomDatabase
import az.khayalsharifli.data.BuildConfig
import az.khayalsharifli.data.local.epic.EpicDataBase
import az.khayalsharifli.data.local.epic.EpicLocalDataSource
import az.khayalsharifli.data.local.epic.EpicLocalDataSourceImpl
import az.khayalsharifli.data.remote.EpicApi
import az.khayalsharifli.data.repository.EpicRepositoryImpl
import az.khayalsharifli.domain.repository.EpicRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {
        val builder = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(getProperty<String>("base"))
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory<EpicApi> { get<Retrofit>().create(EpicApi::class.java) }

    factory<EpicRepository> {
        EpicRepositoryImpl(
            epicApi = get(), epicLocalDataSource = get()
        )
    }

    //////////////////////////////////  LOCAL   ////////////////////////////////////////////////////

    single { get<EpicDataBase>().epicDao() }

    single<EpicLocalDataSource> {
        EpicLocalDataSourceImpl(epicDao = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            EpicDataBase::class.java,
            "epic-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


}