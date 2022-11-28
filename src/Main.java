import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            parser.tryWriteResult(parser.tryParseJSON());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
