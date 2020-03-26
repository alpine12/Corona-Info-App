package id.alpine.coronainformation.ui.`kasus nasional`

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.network.Resource
import id.alpine.coronainformation.repository.Repository
import kotlinx.coroutines.CompletableJob

class KasusViewModel : ViewModel() {

    private val _negara: MutableLiveData<String> = MutableLiveData()

    private lateinit var job: CompletableJob

    val getInfoNegara: LiveData<Resource<ResponseCountries>> = Transformations
        .switchMap(_negara) { negara ->
            Repository.getNegara(negara)
        }

    fun setNegara(negara: String) {
        val update = negara
        if (_negara.value == update) return
        _negara.value = update
        println("Debug viewModel : ")
    }

    fun cancelJob() {
        Repository.cancelJob()
    }

    fun debug(text: String) {
        println("Debug : $text")
    }
}