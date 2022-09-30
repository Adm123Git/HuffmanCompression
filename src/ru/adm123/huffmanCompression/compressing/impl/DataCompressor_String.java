package ru.adm123.huffmanCompression.compressing.impl;

import com.sun.istack.internal.NotNull;
import ru.adm123.huffmanCompression.mapping.CodeTreeCreator;
import ru.adm123.huffmanCompression.mapping.model.CodeTreeNode;
import ru.adm123.huffmanCompression.compressing.DataCompressor;

import java.util.Map;

public class DataCompressor_String implements DataCompressor<String> {

    /**
     * Дерево кодов
     */
    @NotNull
    private final CodeTreeNode codeTree;

    public DataCompressor_String(@NotNull CodeTreeCreator codeTreeCreator,
                                 @NotNull Map<Character, Integer> characterWeightMap) {
        this.codeTree = codeTreeCreator.getCodeTree(characterWeightMap);
    }

    /**
     * Реализация {@link DataCompressor#compress(Object)} для строковых данных
     */
    @NotNull
    public String compress(@NotNull String symbolString) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < symbolString.length(); i++) {
            stringBuilder.append(codeTree.getCharacterCode(symbolString.charAt(i)));
        }
        return stringBuilder.toString();
    }

    /**
     * Реализация {@link DataCompressor#uncompress(String)} для строковых данных
     */
    @NotNull
    public String uncompress(@NotNull String bitString) {
        final StringBuilder stringBuilder = new StringBuilder();
        while (bitString.length() > 0) {
            Character character = null;
            int endIndex = 0;
            while (character == null && endIndex < bitString.length()) {
                String path = bitString.substring(0, ++endIndex);
                character = codeTree.getCharacter(path);
            }
            if (character != null) {
                stringBuilder.append(character);
            }
            bitString = bitString.substring(endIndex);
        }
        return stringBuilder.toString();
    }

}
