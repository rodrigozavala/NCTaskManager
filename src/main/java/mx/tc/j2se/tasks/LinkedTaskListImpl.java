package mx.tc.j2se.tasks;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LinkedTaskListImpl extends AbstractTaskList {


    private link head;
    private int sizeList=0;
    private link tail;


    public LinkedTaskListImpl(){

    }

    public LinkedTaskListImpl(Task initialTask){
        head=null;
        this.add(initialTask);
    }


    @Override
    public void add(Task task) {
        if(task==null){
            throw new NullPointerException("task passed cannot be null");
        }
        //ADDING
        if (this.head!=null){//if head already exist

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
            throw new NullPointerException("task passed cannot be null");
        }

        if(this.head!=null){//if head exists
            link current=this.head;

            while (current.isTail()==false){
                if(current.getTask().equals(task)){//we found the wanted task
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
            if(current.getTask().equals(task)){//the tail is the link we want to remove
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

        //Predicate<TaskImpl> myFilter1=t->((TaskImpl)task).equals(t);

        //this.getStream().filter(t->myFilter1.negate().test((TaskImpl) t))
          //      .toArray();
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


    /**
     *
     * @return An Iterator for linked lists
     */
    @Override
    public Iterator<Task> iterator(){
        //int position1 = position;

        return new Iterator<Task>() {
            link currentLink=head;
            @Override
            public boolean hasNext() {


                return currentLink!=null;
            }

            @Override
            public Task next() {
                if (hasNext()){
                    Task t= currentLink.getTask();
                    currentLink=currentLink.next;
                    return t;

                }

                return null;
            }
        };

    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject){
            return true;
        }
        if(otherObject==null || getClass()!=otherObject.getClass()){
            return false;
        }

        LinkedTaskListImpl A=(LinkedTaskListImpl) otherObject;

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
        int sum=this.getClass().toString().length()+this.size()^3;
        return super.hashCode()+sum;
    }

    @Override
    public String toString() {
        return "LinkedTaskListImpl"+super.toString();
    }

    @Override
    protected LinkedTaskListImpl clone() throws CloneNotSupportedException {
        return (LinkedTaskListImpl) super.clone();
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
         * @param previous the previous link wanted
         */
        public void linkPrevious(link previous){
            this.previous=previous;
        }

        /**
         * Set the next link
         * @param next the next link wanted
         */
        public void linkNext(link next){
            this.next=next;
            next.previous=this;
        }

        /**
         *
         * @return Return true if current link is head, false otherwise
         */
        public boolean isHead(){
            if (previous==null){
                return true;
            }
            return false;
        }

        /**
         *
         * @return Return true if current link is tail, false otherwise
         */
        public boolean isTail(){
            if (next==null){
                return true;
            }
            return false;
        }

        /**
         * Set the task to be store
         * @param task the task to store
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

    @Override
    public Stream<Task> getStream() {
        return super.getStream();
    }
}
