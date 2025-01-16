# Java To-Do List Application

This is a Java application that allows users to create and manage multiple to-do lists.

## Features

-   Create to-do lists with custom names.
-   Add to-do items with unique IDs and text descriptions.
-   Display all to-do lists and their items with timestamps.
-   Manage to-do lists (view existing lists).

## Classes

-   **TodoItem:** Defines the structure of a to-do item with properties like ID, text, completion status, timestamp, and formatted date/time string.
-   **TodoList:** Manages a list of TodoItem objects and provides methods to add items, display the list, and get all existing lists.
-   **todo (main class):** Demonstrates the application's functionality by creating and managing to-do lists.

## Getting Started

1. Clone the repository.
2. Navigate to the project directory.
3. (Optional) Install any required dependencies.
4. Run the application.

This will compile the project and run the `todo` class, which demonstrates the application's functionality.

## MongoDB Driver Installation

To use MongoDB with this application, you need to install the MongoDB Java driver. You can add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.3.0</version>
</dependency>
```

## MongoDB Example CRUD Operations

1. Insert a document into a collection:

```java
ObjectID id = new ObjectId();
InsertOneResult r = collection.insertOne(new Document()
                    .append("_id", id)
                    .append("text", text)
                    .append("dateTime", StringDateTime)
                    .append("check", check));
```

2. Find a document in a collection:

```java
    ObjectId ob = new ObjectId("67853947c0342a354f6817a9");
    Document d = collection.find(new Document().append("_id", ob)).first();
    System.out.println(d.toJson());
```

3. Update

```java
        TodoItem item = new TodoItem();
        ObjectId todoId = item.setItem(text);
        collection.updateOne(new Document().append("_id", ListId), new Document().append("$push", new Document().append("list", todoId)));
```
