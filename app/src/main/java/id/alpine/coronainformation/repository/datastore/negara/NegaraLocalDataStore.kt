package id.alpine.coronainformation.repository.datastore.negara

import id.alpine.coronainformation.helper.Debuger
import id.alpine.coronainformation.model.ResponseCountries

class NegaraLocalDataStore :
    NegaralDataStore {

    private var caches = ResponseCountries()

    override suspend fun getNegara(negara: String): ResponseCountries? =
        if (caches.cases != null) {
            Debuger().debug("Local data store -> caces")
            caches
        } else {
            Debuger().debug("Local data store -> null")
            null
        }

    override suspend fun addAll(data: ResponseCountries?) {
        data?.let {
            caches = data
        }
    }

}