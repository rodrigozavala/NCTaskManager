package mx.tc.j2se.tasks;

public class LinkedTaskListImpl extends AbstractTaskList {


    private link head;
    private int sizeList=0;
    private link tail;
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
        if(task==null){
            throw new NullPointerException("task cannot be null");
        }
        //ADDING
        if (this.head!=null){//if head already exist
            /*
            link current=this.head;
            while (current.isTail()==false){//repeats until the real tail is found
                current=current.next;
            }
            current.next=new link(task,current,null);*/
            /*if (this.sizeList==1){
                this.head.next=new link(task,head,null);
                this.tail=this.head.next;
                this.tail.previous=this.head;
            }else{
                this.tail.next=new link(task,tail,null);
                this.tail=this.tail.next;

            }
             */
            this.tail.next=new link(task,tail,null);
            this.tail=this.tail.next;
        }else{//head doesn't exist
            this.head=new link(task,null,null);
            this.tail=head;
        }
        this.sizeList+=1;
    }

    @Override
    public boolean remove(Task task) {
        if(task==null){
            throw new NullPointerException("task cannot be null");
        }

        if(this.head!=null){//if head exists
            link current=this.head;

            while (current.isTail()==false){
                if(compareTasks(current.getTask(),task)){//we found the wanted task
                    this.sizeList-=1;
                    //REMOVING
                    if(current.isHead()){//we want to get rid of the head
                        this.head=this.head.next;//now head points in the next direction
                        this.head.previous=null;

                    }else{//we want to get rid of a link different from the head and tail
                        link aux=current.next;
                        aux.previous=current.previous;
                        current.previous.next=aux;

                    }
                    return true;
                }
                current=current.next;
            }
            if(compareTasks(current.getTask(),task)){//the tail is the link we want to remove
                this.sizeList-=1;
                current.previous.next=null;
                this.tail=current.previous;
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
        return sizeList;
    }

    @Override
    public Task getTask(int index) {
        link current=this.head;
        int counter=0;
        while (current!=null){
            if (counter==index){
                return current.getTask();
            }
            counter+=1;
            current=current.next;
        }
        return null;
    }



    private class link{
        private link previous;
        private link next;

        private Task task;



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


    }

}
