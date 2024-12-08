package com.todo.app.service;

import com.todo.app.dao.TodoDao;
import com.todo.app.entity.Todo;
import com.todo.app.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoDao {

    @Autowired
    private TodoRepo todoRepo;
    @Override
    public Todo saveTodo(Todo todo) {
        return  todoRepo.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepo.findAll();
    }

    @Override
    public Optional<Todo> getTodobyid(int id) {
        return todoRepo.findById(id);
    }

    @Override
    public Optional<Todo> updateTodo(Todo todo, int id) {
        Optional<Todo> existingTodo=todoRepo.findById(id);
        if(existingTodo.isPresent()){
            Todo updatedTodo=existingTodo.get();

            updatedTodo.setName(todo.getName());
            updatedTodo.setTaskName(todo.getTaskName());
            updatedTodo.setCompleted(todo.isCompleted());

            return Optional.of(todoRepo.save(updatedTodo));
        }
        return Optional.empty();
    }

    public boolean deleteTodo(int id) {
        Optional<Todo> todo = todoRepo.findById(id);
        if (todo.isPresent()) {
            todoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Todo> findAllompletedTasks() {
        return todoRepo.findByCompletedTrue();
    }

    @Override
    public List<Todo> findInCompletedTasks() {
        return todoRepo.findByCompletedFalse();
    }


}
