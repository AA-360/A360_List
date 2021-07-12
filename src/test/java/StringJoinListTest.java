import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.basic.StringJoinList;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StringJoinListTest {
    @Test
    public void teste(){
        StringJoinList a = new StringJoinList();



        ListValue<String> returnvalue = new ListValue<String>();
        List<String> vals = new ArrayList<String>();

        vals.add("ASD");
        vals.add("ASD");
        vals.add("ASD");
        vals.add("ASD");

        //returnvalue.set(vals);

        StringValue ret = a.action(";",vals);
        System.out.println("==================" + ret.toString());
    }
}