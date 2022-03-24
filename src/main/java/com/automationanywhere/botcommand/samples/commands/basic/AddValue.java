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
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "AddValue",
        description = "Esta action adciona um valor a uma lista",
        node_label = "Adciona valor a lista {{lista}}",
        icon = "pkg.svg",
        name = "AddValue",
        return_description = "",
        return_type = DataType.LIST,
        return_sub_type = DataType.STRING,
        return_required = true
)


public class AddValue {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = LIST)
            @Pkg(label = "List String", description = "Lista de string a ser unificada!")
            @NotEmpty
            List<Value> lista,
            @Idx(index = "2", type = UNKNOWN)
            @Pkg(label = "Novo Valor:")
            @NotEmpty
             Value
    ) {
        String value2 = String.copyValueOf(Value.toCharArray());
        ListValue<String> OutPut = new ListValue<String>();
        List<Value> CTRL = new ArrayList<Value>();

        for(Value item: lista){
            CTRL.add(item);
        }
        CTRL.add(new StringValue(value2));
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
