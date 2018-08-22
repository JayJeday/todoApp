package andplus.todoapp.ui.detail;

import andplus.todoapp.data.Task;

public interface TaskDetailNavigator {

    void completeTask();

    void setupViews(Task task);
}
