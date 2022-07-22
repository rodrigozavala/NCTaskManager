package mx.tc.j2se.tasks;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.time.LocalDateTime;
/**
 * TaskImp1 class implements the Task Interface
 * @author Rodrigo Zavala
 */
public class TaskImpl implements Task,Cloneable{
    /** Task's title*/
    private String title;
    private boolean active;

    private boolean repeated;
    /** Current time*/
    private LocalDateTime time;
    private long interval;
    /** Time at which the task began*/
    private LocalDateTime start;
    /** Time at which the task will end*/
    private LocalDateTime end;

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
    public TaskImpl(String title, LocalDateTime time){
        /*if(time<0){
            throw new IllegalArgumentException("time must be positive or zero");
        }

         */
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
    public TaskImpl(String title, LocalDateTime start, LocalDateTime end, long interval){
        if(start==null || end==null/*start<0 || end<0*/){
            throw new IllegalArgumentException("Time must be non-null");
        } else if (start.isAfter(end)|| start.isEqual(end)) {
            throw new IllegalArgumentException("start must be before end time-mark");

        } else if (interval<=0) {
            throw new IllegalArgumentException("interval must be positive");
        }

        setTitle(title);
        setTime(start,end,interval);
        setActive(false);
    }

    @Override
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (/*current<0*/current==null){
            throw new IllegalArgumentException("time must be non-null");

        }
        if (current.isBefore(LocalDateTime.MIN)){
            throw new IllegalArgumentException("time mustn't be before"+LocalDateTime.MIN);
        }
        if (current.isAfter(LocalDateTime.MAX)){
            throw new IllegalArgumentException("time must be before"+LocalDateTime.MAX);
        }

        if(isActive()==false){//Task isn't active
            LocalDateTime ldt=LocalDateTime.MIN;
            return ldt;
        } else if (isRepeated()==false) {//task is active and non-repetitive
            if(/*(getEndTime()-current)<=0*/ getEndTime().isBefore(current) || getEndTime().isEqual(current)){//task has ended
                LocalDateTime ldt=LocalDateTime.MIN;
                return ldt;
            } else {//task can be completed
                return getTime();
            }
        } else{//task is active and repetitive
            if (/*getEndTime()-current>0*/ getEndTime().isAfter(current)){ //task can be completed
                for(LocalDateTime i=getStartTime();i.isBefore(getEndTime())|| i.isEqual(getEndTime());i=i.plus(this.interval, ChronoUnit.HOURS)){
                    if(i.isAfter(current)){
                        return i;
                    }

                }
            }

            return LocalDateTime.MIN;

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
    public LocalDateTime getTime() {
        if (isRepeated()){
            return this.start;
        }
        else{
            return time;
        }
    }


    @Override
    public void setTime(LocalDateTime time) {
        if (time==null){
            throw new IllegalArgumentException("time must be non-null");
        }
        if(isRepeated()){
            this.repeated=false;
        }
        this.time=time.plusHours(0);
    }

    @Override
    public LocalDateTime getStartTime() {
        if (isRepeated()==false){
            return this.time;
        }
        return this.start;
    }

    @Override
    public LocalDateTime getEndTime() {
        if(isRepeated()==false){
            return this.time;
        }
        return this.end;
    }

    @Override
    public long getRepeatInterval() {
        if (isRepeated()==false){
            return 0;
        }
        return this.interval;
    }

    @Override
    public void setTime(LocalDateTime start, LocalDateTime end, long interval) {
        if(start==null || end ==null/*start<0 || end<0*/){
            throw new IllegalArgumentException("Time must be positive or zero");
        } else if (start.isAfter(end)|| start.isEqual(end)) {
            throw new IllegalArgumentException("start must be before end time-mark");

        } else if (interval<=0) {
            throw new IllegalArgumentException("interval must be positive");
        }
        if(isRepeated()==false){
            this.repeated=true;
        }
        this.interval=interval;//.plusHours(0);
        this.start=start.plusHours(0);
        this.end=end.plusHours(0);
    }

    @Override
    public boolean isRepeated() {
        return this.repeated;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o==null || getClass()!=o.getClass()){
            return false;
        }

        TaskImpl t= (TaskImpl)o;
        boolean var1=this.getTitle().equals(t.getTitle())&& this.isActive()==t.isActive();
        boolean var2= this.isRepeated()==t.isRepeated() && this.getEndTime().isEqual(t.getEndTime());
        boolean var3= this.getTime().isEqual(t.getTime()) && this.getStartTime().isEqual(t.getStartTime());
        //Check this instruction of interval:
        boolean var4= this.getRepeatInterval()==t.getRepeatInterval();
        return  var1 && var2 && var3 && var4;
    }

    @Override
    public String toString() {
        return "TaskImpl{" +
                "title='" + title + '\'' +
                ", active=" + active +
                ", repeated=" + repeated +
                ", time=" + time +
                ", interval=" + interval +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    //@Override
    protected TaskImpl clone() throws CloneNotSupportedException {

        TaskImpl t= (TaskImpl) super.clone();
        return t;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, active, repeated, time, interval, start, end)+title.length()^2;
    }
}
