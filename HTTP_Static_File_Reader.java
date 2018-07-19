import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HTTP_Static_File_Reader {
    private String path;

    private static final String FilePathRoot = "C:\\Users\\H_K_K\\codefellows\\401\\Folder-For-Lab08";

    public HTTP_Static_File_Reader(HTTP_Request request){
        this.path=request.RequestPath;
    }

    public String getContents() throws IOException {
        String result = "";

        String filepath = FilePathRoot + this.path;
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            result += processLine(line);

        }
        return result;
    }

    private String processLine(String line){
        if (line.contains("{{") && line.contains("}}")){
            String[] split = line.split("\\{\\{");
            String split0 = split[0];
            split = split[1].split("\\}\\}");
            String split1 = split[0];
            String split2 = split[1];

            String content = "";

            if (split1.equals("RANDOM_JSON_QUOTE")){
                content = randomJSONQuote.send();
            }

            return split0 + content + split2;
        } else {
            return line;
        }
    }


}

