package com.todo.app.dao;
import com.todo.app.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoDao {
    public Todo saveTodo(Todo todo);
    public List<Todo> getAllTodos();
    public Optional<Todo> getTodobyid(int id);
    public Optional<Todo> updateTodo(Todo todo,int id);
    public boolean deleteTodo(int id);
    public List<Todo>findAllompletedTasks();
    public List<Todo>findInCompletedTasks();
}
