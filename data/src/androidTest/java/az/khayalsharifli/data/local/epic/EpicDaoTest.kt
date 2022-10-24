package az.khayalsharifli.data.local.epic

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import org.junit.After
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class EpicDaoTest {
    private lateinit var dataBase: EpicDataBase
    private lateinit var epicDao: EpicDao

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        //inMemoryDatabaseBuilder - is create database in device ram
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EpicDataBase::class.java
        ).allowMainThreadQueries().build()
        epicDao = dataBase.epicDao()
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun insertEpic() = runBlocking {
        //Given
        val centroidCoordinates = EpicLocalDto.CentroidCoordinates(0.0, 0.0)
        val epicItem = EpicLocalDto(
            -1,
            caption = "Caption",
            centroid_coordinates = centroidCoordinates,
            date = "11.11.2031",
            identifier = "identifier",
            image = "image",
            version = "version"
        )
        //When
        epicDao.insertEpic(listOf(epicItem))

        //Then
        val allEpicItem = ArrayList<EpicLocalDto>()
        epicDao.observeEpic().test {
            val list = this.awaitItem()
            for (i in list) allEpicItem.add(i)
        }
        assertThat(allEpicItem).isEqualTo(listOf(epicItem))
    }

}



