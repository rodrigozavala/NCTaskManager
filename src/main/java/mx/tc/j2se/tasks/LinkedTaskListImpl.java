package mx.tc.j2se.tasks;

public class LinkedTaskListImpl implements LinkedTaskList {


    private link head;


    private boolean compareTasks(Task t1,Task t2){
        if (t1==null || t2==null){
            return false;
        }
        boolean var1=t1.getTitle().equals(t2.getTitle())&& t1.isActive()==t2.isActive();
        boolean var2= t1.isRepeated()==t2.isRepeated() && t1.getEndTime()==t2.getEndTime();
        boolean var3= t1.getTime()==t2.getTime() && t1.getStartTime()==t2.getStartTime();
        boolean var4= t1.getRepeatInterval()==t2.getRepeatInterval();
        return  var1 && var2 && var3 && var4;
    }


    public LinkedTaskListImpl(){
    }

    public LinkedTaskListImpl(Task initialTask){

        head=null;
        this.add(initialTask);
    }


    @Override
    public void add(Task task) {
        if (this.head!=null){//if head already exist
            link tail=this.head;
            while (tail.isTail()==false){//repeats until the real tail is found
                tail=tail.next;
            }
            tail.next=new link(task,tail,null);
            tail.next.setIndex(tail.getIndex()+1);
        }else{//head doesn't exist
            if(task==null){
                throw new NullPointerException("task cannot be null");
            }
            head=new link(task,null,null);
            head.setIndex(0);
        }
    }

    @Override
    public boolean remove(Task task) {
        if(task==null){
            throw new NullPointerException("task cannot be null");
        }
        if(this.head!=null){
            link tail=this.head;
            while (tail.isTail()==false){
                if(compareTasks(tail.getTask(),task)){//we found the wanted task

                    if(tail.isHead()){//we want to get rid of the head
                        this.head=this.head.next;//now head points in the next direction
                        this.head.previous=null;

                        //Reindexing all the links
                        if(this.head!=null){
                            this.head.setIndex(0);
                            link current=this.head.next;
                            while(current!=null){
                                current.setIndex(current.previous.getIndex()+1);
                                current=current.next;
                            }
                        }

                    }else{//we want to get rid of a link different from the head and tail
                        link aux=tail.next;
                        aux.previous=tail.previous;
                        tail.previous.next=aux;
                        //Reindexing all the links
                        link current=aux.previous;
                        while(current!=null){
                            if (current.isHead()){
                                current.setIndex(0);
                            }else{
                                current.setIndex(current.previous.getIndex()+1);
                            }
                            current=current.next;
                        }
                    }
                    return true;
                }
                tail=tail.next;
            }
            if(compareTasks(tail.getTask(),task)){//the tail is the link we want to remove
                tail.previous.next=null;
                //We don't need to reindex
                return true;
            }
            //if we didn't find the wanted task in the list
            return false;
        }else{//there is nothing to remove
            return false;
        }
    }

    @Override
    public int size() {
        if(this.head!=null){
            int counter=0;
            link tail=this.head;
            while(tail.isTail()==false){
                counter++;
                tail=tail.next;
            }
            return counter+1;
        }else{
            return 0;
        }
    }

    @Override
    public Task getTask(int index) {
        link current=this.head;
        while (current!=null){
            if (current.getIndex()==index){
                return current.getTask();
            }
            current=current.next;
        }
        return null;
    }

    @Override
    public LinkedTaskList incoming(int from, int to) {
        if (from<0 || to<0){
            throw new IllegalArgumentException("Time marks cannot be negative");
        } else if (to<=from) {
            throw new IllegalArgumentException("'To' time mark cannot be minor or equal to 'from' time mark");
        }
        if(this.head!=null){
            LinkedTaskListImpl myList=new LinkedTaskListImpl();
            link current=this.head;
            while (current!=null){
                if (current.getTask().nextTimeAfter(from)!=-1){//then task is active and can be completed
                    if (current.getTask().nextTimeAfter(from)<to && current.getTask().nextTimeAfter(from)>from){
                        myList.add(current.getTask());
                    }
                }
                current=current.next;
            }
            return  myList;
        }
        return new LinkedTaskListImpl();
    }

    private class link{
        private link previous;
        private link next;

        private Task task;

        private int index;

        /**
         * Empty class constructor
         */
        public link(){
            this.previous=null;
            this.next=null;
        }

        /**
         *
         * @param t a task to be stored
         * @param prev the previous link
         * @param next the next link
         */
        public link(Task t,link prev, link next){
            this.setTask(t);
            this.previous=prev;
            this.next=next;
        }

        /**
         * Set the previous link
         * @param previous
         */
        public void linkPrevious(link previous){
            this.previous=previous;
        }

        /**
         * Set the next link
         * @param next
         */
        public void linkNext(link next){
            this.next=next;
            next.previous=this;
        }

        /**
         * Return true if current link is head, false otherwise
         * @return
         */
        public boolean isHead(){
            if (previous==null){
                return true;
            }
            return false;
        }

        /**
         * Return true if current link is tail, false otherwise
         * @return
         */
        public boolean isTail(){
            if (next==null){
                return true;
            }
            return false;
        }

        /**
         * Set the task to be store
         * @param task
         */
        public void setTask(Task task){
            this.task= task;
        }

        /**
         * Get the task stored in this link of the list
         * @return task
         */
        public Task getTask(){
            return this.task;
        }


        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

}
