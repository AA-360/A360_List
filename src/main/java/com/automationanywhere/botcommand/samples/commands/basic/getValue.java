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

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "getValue",
        description = "get value from dict",
        node_label = "get value from {{dict}}",
        icon = "pkg.svg",
        name = "getValue",
        return_description = "",
        return_type = DataType.ANY,
        return_sub_type = DataType.ANY,
        return_required = true
)


public class getValue {

    @Execute
    public Value action(
            @Idx(index = "1", type = AttributeType.DICTIONARY)
            @VariableType(DICTIONARY)
            @Pkg(label = "Dict:")
            @NotEmpty
                    Map<String, Value> dict,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Keys:", description = "Keys Ex: key1.key2")
            @NotEmpty
                    String keys
            ) {


        if(DictUteis.KeyExists(keys,dict)){
            return DictUteis.GetValue(keys,dict);
        }else{
            throw new BotCommandException("Key " + keys + " not found !");
        }
    }


}
