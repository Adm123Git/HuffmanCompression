package ru.adm123.huffmanCompression;

import ru.adm123.huffmanCompression.mapping.*;
import ru.adm123.huffmanCompression.mapping.impl.CodeTreeCreator_Huffman;
import ru.adm123.huffmanCompression.mapping.impl.WeightMapCreator_Frequency;
import ru.adm123.huffmanCompression.compressing.impl.DataCompressor_String;

import java.util.Map;

public class Main {

    private final static String string = "Строка для испытания сжатия данных с использованием алгоритма Хаффмана";
    private final static CodeTreeCreator codeTreeCreator = new CodeTreeCreator_Huffman();
    private final static WeightMapCreator weightMapCreator = new WeightMapCreator_Frequency();

    public static void main(String[] args) {
        // При сохранении или отправке данных эта мапа должна идти вместе с ними, чтобы потом клиент мог
        // создать свой экземпляр класса Compressor и распаковать полученное (именно поэтому нет смысла
        // сжимать небольшие объемы данных - тогда сохранение инфы об этой мапе съест всю экономию)
        final Map<Character, Integer> characterWeightMap = weightMapCreator.getCharacterWeightMap(string);
        final DataCompressor_String compressor = new DataCompressor_String(codeTreeCreator, characterWeightMap);
        final String compressedString = compressor.compress(string);
        final String uncompressedString = compressor.uncompress(compressedString);
        System.out.println("Исходная строка: " + string);
        System.out.println("Размер исходной строки: " + string.getBytes().length * 8);
        System.out.println("Сжатая строка: " + compressedString);
        // Тут немного лукавим.
        // Конечно это не размер, а длина строки. Но мы имеем в виду сохранение или отправку
        // данных куда-либо, а при этом мы будем заменять "1" и "0" соответствующими битами, поэтому это как раз
        // и окажется размер строки.
        // Хотя, конечно, писать их придется в байты и размер может оказаться больше
        // (если длина не будет кратна 8, то так и будет), но максимум на 7 бит, чем мы в этом выводе смело пренебрегаем
        System.out.println("Размер сжатой строки: " + compressedString.length());
        System.out.println("Обратно разжатая строка: " + uncompressedString);
    }

}
