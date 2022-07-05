package mx.tc.j2se.tasks;

public abstract class AbstractTaskList {
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
     * To get the tasks between two time marks
     * @param from the initial time of the interval
     * @param to the ending time of the interval
     * @return a subset of tasks that are scheduled for execution
     * at least once after the "from" time, and not later than the
     * "to" time.
     */
    public AbstractTaskList incoming(int from, int to){
        if (from<0 || to<0){
            throw new IllegalArgumentException("Time marks cannot be negative");
        } else if (to<=from) {
            throw new IllegalArgumentException("'To' time mark cannot be minor or equal to 'from' time mark");
        }

        AbstractTaskList myList=null;
        if(this.getClass().equals(ArrayTaskListImpl.class)){
            myList=new ArrayTaskListImpl();
        }else if(this.getClass().equals(LinkedTaskListImpl.class)){
            myList=new LinkedTaskListImpl();
        }

        for(int i=0;i<this.size();i++){
            if(this.getTask(i).nextTimeAfter(from)!=-1){
                if (this.getTask(i).nextTimeAfter(from)<to && this.getTask(i).nextTimeAfter(from)>from){
                    myList.add(this.getTask(i));
                }
            }
        }
        return myList;
    }
}
