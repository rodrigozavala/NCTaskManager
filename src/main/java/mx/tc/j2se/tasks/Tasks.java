package mx.tc.j2se.tasks;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    public static Iterator<Task> incoming(Iterator<Task> tasks, LocalDateTime start, LocalDateTime end){

        if (start==null || end==null){
            throw new IllegalArgumentException("Time marks cannot be null");
        } else if (end.isBefore(start)|| end.isEqual(start)) {
            throw new IllegalArgumentException("'To' time mark cannot be minor or equal to 'from' time mark");
        }

        LinkedList<Task> myList=new LinkedList<Task>();
        while (tasks.hasNext()){
            Task t=tasks.next();
            if(t.nextTimeAfter(start).isBefore(end) && t.nextTimeAfter(start).isAfter(start)){
                myList.add(t);
            }
        }

        return myList.iterator();
    }


    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterator<Task> tasks, LocalDateTime start,LocalDateTime end){
        Comparator<LocalDateTime> comparator=(ldt0,ldt1)->{return ldt0.compareTo(ldt1);};
        Comparator<Task> compareTask=(t1,t2)->{return t1.getTime().compareTo(t2.getTime());};
        SortedMap<LocalDateTime, Set<Task>> map= new TreeMap<LocalDateTime, Set<Task>>(comparator);
        //LinkedTaskListImpl list=(LinkedTaskListImpl)incoming(tasks,start,end);//Has repetitive tasks
        Iterator<Task> list=incoming(tasks,start,end);

        while(list.hasNext())
        /*for(Task t:(Iterable <Task>)list)*/{
            Task t=list.next();
            if(t.isRepeated()){//if t is repetitive, then maybe it has a lot of repetitions in the same period
                //Duration dur0=Duration.between(t.getStartTime(),t.getEndTime()).dividedBy(t.getRepeatInterval());
                for(LocalDateTime i=t.nextTimeAfter(start);i.isBefore(end) || i.isEqual(end);i=i.plus(t.getRepeatInterval(), ChronoUnit.HOURS)){
                    if(map.keySet().contains(i)){
                        /*
                        for (Map.Entry<LocalDateTime,Set<Task>>entry:map.entrySet()){
                            if(entry.getKey().isEqual(i)){
                                (entry).getValue().add(t);
                            }
                        }*/
                        map.get(i).add(t);

                    }else{
                        Set<Task> set1=new TreeSet<Task>(compareTask);
                        set1.add(t);
                        map.put(i,set1);
                    }
                }
            }else{//t is non-repetitive
                if(map.keySet().contains(t.getTime())){//map does contain the task's date
                    /*
                    for (Map.Entry<LocalDateTime,Set<Task>>entry:map.entrySet()){
                        if(entry.getKey().isEqual(t.getTime())){
                            (entry).getValue().add(t);
                        }
                    }*/
                    map.get(t.getTime()).add(t);
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
