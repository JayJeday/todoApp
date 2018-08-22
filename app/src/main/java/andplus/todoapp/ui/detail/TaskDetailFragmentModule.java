package andplus.todoapp.ui.detail;

import android.arch.lifecycle.ViewModelProvider;



import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.util.ViewModelProviderFactory;
import andplus.todoapp.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class TaskDetailFragmentModule {

    @Provides
    TaskDetailViewModel provideTaskDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new TaskDetailViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideTaskDetailViewModelFactory(TaskDetailViewModel taskDetailViewModel) {
        return new ViewModelProviderFactory<>(taskDetailViewModel);
    }
}
