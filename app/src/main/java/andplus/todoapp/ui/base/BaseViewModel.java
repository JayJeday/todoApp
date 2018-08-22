package andplus.todoapp.ui.base;

import android.arch.lifecycle.ViewModel;

import andplus.todoapp.data.LocalDataManager;
import andplus.todoapp.data.local.db.DataManager;
import andplus.todoapp.util.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<T> extends ViewModel {

    private final DataManager mDataManager;

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private T mNavigator;

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        mDataManager = dataManager;
        mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public T getNavigator() {
        return mNavigator;
    }

    public void setNavigator(T navigator) {
        mNavigator = navigator;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}
