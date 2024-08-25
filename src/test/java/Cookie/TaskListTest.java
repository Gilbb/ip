package Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TaskListTest {
    private TaskList taskList;
    private Task todoTask;
    private Task eventTask;
    private Task deadlineTask;

    @BeforeEach
    void setUp() {
        // Create sample tasks for testing
        todoTask = new ToDo("Finish homework");
        eventTask = new Event("Team meeting", "2024-08-25", "2024-08-26");
        deadlineTask = new Deadline("Submit report", "2024-09-01");

        // Initialize TaskList with these tasks
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todoTask);
        tasks.add(eventTask);
        tasks.add(deadlineTask);
        taskList = new TaskList(tasks);
    }

    @Test
    void testConstructor() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList taskList = new TaskList(tasks);
        assertNotNull(taskList.getTaskArrayList());
        assertTrue(taskList.getTaskArrayList().isEmpty());
    }

    @Test
    void testAddTask() {
        Task newTask = new ToDo("New task");
        taskList.addTask(newTask);
        assertEquals(4, taskList.getSize());
        assertEquals(newTask, taskList.getTask(4));
    }

    @Test
    void testDeleteTask() {
        taskList.delete(1);
        assertEquals(2, taskList.getSize());
        assertEquals(eventTask, taskList.getTask(1));
    }

    @Test
    void testPrintTasks() {
        String expectedOutput = "1: [T][] Finish homework\n" +
                "2: [E][] Team meeting (from: Aug 25 2024 to: Aug 26 2024 )\n" +
                "3: [D][] Submit report (by: Sept 1 2024)\n";
        assertEquals(expectedOutput, taskList.printTasks());
    }

    @Test
    void testGetSize() {
        assertEquals(3, taskList.getSize());
    }

    @Test
    void testGetTask() {
        assertEquals(todoTask, taskList.getTask(1));
        assertEquals(eventTask, taskList.getTask(2));
        assertEquals(deadlineTask, taskList.getTask(3));
    }

}
