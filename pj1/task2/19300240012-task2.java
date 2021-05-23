package pore.pj.wechat_delay_message;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static java.lang.Thread.sleep;

public class HookChat_delay_message implements IXposedHookLoadPackage {
    private String packet = "com.tencent.mm";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if(!lpparam.packageName.equals(packet)) {
            return;
        }
//        聊天延时发消息
        
        try {
            XposedBridge.log("Hook开始：" + lpparam.processName + ":" + lpparam.packageName);

            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.chatting.d.aw",
                    lpparam.classLoader, "gW", String.class, int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            String old = (String) param.args[0];
                            int size = old.length();
                            int time = -1;
                            if (size > 9 && (old.substring(0, 7)).equals("@timer:")) {
                                for (int i = 7; i < size; ++i) {
                                    if (old.charAt(i) == ':') {
                                        int leftLength = i - 7;
                                        if (leftLength > 3 && ((old.substring(i - 3, i)).equals("min"))) {
                                            time = Integer.parseInt(old.substring(7, i - 3));
                                            time *= 60;
                                        }
                                        if (leftLength > 1 && old.charAt(i - 1) == 's') {
                                            time = Integer.parseInt(old.substring(7, i - 1));
                                        }
                                        if(time >= 0){
                                            param.args[0] = "";
                                            XposedBridge.log("延时" + time + 's');
                                            int finalTime = time;
                                            String finalMessage = old.substring(i+1, size);
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Thread.sleep(finalTime * 1000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    XposedHelpers.callMethod(param.thisObject, "gW", finalMessage, param.args[1]);
                                                }
                                            }.start();
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    });


        }catch (Throwable t) {
            XposedBridge.log("Hook出错" + t);
        }



    }
}

