package pore.pj.wechat_cheat;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookChat_cheat implements IXposedHookLoadPackage {
    private String packet = "com.tencent.mm";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if(!lpparam.packageName.equals(packet)) {
            return;
        }
//        骰子控制点数

        try {
            XposedBridge.log("Hook开始：" + lpparam.processName + ":" + lpparam.packageName);
            XposedHelpers.findAndHookMethod("com.tencent.mm.sdk.platformtools.Util",
                    lpparam.classLoader, "getIntRandom", int.class, int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            if(((int)param.args[0] == 5) && ((int)param.args[1] == 0)){
                                XposedBridge.log("将骰子点数设置为6");
                                param.setResult(5);
                            }
                        }
                    });
        }catch (Throwable t) {
            XposedBridge.log("Hook出错" + t);
        }



    }
}

