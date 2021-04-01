.class public LLittleSpider;
.super Ljava/lang/Object;
.source "LittleSpider.java"


# direct methods
.method public constructor <init>()V
    .registers 1

    .line 8
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getPageSource(Ljava/lang/String;)Ljava/lang/String;
    .registers 6
    .param p0, "urlStr"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    #######################################################
    #  - You should add your codes here.                  #
    #  - Must not change :                                #
    #     1. the codes in function "main";                #
    #     2. the codes at the beginning of this function. #
    #######################################################
    new-instance v0, Ljava/lang/StringBuilder;
    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V    

    new-instance v1, Ljava/net/URL;
    invoke-direct {v1,p0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V    

    invoke-virtual {v1}, Ljava/net/URL;->openStream()Ljava/io/InputStream;
	move-result-object v1

    new-instance v2, Ljava/io/InputStreamReader;
    invoke-direct {v2,v1}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V    

    new-instance v1, Ljava/io/BufferedReader;
    invoke-direct {v1,v2}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    const-string v2, ""   #line

    :goto_0

    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;
	move-result-object v2           #line

    if-eqz v2,:cond_0
    invoke-virtual {v0,v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :cond_0
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
	move-result-object v0
    return-object v0

.end method

.method public static main([Ljava/lang/String;)V
    .registers 4
    .param p0, "argv"    # [Ljava/lang/String;

    const/4 v0, 0x0

    aget-object v0, p0, v0

    .local v0, "target":Ljava/lang/String;
    :try_start_3
    invoke-static {v0}, LLittleSpider;->getPageSource(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .local v1, "content":Ljava/lang/String;
    sget-object v2, Ljava/lang/System;->out:Ljava/io/PrintStream;

    invoke-virtual {v2, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V
    :try_end_c
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_c} :catch_d

    .end local v1    # "content":Ljava/lang/String;
    goto :goto_11

    :catch_d
    move-exception v1

    .local v1, "e":Ljava/io/IOException;
    invoke-virtual {v1}, Ljava/io/IOException;->printStackTrace()V

    .end local v1    # "e":Ljava/io/IOException;
    :goto_11
    return-void
.end method
