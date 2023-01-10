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

In this class, we implemented all three methods in addition to a method that creates n text files with a random number of lines in each file.
For the first method we counted the number of lines in each file, added them up and returned the total number of lines. 
For the second, we created two threads (using our NewThread class). Each thread will get the number of lines of half of the files. 
Together we'll get the total number of lines. In the third method, we created a ThreadPool (using our ThreadPool class) which contains n threads. 
Each thread will get the number of lines in one file and together we'll the total number of lines.

##### NewThread

In this class we created our own thread class. We extended the class using the Thread class and changed the run method to get the number of lines in a single file.

##### ThreadPool

In this class, we created our own ThreadPool class. This class extends ThreadPoolExecutor and implements Callable, to execute the tasks (finding number of lines in each file) and using the call method to actually return the number of line in each file.

## Ex2 part 2

### Description

In this part, we overcome a specific limitation of the Java's concurrency designs.


#### Classes description

##### Task

##### CustomExecuter

##### TaskType

 Type Enum to descript a Task object's type. Tasks will be classtified as so:
 * COMPUTATIONAL(1)
 * IO(2)
 * OTHER(3)
 The priorities of the tasks are set that COMPUTATIONAL(1) has the highest priority and OTHER(3) has the lowest.






