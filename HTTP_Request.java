import java.io.BufferedReader;
import java.io.IOException;

public class HTTP_Request {
    public String RequestPath;

    public HTTP_Request(BufferedReader inFromClient) {
        ProcessRequest(inFromClient);
    }

    public  void  ProcessRequest(BufferedReader inFromClient) {
        try {
            // peel off the first GET/POST PATH line
            String requestLine = inFromClient.readLine();
            this.RequestPath = requestLine.split(" ")[1];

            if(this.RequestPath.equals("/")){
                this.RequestPath="/index.html";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


