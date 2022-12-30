# Observer Project
## Ex1 - practice observer design pattern


In this assignment, we expanded on the previous assignment in which we developed a new type of string builder that allows an 'undo' operation to be performed.
This time, We gave the control over the 'UndoableStringBuilder' object to a group admin class "GroupAdmin". In addition, we implemented "Member" Interface that allows class that implemens it (e.g. 'ConcreteMember') to register to a group admin and receive notifications of any changes of it's undoableStringBuilder. for that, we used the "Observer" design pattern that we learned.

In this project:
- "Observer" design pattern
- Tests classes



## Design Pattern
The Observer Pattern defines a one to many dependency between objects so that one object changes state (in our case - group admin), all of its dependents (in our case - members) are notified and updated automatically.

## Data Structure
We used HashSet to hold the member list of each group admin.
A HashSet is a collection of items where every item is unique, and it is found in the java.util package.
The advantage of using HashSet is running time obtained by the hashing function, which is mainly relevant in the remove method, which now is O(1).
Also, by using a set, we avoid duplicate registration of a member.

## Install & Build via Maven

```sh
git clone https://github.com/SagitMalka/OOP1.git observer
cd observer
mvn clean 
mvn install
```


## Contributors
- Sagit Malka
- Liel Yoash

ARIEL University

**enjoy!**

