import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.basic.JoinList;
import com.automationanywhere.botcommand.samples.commands.basic.UniqueValues;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StringJoinListTest {
    //@Test
    public void Uniqus() {

    }


    @Test
    public void Unique(){
        UniqueValues a = new UniqueValues();
        JoinList b = new JoinList();



        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("DEF"));
        vals.add(new StringValue("DEF"));

        //returnvalue.set(vals);

        ListValue<String> ret = a.action(vals,false);
        System.out.println("==================" + ret.get().size());
        StringValue ret2 = b.action(";",ret.get());

        System.out.println("==================" + ret2.toString());
    }

    @Test
    public void teste(){
        JoinList a = new JoinList();



        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("ASD"));
        vals.add(new StringValue("ASD"));
        vals.add(new StringValue("ASD"));
        vals.add(new StringValue("ASD"));

        returnvalue.set(vals);

        StringValue ret = a.action(";",vals);
        System.out.println("==================" + ret.toString());
    }
}