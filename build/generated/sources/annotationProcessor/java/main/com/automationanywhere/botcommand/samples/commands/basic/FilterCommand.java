package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FilterCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(FilterCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    Filter command = new Filter();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("lista") && parameters.get("lista") != null && parameters.get("lista").get() != null) {
      convertedParameters.put("lista", parameters.get("lista").get());
      if(convertedParameters.get("lista") !=null && !(convertedParameters.get("lista") instanceof List)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","lista", "List", parameters.get("lista").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("lista") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","lista"));
    }

    if(parameters.containsKey("parametro") && parameters.get("parametro") != null && parameters.get("parametro").get() != null) {
      convertedParameters.put("parametro", parameters.get("parametro").get());
      if(convertedParameters.get("parametro") !=null && !(convertedParameters.get("parametro") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","parametro", "String", parameters.get("parametro").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("code") && parameters.get("code") != null && parameters.get("code").get() != null) {
      convertedParameters.put("code", parameters.get("code").get());
      if(convertedParameters.get("code") !=null && !(convertedParameters.get("code") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","code", "String", parameters.get("code").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("code") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","code"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((List<Value>)convertedParameters.get("lista"),(String)convertedParameters.get("parametro"),(String)convertedParameters.get("code")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
