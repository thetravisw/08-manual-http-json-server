import java.io.BufferedWriter;
import java.io.IOException;

public class HTTPResponse {
    public int statusCode;
    public String body;
    public HTTPResponse(int statusCode, String body){
        this.body=body;
        this.statusCode=statusCode;
    }

    public void send (BufferedWriter outToClient){
        try {
        outToClient.write("HTTP/1.1" + statusCode + "\n" );
        outToClient.write("Content-Length: " + this.body.length() + "\n");
        outToClient.write("\n");
        outToClient.write(this.body + "\n");

        outToClient.flush();
        outToClient.close();

        System.out.println("closed request.");
        } catch (IOException e) {
            System.out.println("response body: " + this.body);
            e.printStackTrace();
        }
    }
}
