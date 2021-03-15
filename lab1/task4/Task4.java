package lab1.task4;

public class Task4 {
    public static void main(String[] args) {
        // Do not change this method!
        MyStack<Integer> myStack = new MyStack<Integer>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println(myStack);
        System.out.println("pop:"+myStack.pop());
        System.out.println("pop:"+myStack.pop());
        System.out.println(myStack);
        myStack.push(6);
        myStack.push(7);
        System.out.println(myStack);
        myStack.push(8);
        System.out.println("pop:"+myStack.pop());
        System.out.println("pop:"+myStack.pop());
        System.out.println("pop:"+myStack.pop());
        System.out.println("pop:"+myStack.pop());
        myStack.push(9);
        myStack.push(10);
        System.out.println(myStack);
        System.out.println("top element:"+myStack.peek());
        System.out.println("stack size:"+myStack.getSize());
        System.out.println("isEmpty:"+myStack.isEmpty());
    }
}
