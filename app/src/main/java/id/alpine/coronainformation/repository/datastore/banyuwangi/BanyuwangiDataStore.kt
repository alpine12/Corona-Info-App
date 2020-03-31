package id.alpine.coronainformation.repository.datastore.banyuwangi

import id.alpine.coronainformation.model.Data

interface BanyuwangiDataStore {

    suspend fun getData(): Data?
    suspend fun addAll(data: Data?)

}