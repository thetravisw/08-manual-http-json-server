# ![CF](http://i.imgur.com/7v5ASc8.png) Popular Book Quotes

## Resources  
* [GSON User Guide](https://github.com/google/gson/blob/master/UserGuide.md)

## Feature Tasks
Use the file `recentquotes.json` to show random popular book quotes on a webpage.
Your program should use `GSON` to parse the .json file. The page needs no
functionality other than showing the quote, and the author. The page should
choose one quote each time the page refreshes.

## Input / Output
Here's code snippets to help you receive connections and send responses:

```java
import java.io.*;
import java.net.*;
```

```java
ServerSocket welcomeSocket = new ServerSocket(PORT);

boolean isRunning = true;
while (isRunning) {
  Socket connectionSocket = welcomeSocket.accept();
  BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
  BufferedWriter outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

  String requestLine = inFromClient.readLine();
}
```

You must follow the HTTP specification. The specification for a response
requires writing a first line with the version, and status code. One header
must include `Content-Length` and have the value of the number of characters in
the HTTP response body.

```java
// write the body
StringBuffer httpBody = new StringBuffer();
httpBody.write("<h1>random quotes</h1>\n");
httpBody.write("<p>refresh page to see another random quote!</p>\n");

// create content
String quote = getRandomQuote();
httpBody.write("<p>");
httpBody.write(qoute);
httpBody.write("</p>");

outToClient.write("HTTP/1.1 200 OK\n");
outToClient.write("Content-Length: " + httpBody.length() + "\n");
outToClient.write("\n");
outToClient.write(httpBody + "\n");
```
  
## Testing  
* Use JUnit to write a test to make sure quotes are chosen appropriately
* Test to make sure there are no `ArrayIndexOutOfBounds` exceptions by having
  off-by-one errors at the front or back of the list.
  * make a test that guarantees the first quote is returned
  * make a test that guarantees a middle quote is returned
  * make a test that guarantees the last quote is returned

## Documentation
* Provide a brief description of what the application does in `README.md`.
* Include clear instructions of how to install dependencies and run the
  app after someone clones the repo.

## Stretch Goals
Write a program that reads through all of the JSON data in the file containing
"IMDB Most Popular by Year." Tally up each genre for each year and print out
the most popular genre for the year.

## Submission Instructions
* Work in a fork of this repository
* Work in a branch on your fork
* Write all of your code in a directory named `lab-` + `<your name>` **e.g.** `lab-susan`
* Open a pull request to this repository
* Submit on canvas a question and observation, how long you spent, and a link to
  your pull request
