package andplus.todoapp.ui;

import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.ui.base.BaseViewModel;
import andplus.todoapp.util.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
