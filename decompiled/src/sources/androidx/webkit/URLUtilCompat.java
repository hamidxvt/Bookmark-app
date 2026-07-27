package androidx.webkit;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public final class URLUtilCompat {
    private static final Pattern DISPOSITION_PATTERN = Pattern.compile("\\s*(\\S+?) # Group 1: parameter name\n\\s*=\\s* # Match equals sign\n(?: # non-capturing group of options\n   '( (?: [^'\\\\] | \\\\. )* )' # Group 2: single-quoted\n | \"( (?: [^\"\\\\] | \\\\. )*  )\" # Group 3: double-quoted\n | ( [^'\"][^;\\s]* ) # Group 4: un-quoted parameter\n)\\s*;? # Optional end semicolon", 4);

    private URLUtilCompat() {
    }

    public static String guessFileName(String url, String contentDisposition, String mimeType) {
        String filename = getFilenameSuggestion(url, contentDisposition);
        String extensionFromMimeType = suggestExtensionFromMimeType(mimeType);
        if (filename.indexOf(46) < 0) {
            return filename + extensionFromMimeType;
        }
        if (mimeType != null && extensionDifferentFromMimeType(filename, mimeType)) {
            return filename + extensionFromMimeType;
        }
        return filename;
    }

    private static String getFilenameSuggestion(String url, String contentDisposition) {
        String lastPathSegment;
        String filename;
        if (contentDisposition != null && (filename = getFilenameFromContentDisposition(contentDisposition)) != null) {
            return replacePathSeparators(filename);
        }
        Uri parsedUri = Uri.parse(url);
        if (parsedUri != null && (lastPathSegment = parsedUri.getLastPathSegment()) != null) {
            return replacePathSeparators(lastPathSegment);
        }
        return "downloadfile";
    }

    private static String replacePathSeparators(String raw) {
        return raw.replaceAll("/", "_");
    }

    private static boolean extensionDifferentFromMimeType(String filename, String mimeType) {
        int lastDotIndex = filename.lastIndexOf(46);
        String typeFromExt = MimeTypeMap.getSingleton().getMimeTypeFromExtension(filename.substring(lastDotIndex + 1));
        return (typeFromExt == null || typeFromExt.equalsIgnoreCase(mimeType)) ? false : true;
    }

    private static String suggestExtensionFromMimeType(String mimeType) {
        if (mimeType == null) {
            return ".bin";
        }
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
        if (extensionFromMimeType != null) {
            return "." + extensionFromMimeType;
        }
        if (mimeType.equalsIgnoreCase("text/html")) {
            return ".html";
        }
        if (!mimeType.toLowerCase(Locale.ROOT).startsWith("text/")) {
            return ".bin";
        }
        return ".txt";
    }

    public static String getFilenameFromContentDisposition(String contentDisposition) {
        String value;
        String[] parts = contentDisposition.trim().split(";", 2);
        if (parts.length < 2) {
            return null;
        }
        String dispositionType = parts[0].trim();
        if ("inline".equalsIgnoreCase(dispositionType)) {
            return null;
        }
        String dispositionParameters = parts[1];
        Matcher matcher = DISPOSITION_PATTERN.matcher(dispositionParameters);
        String filename = null;
        String filenameExt = null;
        while (matcher.find()) {
            String parameter = matcher.group(1);
            if (matcher.group(2) != null) {
                value = removeSlashEscapes(matcher.group(2));
            } else if (matcher.group(3) != null) {
                value = removeSlashEscapes(matcher.group(3));
            } else {
                value = matcher.group(4);
            }
            if (parameter != null && value != null) {
                if ("filename*".equalsIgnoreCase(parameter)) {
                    filenameExt = parseExtValueString(value);
                } else if ("filename".equalsIgnoreCase(parameter)) {
                    filename = value;
                }
            }
        }
        if (filenameExt != null) {
            return filenameExt;
        }
        return filename;
    }

    private static String removeSlashEscapes(String raw) {
        if (raw == null) {
            return null;
        }
        return raw.replaceAll("\\\\(.)", "$1");
    }

    private static String parseExtValueString(String raw) {
        String[] parts = raw.split("'", 3);
        if (parts.length < 3) {
            return null;
        }
        String encoding = parts[0];
        String valueChars = parts[2];
        try {
            String valueWithEncodedPlus = encodePlusCharacters(valueChars, encoding);
            return URLDecoder.decode(valueWithEncodedPlus, encoding);
        } catch (UnsupportedEncodingException | RuntimeException e) {
            return null;
        }
    }

    private static String encodePlusCharacters(String valueChars, String encoding) {
        Charset charset = Charset.forName(encoding);
        StringBuilder sb = new StringBuilder();
        for (byte b : charset.encode(Marker.ANY_NON_NULL_MARKER).array()) {
            sb.append(String.format("%02x", Byte.valueOf(b)));
        }
        return valueChars.replaceAll("\\+", sb.toString());
    }
}
