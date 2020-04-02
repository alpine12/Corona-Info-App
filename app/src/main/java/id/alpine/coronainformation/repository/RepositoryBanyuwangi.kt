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

    suspend fun refresh(): String {
        return try {
            val response = remoteDataStore?.getData()
            localDataStore?.addAll(response)
            "Update"
        } catch (e: Exception) {
            "Gagal Update, Jaringan Bermasalah"
        }
    }

    companion object {
        val instance by lazy { RepositoryBanyuwangi() }
    }

}