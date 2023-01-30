package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/")
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        System.out.println("CREATE");

        if(ObjectUtils.isEmpty(request.getTitle())){
            return ResponseEntity.badRequest().build();
        }
        if(ObjectUtils.isEmpty(request.getOrder())){
            request.setOrder(0L);
        }
        if(ObjectUtils.isEmpty(request.getCompleted())){
            request.setCompleted(false);
        }
        return ResponseEntity.ok(new TodoResponse(service.add(request)));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        System.out.println("READ ONE");
        TodoEntity result = service.searchById(id);

        return ResponseEntity.ok(new TodoResponse(result));

    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll(){
        System.out.println("READ ALL");
        List<TodoEntity> list = service.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        System.out.println("UPDATE");

        TodoEntity result = service.updateById(id, request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TodoResponse> deleteOne(@PathVariable Long id){
        System.out.println("DELETE");
        service.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        System.out.println("DELETE ALL");
        service.deleteAll();

        return ResponseEntity.ok().build();
    }

}
