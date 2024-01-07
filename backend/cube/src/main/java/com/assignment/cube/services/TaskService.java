package com.assignment.cube.services;

import com.assignment.cube.exceptions.*;
import com.assignment.cube.model.Task;
import com.assignment.cube.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            log.error("Error while retrieving all tasks", e);
            throw new TaskRetrievalException("Error while retrieving all tasks", e);
        }
    }

    public Optional<Task> getTaskById(Long id) {
        try {
            return taskRepository.findById(id);
        } catch (Exception e) {
            log.error("Error while retrieving task with id: {}", id, e);
            throw new TaskRetrievalException("Error while retrieving task with id: " + id, e);
        }
    }

    public Task createTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            log.error("Error while creating a task", e);
            throw new TaskCreationException("Error while creating a task", e);
        }
    }

    public Task updateTask(Long id, Task updatedTask) {
        try {
            Optional<Task> existingTask = taskRepository.findById(id);

            if (existingTask.isPresent()) {
                Task taskToUpdate = existingTask.get();
                taskToUpdate.setTitle(updatedTask.getTitle());
                taskToUpdate.setDescription(updatedTask.getDescription());
                return taskRepository.save(taskToUpdate);
            } else {
                throw new TaskNotFoundException("Task not found with id: " + id);
            }
        } catch (Exception e) {
            log.error("Error while updating task with id: {}", id, e);
            throw new TaskUpdateException("Error while updating task with id: " + id, e);
        }
    }

    public void deleteTask(Long id) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);

            if (taskOptional.isPresent()) {
                taskRepository.deleteById(id);
                log.info("Deleted task with id: {}", id);
            } else {
                log.warn("Task not found with id: {}", id);
                throw new TaskNotFoundException("Task not found with id: " + id);
            }
        }catch (TaskNotFoundException e){
            throw e;
        }catch (Exception e) {
            log.error("Error while deleting task with id: {}", id, e);
            throw new TaskDeletionException("Error while deleting task with id: " + id, e);
        }
    }

}