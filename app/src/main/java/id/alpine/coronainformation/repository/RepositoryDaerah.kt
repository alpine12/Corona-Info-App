package id.alpine.coronainformation.repository

import id.alpine.coronainformation.model.ResponseDaerah
import id.alpine.coronainformation.repository.datastore.daerah.DaerahlDataStore

class RepositoryDaerah internal constructor() : BaseRepository<DaerahlDataStore>() {

    suspend fun getDaerah(): MutableList<ResponseDaerah>? {
        val caches = localDataStore?.getDaerah()
        if (caches != null) return caches
        val response = remoteDataStore?.getDaerah()
        localDataStore?.addAll(response)
        return response
    }

    suspend fun refresh(): String {
        return try {
            val response = remoteDataStore?.getDaerah()
            localDataStore?.addAll(response)
            "Update"
        } catch (e: Exception) {
            "Gagal Update, Jaringan Bermasalah"
        }
    }

    companion object {
        val instance by lazy {
            RepositoryDaerah()
        }
    }
}