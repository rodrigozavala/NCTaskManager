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

    @Test
    void testEquals() {
    }

    @Test
    void testToString() {
    }

    @Test
    void testClone() {
    }

    @Test
    void testHashCode() {
        TaskImpl t1=new TaskImpl("Running for my life",60,100,2);
        TaskImpl t2=new TaskImpl("Working",45,70,5);
        TaskImpl t3= new TaskImpl("Eating candy",36,54,6);
        TaskImpl t4= new TaskImpl("Eating candy with ease",106,154,6);
        TaskImpl t= new TaskImpl("Running in the 90's",100,150,5);
        TaskImpl ty= new TaskImpl("Running in the 90's",100,150,5);
        try{
            TaskImpl tx= t.clone();
            int myhash=tx.hashCode();
            for(int i=0;i<100;i++){
                assertTrue(myhash==tx.hashCode());
            }


            assertTrue(tx.equals(t));
            assertTrue(tx.hashCode()==t.hashCode());

            assertTrue(tx.equals(ty));
            assertTrue(tx.hashCode()==ty.hashCode());


            assertTrue(!t2.equals(t3));
            assertTrue(t2.hashCode()!=t3.hashCode());
            System.out.println("It worked");
        }catch (CloneNotSupportedException e){
            System.out.println("Didn't work");
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }
    }
}