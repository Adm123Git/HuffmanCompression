package ru.adm123.huffmanCompression.mapping.impl;

import com.sun.istack.internal.NotNull;
import ru.adm123.huffmanCompression.mapping.CodeTreeCreator;
import ru.adm123.huffmanCompression.mapping.model.CodeTreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class CodeTreeCreator_Huffman implements CodeTreeCreator {

    /**
     * Реализация {@link CodeTreeCreator#getCodeTree(Map)}, использующая алгоритм Хаффмана
     */
    @Override
    public CodeTreeNode getCodeTree(@NotNull Map<Character, Integer> characterWeightMap) {
        final List<CodeTreeNode> sheetList = getSheetList(characterWeightMap);
        while (sheetList.size() > 1) {
            CodeTreeNode node1 = sheetList.get(sheetList.size() - 1);
            CodeTreeNode node2 = sheetList.get(sheetList.size() - 2);
            sheetList.remove(node1);
            sheetList.remove(node2);
            sheetList.add(new CodeTreeNode(node1, node2));
            Collections.sort(sheetList);
        }
        return sheetList.size() == 1
                ? sheetList.get(0)
                : null;
    }

    /**
     * Метод для создания списка листьев
     *
     * @param characterWeightMap {@link Map} с ключами - сиволами и значениями - частотой их вхождения в строку
     * @return {@link List} элементов {@link CodeTreeNode}, содержащий все листья дерева кодов в упорядоченном виде
     */
    @NotNull
    private List<CodeTreeNode> getSheetList(@NotNull Map<Character, Integer> characterWeightMap) {
        return characterWeightMap.entrySet().stream()
                .map(entry -> new CodeTreeNode(entry.getValue(), entry.getKey()))
                .sorted()
                .collect(Collectors.toList());
    }

}
