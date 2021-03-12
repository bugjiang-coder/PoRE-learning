package lab1.task2;

public class Task2 {
    public static void main(String[] args) {
        // Do not change this method!
        Add myadd = new Add();

        int[] num1 = new int[25];
        int[] num2 = new int[25];
        int[] num3 = new int[25];
        int[] num4 = new int[25];
        for(int i = 0;i < 25;i++){
            num1[i] = i+1;
            num2[i] = i+26;
            num3[i] = i+51;
            num4[i] = i+76;
        }

        Calc c1 = new Calc(myadd,num1);
        Calc c2 = new Calc(myadd,num2);
        Calc c3 = new Calc(myadd,num3);
        Calc c4 = new Calc(myadd,num4);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        t2.start();
        t1.start();
        t4.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();

            if(myadd.sum==5050){
                System.out.println("Congratulations, you get the right answer!");
            }
            else{
                System.out.println("You get "+myadd.sum+",Try again!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
