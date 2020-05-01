package id.alpine.coronainformation.repository.datastore.banyuwangi

import id.alpine.coronainformation.database.BanyuwangiDao
import id.alpine.coronainformation.model.Data

class BanyuwangiRoomDataStore(val banyuwangiDao: BanyuwangiDao) : BanyuwangiDataStore {
    override suspend fun getData(): Data? {
        val response = banyuwangiDao.getDaerah()
        return if (response != null) response else null
    }

    override suspend fun addAll(data: Data?) {
        data?.let {
            banyuwangiDao.insertDaerah(it)
        }
    }

}