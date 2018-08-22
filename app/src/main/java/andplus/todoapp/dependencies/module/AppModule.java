package andplus.todoapp.dependencies.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import andplus.todoapp.data.LocalDataManager;
import andplus.todoapp.data.local.db.AppDatabase;
import andplus.todoapp.data.local.db.AppDbHelper;
import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.data.local.db.DbHelper;
import andplus.todoapp.dependencies.DatabaseInfo;
import andplus.todoapp.util.AppConstants;
import andplus.todoapp.util.rx.AppSchedulerProvider;
import andplus.todoapp.util.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

/**
 * provide application level dependencies
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideAppContext(Application application){
        return application;
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context){
        return Room.databaseBuilder(context,AppDatabase.class,dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Singleton
    @Provides
    DataManager provideLocalDataManager(LocalDataManager localDataManager){
        return localDataManager;
    }

    @Provides
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
