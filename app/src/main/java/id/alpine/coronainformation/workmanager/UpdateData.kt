package id.alpine.coronainformation.workmanager

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import id.alpine.coronainformation.MainActivity
import id.alpine.coronainformation.R
import id.alpine.coronainformation.database.AppDatabase
import id.alpine.coronainformation.helper.Constant
import id.alpine.coronainformation.model.Data
import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.model.ResponseDaerah
import id.alpine.coronainformation.network.RetrofitBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class UpdateData(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        fecthData()
        return Result.success()
    }

    private fun getDtn(): String {
        val current = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-YYYY HH:mm", Locale("ID"))
        return formatter.format(current)
    }

    private suspend fun fecthData() {
        val appDatabase = AppDatabase.getInstance(applicationContext)
        val responseNegara = RetrofitBuilder.apiService(Constant.URL_NEGARA).getNegara("indonesia")
        val responseDaerah = RetrofitBuilder.apiService(Constant.URL_DAERAH).getDaerah()
        val responseBwx = RetrofitBuilder.apiService(Constant.URL_BANYUWANGI).getBanyuwangi()

        //Negara
        val dataNegara = appDatabase.negaraDao()
        if (responseNegara.isSuccessful) {
            val negara = responseNegara.body() as ResponseCountries
            if (negara != dataNegara.getNegara()) {
                showNotification(negara)
            }
            dataNegara.insertNegara(negara)
        }

        //Daerah
        val dataDaerah = appDatabase.daerahDao()
        if (responseDaerah.isSuccessful) {
            val daerah = responseDaerah.body() as MutableList<ResponseDaerah>
            daerah.let {
                for (data in it) {
                    dataDaerah.insertDaerah(data.attributes!!)
                }
            }
        }

        //Bwx
        val dataBwx = appDatabase.banyuwangiDao()
        if (responseBwx.isSuccessful) {
            val bwx = responseBwx.body()!!.data as Data
            dataBwx.insertDaerah(bwx)
        }

    }

    private fun showNotification(negara: ResponseCountries?) {

        // Create an explicit intent for an Activity in your app
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val builder = NotificationCompat.Builder(applicationContext, Constant.CHANEL_ID)
            .setSmallIcon(R.mipmap.ic_bacteria)
            .setContentTitle("Update Informasi Covid-19")
            .setContentText("Kasus hari ini ${negara?.todayCases} , Update pada ${getDtn()}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(Random.nextInt(0, 100), builder.build())
        }
    }

}