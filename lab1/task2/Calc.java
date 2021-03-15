package lab1.task2;

public class Calc implements Runnable{
    // Do not change this class!
    Add a;
    private int[] nums;

    public Calc(Add a,int[] nums){
        this.a=a;
        this.nums=nums;
    }
    @Override
    public void run() {
        try {
            for (int k : this.nums) {
                this.a.add(k);
                Thread.sleep(30);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
