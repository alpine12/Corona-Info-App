package id.alpine.coronainformation.repository.datastore.daerah

import id.alpine.coronainformation.model.ResponseDaerah

interface DaerahlDataStore {
    suspend fun getDaerah(): MutableList<ResponseDaerah>?

    suspend fun addAll(data: MutableList<ResponseDaerah>?)
}