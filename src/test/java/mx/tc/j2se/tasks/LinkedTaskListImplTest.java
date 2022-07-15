package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LinkedTaskListImplTest {

    @Test
    void add() {
        LinkedTaskListImpl myTaskList= new LinkedTaskListImpl();
        TaskImpl t1=new TaskImpl("Running for my life",10,100,2);
        TaskImpl t2=new TaskImpl("Working",15,50,5);
        TaskImpl t3= new TaskImpl("Eating candy",6,54,6);
        t1.setActive(true);
        t2.setActive(true);
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        System.out.println("The content of the list"+ myTaskList.size());

        /*System.out.println(myTaskList.getTask(0).getTitle());
        System.out.println(myTaskList.getTask(1).getTitle());
        System.out.println(myTaskList.getTask(2).getTitle());
        System.out.println(myTaskList.getTask(3).getTitle());*/

        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle()+" Is Active? "+myTaskList.getTask(i).isActive());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
    }

    @Test
    void remove() {
        LinkedTaskListImpl myTaskList= new LinkedTaskListImpl();
        TaskImpl t1=new TaskImpl("Running for my life",10,100,2);
        TaskImpl t2=new TaskImpl("Working",15,50,5);
        TaskImpl t3= new TaskImpl("Eating candy",6,54,6);
        TaskImpl t4=new TaskImpl("Working",15,50,5);
        TaskImpl t5=new TaskImpl("Working harder",15,50,5);
        TaskImpl t6=new TaskImpl("Working harder and better",15,50,5);
        TaskImpl t7=new TaskImpl("Singing dark ancient songs",15,50,5);
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        myTaskList.add(t6);
        System.out.println("### The content of the list");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        System.out.println("The task was removed: "+myTaskList.remove(t4));
        System.out.println("### The content of the list Now: "+myTaskList.size());
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        System.out.println("The task: "+ t5.getTitle()+" was removed: "+myTaskList.remove(t5));
        System.out.println("### The content of the list Now:"+myTaskList.size());
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }

        System.out.println("The task: 6 was removed: "+myTaskList.remove(t6));
        System.out.println("### The content of the list Now: "+myTaskList.size());
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        myTaskList.add(t7);

        System.out.println("The task 7 was added "+t7.getTitle());
        System.out.println("### The content of the list Now: ");
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        System.out.println("The task: 7 was removed: "+myTaskList.remove(t7));
        System.out.println("### The content of the list Now: "+myTaskList.size());
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        System.out.println("The task: 1 was removed: "+myTaskList.remove(t1));
        System.out.println("### The content of the list Now: "+myTaskList.size());
        for (int i=0;i< myTaskList.size();i++){
            System.out.println(myTaskList.getTask(i).getTitle());
            System.out.println(myTaskList.getTask(i).getEndTime());
        }
        myTaskList.add(t1);
        System.out.println("The task 1 was added "+t1.getTitle());
        System.out.println("### The content of the list Now: "+myTaskList.size());
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
        //this is actually a test of the TaskListFactory
        LinkedTaskListImpl myLinkedList=(LinkedTaskListImpl) TaskListFactory.createTaskList(ListTypes.types.LINKED);
        System.out.println("Class is: "+myLinkedList.getClass());
    }

    @Test
    void incoming() {
        LinkedTaskListImpl myTaskList= new LinkedTaskListImpl();
        LinkedTaskListImpl myTaskList2= new LinkedTaskListImpl();
        LinkedTaskListImpl myTaskList3= new LinkedTaskListImpl();
        LinkedTaskListImpl myTaskList4= new LinkedTaskListImpl();
        TaskImpl t1=new TaskImpl("Running for my life",60,100,2);
        TaskImpl t2=new TaskImpl("Working",45,70,5);
        TaskImpl t3= new TaskImpl("Eating candy",36,54,6);
        TaskImpl t4= new TaskImpl("Eating candy with ease",106,154,6);
        t1.setActive(true);
        t2.setActive(true);
        t4.setActive(true);
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        myTaskList.add(t4);

        myTaskList2.add(t1);
        myTaskList2.add(t2);
        myTaskList2.add(t2);
        myTaskList2.add(t3);
        myTaskList2.add(t4);

        myTaskList3.add(t1);
        myTaskList3.add(t2);
        myTaskList3.add(t2);
        myTaskList3.add(t3);
        myTaskList3.add(t4);

        myTaskList4.add(t1);
        myTaskList4.add(t3);
        myTaskList4.add(t2);
        myTaskList4.add(t3);
        myTaskList4.add(t2);



        TaskImpl t= new TaskImpl("Running in the 90's",100,150,5);
        try{
            TaskImpl tx= t.clone();
            assertTrue(tx.equals(t));

            LinkedTaskListImpl otherList=myTaskList4.clone();
            assertTrue(otherList.equals(myTaskList4));
            assertTrue(!otherList.equals(myTaskList));
            System.out.println("x)"+myTaskList4.toString()+"\n y)"+otherList.toString());
            System.out.println("It worked");
        }catch (CloneNotSupportedException e){
            System.out.println("Didn't work");
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }


        //Reflexivity
        assertTrue (myTaskList.equals(myTaskList));
        //Symmetry
        assertTrue (myTaskList.equals(myTaskList2));
        assertTrue (myTaskList2.equals(myTaskList));
        //Transitivity
        assertTrue (myTaskList.equals(myTaskList2));
        assertTrue (myTaskList2.equals(myTaskList3));
        assertTrue (myTaskList.equals(myTaskList3));

        //Consistency

        for (int j=0;j<10;j++){
            assertTrue(!myTaskList.equals(myTaskList4));
            assertTrue (myTaskList.equals(myTaskList2));
        }

        //Comparison with null
        assertTrue (!myTaskList.equals(null));
        assertTrue (!myTaskList2.equals(null));
        assertTrue (!myTaskList3.equals(null));
        assertTrue (!myTaskList4.equals(null));

        LinkedTaskListImpl subList= (LinkedTaskListImpl) myTaskList.incoming(30,75);
        System.out.println("1) The content of the sublist "+subList.size());
        for (int i=0;i< subList.size();i++){
            System.out.println(subList.getTask(i).getTitle());
            System.out.println(subList.getTask(i).getEndTime());
        }
        LinkedTaskListImpl subList3= (LinkedTaskListImpl) myTaskList.incoming(3,150);
        System.out.println("2) The content of the new sublist: "+subList3.size());
        for (int i=0;i< subList3.size();i++){
            System.out.println(subList3.getTask(i).getTitle()+" is Active? "+subList3.getTask(i).isActive());
            System.out.println(subList3.getTask(i).getEndTime());
        }
        System.out.println("#########$$$$$$$$$$$$$$$$$");
        myTaskList.getStream().forEach(x->System.out.println(x));

    }
    @Test
    void getStream(){
        LinkedTaskListImpl myList=new LinkedTaskListImpl();
        TaskImpl t1=new TaskImpl("Running for my life",60,100,2);
        TaskImpl t2=new TaskImpl("Working",45,70,5);
        TaskImpl t3= new TaskImpl("Eating candy",36,54,6);
        TaskImpl t4= new TaskImpl("Eating candy with ease",106,154,6);
        t1.setActive(true);
        t2.setActive(true);
        t4.setActive(true);
        LinkedTaskListImpl myList2=new LinkedTaskListImpl();

        myList.add(t1);
        myList.add(t2);
        myList.add(t3);
        myList.add(t4);
        myList.add(t1);
        myList.getStream().forEach(t->System.out.println(t.toString()));
        myList.getStream().forEach(t->System.out.println(t.toString()+"Hola:D"));
        System.out.println("Hi");
        try{
            myList2.getStream().forEach(System.out::println);
            myList2.getStream().forEach(t->System.out.println(t.toString()));
        }catch(RuntimeException e){
            System.err.println(e.getMessage()+"\n"+e.getStackTrace());
        }
        System.out.println("Hi");
        System.out.println("Hi");


    }

    @Test
    void testHashCode() {
        LinkedTaskListImpl myTaskList= new LinkedTaskListImpl();
        LinkedTaskListImpl myTaskList2= new LinkedTaskListImpl();
        LinkedTaskListImpl myTaskList3= new LinkedTaskListImpl();
        LinkedTaskListImpl myTaskList4= new LinkedTaskListImpl();
        TaskImpl t1=new TaskImpl("Running for my life",60,100,2);
        TaskImpl t2=new TaskImpl("Working",45,70,5);
        TaskImpl t3= new TaskImpl("Eating candy",36,54,6);
        TaskImpl t4= new TaskImpl("Eating candy with ease",106,154,6);
        t1.setActive(true);
        t2.setActive(true);
        t4.setActive(true);
        myTaskList.add(t1);
        myTaskList.add(t2);
        myTaskList.add(t2);
        myTaskList.add(t3);
        myTaskList.add(t4);

        myTaskList2.add(t1);
        myTaskList2.add(t2);
        myTaskList2.add(t2);
        myTaskList2.add(t3);
        myTaskList2.add(t4);

        myTaskList3.add(t1);
        myTaskList3.add(t2);
        myTaskList3.add(t2);
        myTaskList3.add(t3);
        myTaskList3.add(t4);

        myTaskList4.add(t1);
        myTaskList4.add(t3);
        myTaskList4.add(t2);
        myTaskList4.add(t3);
        myTaskList4.add(t2);

        TaskImpl t= new TaskImpl("Running in the 90's",100,150,5);
        try{
            TaskImpl tx= t.clone();
            assertTrue(tx.equals(t));
            assertTrue(tx.hashCode()==t.hashCode());

            LinkedTaskListImpl otherList=myTaskList4.clone();
            assertTrue(otherList.equals(myTaskList4));
            assertTrue(!otherList.equals(myTaskList));
            System.out.println("x)"+myTaskList4.toString()+"\n y)"+otherList.toString());
            System.out.println("A hash code:"+myTaskList2.hashCode());
            System.out.println("It worked");
        }catch (CloneNotSupportedException e){
            System.out.println("Didn't work");
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }


        //Reflexivity
        assertTrue (myTaskList.equals(myTaskList));
        assertTrue(myTaskList.hashCode()== myTaskList.hashCode());
        //Symmetry
        assertTrue (myTaskList.equals(myTaskList2));
        assertTrue (myTaskList2.equals(myTaskList));

        assertTrue(myTaskList.hashCode()== myTaskList2.hashCode());
        //Transitivity
        assertTrue (myTaskList.equals(myTaskList2));
        assertTrue (myTaskList2.equals(myTaskList3));
        assertTrue (myTaskList.equals(myTaskList3));

        //Consistency

        for (int j=0;j<10;j++){
            assertTrue(!myTaskList.equals(myTaskList4));
            assertTrue (myTaskList.equals(myTaskList2));
            assertTrue(myTaskList.hashCode()!=myTaskList4.hashCode());
        }



    }
}