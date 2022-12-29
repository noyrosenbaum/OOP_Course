# OOP Course - Ex1

Submitters: Noy Rosenbaum and Maya **SURNAME NOT COMPLITED**


## Description

In this assignment, we expended UndoableStringBuilder's functionality.
* Added an option to create a group of members that receives real time updates whenever UndoableStringBuilder's status changes.
* Added a track on Object's size in the Heap.

We used Observer Design Pattern by implementing two given interfaces:
* Sender - Observable interface.
* Member - Observer interface.

### Observer

Observer design pattern is a behavioral design pattern that defines a one-to-many dependency between objects. 
It allows an object, called the subject (Admin\Sender) in our assignment, to notify a set of objects, called observers (Members), when its state changes.
The Observer design pattern is implemented by creating a subject class (GroupAdmin) and an observer (ConcreteMember) class. 
The subject class maintains a list of observer objects and has methods for adding or removing observers from the list. 
The observer class has a method for receiving notifications from the subject, called the update method.
When the subject's state changes, it calls the update method on each of its observers, passing the new state as an argument. 
The observers can then use the update method to perform any necessary actions in response to the state change.
 
### GroupAdmin

GroupAdmin class 'represents' our observable, a GroupAdmin's object contains two properties:
* An UndoableStringBuilder object.
* An ArrayList object that stores all registered members.

Each member in the list holds an UndoableStringBuilder object and a name which typed as string, whenever a change is made on
the UndoableStringBuilder, GroupAdmin's object will notify to all the registered members about the change (via "notifyMembers" function).
GroupAdmin's functionality is built on some of UndoableStringBuilder's methods:
* Append
* insert
* Undo
* Delete

### ConcreteMember

ConcreteMember class 'represents' our observer, a ConcreteMember's object contains two properties:
* A name - String type.
* An UndoableStringBuilder reference.

Whenever an update is made on UndoableStringBuilder's object (GroupAdmin's side), the GroupAdmin's object sends an update
(notifyMembers method call at the end of each of GroupAdmin's other methods) to the registered members which are ConcreteMember's
objects and updates their references to point to the GroupAdmin's UndoableStringBuilder object, 'clone()' method does the shallow copy.

### Tests

#### JvmUtiltest

Here we used JvmUtilities class and pom.xml in order to check code's efficiency by looking for object's size in JVM's memory.

#### GroupAdmintest

Here we used JUnit's functionality (assertEquals) to examine GroupAdmin methods' behavior on a ConcreteMember's registered object.