package pore.pj.wechat_autochat;
import android.content.ContentValues;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class autoChat implements IXposedHookLoadPackage {
    private String packet = "com.tencent.mm";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if(!lpparam.packageName.equals(packet)) {
            return;
        }
//        自动回复消息

        
        try {
            XposedBridge.log("Hook开始：" + lpparam.processName + ":" + lpparam.packageName);



            final Object[] sendMassage = {null};

            XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase",
                    lpparam.classLoader, "insert", String.class, String.class, "android.content.ContentValues",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            String type =  (String)param.args[0];
                            ContentValues content = (ContentValues) param.args[2];
                            XposedBridge.log("Hook的类型"+type);
                            String contentS;
                            if(type == "message" && sendMassage[0] != null) {
                                contentS = content.getAsString("content");
                                XposedHelpers.callMethod(sendMassage[0], "axj", "收到了：" + contentS);
                                XposedBridge.log("Hook的content：" + contentS);

                            }
                        }
                    });




            XposedHelpers.findAndHookMethod("com.tencent.mm.pluginsdk.ui.chat.ChatFooter",
                    lpparam.classLoader, "setFooterEventListener", "com.tencent.mm.pluginsdk.ui.chat.b",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                            sendMassage[0] = XposedHelpers.getObjectField(param.thisObject, "Hvw");
                            XposedBridge.log("实例化接口");

                        }
                    });
        }catch (Throwable t) {
            XposedBridge.log("Hook出错" + t);
        }



    }
}

