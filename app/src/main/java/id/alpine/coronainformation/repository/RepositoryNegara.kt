package id.alpine.coronainformation.repository

import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.repository.datastore.negara.NegaralDataStore

class RepositoryNegara internal constructor() : BaseRepository<NegaralDataStore>() {

    suspend fun getNegara(negara: String): ResponseCountries? {
        val caches = localDataStore?.getNegara(negara)
        if (caches != null) return caches
        val response = remoteDataStore?.getNegara(negara)
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { RepositoryNegara() }
    }
}