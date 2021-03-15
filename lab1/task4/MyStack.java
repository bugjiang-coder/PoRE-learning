package lab1.task4;

import java.util.ArrayList;

public class MyStack<T> {
    // TODO: Finish it!

    private ArrayList<T> list = new ArrayList<T>();
    public boolean isEmpty(){return list.isEmpty();}
    public int getSize(){return list.size();}
    public T peek(){return list.get(list.size()-1);}
    public T pop(){
        int index = list.size()-1;
        T temp = list.get(index);
        list.remove(index);
        return temp;
    }
    public void push(T o){
        list.add(o);
    }

    @Override
    public String toString() {
        return "stack:"+list.toString();
    }
}
