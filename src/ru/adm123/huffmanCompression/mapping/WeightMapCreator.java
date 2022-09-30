package ru.adm123.huffmanCompression.mapping;

import com.sun.istack.internal.NotNull;

import java.util.Map;

/**
 * Создатель мапы весов символов, на основании которой строится дерево кодов (см. {@link CodeTreeCreator})
 */
public interface WeightMapCreator {

    /**
     * Метод для формирования мапы весов символов
     *
     * @param string строка сиволов
     * @return {@link Map} с ключом - символом и значением - его весом.
     * Именно эту мапу нужно сохранять вместе с кодированной строкой, т.к. на ее основании можно
     * построить дерево кодов, которое потом использовать для декодирования.
     */
    @NotNull
    Map<Character, Integer> getCharacterWeightMap(@NotNull String string);

}
