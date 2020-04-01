package id.alpine.coronainformation.model

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("covid_meninggal")
    val covidMeninggal: String? = null,

    @field:SerializedName("odp_proses")
    val odpProses: String? = null,

    @field:SerializedName("pdp_rawat")
    val pdpRawat: String? = null,

    @field:SerializedName("total_covid")
    val totalCovid: String? = null,

    @field:SerializedName("covid_sembuh")
    val covidSembuh: String? = null,

    @field:SerializedName("total_odp")
    val totalOdp: String? = null,

    @field:SerializedName("odp_selesai")
    val odpSelesai: String? = null,

    @field:SerializedName("total_pdp")
    val totalPdp: String? = null,

    @field:SerializedName("pdp_sembuh")
    val pdpSembuh: String? = null,

    @field:SerializedName("covid_rawat")
    val covidRawat: String? = null
) {
    data class ResponseBanyuwangi(val data: Data? = null)
}