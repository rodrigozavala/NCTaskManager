package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListImplTest {

    @Test
    void incoming() {
        ArrayTaskListImpl myTaskList= new ArrayTaskListImpl();
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
        TaskImpl t1=new TaskImpl("Running for my life",s1,e1,20/*int1*/);
        TaskImpl t2=new TaskImpl("Working",s2,e2,20/*int2*/);
        TaskImpl t3= new TaskImpl("Eating candy",s3,e3,20/*int3*/);
        TaskImpl t4= new TaskImpl("Eating candy with ease",s4,e4,30/*int4*/);
        t1.setActive(true);
        t2.setActive(true);
        t4.setActive(true);
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        myTaskList.add(t4);

        ArrayTaskListImpl subList= (ArrayTaskListImpl) Tasks.incoming(myTaskList,e2,e1); /*myTaskList.incoming(30,75)*/;

        System.out.println("1) The content of the sublist: "+subList.size());
        for (int i=0;i< subList.size();i++){
            System.out.println(subList.getTask(i).toString());
            //System.out.println(subList.getTask(i).getEndTime());
        }
        ArrayTaskListImpl subList3= (ArrayTaskListImpl)Tasks.incoming(myTaskList,e2,e4); //myTaskList.incoming(3,150);
        System.out.println("2) The content of the new sublist: "+subList3.size());
        for (int i=0;i< subList3.size();i++){
            System.out.println(subList3.getTask(i).toString());
            //System.out.println(subList3.getTask(i).getTitle()+" is Active? "+subList3.getTask(i).isActive());
            //System.out.println(subList3.getTask(i).getEndTime());
        }



    }

    @Test
    void add() {
        ArrayTaskListImpl myTaskList= new ArrayTaskListImpl();
        LocalDateTime s1=LocalDateTime.of(2019, Month.MARCH, 28, 14, 33);
        LocalDateTime e1=LocalDateTime.of(2019, Month.SEPTEMBER, 28, 14, 33);
        LocalDateTime s2=LocalDateTime.of(2019, Month.MARCH, 28, 14, 33);
        LocalDateTime e2=LocalDateTime.of(2019, Month.JULY, 1, 14, 33);
        LocalDateTime s3=LocalDateTime.of(2019, Month.AUGUST, 28, 14, 33);
        LocalDateTime e3=LocalDateTime.of(2019, Month.SEPTEMBER, 8, 14, 33);

        Duration int1= Duration.of(10, ChronoUnit.HOURS);
        Duration int2= Duration.of(12, ChronoUnit.HOURS);
        Duration int3= Duration.of(8, ChronoUnit.HOURS);

        TaskImpl t1=new TaskImpl("Running for my life",s1,e1,20/*int1*/);
        TaskImpl t2=new TaskImpl("Working",s2,e2,20/*int2*/);
        TaskImpl t3= new TaskImpl("Eating candy",s3,e3,20/*int3*/);

        t1.setActive(true);
        t2.setActive(true);
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        System.out.println("The content of the list");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle()+" Is Active? "+myTaskList.getTask(i).isActive());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
    }

    @Test
    void remove() {
        ArrayTaskListImpl myTaskList= new ArrayTaskListImpl();
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
        TaskImpl t1=new TaskImpl("Running for my life",s1,e1,20/*int1*/);
        TaskImpl t2=new TaskImpl("Working",s2,e2,20/*int2*/);
        TaskImpl t3= new TaskImpl("Eating candy",s3,e3,20/*int3*/);
        TaskImpl t4= new TaskImpl("Eating candy with ease",s4,e4,30/*int4*/);
        TaskImpl t5=new TaskImpl("Working harder",s4,e4,40/*int4*/);
        TaskImpl t6=null;
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        System.out.println("### The content of the list");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        System.out.println("The task was removed: "+myTaskList.remove(t4));
        System.out.println("### The content of the list Now: ");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        System.out.println("The task: "+ t5.getTitle()+" was removed: "+myTaskList.remove(t5));
        System.out.println("### The content of the list Now: ");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }

        System.out.println("The task: null was removed: "+myTaskList.remove(t6));
        System.out.println("### The content of the list Now: ");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }

    }

    @Test
    void size() {
    }

    @Test
    void getTask() {

    }
}