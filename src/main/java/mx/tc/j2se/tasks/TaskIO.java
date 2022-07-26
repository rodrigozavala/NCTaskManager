package mx.tc.j2se.tasks;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskIO {
    /**
     * writes the tasks from the
     * list to the stream in a binary format, described below.
     * @param tasks
     * @param out
     */
    public void writeBinary(ArrayTaskList tasks, OutputStream out){
        DataOutputStream dos=new DataOutputStream(out);
        try {

            dos.writeInt(tasks.size());
            int i;
            for (i = 0; i < tasks.size(); i++){
                dos.writeInt(tasks.getTask(i).getTitle().length());
                dos.writeUTF(tasks.getTask(i).getTitle());
                dos.writeUTF(("Activity: "+((tasks.getTask(i).isActive())?"1":"0")));
                dos.writeLong(tasks.getTask(i).getRepeatInterval());
                if(tasks.getTask(i).isRepeated()){
                    dos.writeUTF(String.valueOf(tasks.getTask(i).getStartTime()).replace("T", " "));
                    dos.writeUTF(String.valueOf(tasks.getTask(i).getEndTime()).replace("T"," "));
                }else{
                    dos.writeUTF(String.valueOf(tasks.getTask(i).getTime()).replace("T"," "));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                dos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Reads tasks from the binary
     *     stream to the current task list.
     * @param tasks
     * @param in
     */
    public void readBinary(ArrayTaskList tasks, InputStream in){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DataInputStream dis=new DataInputStream(in);
        try {
            int sizeOfList=dis.readInt();
            int counter=0;
            while(counter<sizeOfList){
                int namelength=dis.readInt();
                Task t=new TaskImpl();
                String title="";
                Boolean isActive=false;
                title+=dis.readUTF();
                while(namelength!=title.length()){
                    title+=dis.readUTF();
                }
                t.setTitle(title);
                isActive=(dis.readUTF().contains("1"))?true:false;
                t.setActive(isActive);
                long interval=dis.readLong();

                if(interval==0){
                    LocalDateTime time=LocalDateTime.parse(dis.readUTF(),formatter);
                    t.setTime(time);

                }else{
                    LocalDateTime start=LocalDateTime.parse(dis.readUTF(),formatter);
                    LocalDateTime end=LocalDateTime.parse(dis.readUTF(),formatter);
                    t.setTime(start,end,interval);
                }
                tasks.add(t);


                counter++;
            }
            dis.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Writes tasks from the list to the binary file.
     * @param tasks
     * @param file
     */
    public void write(ArrayTaskList tasks, File file){
        try {
            writeBinary(tasks,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Reads tasks from the binary file to the task
     * list.
     * @param tasks
     * @param file
     */
    public void read(ArrayTaskList tasks, File file){
        try {
            readBinary(tasks,new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
