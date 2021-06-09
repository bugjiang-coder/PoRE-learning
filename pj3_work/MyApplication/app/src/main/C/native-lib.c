#include <jni.h>
#include "kernal.c"
#include <string.h>
#include <malloc.h>

/**
 * 把一个jstring转换成一个c语言的char* 类型.
 */
char* _JString2CStr(JNIEnv* env, jstring jstr) {
     char* rtn = NULL;
     jclass clsstring = (*env)->FindClass(env, "java/lang/String");
     jstring strencode = (*env)->NewStringUTF(env,"GB2312");
     jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
     jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid, strencode); // String .getByte("GB2312");
     jsize alen = (*env)->GetArrayLength(env, barr);
     jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
     if(alen > 0) {
        rtn = (char*)malloc(alen+1); //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen]=0;
         }
     (*env)->ReleaseByteArrayElements(env, barr, ba,0);
     return rtn;
}

jstring Java_pore_pj3_maze_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject jobj,
        jstring jstr) {
    char *input = _JString2CStr(env , jstr);
    const char *str = "Wrong way";
    const char *str2 = "The road may be right, but the flag is not here";
    if(judge(input)){
        return (*env)->NewStringUTF(env, str2);
    }

    return (*env)->NewStringUTF(env, str);
}