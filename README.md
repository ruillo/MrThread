# MrThread
简介：
MrThread是通过多线程实现的MapReduce的简称  
MrThread是轻量级的MapReduce框架。 它在多个线程上执行MapReduce作业，这些线程可能属于一个进程，或者属于一台计算机的多个进程，或者属于多个计算机的多个进程。  
MrThread用于模拟在较大规模或更大规模集群上运行的MapReduce作业，还用于突出显示MapReduce的分布式核心而不是其他功能。 由于MrThread中的线程上的Worker代表节点上的worker。  

数据存储区：
数据存储区独立于计算平台。 它由本地文件系统实现。 数据存储区有一些概念：Datastore, Shard, Space, Table, Key and Line。  
数据存储区管理所有Shard，其中一些是本地的，一些是远程的。  
Shard是工作者的存储区。  
Shard中包含的空间是在相应Worker上运行的特定Job的存储。  
Shard中包含的Table是相应作业的特定任务的存储。  
Table中包含的Key是相应任务的输出组的名称。  
Line是包含在相应密钥中的一行数据文件。  
Shard, Space, Table, Key都被建模为文件夹，并且行由文本文件实现。  
KeyLine是<Key，Line>数据结构。  

运行过程：
1.用户编写Mapper和Reducer函数。  
2.用户配置server.properties函数。  
3.用户准备数据集。每个目录的文件均为文本文件。服务器与ip，port（端口号）和root（默认名称+端口号）关联。一个目录映射到Worker，子目录是特定job的输入文件，缓存文件和输出文件。  
4.打开每一个MrThread服务器。  
5.MrThread解析server.properties并加载所有服务器。通过ServerRegistry加载每个服务器信息（id，ip，port，workerSize）。  
6.如果不是主服务器（即server.properties的第一行），则服务器开始侦听。  
7.MrThread通过DataStore标识每台服务器上的根目录； 并为所有Worker初始化所有碎片，以目录表示。 对于服务器，可以最初通过FolderBuilder定义这些目录。  
8.MrThread通过URL（IP和端口）标识当前服务器，并通过WorkerLocalPool初始化服务器的所有Worker，这些Worker由服务器URL及其ID标识。  
9.MrThread通过给所有Worker统一的顺序来初始化KeyDistribution。  
10.用户用名字创建一个Job。  
11.用户定义了Mapper和Reducer以及它们的连接方式。 并为该作业创建一组有序Task。  
12.将所有Task提交到master  
13.Master解析Job的任务，并处理任务和排列任务顺序。对于每个任务，将任务提交给每个服务器，然后Master等待直到返回所有结果。（如果服务器是“本地”服务器，则Master只需调用Server的方法，否则Master通过RMI调用它）  
14.对于每个分布式服务器，服务器将为其中每个Worker初始化其本地任务。 Woker是本地Worker，且他们具有不同的WorkerID。  
15.对于Worker中的Tasker，将初始化OutputWriter。 Worker通过Job名称和Task顺序区分不同的Task。  
16.服务器的start方法作为许多Worker线程的主线程运行。  
17.服务器的所有worker返回，并且所有服务器返回，Tasker完成。
