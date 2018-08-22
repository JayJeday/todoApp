package andplus.todoapp.ui.addtask;

import andplus.todoapp.data.LocalDataManager;
import andplus.todoapp.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class TaskCreationActivityModule {

    @Provides
    TaskCreationViewModel provideTaskCreationViewModel(LocalDataManager dataManager, SchedulerProvider scheduler){
        return new TaskCreationViewModel(dataManager,scheduler);
    }

}
