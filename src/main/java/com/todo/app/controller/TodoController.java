package com.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.app.entity.Todo;
import com.todo.app.mapper.TodoMapper;

@RequestMapping("")
@Controller
public class TodoController {

    @Autowired
    TodoMapper todoMapper;

    @RequestMapping("/")
    public String index(Model model) throws JsonProcessingException {
        List<Todo> list = todoMapper.selectIncomplete();
        List<Todo> doneList = todoMapper.selectComplete();
        List<Todo> highList = todoMapper.selectHigh();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTodos = objectMapper.writeValueAsString(list);

        model.addAttribute("todos", list);
        model.addAttribute("doneTodos", doneList);
        model.addAttribute("highTodos", highList);
        model.addAttribute("jsonTodos", jsonTodos);

        return "index";
    }

    @RequestMapping("/add")
    public String add(Todo todo) {
        todoMapper.add(todo);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String update(Todo todo) {
        todoMapper.update(todo);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete() {
        todoMapper.delete();
        return "redirect:/";
    }
}