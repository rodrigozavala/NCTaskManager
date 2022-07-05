package mx.tc.j2se.tasks;

import org.junit.jupiter.api.Test;

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
        //this is actually a test of the TaskListFactory
        LinkedTaskListImpl myLinkedList=(LinkedTaskListImpl) TaskListFactory.createTaskList(ListTypes.types.LINKED);
        System.out.println("Class is: "+myLinkedList.getClass());
    }

    @Test
    void incoming() {
        LinkedTaskListImpl myTaskList= new LinkedTaskListImpl();
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
        LinkedTaskListImpl subList= (LinkedTaskListImpl) myTaskList.incoming(30,75);
        System.out.println("The content of the sublist");
        for (int i=0;i< subList.size();i++){
            System.out.println(subList.getTask(i).getTitle());
            System.out.println(subList.getTask(i).getEndTime());
        }
        LinkedTaskListImpl subList3= (LinkedTaskListImpl) myTaskList.incoming(3,150);
        System.out.println("The content of the new sublist: "+subList3.size());
        for (int i=0;i< subList3.size();i++){
            System.out.println(subList3.getTask(i).getTitle()+" is Active? "+subList3.getTask(i).isActive());
            System.out.println(subList3.getTask(i).getEndTime());
        }

    }
}