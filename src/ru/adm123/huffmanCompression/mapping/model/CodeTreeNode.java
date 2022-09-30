package ru.adm123.huffmanCompression.mapping.model;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Stack;

/**
 * Класс, представляющий узел бинарного дерева кодов
 */
public class CodeTreeNode implements Comparable<CodeTreeNode> {

    /**
     * Вес символа (количество вхождений в строку)
     */
    private final int weight;
    /**
     * Символ
     */
    @Nullable
    private final Character character;
    /**
     * Родительский узел
     */
    @Nullable
    private CodeTreeNode parent;
    /**
     * Левый узел-потомок
     */
    @Nullable
    private final CodeTreeNode leftChild;
    /**
     * Правый узел-потомок
     */
    @Nullable
    private final CodeTreeNode rightChild;

    public CodeTreeNode(int weight,
                        @NotNull Character character) {
        this.weight = weight;
        this.character = character;
        this.leftChild = null;
        this.rightChild = null;
    }

    public CodeTreeNode(@Nullable CodeTreeNode leftChild,
                        @Nullable CodeTreeNode rightChild) {
        this.weight = (leftChild == null ? 0 : leftChild.weight) + (rightChild == null ? 0 : rightChild.weight);
        this.character = null;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        if (hasLeftChild()) {
            this.leftChild.parent = this;
        }
        if (hasRightChild()) {
            this.rightChild.parent = this;
        }
    }

    private boolean hasCharacter() {
        return character != null;
    }

    private boolean hasParent() {
        return parent != null;
    }

    private boolean hasLeftChild() {
        return leftChild != null;
    }

    private boolean hasRightChild() {
        return rightChild != null;
    }

    /**
     * Поиск кода символа по дереву еачиная от узла, на которм вызывается метод
     *
     * @param character символ, путь до которого ищется
     * @return {@link String} вида "110010101". Если символ в дереве не найден - возвращается {@code null}
     */
    @Nullable
    public String getCharacterCode(@NotNull Character character) {
        final Stack<CodeTreeNode> stack = new Stack<>();
        CodeTreeNode node = stack.push(this);
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.character != null && character.charValue() == node.character.charValue()) {
                break;
            }
            if (node.leftChild != null) {
                stack.push(node.leftChild);
            }
            if (node.rightChild != null) {
                stack.push(node.rightChild);
            }
        }
        final StringBuilder stringBuilder = new StringBuilder();
        while (node.hasParent()) {
            if (node.parent.leftChild == node) {
                stringBuilder.append(0);
            } else if (node.parent.rightChild == node) {
                stringBuilder.append(1);
            }
            node = node.parent;
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * Поиск в дереве символа по известному пути начиная с того узла, на котором метод вызван
     *
     * @param code код символа - {@link String} вида "110010101"
     * @return элемент {@link Character} или null, если по указанному пути он не найден
     */
    @Nullable
    public Character getCharacter(@NotNull String code) {
        CodeTreeNode node = this;
        for (int i = 0; i < code.length(); i++) {
            char stepCode = code.charAt(i);
            if (stepCode == '1') {
                node = node.rightChild;
            } else if (stepCode == '0') {
                node = node.leftChild;
            }
            if (node == null) {
                throw new IllegalArgumentException("wrong code");
            }
        }
        return node.character;
    }

    @Override
    public int compareTo(CodeTreeNode o) {
        if (o == null) {
            return 1;
        }
        return Integer.compare(o.weight, weight);
    }

    /**
     * Отдадим тут json, чтоб было удобно смотреть результат
     *
     * @return {@link String} в формате json
     */
    @Override
    public String toString() {
        return "{\"character\": " + (hasCharacter() ? "\"" + character + "\"" : null) + ", "
                + "\"weight\": " + weight + ", "
                + "\"parent\": " + (hasParent() ? parent.printShortInfo() : null) + ", "
                + "\"let\": " + (hasLeftChild() ? leftChild.toString() : null) + ", "
                + "\"right\": " + (hasRightChild() ? rightChild.toString() : null) + "}";
    }

    /**
     * Используется для вывода инфы о родительском узле воизбежание StackOverflowException
     *
     * @return {@link String} с короткой инфой об узле
     */
    @NotNull
    private String printShortInfo() {
        return "{\"character\": " + (hasCharacter() ? "\"" + character + "\"" : null) + ", "
                + "\"weight\": " + weight + ", "
                + "\"hasLeft\": " + hasLeftChild() + ", "
                + "\"hasRight\": " + hasRightChild()
                + "}";
    }

}
