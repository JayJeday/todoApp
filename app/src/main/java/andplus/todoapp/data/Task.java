package andplus.todoapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "taskid")
    private final String mId;

    @Nullable
    @ColumnInfo(name = "name")
    private final String mName;

    @Nullable
    @ColumnInfo(name = "assigneename")
    private final String mAssigneeName;

    @Nullable
    @ColumnInfo(name = "assigneeemail")
    private final String mAssigneeEmail;

    @ColumnInfo(name = "completed")
    private final boolean mCompleted;

    @Ignore
    public Task(@Nullable String name, @Nullable String assigneeName, @Nullable String assigneeEmail) {
        this(name, assigneeName,assigneeEmail, UUID.randomUUID().toString(), false);
    }

    @Ignore
    public Task(@Nullable String name, @Nullable String assigneeName,@Nullable String assigneeEmail, @NonNull String id) {
        this(name, assigneeName,assigneeEmail, id, false);
    }

    @Ignore
    public Task(@Nullable String name, @Nullable String assigneeName,@Nullable String assigneeEmail, boolean completed) {
        this(name, assigneeName,assigneeEmail, UUID.randomUUID().toString(), completed);
    }

    public Task(@Nullable String name, @Nullable String assigneeName,@Nullable String assigneeEmail,
                @NonNull String id, boolean completed) {
        mId = id;
        mName = name;
        mAssigneeName = assigneeName;
        mAssigneeEmail = assigneeEmail;
        mCompleted = completed;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getName() {
        return mName;
    }


    @Nullable
    public String getAssigneeName() {
        return mAssigneeName;
    }

    @Nullable
    public String getAssigneeEmail() {
        return mAssigneeEmail;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return mCompleted == task.mCompleted &&
                Objects.equals(mId, task.mId) &&
                Objects.equals(mName, task.mName) &&
                Objects.equals(mAssigneeName, task.mAssigneeName) &&
                Objects.equals(mAssigneeEmail, task.mAssigneeEmail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mName, mAssigneeName, mAssigneeEmail, mCompleted);
    }
}
