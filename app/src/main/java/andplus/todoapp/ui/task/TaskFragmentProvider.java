package andplus.todoapp.ui.task;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TaskFragmentProvider {

    @ContributesAndroidInjector(modules = TaskFragmentModule.class)
    abstract TaskFragment provideTaskFragmentFactory();
}
