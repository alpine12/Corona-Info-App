package id.alpine.coronainformation.ui.kasus_nasional

import id.alpine.coronainformation.model.ResponseCountries

data class NasionalViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var message: String? = null,
    var data: ResponseCountries? = null
)