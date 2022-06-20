package mx.tc.j2se.tasks;
public interface Task {

    int nextTimeAfter (int current);

    /**
     * Getter method to tittle attribute
     * @return title: the task's title
     */
    String getTitle();
    /**
     * Setter method to set tittle attribute
     * @param title: the task's title
     */
    void setTitle(String title);
    /**
     * Getter method to get active attribute
     * @return active: true if the task is active, false otherwise
     */
    boolean isActive();
    /**
     * Setter method to set active attribute
     * @param active: true if the task must be active, false otherwise
     */
    void setActive(boolean active);
    /**
     * Getter method to get time attribute
     * @return time if the task is not a repetitive one,
     * otherwise it returns start
     */
    int getTime();
    /**
     * Setter method to get time attribute
     * @param time: mustn't be a negative integer
     */
    void setTime(int time);

    /**
     * Getter method to get start time
     * @return start if the task is repeated otherwise it returns
     * the time of the execution
     */
    int getStartTime();

    /**
     * Getter method to get end time
     * @return end if the task is repeated, otherwise it returns
     * the time of the execution
     */
    int getEndTime();

    /**
     * Getter method to get interval
     * @return interval if the task is a repeated one,
     * otherwise it returns 0
     */
    int getRepeatInterval();

    /**
     * Setter method to set time, start and end
     * @param start: the time at which the task began
     * @param end: the time mark at which the task will end
     * @param interval: how often the task will be repeated in time
     */
    void setTime(int start, int end, int interval);

    /**
     * Getter method to get repeated, to know if the task is a repetitive one
     * @return repeated
     */
    boolean isRepeated();
}