package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.Validate;

/* loaded from: classes17.dex */
abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private static final ConcurrentMap<ArrayKey, String> cDateTimeInstanceCache = new ConcurrentHashMap(7);
    private final ConcurrentMap<ArrayKey, F> cInstanceCache = new ConcurrentHashMap(7);

    protected abstract F createInstance(String str, TimeZone timeZone, Locale locale);

    FormatCache() {
    }

    public F getInstance() {
        return getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(String pattern, TimeZone timeZone, Locale locale) {
        Validate.notNull(pattern, "pattern", new Object[0]);
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        Locale locale2 = LocaleUtils.toLocale(locale);
        ArrayKey key = new ArrayKey(pattern, timeZone, locale2);
        F format = this.cInstanceCache.get(key);
        if (format == null) {
            F format2 = createInstance(pattern, timeZone, locale2);
            F previousValue = this.cInstanceCache.putIfAbsent(key, format2);
            if (previousValue != null) {
                return previousValue;
            }
            return format2;
        }
        return format;
    }

    private F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale) {
        Locale locale2 = LocaleUtils.toLocale(locale);
        String pattern = getPatternForStyle(dateStyle, timeStyle, locale2);
        return getInstance(pattern, timeZone, locale2);
    }

    F getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), timeZone, locale);
    }

    F getDateInstance(int dateStyle, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance(Integer.valueOf(dateStyle), (Integer) null, timeZone, locale);
    }

    F getTimeInstance(int timeStyle, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance((Integer) null, Integer.valueOf(timeStyle), timeZone, locale);
    }

    static String getPatternForStyle(Integer dateStyle, Integer timeStyle, Locale locale) {
        DateFormat formatter;
        Locale safeLocale = LocaleUtils.toLocale(locale);
        ArrayKey key = new ArrayKey(dateStyle, timeStyle, safeLocale);
        String pattern = cDateTimeInstanceCache.get(key);
        if (pattern == null) {
            try {
                if (dateStyle == null) {
                    formatter = DateFormat.getTimeInstance(timeStyle.intValue(), safeLocale);
                } else if (timeStyle == null) {
                    formatter = DateFormat.getDateInstance(dateStyle.intValue(), safeLocale);
                } else {
                    formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), safeLocale);
                }
                String pattern2 = ((SimpleDateFormat) formatter).toPattern();
                String previous = cDateTimeInstanceCache.putIfAbsent(key, pattern2);
                return previous != null ? previous : pattern2;
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("No date time pattern for locale: " + safeLocale);
            }
        }
        return pattern;
    }

    private static final class ArrayKey {
        private final int hashCode;
        private final Object[] keys;

        private static int computeHashCode(Object[] keys) {
            int result = (1 * 31) + Arrays.hashCode(keys);
            return result;
        }

        ArrayKey(Object... keys) {
            this.keys = keys;
            this.hashCode = computeHashCode(keys);
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ArrayKey other = (ArrayKey) obj;
            return Arrays.deepEquals(this.keys, other.keys);
        }
    }
}
