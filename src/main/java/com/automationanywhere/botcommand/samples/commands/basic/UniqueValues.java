/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "UniqueValues",
        description = "Esta action retira os valores duplicados",
        node_label = "UniqueValues: Remove duplicatas da lista {{lista}}",
        icon = "pkg.svg",
        name = "UniqueValues",
        return_description = "",
        return_type = DataType.LIST,
        return_sub_type = DataType.STRING,
        return_required = true
)


public class UniqueValues {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = LIST)
            @Pkg(label = "List String", description = "Lista de string a ser unificada!")
            @NotEmpty
            List<Value> lista,
            @Idx(index = "2", type = BOOLEAN)
            @Pkg(label = "remove empty values",default_value_type = DataType.BOOLEAN,default_value = "false")
            @NotEmpty
            Boolean removeEmpty
    ) {
        ListValue<String> OutPut = new ListValue<String>();
        List<Value> CTRL = new ArrayList<Value>();

        for(Value item: lista){
            String val = item.toString().trim();
            if((!(val.isBlank() || val.isEmpty()) && removeEmpty) || !removeEmpty){
                if(this.ocurrenceInList(val,CTRL) == 0){
                    CTRL.add(item);
                }
            }
        }
        OutPut.set(CTRL);
        return OutPut;
    }

    private Integer ocurrenceInList(String value,List<Value> list){
        Integer occurrence = 0;
        for(Value item: list) {
            occurrence += item.toString().equals(value)?1:0;
        }

        return occurrence;
    }

}
