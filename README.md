## nett物联网的介绍

1. 什么是物联网
    Nio通信框架
2. 物联网主要运用到netty哪些特性
    - Tcp 长连接
    - 能够和各种序列化框架完美整合
3. 为什么要用netty,相当于其他通信框架，比如mina有哪些优点
    - 写复杂，但使用非常简单，性能更高，稳定性更好
    - 内存池管理，零拷贝（直接内存）
    - netty版本更新快，社区活跃度比较好，mina基本不更新
    - elasticsearch,spark等底层使用netty，名气大