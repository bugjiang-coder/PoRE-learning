package pore.pj.wechat_prevent_withdraw;

import android.content.ContentValues;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class prevent_withdraw implements IXposedHookLoadPackage {
    private String packet = "com.tencent.mm";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if(!lpparam.packageName.equals(packet)) {
            return;
        }
//        防止撤回

        
        try {
            XposedBridge.log("Hook开始：" + lpparam.processName + ":" + lpparam.packageName);

            XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase",
                    lpparam.classLoader, "updateWithOnConflict", String.class, "android.content.ContentValues", String.class, String[].class, int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            String old =  (String)param.args[0];
                            ContentValues old2 =  (ContentValues)param.args[1];
                            XposedBridge.log("Hook的string："+old);
                            XposedBridge.log("Hook的内容："+old2.toString());
                                param.args[1] = null;

                        }
                    });

        }catch (Throwable t) {
            XposedBridge.log("Hook出错" + t);
        }



    }
}

