package andplus.todoapp.ui.task;

import android.databinding.ObservableField;

import andplus.todoapp.data.Task;

public class TaskItemViewModel {

    public final ObservableField<String>id;

    public final ObservableField<String>name;

    public final ObservableField<String> assigneeName;

    public final ObservableField<String>email;

    public final ObservableField<Boolean>isCompleted;

    public final TaskNavigator mListener;

    public final Task mTask;

    public TaskItemViewModel(Task task,TaskNavigator navigator) {
        this.mTask = task;
        this.mListener = navigator;

        this.id = new ObservableField<>(task.getId());
        this.name = new ObservableField<>(task.getName());
        this.assigneeName = new ObservableField<>(task.getAssigneeName());
        this.email = new ObservableField<>(task.getAssigneeEmail());
        this.isCompleted = new ObservableField<>(task.isCompleted());
    }

    public void onItemClick(){
        mListener.openTaskDetail(mTask.getId());
    }

}
