package andplus.todoapp.ui.task;


import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;

import andplus.todoapp.data.Task;
import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.ui.base.BaseViewModel;
import andplus.todoapp.util.rx.SchedulerProvider;


public class TaskViewModel extends BaseViewModel<TaskNavigator> {

    private final MutableLiveData<List<Task>> taskLiveData;

    public final ObservableList<Task> taskList = new ObservableArrayList<>();

    //notify when group list is empty
    public final ObservableBoolean empty = new ObservableBoolean(false);


    public TaskViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        taskLiveData = new MutableLiveData<>();
        fetchTasks();
    }

    public MutableLiveData<List<Task>> getTaskLiveData() {
        return taskLiveData;
    }

    public void addTaskItemsToList(List<Task> tasks){
        taskList.clear();
        taskList.addAll(tasks);
    }

    public void fetchTasks() {
        getCompositeDisposable().add(getDataManager()
                .getAllTasks()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(tasks -> {
                    if (tasks != null){
                        taskLiveData.setValue(tasks);
                        if(taskLiveData.getValue().isEmpty()){
                            empty.set(true);
                        }else {
                            empty.set(false);
                        }
                    }
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Task> filterForCompleted(){
        List<Task> completedList = taskList.stream().filter(task -> task.isCompleted()).collect(Collectors.toList());
        return completedList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Task> filterForIncomplete(){
        List<Task> incompleteList = taskList.stream().filter(task -> task.isActive()).collect(Collectors.toList());
        return incompleteList;
    }
}
