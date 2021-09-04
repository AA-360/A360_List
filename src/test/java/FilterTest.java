import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.basic.Filter;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilterTest {

    @Test
    public void test(){
        Filter a = new Filter();

        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("DEF"));
        vals.add(new StringValue("DEF"));


        String code = this.code();

        ListValue<String> ret = a.action(vals,"11",code);
        System.out.println(ret.get());



    }
    private String code(){
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get("C:/Temp/js.js"), StandardCharsets.UTF_8);
            StringWriter writer = new StringWriter();
            String code = "";
            String line;
            while ((line = reader.readLine()) != null) {
                code += line + "\n";
            }

            return code;
        }
        catch (Exception e){
            throw new BotCommandException("Error when trying to load Js code!");
        }
    }


}
