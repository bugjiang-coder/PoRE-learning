package lab1.task4;

import java.util.ArrayList;

public class MyStack<T> {
    // TODO: Finish it!

    private ArrayList<T> list = new ArrayList<T>();
    public boolean isEmpty(){return true;}
    public int getSize(){return 0;}
    public T peek(){return null;}
    public T pop(){return null;}
    public void push(T o){}

    @Override
    public String toString() {
        return "stack:"+list.toString();
    }
}
