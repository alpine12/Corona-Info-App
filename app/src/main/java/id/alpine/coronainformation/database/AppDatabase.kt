package id.alpine.coronainformation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.alpine.coronainformation.model.ResponseCountries

@Database(entities = [ResponseCountries::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun negaraDao(): NegaraDao

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