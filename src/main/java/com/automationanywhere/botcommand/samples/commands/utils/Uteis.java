package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.exception.BotCommandException;

import java.util.Arrays;
import java.util.List;

public class Uteis {

    public static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

    public static int countOccurrences(List<String> arr, String x)
    {
        int res = 0;
        for (String oc: arr)
            if (x == oc)
                res++;
        return res;
    }

    public static double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                throw new BotCommandException("Value '" + strNumber + "' is not a valid Double please make sure it is in a english format");
            }
        }
        else return 0;
    }
}
