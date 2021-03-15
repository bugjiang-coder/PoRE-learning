package lab1.task1;

import java.util.Random;

public class Task1 {
    public static void foo1(int value) throws NewException666,NewException999 {
        // Do not change this method!
        System.out.println("foo1 start!");
        if (value <=6){
            NewException666 exception= new NewException666("666 Exception");
            throw  exception;
        }else {
            NewException999 exception = new NewException999("999 Exception");
            throw exception;
        }
    }
    public static void foo2(int value) throws NewException {
        // TODO: How to handler exception to get target output?

        try{
            foo1(value);
        }catch(NewException666 e6){
            NewException newException = new NewException("You got a 666 Exception");
            newException.initCause(e6);
            throw  newException;
        }catch(NewException999 e9){
            NewException newException = new NewException("You got a 999 Exception");
            newException.initCause(e9);/*此方法标注错误的原始异常,initCause(Throwable),打印异常时后面会跟原始异常*/
            throw  newException;
        }finally{
            System.out.println("foo2 finished!");
        }

    }
    public static void foo3(int value){
        // Do not change this method!
        try {
            foo2(value);
        }catch (NewException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // Do not change this method!
        int luckynumber=new Random().nextInt(10);
        System.out.println("You lucky number is: "+luckynumber);
        foo3(luckynumber);
    }
}
