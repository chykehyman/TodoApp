package util;

/**
 * Created by chike on 29/05/2018.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.Todo;

/**
 * TODOs DAO object.
 *
 * @author itcuties
 *
 */
public class TodoDAO {

    private SQLiteDatabase db;
    private TodoSQLiteHelper dbHelper;

    public TodoDAO(Context context) {
        dbHelper = new TodoSQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Close the db
    public void close() {
        db.close();
    }

    /**
     * Create new TODO object
     * @param todoText - todo text string
     */
    public void createTodo(String todoText) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("todo", todoText);
        // Insert into DB
        db.insert("todos", null, contentValues);
    }

    /**
     * Delete TODO object
     * @param todoId - id of the todo-item to delete
     */
    public void deleteTodo(int todoId) {
        // Delete from DB where id match
        db.delete("todos", "_id = " + todoId, null);
    }

    /**
     * Get all TODOs.
     * @return - list of todo-items
     */
    public List<Todo> getTodos() {
        List<Todo> todoList = new ArrayList<>();

        // Name of the columns we want to select
        String[] tableColumns = new String[] {"_id","todo"};

        // Query the database
        Cursor cursor = db.query("todos", tableColumns, null,
                null, null, null, null);
        cursor.moveToFirst();

        // Iterate the results
        while (!cursor.isAfterLast()) {
            Todo todo = new Todo();
            // Take values from the DB
            todo.setId(cursor.getInt(0));
            todo.setText(cursor.getString(1));

            // Add to the DB
            todoList.add(todo);

            // Move to the next result
            cursor.moveToNext();
        }

        cursor.close();

        return todoList;
    }

}
