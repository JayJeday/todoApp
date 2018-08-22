package andplus.todoapp.ui.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements BaseFragment.Callback{

    private T mViewDataBinding;
    private  V mViewModel;

    public abstract V  getViewModel();

    public T getViewDataBinding(){
        return mViewDataBinding;
    }

    public abstract
    @LayoutRes int getLayoutId();

    public abstract int getBindingVariable();

    private void performDependencyInjection(){
        AndroidInjection.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel(): mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(),mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
