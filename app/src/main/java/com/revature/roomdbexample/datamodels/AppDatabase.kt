package com.revature.roomdbexample.datamodels;

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.revature.roomdbexample.dao.CustomerData

import kotlin.jvm.Volatile;

// Below, format, when separating items in table
@Database(entities = [Customer::class], version=1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun customerDao():CustomerData

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase?=null;

        fun getDatabase(context:Context):AppDatabase {

            val tempInstance=INSTANCE
            if(tempInstance!=null)
            {
                return tempInstance
            }

            synchronized(this)
            {
                var instance=Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,"kevin").build()

                INSTANCE=instance

                return instance
            }
        }
    }

}
