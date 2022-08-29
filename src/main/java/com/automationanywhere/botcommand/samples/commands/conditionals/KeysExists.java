package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.DictUteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

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
@CommandPkg(label = "KeysExists", name = "KeysExists",
        description = "Verifica se um texto existe ou nao dentro de uma lista",
        node_label = "KeysExists: Check for {{keys}} in {{dict}} {{select}}"
)
public class KeysExists {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = AttributeType.DICTIONARY)
            @VariableType(DICTIONARY)
            @Pkg(label = "Dict:")
            @NotEmpty
                    Map<String, Value> dict,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Keys:", description = "Keys Ex: key1.key2")
            @NotEmpty
                    String keys,
            @Idx(index = "3", type = AttributeType.SELECT, options = {
                    @Idx.Option(index ="3.1", pkg = @Pkg(label = "Exists", value = "exists")),
                    @Idx.Option(index ="3.2", pkg = @Pkg(label = "Not Exists", value = "not exists")),
            })
            @Pkg(label = "Operator:",default_value_type = STRING,default_value = "exists")
            @NotEmpty
                    String select
    ) {

        boolean val = DictUteis.KeyExists(keys,dict);
        return  select.equals("exists")?val:!val;

    }

}


