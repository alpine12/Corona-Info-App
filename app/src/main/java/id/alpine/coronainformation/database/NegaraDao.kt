package id.alpine.coronainformation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.alpine.coronainformation.model.ResponseCountries

@Dao
interface NegaraDao {
    @Query("SELECT * FROM ResponseCountries")
    suspend fun getNegara(): ResponseCountries

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNegara(negara: ResponseCountries)

}