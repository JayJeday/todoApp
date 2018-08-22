package andplus.todoapp.ui;



import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new MainViewModel(dataManager,schedulerProvider);
    }

}
