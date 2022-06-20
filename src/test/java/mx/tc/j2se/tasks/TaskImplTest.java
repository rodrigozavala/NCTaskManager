package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskImplTest {


    @Test
    void nextTimeAfter() {
        TaskImpl mytask=new TaskImpl();
    }


    @Test
    void getTime() {

        TaskImpl myTask=new TaskImpl("Running",10,100,5);
        TaskImpl myTask2=new TaskImpl("Working",20);
        assertTrue (false==myTask.isActive());
        assertTrue (false==myTask2.isActive());
        assertTrue (true==myTask.isRepeated());
        assertTrue (false==myTask2.isRepeated());
        assertEquals (10,myTask.getTime());
        assertEquals(20,myTask2.getTime());

    }

    @Test
    void getStartTime() {
    }

    @Test
    void getEndTime() {
    }

    @Test
    void getRepeatInterval() {
    }
}