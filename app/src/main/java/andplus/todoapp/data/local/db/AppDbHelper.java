package andplus.todoapp.data.local.db;


import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import andplus.todoapp.data.Task;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    /*
    get all task in the database
     */
    @Override
    public Observable<List<Task>> getAllTasks() {
        return Observable.fromCallable(new Callable<List<Task>>() {
            @Override
            public List<Task> call() throws Exception {
                return mAppDatabase.taskDao().getTasks();
            }
        });
    }

    @Override
    public Observable<Boolean> insertTask(Task task) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                 mAppDatabase.taskDao().insertTask(task);
                 return true;
            }
        });
    }

    @Override
    public Single<Task> getTaskById(String id) {
        return Single.fromCallable(new Callable<Task>() {
            @Override
            public Task call() throws Exception {
                return mAppDatabase.taskDao().getTaskById(id);
            }
        });
    }
}
