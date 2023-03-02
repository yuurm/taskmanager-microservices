package com.yuurm.taskmanagertasks.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yuurm.taskmanagerentities.entity.Category;
import com.yuurm.taskmanagerentities.entity.User;
import com.yuurm.taskmanagertasks.feign.UserFeignClient;
import com.yuurm.taskmanagertasks.search.CategorySearchValues;
import com.yuurm.taskmanagertasks.service.CategoryService;
import com.yuurm.taskmanagerutils.utils.rest.resttemplate.UserRestBuilder;
import com.yuurm.taskmanagerutils.utils.rest.webclient.UserWebClientBuilder;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/category")
public class CategoryController {


    private CategoryService categoryService;


    private UserRestBuilder userRestBuilder;


    private UserWebClientBuilder userWebClientBuilder;


    private UserFeignClient userFeignClient;


    public CategoryController(CategoryService categoryService, UserFeignClient userFeignClient, UserRestBuilder userRestBuilder, UserWebClientBuilder userWebClientBuilder) {
        this.categoryService = categoryService;
        this.userRestBuilder = userRestBuilder;
        this.userWebClientBuilder = userWebClientBuilder;
        this.userFeignClient = userFeignClient;
    }

    @PostMapping("/all")
    public List<Category> findAll(@RequestBody Long userId) {
        return categoryService.findAll(userId);
    }


    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {


        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }


        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }



        ResponseEntity<User> result =  userFeignClient.findUserById(category.getUserId());

        if (result == null){
            return new ResponseEntity("система пользователей недоступна, попробуйте позже", HttpStatus.NOT_FOUND);
        }

        if (result.getBody() != null){
            return ResponseEntity.ok(categoryService.add(category));
        }


        return new ResponseEntity("user id=" + category.getUserId() + " not found", HttpStatus.NOT_ACCEPTABLE);

    }


    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Category category) {

        // проверка на обязательные параметры
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        // если передали пустое значение title
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        // save работает как на добавление, так и на обновление
        categoryService.update(category);

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {


        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 без объектов (операция прошла успешно)
    }



    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {


        if (categorySearchValues.getUserId() == null || categorySearchValues.getUserId() == 0) {
            return new ResponseEntity("missed param: user id", HttpStatus.NOT_ACCEPTABLE);
        }


        List<Category> list = categoryService.findByTitle(categorySearchValues.getTitle(), categorySearchValues.getUserId());

        return ResponseEntity.ok(list);
    }



    @PostMapping("/id")
    public ResponseEntity<Category> findById(@RequestBody Long id) {

        Category category = null;


        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

}
