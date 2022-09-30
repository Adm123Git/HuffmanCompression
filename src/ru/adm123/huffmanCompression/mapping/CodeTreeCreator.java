package ru.adm123.huffmanCompression.mapping;

import com.sun.istack.internal.NotNull;
import ru.adm123.huffmanCompression.mapping.model.CodeTreeNode;

import java.util.Map;

/**
 * Формировщик дерева кодов, т.е. дерева, на основании которого вычисляются коды символов (по положению символа в дереве)
 */
public interface CodeTreeCreator {

    /**
     * Метод формирует бинарное дерево на основании мапы весов символов
     *
     * @param characterWeightMap мапа весов сиволов
     * @return верхний узел сформированного дерева
     */
    CodeTreeNode getCodeTree(@NotNull Map<Character, Integer> characterWeightMap);

}
