package id.alpine.coronainformation.repository

import id.alpine.coronainformation.model.Data
import id.alpine.coronainformation.repository.datastore.banyuwangi.BanyuwangiDataStore

class RepositoryBanyuwangi internal constructor() : BaseRepository<BanyuwangiDataStore>() {

    suspend fun getBanyuwangi(): Data? {
        val caches = localDataStore?.getData()
        if (caches != null) return caches

        val response = remoteDataStore?.getData()
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { RepositoryBanyuwangi() }
    }

}