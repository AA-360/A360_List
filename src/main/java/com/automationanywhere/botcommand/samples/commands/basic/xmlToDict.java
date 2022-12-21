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

import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.samples.commands.utils.File;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.DictUteis;
import com.automationanywhere.botcommand.samples.commands.utils.JsonUtils;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.FileExtension;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.annotations.rules.VariableType;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "xmlToDict",
        description = "xml file to dict",
        node_label = "xml {{file}} to {{returnTo}}",
        icon = "pkg.svg",
        name = "xmlToDict",
        return_description = "",
        return_type = DataType.DICTIONARY,
        return_required = true
)


public class xmlToDict {

    @Execute
    public DictionaryValue action(
            @Idx(index = "1", type = FILE)
            @Pkg(label = "xml:")
            @FileExtension("xml")
                    String file,
            @Idx(index = "2", type = CHECKBOX)
            @Pkg(label = "ForceString:", description = "force number values as string")
            @NotEmpty
                    Boolean forceString
            ) {

        DictionaryValue ret = new DictionaryValue();
        File fl = new File();
        List<String> buffer = fl.readFile(file);

        String joined = "";
        for(String line: buffer){
            joined +=line + "\n";
        }

        joined = joined.substring(0, joined.length() - 1);
        JSONObject jsonObject;

        jsonObject = JsonUtils.XmlToObj(joined,forceString);

        Map<String, Value> v =  JsonUtils.JsonObjToMapValue(jsonObject);

        ret.set(v);
        return ret;
    }


}
