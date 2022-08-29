import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.conditionals.text_in_list;
import com.automationanywhere.botcommand.samples.commands.conditionals.text_not_in_list;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class text_in_list_test {
@Test
    void teste(){
    text_in_list act = new text_in_list();
    text_not_in_list act2 = new text_not_in_list();

    List<Value> lista = new ArrayList<Value>();
    lista.add(new StringValue("test"));

    Boolean a = act2.validate("test","equals",lista);
    System.out.println("================== Value:" + a);
}

}
