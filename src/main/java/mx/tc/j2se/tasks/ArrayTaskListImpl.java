package mx.tc.j2se.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayTaskListImpl extends AbstractTaskList{

    private Task [] myTaskArray;

    /**
     * Empty constructor of the class
     */
    public ArrayTaskListImpl(){
    }


    @Override
    public void add(Task task) {
        if (task==null){
            return;
        }
        if (myTaskArray ==null){
            myTaskArray =new Task[]{task};
        }else{
            int lengthArray= myTaskArray.length;

            Task[] newOne= new Task[lengthArray+1];
            System.arraycopy(myTaskArray,0,newOne,0,lengthArray);
            newOne[lengthArray]=task;
            myTaskArray=newOne;
        }
    }


    @Override
    public boolean remove(Task task) {
        if(myTaskArray==null || task==null){
            return false;
        }
        int indexToRemove=-1;
        //looking for the index of the task to remove
        for (int i = 0; i< myTaskArray.length; i++){
            if(task.equals(myTaskArray[i])){
                indexToRemove=i;
            }
        }


        if(indexToRemove==-1 || indexToRemove<0){ //the task isn't in the array
            return false;
        }else{

            int lengthArray= myTaskArray.length;
            Task[] oldOne= new Task[lengthArray];
            System.arraycopy(myTaskArray,0,oldOne,0,lengthArray);
            if(lengthArray-1>=1){
                myTaskArray =new Task[lengthArray-1];
            }else if(lengthArray-1==0){
                myTaskArray =null;
                return true;
            }

            if(indexToRemove!=0 && indexToRemove!=lengthArray-1){//the task is not the first and neither the last element
                System.arraycopy(oldOne,0, myTaskArray,0,indexToRemove);
                System.arraycopy(oldOne,indexToRemove+1, myTaskArray,indexToRemove,lengthArray-indexToRemove-1);
            } else if (indexToRemove!=0 && indexToRemove==lengthArray-1) {//indexToRemove is the last task in the old array
                System.arraycopy(oldOne,0, myTaskArray,0,lengthArray-1);
            } else{//indexToRemove is the first task in the old list array
                System.arraycopy(oldOne,1, myTaskArray,0,lengthArray-1);
            }
            return true;
        }

    }

    @Override
    public int size() {
        if(myTaskArray ==null){
            return 0;
        }
        return myTaskArray.length;
    }


    @Override
    public Task getTask(int index) {
        if(myTaskArray !=null && index< myTaskArray.length && index>-1){
            return myTaskArray[index];
        }
        return null;
    }
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
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
    }

    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject){
            return true;
        }
        if(otherObject==null || getClass()!=otherObject.getClass()){
            return false;
        }

        ArrayTaskListImpl A=(ArrayTaskListImpl) otherObject;
        if(A.size()!=this.size()){
            return false;
        }
        int counter=0;
        for (Task t: this){
            if(!t.equals(A.getTask(counter))){
                return false;
            }
            counter++;
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
        return "ArrayTaskListImpl"+super.toString();
    }
    @Override
    public ArrayTaskListImpl clone()  {
        try {
            return (ArrayTaskListImpl) super.clone();
        }catch (CloneNotSupportedException e){
            System.err.println(e.getMessage()+"\n"+e.getStackTrace());
            return null;
        }
    }

    @Override
    public Stream<Task> getStream() {
        return super.getStream();
    }
}
