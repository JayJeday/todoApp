package andplus.todoapp.ui.task;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import andplus.todoapp.data.LocalDataManager;
import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.util.ViewModelProviderFactory;
import andplus.todoapp.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class TaskFragmentModule {

    @Provides
    TaskViewModel provideTaskViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new TaskViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory provideTaskViewModelFactory(TaskViewModel taskViewModel) {
        return new ViewModelProviderFactory<>(taskViewModel);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TaskFragment taskFragment) {
        return new LinearLayoutManager(taskFragment.getActivity());
    }

    @Provides
    TasksAdapter provideTasksAdapter(){
        return new TasksAdapter(new ArrayList<>());
    }


}
