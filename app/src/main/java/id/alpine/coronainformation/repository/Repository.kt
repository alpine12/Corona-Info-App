package id.alpine.coronainformation.repository

import androidx.lifecycle.LiveData
import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.network.Resource
import id.alpine.coronainformation.network.RetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.net.UnknownHostException

object Repository {

    lateinit var job: CompletableJob

    fun getNegara(negara: String): LiveData<Resource<ResponseCountries>> {
        job = Job()
        return object : LiveData<Resource<ResponseCountries>>() {
            override fun onActive() {
                super.onActive()
                job.let { theJob ->
                    value = Resource.loading()
                    CoroutineScope(IO + theJob).launch {
                        try {
                            withTimeout(9000) {
                                val data = RetrofitBuilder.apiService("negara").getNegara(negara)
                                withContext(Main) {
                                    if (data.isSuccessful) {
                                        val negara = data.body() as ResponseCountries
                                        value = Resource.success(negara)
                                        theJob.complete()
                                    } else {
                                        value = Resource.error("Koneksi Bermasalah")
                                        theJob.complete()
                                    }
                                }
                            }

                        } catch (e: CancellationException) {
                            withContext(Main) {
                                value = Resource.error(e.toString())
                                theJob.cancel()
                            }
                        } catch (e: UnknownHostException) {
                            withContext(Main) {
                                value = Resource.error(e.toString())
                                theJob.cancel()
                            }
                        }
                    }
                }
            }
        }
    }

    fun cancelJob() {
        job.cancel()
    }

}