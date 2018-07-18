# ![CF](http://i.imgur.com/7v5ASc8.png) Manual HTTP JSON Server

## Resources  
* [GSON User Guide](https://github.com/google/gson/blob/master/UserGuide.md)

## Part 1: Building a Manual HTTP HTML File Server
Build a manual HTTP server that processes HTTP requests and sends back HTML
files according to the request. The server should look at the path to determine
which file it should load. The server should return with `404` not found errors
if someone requests a page that doesn't exist.

Configure a String property on your server to define the `static` root. The
root of your server should be a file that contains HTML files. Make several
small raw HTML files (without server-hosted images, CSS or JavaScript) that
can be served by your server. Use Java to read the file contents and write
the contents back as part of an HTTP response.

Create classes like `HTTPRequest`, `HTTPResponse` and `HTTPStaticFileReader`
to build useful objects that will help you build out the entire server.

* The `HTTPRequest` class should be responsible for parsing and defining the
  structure of a proper `HTTPRequest`
* The `HTTPResponse` class should be responsible for generating proper HTTP
  responses
* The `HTTPStaticFileReader` class should have access to a `HTTPRequest` object
  and use it to find the path of the requested file, read the file contents,
  then format a `HTTPResponse with the file contents`

## Part 2: Inserting JSON Data in Templates
Create a special template syntax to allow you to insert JSON data in the middle
of parsing an HTML file.

Parse the file by reading it one line at a time. Check each line to see if it
has the special template syntax `{{` and `}}`. Parse out the text between the
curly-braces and look specifically for `RANDOM_JSON_QUOTE`. If
`RANDOM_JSON_QUOTE` is there then replace the entire `{{RANDOM_JSON_QUOTE}}`
portion of the file with an actual random JSON quote when you send out the 
contents of the file in the HTTP Response.

```html
<html>
  <head>
    <title>My Website</title>
  </head>
  <body>
    <h1>Random Book Quote</h1>
    <p>{{RANDOM_JSON_QUOTE}}</p>
  </body>
</html>
```

For example, here's pseudo-code for accomplishing this:

```
read the HTTP request
find the file associated with the request
if the file doesn't exist
  return a 404

read each line of the file
  detect if the line has special symbols {{ }}
  if the symbol doesn't appear
    append the line to the HTTP response

  if the line has those symbols the following:
    send the portion of the line before {{

    extract the text between the symbols      
    execute a function related to the symbol
    send the result of the function related to the symbol

    send the portion of the line before }}
send the entire HTTP response
```

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
