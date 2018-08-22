package andplus.todoapp.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import andplus.todoapp.data.Task;
import andplus.todoapp.ui.task.TasksAdapter;

public class BindingUtils {

    private BindingUtils() {
        //class cant be instantiated
    }

    @BindingAdapter({"adapter"})
    public static void addTaskItems(RecyclerView recyclerView, List<Task> tasks) {
        TasksAdapter adapter = (TasksAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(tasks);
        }
    }
}
