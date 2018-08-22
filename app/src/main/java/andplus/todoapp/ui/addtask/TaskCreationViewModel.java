package andplus.todoapp.ui.addtask;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.HashSet;
import java.util.Set;

import andplus.todoapp.data.LocalDataManager;
import andplus.todoapp.data.Task;
import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.ui.base.BaseViewModel;
import andplus.todoapp.util.rx.SchedulerProvider;

public class TaskCreationViewModel extends BaseViewModel<TaskCreationNavigator> {

    public TaskCreationViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public Set<String> taskCreationFieldsValidation(String taskName, String assignedName, String assignedEmail) {
        //list that hold witch field is validated
        Set<String> mapValidation = new HashSet<>();
        if (TextUtils.isEmpty(assignedEmail) || !isEmailValid(assignedEmail)) {
            mapValidation.add("email");
        }

        if (TextUtils.isEmpty(taskName)) {
            mapValidation.add("task name");
        }

        if (TextUtils.isEmpty(assignedName)) {
            mapValidation.add("assignedName");
        }
        return mapValidation;
    }

    public void createTask(String taskName, String name, String email) {
        //insert to the datatask
        Task task = new Task(taskName, name, email);
        getCompositeDisposable().add(getDataManager()
                .insertTask(task)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(isSaved -> {
                    if (isSaved){
                    getNavigator().validated();
                    }
                }, throwable -> {
                    getNavigator().onError(throwable);
                })
        );
    }


    public void onClickCreateTask() {
        getNavigator().createTask();
    }

    /*
   Validate email input
    */
    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
