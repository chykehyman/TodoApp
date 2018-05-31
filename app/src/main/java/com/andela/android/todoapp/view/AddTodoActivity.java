package com.andela.android.todoapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.andela.android.todoapp.R;

import util.TodoDAO;

public class AddTodoActivity extends AppCompatActivity {
    Button saveTodo;
    TextInputEditText mEditText;
    Intent intent;

    private TodoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        dao = new TodoDAO(this);

        intent = new Intent(this, MainActivity.class);

        mEditText = findViewById(R.id.text_todo_edit);
        saveTodo = findViewById(R.id.button_save_todo);
        saveTodo.setOnClickListener(saveTodoItem);

    }

    private View.OnClickListener saveTodoItem = new View.OnClickListener() {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;

            if (imm.isAcceptingText()) {
                mEditText.clearFocus();
                imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
            }

            if (TextUtils.isEmpty(mEditText.getText())) {
                // Display failure information
                Toast.makeText(getApplicationContext(), "enter a todo", Toast.LENGTH_SHORT).show();
            } else {
                // Get entered text
                String todoTextValue = mEditText.getText().toString();
                mEditText.setText("");

                // Add text to the database
                dao.createTodo(todoTextValue);

                startActivity(intent);
            }
        }
    };

}
