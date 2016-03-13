package net.dzikoysk.server.element;

import java.util.HashMap;
import java.util.Map;

public enum ChatColor {

    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),

    OBFUSCATED('k'),
    BOLD('l'),
    STRIKETHROUGH('m'),
    UNDERLINE('n'),
    ITALIC('o'),
    RESET('r');

    public static final char COLOR_CHAR = '\u00A7';
    public static final char DEFAULT_ALTERNATE_COLOR_CHAR = '&';

    private static final Map<Character, ChatColor> BY_CHAR;

    static {
        BY_CHAR = new HashMap<>();

        for (ChatColor chatColor : values()) {
            BY_CHAR.put(chatColor.getCharCode(), chatColor);
        }
    }

    private final char charCode;

    ChatColor(char charCode) {
        this.charCode = charCode;
    }

    public static ChatColor getByCharCode(char c) {
        return BY_CHAR.get(c);
    }

    public static String translate(String string) {
        return translate(DEFAULT_ALTERNATE_COLOR_CHAR, string);
    }

    public static String translate(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < (b.length - 1); i++) {
            if ((b[i] == altColorChar) && ("0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[(i + 1)]) > -1)) {
                b[i] = COLOR_CHAR;
                b[(i + 1)] = Character.toLowerCase(b[(i + 1)]);
            }
        }
        return new String(b);
    }

    public char getCharCode() {
        return charCode;
    }

}