package id.alpine.coronainformation.repository.datastore.negara

import id.alpine.coronainformation.model.ResponseCountries

interface NegaralDataStore {
    suspend fun getNegara(negara: String): ResponseCountries?
    suspend fun addAll(data: ResponseCountries?)
}