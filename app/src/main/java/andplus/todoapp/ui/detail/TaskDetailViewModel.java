package andplus.todoapp.ui.detail;


import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;

import andplus.todoapp.data.Task;
import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.ui.base.BaseViewModel;
import andplus.todoapp.util.rx.SchedulerProvider;

public class TaskDetailViewModel extends BaseViewModel<TaskDetailNavigator> {

    public final ObservableField<String> taskId = new ObservableField<>();

    public final ObservableField<Task> mTask = new ObservableField<>();

    //represent the trip
    public final MutableLiveData<Task> mLiveDataTask;

    public TaskDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mLiveDataTask = new MutableLiveData<>();
    }


    public void fetchSelectedTask(String id) {
        getCompositeDisposable().add(getDataManager().getTaskById(id)
                .doOnSuccess(task -> {
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(task -> {
                    getNavigator().setupViews(task);
                    mTask.set(task);
                }, throwable -> {
                }));
    }

    public void updateTaskToComplete(){

        Task task = mTask.get();
        Task taskCompleted = new Task(task.getName(),task.getAssigneeName(),task.getAssigneeEmail(),task.getId(),true);

        getCompositeDisposable().add(getDataManager()
                .insertTask(taskCompleted)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(tasks -> {

                }, throwable -> {

                }));
    }
}
