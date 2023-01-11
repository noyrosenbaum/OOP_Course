# OOP Course - Ex2

Submitters: Noy Rosenbaum and Maya Hadad

- [OOP Course - Ex2](#oop-course---ex2)
  - [Ex2 part 1](#ex2-part-1)
    - [Description](#description)
      - [Threads](#threads)
      - [ThreadPool](#threadpool)
      - [Classes description](#classes-description)
        - [Ex2\_1](#ex2_1)
        - [GetLinesThread](#getlinesthread)
        - [GetLinesCallable](#getlinescallable)
      - [Test Result](#test-result)
  - [Ex2 part 2](#ex2-part-2)
    - [Description](#description-1)
      - [Classes description](#classes-description-1)
        - [Task](#task)
        - [CustomExecutor](#customexecutor)
        - [TaskType](#tasktype)
  - [Initialization - setup](#initialization---setup)
    - [Setup project locally](#setup-project-locally)
      - [Clone an existing repository (**if it does not exist locally already**):](#clone-an-existing-repository-if-it-does-not-exist-locally-already)
      - [Run code](#run-code)


## Ex2 part 1

### Description

In this assignment, we created several text files and calculated the total number of lines using three methods:
* With no use of Threads or ThreadPool
* Threads
* ThreadPool
  
**NOTE:**  For detailed explanations about any of the code sections, check out JavaDoc in each one of the scripts.

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

#### Test Result

In total the random number of lines in our run was 808678. The time it took to read the lines without threads was 423 milliseconds. While using threads it took 53 milliseconds and when using thread pool, it took 48 milliseconds.
Because threads require less resources, the time it took to get the total number of lines without threads was significantly longer. 
The minor difference between using threads and a thread pool is because of the computers resources and the way they are split. 

## Ex2 part 2

### Description

In this part, we overcome a specific limitation of the Java's concurrency designs. \
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

Task class creates a generic task with a Type that returns a result and may throw an exception. \
Each task has a priority used for scheduling, inferred from the integer value of the task's Type. \
The class extends FutureTask class so we could have it's functionality to return a result and/or throw an exception upon completion. \
It is used to submit tasks to an Executor and retrieve the result asynchronously.
In addition, it implements Callable and Comparable in order to make Task's objects to be typed Callable and enables comparison of 2 objects in order to keep the tasks ordered in the priority queue by their priorities.

##### CustomExecutor

CustomExecutor class creates a custom thread pool class that defines a method for submitting a generic task as described below to a priority queue, and a method for submitting a generic task created by a
Callable<V> and a Type, passed as arguments. \
The class extends ThreadPoolExecutor which is an implementation of the Executor interface and it is used to manage a pool of worker threads, this class allows you to create a pool of worker threads that can be reused to execute a large number of tasks concurrently. \
The pool is able to adjust the number of active threads dynamically, based on the number of tasks currently waiting to be executed. \
CustomExecutor's constructor "inherits" ThreadPoolExecutor's constructor and has custom hardware adjestments. 

##### TaskType

 Type Enum to descript a Task object's type. Tasks will be classtified as so:
 * COMPUTATIONAL(1)
 * IO(2)
 * OTHER(3)
 The priorities of the tasks are set that COMPUTATIONAL(1) has the highest priority and OTHER(3) has the lowest.

## Initialization - setup

### Setup project locally

#### Clone an existing repository (**if it does not exist locally already**):

NOTE: *Get in the directory where you want to execute the program before.* \
To clone a repository locally do:
```
git clone <repo URL>
```
For example:
```
git clone https://github.com/noyrosenbaum/OOP_Course.git
```

#### Run code

1. `cd` to the existing repo directory.
```sh
cd ~/OOP_Course/Ex2
```
2. For Ex2_1, simply run Ex2_1 class.
3. For Ex2_2, use the attached tests.