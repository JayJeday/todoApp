package andplus.todoapp.ui.addtask;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

import javax.inject.Inject;

import andplus.todoapp.BR;
import andplus.todoapp.R;

import andplus.todoapp.databinding.ActivityTaskcreationBinding;
import andplus.todoapp.ui.base.BaseActivity;

public class TaskCreationActivity extends BaseActivity<ActivityTaskcreationBinding,TaskCreationViewModel> implements TaskCreationNavigator{

    @Inject
    TaskCreationViewModel mViewModel;

    private ActivityTaskcreationBinding mActivityTaskcreationBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, TaskCreationActivity.class);
    }

    @Override
    public TaskCreationViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_taskcreation;
    }

    @Override
    public int getBindingVariable() {
       return BR.viewModel;
    }


    @Override
    public void createTask() {

        String taskName = mActivityTaskcreationBinding.etTaskName.getText().toString();
        String assignName = mActivityTaskcreationBinding.etAssigneeName.getText().toString();
        String assignEmail = mActivityTaskcreationBinding.etAssigneeEmail.getText().toString();

        clearValidationTv();

        Set<String> validateFailuresFields = mViewModel.taskCreationFieldsValidation(taskName,assignName,assignEmail);

        if (validateFailuresFields.isEmpty()){
            mViewModel.createTask(taskName,assignName,assignEmail);
        }else{
            for (String field : validateFailuresFields) {
                switch (field) {
                    case "email":
                        mActivityTaskcreationBinding.tvAssigneeEmail.setVisibility(View.VISIBLE);
                        break;
                    case "task name":
                        mActivityTaskcreationBinding.tvTaskName.setVisibility(View.VISIBLE);
                        break;
                    case "assignedName":
                        mActivityTaskcreationBinding.tvAssiName.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this, "throwable.getMessage();", Toast.LENGTH_LONG).show();
    }

    @Override
    public void validated() {
        Toast.makeText(this, "Successfully saved in the Database", Toast.LENGTH_LONG).show();
        mActivityTaskcreationBinding.etAssigneeEmail.setText("");
        mActivityTaskcreationBinding.etAssigneeName.setText("");
        mActivityTaskcreationBinding.etTaskName.setText("");

    }

    private void clearValidationTv() {
        mActivityTaskcreationBinding.tvAssigneeEmail.setVisibility(View.GONE);
        mActivityTaskcreationBinding.tvTaskName.setVisibility(View.GONE);
        mActivityTaskcreationBinding.tvTaskName.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTaskcreationBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
