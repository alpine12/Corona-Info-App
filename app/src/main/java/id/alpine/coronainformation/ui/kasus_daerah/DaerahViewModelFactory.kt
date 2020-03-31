package id.alpine.coronainformation.ui.kasus_daerah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.repository.RepositoryDaerah


class DaerahViewModelFactory(private val repository: RepositoryDaerah) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DaerahVIewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DaerahVIewModel(repository) as T
        }

        throw IllegalArgumentException()
    }

}