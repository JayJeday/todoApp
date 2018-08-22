package andplus.todoapp.ui.detail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TaskDetailFragmentProvider {

    @ContributesAndroidInjector(modules = TaskDetailFragmentModule.class)
    abstract TaskDetailFragment provideTaskDetailFragmentFactory();
}
