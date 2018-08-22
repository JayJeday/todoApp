package andplus.todoapp.data.local.db;

import java.util.List;

import andplus.todoapp.data.Task;
import io.reactivex.Observable;
import io.reactivex.Single;

/*
    list of data operation in the database
 */
public interface DbHelper {

    Observable<List<Task>> getAllTasks();

    Observable<Boolean> insertTask(Task task);

    Single<Task> getTaskById(String id);


}
