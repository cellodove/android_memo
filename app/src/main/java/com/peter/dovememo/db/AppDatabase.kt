package com.peter.dovememo.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.peter.dovememo.db.table.memo.Memo
import com.peter.dovememo.db.table.memo.MemoDao

@Database(entities = [Memo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private lateinit var instance: AppDatabase

        fun getInstance(application: Application): AppDatabase {
            if (!::instance.isInitialized) {
                instance =
                    Room.databaseBuilder(
                        application,
                        AppDatabase::class.java,
                        "memo-db"
                    ).build()
            }
            return instance
        }
    }

    abstract fun memoDao(): MemoDao
}