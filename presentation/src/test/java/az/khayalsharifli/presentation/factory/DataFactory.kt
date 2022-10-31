package az.khayalsharifli.presentation.factory

import java.util.*
import kotlin.random.Random

object DataFactory {

    fun getRandomString(): String {
        return UUID.randomUUID().toString().substring(0, 15)
    }

    fun getRandomDouble(): Double {
        return Random.nextDouble()
    }

}