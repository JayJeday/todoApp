package andplus.todoapp.dependencies.builder;

import andplus.todoapp.MainActivity;

import andplus.todoapp.ui.MainActivityModule;
import andplus.todoapp.ui.addtask.TaskCreationActivity;
import andplus.todoapp.ui.addtask.TaskCreationActivityModule;
import andplus.todoapp.ui.detail.TaskDetailFragmentProvider;
import andplus.todoapp.ui.task.TaskFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = TaskCreationActivityModule.class)
    abstract TaskCreationActivity bindTaskCreationActivity();

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            TaskFragmentProvider.class,
            TaskDetailFragmentProvider.class
    })
    abstract MainActivity bindMainActivity();



}
