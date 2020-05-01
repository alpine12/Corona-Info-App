package id.alpine.coronainformation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.alpine.coronainformation.model.Attributes
import id.alpine.coronainformation.model.Data
import id.alpine.coronainformation.model.ResponseCountries

@Database(
    entities = [ResponseCountries::class, Attributes::class, Data::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun negaraDao(): NegaraDao
    abstract fun daerahDao(): DaerahDao
    abstract fun banyuwangiDao(): BanyuwangiDao


    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            instance?.let { return it }
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "covid_db"
            ).build()
            return instance!!
        }
    }
}