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

        Consumer<TaskImpl> taskConsumer= myList::add;
        this.getStream()
                .filter(t->{
                    return (t.nextTimeAfter(from)<to && t.nextTimeAfter(from)>from);}
                ).forEach(t->{taskConsumer.accept((TaskImpl) t);});

        return myList;
    }


    /**
     *
     * @return an Iterator for linked list or arrays, it's optimized in each implementation
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
                if(hasNext()){
                    return getTask(position++);
                }
                else {
                    throw new ArrayIndexOutOfBoundsException("There isn't a new element in this list");
                }
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


    /**
     * Compare this list with other that is passed as an argument
     * @param otherObject is the object to be compared
     * @return true if the passed object is equals to this one,
     * false otherwise
     */
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

    /**
     * A code to identify this list
     * @return An int related to the qualities of this list
     */
    @Override
    public int hashCode() {


        int fTasks=12;
        if(this.size()>=1){
            int myLimit=(this.size()>=2)?this.size()-1:this.size();
            fTasks= (int)this.getStream().
                    limit((long)myLimit).
                    map(t->t.getTitle().length())
                    .reduce(0,(Integer a,Integer b)->a*2+b-50);
        }

        int sum=(int)this.getStream().
                map(t->(t.hashCode())).
                reduce(0,Integer::sum);

        int res=(int)this.getStream().
                filter(t->t.getStartTime()%2==0).
                map(t->t.getStartTime()).
                reduce(0,(a,b)->{return (a*5)+b/2;});
        return (int)(-fTasks+(2*sum-res)^2+this.size()+(int)(this.getStream().filter(t->t.isActive()).count())*this.size());
    }


    /**
     *
     * @return A string with significant information about this list
     */
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

    /**
     *
     * @return a copy of the object being cloned
     * @throws CloneNotSupportedException
     */
    public AbstractTaskList clone() throws CloneNotSupportedException {
        try{
            return (AbstractTaskList) super.clone();
        }catch (CloneNotSupportedException e){
            System.err.println(e.getMessage()+"\n"+e.getStackTrace());
            return null;
        }
    }

    /**
     *
     * @return a stream with the elements of the list, if a list doesn't have any
     * task, then it throws a RuntimeException
     */
    public Stream<Task> getStream(){

        if(this.size()==0){
            throw new RuntimeException("There are not elements in this list");
        }

        Spliterator<Task> taskSpliterator= Spliterators.
                spliteratorUnknownSize(this.iterator(),0);
        return StreamSupport.stream(taskSpliterator,false);

    }

}
