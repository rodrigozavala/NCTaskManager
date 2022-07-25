package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class TaskImplTest {


    @Test
    void nextTimeAfter() {
        TaskImpl mytask=new TaskImpl();
    }


    @Test
    void getTime() {
        LocalDateTime s1=LocalDateTime.of(2020,Month.JULY,15,19,0);
        LocalDateTime e1=LocalDateTime.of(2020,Month.DECEMBER,12,8,30);
        LocalDateTime time1=LocalDateTime.of(2020,Month.SEPTEMBER,12,11,40);
        Duration dur=Duration.of(20,ChronoUnit.HOURS);
        TaskImpl myTask=new TaskImpl("Running",s1,e1,20);
        TaskImpl myTask2=new TaskImpl("Working",time1);
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
        LocalDateTime s1=LocalDateTime.of(2019, Month.MARCH, 28, 14, 33);
        LocalDateTime e1=LocalDateTime.of(2019, Month.SEPTEMBER, 28, 14, 33);
        LocalDateTime s2=LocalDateTime.of(2019, Month.MARCH, 28, 14, 33);
        LocalDateTime e2=LocalDateTime.of(2019, Month.JULY, 1, 14, 33);
        LocalDateTime s3=LocalDateTime.of(2019, Month.AUGUST, 28, 14, 33);
        LocalDateTime e3=LocalDateTime.of(2019, Month.SEPTEMBER, 8, 14, 33);
        LocalDateTime s4=LocalDateTime.of(2020, Month.JANUARY, 28, 14, 33);
        LocalDateTime e4=LocalDateTime.of(2020, Month.MARCH, 28, 14, 33);
        Duration int1= Duration.of(10, ChronoUnit.HOURS);
        Duration int2= Duration.of(12, ChronoUnit.HOURS);
        Duration int3= Duration.of(8, ChronoUnit.HOURS);
        Duration int4= Duration.of(5, ChronoUnit.HOURS);
        TaskImpl t1=new TaskImpl("Running for my life",s1,e1,10);
        TaskImpl t2=new TaskImpl("Working",s2,e2,12);
        TaskImpl t3= new TaskImpl("Eating candy",s3,e3,8);
        TaskImpl t4= new TaskImpl("Eating candy with ease",s4,e4,5);

        TaskImpl t= new TaskImpl("Running in the 90's",e3,e1,12);
        TaskImpl ty= new TaskImpl("Running in the 90's",e3,e1,12);
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