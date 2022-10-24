package az.khayalsharifli.data.di

import androidx.room.Room
import az.khayalsharifli.data.BuildConfig
import az.khayalsharifli.data.errors.RemoteErrorMapper
import az.khayalsharifli.data.local.epic.EpicDataBase
import az.khayalsharifli.data.local.epic.EpicLocalDataSource
import az.khayalsharifli.data.local.epic.EpicLocalDataSourceImpl
import az.khayalsharifli.data.mapper.Mapper
import az.khayalsharifli.data.mapper.epic.EpicMapper
import az.khayalsharifli.data.remote.EpicService
import az.khayalsharifli.data.repository.EpicRepositoryImpl
import az.khayalsharifli.domain.di.ERROR_MAPPER_NETWORK
import az.khayalsharifli.domain.di.IO_CONTEXT
import az.khayalsharifli.domain.exceptions.ErrorMapper
import az.khayalsharifli.domain.repository.EpicRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

val dataModule = module {

    single<CoroutineContext>(named(IO_CONTEXT)) { Dispatchers.IO }

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
            .baseUrl(getProperty("base"))
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    factory<EpicService> { get<Retrofit>().create(EpicService::class.java) }

    factory<EpicRepository> {
        EpicRepositoryImpl(
            mapper = get(),
            epicService = get(), epicLocalDataSource = get()
        )
    }

    factory<EpicMapper> {
        EpicMapper()
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

    /////////////////////////////// REMOTE ERROR MAP ///////////////////////////////////////////////

    factory<ErrorMapper>(named(ERROR_MAPPER_NETWORK)) { RemoteErrorMapper() }

}