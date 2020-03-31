package id.alpine.coronainformation.repository.datastore.negara

import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.network.ApiService

class NegaraRemoteDataStore(private val apiService: ApiService) :
    NegaralDataStore {
    override suspend fun getNegara(negara: String): ResponseCountries? {
        val response = apiService.getNegara(negara)
        if (response.isSuccessful) return response.body()

        throw IllegalArgumentException()
    }

    override suspend fun addAll(data: ResponseCountries?) {
    }

}