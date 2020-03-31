package id.alpine.coronainformation.ui.kasus_nasional

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.alpine.coronainformation.repository.RepositoryNegara

class NaisonalViewModelFactory(private val repositoryNegara: RepositoryNegara) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NasionalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NasionalViewModel(repositoryNegara) as T
        }
        throw IllegalArgumentException()
    }

}