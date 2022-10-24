package az.khayalsharifli.data.mapper

/**
 * Obtaining models from external data source layers and transferring
 * them from the internal data source to the business model.
 */
interface Mapper<REMOTE, LOCAL, DOMAIN> {

    fun remoteToLocal(type: REMOTE): LOCAL

    fun localToDomain(type: LOCAL): DOMAIN
}
