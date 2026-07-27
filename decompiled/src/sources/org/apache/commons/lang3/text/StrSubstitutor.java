package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* loaded from: classes17.dex */
public class StrSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
    public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher("}");
    public static final StrMatcher DEFAULT_VALUE_DELIMITER = StrMatcher.stringMatcher(":-");
    private boolean enableSubstitutionInVariables;
    private char escapeChar;
    private StrMatcher prefixMatcher;
    private boolean preserveEscapes;
    private StrMatcher suffixMatcher;
    private StrMatcher valueDelimiterMatcher;
    private StrLookup<?> variableResolver;

    public static <V> String replace(Object source, Map<String, V> valueMap) {
        return new StrSubstitutor(valueMap).replace(source);
    }

    public static <V> String replace(Object source, Map<String, V> valueMap, String prefix, String suffix) {
        return new StrSubstitutor(valueMap, prefix, suffix).replace(source);
    }

    public static String replace(Object source, Properties valueProperties) {
        if (valueProperties == null) {
            return source.toString();
        }
        Map<String, String> valueMap = new HashMap<>();
        Enumeration<?> propNames = valueProperties.propertyNames();
        while (propNames.hasMoreElements()) {
            String propName = (String) propNames.nextElement();
            String propValue = valueProperties.getProperty(propName);
            valueMap.put(propName, propValue);
        }
        return replace(source, valueMap);
    }

    public static String replaceSystemProperties(Object source) {
        return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(source);
    }

    public StrSubstitutor() {
        this((StrLookup<?>) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public <V> StrSubstitutor(Map<String, V> valueMap) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), prefix, suffix, '$');
    }

    public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix, char escape) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), prefix, suffix, escape);
    }

    public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix, char escape, String valueDelimiter) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), prefix, suffix, escape, valueDelimiter);
    }

    public StrSubstitutor(StrLookup<?> variableResolver) {
        this(variableResolver, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public StrSubstitutor(StrLookup<?> variableResolver, String prefix, String suffix, char escape) {
        setVariableResolver(variableResolver);
        setVariablePrefix(prefix);
        setVariableSuffix(suffix);
        setEscapeChar(escape);
        setValueDelimiterMatcher(DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> variableResolver, String prefix, String suffix, char escape, String valueDelimiter) {
        setVariableResolver(variableResolver);
        setVariablePrefix(prefix);
        setVariableSuffix(suffix);
        setEscapeChar(escape);
        setValueDelimiter(valueDelimiter);
    }

    public StrSubstitutor(StrLookup<?> variableResolver, StrMatcher prefixMatcher, StrMatcher suffixMatcher, char escape) {
        this(variableResolver, prefixMatcher, suffixMatcher, escape, DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> variableResolver, StrMatcher prefixMatcher, StrMatcher suffixMatcher, char escape, StrMatcher valueDelimiterMatcher) {
        setVariableResolver(variableResolver);
        setVariablePrefixMatcher(prefixMatcher);
        setVariableSuffixMatcher(suffixMatcher);
        setEscapeChar(escape);
        setValueDelimiterMatcher(valueDelimiterMatcher);
    }

    public String replace(String source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source);
        if (!substitute(buf, 0, source.length())) {
            return source;
        }
        return buf.toString();
    }

    public String replace(String source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        if (!substitute(buf, 0, length)) {
            return source.substring(offset, offset + length);
        }
        return buf.toString();
    }

    public String replace(char[] source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source.length).append(source);
        substitute(buf, 0, source.length);
        return buf.toString();
    }

    public String replace(char[] source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(StringBuffer source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source.length()).append(source);
        substitute(buf, 0, buf.length());
        return buf.toString();
    }

    public String replace(StringBuffer source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(CharSequence source) {
        if (source == null) {
            return null;
        }
        return replace(source, 0, source.length());
    }

    public String replace(CharSequence source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(StrBuilder source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source.length()).append(source);
        substitute(buf, 0, buf.length());
        return buf.toString();
    }

    public String replace(StrBuilder source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(Object source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder().append(source);
        substitute(buf, 0, buf.length());
        return buf.toString();
    }

    public boolean replaceIn(StringBuffer source) {
        if (source == null) {
            return false;
        }
        return replaceIn(source, 0, source.length());
    }

    public boolean replaceIn(StringBuffer source, int offset, int length) {
        if (source == null) {
            return false;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        if (!substitute(buf, 0, length)) {
            return false;
        }
        source.replace(offset, offset + length, buf.toString());
        return true;
    }

    public boolean replaceIn(StringBuilder source) {
        if (source == null) {
            return false;
        }
        return replaceIn(source, 0, source.length());
    }

    public boolean replaceIn(StringBuilder source, int offset, int length) {
        if (source == null) {
            return false;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        if (!substitute(buf, 0, length)) {
            return false;
        }
        source.replace(offset, offset + length, buf.toString());
        return true;
    }

    public boolean replaceIn(StrBuilder source) {
        if (source == null) {
            return false;
        }
        return substitute(source, 0, source.length());
    }

    public boolean replaceIn(StrBuilder source, int offset, int length) {
        if (source == null) {
            return false;
        }
        return substitute(source, offset, length);
    }

    protected boolean substitute(StrBuilder buf, int offset, int length) {
        return substitute(buf, offset, length, null) > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int substitute(StrBuilder buf, int offset, int length, List<String> priorVariables) {
        StrMatcher suffMatcher;
        char escape;
        boolean top;
        int i;
        int bufEnd;
        String varName;
        int lengthChange;
        String varName2;
        String varValue;
        int i2;
        int endMatchLen;
        StrSubstitutor strSubstitutor = this;
        StrMatcher pfxMatcher = getVariablePrefixMatcher();
        StrMatcher suffMatcher2 = getVariableSuffixMatcher();
        char escape2 = getEscapeChar();
        StrMatcher valueDelimMatcher = getValueDelimiterMatcher();
        boolean substitutionInVariablesEnabled = isEnableSubstitutionInVariables();
        boolean top2 = priorVariables == null;
        char[] chars = buf.buffer;
        int bufEnd2 = offset + length;
        int bufEnd3 = bufEnd2;
        int pos = offset;
        char[] chars2 = chars;
        int lengthChange2 = 0;
        int lengthChange3 = 0;
        List<String> priorVariables2 = priorVariables;
        while (pos < bufEnd3) {
            int startMatchLen = pfxMatcher.isMatch(chars2, pos, offset, bufEnd3);
            if (startMatchLen == 0) {
                pos++;
                suffMatcher = suffMatcher2;
                escape = escape2;
                top = top2;
            } else {
                if (pos > offset) {
                    i = lengthChange3;
                    if (chars2[pos - 1] == escape2) {
                        if (strSubstitutor.preserveEscapes) {
                            pos++;
                            lengthChange3 = i;
                        } else {
                            buf.deleteCharAt(pos - 1);
                            char[] chars3 = buf.buffer;
                            lengthChange2--;
                            bufEnd3--;
                            suffMatcher = suffMatcher2;
                            escape = escape2;
                            top = top2;
                            chars2 = chars3;
                            lengthChange3 = 1;
                        }
                    }
                } else {
                    i = lengthChange3;
                }
                int startPos = pos;
                pos += startMatchLen;
                int nestedVarCount = 0;
                while (true) {
                    if (pos >= bufEnd3) {
                        suffMatcher = suffMatcher2;
                        escape = escape2;
                        top = top2;
                        lengthChange3 = i;
                        break;
                    }
                    if (substitutionInVariablesEnabled && (endMatchLen = pfxMatcher.isMatch(chars2, pos, offset, bufEnd3)) != 0) {
                        nestedVarCount++;
                        pos += endMatchLen;
                    } else {
                        int endMatchLen2 = suffMatcher2.isMatch(chars2, pos, offset, bufEnd3);
                        if (endMatchLen2 == 0) {
                            pos++;
                        } else if (nestedVarCount == 0) {
                            suffMatcher = suffMatcher2;
                            escape = escape2;
                            top = top2;
                            String varNameExpr = new String(chars2, startPos + startMatchLen, (pos - startPos) - startMatchLen);
                            if (substitutionInVariablesEnabled) {
                                StrBuilder bufName = new StrBuilder(varNameExpr);
                                strSubstitutor.substitute(bufName, 0, bufName.length());
                                varNameExpr = bufName.toString();
                            }
                            pos += endMatchLen2;
                            String varName3 = varNameExpr;
                            String varDefaultValue = null;
                            if (valueDelimMatcher == null) {
                                bufEnd = bufEnd3;
                                varName = varName3;
                                lengthChange = lengthChange2;
                            } else {
                                varName = varName3;
                                char[] varNameExprChars = varNameExpr.toCharArray();
                                lengthChange = lengthChange2;
                                int lengthChange4 = 0;
                                while (true) {
                                    bufEnd = bufEnd3;
                                    int bufEnd4 = varNameExprChars.length;
                                    if (lengthChange4 >= bufEnd4 || (!substitutionInVariablesEnabled && pfxMatcher.isMatch(varNameExprChars, lengthChange4, lengthChange4, varNameExprChars.length) != 0)) {
                                        break;
                                    }
                                    int valueDelimiterMatchLen = valueDelimMatcher.isMatch(varNameExprChars, lengthChange4);
                                    if (valueDelimiterMatchLen == 0) {
                                        lengthChange4++;
                                        bufEnd3 = bufEnd;
                                    } else {
                                        String varName4 = varNameExpr.substring(0, lengthChange4);
                                        varDefaultValue = varNameExpr.substring(lengthChange4 + valueDelimiterMatchLen);
                                        varName2 = varName4;
                                        break;
                                    }
                                }
                                if (priorVariables2 == null) {
                                    priorVariables2 = new ArrayList();
                                    priorVariables2.add(new String(chars2, offset, length));
                                }
                                strSubstitutor.checkCyclicSubstitution(varName2, priorVariables2);
                                priorVariables2.add(varName2);
                                varValue = strSubstitutor.resolveVariable(varName2, buf, startPos, pos);
                                if (varValue == null) {
                                    varValue = varDefaultValue;
                                }
                                if (varValue != null) {
                                    i2 = i;
                                    lengthChange2 = lengthChange;
                                } else {
                                    int varLen = varValue.length();
                                    buf.replace(startPos, pos, varValue);
                                    i2 = 1;
                                    int change = (strSubstitutor.substitute(buf, startPos, varLen, priorVariables2) + varLen) - (pos - startPos);
                                    pos += change;
                                    chars2 = buf.buffer;
                                    bufEnd += change;
                                    lengthChange2 = lengthChange + change;
                                }
                                priorVariables2.remove(priorVariables2.size() - 1);
                                lengthChange3 = i2;
                                bufEnd3 = bufEnd;
                            }
                            varName2 = varName;
                            if (priorVariables2 == null) {
                            }
                            strSubstitutor.checkCyclicSubstitution(varName2, priorVariables2);
                            priorVariables2.add(varName2);
                            varValue = strSubstitutor.resolveVariable(varName2, buf, startPos, pos);
                            if (varValue == null) {
                            }
                            if (varValue != null) {
                            }
                            priorVariables2.remove(priorVariables2.size() - 1);
                            lengthChange3 = i2;
                            bufEnd3 = bufEnd;
                        } else {
                            nestedVarCount--;
                            pos += endMatchLen2;
                            strSubstitutor = this;
                        }
                    }
                }
            }
            strSubstitutor = this;
            suffMatcher2 = suffMatcher;
            escape2 = escape;
            top2 = top;
        }
        int i3 = lengthChange3;
        int lengthChange5 = lengthChange2;
        if (top2) {
            return i3;
        }
        return lengthChange5;
    }

    private void checkCyclicSubstitution(String varName, List<String> priorVariables) {
        if (!priorVariables.contains(varName)) {
            return;
        }
        StrBuilder buf = new StrBuilder(256);
        buf.append("Infinite loop in property interpolation of ");
        buf.append(priorVariables.remove(0));
        buf.append(": ");
        buf.appendWithSeparators(priorVariables, "->");
        throw new IllegalStateException(buf.toString());
    }

    protected String resolveVariable(String variableName, StrBuilder buf, int startPos, int endPos) {
        StrLookup<?> resolver = getVariableResolver();
        if (resolver == null) {
            return null;
        }
        return resolver.lookup(variableName);
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public void setEscapeChar(char escapeCharacter) {
        this.escapeChar = escapeCharacter;
    }

    public StrMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StrSubstitutor setVariablePrefixMatcher(StrMatcher prefixMatcher) {
        if (prefixMatcher == null) {
            throw new IllegalArgumentException("Variable prefix matcher must not be null.");
        }
        this.prefixMatcher = prefixMatcher;
        return this;
    }

    public StrSubstitutor setVariablePrefix(char prefix) {
        return setVariablePrefixMatcher(StrMatcher.charMatcher(prefix));
    }

    public StrSubstitutor setVariablePrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Variable prefix must not be null.");
        }
        return setVariablePrefixMatcher(StrMatcher.stringMatcher(prefix));
    }

    public StrMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public StrSubstitutor setVariableSuffixMatcher(StrMatcher suffixMatcher) {
        if (suffixMatcher == null) {
            throw new IllegalArgumentException("Variable suffix matcher must not be null.");
        }
        this.suffixMatcher = suffixMatcher;
        return this;
    }

    public StrSubstitutor setVariableSuffix(char suffix) {
        return setVariableSuffixMatcher(StrMatcher.charMatcher(suffix));
    }

    public StrSubstitutor setVariableSuffix(String suffix) {
        if (suffix == null) {
            throw new IllegalArgumentException("Variable suffix must not be null.");
        }
        return setVariableSuffixMatcher(StrMatcher.stringMatcher(suffix));
    }

    public StrMatcher getValueDelimiterMatcher() {
        return this.valueDelimiterMatcher;
    }

    public StrSubstitutor setValueDelimiterMatcher(StrMatcher valueDelimiterMatcher) {
        this.valueDelimiterMatcher = valueDelimiterMatcher;
        return this;
    }

    public StrSubstitutor setValueDelimiter(char valueDelimiter) {
        return setValueDelimiterMatcher(StrMatcher.charMatcher(valueDelimiter));
    }

    public StrSubstitutor setValueDelimiter(String valueDelimiter) {
        if (StringUtils.isEmpty(valueDelimiter)) {
            setValueDelimiterMatcher(null);
            return this;
        }
        return setValueDelimiterMatcher(StrMatcher.stringMatcher(valueDelimiter));
    }

    public StrLookup<?> getVariableResolver() {
        return this.variableResolver;
    }

    public void setVariableResolver(StrLookup<?> variableResolver) {
        this.variableResolver = variableResolver;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public void setEnableSubstitutionInVariables(boolean enableSubstitutionInVariables) {
        this.enableSubstitutionInVariables = enableSubstitutionInVariables;
    }

    public boolean isPreserveEscapes() {
        return this.preserveEscapes;
    }

    public void setPreserveEscapes(boolean preserveEscapes) {
        this.preserveEscapes = preserveEscapes;
    }
}
