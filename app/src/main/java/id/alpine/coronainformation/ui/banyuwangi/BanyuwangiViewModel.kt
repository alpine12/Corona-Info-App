package id.alpine.coronainformation.ui.banyuwangi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.alpine.coronainformation.repository.RepositoryBanyuwangi
import kotlinx.coroutines.launch

class BanyuwangiViewModel(private val repository: RepositoryBanyuwangi) : ViewModel() {

    private val _mViewState = MutableLiveData<BanyuwangiViewState>().apply {
        value = BanyuwangiViewState(true)
    }

    val viewState: LiveData<BanyuwangiViewState> get() = _mViewState

    init {
        getData()
    }

    fun refresh() = viewModelScope.launch {
        val message = repository.refresh()
        _mViewState.value = _mViewState.value?.copy(loading = false, message = message)
        getData()
        _mViewState.value = _mViewState.value?.copy(loading = false, message = null)
    }

    fun getData() = viewModelScope.launch {
        try {
            val data = repository.getBanyuwangi()
            _mViewState.value = _mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (e: Exception) {
            _mViewState.value = _mViewState.value?.copy(
                loading = false,
                error = e,
                data = null,
                message = e.toString()
            )
        }

    }
}