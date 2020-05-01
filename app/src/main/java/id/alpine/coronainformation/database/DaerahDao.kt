package id.alpine.coronainformation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.alpine.coronainformation.model.Attributes

@Dao
interface DaerahDao {

    @Query("Select * from Attributes")
    suspend fun getDaerah(): MutableList<Attributes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaerah(attributes: Attributes)
}