package lab1.task2;

public class Add{
    public int sum=0;

    // TODO: How to make add stable?

    public synchronized void add(int i){
        sum+=i;
    }

}
