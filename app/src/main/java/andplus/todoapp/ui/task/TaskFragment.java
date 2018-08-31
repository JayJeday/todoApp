package andplus.todoapp.ui.task;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import andplus.todoapp.BR;
import andplus.todoapp.R;
import andplus.todoapp.databinding.FragmentTaskBinding;
import andplus.todoapp.ui.addtask.TaskCreationActivity;
import andplus.todoapp.ui.base.BaseFragment;
import andplus.todoapp.ui.detail.TaskDetailFragment;


public class TaskFragment extends BaseFragment<FragmentTaskBinding,TaskViewModel> implements TaskNavigator{

    public static final String ARGUMENT_TASK_ID = "TASK_ID";

    private static final int REQUEST_CHANGE = 0;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    TasksAdapter mTasksAdapter;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    FragmentTaskBinding mFragmentTaskBinding;

    private TaskViewModel mTaskViewModel;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public TaskViewModel getViewModel() {
        mTaskViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TaskViewModel.class);
        return mTaskViewModel;
    }

    @Override
    public void openTaskDetail(String id) {

        TaskDetailFragment taskDetailFragment = (TaskDetailFragment) TaskDetailFragment.newInstance(id);
        taskDetailFragment.setTargetFragment(this,REQUEST_CHANGE);

        getBaseActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.fragmentContainer,taskDetailFragment).commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_CHANGE){
            mTaskViewModel.fetchTasks();
        }
    }

    @Override
    public void openTaskCreator() {
        getBaseActivity().startActivity(TaskCreationActivity.newIntent(getBaseActivity()));
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mTaskViewModel.setNavigator(this);
        mTasksAdapter.setTaskNavigator(this);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.task_menu,menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.all:
                mTasksAdapter.addItems(mTaskViewModel.taskList);
                break;
            case R.id.incompleted:
              mTasksAdapter.addItems(mTaskViewModel.filterForIncomplete());
                break;
            case R.id.completed:
                mTasksAdapter.addItems(mTaskViewModel.filterForCompleted());
                break;
        }
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentTaskBinding = getViewDataBinding();
        setHasOptionsMenu(true);
        setUpRecycleView();
        subscribeToLiveData();
        setUpFab();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setUpRecycleView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseActivity());
         mFragmentTaskBinding.TaskRv.setLayoutManager(manager);
        mFragmentTaskBinding.TaskRv.setItemAnimator(new DefaultItemAnimator());
         mFragmentTaskBinding.TaskRv.setAdapter(mTasksAdapter);
    }

    private void subscribeToLiveData() {
        mTaskViewModel.getTaskLiveData().observe(this, tasks -> mTaskViewModel.addTaskItemsToList(tasks));
    }

    private void setUpFab(){
        FloatingActionButton fab = mFragmentTaskBinding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTaskCreator();
            }
        });
    }

}
