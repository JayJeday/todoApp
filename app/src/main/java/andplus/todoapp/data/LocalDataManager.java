package andplus.todoapp.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.data.local.db.DbHelper;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class LocalDataManager implements DataManager {

    private final Context mContext;

    private  final DbHelper mDbHelper;

    @Inject
    public LocalDataManager(Context context, DbHelper dbHelper){
    mContext = context;
    mDbHelper = dbHelper;
    }

    @Override
    public Observable<List<Task>> getAllTasks() {
        return mDbHelper.getAllTasks();
    }

    @Override
    public Observable<Boolean> insertTask(Task task) {
        return mDbHelper.insertTask(task);
    }

    @Override
    public Single<Task> getTaskById(String id) {
        return mDbHelper.getTaskById(id);
    }


}
