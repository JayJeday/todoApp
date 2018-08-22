package andplus.todoapp.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import andplus.todoapp.data.Task;
import andplus.todoapp.data.local.db.dao.TaskDao;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

}
