package id.alpine.coronainformation.ui.banyuwangi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.repository.RepositoryBanyuwangi

@Suppress("UNCHECKED_CAST")
class BanyuwangiVIewModelFactory(private val repository: RepositoryBanyuwangi) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BanyuwangiViewModel::class.java))
            return BanyuwangiViewModel(repository) as T

        throw IllegalArgumentException()
    }

}