package id.alpine.coronainformation

import android.app.Application
import id.alpine.coronainformation.database.AppDatabase
import id.alpine.coronainformation.helper.Constant
import id.alpine.coronainformation.network.RetrofitBuilder
import id.alpine.coronainformation.repository.RepositoryBanyuwangi
import id.alpine.coronainformation.repository.RepositoryDaerah
import id.alpine.coronainformation.repository.RepositoryNegara
import id.alpine.coronainformation.repository.datastore.banyuwangi.BanyuwangiLocalDataStore
import id.alpine.coronainformation.repository.datastore.banyuwangi.BanyuwangiRemoteDataSotre
import id.alpine.coronainformation.repository.datastore.daerah.DaerahLocalDataStore
import id.alpine.coronainformation.repository.datastore.daerah.DaerahRemoteDataStore
import id.alpine.coronainformation.repository.datastore.negara.NegaraRemoteDataStore
import id.alpine.coronainformation.repository.datastore.negara.NegaraRoomDataStore

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val apiServiceNegara = RetrofitBuilder.apiService(Constant.URL_NEGARA)
        val apiServiceDaerah = RetrofitBuilder.apiService(Constant.URL_DAERAH)
        val apiServiceBwx = RetrofitBuilder.apiService(Constant.URL_BANYUWANGI)

        val appDatabase = AppDatabase.getInstance(this)

        RepositoryNegara.instance.apply {
            init(
                NegaraRoomDataStore(appDatabase.negaraDao()),
                NegaraRemoteDataStore(apiServiceNegara)
            )
        }

        RepositoryDaerah.instance.apply {
            init(DaerahLocalDataStore(), DaerahRemoteDataStore(apiServiceDaerah))
        }

        RepositoryBanyuwangi.instance.apply {
            init(BanyuwangiLocalDataStore(), BanyuwangiRemoteDataSotre(apiServiceBwx))
        }
    }
}