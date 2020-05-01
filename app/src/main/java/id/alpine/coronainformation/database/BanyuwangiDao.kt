package id.alpine.coronainformation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.alpine.coronainformation.model.Data

@Dao
interface BanyuwangiDao {

    @Query("Select * from Data")
    suspend fun getDaerah(): Data

    @Insert
    suspend fun insertDaerah(data: Data)

}