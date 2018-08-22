package andplus.todoapp.ui.addtask;

public interface TaskCreationNavigator {

    void createTask();

    void onError(Throwable throwable);

    void validated();

}
