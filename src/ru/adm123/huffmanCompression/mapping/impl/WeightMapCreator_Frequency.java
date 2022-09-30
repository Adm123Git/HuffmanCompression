package ru.adm123.huffmanCompression.mapping.impl;

import com.sun.istack.internal.NotNull;
import ru.adm123.huffmanCompression.mapping.WeightMapCreator;

import java.util.HashMap;
import java.util.Map;

public class WeightMapCreator_Frequency implements WeightMapCreator {

    /**
     * Реализация {@link WeightMapCreator#getCharacterWeightMap(String)}, формирующая веса символов как количество
     * вхождений этих символов в заданную строку
     */
    @NotNull
    public Map<Character, Integer> getCharacterWeightMap(@NotNull String string) {
        final Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            map.compute(string.charAt(i), (key, val) -> val == null ? 1 : val + 1);
        }
        return map;
    }

}
