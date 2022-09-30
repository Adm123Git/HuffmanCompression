package ru.adm123.huffmanCompression.compressing;

import com.sun.istack.internal.NotNull;

/**
 * Упаковщик и распаковщик данных
 */
public interface DataCompressor<T> {

    /**
     * Метод, обеспечивающий сжатие данных
     *
     * @param source исходные данные
     * @return строка, представляющая собой сжаты данные
     */
    String compress(@NotNull T source);

    /**
     * Метод, обеспечивающий распаковку данных
     *
     * @param string строка, представляющая собой сжаты данные
     * @return распакованные данные
     */
    T uncompress(@NotNull String string);

}
