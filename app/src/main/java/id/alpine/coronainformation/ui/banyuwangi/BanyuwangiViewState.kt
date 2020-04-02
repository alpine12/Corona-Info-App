package id.alpine.coronainformation.ui.banyuwangi

import id.alpine.coronainformation.model.Data

data class BanyuwangiViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var message: String? = null,
    var data: Data? = null
)