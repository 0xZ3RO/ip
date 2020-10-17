# User Guide 

## Getting started
To ensure that all outputs are displayed correctly, please run the program in an environment where the output is displayed in UTF-8 with a suitable font like Raster Fonts. To get a list of commands to use, enter `help` into the program.

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
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Got it. I've added this task:
  [T][✘] borrow book
 Now you have 1 tasks in the list.
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
```

### `event` - Adds an upcoming event with the date to the list
Creates an event with the given description and date, then stores it in the list of existing tasks.
`event <description> /at <when (format: yyyy-mm-dd hhmm)>`

Example of usage:

```
> event project meeting /at 2019-10-15 1000
```

Expected outcome:

```
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Got it. I've added this task:
   [E][✘] project meeting (at: Oct 15 2019 1000)
 Now you have 2 tasks in the list.
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
```

### `deadline` - Adds a task with a deadline to the list
Creates a task with the given description and deadline, then stores it in the list of existing tasks.
`deadline <description> /by <when (format: yyyy-mm-dd hhmm)>`

Example of usage:

```
> deadline return book /by 2019-10-15 1000
```

Expected outcome:

```
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Got it. I've added this task:
   [D][✘] return book (by: Oct 15 2019 1000)
 Now you have 3 tasks in the list.
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
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
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Nice! I've marked this task as done: 
   [E][✓] project meeting (at: Oct 15 2019 1000)
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
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
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Noted! I've removed this task: 
   [T][✘] borrow book
 Now you have 2 tasks in the list.
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
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
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Here are the matching tasks in your list:
  [D][✘] return book (by: Oct 15 2019 1000)
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
```


### `list` - List tasks 
List the stored tasks.
`list`

Example of usage: 
```
> list
```

Expected outcome:

```
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Here are the tasks in your list:
   1. [E][✓] project meeting (at: Oct 15 2019 1000)
   2. [D][✘] return book (by: Oct 15 2019 1000)
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
```


### `help` - Show commands
Show the list of available commands.
`help`

Example of usage: 
```
> help
```

Expected outcome:

```
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Here are the available commands:
   Help
   List
   Todo <task>
   Deadline <task> /by <when (format: yyyy-mm-dd hhmm)>
   Event <task> /at <when (format: yyyy-mm-dd hhmm)>
   Done <index>
   Delete <index>
   Find <task>
   Bye
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
```



### `bye` - Exits program
Exits the program.
`bye`

Example of usage: 
```
> bye
```

Expected outcome:

```
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
 Bye! Hope to see you again soon!
──────────────────────────── ⋆⋅☆⋅⋆ ────────────────────────────
```
