package andplus.todoapp.ui.task;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import andplus.todoapp.data.Task;
import andplus.todoapp.databinding.ItemTaskViewBinding;
import andplus.todoapp.util.BaseViewHolder;

public class TasksAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private TaskNavigator mTaskNavigator;

    private List<Task> mTaskList;

    public TasksAdapter(List<Task>taskList){
        this.mTaskList = taskList;
    }

    public void setTaskNavigator(TaskNavigator taskNavigator) {
        mTaskNavigator = taskNavigator;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTaskViewBinding viewBinding =   ItemTaskViewBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
       return new TaskViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public void addItems(List<Task> taskList){
        mTaskList.clear();
        mTaskList.addAll(taskList);
        notifyDataSetChanged();
    }

    public void clearItems(){
        mTaskList.clear();
    }


    public class TaskViewHolder extends BaseViewHolder implements TaskNavigator{

      private  TaskItemViewModel mTaskItemViewModel;

      private  ItemTaskViewBinding binding;

        public TaskViewHolder(@NonNull ItemTaskViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.binding = viewBinding;
        }

        @Override
        public void onBind(int position) {
            final Task task = mTaskList.get(position);
            mTaskItemViewModel = new TaskItemViewModel(task,this);
            binding.setViewModel(mTaskItemViewModel);
            binding.executePendingBindings();
        }

        @Override
        public void openTaskDetail(String id) {
            mTaskNavigator.openTaskDetail(id);

        }

        @Override
        public void openTaskCreator() {

        }

        @Override
        public void handleError(Throwable throwable) {

        }
    }
}
