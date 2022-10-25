package az.khayalsharifli.data.mapper

import az.khayalsharifli.data.factory.epic.EpicFactory
import az.khayalsharifli.data.local.epic.model.EpicLocalDto
import az.khayalsharifli.data.mapper.epic.EpicMapper
import az.khayalsharifli.data.remote.model.EpicRemoteDto
import az.khayalsharifli.domain.model.Epic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EpicMapperTest {

    private lateinit var epicMapper: EpicMapper

    @Before
    fun setUp() {
        epicMapper = EpicMapper()
    }

    @Test
    fun remoteToLocalTest() {
        //Given
        val remote = EpicFactory.generateRemoteEpic()

        //When
        val local = epicMapper.remoteToLocal(remote)

        //Then
        assertRemoteToLocalTest(remote, local)
    }

    @Test
    fun localToDomainTest() {
        //Given
        val local = EpicFactory.generateLocalEpic()

        //When
        val domain = epicMapper.localToDomain(local)

        //Then
        assertLocalToDomainTest(local, domain)
    }

    /**
     * Helper Methods
     **/
    private fun assertRemoteToLocalTest(remote: EpicRemoteDto, local: EpicLocalDto) {
        val remoteCoordinatesLat = remote.centroid_coordinates.lat
        val remoteCoordinatesLon = remote.centroid_coordinates.lon
        val localCoordinatesLat = local.centroid_coordinates.lat
        val localCoordinatesLon = local.centroid_coordinates.lon

        assertEquals(remote.caption, local.caption)
        assertEquals(remote.date, local.date)
        assertEquals(remote.identifier, local.identifier)
        assertEquals(remote.version, local.version)
        assertEquals(remote.image, local.image)
        assertEquals(remoteCoordinatesLat.toString(), localCoordinatesLat.toString())
        assertEquals(remoteCoordinatesLon.toString(), localCoordinatesLon.toString())

    }

    private fun assertLocalToDomainTest(local: EpicLocalDto, domain: Epic) {
        val domainCoordinatesLat = domain.centroid_coordinates.lat
        val domainCoordinatesLon = domain.centroid_coordinates.lon
        val localCoordinatesLat = local.centroid_coordinates.lat
        val localCoordinatesLon = local.centroid_coordinates.lon

        assertEquals(local.caption, domain.caption)
        assertEquals(local.date, domain.date)
        assertEquals(local.identifier, domain.identifier)
        assertEquals(local.version, domain.version)
        assertEquals(local.image, domain.image)
        assertEquals(localCoordinatesLat.toString(), domainCoordinatesLat.toString())
        assertEquals(localCoordinatesLon.toString(), domainCoordinatesLon.toString())
    }
}