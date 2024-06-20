package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    public TaskControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFetchTasks() {
        // Given
        List<Task> tasks = Arrays.asList(new Task(1L, "Test title", "Test content"));
        List<TaskDto> taskDtos = Arrays.asList(new TaskDto(1L, "Test title", "Test content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        // When
        ResponseEntity<List<TaskDto>> response = taskController.getTasks();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Test title", response.getBody().get(0).getTitle());
        assertEquals("Test content", response.getBody().get(0).getContent());
    }

    @Test
    void shouldFetchTask() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "Test title", "Test content");
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.getTask(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test title", response.getBody().getTitle());
        assertEquals("Test content", response.getBody().getContent());
    }

    @Test
    void shouldDeleteTask() {
        // Given
        doNothing().when(dbService).deleteTask(1L);

        // When
        ResponseEntity<Void> response = taskController.deleteTask(1L);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(dbService, times(1)).deleteTask(1L);
    }

    @Test
    void shouldUpdateTask() {
        // Given
        Task task = new Task(1L, "Updated title", "Updated content");
        TaskDto taskDto = new TaskDto(1L, "Updated title", "Updated content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        ResponseEntity<TaskDto> response = taskController.updateTask(taskDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Updated title", response.getBody().getTitle());
        assertEquals("Updated content", response.getBody().getContent());
    }

    @Test
    void shouldCreateTask() {
        // Given
        Task task = new Task(1L, "Test title", "Test content");
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When
        ResponseEntity<Void> response = taskController.createTask(taskDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(dbService, times(1)).saveTask(task);
    }
}
