package andplus.todoapp.ui.detail;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

import javax.inject.Inject;

import andplus.todoapp.BR;
import andplus.todoapp.R;

import andplus.todoapp.data.Task;
import andplus.todoapp.databinding.FragmentDetailBinding;
import andplus.todoapp.ui.base.BaseFragment;
import andplus.todoapp.ui.base.BaseViewModel;
import andplus.todoapp.ui.task.TaskFragment;
import andplus.todoapp.ui.task.TaskViewModel;

public class TaskDetailFragment extends BaseFragment<FragmentDetailBinding, TaskDetailViewModel> implements TaskDetailNavigator {

    FragmentDetailBinding mFragmentDetailBinding;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private TaskDetailViewModel mTaskDetailViewModel;

    private static final String EXTRA_CHANGE = "TaskDetailFragment.changes";

    public static Fragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(TaskFragment.ARGUMENT_TASK_ID, id);
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTaskDetailViewModel.setNavigator(this);

        mTaskDetailViewModel.taskId.set((String) (getArguments() != null ? getArguments().get(TaskFragment.ARGUMENT_TASK_ID) : null));

        mTaskDetailViewModel.fetchSelectedTask(mTaskDetailViewModel.taskId.get());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentDetailBinding = getViewDataBinding();

        mFragmentDetailBinding.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, mTaskDetailViewModel.mTask.get().getAssigneeEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, mTaskDetailViewModel.mTask.get().getName());

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public TaskDetailViewModel getViewModel() {
        mTaskDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TaskDetailViewModel.class);
        return mTaskDetailViewModel;
    }

    @Override
    public void completeTask() {
        Toast.makeText(getBaseActivity(),"Task completed",Toast.LENGTH_LONG).show();
        mFragmentDetailBinding.btnCompleteTask.setEnabled(false);
        sendResult(Activity.RESULT_OK);
    }

    @Override
    public void setupViews(Task task) {
        mFragmentDetailBinding.taskId.setText(task.getId());
        mFragmentDetailBinding.taskName.setText(task.getName());
        mFragmentDetailBinding.taskAssigned.setText(task.getAssigneeName());
        mFragmentDetailBinding.taskEmail.setText(task.getAssigneeEmail());
        mFragmentDetailBinding.taskCompleted.setText(task.isCompleted() + "");

        if (task.isCompleted()) {
            mFragmentDetailBinding.btnCompleteTask.setEnabled(false);
        }
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CHANGE, true);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
