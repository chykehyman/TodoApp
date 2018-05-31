package com.andela.android.todoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.andela.android.todoapp.R;

import java.util.List;

import adapter.TodoAdapter;
import contract.TodoItemContract;
import model.Todo;
import util.TodoDAO;

public class MainActivity extends AppCompatActivity implements TodoItemContract {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    TodoDAO dao;
    TextView noTodoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noTodoText = findViewById(R.id.text_no_todo);

        // Create DAO object
        dao = new TodoDAO(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        final Intent mIntent = new Intent(this, AddTodoActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mIntent);
            }
        });

        initializeRecyclerView();

        displayTodoList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.layout_recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
    }

    public void displayTodoList() {
        List<Todo> listOfTodos = dao.getTodos();

        if (listOfTodos.size() > 0) {
            noTodoText.setVisibility(View.GONE);
            RecyclerView.Adapter adapter = new TodoAdapter(listOfTodos, this);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            noTodoText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDeleteIconClick(int todoId) {
        dao.deleteTodo(todoId);

        displayTodoList();
        Toast toast = Toast.makeText(this, "Todo item deleted", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
