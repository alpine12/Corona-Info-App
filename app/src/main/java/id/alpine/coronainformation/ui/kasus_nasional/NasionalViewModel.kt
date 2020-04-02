package id.alpine.coronainformation.ui.kasus_nasional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.alpine.coronainformation.repository.RepositoryNegara
import kotlinx.coroutines.launch

class NasionalViewModel(private val repositoryNegara: RepositoryNegara) : ViewModel() {

    private val mViewState = MutableLiveData<NasionalViewState>().apply {
        value = NasionalViewState(true)
    }

    val viewState: LiveData<NasionalViewState> get() = mViewState

    init {
        getNegara("indonesia")
    }

    fun refresh(negara: String) = viewModelScope.launch {
        val message = repositoryNegara.refresh(negara)
        mViewState.value = mViewState.value?.copy(loading = false, message = message)
        getNegara(negara)
        mViewState.value = mViewState.value?.copy(loading = false, message = null)
    }

    fun getNegara(negara: String) = viewModelScope.launch {
        try {
            val data = repositoryNegara.getNegara(negara)
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (e: Exception) {
            mViewState.value = mViewState.value?.copy(
                loading = false,
                error = e,
                data = null,
                message = e.toString()
            )
        }
    }
}