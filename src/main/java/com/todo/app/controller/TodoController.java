package com.todo.app.controller;
import com.todo.app.entity.Todo;
import com.todo.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;


    @PostMapping("/create")
    public ResponseEntity<Todo> createtodo(@RequestBody Todo todo)
    {
        return ResponseEntity.ok(todoService.saveTodo(todo));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos()
    {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Todo>>getTodoById(@PathVariable int id)
    {
        Optional<Todo>todo=todoService.getTodobyid(id);
        if(todo.isPresent()){
            return ResponseEntity.ok(Optional.of(todo.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Todo>>updateTodo(@RequestBody Todo todo,@PathVariable int id){
        Optional<Todo>updatedTodo=todoService.updateTodo(todo,id);
        if(updatedTodo.isPresent()){
            return ResponseEntity.ok(updatedTodo);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        boolean isDeleted = todoService.deleteTodo(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/allcompletedtasks")
    public ResponseEntity<List<Todo>>getAllCompletedTasks(){
        return ResponseEntity.ok(todoService.findAllompletedTasks());
    }

    @GetMapping("/alluncompletedtasks")
    public ResponseEntity<List<Todo>>getAllInCompletedTasks(){
        return ResponseEntity.ok(todoService.findInCompletedTasks());
    }





}
