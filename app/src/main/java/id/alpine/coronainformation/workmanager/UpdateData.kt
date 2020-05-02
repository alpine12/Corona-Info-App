package id.alpine.coronainformation.workmanager

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import id.alpine.coronainformation.R
import id.alpine.coronainformation.database.AppDatabase
import id.alpine.coronainformation.helper.Constant
import id.alpine.coronainformation.model.ResponseCountries
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
        val responseNegara = RetrofitBuilder.apiService(Constant.URL_NEGARA).getNegara("indonesia")
        val appDatabase = AppDatabase.getInstance(applicationContext).negaraDao()
        val data = appDatabase.getNegara()
        if (responseNegara.isSuccessful) {
            val negara = responseNegara.body() as ResponseCountries
            if (negara != data) {
                showNotification(negara)
                appDatabase.insertNegara(negara)
            }
        }
    }

    private fun showNotification(negara: ResponseCountries?) {
        val builder = NotificationCompat.Builder(applicationContext, Constant.CHANEL_ID)
            .setSmallIcon(R.mipmap.ic_bacteria)
            .setContentTitle("Update Informasi Covid-19")
            .setContentText("Kasus hari ini ${negara!!.todayCases} , Update pada ${getDtn()}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(Random.nextInt(0, 100), builder.build())
        }
    }

}