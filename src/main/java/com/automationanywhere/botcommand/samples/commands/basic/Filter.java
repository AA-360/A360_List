package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.Assets;
import com.automationanywhere.botcommand.samples.commands.utils.Debugger;
import com.automationanywhere.botcommand.samples.commands.utils.Uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.CodeType;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;

@BotCommand
@CommandPkg(
        label = "Filter",
        description = "Filtro de registros na Lista com formula em JS",
        icon = "pkg.svg",
        name = "Filter",
        return_description = "",
        return_type = DataType.LIST,
        return_required = true,
        return_sub_type = DataType.STRING
)


public class Filter {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = LIST)
            @Pkg(label = "Lista:")
            @NotEmpty
                    List<Value> lista,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Parametro",description = "Parametro extra de entrada")
                    String parametro,
            @Idx(index = "3", type = AttributeType.CODE)
            @Pkg(label = "javaScript Code",description = "sua função deve se chamar 'filter' obrigatoriamente e retornar true|false \n VALUE -> cada valor\nINPUT -> Parametro")
            @CodeType(value = "text/javascript")
            @NotEmpty
                    String code
    ) {

        //========================================================== LITURA DO JS
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        String library = new Assets().codeLibrary();

        try{
            //this.alert(Thread.currentThread().getContextClassLoader().getResource("./config/library.js").toString());
            //InputStream inputStream =  Thread.currentThread().getContextClassLoader().getResource("./config/library.js").openStream();
            //String library = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            engine.eval(library);
            engine.eval(code);

            //engine.eval(Files.newBufferedReader(Paths.get("C:/Temp/js.js"), StandardCharsets.UTF_8));
        }
        catch (Exception e){
            throw new BotCommandException("Error when trying to load Js code!" + e.getMessage());
        }

        //============================================================ RUN FILTER
        Debugger debugger = new Debugger();
        Invocable inv = (Invocable) engine;
        List<Value> RowListValues = new ArrayList<>();
        ListValue<String> OUTPUT = new ListValue<String>();
        int counter = 0;

        for(Value rw: lista){
            counter ++;
            Object params[] = {};

            try{
                //ADCIONA OS PARAMETROS DA LINHA
                debugger.setInvVar(inv,"VALUE",rw.toString());
                debugger.setInvVar(inv,"INPUT",parametro);

                //EXECUTA A FORMULA
                Boolean filter = Boolean.parseBoolean(inv.invokeFunction("filter").toString());
                debugger.debug(inv,filter.toString());

                if(filter){
                    RowListValues.add(rw);
                }
            }
            catch (Exception e){
                throw new BotCommandException("Error calling method 'filter': Item " + counter + ":" + e.getMessage());
            }
        }
        OUTPUT.set(RowListValues);

        return OUTPUT;

    }
}
