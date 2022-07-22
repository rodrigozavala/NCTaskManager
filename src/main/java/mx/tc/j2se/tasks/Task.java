package mx.tc.j2se.tasks;


import java.time.LocalDateTime;


public interface Task {

    LocalDateTime nextTimeAfter (LocalDateTime current);

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
    LocalDateTime getTime();
    /**
     * Setter method to get time attribute
     * @param time: mustn't be a negative integer
     */
    void setTime(LocalDateTime time);

    /**
     * Getter method to get start time
     * @return start if the task is repeated otherwise it returns
     * the time of the execution
     */
    LocalDateTime getStartTime();

    /**
     * Getter method to get end time
     * @return end if the task is repeated, otherwise it returns
     * the time of the execution
     */
    LocalDateTime getEndTime();

    /**
     * Getter method to get interval
     * @return interval if the task is a repeated one,
     * otherwise it returns 0
     */
    long getRepeatInterval();

    /**
     * Setter method to set time, start and end
     * @param start: the time at which the task began
     * @param end: the time mark at which the task will end
     * @param interval: how often the task will be repeated in time
     */
    void setTime(LocalDateTime start, LocalDateTime end, long interval);

    /**
     * Getter method to get repeated, to know if the task is a repetitive one
     * @return repeated
     */
    boolean isRepeated();
}