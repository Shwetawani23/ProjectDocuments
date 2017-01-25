import java.util.*;
import java.io.*; // for test program only

public class Access {

// Access Log file (NCSA format) analysis

String Host;
String Time;
String Request;
String Method;
String URL;
int status;
int size = 0;
String Referer;
String UserAgent;

/* Sample data line:

180.193.30.81 - - [11/Nov/2010:03:32:39 +0000] "GET /resources/J703.html
HTTP/1.1" 200 21984 "http://www.wellho.net/course/sjfull.html" "Mozilla/5.0
(Windows; U; Windows NT 5.2; en-US; rv:1.9.0.19) Gecko/2010031422
Firefox/3.0.19"

*/

public Access (String Data) {

        StringTokenizer Splitter = new StringTokenizer(Data," \t");
        String skip;

 //       Host = Splitter.nextToken();
  //      skip = Splitter.nextToken();
        skip = Splitter.nextToken("[");
        Time = Splitter.nextToken(" \t");
        skip = Splitter.nextToken("\"");

        Request = Splitter.nextToken();
        skip = Splitter.nextToken(" \t");

        status = Integer.parseInt(Splitter.nextToken(" \t"));

        try {
                size = Integer.parseInt(Splitter.nextToken(" \t"));
        } catch (Exception e) {
                size = 0;
        }

        skip = Splitter.nextToken("\"");
        Referer = Splitter.nextToken();
        skip = Splitter.nextToken();
        UserAgent = Splitter.nextToken();

        // Use another String Tokenizer on the HTTP Request

        Splitter = new StringTokenizer(Request);
        Method = Splitter.nextToken();
        URL = Splitter.nextToken();

        }

// Test code

public static void main (String [] args) throws IOException {

        BufferedReader Source = new BufferedReader(
                        new FileReader("files.txt"));

        String Line = Source.readLine();

        Access Earliest = new Access(Line);

        System.out.println("Access to web page "+Earliest.URL);
        System.out.println("Information returned (in bytes) "+Earliest.size);
        System.out.println("Called by "+Earliest.UserAgent);

        }

}
