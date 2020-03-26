package id.alpine.coronainformation.model

data class ResponseCountries(
    val country: String? = null,
    val recovered: Int? = null,
    val cases: Int? = null,
    val critical: Int? = null,
    val active: Int? = null,
    val casesPerOneMillion: Int? = null,
    val deaths: Int? = null,
    val todayCases: Int? = null,
    val todayDeaths: Int? = null
)
