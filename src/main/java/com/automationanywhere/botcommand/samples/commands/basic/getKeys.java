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
import com.automationanywhere.botcommand.samples.commands.utils.DictUteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;
//import MaskFormatter;

//import java.Math;
//import Math;

//@BotCommand
@CommandPkg(
        label = "getKeys",
        description = "get keys from dict",
        node_label = "get keys from {{dict}}",
        icon = "pkg.svg",
        name = "getKeys",
        return_description = "",
        return_type = DataType.LIST,
        return_sub_type = DataType.STRING,
        return_required = true
)


public class getKeys {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = AttributeType.DICTIONARY)
            @VariableType(DICTIONARY)
            @Pkg(label = "Dict:")
            @NotEmpty
<<<<<<< HEAD:src/main/java/com/automationanywhere/botcommand/samples/commands/basic/AddValue.java
            List<Value> lista,
            @Idx(index = "2", type = VARIABLE)
            @Pkg(label = "Novo Valor:")
            @NotEmpty
             Value v
    ) {
//        String value2 = String.copyValueOf(Value.toCharArray());
        ListValue<String> OutPut = new ListValue<String>();
//        List<Value> CTRL = new ArrayList<Value>();
//
//        for(Value item: lista){
//            CTRL.add(item);
//        }
//        CTRL.add(new StringValue(value2));
//        OutPut.set(CTRL);
=======
                    Map<String, Value> dict
            ) {

        ListValue<String> OutPut = new ListValue<String>();
        List<Value> CTRL = new ArrayList<Value>();

        for(String item: DictUteis.getKeys(dict)){
            CTRL.add(new StringValue(item));
        }
        OutPut.set(CTRL);
>>>>>>> 69636e633f9b8b4e4ea8f298ac8ad76677bf5452:src/main/java/com/automationanywhere/botcommand/samples/commands/basic/getKeys.java
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
