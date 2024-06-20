package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void testGetAllTasks() {
        // Given
        Task task1 = new Task(1L, "Test Task 1", "Test Content 1");
        Task task2 = new Task(2L, "Test Task 2", "Test Content 2");
        List<Task> taskList = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(taskList);

        // When
        List<Task> result = dbService.getAllTasks();

        // Then
        assertEquals(2, result.size());
        assertEquals(task1, result.get(0));
        assertEquals(task2, result.get(1));
    }

    @Test
    void testGetTask() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Test Task", "Test Content");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Task result = dbService.getTask(1L);

        // Then
        assertEquals(task, result);
    }

    @Test
    void testGetTaskNotFound() {
        // Given
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(1L));
    }

    @Test
    void testSaveTask() {
        // Given
        Task task = new Task(1L, "Test Task", "Test Content");
        when(taskRepository.save(task)).thenReturn(task);

        // When
        Task result = dbService.saveTask(task);

        // Then
        assertEquals(task, result);
    }

    @Test
    void testDeleteTask() {
        // Given
        Long taskId = 1L;
        doNothing().when(taskRepository).deleteById(taskId);

        // When
        dbService.deleteTask(taskId);

        // Then
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testExistsById() {
        // Given
        Long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        // When
        boolean result = dbService.existsById(taskId);

        // Then
        assertTrue(result);
    }
}
