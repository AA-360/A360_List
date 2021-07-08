package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
import static com.automationanywhere.commandsdk.model.AttributeType.BOOLEAN;
import static com.automationanywhere.commandsdk.model.AttributeType.LIST;
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
@CommandPkg(label = "TextInList", name = "TextInList",
        description = "Verifica se um texto existe ou nao dentro de uma lista",
        node_label = "TextInList: Check for {{value}} in {{lista}} - Condition ->{{select}}"
)
public class text_in_list {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = AttributeType.TEXT)
            @VariableType(STRING)
            @Pkg(label = "Valor", default_value_type = STRING)
            @NotEmpty
            String value,
            @Idx(index = "2", type = AttributeType.SELECT, options = {
                    @Idx.Option(index ="2.1", pkg = @Pkg(label = "Igual (=)", value = "equals")),
                    @Idx.Option(index ="2.2", pkg = @Pkg(label = "Diferente (≠)", value = "different")),
                    @Idx.Option(index ="2.3", pkg = @Pkg(label = "Inclui", value = "in")),
                    @Idx.Option(index ="2.4", pkg = @Pkg(label = "Não Inclui", value = "not in"))
            })
            @Pkg(label = "Operador",default_value_type = STRING,default_value = "equals",description="Procura um  valor dentro da lista com a condição correspondente")
            @NotEmpty
            String select,
            @Idx(index = "3", type = LIST)
            @Pkg(label = "List String", description = "Lista de string a ser consultada")
            @NotEmpty
            ArrayList<String> lista
    ) {

        for(String val : lista) {
            if(select.equals("equals")){
                if(val.equals(value))
                    return true;
            }else if(select.equals("different")){
                if(!val.equals(value))
                    return true;
            }else if(select.equals("in")){
                if (val.contains(value))
                    return true;
            }else {
                if (!val.contains(value))
                    return true;
            }
        }

        return false;

    }

}


