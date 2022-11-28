import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Илюха_1999_орелмолод\\Desktop\\code\\outletsWithoutContactPC\\requestedOutlets")){
            int i=-1;
            StringBuilder stringBuilder = new StringBuilder();
            while((i=fileInputStream.read())!=-1){
                stringBuilder.append((char)i);
            }
            String[] arrayList = stringBuilder.toString().split(" ");
            for (String id: arrayList){
                parser.tryParseJSON(id);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
