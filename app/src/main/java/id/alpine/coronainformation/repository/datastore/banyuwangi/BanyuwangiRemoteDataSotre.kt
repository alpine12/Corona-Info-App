package id.alpine.coronainformation.repository.datastore.banyuwangi

import id.alpine.coronainformation.model.Data
import id.alpine.coronainformation.network.ApiService

class BanyuwangiRemoteDataSotre(private val apiService: ApiService) : BanyuwangiDataStore {
    override suspend fun getData(): Data? {
        val caches = apiService.getBanyuwangi()
        if (caches.isSuccessful) return caches.body()?.data

        throw IllegalArgumentException()
    }

    override suspend fun addAll(data: Data?) {}


}