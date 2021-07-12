import com.automationanywhere.botcommand.samples.commands.conditionals.text_in_list;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class text_in_list_test {
@Test
    void teste(){
    text_in_list act = new text_in_list();

    List<String> lista = new ArrayList<String>();
    lista.add("test");

    Boolean a = act.validate("test","equals",lista);
    System.out.println("================== Value:" + a);
}

}
