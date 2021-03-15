package lab1.task3;

public class PhoneSystem {

    // You need to modify this class.
    // TODO: How to set callback?
    CSCallBack callBack;


    public void performOnClick(CSCallBack InterFace) {
        callBack = InterFace;
        System.out.println("performOnClick clicked");
        try {
            callBack.OnClick();
            Thread.sleep( 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
