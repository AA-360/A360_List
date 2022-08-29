package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DictUteis {

    public static Boolean KeyExists(String key, DictionaryValue json){
        List<String> keys = getKeys(json);
        Value stored = null;
        boolean ret = true;

        for(String k: key.split("\\.")){
            if(stored == null){
                if(k.contains("[")){
                    stored = getFromList(k,json);
                    if(stored == null) return false;
                }else {
                    if(!getKeys(json).contains(k)) return false;
                    stored = json.get(k);
                }

            }
            else{
                if(k.contains("[")){
                    stored = getFromList(k,stored);
                    if(stored == null) return false;
                }else{
                    if(!getKeys((DictionaryValue)stored).contains(k)) return false;
                    stored = stored.get(k);
                }

            }
        }

        return ret;
    }


    public static Value GetValue(String key, DictionaryValue json){
          Value stored = null;

        for(String k: key.split("\\.")){
            if(stored == null){
                if(k.contains("[")){
                    stored = getFromList(k,json);
                }else {
                    stored = json.get(k);
                }

            }
            else{
                if(k.contains("[")){
                    stored = getFromList(k,stored);
                }else{
                    stored = stored.get(k);
                }

            }
        }

        return stored;
    }

    public static Value getFromList(String key,Value lst){
        Integer idx = Integer.valueOf(key.split("\\[")[1].replace("]",""));
        String k = key.split("\\[")[0];

        if(!getKeys((DictionaryValue)lst).contains(k)) return null;
        ListValue<Value> listV = (ListValue<Value>)lst.get(k);

        if(listV.get().size()-1 < idx ) return null;

        return listV.get(idx);

    }


    public static List<String> getKeys(DictionaryValue json){
        List<String> l = new ArrayList<>();

        Iterator<String> keys = json.get().keySet().iterator();

        while(keys.hasNext()) {
            String key = keys.next();
            l.add(key);
        }
        return l;

    }


    public static List<String> getKeys(Map<String, Value> json){
        DictionaryValue ts = new DictionaryValue();
        ts.set(json);
        return getKeys(ts);
    }


    public static boolean KeyExists(String key,Map<String, Value> json){
        DictionaryValue ts = new DictionaryValue();
        ts.set(json);
        return KeyExists(key,ts);
    }

    public static Value GetValue(String key,Map<String, Value> json){
        DictionaryValue ts = new DictionaryValue();
        ts.set(json);
        return GetValue(key,ts);
    }
}
