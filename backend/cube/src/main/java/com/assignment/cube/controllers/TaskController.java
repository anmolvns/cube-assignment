package com.assignment.cube.controllers;

import com.assignment.cube.constants.Constants;
import com.assignment.cube.exceptions.*;
import com.assignment.cube.model.Task;
import com.assignment.cube.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constants.TASK_BASE_URL)
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(Constants.TASK_GET_ALL)
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            List<Task> tasks = taskService.getAllTasks();
            log.info("Retrieved all tasks successfully");
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (TaskRetrievalException e) {
            log.error("Error while retrieving all tasks", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(Constants.TASK_GET_BY_ID + "/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        try {
            Optional<Task> task = taskService.getTaskById(id);
            if (task.isPresent()) {
                log.info("Retrieved task with id: {} successfully", id);
                return new ResponseEntity<>(task.get(), HttpStatus.OK);
            } else {
                log.info("Task not found with id: {}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (TaskRetrievalException e) {
            log.error("Error while retrieving task with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(Constants.TASK_CREATE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            log.info("Created a new task with id: {}", createdTask.getId());
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (TaskCreationException e) {
            log.error("Error while creating a task", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(Constants.TASK_UPDATE + "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            log.info("Updated task with id: {}", id);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (TaskUpdateException | TaskNotFoundException e) {
            log.info("Task not found or error while updating task with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TaskRetrievalException e) {
            log.error("Error while updating task with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(Constants.TASK_DELETE + "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            log.info("Deleted task with id: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            log.info("Task not found or error while deleting task with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TaskDeletionException e) {
            log.error("Error while deleting task with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}