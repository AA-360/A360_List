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

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "StringSplit",
        description = "Esta action quebra um texto em uma lista de texto por um delimitador",
        node_label = "Split {{text}} por {{delimitador}} e atribui a {{returnTo}}",
        icon = "pkg.svg",
        name = "StringSplit",
        return_description = "",
        return_type = DataType.LIST,
        return_sub_type = DataType.STRING,
        return_required = true
)


public class StringSplit {

    @Execute
    public ListValue<String> action(
            @Idx(index = "1", type = TEXTAREA)
            @Pkg(label = "Texto a ser quebrado", description = "a;b;c;d\na,b,c,d")
            @NotEmpty
                    String text,

            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Delimitador")
            @NotEmpty
                String delimitador
    ) {
        ListValue<String> OutPut = new ListValue<String>();
        List<Value> CTRL = new ArrayList<Value>();

        for(String t:text.split(delimitador)){
            CTRL.add(new StringValue(t));
        }

        OutPut.set(CTRL);
        return OutPut;
    }

}
