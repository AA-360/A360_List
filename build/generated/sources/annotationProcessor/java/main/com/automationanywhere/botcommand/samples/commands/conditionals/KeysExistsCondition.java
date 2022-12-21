package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.Condition;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class KeysExistsCondition implements Condition {
  private static final Logger logger = LogManager.getLogger(KeysExistsCondition.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  public boolean test(Map<String, Value> parameters) {
    return test(null, parameters, null);
  }

  public boolean test(GlobalSessionContext globalSessionContext, Map<String, Value> parameters,
      Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    KeysExists command = new KeysExists();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("dict") && parameters.get("dict") != null && parameters.get("dict").get() != null) {
      convertedParameters.put("dict", parameters.get("dict").get());
      if(convertedParameters.get("dict") !=null && !(convertedParameters.get("dict") instanceof Map)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","dict", "Map", parameters.get("dict").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("dict") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","dict"));
    }

    if(parameters.containsKey("keys") && parameters.get("keys") != null && parameters.get("keys").get() != null) {
      convertedParameters.put("keys", parameters.get("keys").get());
      if(convertedParameters.get("keys") !=null && !(convertedParameters.get("keys") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","keys", "String", parameters.get("keys").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("keys") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","keys"));
    }

    if(parameters.containsKey("select") && parameters.get("select") != null && parameters.get("select").get() != null) {
      convertedParameters.put("select", parameters.get("select").get());
      if(convertedParameters.get("select") !=null && !(convertedParameters.get("select") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","select", "String", parameters.get("select").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("select") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","select"));
    }
    if(convertedParameters.get("select") != null) {
      switch((String)convertedParameters.get("select")) {
        case "exists" : {

        } break;
        case "not exists" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","select"));
      }
    }

    try {
      boolean result = command.validate((Map<String, Value>)convertedParameters.get("dict"),(String)convertedParameters.get("keys"),(String)convertedParameters.get("select"));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","validate"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(), e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
