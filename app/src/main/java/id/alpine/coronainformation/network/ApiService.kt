package id.alpine.coronainformation.network

import id.alpine.coronainformation.model.Data
import id.alpine.coronainformation.model.ResponseCountries
import id.alpine.coronainformation.model.ResponseDaerah
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("countries/{countries}")
    suspend fun getNegara(@Path("countries") negara: String): Response<ResponseCountries>

    @GET("provinsi")
    suspend fun getDaerah(): Response<MutableList<ResponseDaerah>>

    @GET("covid19")
    suspend fun getBanyuwangi(): Response<Data.ResponseBanyuwangi>
}
