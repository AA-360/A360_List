package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.exception.BotCommandException;

import javax.script.Invocable;
import javax.script.ScriptException;
import javax.swing.*;

public class Debugger {

    public Debugger(){}

    public Integer alert(String text){
        Object[] options = { "Next", "Stop" };
        return JOptionPane.showOptionDialog(null, text, "Debug", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

        //JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.showConfirmDialog());
    }

    public String getInvVar(Invocable inv,String var)throws Exception{
        try {
            Object a = inv.invokeFunction(var + "_FNC");
            return a == null?"null":a.toString();
        }catch(ScriptException e){
            throw new Exception(e.getMessage());
        }catch (NoSuchMethodException e){
            throw new Exception(e.getMessage());
        }
    }
    public void setInvVar(Invocable inv,String var,Object value)throws Exception {

        try {
            Object param[] = {value};
            inv.invokeFunction(var + "_FNC", param);
        }catch(ScriptException e){
            throw new Exception(e.getMessage());
        }catch (NoSuchMethodException e){
            throw new Exception(e.getMessage());
        }
    }

    public void debug(Invocable inv, String newValue){
        try {
            Boolean ALERT_EACH_RESULT = Boolean.parseBoolean(this.getInvVar(inv,"ALERT_EACH_RESULT"));
            Boolean stop = Boolean.parseBoolean(this.getInvVar(inv,"STOP"));
            String ALLERT_MESSAGE = this.getInvVar(inv,"ALLERT_MESSAGE");;

            if (ALERT_EACH_RESULT && !stop) {
                newValue = "Value:" + newValue;
                newValue = ALLERT_MESSAGE == "null"?newValue:newValue + "\nMessage:" + ALLERT_MESSAGE;
                if(this.alert(newValue) == 1){
                    this.setInvVar(inv,"STOP",true);
                }
            }
            this.setInvVar(inv,"ALLERT_MESSAGE","null");

        } catch (Exception e){
            throw new BotCommandException("Error debuging:" + e.getMessage());
        }


    }

}
