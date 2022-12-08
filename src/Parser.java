import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Parser {

    StringBuilder tryParseJSON() throws FileNotFoundException {
        RequestInitiator requestInitiator = new RequestInitiator();
        JSONParser jsonParser = new JSONParser();
        StringBuilder stringBuilder = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cookie : ");
        try {
            String[] arrayList = tryReadInfo().toString().split(" ");
            JSONObject jsonObject = (JSONObject) jsonParser.parse(requestInitiator.tryGetRequest(sc.nextLine()));
            JSONArray jsonArray = (JSONArray) jsonObject.get("result");

            for (String id : arrayList) {
                for (Object o : jsonArray) {
                    String jsonObj = o.toString();
                    JSONObject outlet = (JSONObject) jsonParser.parse(jsonObj);
                    if (outlet.get("globalId").equals(id)) {
                        String extra = outlet.get("extra").toString();
                        JSONObject outletExtra = (JSONObject) jsonParser.parse(extra);
                        if (outletExtra.get("contact_point_code") != null) {
                        } else {
                            if (Objects.equals(outlet.get("status").toString(), "accepted") && outlet.get("posId") != "") {
                                stringBuilder.append(outlet.get("id").toString()).append(" ").append(outlet.get("posId").toString()).append("\n");
                            }
                        }
                    }
                }
            }
            stringBuilder.delete((stringBuilder.length()-1),stringBuilder.length());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    void tryWriteResult(StringBuilder outlets) {
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Илюха_1999_орелмолод\\Desktop\\code\\outletsWithoutContactPC\\result.txt")) {
            byte[] buffer = outlets.toString().getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    StringBuilder tryReadInfo() {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Илюха_1999_орелмолод\\Desktop\\code\\outletsWithoutContactPC\\requestedOutlets")) {
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                sb.append((char) i);
            }
            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
