package andplus.todoapp.ui.task;

public interface TaskNavigator {

    void openTaskDetail(String id);

    void openTaskCreator();

    void handleError(Throwable throwable);
}
