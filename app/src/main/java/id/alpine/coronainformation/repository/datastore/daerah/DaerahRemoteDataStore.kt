package id.alpine.coronainformation.repository.datastore.daerah

import id.alpine.coronainformation.model.ResponseDaerah
import id.alpine.coronainformation.network.ApiService

class DaerahRemoteDataStore(private val apiService: ApiService) :

    DaerahlDataStore {
    override suspend fun getDaerah(): MutableList<ResponseDaerah>? {
        val response = apiService.getDaerah()
        if (response.isSuccessful) return response.body()

        throw IllegalArgumentException()
    }

    override suspend fun addAll(data: MutableList<ResponseDaerah>?) {
    }

}