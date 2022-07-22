package mx.tc.j2se.tasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;

public class Tasks {
    /**
     * To get the tasks between two time marks, inside a task list
     * @param start the initial time of the interval
     * @param end the ending time of the interval
     * @param  tasks the iterable where the tasks must be found
     * @return a subset of tasks that are scheduled for execution
     * at least once after the "from" time, and not later than the
     * "to" time.
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        if (start==null || end==null){
            throw new IllegalArgumentException("Time marks cannot be null");
        } else if (end.isBefore(start)|| end.isEqual(start)) {
            throw new IllegalArgumentException("'To' time mark cannot be minor or equal to 'from' time mark");
        }

        Iterable<Task> myList;
        //myList=(tasks.getClass().equals(ArrayTaskListImpl.class))?new ArrayTaskListImpl():new LinkedTaskListImpl();
        myList=new LinkedList<Task>();
        Consumer<Task> taskConsumer= ((LinkedList<Task>/*AbstractTaskList*/)myList)::add;

        ((/*AbstractTaskList*/LinkedList<Task>)tasks).stream()
                .filter(t->{
                    return (t.nextTimeAfter(start).isBefore(end) && t.nextTimeAfter(start).isAfter(start));}
                ).forEach(t->{taskConsumer.accept((Task) t);});

        return myList;
    }


    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,LocalDateTime end){
        Comparator<LocalDateTime> comparator=(ldt0,ldt1)->{return ldt0.compareTo(ldt1);};
        Comparator<Task> compareTask=(t1,t2)->{return t1.getTime().compareTo(t2.getTime());};
        SortedMap<LocalDateTime, Set<Task>> map= new TreeMap<LocalDateTime, Set<Task>>(comparator);
        //LinkedTaskListImpl list=(LinkedTaskListImpl)incoming(tasks,start,end);//Has repetitive tasks
        LinkedList<Task> list=(LinkedList<Task>)incoming(tasks,start,end);
        for(Task t:list){
            if(t.isRepeated()){//if t is repetitive, then maybe it has a lot of repetitions in the same period
                //Duration dur0=Duration.between(t.getStartTime(),t.getEndTime()).dividedBy(t.getRepeatInterval());
                for(LocalDateTime i=t.nextTimeAfter(start);i.isBefore(end) || i.isEqual(end);i=i.plus(t.getRepeatInterval(), ChronoUnit.HOURS)){
                    if(map.keySet().contains(i)){

                        for (Map.Entry<LocalDateTime,Set<Task>>entry:map.entrySet()){
                            if(entry.getKey().isEqual(i)){
                                (entry).getValue().add(t);
                            }
                        }

                    }else{
                        Set<Task> set1=new TreeSet<Task>(compareTask);
                        set1.add(t);
                        map.put(i,set1);
                    }
                }
            }else{//t is non-repetitive
                if(map.keySet().contains(t.getTime())){//map does contain the task's date
                    for (Map.Entry<LocalDateTime,Set<Task>>entry:map.entrySet()){
                        if(entry.getKey().isEqual(t.getTime())){
                            (entry).getValue().add(t);
                        }
                    }
                }else{//map does not contain the task's date

                    Set<Task> set=new TreeSet<Task>(compareTask);
                    set.add(t);
                    map.put(t.getTime(),set);
                }
            }
        }

        return map;
    }

}
