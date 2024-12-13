package com.todo.app.service;

import com.todo.app.dao.TodoDao;
import com.todo.app.entity.Todo;
import com.todo.app.repo.TodoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoDao {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    private TodoRepo todoRepo;

    @Override
    public Todo saveTodo(Todo todo) {
        logger.info("Attempting to save a new todo: {}", todo);
        Todo savedTodo = todoRepo.save(todo);
        logger.info("Todo saved successfully: {}", savedTodo);
        return savedTodo;
    }

    @Override
    public List<Todo> getAllTodos() {
        logger.info("Fetching all todos.");
        List<Todo> todos = todoRepo.findAll();
        logger.info("Fetched {} todos.", todos.size());
        return todos;
    }

    @Override
    public Optional<Todo> getTodobyid(int id) {
        logger.info("Fetching todo by id: {}", id);
        Optional<Todo> todo = todoRepo.findById(id);
        if (todo.isPresent()) {
            logger.info("Found todo: {}", todo.get());
        } else {
            logger.warn("No todo found with id: {}", id);
        }
        return todo;
    }

    @Override
    public Optional<Todo> updateTodo(Todo todo, int id) {
        logger.info("Attempting to update todo with id: {}", id);
        Optional<Todo> existingTodo = todoRepo.findById(id);
        if (existingTodo.isPresent()) {
            Todo updatedTodo = existingTodo.get();
            updatedTodo.setName(todo.getName());
            updatedTodo.setTaskName(todo.getTaskName());
            updatedTodo.setCompleted(todo.isCompleted());
            Todo savedUpdatedTodo = todoRepo.save(updatedTodo);
            logger.info("Todo updated successfully: {}", savedUpdatedTodo);
            return Optional.of(savedUpdatedTodo);
        } else {
            logger.error("Todo with id {} not found for update.", id);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteTodo(int id) {
        logger.info("Attempting to delete todo with id: {}", id);
        Optional<Todo> todo = todoRepo.findById(id);
        if (todo.isPresent()) {
            todoRepo.deleteById(id);
            logger.info("Todo with id {} deleted successfully.", id);
            return true;
        } else {
            logger.error("Todo with id {} not found for deletion.", id);
            return false;
        }
    }

    @Override
    public List<Todo> findAllompletedTasks() {
        logger.info("Fetching all completed tasks.");
        List<Todo> completedTodos = todoRepo.findByCompletedTrue();
        logger.info("Fetched {} completed tasks.", completedTodos.size());
        return completedTodos;
    }

    @Override
    public List<Todo> findInCompletedTasks() {
        logger.info("Fetching all incomplete tasks.");
        List<Todo> incompleteTodos = todoRepo.findByCompletedFalse();
        logger.info("Fetched {} incomplete tasks.", incompleteTodos.size());
        return incompleteTodos;
    }
}
