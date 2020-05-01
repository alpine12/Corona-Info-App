package id.alpine.coronainformation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.alpine.coronainformation.model.Data

@Dao
interface BanyuwangiDao {

    @Query("Select * from data")
    suspend fun getDaerah(): Data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaerah(data: Data)

}