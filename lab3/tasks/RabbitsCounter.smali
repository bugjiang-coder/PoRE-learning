.class public LRabbitsCounter;
.super Ljava/lang/Object;


.method public constructor <init>()V
    .registers 1

    .line 5
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static count(Ljava/lang/Integer;)Ljava/lang/Integer;
    .registers 5
    .param p0, "months"

    ####################################################
    #  - You should add your codes here.               #
    #  - Must not change the codes in function "main"  #
    ####################################################
    const/4 v1, 0x2
    const/4 v0, 0x1

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I
    move-result p0
    

    if-le p0, v1, :cond_0

    sub-int v0, p0, v0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
    move-result-object v0
    invoke-static {v0},LRabbitsCounter;->count(Ljava/lang/Integer;)Ljava/lang/Integer;
    move-result-object v3

    sub-int v1, p0, v1
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
    move-result-object v1
    invoke-static {v1},LRabbitsCounter;->count(Ljava/lang/Integer;)Ljava/lang/Integer;
    move-result-object v4
    
    invoke-virtual {v3}, Ljava/lang/Integer;->intValue()I
    move-result v3
    invoke-virtual {v4}, Ljava/lang/Integer;->intValue()I
    move-result v4
    add-int v3,v3,v4
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
    move-result-object v3
    
    
    return-object v3

    :cond_0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
    move-result-object v0
    return-object v0


    
.end method

.method public static main([Ljava/lang/String;)V
    .registers 6
    .param p0, "args"

    const/4 v0, 0x0

    aget-object v0, p0, v0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    .local v0, "months":Ljava/lang/Integer;
    invoke-static {v0}, LRabbitsCounter;->count(Ljava/lang/Integer;)Ljava/lang/Integer;

    move-result-object v1

    .local v1, "res":Ljava/lang/Integer;
    sget-object v2, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "There will be "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v4, " pairs in "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v4, " months."

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    return-void
.end method
