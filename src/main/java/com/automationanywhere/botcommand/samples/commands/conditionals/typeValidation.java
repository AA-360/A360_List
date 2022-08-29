package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.utils.DictUteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.*;

/**
 *
 * This example shows how to create an Condition.
 * <p>
 * Here we are checking of the provided boolean value is false.
 *
 *
 * @author Raj Singh Sisodia
 *
 */
@BotCommand(commandType = Condition)
@CommandPkg(label = "typeValidation", name = "typeValidation",
        description = "Validates an any var type to according type",
        node_label = "Check for {{v}} {{match}} {{type}}"
)
public class typeValidation {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1",type = AttributeType.VARIABLE)
            @VariableType(ANY)
            @Pkg(label = "Var:")
            @NotEmpty
                    Object v,
            @Idx(index = "2", type = AttributeType.SELECT, options = {
                    @Idx.Option(index ="2.1", pkg = @Pkg(label = "String", value = "string")),
                    @Idx.Option(index ="2.2", pkg = @Pkg(label = "Number", value = "number")),
                    @Idx.Option(index ="2.3", pkg = @Pkg(label = "Dictionary", value = "dictionary")),
                    @Idx.Option(index ="2.4", pkg = @Pkg(label = "List", value = "list"))
            })
            @Pkg(label = "Type:",default_value_type = STRING,default_value = "string")
            @NotEmpty
                    String type,
            @Idx(index = "3", type = AttributeType.SELECT, options = {
                    @Idx.Option(index ="3.1", pkg = @Pkg(label = "Match", value = "match")),
                    @Idx.Option(index ="3.2", pkg = @Pkg(label = "Not Exists", value = "not match")),
            })
            @Pkg(label = "Operator:",default_value_type = STRING,default_value = "match")
            @NotEmpty
                    String match
    ) {

        //alert(v.getClass().toString());

        if(type.equals("string")){
            return match.equals("match")?v instanceof String:!(v instanceof String);
        }
        if(type.equals("number")){
            return match.equals("match")?v instanceof Double:!(v instanceof Double);
        }
        if(type.equals("dictionary")){
            return match.equals("match")?v instanceof HashMap :!(v instanceof HashMap);
        }
        if(type.equals("list")){
            return match.equals("match")?v instanceof ArrayList :!(v instanceof ArrayList);
        }

        return false;

    }


    private void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }
}


