package id.alpine.coronainformation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import com.pixplicity.easyprefs.library.Prefs
import id.alpine.coronainformation.database.AppDatabase
import id.alpine.coronainformation.helper.Constant
import id.alpine.coronainformation.network.RetrofitBuilder
import id.alpine.coronainformation.repository.RepositoryBanyuwangi
import id.alpine.coronainformation.repository.RepositoryDaerah
import id.alpine.coronainformation.repository.RepositoryNegara
import id.alpine.coronainformation.repository.datastore.banyuwangi.BanyuwangiRemoteDataSotre
import id.alpine.coronainformation.repository.datastore.banyuwangi.BanyuwangiRoomDataStore
import id.alpine.coronainformation.repository.datastore.daerah.DaerahRemoteDataStore
import id.alpine.coronainformation.repository.datastore.daerah.DaerahRoomDataStore
import id.alpine.coronainformation.repository.datastore.negara.NegaraRemoteDataStore
import id.alpine.coronainformation.repository.datastore.negara.NegaraRoomDataStore

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initRepo()
        easyPref()
        notificationsChanel()

    }

    private fun initRepo() {
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
            init(
                DaerahRoomDataStore(appDatabase.daerahDao()),
                DaerahRemoteDataStore(apiServiceDaerah)
            )
        }

        RepositoryBanyuwangi.instance.apply {
            init(
                BanyuwangiRoomDataStore(appDatabase.banyuwangiDao()),
                BanyuwangiRemoteDataSotre(apiServiceBwx)
            )
        }
    }

    private fun easyPref() {
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }

    private fun notificationsChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.chanel_name)
            val desc = getString(R.string.chanel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChancel = NotificationChannel(Constant.CHANEL_ID, name, importance)
            mChancel.description = desc
            // Register the channel with the system;
            // you can't change the importance
            // or other notification behaviors after this
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChancel)
        }
    }
}