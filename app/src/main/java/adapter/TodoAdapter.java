package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.android.todoapp.R;

import java.util.List;

import contract.TodoItemContract;
import model.Todo;

/**
 * Created by chike on 30/05/2018.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private final List<Todo> todos;
    TodoItemContract todoItemContract;

    public TodoAdapter(List<Todo> todos, TodoItemContract todoItemContract) {
        this.todos = todos;
        this.todoItemContract = todoItemContract;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.todo_item_card, parent, false);
       return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, final int position) {
        final String todoTextItem = todos.get(position).getText();

        holder.todoText.setText(todoTextItem);

        holder.itemView.findViewById(R.id.image_trash_icon)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        todoItemContract.onDeleteIconClick(todos.get(position).getId());
                    }
                });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return todos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView todoText;
        ViewHolder(View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.text_todo_input);
        }
    }
}
