package mx.tc.j2se.tasks;

public class ArrayTaskListImpl extends AbstractTaskList{



    private Task [] myTaskArray;

    /**
     * Empty constructor of the class
     */
    public ArrayTaskListImpl(){
    }


    private boolean compareTasks(Task t1,Task t2){
        boolean var1=t1.getTitle().equals(t2.getTitle())&& t1.isActive()==t2.isActive();
        boolean var2= t1.isRepeated()==t2.isRepeated() && t1.getEndTime()==t2.getEndTime();
        boolean var3= t1.getTime()==t2.getTime() && t1.getStartTime()==t2.getStartTime();
        boolean var4= t1.getRepeatInterval()==t2.getRepeatInterval();
        return  var1 && var2 && var3 && var4;
    }


    /*
    @Override
    public ArrayTaskList incoming(int from, int to) {
        if(myTaskArray==null){
            return new ArrayTaskListImpl();
        }
        ArrayTaskList myList=new ArrayTaskListImpl();
        for (Task tasks: myTaskArray){
            if (tasks.nextTimeAfter(from)!=-1){//then task is active and can be completed
                if (tasks.nextTimeAfter(from)<to && tasks.nextTimeAfter(from)>from){
                    myList.add(tasks);
                }
            }
        }
        return myList;
    }
     */

    @Override
    public void add(Task task) {
        if (task==null){
            return;
        }
        if (myTaskArray ==null){
            myTaskArray =new Task[]{task};
        }else{
            int lengthArray= myTaskArray.length;
            Task[] oldOne= new Task[lengthArray];
            System.arraycopy(myTaskArray,0,oldOne,0,lengthArray);
            myTaskArray =new Task[lengthArray+1];
            System.arraycopy(oldOne,0, myTaskArray,0,lengthArray);//
            System.arraycopy(new Task[]{task},0, myTaskArray,lengthArray,1);
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
            if(this.compareTasks(task,myTaskArray[i])){
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
}
