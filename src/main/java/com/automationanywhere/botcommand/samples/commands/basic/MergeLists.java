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

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.LIST;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "MergeLists",
        description = "Esta action unifica os itens de 2 listas",
        node_label = "Merge lists {{lista1}} and {{lista2}} into one list",
        icon = "pkg.svg",
        name = "MergeLists",
        return_description = "",
        return_type = DataType.LIST,
        return_sub_type = STRING,
        return_required = true
)


public class MergeLists {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = LIST)
            @Pkg(label = "List String", description = "Lista de string a ser unificada!")
            @NotEmpty
            List<Value> lista1,
            @Idx(index = "2", type = LIST)
            @Pkg(label = "List String", description = "Lista de string a ser unificada!")
            @NotEmpty
            List<Value> lista2
    ) {
        List<Value> merged = new ArrayList<>();
        ListValue<String> OutPut = new ListValue<String>();

        for(Value item: lista1){
            merged.add(item);
        }
        for(Value item: lista2){
            merged.add(item);
        }
        OutPut.set(merged);

        return OutPut;
    }

}
