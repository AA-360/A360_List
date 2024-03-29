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
        label = "JoinList",
        description = "Esta action Unifica os itens de uma lista separados por um delimitador",
        node_label = "Join {{lista}} by '{{delimiter}}' delimiter",
        icon = "pkg.svg",
        name = "JoinList",
        return_description = "",
        return_type = STRING,
        return_required = true,
        return_sub_type = DataType.STRING
)


public class JoinList {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Delimitador")
            @NotEmpty
            String delimiter,
            @Idx(index = "2", type = LIST)
            @Pkg(label = "List String", description = "Lista de string a ser unificada!")
            @NotEmpty
            List<Value> lista
    ) {

        if(lista.size()==0){
            return new StringValue("");
        }

        String joined = "";
        for(Value item: lista){
            joined +=item.toString() + delimiter;
        }

        return new StringValue(joined.substring(0, joined.length() - 1));
    }

}
