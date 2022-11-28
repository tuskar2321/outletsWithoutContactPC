import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class Parser {

    void tryParseJSON(String global_id){
        JSONParser jsonParser = new JSONParser();

        try (Reader reader = new FileReader("C:\\Users\\Илюха_1999_орелмолод\\Desktop\\code\\outletsWithoutContactPC\\outlets")){
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) jsonObject.get("result");
            StringBuilder stringBuilder = new StringBuilder();
            for (Object o : jsonArray) {
                String jsonObj = o.toString();
                JSONObject outlet = (JSONObject) jsonParser.parse(jsonObj);
                if (outlet.get("globalId").equals(global_id)) {
                    String extra = outlet.get("extra").toString();
                    JSONObject outletExtra = (JSONObject) jsonParser.parse(extra);
                    if (outletExtra.get("contact_point_code")!=null){
                        continue;
                    } else {
                        if (Objects.equals(outlet.get("status").toString(), "accepted") && outlet.get("posId")!=""){
                            stringBuilder.append(outlet.get("id").toString()).append(" ").append(outlet.get("posId").toString()).append("\n");
                        }
                    }
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Илюха_1999_орелмолод\\Desktop\\code\\outletsWithoutContactPC\\result.txt");
            byte[] buffer = stringBuilder.toString().getBytes();
            System.out.println(stringBuilder.toString());
            fileOutputStream.write(buffer);
            fileOutputStream.close();
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
