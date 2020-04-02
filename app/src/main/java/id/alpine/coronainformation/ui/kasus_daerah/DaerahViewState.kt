package id.alpine.coronainformation.ui.kasus_daerah

import id.alpine.coronainformation.model.ResponseDaerah

data class DaerahViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var message: String? = null,
    var data: MutableList<ResponseDaerah>? = null
)