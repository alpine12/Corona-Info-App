package id.alpine.coronainformation.network

import id.alpine.coronainformation.model.ResponseCountries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("countries/{countries}")
    suspend fun getNegara(@Path("countries") negara: String): Response<ResponseCountries>
}
