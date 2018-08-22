package andplus.todoapp;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import android.support.v7.widget.Toolbar;
import javax.inject.Inject;

import andplus.todoapp.databinding.ActivityMainBinding;
import andplus.todoapp.ui.MainViewModel;
import andplus.todoapp.ui.base.BaseActivity;

import andplus.todoapp.ui.task.TaskFragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends BaseActivity< ActivityMainBinding,MainViewModel>implements HasSupportFragmentInjector {

    private Toolbar mToolbar;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;

    @Inject
    MainViewModel mMainViewModel;

    ActivityMainBinding mActivityMainBinding;

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment taskFragment = createTaskFragment();
        goToFragment(getSupportFragmentManager(),taskFragment,R.id.fragmentContainer);

    }

    private Fragment createTaskFragment(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if(fragment == null){
            fragment = TaskFragment.newInstance();
        }
        return fragment;
    }


    private void goToFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
