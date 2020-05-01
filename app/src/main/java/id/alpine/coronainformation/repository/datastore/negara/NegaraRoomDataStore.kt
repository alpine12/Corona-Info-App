package id.alpine.coronainformation.repository.datastore.negara

import id.alpine.coronainformation.database.NegaraDao
import id.alpine.coronainformation.model.ResponseCountries

class NegaraRoomDataStore(private val negaraDao: NegaraDao) : NegaralDataStore {
    override suspend fun getNegara(negara: String): ResponseCountries? {
        val response = negaraDao.getNegara()
        return if (response != null) response else null
    }

    override suspend fun addAll(data: ResponseCountries?) {
        data?.let {
            negaraDao.insertNegara(it)
        }
    }


}