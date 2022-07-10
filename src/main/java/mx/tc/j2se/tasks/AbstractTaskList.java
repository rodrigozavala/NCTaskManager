package mx.tc.j2se.tasks;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractTaskList implements Iterable<Task>,Cloneable{

    //protected int position=0;

    /**
     * Adds the specified task to the list
     * @param task: the task to be added
     */
    public abstract void add(Task task);

    /**
     * Removes a task from the list and returns true, if such a
     * task was in the list. If the list contains the same task several
     * times, any of them should be removed.
     * @param task the task to remove
     * @return True if the task was in the list, else return False
     */
    public abstract boolean remove (Task task);

    /**
     * Returns the number of tasks in the list.
     * @return the number of task in the list
     */
    public abstract int size();

    /**
     * To get a task in the list
     * @param index the place where the task is holded
     * @return a task which takes the specified place in the list
     */
    public abstract Task getTask(int index);

    /**
     * To get the tasks between two time marks, since it's final it cannot be
     * overridden in child classes
     * @param from the initial time of the interval
     * @param to the ending time of the interval
     * @return a subset of tasks that are scheduled for execution
     * at least once after the "from" time, and not later than the
     * "to" time.
     */
    public final AbstractTaskList incoming(int from, int to){
        if (from<0 || to<0){
            throw new IllegalArgumentException("Time marks cannot be negative");
        } else if (to<=from) {
            throw new IllegalArgumentException("'To' time mark cannot be minor or equal to 'from' time mark");
        }

        AbstractTaskList myList;
        myList=(this.getClass().equals(ArrayTaskListImpl.class))?new ArrayTaskListImpl():new LinkedTaskListImpl();
        /*
        if(this.getClass().equals(ArrayTaskListImpl.class)){
            myList=new ArrayTaskListImpl();
        }else if(this.getClass().equals(LinkedTaskListImpl.class)){
            myList=new LinkedTaskListImpl();
        }*/

        /*
        for(int i=0;i<this.size();i++){
            if(this.getTask(i).nextTimeAfter(from)!=-1){
                if (this.getTask(i).nextTimeAfter(from)<to && this.getTask(i).nextTimeAfter(from)>from){
                    myList.add(this.getTask(i));
                }
            }
        }*/
/*
        for(Task t : this){
            if(t.nextTimeAfter(from)!=-1){
                if (t.nextTimeAfter(from)<to && t.nextTimeAfter(from)>from){
                    myList.add(t);
                }
            }
        }

 */

        Consumer<TaskImpl> taskConsumer= myList::add;
        this.getStream()
                .filter(t->{
                    return (t.nextTimeAfter(from)<to && t.nextTimeAfter(from)>from);}
                ).forEach(t->{taskConsumer.accept((TaskImpl) t);});

        return myList;
    }


    /**
     *
     * @return An Iterator for arrays
     */
    @Override
    public Iterator<Task> iterator() {

        Iterator <Task> arrayTask =new Iterator<Task>() {
            int position=0;
            @Override
            public boolean hasNext() {
                if(position<size()){
                    return true;
                }
                //position=0;
                return false;

            }

            @Override
            public Task next() {
                return getTask(position++);
            }
        };

        return arrayTask;
    }


    @Override
    public void forEach(Consumer<? super Task> action) {
        Iterable.super.forEach(action);

    }

    @Override
    public Spliterator<Task> spliterator() {

        return Iterable.super.spliterator();
    }


    public boolean equals(Object otherObject) {
        if(this == otherObject){
            return true;
        }
        if(otherObject==null || getClass()!=otherObject.getClass()){
            return false;
        }

        AbstractTaskList A=(AbstractTaskList) otherObject;

        if(A.size()!=this.size()){
            return false;
        }

        Iterator<Task> iter=A.iterator();
        for(Task t:this){
            Task t2=iter.next();
            if(!t.equals(t2)){
                return false;
            }
        }
        return true;

    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        int sum=(int)this.getStream().
                map(t->(t.hashCode())).
                reduce(0,Integer::sum);

        int res=(int)this.getStream().
                filter(t->t.getStartTime()%2==0).
                map(t->t.getStartTime()).
                reduce(0,(a,b)->{return (a*5)+b/2;});
        return (int)((sum-res)^2+this.size()+(int)(this.getStream().filter(t->t.isActive()).count())*this.size());
    }


    @Override
    public String toString() {
        String cad="{";
        int counter=1;
        for (Task t: this){
            cad+=counter+")"+t.toString()+"\n";
            counter++;
        }
        //return super.toString();

        return cad+"}";
    }

    protected AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();

    }


    public Stream<Task> getStream(){
        /*
        Stream.Builder<Task> myStream=Stream.builder();
        this.forEach((Task t)-> myStream.add(t));
        return myStream.build();
        */

        Spliterator<Task> taskSpliterator= Spliterators.
                spliteratorUnknownSize(this.iterator(),0);
        return StreamSupport.stream(taskSpliterator,false);

    }

}
