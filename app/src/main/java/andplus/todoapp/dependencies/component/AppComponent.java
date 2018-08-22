package andplus.todoapp.dependencies.component;

import android.app.Application;

import javax.inject.Singleton;

import andplus.todoapp.TodoApplication;
import andplus.todoapp.dependencies.builder.ActivityBuilder;
import andplus.todoapp.dependencies.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,AppModule.class,ActivityBuilder.class})
public interface AppComponent {

    void inject(TodoApplication application);

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
