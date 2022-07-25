package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class TaskIOTest {

    @Test
    void writeBinary() {
    }

    @Test
    void readBinary() {
    }

    @Test
    void write() {


        LinkedTaskListImpl myList=new LinkedTaskListImpl();
        LocalDateTime d1=LocalDateTime.of(2022, Month.AUGUST,24,16,00);
        LocalDateTime s1=LocalDateTime.of(2022,Month.MARCH,1,8,15);
        LocalDateTime e1=LocalDateTime.of(2022,Month.SEPTEMBER,1,8,15);
        LocalDateTime s2=LocalDateTime.of(2022,Month.AUGUST,20,8,15);
        LocalDateTime e2=LocalDateTime.of(2022,Month.AUGUST,28,8,15);
        LocalDateTime d2=LocalDateTime.of(2022, Month.SEPTEMBER,1,18,00);
        Task t1=new TaskImpl("Lunch with a beautiful girl",d1);
        Task t2=new TaskImpl("Morning run",s1,e1,300/*dur1*/);
        Task t3=new TaskImpl("Taking medication",s2,e2,200/*dur2*/);
        Task t4=new TaskImpl("Meeting with friends",d2);
        t1.setActive(true);
        t2.setActive(true);
        t3.setActive(true);
        t4.setActive(true);
        myList.add(t1);
        myList.add(t2);
        myList.add(t3);
        myList.add(t4);
        File myFile=new File("myFile.txt");
        TaskIO.write(myList,myFile);
        LinkedTaskListImpl myList2=new LinkedTaskListImpl();
        TaskIO.read(myList2,myFile);
        System.out.println(myList2);
        System.out.println(myList);
        assertEquals(myList,myList2);
    }

    @Test
    void read() {
    }

    @Test
    void testWrite() {
    }

    @Test
    void testRead() {

    }

    @Test
    void writeText() {
        LinkedTaskListImpl myList=new LinkedTaskListImpl();
        LocalDateTime d1=LocalDateTime.of(2022, Month.AUGUST,24,16,00);
        LocalDateTime s1=LocalDateTime.of(2022,Month.MARCH,1,8,15);
        LocalDateTime e1=LocalDateTime.of(2022,Month.SEPTEMBER,1,8,15);
        LocalDateTime s2=LocalDateTime.of(2022,Month.AUGUST,20,8,15);
        LocalDateTime e2=LocalDateTime.of(2022,Month.AUGUST,28,8,15);
        LocalDateTime d2=LocalDateTime.of(2022, Month.SEPTEMBER,1,18,00);
        Task t1=new TaskImpl("Lunch with a beautiful girl",d1);
        Task t2=new TaskImpl("Morning run",s1,e1,300/*dur1*/);
        Task t3=new TaskImpl("Taking medication",s2,e2,200/*dur2*/);
        Task t4=new TaskImpl("Meeting with friends",d2);
        t1.setActive(true);
        t2.setActive(true);
        t3.setActive(true);
        t4.setActive(true);
        myList.add(t1);
        myList.add(t2);
        myList.add(t3);
        myList.add(t4);
        File myFile=new File("myFile.json");
        TaskIO.writeText(myList,myFile);
        LinkedTaskListImpl myList2=new LinkedTaskListImpl();
        TaskIO.readText(myList2,myFile);
        System.out.println(myList2);
        System.out.println(myList);
        assertEquals(myList,myList2);
    }

    @Test
    void readText() {
    }
}