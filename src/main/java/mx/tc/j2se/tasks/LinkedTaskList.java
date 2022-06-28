package mx.tc.j2se.tasks;

public interface LinkedTaskList {
    /**
     * Adds the specified task to the list
     * @param task: the task to be added
     */
    public void add (Task task);

    /**
     * Removes a task from the list and returns true, if such a
     * task was in the list. If the list contains the same task several
     * times, any of them should be removed.
     * @param task the task to remove
     * @return True if the task was in the list, else return False
     */
    public boolean remove (Task task);

    /**
     * Returns the number of tasks in the list.
     * @return the number of task in the list
     */
    public int size();

    /**
     * To get a task in the list
     * @param index the place where the task is holded
     * @return a task which takes the specified place in the list
     */
    public Task getTask(int index);

    /**
     * To get the tasks between two time marks
     * @param from the initial time of the interval
     * @param to the ending time of the interval
     * @return a subset of tasks that are scheduled for execution
     * at least once after the "from" time, and not later than the
     * "to" time.
     */
    public LinkedTaskList incoming(int from, int to);
}
