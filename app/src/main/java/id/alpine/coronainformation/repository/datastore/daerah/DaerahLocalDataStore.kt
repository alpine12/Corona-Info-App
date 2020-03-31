package id.alpine.coronainformation.repository.datastore.daerah

import id.alpine.coronainformation.model.ResponseDaerah

class DaerahLocalDataStore :
    DaerahlDataStore {
    private var caches = mutableListOf<ResponseDaerah>()
    override suspend fun getDaerah(): MutableList<ResponseDaerah>? =
        if (caches.isNotEmpty()) caches else null

    override suspend fun addAll(data: MutableList<ResponseDaerah>?) {
        data?.let {
            caches = data
        }
    }
}