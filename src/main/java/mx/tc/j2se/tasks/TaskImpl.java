package mx.tc.j2se.tasks;

/**
 * TaskImp1 class implements the Task Interface
 * @author Rodrigo Zavala
 */
public class TaskImpl implements Task{
    /** Task's title*/
    private String title;
    private boolean active;

    private boolean repeated;
    /** Current time*/
    private int time;
    private int interval;
    /** Time at which the task began*/
    private int start;
    /** Time at which the task will end*/
    private int end;

    /**
     * Empty constructor, it doesn't set any property
     */
    public TaskImpl(){}

    /**
     * constructor constructs an inactive task to run at a
     * specified time without repeating with a given name.
     * @param title: task's title
     * @param time: current time
     */
    public TaskImpl(String title, int time){
        if(time<0){
            throw new IllegalArgumentException("time must be positive or zero");
        }
        setTitle(title);
        setTime(time);
        setActive(false);
    }

    /**
     * constructor constructs an inactive task to run within the specified
     * time range (including the start and the end time) with the set
     * repetition interval and with a given name.
     * @param title: task's title
     * @param start: start time of the task
     * @param end: end time of the task
     * @param interval: time interval that specifies how often the task will be repeated
     */
    public TaskImpl(String title, int start, int end, int interval){
        if(start<0 || end<0){
            throw new IllegalArgumentException("Time must be positive or zero");
        } else if (start>=end) {
            throw new IllegalArgumentException("start must be less than end time-mark");

        } else if (interval<=0) {
            throw new IllegalArgumentException("interval must be positive");
        }
        setTitle(title);
        setTime(start,end,interval);
        setActive(false);
    }

    @Override
    public int nextTimeAfter(int current) {
        if (current<0){
            throw new IllegalArgumentException("time must be positive or zero");
        }
        if(isActive()==false){//Task isn't active
            return -1;
        } else if (isRepeated()==false) {//task is active and non-repetitive
            if((getEndTime()-current)<=0){//task has ended
                return -1;
            } else {//task can be completed
                return getTime();
            }
        } else{//task is active and repetitive
            if (getEndTime()-current>0){ //task can be completed
                for (int i=getStartTime();i<=getEndTime();i+=this.interval){
                    if (i-current>0){
                        return i;
                    }
                }
            }
            return -1;

        }

    }

    @Override
    public String getTitle() {
        return title;
    }


    @Override
    public void setTitle(String title) {
        this.title=title;
    }


    @Override
    public boolean isActive() {
        return active;
    }


    @Override
    public void setActive(boolean active) { this.active=active;}


    @Override
    public int getTime() {
        if (isRepeated()){
            return this.start;
        }
        else{
            return time;
        }
    }


    @Override
    public void setTime(int time) {
        if (time<0){
            throw new IllegalArgumentException("time must be positive or zero");
        }
        if(isRepeated()){
            this.repeated=false;
        }
        this.time=time;}

    @Override
    public int getStartTime() {
        if (isRepeated()==false){
            return this.time;
        }
        return this.start;
    }

    @Override
    public int getEndTime() {
        if(isRepeated()==false){
            return this.time;
        }
        return this.end;
    }

    @Override
    public int getRepeatInterval() {
        if (isRepeated()==false){
            return 0;
        }
        return this.interval;
    }

    @Override
    public void setTime(int start, int end, int interval) {
        if(start<0 || end<0){
            throw new IllegalArgumentException("Time must be positive or zero");
        } else if (start>=end) {
            throw new IllegalArgumentException("start must be minor than end time-mark");

        } else if (interval<=0) {
            throw new IllegalArgumentException("interval must be positive");
        }
        if(isRepeated()==false){
            this.repeated=true;
        }
        this.interval=interval;
        this.start=start;
        this.end=end;
    }

    @Override
    public boolean isRepeated() {
        return this.repeated;
    }
}
