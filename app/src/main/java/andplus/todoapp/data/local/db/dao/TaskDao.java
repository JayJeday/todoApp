package andplus.todoapp.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import andplus.todoapp.data.Task;

@Dao
public interface TaskDao {

    /*
    get all task
     */
    @Query("Select * FROM Tasks")
    List<Task> getTasks();

    /*
    get task by id
     */
    @Query("Select * FROM Tasks WHERE taskid = :taskId")
    Task getTaskById(String taskId);

    /*
    Insert task and if there is conflict replace the duplicate
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    /*
    Update a task
     */
    @Update
    int updateTask(Task task);

}
