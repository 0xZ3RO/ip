# User Guide

## Features 




### Track tasks 
This program helps you to keep track of upcoming events, deadlines or any tasks to complete.
At any point of time, you can mark a task as done, or delete it from the list. 

You can also choose to search for tasks from the list with a keyword that you input in the command line.

### Persistent storage
The program stores the list of tasks locally on your computer in a text file.


### `todo` - Adds a general task to the list

Creates a task with the given description and stores it in the list of existing tasks.
`todo <description>`

Example of usage: 

```
> todo borrow book
```

Expected outcome:

```
  Added: [T][✘] borrow book
  You now have 1 task in your list.
```

### `event` - Adds an upcoming event with the date to the list
Creates an event with the given description and date, then stores it in the list of existing tasks.
`event [description] /at [when (format: yyyy-mm-dd hhmm)]`

Example of usage:

```
> event project meeting /at 2019-10-15 1000
```

Expected outcome:

```
  Added: [E][✘] project meeting (at: Oct 15 2019 1000)
  You now have 2 tasks in your list.
```

### `deadline` - Adds a task with a deadline to the list
Creates a task with the given description and deadline, then stores it in the list of existing tasks.
`deadline [description] /by [when (format: yyyy-mm-dd hhmm)]`

Example of usage:

```
> deadline return book /by 2019-10-15 1000
```

Expected outcome:

```
  Added: [D][✘] return book (at: Oct 15 2019 1000)
  You now have 3 tasks in your list.
```

### `done` - Marks a task as done

Marks a task in the list as done based on the given index.
`done <index>`

Example of usage: 

```
> done 2
```

Expected outcome:

```
  Ok! I've marked this task as done:
    [E][✓] project meeting (at: Oct 15 2019 1000)
```

### `delete` - Deletes a task

Deletes a task from the list based on the given index.
`done <index>`

Example of usage: 

```
> delete 1
```

Expected outcome:

```
  Ok! I've deleted this task:
    [T][✘] borrow book
```


### `find` - Searches for 
Searches for tasks that contains the given text.
`find <filter>`

Example of usage: 
```
> find book
```

Expected outcome:

```
  Here are the tasks that contains the text "beef"
  1. [D][✘] return book (at: Oct 15 2019 1000)
```

