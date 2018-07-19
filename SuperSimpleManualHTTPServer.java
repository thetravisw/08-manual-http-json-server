import java.io.*;
import java.net.*;

class MyHttpServer {
    public static final int PORT = 7890;

    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(PORT);
        System.out.println("Listening on http://localhost:" + PORT);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("waiting for request...");
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedWriter outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

            HTTP_Request request = new HTTP_Request(inFromClient);
            HTTP_Static_File_Reader file = new HTTP_Static_File_Reader(request);

            int statusCode = 200;
            String body = file.getContents();
            HTTPResponse response = new HTTPResponse (statusCode, body);
            response.send(outToClient);
        }
    }
}
