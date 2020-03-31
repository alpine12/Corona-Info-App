package id.alpine.coronainformation.repository.datastore.banyuwangi

import id.alpine.coronainformation.model.Data

class BanyuwangiLocalDataStore : BanyuwangiDataStore {
    private var caches = Data()
    override suspend fun getData(): Data? =
        if (caches.lastUpdated != null) caches else null


    override suspend fun addAll(data: Data?) {
        data?.let {
            caches = data
        }
    }
}
