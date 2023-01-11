# OOP Course - Ex2

Submitters: Noy Rosenbaum and Maya Hadad

## Ex2 part 1

### Description

In this assignment, we created several text files and calculated the total number of lines using three methods:
* With no use of Threads or ThreadPool
* Threads
* ThreadPool

#### Threads

A thread is a way to execute a task within a process. A process can contain more than one thread. 
The idea of threads is to divide the tasks and execute them simultaneously. This makes the process execution faster.

#### ThreadPool

Thread pool is a design pattern for achieving multiple tasks being executed out of order. 
A thread pool maintains multiple threads, while each thread waits for a task to execute for the queue.
Threads class can support a single task whereas thread pool can support multiple tasks.

#### Classes description

##### Ex2_1

In this class, we implemented all three methods in addition to a method that creates n text files with a random number of lines in each file. \
For the first method we counted the number of lines in each file, added them up and returned the total number of lines. 
For the second, we created a thread (using our GetLinesThread class). \
The thread will get the number of lines of in each file. Together we'll get the total number of lines. In the third method, we created a ThreadPool (using our ThreadPoolExecutor) which contains n threads. Each thread will get the number of lines in one file (using the GetLinesCallable class) and together we'll the total number of lines.

##### GetLinesThread

In this class we created our own thread class. We extended the class using the Thread class and changed the run method to get the number of lines in a single file.

##### GetLinesCallable

In this class, we implemented Callable interface. The call method is the method that will return the number of lines in one file. 

##### Test Result

In total the random number of lines in our run was 808678. The time it took to read the lines without threads was 423 milliseconds. While using threads it took 53 milliseconds and when using thread pool, it took 48 milliseconds.
Because threads require less resources, the time it took to get the total number of lines without threads was significantly longer. 
The minor difference between using threads and a thread pool is because of the computers resources and the way they are split. 

## Ex2 part 2

### Description

In this part, we overcome a specific limitation of the Java's concurrency designs.
Java enables developers to set the priority of a thread, but not the Runnable operation it executes. \
Tightly coupling the operation with the execution path that runs it creates major drawback when
using an executor such as a ThreadPoolExecutor: the collection of threads in an executor is defined by
a ThreadFactory.  
By default, it creates all threads with the same priority and non-daemon status.
Moreover, if we wish to execute a returning value operation, for example using the Callable<V>
interface, there are no constructors in the Thread class that get a Callable<V> as parameter and we
ought to use an Executor of some type, such as a ThreadPoolExecutor. \
Below we describe how we managed to prioritized a task by its numerical value instead of the built-in prioritization.

#### Classes description

##### Task

##### CustomExecuter

##### TaskType

 Type Enum to descript a Task object's type. Tasks will be classtified as so:
 * COMPUTATIONAL(1)
 * IO(2)
 * OTHER(3)
 The priorities of the tasks are set that COMPUTATIONAL(1) has the highest priority and OTHER(3) has the lowest.