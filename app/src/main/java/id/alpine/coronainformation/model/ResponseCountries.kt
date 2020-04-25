package id.alpine.coronainformation.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResponseCountries(
    @PrimaryKey
    val country: String,
    val recovered: Int? = null,
    val cases: Int? = null,
    val critical: Int? = null,
    val active: Int? = null,
    val casesPerOneMillion: Int? = null,
    val deaths: Int? = null,
    val todayCases: Int? = null,
    val todayDeaths: Int? = null
)
