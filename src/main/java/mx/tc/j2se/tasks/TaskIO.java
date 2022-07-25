package mx.tc.j2se.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

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
    public static void writeBinary(AbstractTaskList tasks, OutputStream out){
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
    public static void readBinary(AbstractTaskList tasks, InputStream in){
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
    public static void write(AbstractTaskList tasks, File file){
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
    public static void read(AbstractTaskList tasks, File file){
        try {
            readBinary(tasks,new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * – writes tasks from the list to the stream in
     *     the JSON format.
     * @param tasks
     * @param out
     */
    public static void write(AbstractTaskList tasks, Writer out){
        Gson myGson=new GsonBuilder().registerTypeAdapter(AbstractTaskList.class,new myTaskListAdapter()).create();
        myGson.toJson(tasks,AbstractTaskList.class,out);
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * – reads tasks from the JSON stream to the list.
     * @param tasks
     * @param in
     */
    public static void read(AbstractTaskList tasks, Reader in){
        Gson myGson=new GsonBuilder().registerTypeAdapter(AbstractTaskList.class,new myTaskListAdapter()).create();
        AbstractTaskList myList=myGson.fromJson(in,AbstractTaskList.class);
        for (Task t: myList){
            tasks.add(t);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * – writes tasks to the file in JSON format
     * @param tasks
     * @param file
     */
    public static void writeText(AbstractTaskList tasks, File file){
        try {
            write(tasks,new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  – reads tasks from the JSON file.
     * @param tasks
     * @param file
     */
    public static void readText(AbstractTaskList tasks, File file){
        try {
            read(tasks,new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public static class myTaskAdapter extends TypeAdapter<Task>{

        @Override
        public void write(JsonWriter writer, Task task) throws IOException {
            if (task == null) {
                writer.nullValue();
                return;
            }
            writer.beginObject();
            writer.name("Title");
            writer.value(task.getTitle());
            writer.name("IsActive");
            writer.value((task.isActive())?"true":"false");
            writer.name("Interval");
            writer.value(task.getRepeatInterval());
            if(task.isRepeated()){
                writer.name("Start Time");
                writer.value(task.getStartTime().toString().replace("T"," "));
                writer.name("End Time");
                writer.value(task.getEndTime().toString().replace("T"," "));
            }else{
                writer.name("Time");
                writer.value(task.getTime().toString().replace("T"," "));
            }
            writer.endObject();
        }

        @Override
        public Task read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            Task t=new TaskImpl();
            reader.beginObject();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if(reader.nextName().equals("Title")){
                t.setTitle(reader.nextString());
            }
            if(reader.nextName().equals("IsActive")){
                t.setActive(Boolean.valueOf(reader.nextString()));
            }
            if(reader.nextName().equals("Interval")){

                long interval=(long)Integer.valueOf(reader.nextString());
                if(interval==0){
                    if(reader.nextName().equals("Time")){
                        t.setTime(LocalDateTime.parse(reader.nextString(),formatter));
                    }
                }else{
                    if(reader.nextName().equals("Start Time")){
                        LocalDateTime start=LocalDateTime.parse(reader.nextString(),formatter);
                        if(reader.nextName().equals("End Time")){
                            LocalDateTime end=LocalDateTime.parse(reader.nextString(),formatter);
                            t.setTime(start,end,interval);
                        }
                    }

                }
                reader.endObject();
            }

            return t;
        }
    }
    public static class myTaskListAdapter extends TypeAdapter<AbstractTaskList>{

        @Override
        public void write(JsonWriter writer, AbstractTaskList tasks) throws IOException {
            if (tasks == null) {
                writer.nullValue();
                return;
            }
            Gson myGson=new GsonBuilder().registerTypeAdapter(Task.class,new myTaskAdapter()).create();
            writer.beginArray();
            for(Task t: tasks){
                myGson.toJson(t,Task.class,writer);
            }
            writer.endArray();

        }

        @Override
        public AbstractTaskList read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            Gson myGson=new GsonBuilder().registerTypeAdapter(Task.class,new myTaskAdapter()).create();
            AbstractTaskList myList=new LinkedTaskListImpl();
            reader.beginArray();
            while(reader.hasNext()){
                myList.add(myGson.fromJson(reader,Task.class));
            }
            reader.endArray();
            //System.out.println(myList);
            return myList;
        }
    }


}
