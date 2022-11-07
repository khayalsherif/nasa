package az.khayalsharifli.presentation.flow.content.home

import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule


@MediumTest
@ExperimentalCoroutinesApi
class HomeFragmentTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
    }

    @Before
    fun setUp() {

    }

    @After
    fun close() {
        stopKoin()
    }

    fun test(){
        
    }
}