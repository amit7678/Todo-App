package com.todo.app.repo;
import com.todo.app.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface TodoRepo  extends JpaRepository<Todo,Integer> {
    public List<Todo>findByCompletedTrue();
    public List<Todo>findByCompletedFalse();

}
