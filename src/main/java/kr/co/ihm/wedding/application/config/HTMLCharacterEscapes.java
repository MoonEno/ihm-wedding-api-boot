package kr.co.ihm.wedding.application.config;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import org.apache.commons.lang3.StringEscapeUtils;

/**

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

import org.apache.commons.text.StringEscapeUtils;

/**
 * @desc XSS Filter 용
 * @author hdkim
 */
@SuppressWarnings("serial")
public class HTMLCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    public HTMLCharacterEscapes() {
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        int ret[] = null;

        if (this.asciiEscapes != null) {
            ret = new int[this.asciiEscapes.length];

            for (int i=0; i<this.asciiEscapes.length; ++i) {
                ret[i] = this.asciiEscapes[i];
            }
        }

        return ret;
    }

    @Override
    public SerializableString getEscapeSequence(int arg0) {
        return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString((char)arg0)));
    }
}
