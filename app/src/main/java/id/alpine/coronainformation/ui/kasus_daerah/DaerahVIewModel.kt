package id.alpine.coronainformation.ui.kasus_daerah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.alpine.coronainformation.repository.RepositoryDaerah
import kotlinx.coroutines.launch

class DaerahVIewModel(private val repository: RepositoryDaerah) : ViewModel() {

    private val _mViewState = MutableLiveData<DaerahViewState>().apply {
        value = DaerahViewState(false)
    }

    val _viewState: LiveData<DaerahViewState> get() = _mViewState

    fun getDaerah() = viewModelScope.launch {
        try {
            val data = repository.getDaerah()
            _mViewState.value = _mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (e: Exception) {
            _mViewState.value = _mViewState.value?.copy(loading = false, error = e, data = null)
        }
    }

}