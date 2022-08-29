package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;

import java.util.List;

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
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
@CommandPkg(label = "TextNotInList", name = "TextNotInList",
        description = "Verifica se um texto não existe dentro de uma lista",
        node_label = "Check for {{value}} not in {{lista}} - Condition ->{{select}}"
)
public class text_not_in_list {

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
            List<Value> lista
    ) {

        for(Value val : lista) {
            if(select.equals("equals")){
                if(val.toString().equals(value))
                    return false;
            }else if(select.equals("different")){
                if(!val.toString().equals(value))
                    return false;
            }else if(select.equals("in")){
                if (val.toString().contains(value))
                    return false;
            }else {
                if (!val.toString().contains(value))
                    return false;
            }
        }

        return true;

    }

}


