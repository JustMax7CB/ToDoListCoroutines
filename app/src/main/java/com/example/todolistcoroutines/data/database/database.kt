package com.example.todolistcoroutines.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todolistcoroutines.data.dao.ToDoDao
import com.example.todolistcoroutines.model.ToDo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {
        @Volatile
        private var instance: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ToDoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ToDoDatabase::class.java,
                "todoDatabase"
            ).build()
        }


        public class ToDoDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { database -> scope.launch {
                    populateDatabase(database.toDoDao())
                } }
            }

            suspend fun populateDatabase(toDoDao: ToDoDao) {
                toDoDao.nukeTable()
                val item = ToDo(title="Item 1", description = "Item 1 description")
                toDoDao.insert(item)
            }
        }
    }
}

