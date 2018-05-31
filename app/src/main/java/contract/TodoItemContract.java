package contract;

/**
 * Created by chike on 31/05/2018.
 */

public interface TodoItemContract {
    /**
     * deletes a todo item from the database.
     *
     * @param todoId - current todo ID
     */
    void onDeleteIconClick(int todoId);
}
