package com.todo.app.controller;
import com.todo.app.entity.Todo;
import com.todo.app.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    private final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @PostMapping("/create")
    public ResponseEntity<Todo> createtodo(@RequestBody Todo todo) {
        logger.info("Received request to create todo: {}", todo);
        try {
            Todo savedTodo = todoService.saveTodo(todo);
            logger.info("Todo created successfully: {}", savedTodo);
            return ResponseEntity.ok(savedTodo);
        } catch (Exception e) {
            logger.error("Error occurred while creating todo: {}", todo, e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos() {
        logger.info("Received request to fetch all todos.");
        List<Todo> todoController = todoService.getAllTodos();
        logger.info("Fetched {} todos.", todoController.size());
        return ResponseEntity.ok(todoController);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable int id) {
        logger.info("Received request to fetch todo by id: {}", id);
        Optional<Todo> todo = todoService.getTodobyid(id);
        if (todo.isPresent()) {
            logger.info("Found todo with id: {}", id);
            return ResponseEntity.ok(todo);
        } else {
            logger.error("No todo found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Todo>> updateTodo(@RequestBody Todo todo, @PathVariable int id) {
        logger.info("Received request to update todo with id: {}", id);
        Optional<Todo> updatedTodo = todoService.updateTodo(todo, id);
        if (updatedTodo.isPresent()) {
            logger.info("Todo updated successfully with id: {}", id);
            return ResponseEntity.ok(updatedTodo);
        } else {
            logger.error("No todo found with id: {} to update.", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        logger.info("Received request to delete todo with id: {}", id);
        boolean isDeleted = todoService.deleteTodo(id);

        if (isDeleted) {
            logger.info("Todo with id: {} deleted successfully.", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.error("No todo found with id: {} to delete.", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allcompletedtasks")
    public ResponseEntity<List<Todo>> getAllCompletedTasks() {
        logger.info("Received request to fetch all completed tasks.");
        List<Todo> completedTasks = todoService.findAllompletedTasks();
        logger.info("Fetched {} completed tasks.", completedTasks.size());
        return ResponseEntity.ok(completedTasks);
    }

    @GetMapping("/alluncompletedtasks")
    public ResponseEntity<List<Todo>> getAllInCompletedTasks() {
        logger.info("Received request to fetch all incomplete tasks.");
        List<Todo> incompleteTasks = todoService.findInCompletedTasks();
        logger.info("Fetched {} incomplete tasks.", incompleteTasks.size());
        return ResponseEntity.ok(incompleteTasks);
    }
}
