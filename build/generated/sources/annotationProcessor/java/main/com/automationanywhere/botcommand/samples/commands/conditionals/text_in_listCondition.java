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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class text_in_listCondition implements Condition {
  private static final Logger logger = LogManager.getLogger(text_in_listCondition.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  public boolean test(Map<String, Value> parameters) {
    return test(null, parameters, null);
  }

  public boolean test(GlobalSessionContext globalSessionContext, Map<String, Value> parameters,
      Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    text_in_list command = new text_in_list();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("value") && parameters.get("value") != null && parameters.get("value").get() != null) {
      convertedParameters.put("value", parameters.get("value").get());
      if(convertedParameters.get("value") !=null && !(convertedParameters.get("value") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","value", "String", parameters.get("value").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("value") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","value"));
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
        case "equals" : {

        } break;
        case "different" : {

        } break;
        case "in" : {

        } break;
        case "not in" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","select"));
      }
    }

    if(parameters.containsKey("lista") && parameters.get("lista") != null && parameters.get("lista").get() != null) {
      convertedParameters.put("lista", parameters.get("lista").get());
      if(convertedParameters.get("lista") !=null && !(convertedParameters.get("lista") instanceof List)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","lista", "List", parameters.get("lista").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("lista") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","lista"));
    }

    try {
      boolean result = command.validate((String)convertedParameters.get("value"),(String)convertedParameters.get("select"),(List<String>)convertedParameters.get("lista"));
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
