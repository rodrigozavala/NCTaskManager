package mx.tc.j2se.tasks;

//import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class TasksTest {


    @Test
    void calendar() {
        LinkedTaskListImpl myList=new LinkedTaskListImpl();
        LocalDateTime d1=LocalDateTime.of(2022, Month.AUGUST,24,16,00);

        LocalDateTime s1=LocalDateTime.of(2022,Month.MARCH,1,8,15);
        LocalDateTime e1=LocalDateTime.of(2022,Month.SEPTEMBER,1,8,15);
        Duration dur1=Duration.of(1, ChronoUnit.DAYS);

        LocalDateTime s2=LocalDateTime.of(2022,Month.AUGUST,20,8,15);
        LocalDateTime e2=LocalDateTime.of(2022,Month.AUGUST,28,8,15);
        Duration dur2=Duration.of(12, ChronoUnit.HOURS);

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

        LocalDateTime p1=LocalDateTime.of(2022,Month.AUGUST,25,8,0);
        LocalDateTime p2=LocalDateTime.of(2022,Month.AUGUST,26,8,0);
        TreeMap<LocalDateTime, Set<Task>> cal= (TreeMap<LocalDateTime, Set<Task>>) Tasks.calendar(myList.iterator(),p1,p2);
        System.out.println("Hola");
        for(Map.Entry<LocalDateTime,Set<Task>>entry: cal.entrySet()){
            System.out.println(entry.toString());
        }
        //System.out.println(cal.keySet());
    }
}