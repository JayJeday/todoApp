package andplus.todoapp;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import andplus.todoapp.dependencies.component.DaggerAppComponent;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class TodoApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder().application(this).build().inject(this);
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

}
