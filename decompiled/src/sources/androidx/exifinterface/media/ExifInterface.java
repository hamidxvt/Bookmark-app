package androidx.exifinterface.media;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import androidx.work.WorkRequest;
import com.budiyev.android.codescanner.BarcodeUtils;
import com.github.mikephil.charting.utils.Utils;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.CharUtils;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class ExifInterface {
    public static final short ALTITUDE_ABOVE_SEA_LEVEL = 0;
    public static final short ALTITUDE_BELOW_SEA_LEVEL = 1;
    static final short BYTE_ALIGN_II = 18761;
    static final short BYTE_ALIGN_MM = 19789;
    public static final int COLOR_SPACE_S_RGB = 1;
    public static final int COLOR_SPACE_UNCALIBRATED = 65535;
    public static final short CONTRAST_HARD = 2;
    public static final short CONTRAST_NORMAL = 0;
    public static final short CONTRAST_SOFT = 1;
    public static final int DATA_DEFLATE_ZIP = 8;
    public static final int DATA_HUFFMAN_COMPRESSED = 2;
    public static final int DATA_JPEG = 6;
    public static final int DATA_JPEG_COMPRESSED = 7;
    public static final int DATA_LOSSY_JPEG = 34892;
    public static final int DATA_PACK_BITS_COMPRESSED = 32773;
    public static final int DATA_UNCOMPRESSED = 1;
    private static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN;
    private static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN;
    private static final int DATETIME_VALUE_STRING_LENGTH = 19;
    public static final short EXPOSURE_MODE_AUTO = 0;
    public static final short EXPOSURE_MODE_AUTO_BRACKET = 2;
    public static final short EXPOSURE_MODE_MANUAL = 1;
    public static final short EXPOSURE_PROGRAM_ACTION = 6;
    public static final short EXPOSURE_PROGRAM_APERTURE_PRIORITY = 3;
    public static final short EXPOSURE_PROGRAM_CREATIVE = 5;
    public static final short EXPOSURE_PROGRAM_LANDSCAPE_MODE = 8;
    public static final short EXPOSURE_PROGRAM_MANUAL = 1;
    public static final short EXPOSURE_PROGRAM_NORMAL = 2;
    public static final short EXPOSURE_PROGRAM_NOT_DEFINED = 0;
    public static final short EXPOSURE_PROGRAM_PORTRAIT_MODE = 7;
    public static final short EXPOSURE_PROGRAM_SHUTTER_PRIORITY = 4;
    public static final short FILE_SOURCE_DSC = 3;
    public static final short FILE_SOURCE_OTHER = 0;
    public static final short FILE_SOURCE_REFLEX_SCANNER = 2;
    public static final short FILE_SOURCE_TRANSPARENT_SCANNER = 1;
    public static final short FLAG_FLASH_FIRED = 1;
    public static final short FLAG_FLASH_MODE_AUTO = 24;
    public static final short FLAG_FLASH_MODE_COMPULSORY_FIRING = 8;
    public static final short FLAG_FLASH_MODE_COMPULSORY_SUPPRESSION = 16;
    public static final short FLAG_FLASH_NO_FLASH_FUNCTION = 32;
    public static final short FLAG_FLASH_RED_EYE_SUPPORTED = 64;
    public static final short FLAG_FLASH_RETURN_LIGHT_DETECTED = 6;
    public static final short FLAG_FLASH_RETURN_LIGHT_NOT_DETECTED = 4;
    public static final short FORMAT_CHUNKY = 1;
    public static final short FORMAT_PLANAR = 2;
    public static final short GAIN_CONTROL_HIGH_GAIN_DOWN = 4;
    public static final short GAIN_CONTROL_HIGH_GAIN_UP = 2;
    public static final short GAIN_CONTROL_LOW_GAIN_DOWN = 3;
    public static final short GAIN_CONTROL_LOW_GAIN_UP = 1;
    public static final short GAIN_CONTROL_NONE = 0;
    public static final String GPS_DIRECTION_MAGNETIC = "M";
    public static final String GPS_DIRECTION_TRUE = "T";
    public static final String GPS_DISTANCE_KILOMETERS = "K";
    public static final String GPS_DISTANCE_MILES = "M";
    public static final String GPS_DISTANCE_NAUTICAL_MILES = "N";
    public static final String GPS_MEASUREMENT_2D = "2";
    public static final String GPS_MEASUREMENT_3D = "3";
    public static final short GPS_MEASUREMENT_DIFFERENTIAL_CORRECTED = 1;
    public static final String GPS_MEASUREMENT_INTERRUPTED = "V";
    public static final String GPS_MEASUREMENT_IN_PROGRESS = "A";
    public static final short GPS_MEASUREMENT_NO_DIFFERENTIAL = 0;
    public static final String GPS_SPEED_KILOMETERS_PER_HOUR = "K";
    public static final String GPS_SPEED_KNOTS = "N";
    public static final String GPS_SPEED_MILES_PER_HOUR = "M";
    private static final Pattern GPS_TIMESTAMP_PATTERN;
    private static final int IFD_FORMAT_BYTE = 1;
    private static final int IFD_FORMAT_DOUBLE = 12;
    private static final int IFD_FORMAT_IFD = 13;
    private static final int IFD_FORMAT_SBYTE = 6;
    private static final int IFD_FORMAT_SINGLE = 11;
    private static final int IFD_FORMAT_SLONG = 9;
    private static final int IFD_FORMAT_SRATIONAL = 10;
    private static final int IFD_FORMAT_SSHORT = 8;
    private static final int IFD_FORMAT_STRING = 2;
    private static final int IFD_FORMAT_ULONG = 4;
    private static final int IFD_FORMAT_UNDEFINED = 7;
    private static final int IFD_FORMAT_URATIONAL = 5;
    private static final int IFD_FORMAT_USHORT = 3;
    private static final int IFD_OFFSET = 8;
    private static final int IFD_TYPE_EXIF = 1;
    private static final int IFD_TYPE_GPS = 2;
    private static final int IFD_TYPE_INTEROPERABILITY = 3;
    private static final int IFD_TYPE_ORF_CAMERA_SETTINGS = 7;
    private static final int IFD_TYPE_ORF_IMAGE_PROCESSING = 8;
    private static final int IFD_TYPE_ORF_MAKER_NOTE = 6;
    private static final int IFD_TYPE_PEF = 9;
    static final int IFD_TYPE_PREVIEW = 5;
    static final int IFD_TYPE_PRIMARY = 0;
    static final int IFD_TYPE_THUMBNAIL = 4;
    private static final int IMAGE_TYPE_ARW = 1;
    private static final int IMAGE_TYPE_CR2 = 2;
    private static final int IMAGE_TYPE_DNG = 3;
    private static final int IMAGE_TYPE_HEIF = 12;
    private static final int IMAGE_TYPE_JPEG = 4;
    private static final int IMAGE_TYPE_NEF = 5;
    private static final int IMAGE_TYPE_NRW = 6;
    private static final int IMAGE_TYPE_ORF = 7;
    private static final int IMAGE_TYPE_PEF = 8;
    private static final int IMAGE_TYPE_PNG = 13;
    private static final int IMAGE_TYPE_RAF = 9;
    private static final int IMAGE_TYPE_RW2 = 10;
    private static final int IMAGE_TYPE_SRW = 11;
    private static final int IMAGE_TYPE_UNKNOWN = 0;
    private static final int IMAGE_TYPE_WEBP = 14;
    public static final String LATITUDE_NORTH = "N";
    public static final String LATITUDE_SOUTH = "S";
    public static final short LIGHT_SOURCE_CLOUDY_WEATHER = 10;
    public static final short LIGHT_SOURCE_COOL_WHITE_FLUORESCENT = 14;
    public static final short LIGHT_SOURCE_D50 = 23;
    public static final short LIGHT_SOURCE_D55 = 20;
    public static final short LIGHT_SOURCE_D65 = 21;
    public static final short LIGHT_SOURCE_D75 = 22;
    public static final short LIGHT_SOURCE_DAYLIGHT = 1;
    public static final short LIGHT_SOURCE_DAYLIGHT_FLUORESCENT = 12;
    public static final short LIGHT_SOURCE_DAY_WHITE_FLUORESCENT = 13;
    public static final short LIGHT_SOURCE_FINE_WEATHER = 9;
    public static final short LIGHT_SOURCE_FLASH = 4;
    public static final short LIGHT_SOURCE_FLUORESCENT = 2;
    public static final short LIGHT_SOURCE_ISO_STUDIO_TUNGSTEN = 24;
    public static final short LIGHT_SOURCE_OTHER = 255;
    public static final short LIGHT_SOURCE_SHADE = 11;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_A = 17;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_B = 18;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_C = 19;
    public static final short LIGHT_SOURCE_TUNGSTEN = 3;
    public static final short LIGHT_SOURCE_UNKNOWN = 0;
    public static final short LIGHT_SOURCE_WARM_WHITE_FLUORESCENT = 16;
    public static final short LIGHT_SOURCE_WHITE_FLUORESCENT = 15;
    public static final String LONGITUDE_EAST = "E";
    public static final String LONGITUDE_WEST = "W";
    static final byte MARKER = -1;
    static final byte MARKER_APP1 = -31;
    private static final byte MARKER_COM = -2;
    static final byte MARKER_EOI = -39;
    private static final byte MARKER_SOF0 = -64;
    private static final byte MARKER_SOF1 = -63;
    private static final byte MARKER_SOF10 = -54;
    private static final byte MARKER_SOF11 = -53;
    private static final byte MARKER_SOF13 = -51;
    private static final byte MARKER_SOF14 = -50;
    private static final byte MARKER_SOF15 = -49;
    private static final byte MARKER_SOF2 = -62;
    private static final byte MARKER_SOF3 = -61;
    private static final byte MARKER_SOF5 = -59;
    private static final byte MARKER_SOF6 = -58;
    private static final byte MARKER_SOF7 = -57;
    private static final byte MARKER_SOF9 = -55;
    private static final byte MARKER_SOS = -38;
    private static final int MAX_THUMBNAIL_SIZE = 512;
    public static final short METERING_MODE_AVERAGE = 1;
    public static final short METERING_MODE_CENTER_WEIGHT_AVERAGE = 2;
    public static final short METERING_MODE_MULTI_SPOT = 4;
    public static final short METERING_MODE_OTHER = 255;
    public static final short METERING_MODE_PARTIAL = 6;
    public static final short METERING_MODE_PATTERN = 5;
    public static final short METERING_MODE_SPOT = 3;
    public static final short METERING_MODE_UNKNOWN = 0;
    private static final Pattern NON_ZERO_TIME_PATTERN;
    private static final int ORF_MAKER_NOTE_HEADER_1_SIZE = 8;
    private static final int ORF_MAKER_NOTE_HEADER_2_SIZE = 12;
    private static final short ORF_SIGNATURE_1 = 20306;
    private static final short ORF_SIGNATURE_2 = 21330;
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int ORIGINAL_RESOLUTION_IMAGE = 0;
    private static final int PEF_MAKER_NOTE_SKIP_SIZE = 6;
    private static final String PEF_SIGNATURE = "PENTAX";
    public static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    private static final int PNG_CHUNK_CRC_BYTE_LENGTH = 4;
    private static final int PNG_CHUNK_TYPE_BYTE_LENGTH = 4;
    private static final int RAF_OFFSET_TO_JPEG_IMAGE_OFFSET = 84;
    private static final String RAF_SIGNATURE = "FUJIFILMCCD-RAW";
    public static final int REDUCED_RESOLUTION_IMAGE = 1;
    public static final short RENDERED_PROCESS_CUSTOM = 1;
    public static final short RENDERED_PROCESS_NORMAL = 0;
    public static final short RESOLUTION_UNIT_CENTIMETERS = 3;
    public static final short RESOLUTION_UNIT_INCHES = 2;
    private static final short RW2_SIGNATURE = 85;
    public static final short SATURATION_HIGH = 0;
    public static final short SATURATION_LOW = 0;
    public static final short SATURATION_NORMAL = 0;
    public static final short SCENE_CAPTURE_TYPE_LANDSCAPE = 1;
    public static final short SCENE_CAPTURE_TYPE_NIGHT = 3;
    public static final short SCENE_CAPTURE_TYPE_PORTRAIT = 2;
    public static final short SCENE_CAPTURE_TYPE_STANDARD = 0;
    public static final short SCENE_TYPE_DIRECTLY_PHOTOGRAPHED = 1;
    public static final short SENSITIVITY_TYPE_ISO_SPEED = 3;
    public static final short SENSITIVITY_TYPE_REI = 2;
    public static final short SENSITIVITY_TYPE_REI_AND_ISO = 6;
    public static final short SENSITIVITY_TYPE_SOS = 1;
    public static final short SENSITIVITY_TYPE_SOS_AND_ISO = 5;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI = 4;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI_AND_ISO = 7;
    public static final short SENSITIVITY_TYPE_UNKNOWN = 0;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL = 5;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL_LINEAR = 8;
    public static final short SENSOR_TYPE_NOT_DEFINED = 1;
    public static final short SENSOR_TYPE_ONE_CHIP = 2;
    public static final short SENSOR_TYPE_THREE_CHIP = 4;
    public static final short SENSOR_TYPE_TRILINEAR = 7;
    public static final short SENSOR_TYPE_TWO_CHIP = 3;
    public static final short SHARPNESS_HARD = 2;
    public static final short SHARPNESS_NORMAL = 0;
    public static final short SHARPNESS_SOFT = 1;
    private static final int SIGNATURE_CHECK_SIZE = 5000;
    public static final int STREAM_TYPE_EXIF_DATA_ONLY = 1;
    public static final int STREAM_TYPE_FULL_IMAGE_DATA = 0;
    public static final short SUBJECT_DISTANCE_RANGE_CLOSE_VIEW = 2;
    public static final short SUBJECT_DISTANCE_RANGE_DISTANT_VIEW = 3;
    public static final short SUBJECT_DISTANCE_RANGE_MACRO = 1;
    public static final short SUBJECT_DISTANCE_RANGE_UNKNOWN = 0;
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BODY_SERIAL_NUMBER = "BodySerialNumber";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";

    @Deprecated
    public static final String TAG_CAMARA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CAMERA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_COPYRIGHT = "Copyright";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_DNG_VERSION = "DNGVersion";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
    public static final String TAG_F_NUMBER = "FNumber";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_GAMMA = "Gamma";
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
    public static final String TAG_GPS_DOP = "GPSDOP";
    public static final String TAG_GPS_H_POSITIONING_ERROR = "GPSHPositioningError";
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";
    public static final String TAG_GPS_SPEED = "GPSSpeed";
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";
    public static final String TAG_GPS_STATUS = "GPSStatus";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_GPS_TRACK = "GPSTrack";
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";
    public static final String TAG_ISO_SPEED = "ISOSpeed";
    public static final String TAG_ISO_SPEED_LATITUDE_YYY = "ISOSpeedLatitudeyyy";
    public static final String TAG_ISO_SPEED_LATITUDE_ZZZ = "ISOSpeedLatitudezzz";

    @Deprecated
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";
    public static final String TAG_LENS_MAKE = "LensMake";
    public static final String TAG_LENS_MODEL = "LensModel";
    public static final String TAG_LENS_SERIAL_NUMBER = "LensSerialNumber";
    public static final String TAG_LENS_SPECIFICATION = "LensSpecification";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MAKER_NOTE = "MakerNote";
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";
    public static final String TAG_OECF = "OECF";
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_PHOTOGRAPHIC_SENSITIVITY = "PhotographicSensitivity";
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    public static final String TAG_RECOMMENDED_EXPOSURE_INDEX = "RecommendedExposureIndex";
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";
    public static final String TAG_RW2_ISO = "ISO";
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_SENSITIVITY_TYPE = "SensitivityType";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    public static final String TAG_STANDARD_OUTPUT_SENSITIVITY = "StandardOutputSensitivity";
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";
    public static final String TAG_SUBFILE_TYPE = "SubfileType";
    public static final String TAG_SUBJECT_AREA = "SubjectArea";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";
    public static final String TAG_SUBSEC_TIME = "SubSecTime";
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    private static final int WEBP_CHUNK_SIZE_BYTE_LENGTH = 4;
    private static final int WEBP_CHUNK_TYPE_BYTE_LENGTH = 4;
    private static final int WEBP_CHUNK_TYPE_VP8X_DEFAULT_LENGTH = 10;
    private static final int WEBP_FILE_SIZE_BYTE_LENGTH = 4;
    private static final byte WEBP_VP8L_SIGNATURE = 47;

    @Deprecated
    public static final int WHITEBALANCE_AUTO = 0;

    @Deprecated
    public static final int WHITEBALANCE_MANUAL = 1;
    public static final short WHITE_BALANCE_AUTO = 0;
    public static final short WHITE_BALANCE_MANUAL = 1;
    public static final short Y_CB_CR_POSITIONING_CENTERED = 1;
    public static final short Y_CB_CR_POSITIONING_CO_SITED = 2;
    private static SimpleDateFormat sFormatterSecondary;
    private boolean mAreThumbnailStripsConsecutive;
    private AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes;
    private Set<Integer> mAttributesOffsets;
    private ByteOrder mExifByteOrder;
    private String mFilename;
    private boolean mHasThumbnail;
    private boolean mHasThumbnailStrips;
    private boolean mIsExifDataOnly;
    private int mMimeType;
    private boolean mModified;
    private int mOffsetToExifData;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private FileDescriptor mSeekableFileDescriptor;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;
    private boolean mXmpIsFromSeparateMarker;
    private static final String TAG = "ExifInterface";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(1, 6, 3, 8);
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(2, 7, 4, 5);
    public static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    private static final byte MARKER_SOI = -40;
    static final byte[] JPEG_SIGNATURE = {-1, MARKER_SOI, -1};
    private static final byte[] HEIF_TYPE_FTYP = {102, 116, 121, 112};
    private static final byte[] HEIF_BRAND_MIF1 = {109, 105, 102, 49};
    private static final byte[] HEIF_BRAND_HEIC = {104, 101, 105, 99};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    private static final byte[] PNG_SIGNATURE = {-119, 80, 78, 71, Ascii.CR, 10, Ascii.SUB, 10};
    private static final byte[] PNG_CHUNK_TYPE_EXIF = {101, 88, 73, 102};
    private static final byte[] PNG_CHUNK_TYPE_IHDR = {73, 72, 68, 82};
    private static final byte[] PNG_CHUNK_TYPE_IEND = {73, 69, 78, 68};
    private static final byte[] WEBP_SIGNATURE_1 = {82, 73, 70, 70};
    private static final byte[] WEBP_SIGNATURE_2 = {87, 69, 66, 80};
    private static final byte[] WEBP_CHUNK_TYPE_EXIF = {69, 88, 73, 70};
    static final byte START_CODE = 42;
    private static final byte[] WEBP_VP8_SIGNATURE = {-99, 1, START_CODE};
    private static final byte[] WEBP_CHUNK_TYPE_VP8X = "VP8X".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_VP8L = "VP8L".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_VP8 = "VP8 ".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_ANIM = "ANIM".getBytes(Charset.defaultCharset());
    private static final byte[] WEBP_CHUNK_TYPE_ANMF = "ANMF".getBytes(Charset.defaultCharset());
    static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    private static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
    private static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
    public static final String TAG_XMP = "Xmp";
    private static final ExifTag[] IFD_TIFF_TAGS = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ImageWidth", 256, 3, 4), new ExifTag("ImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", BarcodeUtils.ROTATION_270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag("Orientation", 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", TypedValues.AttributesType.TYPE_PIVOT_TARGET, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag("JPEGInterchangeFormat", InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag("SensorTopBorder", 4, 4), new ExifTag("SensorLeftBorder", 5, 4), new ExifTag("SensorBottomBorder", 6, 4), new ExifTag("SensorRightBorder", 7, 4), new ExifTag("ISO", 23, 3), new ExifTag("JpgFromRaw", 46, 7), new ExifTag(TAG_XMP, TypedValues.TransitionType.TYPE_DURATION, 1)};
    public static final String TAG_OFFSET_TIME = "OffsetTime";
    public static final String TAG_OFFSET_TIME_ORIGINAL = "OffsetTimeOriginal";
    public static final String TAG_OFFSET_TIME_DIGITIZED = "OffsetTimeDigitized";
    private static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
    private static final ExifTag[] IFD_EXIF_TAGS = {new ExifTag("ExposureTime", 33434, 5), new ExifTag("FNumber", 33437, 5), new ExifTag("ExposureProgram", 34850, 3), new ExifTag("SpectralSensitivity", 34852, 2), new ExifTag("PhotographicSensitivity", 34855, 3), new ExifTag("OECF", 34856, 7), new ExifTag("SensitivityType", 34864, 3), new ExifTag("StandardOutputSensitivity", 34865, 4), new ExifTag("RecommendedExposureIndex", 34866, 4), new ExifTag("ISOSpeed", 34867, 4), new ExifTag("ISOSpeedLatitudeyyy", 34868, 4), new ExifTag("ISOSpeedLatitudezzz", 34869, 4), new ExifTag("ExifVersion", 36864, 2), new ExifTag("DateTimeOriginal", 36867, 2), new ExifTag("DateTimeDigitized", 36868, 2), new ExifTag(TAG_OFFSET_TIME, 36880, 2), new ExifTag(TAG_OFFSET_TIME_ORIGINAL, 36881, 2), new ExifTag(TAG_OFFSET_TIME_DIGITIZED, 36882, 2), new ExifTag("ComponentsConfiguration", 37121, 7), new ExifTag("CompressedBitsPerPixel", 37122, 5), new ExifTag("ShutterSpeedValue", 37377, 10), new ExifTag("ApertureValue", 37378, 5), new ExifTag("BrightnessValue", 37379, 10), new ExifTag("ExposureBiasValue", 37380, 10), new ExifTag("MaxApertureValue", 37381, 5), new ExifTag("SubjectDistance", 37382, 5), new ExifTag("MeteringMode", 37383, 3), new ExifTag("LightSource", 37384, 3), new ExifTag("Flash", 37385, 3), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, 3), new ExifTag("MakerNote", 37500, 7), new ExifTag("UserComment", 37510, 7), new ExifTag("SubSecTime", 37520, 2), new ExifTag("SubSecTimeOriginal", 37521, 2), new ExifTag("SubSecTimeDigitized", 37522, 2), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, 3), new ExifTag("PixelXDimension", 40962, 3, 4), new ExifTag("PixelYDimension", 40963, 3, 4), new ExifTag("RelatedSoundFile", 40964, 2), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, 5), new ExifTag("FocalPlaneYResolution", 41487, 5), new ExifTag("FocalPlaneResolutionUnit", 41488, 3), new ExifTag("SubjectLocation", 41492, 3), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, 3), new ExifTag("FileSource", 41728, 7), new ExifTag("SceneType", 41729, 7), new ExifTag("CFAPattern", 41730, 7), new ExifTag("CustomRendered", 41985, 3), new ExifTag("ExposureMode", 41986, 3), new ExifTag("WhiteBalance", 41987, 3), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, 3), new ExifTag("SceneCaptureType", 41990, 3), new ExifTag("GainControl", 41991, 3), new ExifTag("Contrast", 41992, 3), new ExifTag("Saturation", 41993, 3), new ExifTag("Sharpness", 41994, 3), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, 3), new ExifTag("ImageUniqueID", 42016, 2), new ExifTag("CameraOwnerName", 42032, 2), new ExifTag("BodySerialNumber", 42033, 2), new ExifTag("LensSpecification", 42034, 5), new ExifTag("LensMake", 42035, 2), new ExifTag("LensModel", 42036, 2), new ExifTag("Gamma", 42240, 5), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
    private static final ExifTag[] IFD_GPS_TAGS = {new ExifTag("GPSVersionID", 0, 1), new ExifTag("GPSLatitudeRef", 1, 2), new ExifTag("GPSLatitude", 2, 5, 10), new ExifTag("GPSLongitudeRef", 3, 2), new ExifTag("GPSLongitude", 4, 5, 10), new ExifTag("GPSAltitudeRef", 5, 1), new ExifTag("GPSAltitude", 6, 5), new ExifTag("GPSTimeStamp", 7, 5), new ExifTag("GPSSatellites", 8, 2), new ExifTag("GPSStatus", 9, 2), new ExifTag("GPSMeasureMode", 10, 2), new ExifTag("GPSDOP", 11, 5), new ExifTag("GPSSpeedRef", 12, 2), new ExifTag("GPSSpeed", 13, 5), new ExifTag("GPSTrackRef", 14, 2), new ExifTag("GPSTrack", 15, 5), new ExifTag("GPSImgDirectionRef", 16, 2), new ExifTag("GPSImgDirection", 17, 5), new ExifTag("GPSMapDatum", 18, 2), new ExifTag("GPSDestLatitudeRef", 19, 2), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, 2), new ExifTag("GPSDestLongitude", 22, 5), new ExifTag("GPSDestBearingRef", 23, 2), new ExifTag("GPSDestBearing", 24, 5), new ExifTag("GPSDestDistanceRef", 25, 2), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, 7), new ExifTag("GPSAreaInformation", 28, 7), new ExifTag("GPSDateStamp", 29, 2), new ExifTag("GPSDifferential", 30, 3), new ExifTag("GPSHPositioningError", 31, 5)};
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS = {new ExifTag("InteroperabilityIndex", 1, 2)};
    public static final String TAG_THUMBNAIL_ORIENTATION = "ThumbnailOrientation";
    private static final ExifTag[] IFD_THUMBNAIL_TAGS = {new ExifTag("NewSubfileType", 254, 4), new ExifTag("SubfileType", 255, 4), new ExifTag("ThumbnailImageWidth", 256, 3, 4), new ExifTag("ThumbnailImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, 3), new ExifTag("Compression", 259, 3), new ExifTag("PhotometricInterpretation", 262, 3), new ExifTag("ImageDescription", BarcodeUtils.ROTATION_270, 2), new ExifTag("Make", 271, 2), new ExifTag("Model", 272, 2), new ExifTag("StripOffsets", 273, 3, 4), new ExifTag(TAG_THUMBNAIL_ORIENTATION, 274, 3), new ExifTag("SamplesPerPixel", 277, 3), new ExifTag("RowsPerStrip", 278, 3, 4), new ExifTag("StripByteCounts", 279, 3, 4), new ExifTag("XResolution", 282, 5), new ExifTag("YResolution", 283, 5), new ExifTag("PlanarConfiguration", 284, 3), new ExifTag("ResolutionUnit", 296, 3), new ExifTag("TransferFunction", 301, 3), new ExifTag("Software", 305, 2), new ExifTag("DateTime", 306, 2), new ExifTag("Artist", 315, 2), new ExifTag("WhitePoint", TypedValues.AttributesType.TYPE_PIVOT_TARGET, 5), new ExifTag("PrimaryChromaticities", 319, 5), new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag("JPEGInterchangeFormat", InputDeviceCompat.SOURCE_DPAD, 4), new ExifTag("JPEGInterchangeFormatLength", 514, 4), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, 3), new ExifTag("YCbCrPositioning", 531, 3), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, 2), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, 3, 4)};
    private static final ExifTag TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, 3);
    private static final String TAG_ORF_CAMERA_SETTINGS_IFD_POINTER = "CameraSettingsIFDPointer";
    private static final String TAG_ORF_IMAGE_PROCESSING_IFD_POINTER = "ImageProcessingIFDPointer";
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS = {new ExifTag("ThumbnailImage", 256, 7), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 4), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 4)};
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS = {new ExifTag("PreviewImageStart", 257, 4), new ExifTag("PreviewImageLength", 258, 4)};
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS = {new ExifTag("AspectFrame", 4371, 3)};
    private static final ExifTag[] PEF_TAGS = {new ExifTag("ColorSpace", 55, 3)};
    static final ExifTag[][] EXIF_TAGS = {IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS};
    private static final ExifTag[] EXIF_POINTER_TAGS = {new ExifTag(TAG_SUB_IFD_POINTER, 330, 4), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, 4), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, 4), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, 1), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, 1)};
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag("JPEGInterchangeFormat", InputDeviceCompat.SOURCE_DPAD, 4);
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag("JPEGInterchangeFormatLength", 514, 4);
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
    private static final HashSet<String> sTagSetForCompatibility = new HashSet<>(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
    private static final HashMap<Integer, Integer> sExifPointerTagMap = new HashMap<>();
    static final Charset ASCII = Charset.forName(CharEncoding.US_ASCII);
    static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ASCII);
    private static final byte[] IDENTIFIER_XMP_APP1 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(ASCII);
    private static SimpleDateFormat sFormatterPrimary = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExifStreamType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IfdType {
    }

    static {
        sFormatterPrimary.setTimeZone(TimeZone.getTimeZone("UTC"));
        sFormatterSecondary = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        sFormatterSecondary.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int ifdType = 0; ifdType < EXIF_TAGS.length; ifdType++) {
            sExifTagMapsForReading[ifdType] = new HashMap<>();
            sExifTagMapsForWriting[ifdType] = new HashMap<>();
            for (ExifTag tag : EXIF_TAGS[ifdType]) {
                sExifTagMapsForReading[ifdType].put(Integer.valueOf(tag.number), tag);
                sExifTagMapsForWriting[ifdType].put(tag.name, tag);
            }
        }
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[0].number), 5);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[1].number), 1);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[2].number), 2);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[3].number), 3);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[4].number), 7);
        sExifPointerTagMap.put(Integer.valueOf(EXIF_POINTER_TAGS[5].number), 8);
        NON_ZERO_TIME_PATTERN = Pattern.compile(".*[1-9].*");
        GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        Rational(double value) {
            this((long) (10000.0d * value), WorkRequest.MIN_BACKOFF_MILLIS);
        }

        Rational(long numerator, long denominator) {
            if (denominator == 0) {
                this.numerator = 0L;
                this.denominator = 1L;
            } else {
                this.numerator = numerator;
                this.denominator = denominator;
            }
        }

        public String toString() {
            return this.numerator + "/" + this.denominator;
        }

        public double calculate() {
            return this.numerator / this.denominator;
        }
    }

    private static class ExifAttribute {
        public static final long BYTES_OFFSET_UNKNOWN = -1;
        public final byte[] bytes;
        public final long bytesOffset;
        public final int format;
        public final int numberOfComponents;

        ExifAttribute(int format, int numberOfComponents, byte[] bytes) {
            this(format, numberOfComponents, -1L, bytes);
        }

        ExifAttribute(int format, int numberOfComponents, long bytesOffset, byte[] bytes) {
            this.format = format;
            this.numberOfComponents = numberOfComponents;
            this.bytesOffset = bytesOffset;
            this.bytes = bytes;
        }

        public static ExifAttribute createUShort(int[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[3] * values.length]);
            buffer.order(byteOrder);
            for (int value : values) {
                buffer.putShort((short) value);
            }
            return new ExifAttribute(3, values.length, buffer.array());
        }

        public static ExifAttribute createUShort(int value, ByteOrder byteOrder) {
            return createUShort(new int[]{value}, byteOrder);
        }

        public static ExifAttribute createULong(long[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[4] * values.length]);
            buffer.order(byteOrder);
            for (long value : values) {
                buffer.putInt((int) value);
            }
            return new ExifAttribute(4, values.length, buffer.array());
        }

        public static ExifAttribute createULong(long value, ByteOrder byteOrder) {
            return createULong(new long[]{value}, byteOrder);
        }

        public static ExifAttribute createSLong(int[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[9] * values.length]);
            buffer.order(byteOrder);
            for (int value : values) {
                buffer.putInt(value);
            }
            return new ExifAttribute(9, values.length, buffer.array());
        }

        public static ExifAttribute createSLong(int value, ByteOrder byteOrder) {
            return createSLong(new int[]{value}, byteOrder);
        }

        public static ExifAttribute createByte(String value) {
            if (value.length() == 1 && value.charAt(0) >= '0' && value.charAt(0) <= '1') {
                byte[] bytes = {(byte) (value.charAt(0) - '0')};
                return new ExifAttribute(1, bytes.length, bytes);
            }
            byte[] ascii = value.getBytes(ExifInterface.ASCII);
            return new ExifAttribute(1, ascii.length, ascii);
        }

        public static ExifAttribute createString(String value) {
            byte[] ascii = (value + (char) 0).getBytes(ExifInterface.ASCII);
            return new ExifAttribute(2, ascii.length, ascii);
        }

        public static ExifAttribute createURational(Rational[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[5] * values.length]);
            buffer.order(byteOrder);
            for (Rational value : values) {
                buffer.putInt((int) value.numerator);
                buffer.putInt((int) value.denominator);
            }
            return new ExifAttribute(5, values.length, buffer.array());
        }

        public static ExifAttribute createURational(Rational value, ByteOrder byteOrder) {
            return createURational(new Rational[]{value}, byteOrder);
        }

        public static ExifAttribute createSRational(Rational[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[10] * values.length]);
            buffer.order(byteOrder);
            for (Rational value : values) {
                buffer.putInt((int) value.numerator);
                buffer.putInt((int) value.denominator);
            }
            return new ExifAttribute(10, values.length, buffer.array());
        }

        public static ExifAttribute createSRational(Rational value, ByteOrder byteOrder) {
            return createSRational(new Rational[]{value}, byteOrder);
        }

        public static ExifAttribute createDouble(double[] values, ByteOrder byteOrder) {
            ByteBuffer buffer = ByteBuffer.wrap(new byte[ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[12] * values.length]);
            buffer.order(byteOrder);
            for (double value : values) {
                buffer.putDouble(value);
            }
            return new ExifAttribute(12, values.length, buffer.array());
        }

        public static ExifAttribute createDouble(double value, ByteOrder byteOrder) {
            return createDouble(new double[]{value}, byteOrder);
        }

        public String toString() {
            return "(" + ExifInterface.IFD_FORMAT_NAMES[this.format] + ", data length:" + this.bytes.length + ")";
        }

        Object getValue(ByteOrder byteOrder) {
            int ch;
            ByteOrderedDataInputStream inputStream = null;
            try {
                try {
                    ByteOrderedDataInputStream inputStream2 = new ByteOrderedDataInputStream(this.bytes);
                    inputStream2.setByteOrder(byteOrder);
                    switch (this.format) {
                        case 1:
                        case 6:
                            if (this.bytes.length != 1 || this.bytes[0] < 0 || this.bytes[0] > 1) {
                                String str = new String(this.bytes, ExifInterface.ASCII);
                                try {
                                    inputStream2.close();
                                } catch (IOException e) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e);
                                }
                                return str;
                            }
                            String str2 = new String(new char[]{(char) (this.bytes[0] + 48)});
                            try {
                                inputStream2.close();
                            } catch (IOException e2) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e2);
                            }
                            return str2;
                        case 2:
                        case 7:
                            int index = 0;
                            if (this.numberOfComponents >= ExifInterface.EXIF_ASCII_PREFIX.length) {
                                boolean same = true;
                                int i = 0;
                                while (true) {
                                    if (i < ExifInterface.EXIF_ASCII_PREFIX.length) {
                                        if (this.bytes[i] != ExifInterface.EXIF_ASCII_PREFIX[i]) {
                                            same = false;
                                        } else {
                                            i++;
                                        }
                                    }
                                }
                                if (same) {
                                    index = ExifInterface.EXIF_ASCII_PREFIX.length;
                                }
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            while (index < this.numberOfComponents && (ch = this.bytes[index]) != 0) {
                                if (ch >= 32) {
                                    stringBuilder.append((char) ch);
                                } else {
                                    stringBuilder.append('?');
                                }
                                index++;
                            }
                            String sb = stringBuilder.toString();
                            try {
                                inputStream2.close();
                            } catch (IOException e3) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e3);
                            }
                            return sb;
                        case 3:
                            int[] values = new int[this.numberOfComponents];
                            for (int i2 = 0; i2 < this.numberOfComponents; i2++) {
                                values[i2] = inputStream2.readUnsignedShort();
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e4) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e4);
                            }
                            return values;
                        case 4:
                            long[] values2 = new long[this.numberOfComponents];
                            for (int i3 = 0; i3 < this.numberOfComponents; i3++) {
                                values2[i3] = inputStream2.readUnsignedInt();
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e5) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e5);
                            }
                            return values2;
                        case 5:
                            Rational[] values3 = new Rational[this.numberOfComponents];
                            for (int i4 = 0; i4 < this.numberOfComponents; i4++) {
                                long numerator = inputStream2.readUnsignedInt();
                                long denominator = inputStream2.readUnsignedInt();
                                values3[i4] = new Rational(numerator, denominator);
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e6) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e6);
                            }
                            return values3;
                        case 8:
                            int[] values4 = new int[this.numberOfComponents];
                            for (int i5 = 0; i5 < this.numberOfComponents; i5++) {
                                values4[i5] = inputStream2.readShort();
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e7) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e7);
                            }
                            return values4;
                        case 9:
                            int[] values5 = new int[this.numberOfComponents];
                            for (int i6 = 0; i6 < this.numberOfComponents; i6++) {
                                values5[i6] = inputStream2.readInt();
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e8) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e8);
                            }
                            return values5;
                        case 10:
                            Rational[] values6 = new Rational[this.numberOfComponents];
                            for (int i7 = 0; i7 < this.numberOfComponents; i7++) {
                                long numerator2 = inputStream2.readInt();
                                long denominator2 = inputStream2.readInt();
                                values6[i7] = new Rational(numerator2, denominator2);
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e9) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e9);
                            }
                            return values6;
                        case 11:
                            double[] values7 = new double[this.numberOfComponents];
                            for (int i8 = 0; i8 < this.numberOfComponents; i8++) {
                                values7[i8] = inputStream2.readFloat();
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e10) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e10);
                            }
                            return values7;
                        case 12:
                            double[] values8 = new double[this.numberOfComponents];
                            for (int i9 = 0; i9 < this.numberOfComponents; i9++) {
                                values8[i9] = inputStream2.readDouble();
                            }
                            try {
                                inputStream2.close();
                            } catch (IOException e11) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e11);
                            }
                            return values8;
                        default:
                            try {
                                inputStream2.close();
                            } catch (IOException e12) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e12);
                            }
                            return null;
                    }
                } catch (IOException e13) {
                    Log.w(ExifInterface.TAG, "IOException occurred during reading a value", e13);
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e14) {
                            Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e14);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e15) {
                        Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e15);
                    }
                }
                throw th;
            }
        }

        public double getDoubleValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a double value");
            }
            if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
            if (value instanceof long[]) {
                long[] array = (long[]) value;
                if (array.length == 1) {
                    return array[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof int[]) {
                int[] array2 = (int[]) value;
                if (array2.length == 1) {
                    return array2[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof double[]) {
                double[] array3 = (double[]) value;
                if (array3.length == 1) {
                    return array3[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof Rational[]) {
                Rational[] array4 = (Rational[]) value;
                if (array4.length == 1) {
                    return array4[0].calculate();
                }
                throw new NumberFormatException("There are more than one component");
            }
            throw new NumberFormatException("Couldn't find a double value");
        }

        public int getIntValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                throw new NumberFormatException("NULL can't be converted to a integer value");
            }
            if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            if (value instanceof long[]) {
                long[] array = (long[]) value;
                if (array.length == 1) {
                    return (int) array[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            if (value instanceof int[]) {
                int[] array2 = (int[]) value;
                if (array2.length == 1) {
                    return array2[0];
                }
                throw new NumberFormatException("There are more than one component");
            }
            throw new NumberFormatException("Couldn't find a integer value");
        }

        public String getStringValue(ByteOrder byteOrder) {
            Object value = getValue(byteOrder);
            if (value == null) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (value instanceof long[]) {
                long[] array = (long[]) value;
                for (int i = 0; i < array.length; i++) {
                    stringBuilder.append(array[i]);
                    if (i + 1 != array.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            }
            if (value instanceof int[]) {
                int[] array2 = (int[]) value;
                for (int i2 = 0; i2 < array2.length; i2++) {
                    stringBuilder.append(array2[i2]);
                    if (i2 + 1 != array2.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            }
            if (value instanceof double[]) {
                double[] array3 = (double[]) value;
                for (int i3 = 0; i3 < array3.length; i3++) {
                    stringBuilder.append(array3[i3]);
                    if (i3 + 1 != array3.length) {
                        stringBuilder.append(",");
                    }
                }
                return stringBuilder.toString();
            }
            if (!(value instanceof Rational[])) {
                return null;
            }
            Rational[] array4 = (Rational[]) value;
            for (int i4 = 0; i4 < array4.length; i4++) {
                stringBuilder.append(array4[i4].numerator);
                stringBuilder.append('/');
                stringBuilder.append(array4[i4].denominator);
                if (i4 + 1 != array4.length) {
                    stringBuilder.append(",");
                }
            }
            return stringBuilder.toString();
        }

        public int size() {
            return ExifInterface.IFD_FORMAT_BYTES_PER_FORMAT[this.format] * this.numberOfComponents;
        }
    }

    static class ExifTag {
        public final String name;
        public final int number;
        public final int primaryFormat;
        public final int secondaryFormat;

        ExifTag(String name, int number, int format) {
            this.name = name;
            this.number = number;
            this.primaryFormat = format;
            this.secondaryFormat = -1;
        }

        ExifTag(String name, int number, int primaryFormat, int secondaryFormat) {
            this.name = name;
            this.number = number;
            this.primaryFormat = primaryFormat;
            this.secondaryFormat = secondaryFormat;
        }

        boolean isFormatCompatible(int format) {
            if (this.primaryFormat == 7 || format == 7 || this.primaryFormat == format || this.secondaryFormat == format) {
                return true;
            }
            if ((this.primaryFormat == 4 || this.secondaryFormat == 4) && format == 3) {
                return true;
            }
            if ((this.primaryFormat == 9 || this.secondaryFormat == 9) && format == 8) {
                return true;
            }
            return (this.primaryFormat == 12 || this.secondaryFormat == 12) && format == 11;
        }
    }

    public ExifInterface(File file) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }
        initForFilename(file.getAbsolutePath());
    }

    public ExifInterface(String filename) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (filename == null) {
            throw new NullPointerException("filename cannot be null");
        }
        initForFilename(filename);
    }

    public ExifInterface(FileDescriptor fileDescriptor) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (fileDescriptor == null) {
            throw new NullPointerException("fileDescriptor cannot be null");
        }
        this.mAssetInputStream = null;
        this.mFilename = null;
        boolean isFdDuped = false;
        if (isSeekableFD(fileDescriptor)) {
            this.mSeekableFileDescriptor = fileDescriptor;
            try {
                fileDescriptor = Os.dup(fileDescriptor);
                isFdDuped = true;
            } catch (Exception e) {
                throw new IOException("Failed to duplicate file descriptor", e);
            }
        } else {
            this.mSeekableFileDescriptor = null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileDescriptor);
            loadAttributes(in);
        } finally {
            closeQuietly(in);
            if (isFdDuped) {
                closeFileDescriptor(fileDescriptor);
            }
        }
    }

    public ExifInterface(InputStream inputStream) throws IOException {
        this(inputStream, 0);
    }

    public ExifInterface(InputStream inputStream, int streamType) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mAttributesOffsets = new HashSet(EXIF_TAGS.length);
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (inputStream == null) {
            throw new NullPointerException("inputStream cannot be null");
        }
        this.mFilename = null;
        boolean shouldBeExifDataOnly = streamType == 1;
        if (shouldBeExifDataOnly) {
            inputStream = new BufferedInputStream(inputStream, SIGNATURE_CHECK_SIZE);
            if (!isExifDataOnly((BufferedInputStream) inputStream)) {
                Log.w(TAG, "Given data does not follow the structure of an Exif-only data.");
                return;
            } else {
                this.mIsExifDataOnly = true;
                this.mAssetInputStream = null;
                this.mSeekableFileDescriptor = null;
            }
        } else if (inputStream instanceof AssetManager.AssetInputStream) {
            this.mAssetInputStream = (AssetManager.AssetInputStream) inputStream;
            this.mSeekableFileDescriptor = null;
        } else if ((inputStream instanceof FileInputStream) && isSeekableFD(((FileInputStream) inputStream).getFD())) {
            this.mAssetInputStream = null;
            this.mSeekableFileDescriptor = ((FileInputStream) inputStream).getFD();
        } else {
            this.mAssetInputStream = null;
            this.mSeekableFileDescriptor = null;
        }
        loadAttributes(inputStream);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean isSupportedMimeType(String mimeType) {
        char c;
        if (mimeType == null) {
            throw new NullPointerException("mimeType shouldn't be null");
        }
        String lowerCase = mimeType.toLowerCase(Locale.ROOT);
        switch (lowerCase.hashCode()) {
            case -1875291391:
                if (lowerCase.equals("image/x-fuji-raf")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1635437028:
                if (lowerCase.equals("image/x-samsung-srw")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1594371159:
                if (lowerCase.equals("image/x-sony-arw")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1487464693:
                if (lowerCase.equals("image/heic")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1487464690:
                if (lowerCase.equals("image/heif")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1487394660:
                if (lowerCase.equals("image/jpeg")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1487018032:
                if (lowerCase.equals("image/webp")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1423313290:
                if (lowerCase.equals("image/x-adobe-dng")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -985160897:
                if (lowerCase.equals("image/x-panasonic-rw2")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -879258763:
                if (lowerCase.equals("image/png")) {
                    c = CharUtils.CR;
                    break;
                }
                c = 65535;
                break;
            case -332763809:
                if (lowerCase.equals("image/x-pentax-pef")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1378106698:
                if (lowerCase.equals("image/x-olympus-orf")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2099152104:
                if (lowerCase.equals("image/x-nikon-nef")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2099152524:
                if (lowerCase.equals("image/x-nikon-nrw")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2111234748:
                if (lowerCase.equals("image/x-canon-cr2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
                return true;
            default:
                return false;
        }
    }

    private ExifAttribute getExifAttribute(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        if ("ISOSpeedRatings".equals(tag)) {
            if (DEBUG) {
                Log.d(TAG, "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            tag = "PhotographicSensitivity";
        }
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            ExifAttribute value = this.mAttributes[i].get(tag);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    public String getAttribute(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        ExifAttribute attribute = getExifAttribute(tag);
        if (attribute == null) {
            return null;
        }
        if (!sTagSetForCompatibility.contains(tag)) {
            return attribute.getStringValue(this.mExifByteOrder);
        }
        if (tag.equals("GPSTimeStamp")) {
            if (attribute.format != 5 && attribute.format != 10) {
                Log.w(TAG, "GPS Timestamp format is not rational. format=" + attribute.format);
                return null;
            }
            Rational[] array = (Rational[]) attribute.getValue(this.mExifByteOrder);
            if (array == null || array.length != 3) {
                Log.w(TAG, "Invalid GPS Timestamp array. array=" + Arrays.toString(array));
                return null;
            }
            return String.format("%02d:%02d:%02d", Integer.valueOf((int) (array[0].numerator / array[0].denominator)), Integer.valueOf((int) (array[1].numerator / array[1].denominator)), Integer.valueOf((int) (array[2].numerator / array[2].denominator)));
        }
        try {
            return Double.toString(attribute.getDoubleValue(this.mExifByteOrder));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int getAttributeInt(String tag, int defaultValue) {
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        ExifAttribute exifAttribute = getExifAttribute(tag);
        if (exifAttribute == null) {
            return defaultValue;
        }
        try {
            return exifAttribute.getIntValue(this.mExifByteOrder);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double getAttributeDouble(String tag, double defaultValue) {
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        ExifAttribute exifAttribute = getExifAttribute(tag);
        if (exifAttribute == null) {
            return defaultValue;
        }
        try {
            return exifAttribute.getDoubleValue(this.mExifByteOrder);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setAttribute(String tag, String value) {
        String tag2;
        int i;
        int dataFormat;
        String value2 = value;
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        if (("DateTime".equals(tag) || "DateTimeOriginal".equals(tag) || "DateTimeDigitized".equals(tag)) && value2 != null) {
            boolean isPrimaryFormat = DATETIME_PRIMARY_FORMAT_PATTERN.matcher(value2).find();
            boolean isSecondaryFormat = DATETIME_SECONDARY_FORMAT_PATTERN.matcher(value2).find();
            if (value.length() != 19 || (!isPrimaryFormat && !isSecondaryFormat)) {
                Log.w(TAG, "Invalid value for " + tag + " : " + value2);
                return;
            } else if (isSecondaryFormat) {
                value2 = value2.replaceAll("-", ":");
            }
        }
        if (!"ISOSpeedRatings".equals(tag)) {
            tag2 = tag;
        } else {
            if (DEBUG) {
                Log.d(TAG, "setAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            tag2 = "PhotographicSensitivity";
        }
        int i2 = 2;
        int i3 = 1;
        if (value2 != null && sTagSetForCompatibility.contains(tag2)) {
            if (tag2.equals("GPSTimeStamp")) {
                Matcher m = GPS_TIMESTAMP_PATTERN.matcher(value2);
                if (!m.find()) {
                    Log.w(TAG, "Invalid value for " + tag2 + " : " + value2);
                    return;
                }
                value2 = Integer.parseInt(m.group(1)) + "/1," + Integer.parseInt(m.group(2)) + "/1," + Integer.parseInt(m.group(3)) + "/1";
            } else {
                try {
                    double doubleValue = Double.parseDouble(value2);
                    value2 = new Rational(doubleValue).toString();
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Invalid value for " + tag2 + " : " + value2);
                    return;
                }
            }
        }
        int i4 = 0;
        while (i4 < EXIF_TAGS.length) {
            if (i4 == 4 && !this.mHasThumbnail) {
                i = i3;
            } else {
                ExifTag exifTag = sExifTagMapsForWriting[i4].get(tag2);
                if (exifTag == null) {
                    i = i3;
                } else if (value2 == null) {
                    this.mAttributes[i4].remove(tag2);
                    i = i3;
                } else {
                    Pair<Integer, Integer> guess = guessDataFormat(value2);
                    int i5 = -1;
                    if (exifTag.primaryFormat == ((Integer) guess.first).intValue() || exifTag.primaryFormat == ((Integer) guess.second).intValue()) {
                        dataFormat = exifTag.primaryFormat;
                    } else if (exifTag.secondaryFormat != -1 && (exifTag.secondaryFormat == ((Integer) guess.first).intValue() || exifTag.secondaryFormat == ((Integer) guess.second).intValue())) {
                        dataFormat = exifTag.secondaryFormat;
                    } else {
                        int dataFormat2 = exifTag.primaryFormat;
                        if (dataFormat2 == i3 || exifTag.primaryFormat == 7 || exifTag.primaryFormat == i2) {
                            dataFormat = exifTag.primaryFormat;
                        } else if (!DEBUG) {
                            i = i3;
                        } else {
                            Log.d(TAG, "Given tag (" + tag2 + ") value didn't match with one of expected formats: " + IFD_FORMAT_NAMES[exifTag.primaryFormat] + (exifTag.secondaryFormat == -1 ? "" : ", " + IFD_FORMAT_NAMES[exifTag.secondaryFormat]) + " (guess: " + IFD_FORMAT_NAMES[((Integer) guess.first).intValue()] + (((Integer) guess.second).intValue() != -1 ? ", " + IFD_FORMAT_NAMES[((Integer) guess.second).intValue()] : "") + ")");
                            i = i3;
                        }
                    }
                    char c = 0;
                    String str = "/";
                    switch (dataFormat) {
                        case 1:
                            i = i3;
                            this.mAttributes[i4].put(tag2, ExifAttribute.createByte(value2));
                            break;
                        case 2:
                        case 7:
                            i = i3;
                            this.mAttributes[i4].put(tag2, ExifAttribute.createString(value2));
                            break;
                        case 3:
                            i = i3;
                            String[] values = value2.split(",", -1);
                            int[] intArray = new int[values.length];
                            for (int j = 0; j < values.length; j++) {
                                intArray[j] = Integer.parseInt(values[j]);
                            }
                            this.mAttributes[i4].put(tag2, ExifAttribute.createUShort(intArray, this.mExifByteOrder));
                            break;
                        case 4:
                            i = i3;
                            String[] values2 = value2.split(",", -1);
                            long[] longArray = new long[values2.length];
                            for (int j2 = 0; j2 < values2.length; j2++) {
                                longArray[j2] = Long.parseLong(values2[j2]);
                            }
                            this.mAttributes[i4].put(tag2, ExifAttribute.createULong(longArray, this.mExifByteOrder));
                            break;
                        case 5:
                            String[] values3 = value2.split(",", -1);
                            Rational[] rationalArray = new Rational[values3.length];
                            int j3 = 0;
                            while (j3 < values3.length) {
                                String[] numbers = values3[j3].split(str, -1);
                                rationalArray[j3] = new Rational((long) Double.parseDouble(numbers[0]), (long) Double.parseDouble(numbers[1]));
                                j3++;
                                str = str;
                            }
                            i = 1;
                            this.mAttributes[i4].put(tag2, ExifAttribute.createURational(rationalArray, this.mExifByteOrder));
                            break;
                        case 6:
                        case 8:
                        case 11:
                        default:
                            i = i3;
                            if (DEBUG) {
                                Log.d(TAG, "Data format isn't one of expected formats: " + dataFormat);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            String[] values4 = value2.split(",", -1);
                            int[] intArray2 = new int[values4.length];
                            for (int j4 = 0; j4 < values4.length; j4++) {
                                intArray2[j4] = Integer.parseInt(values4[j4]);
                            }
                            this.mAttributes[i4].put(tag2, ExifAttribute.createSLong(intArray2, this.mExifByteOrder));
                            i = 1;
                            break;
                        case 10:
                            String[] values5 = value2.split(",", -1);
                            Rational[] rationalArray2 = new Rational[values5.length];
                            int j5 = 0;
                            while (j5 < values5.length) {
                                String[] numbers2 = values5[j5].split("/", i5);
                                rationalArray2[j5] = new Rational((long) Double.parseDouble(numbers2[c]), (long) Double.parseDouble(numbers2[i3]));
                                j5++;
                                guess = guess;
                                i3 = 1;
                                c = 0;
                                i5 = -1;
                            }
                            this.mAttributes[i4].put(tag2, ExifAttribute.createSRational(rationalArray2, this.mExifByteOrder));
                            i = 1;
                            break;
                        case 12:
                            String[] values6 = value2.split(",", -1);
                            double[] doubleArray = new double[values6.length];
                            for (int j6 = 0; j6 < values6.length; j6++) {
                                doubleArray[j6] = Double.parseDouble(values6[j6]);
                            }
                            this.mAttributes[i4].put(tag2, ExifAttribute.createDouble(doubleArray, this.mExifByteOrder));
                            i = i3;
                            break;
                    }
                }
            }
            i4++;
            i3 = i;
            i2 = 2;
        }
    }

    public void resetOrientation() {
        setAttribute("Orientation", Integer.toString(1));
    }

    public void rotate(int degree) {
        int resultOrientation;
        if (degree % 90 != 0) {
            throw new IllegalArgumentException("degree should be a multiple of 90");
        }
        int currentOrientation = getAttributeInt("Orientation", 1);
        if (ROTATION_ORDER.contains(Integer.valueOf(currentOrientation))) {
            int currentIndex = ROTATION_ORDER.indexOf(Integer.valueOf(currentOrientation));
            int newIndex = ((degree / 90) + currentIndex) % 4;
            resultOrientation = ROTATION_ORDER.get(newIndex + (newIndex < 0 ? 4 : 0)).intValue();
        } else if (FLIPPED_ROTATION_ORDER.contains(Integer.valueOf(currentOrientation))) {
            int currentIndex2 = FLIPPED_ROTATION_ORDER.indexOf(Integer.valueOf(currentOrientation));
            int newIndex2 = ((degree / 90) + currentIndex2) % 4;
            resultOrientation = FLIPPED_ROTATION_ORDER.get(newIndex2 + (newIndex2 < 0 ? 4 : 0)).intValue();
        } else {
            resultOrientation = 0;
        }
        setAttribute("Orientation", Integer.toString(resultOrientation));
    }

    public void flipVertically() {
        int resultOrientation;
        int currentOrientation = getAttributeInt("Orientation", 1);
        switch (currentOrientation) {
            case 1:
                resultOrientation = 4;
                break;
            case 2:
                resultOrientation = 3;
                break;
            case 3:
                resultOrientation = 2;
                break;
            case 4:
                resultOrientation = 1;
                break;
            case 5:
                resultOrientation = 8;
                break;
            case 6:
                resultOrientation = 7;
                break;
            case 7:
                resultOrientation = 6;
                break;
            case 8:
                resultOrientation = 5;
                break;
            default:
                resultOrientation = 0;
                break;
        }
        setAttribute("Orientation", Integer.toString(resultOrientation));
    }

    public void flipHorizontally() {
        int resultOrientation;
        int currentOrientation = getAttributeInt("Orientation", 1);
        switch (currentOrientation) {
            case 1:
                resultOrientation = 2;
                break;
            case 2:
                resultOrientation = 1;
                break;
            case 3:
                resultOrientation = 4;
                break;
            case 4:
                resultOrientation = 3;
                break;
            case 5:
                resultOrientation = 6;
                break;
            case 6:
                resultOrientation = 5;
                break;
            case 7:
                resultOrientation = 8;
                break;
            case 8:
                resultOrientation = 7;
                break;
            default:
                resultOrientation = 0;
                break;
        }
        setAttribute("Orientation", Integer.toString(resultOrientation));
    }

    public boolean isFlipped() {
        int orientation = getAttributeInt("Orientation", 1);
        switch (orientation) {
            case 2:
            case 4:
            case 5:
            case 7:
                return true;
            case 3:
            case 6:
            default:
                return false;
        }
    }

    public int getRotationDegrees() {
        int orientation = getAttributeInt("Orientation", 1);
        switch (orientation) {
            case 3:
            case 4:
                return BarcodeUtils.ROTATION_180;
            case 5:
            case 8:
                return BarcodeUtils.ROTATION_270;
            case 6:
            case 7:
                return 90;
            default:
                return 0;
        }
    }

    private void removeAttribute(String tag) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            this.mAttributes[i].remove(tag);
        }
    }

    private void loadAttributes(InputStream in) {
        if (in == null) {
            throw new NullPointerException("inputstream shouldn't be null");
        }
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            try {
                try {
                    this.mAttributes[i] = new HashMap<>();
                } catch (IOException e) {
                    if (DEBUG) {
                        Log.w(TAG, "Invalid image: ExifInterface got an unsupported image format file(ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e);
                    }
                    addDefaultValuesForCompatibility();
                    if (!DEBUG) {
                        return;
                    }
                }
            } catch (Throwable th) {
                addDefaultValuesForCompatibility();
                if (DEBUG) {
                    printAttributes();
                }
                throw th;
            }
        }
        if (!this.mIsExifDataOnly) {
            in = new BufferedInputStream(in, SIGNATURE_CHECK_SIZE);
            this.mMimeType = getMimeType((BufferedInputStream) in);
        }
        ByteOrderedDataInputStream inputStream = new ByteOrderedDataInputStream(in);
        if (this.mIsExifDataOnly) {
            getStandaloneAttributes(inputStream);
        } else {
            switch (this.mMimeType) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 5:
                case 6:
                case 8:
                case 11:
                    getRawAttributes(inputStream);
                    break;
                case 4:
                    getJpegAttributes(inputStream, 0, 0);
                    addDefaultValuesForCompatibility();
                    if (DEBUG) {
                        printAttributes();
                        return;
                    }
                    return;
                case 7:
                    getOrfAttributes(inputStream);
                    break;
                case 9:
                    getRafAttributes(inputStream);
                    addDefaultValuesForCompatibility();
                    if (DEBUG) {
                        printAttributes();
                        return;
                    }
                    return;
                case 10:
                    getRw2Attributes(inputStream);
                    addDefaultValuesForCompatibility();
                    if (DEBUG) {
                        printAttributes();
                        return;
                    }
                    return;
                case 12:
                    getHeifAttributes(inputStream);
                    break;
                case 13:
                    getPngAttributes(inputStream);
                    addDefaultValuesForCompatibility();
                    if (DEBUG) {
                        printAttributes();
                        return;
                    }
                    return;
                case 14:
                    getWebpAttributes(inputStream);
                    addDefaultValuesForCompatibility();
                    if (DEBUG) {
                        printAttributes();
                        return;
                    }
                    return;
            }
        }
        inputStream.seek(this.mOffsetToExifData);
        setThumbnailData(inputStream);
        addDefaultValuesForCompatibility();
        if (!DEBUG) {
            return;
        }
        printAttributes();
    }

    private static boolean isSeekableFD(FileDescriptor fd) {
        try {
            Os.lseek(fd, 0L, OsConstants.SEEK_CUR);
            return true;
        } catch (Exception e) {
            if (DEBUG) {
                Log.d(TAG, "The file descriptor for the given input is not seekable");
                return false;
            }
            return false;
        }
    }

    private void printAttributes() {
        for (int i = 0; i < this.mAttributes.length; i++) {
            Log.d(TAG, "The size of tag group[" + i + "]: " + this.mAttributes[i].size());
            for (Map.Entry<String, ExifAttribute> entry : this.mAttributes[i].entrySet()) {
                ExifAttribute tagValue = entry.getValue();
                Log.d(TAG, "tagName: " + entry.getKey() + ", tagType: " + tagValue.toString() + ", tagValue: '" + tagValue.getStringValue(this.mExifByteOrder) + "'");
            }
        }
    }

    public void saveAttributes() throws IOException {
        FileOutputStream out;
        if (!isSupportedFormatForSavingAttributes()) {
            throw new IOException("ExifInterface only supports saving attributes on JPEG, PNG, or WebP formats.");
        }
        if (this.mSeekableFileDescriptor == null && this.mFilename == null) {
            throw new IOException("ExifInterface does not support saving attributes for the current input.");
        }
        this.mModified = true;
        this.mThumbnailBytes = getThumbnail();
        FileInputStream in = null;
        FileOutputStream out2 = null;
        try {
            try {
                File tempFile = File.createTempFile("temp", "tmp");
                if (this.mFilename != null) {
                    in = new FileInputStream(this.mFilename);
                } else {
                    Os.lseek(this.mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
                    in = new FileInputStream(this.mSeekableFileDescriptor);
                }
                out2 = new FileOutputStream(tempFile);
                copy(in, out2);
                closeQuietly(in);
                closeQuietly(out2);
                FileInputStream in2 = null;
                FileOutputStream out3 = null;
                BufferedInputStream bufferedIn = null;
                BufferedOutputStream bufferedOut = null;
                try {
                    try {
                        FileInputStream in3 = new FileInputStream(tempFile);
                        if (this.mFilename != null) {
                            out = new FileOutputStream(this.mFilename);
                        } else {
                            Os.lseek(this.mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
                            out = new FileOutputStream(this.mSeekableFileDescriptor);
                        }
                        bufferedIn = new BufferedInputStream(in3);
                        bufferedOut = new BufferedOutputStream(out);
                        if (this.mMimeType == 4) {
                            saveJpegAttributes(bufferedIn, bufferedOut);
                        } else if (this.mMimeType == 13) {
                            savePngAttributes(bufferedIn, bufferedOut);
                        } else if (this.mMimeType == 14) {
                            saveWebpAttributes(bufferedIn, bufferedOut);
                        }
                        this.mThumbnailBytes = null;
                    } catch (Exception e) {
                        try {
                            try {
                                in2 = new FileInputStream(tempFile);
                                if (this.mFilename != null) {
                                    out3 = new FileOutputStream(this.mFilename);
                                } else {
                                    Os.lseek(this.mSeekableFileDescriptor, 0L, OsConstants.SEEK_SET);
                                    out3 = new FileOutputStream(this.mSeekableFileDescriptor);
                                }
                                copy(in2, out3);
                                closeQuietly(in2);
                                closeQuietly(out3);
                                throw new IOException("Failed to save new file", e);
                            } finally {
                            }
                        } catch (Exception exception) {
                            throw new IOException("Failed to save new file. Original file is stored in " + tempFile.getAbsolutePath(), exception);
                        }
                    }
                } finally {
                    closeQuietly(bufferedIn);
                    closeQuietly(bufferedOut);
                    if (0 == 0) {
                        tempFile.delete();
                    }
                }
            } catch (Exception e2) {
                throw new IOException("Failed to copy original file to temp file", e2);
            }
        } finally {
        }
    }

    public boolean hasThumbnail() {
        return this.mHasThumbnail;
    }

    public boolean hasAttribute(String tag) {
        return getExifAttribute(tag) != null;
    }

    public byte[] getThumbnail() {
        if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
            return getThumbnailBytes();
        }
        return null;
    }

    public byte[] getThumbnailBytes() {
        InputStream in;
        if (!this.mHasThumbnail) {
            return null;
        }
        if (this.mThumbnailBytes != null) {
            return this.mThumbnailBytes;
        }
        FileDescriptor newFileDescriptor = null;
        try {
            try {
                if (this.mAssetInputStream != null) {
                    in = this.mAssetInputStream;
                    if (!in.markSupported()) {
                        Log.d(TAG, "Cannot read thumbnail from inputstream without mark/reset support");
                        closeQuietly(in);
                        if (0 != 0) {
                            closeFileDescriptor(null);
                        }
                        return null;
                    }
                    in.reset();
                } else if (this.mFilename != null) {
                    in = new FileInputStream(this.mFilename);
                } else {
                    newFileDescriptor = Os.dup(this.mSeekableFileDescriptor);
                    Os.lseek(newFileDescriptor, 0L, OsConstants.SEEK_SET);
                    in = new FileInputStream(newFileDescriptor);
                }
                if (in == null) {
                    throw new FileNotFoundException();
                }
                if (in.skip(this.mThumbnailOffset + this.mOffsetToExifData) != this.mThumbnailOffset + this.mOffsetToExifData) {
                    throw new IOException("Corrupted image");
                }
                byte[] buffer = new byte[this.mThumbnailLength];
                if (in.read(buffer) != this.mThumbnailLength) {
                    throw new IOException("Corrupted image");
                }
                this.mThumbnailBytes = buffer;
                closeQuietly(in);
                if (newFileDescriptor != null) {
                    closeFileDescriptor(newFileDescriptor);
                }
                return buffer;
            } catch (Exception e) {
                Log.d(TAG, "Encountered exception while getting thumbnail", e);
                closeQuietly(null);
                if (0 != 0) {
                    closeFileDescriptor(null);
                }
                return null;
            }
        } catch (Throwable th) {
            closeQuietly(null);
            if (0 != 0) {
                closeFileDescriptor(null);
            }
            throw th;
        }
    }

    public Bitmap getThumbnailBitmap() {
        if (!this.mHasThumbnail) {
            return null;
        }
        if (this.mThumbnailBytes == null) {
            this.mThumbnailBytes = getThumbnailBytes();
        }
        if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
            return BitmapFactory.decodeByteArray(this.mThumbnailBytes, 0, this.mThumbnailLength);
        }
        if (this.mThumbnailCompression == 1) {
            int[] rgbValues = new int[this.mThumbnailBytes.length / 3];
            for (int i = 0; i < rgbValues.length; i++) {
                rgbValues[i] = (this.mThumbnailBytes[i * 3] << Ascii.DLE) + 0 + (this.mThumbnailBytes[(i * 3) + 1] << 8) + this.mThumbnailBytes[(i * 3) + 2];
            }
            ExifAttribute imageLengthAttribute = this.mAttributes[4].get("ImageLength");
            ExifAttribute imageWidthAttribute = this.mAttributes[4].get("ImageWidth");
            if (imageLengthAttribute != null && imageWidthAttribute != null) {
                int imageLength = imageLengthAttribute.getIntValue(this.mExifByteOrder);
                int imageWidth = imageWidthAttribute.getIntValue(this.mExifByteOrder);
                return Bitmap.createBitmap(rgbValues, imageWidth, imageLength, Bitmap.Config.ARGB_8888);
            }
        }
        return null;
    }

    public boolean isThumbnailCompressed() {
        if (this.mHasThumbnail) {
            return this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7;
        }
        return false;
    }

    public long[] getThumbnailRange() {
        if (this.mModified) {
            throw new IllegalStateException("The underlying file has been modified since being parsed");
        }
        if (!this.mHasThumbnail) {
            return null;
        }
        if (!this.mHasThumbnailStrips || this.mAreThumbnailStripsConsecutive) {
            return new long[]{this.mThumbnailOffset + this.mOffsetToExifData, this.mThumbnailLength};
        }
        return null;
    }

    public long[] getAttributeRange(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        if (this.mModified) {
            throw new IllegalStateException("The underlying file has been modified since being parsed");
        }
        ExifAttribute attribute = getExifAttribute(tag);
        if (attribute != null) {
            return new long[]{attribute.bytesOffset, attribute.bytes.length};
        }
        return null;
    }

    public byte[] getAttributeBytes(String tag) {
        if (tag == null) {
            throw new NullPointerException("tag shouldn't be null");
        }
        ExifAttribute attribute = getExifAttribute(tag);
        if (attribute != null) {
            return attribute.bytes;
        }
        return null;
    }

    @Deprecated
    public boolean getLatLong(float[] output) {
        double[] latLong = getLatLong();
        if (latLong == null) {
            return false;
        }
        output[0] = (float) latLong[0];
        output[1] = (float) latLong[1];
        return true;
    }

    public double[] getLatLong() {
        String latValue = getAttribute("GPSLatitude");
        String latRef = getAttribute("GPSLatitudeRef");
        String lngValue = getAttribute("GPSLongitude");
        String lngRef = getAttribute("GPSLongitudeRef");
        if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
            try {
                double latitude = convertRationalLatLonToDouble(latValue, latRef);
                double longitude = convertRationalLatLonToDouble(lngValue, lngRef);
                return new double[]{latitude, longitude};
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Latitude/longitude values are not parsable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", latValue, latRef, lngValue, lngRef));
                return null;
            }
        }
        return null;
    }

    public void setGpsInfo(Location location) {
        if (location == null) {
            return;
        }
        setAttribute("GPSProcessingMethod", location.getProvider());
        setLatLong(location.getLatitude(), location.getLongitude());
        setAltitude(location.getAltitude());
        setAttribute("GPSSpeedRef", "K");
        setAttribute("GPSSpeed", new Rational((location.getSpeed() * TimeUnit.HOURS.toSeconds(1L)) / 1000.0f).toString());
        String[] dateTime = sFormatterPrimary.format(new Date(location.getTime())).split("\\s+", -1);
        setAttribute("GPSDateStamp", dateTime[0]);
        setAttribute("GPSTimeStamp", dateTime[1]);
    }

    public void setLatLong(double latitude, double longitude) {
        if (latitude < -90.0d || latitude > 90.0d || Double.isNaN(latitude)) {
            throw new IllegalArgumentException("Latitude value " + latitude + " is not valid.");
        }
        if (longitude < -180.0d || longitude > 180.0d || Double.isNaN(longitude)) {
            throw new IllegalArgumentException("Longitude value " + longitude + " is not valid.");
        }
        setAttribute("GPSLatitudeRef", latitude >= Utils.DOUBLE_EPSILON ? "N" : "S");
        setAttribute("GPSLatitude", convertDecimalDegree(Math.abs(latitude)));
        setAttribute("GPSLongitudeRef", longitude >= Utils.DOUBLE_EPSILON ? "E" : "W");
        setAttribute("GPSLongitude", convertDecimalDegree(Math.abs(longitude)));
    }

    public double getAltitude(double defaultValue) {
        double altitude = getAttributeDouble("GPSAltitude", -1.0d);
        int ref = getAttributeInt("GPSAltitudeRef", -1);
        if (altitude < Utils.DOUBLE_EPSILON || ref < 0) {
            return defaultValue;
        }
        return (ref != 1 ? 1 : -1) * altitude;
    }

    public void setAltitude(double altitude) {
        String ref = altitude >= Utils.DOUBLE_EPSILON ? "0" : "1";
        setAttribute("GPSAltitude", new Rational(Math.abs(altitude)).toString());
        setAttribute("GPSAltitudeRef", ref);
    }

    public void setDateTime(Long timeStamp) {
        long sub = timeStamp.longValue() % 1000;
        setAttribute("DateTime", sFormatterPrimary.format(new Date(timeStamp.longValue())));
        setAttribute("SubSecTime", Long.toString(sub));
    }

    public Long getDateTime() {
        return parseDateTime(getAttribute("DateTime"), getAttribute("SubSecTime"), getAttribute(TAG_OFFSET_TIME));
    }

    public Long getDateTimeDigitized() {
        return parseDateTime(getAttribute("DateTimeDigitized"), getAttribute("SubSecTimeDigitized"), getAttribute(TAG_OFFSET_TIME_DIGITIZED));
    }

    public Long getDateTimeOriginal() {
        return parseDateTime(getAttribute("DateTimeOriginal"), getAttribute("SubSecTimeOriginal"), getAttribute(TAG_OFFSET_TIME_ORIGINAL));
    }

    private static Long parseDateTime(String dateTimeString, String subSecs, String offsetString) {
        if (dateTimeString == null || !NON_ZERO_TIME_PATTERN.matcher(dateTimeString).matches()) {
            return null;
        }
        ParsePosition pos = new ParsePosition(0);
        try {
            Date dateTime = sFormatterPrimary.parse(dateTimeString, pos);
            if (dateTime == null && (dateTime = sFormatterSecondary.parse(dateTimeString, pos)) == null) {
                return null;
            }
            long msecs = dateTime.getTime();
            if (offsetString != null) {
                String sign = offsetString.substring(0, 1);
                int hour = Integer.parseInt(offsetString.substring(1, 3));
                int min = Integer.parseInt(offsetString.substring(4, 6));
                if ((Marker.ANY_NON_NULL_MARKER.equals(sign) || "-".equals(sign)) && ":".equals(offsetString.substring(3, 4)) && hour <= 14) {
                    msecs += ((hour * 60) + min) * 60 * 1000 * ("-".equals(sign) ? 1 : -1);
                }
            }
            if (subSecs != null) {
                try {
                    long sub = Long.parseLong(subSecs);
                    while (sub > 1000) {
                        sub /= 10;
                    }
                    msecs += sub;
                } catch (NumberFormatException e) {
                }
            }
            return Long.valueOf(msecs);
        } catch (IllegalArgumentException e2) {
            return null;
        }
    }

    public Long getGpsDateTime() {
        String date = getAttribute("GPSDateStamp");
        String time = getAttribute("GPSTimeStamp");
        if (date == null || time == null || (!NON_ZERO_TIME_PATTERN.matcher(date).matches() && !NON_ZERO_TIME_PATTERN.matcher(time).matches())) {
            return null;
        }
        String dateTimeString = date + ' ' + time;
        ParsePosition pos = new ParsePosition(0);
        try {
            Date dateTime = sFormatterPrimary.parse(dateTimeString, pos);
            if (dateTime == null && (dateTime = sFormatterSecondary.parse(dateTimeString, pos)) == null) {
                return null;
            }
            return Long.valueOf(dateTime.getTime());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void initForFilename(String filename) throws IOException {
        if (filename == null) {
            throw new NullPointerException("filename cannot be null");
        }
        FileInputStream in = null;
        this.mAssetInputStream = null;
        this.mFilename = filename;
        try {
            in = new FileInputStream(filename);
            if (isSeekableFD(in.getFD())) {
                this.mSeekableFileDescriptor = in.getFD();
            } else {
                this.mSeekableFileDescriptor = null;
            }
            loadAttributes(in);
        } finally {
            closeQuietly(in);
        }
    }

    private static double convertRationalLatLonToDouble(String rationalString, String ref) {
        try {
            String[] parts = rationalString.split(",", -1);
            String[] pair = parts[0].split("/", -1);
            double degrees = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());
            String[] pair2 = parts[1].split("/", -1);
            double minutes = Double.parseDouble(pair2[0].trim()) / Double.parseDouble(pair2[1].trim());
            String[] pair3 = parts[2].split("/", -1);
            double seconds = Double.parseDouble(pair3[0].trim()) / Double.parseDouble(pair3[1].trim());
            double result = (minutes / 60.0d) + degrees + (seconds / 3600.0d);
            if (!ref.equals("S") && !ref.equals("W")) {
                if (!ref.equals("N") && !ref.equals("E")) {
                    throw new IllegalArgumentException();
                }
                return result;
            }
            return -result;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private String convertDecimalDegree(double decimalDegree) {
        long degrees = (long) decimalDegree;
        long minutes = (long) ((decimalDegree - degrees) * 60.0d);
        long seconds = Math.round(((decimalDegree - degrees) - (minutes / 60.0d)) * 3600.0d * 1.0E7d);
        return degrees + "/1," + minutes + "/1," + seconds + "/10000000";
    }

    private int getMimeType(BufferedInputStream in) throws IOException {
        in.mark(SIGNATURE_CHECK_SIZE);
        byte[] signatureCheckBytes = new byte[SIGNATURE_CHECK_SIZE];
        in.read(signatureCheckBytes);
        in.reset();
        if (isJpegFormat(signatureCheckBytes)) {
            return 4;
        }
        if (isRafFormat(signatureCheckBytes)) {
            return 9;
        }
        if (isHeifFormat(signatureCheckBytes)) {
            return 12;
        }
        if (isOrfFormat(signatureCheckBytes)) {
            return 7;
        }
        if (isRw2Format(signatureCheckBytes)) {
            return 10;
        }
        if (isPngFormat(signatureCheckBytes)) {
            return 13;
        }
        if (isWebpFormat(signatureCheckBytes)) {
            return 14;
        }
        return 0;
    }

    private static boolean isJpegFormat(byte[] signatureCheckBytes) throws IOException {
        for (int i = 0; i < JPEG_SIGNATURE.length; i++) {
            if (signatureCheckBytes[i] != JPEG_SIGNATURE[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isRafFormat(byte[] signatureCheckBytes) throws IOException {
        byte[] rafSignatureBytes = RAF_SIGNATURE.getBytes(Charset.defaultCharset());
        for (int i = 0; i < rafSignatureBytes.length; i++) {
            if (signatureCheckBytes[i] != rafSignatureBytes[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isHeifFormat(byte[] signatureCheckBytes) throws IOException {
        long chunkSize;
        byte[] chunkType;
        ByteOrderedDataInputStream signatureInputStream = null;
        try {
            try {
                signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
                chunkSize = signatureInputStream.readInt();
                chunkType = new byte[4];
                signatureInputStream.read(chunkType);
            } catch (Exception e) {
                if (DEBUG) {
                    Log.d(TAG, "Exception parsing HEIF file type box.", e);
                }
                if (signatureInputStream == null) {
                }
            }
            if (!Arrays.equals(chunkType, HEIF_TYPE_FTYP)) {
                signatureInputStream.close();
                return false;
            }
            long chunkDataOffset = 8;
            if (chunkSize == 1) {
                chunkSize = signatureInputStream.readLong();
                if (chunkSize < 16) {
                    signatureInputStream.close();
                    return false;
                }
                chunkDataOffset = 8 + 8;
            }
            if (chunkSize > signatureCheckBytes.length) {
                chunkSize = signatureCheckBytes.length;
            }
            long chunkDataSize = chunkSize - chunkDataOffset;
            if (chunkDataSize < 8) {
                signatureInputStream.close();
                return false;
            }
            byte[] brand = new byte[4];
            boolean isMif1 = false;
            boolean isHeic = false;
            for (long i = 0; i < chunkDataSize / 4; i++) {
                if (signatureInputStream.read(brand) != brand.length) {
                    signatureInputStream.close();
                    return false;
                }
                if (i != 1) {
                    if (Arrays.equals(brand, HEIF_BRAND_MIF1)) {
                        isMif1 = true;
                    } else if (Arrays.equals(brand, HEIF_BRAND_HEIC)) {
                        isHeic = true;
                    }
                    if (isMif1 && isHeic) {
                        signatureInputStream.close();
                        return true;
                    }
                }
            }
            signatureInputStream.close();
            return false;
        } catch (Throwable th) {
            if (signatureInputStream != null) {
                signatureInputStream.close();
            }
            throw th;
        }
    }

    private boolean isOrfFormat(byte[] signatureCheckBytes) throws IOException {
        ByteOrderedDataInputStream signatureInputStream = null;
        try {
            signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
            this.mExifByteOrder = readByteOrder(signatureInputStream);
            signatureInputStream.setByteOrder(this.mExifByteOrder);
            short orfSignature = signatureInputStream.readShort();
            boolean z = orfSignature == 20306 || orfSignature == 21330;
            signatureInputStream.close();
            return z;
        } catch (Exception e) {
            if (signatureInputStream != null) {
                signatureInputStream.close();
            }
            return false;
        } catch (Throwable th) {
            if (signatureInputStream != null) {
                signatureInputStream.close();
            }
            throw th;
        }
    }

    private boolean isRw2Format(byte[] signatureCheckBytes) throws IOException {
        ByteOrderedDataInputStream signatureInputStream = null;
        try {
            signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
            this.mExifByteOrder = readByteOrder(signatureInputStream);
            signatureInputStream.setByteOrder(this.mExifByteOrder);
            short signatureByte = signatureInputStream.readShort();
            boolean z = signatureByte == 85;
            signatureInputStream.close();
            return z;
        } catch (Exception e) {
            if (signatureInputStream != null) {
                signatureInputStream.close();
            }
            return false;
        } catch (Throwable th) {
            if (signatureInputStream != null) {
                signatureInputStream.close();
            }
            throw th;
        }
    }

    private boolean isPngFormat(byte[] signatureCheckBytes) throws IOException {
        for (int i = 0; i < PNG_SIGNATURE.length; i++) {
            if (signatureCheckBytes[i] != PNG_SIGNATURE[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isWebpFormat(byte[] signatureCheckBytes) throws IOException {
        for (int i = 0; i < WEBP_SIGNATURE_1.length; i++) {
            if (signatureCheckBytes[i] != WEBP_SIGNATURE_1[i]) {
                return false;
            }
        }
        for (int i2 = 0; i2 < WEBP_SIGNATURE_2.length; i2++) {
            if (signatureCheckBytes[WEBP_SIGNATURE_1.length + i2 + 4] != WEBP_SIGNATURE_2[i2]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isExifDataOnly(BufferedInputStream in) throws IOException {
        in.mark(IDENTIFIER_EXIF_APP1.length);
        byte[] signatureCheckBytes = new byte[IDENTIFIER_EXIF_APP1.length];
        in.read(signatureCheckBytes);
        in.reset();
        for (int i = 0; i < IDENTIFIER_EXIF_APP1.length; i++) {
            if (signatureCheckBytes[i] != IDENTIFIER_EXIF_APP1[i]) {
                return false;
            }
        }
        return true;
    }

    private void getJpegAttributes(ByteOrderedDataInputStream in, int offsetToJpeg, int imageType) throws IOException {
        String str;
        int i;
        int bytesRead;
        boolean z = DEBUG;
        String str2 = TAG;
        if (z) {
            Log.d(TAG, "getJpegAttributes starting with: " + in);
        }
        char c = 0;
        in.mark(0);
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        byte marker = in.readByte();
        byte b = -1;
        if (marker != -1) {
            throw new IOException("Invalid marker: " + Integer.toHexString(marker & 255));
        }
        int i2 = 1;
        int bytesRead2 = 0 + 1;
        if (in.readByte() != -40) {
            throw new IOException("Invalid marker: " + Integer.toHexString(marker & 255));
        }
        int bytesRead3 = bytesRead2 + 1;
        while (true) {
            byte marker2 = in.readByte();
            if (marker2 != b) {
                throw new IOException("Invalid marker:" + Integer.toHexString(marker2 & 255));
            }
            int bytesRead4 = bytesRead3 + 1;
            byte marker3 = in.readByte();
            if (DEBUG) {
                Log.d(str2, "Found JPEG segment indicator: " + Integer.toHexString(marker3 & 255));
            }
            int bytesRead5 = bytesRead4 + i2;
            if (marker3 != -39 && marker3 != -38) {
                int length = in.readUnsignedShort() - 2;
                int bytesRead6 = bytesRead5 + 2;
                if (DEBUG) {
                    Log.d(str2, "JPEG segment: " + Integer.toHexString(marker3 & 255) + " (length: " + (length + 2) + ")");
                }
                if (length < 0) {
                    throw new IOException("Invalid length");
                }
                switch (marker3) {
                    case -64:
                    case -63:
                    case -62:
                    case -61:
                    case -59:
                    case -58:
                    case -57:
                    case -55:
                    case -54:
                    case -53:
                    case -51:
                    case -50:
                    case -49:
                        str = str2;
                        i = i2;
                        if (in.skipBytes(i) != i) {
                            throw new IOException("Invalid SOFx");
                        }
                        this.mAttributes[imageType].put("ImageLength", ExifAttribute.createULong(in.readUnsignedShort(), this.mExifByteOrder));
                        this.mAttributes[imageType].put("ImageWidth", ExifAttribute.createULong(in.readUnsignedShort(), this.mExifByteOrder));
                        length -= 5;
                        break;
                    case -31:
                        byte[] bytes = new byte[length];
                        in.readFully(bytes);
                        int bytesRead7 = bytesRead6 + length;
                        length = 0;
                        if (startsWith(bytes, IDENTIFIER_EXIF_APP1)) {
                            byte[] value = Arrays.copyOfRange(bytes, IDENTIFIER_EXIF_APP1.length, bytes.length);
                            this.mOffsetToExifData = offsetToJpeg + bytesRead6 + IDENTIFIER_EXIF_APP1.length;
                            readExifSegment(value, imageType);
                            setThumbnailData(new ByteOrderedDataInputStream(value));
                            str = str2;
                            bytesRead = bytesRead7;
                            i = i2;
                        } else if (startsWith(bytes, IDENTIFIER_XMP_APP1)) {
                            int offset = IDENTIFIER_XMP_APP1.length + bytesRead6;
                            byte[] value2 = Arrays.copyOfRange(bytes, IDENTIFIER_XMP_APP1.length, bytes.length);
                            if (getAttribute(TAG_XMP) == null) {
                                str = str2;
                                bytesRead = bytesRead7;
                                this.mAttributes[c].put(TAG_XMP, new ExifAttribute(1, value2.length, offset, value2));
                                i = 1;
                                this.mXmpIsFromSeparateMarker = true;
                            } else {
                                str = str2;
                                bytesRead = bytesRead7;
                                i = i2;
                            }
                        } else {
                            str = str2;
                            bytesRead = bytesRead7;
                            i = i2;
                        }
                        bytesRead6 = bytesRead;
                        break;
                    case -2:
                        byte[] bytes2 = new byte[length];
                        if (in.read(bytes2) != length) {
                            throw new IOException("Invalid exif");
                        }
                        length = 0;
                        if (getAttribute("UserComment") == null) {
                            this.mAttributes[i2].put("UserComment", ExifAttribute.createString(new String(bytes2, ASCII)));
                        }
                        str = str2;
                        i = i2;
                        break;
                    default:
                        str = str2;
                        i = i2;
                        break;
                }
                if (length < 0) {
                    throw new IOException("Invalid length");
                }
                if (in.skipBytes(length) != length) {
                    throw new IOException("Invalid JPEG segment");
                }
                bytesRead3 = bytesRead6 + length;
                i2 = i;
                str2 = str;
                c = 0;
                b = -1;
            }
        }
        in.setByteOrder(this.mExifByteOrder);
    }

    private void getRawAttributes(ByteOrderedDataInputStream in) throws IOException {
        ExifAttribute makerNoteAttribute;
        parseTiffHeaders(in, in.available());
        readImageFileDirectory(in, 0);
        updateImageSizeValues(in, 0);
        updateImageSizeValues(in, 5);
        updateImageSizeValues(in, 4);
        validateImages();
        if (this.mMimeType == 8 && (makerNoteAttribute = this.mAttributes[1].get("MakerNote")) != null) {
            ByteOrderedDataInputStream makerNoteDataInputStream = new ByteOrderedDataInputStream(makerNoteAttribute.bytes);
            makerNoteDataInputStream.setByteOrder(this.mExifByteOrder);
            makerNoteDataInputStream.seek(6L);
            readImageFileDirectory(makerNoteDataInputStream, 9);
            ExifAttribute colorSpaceAttribute = this.mAttributes[9].get("ColorSpace");
            if (colorSpaceAttribute != null) {
                this.mAttributes[1].put("ColorSpace", colorSpaceAttribute);
            }
        }
    }

    private void getRafAttributes(ByteOrderedDataInputStream in) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "getRafAttributes starting with: " + in);
        }
        in.mark(0);
        in.skipBytes(RAF_OFFSET_TO_JPEG_IMAGE_OFFSET);
        byte[] offsetToJpegBytes = new byte[4];
        byte[] jpegLengthBytes = new byte[4];
        byte[] cfaHeaderOffsetBytes = new byte[4];
        in.read(offsetToJpegBytes);
        in.read(jpegLengthBytes);
        in.read(cfaHeaderOffsetBytes);
        int offsetToJpeg = ByteBuffer.wrap(offsetToJpegBytes).getInt();
        int jpegLength = ByteBuffer.wrap(jpegLengthBytes).getInt();
        int cfaHeaderOffset = ByteBuffer.wrap(cfaHeaderOffsetBytes).getInt();
        byte[] jpegBytes = new byte[jpegLength];
        in.seek(offsetToJpeg);
        in.read(jpegBytes);
        ByteOrderedDataInputStream jpegInputStream = new ByteOrderedDataInputStream(jpegBytes);
        getJpegAttributes(jpegInputStream, offsetToJpeg, 5);
        in.seek(cfaHeaderOffset);
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        int numberOfDirectoryEntry = in.readInt();
        if (DEBUG) {
            Log.d(TAG, "numberOfDirectoryEntry: " + numberOfDirectoryEntry);
        }
        for (int i = 0; i < numberOfDirectoryEntry; i++) {
            int tagNumber = in.readUnsignedShort();
            int numberOfBytes = in.readUnsignedShort();
            if (tagNumber != TAG_RAF_IMAGE_SIZE.number) {
                in.skipBytes(numberOfBytes);
            } else {
                int imageLength = in.readShort();
                int imageWidth = in.readShort();
                ExifAttribute imageLengthAttribute = ExifAttribute.createUShort(imageLength, this.mExifByteOrder);
                ExifAttribute imageWidthAttribute = ExifAttribute.createUShort(imageWidth, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", imageLengthAttribute);
                this.mAttributes[0].put("ImageWidth", imageWidthAttribute);
                if (DEBUG) {
                    Log.d(TAG, "Updated to length: " + imageLength + ", width: " + imageWidth);
                    return;
                }
                return;
            }
        }
    }

    private void getHeifAttributes(final ByteOrderedDataInputStream in) throws IOException {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(new MediaDataSource() { // from class: androidx.exifinterface.media.ExifInterface.1
                long mPosition;

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                }

                @Override // android.media.MediaDataSource
                public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
                    if (size == 0) {
                        return 0;
                    }
                    if (position < 0) {
                        return -1;
                    }
                    try {
                        if (this.mPosition != position) {
                            if (this.mPosition >= 0 && position >= this.mPosition + in.available()) {
                                return -1;
                            }
                            in.seek(position);
                            this.mPosition = position;
                        }
                        if (size > in.available()) {
                            size = in.available();
                        }
                        int bytesRead = in.read(buffer, offset, size);
                        if (bytesRead >= 0) {
                            this.mPosition += bytesRead;
                            return bytesRead;
                        }
                    } catch (IOException e) {
                    }
                    this.mPosition = -1L;
                    return -1;
                }

                @Override // android.media.MediaDataSource
                public long getSize() throws IOException {
                    return -1L;
                }
            });
            String exifOffsetStr = retriever.extractMetadata(33);
            String exifLengthStr = retriever.extractMetadata(34);
            String hasImage = retriever.extractMetadata(26);
            String hasVideo = retriever.extractMetadata(17);
            String width = null;
            String height = null;
            String rotation = null;
            if (BooleanUtils.YES.equals(hasImage)) {
                width = retriever.extractMetadata(29);
                height = retriever.extractMetadata(30);
                rotation = retriever.extractMetadata(31);
            } else if (BooleanUtils.YES.equals(hasVideo)) {
                width = retriever.extractMetadata(18);
                height = retriever.extractMetadata(19);
                rotation = retriever.extractMetadata(24);
            }
            if (width != null) {
                this.mAttributes[0].put("ImageWidth", ExifAttribute.createUShort(Integer.parseInt(width), this.mExifByteOrder));
            }
            if (height != null) {
                this.mAttributes[0].put("ImageLength", ExifAttribute.createUShort(Integer.parseInt(height), this.mExifByteOrder));
            }
            if (rotation != null) {
                int orientation = 1;
                switch (Integer.parseInt(rotation)) {
                    case 90:
                        orientation = 6;
                        break;
                    case BarcodeUtils.ROTATION_180 /* 180 */:
                        orientation = 3;
                        break;
                    case BarcodeUtils.ROTATION_270 /* 270 */:
                        orientation = 8;
                        break;
                }
                this.mAttributes[0].put("Orientation", ExifAttribute.createUShort(orientation, this.mExifByteOrder));
            }
            try {
                if (exifOffsetStr != null && exifLengthStr != null) {
                    int offset = Integer.parseInt(exifOffsetStr);
                    int length = Integer.parseInt(exifLengthStr);
                    if (length <= 6) {
                        throw new IOException("Invalid exif length");
                    }
                    try {
                        in.seek(offset);
                        byte[] identifier = new byte[6];
                        if (in.read(identifier) != 6) {
                            throw new IOException("Can't read identifier");
                        }
                        int offset2 = offset + 6;
                        int length2 = length - 6;
                        if (!Arrays.equals(identifier, IDENTIFIER_EXIF_APP1)) {
                            throw new IOException("Invalid identifier");
                        }
                        byte[] bytes = new byte[length2];
                        if (in.read(bytes) != length2) {
                            throw new IOException("Can't read exif");
                        }
                        this.mOffsetToExifData = offset2;
                        readExifSegment(bytes, 0);
                    } catch (Throwable th) {
                        th = th;
                        retriever.release();
                        throw th;
                    }
                }
                if (DEBUG) {
                    Log.d(TAG, "Heif meta: " + width + "x" + height + ", rotation " + rotation);
                }
                retriever.release();
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private void getStandaloneAttributes(ByteOrderedDataInputStream in) throws IOException {
        in.skipBytes(IDENTIFIER_EXIF_APP1.length);
        byte[] data = new byte[in.available()];
        in.readFully(data);
        this.mOffsetToExifData = IDENTIFIER_EXIF_APP1.length;
        readExifSegment(data, 0);
    }

    private void getOrfAttributes(ByteOrderedDataInputStream in) throws IOException {
        getRawAttributes(in);
        ExifAttribute makerNoteAttribute = this.mAttributes[1].get("MakerNote");
        if (makerNoteAttribute != null) {
            ByteOrderedDataInputStream makerNoteDataInputStream = new ByteOrderedDataInputStream(makerNoteAttribute.bytes);
            makerNoteDataInputStream.setByteOrder(this.mExifByteOrder);
            byte[] makerNoteHeader1Bytes = new byte[ORF_MAKER_NOTE_HEADER_1.length];
            makerNoteDataInputStream.readFully(makerNoteHeader1Bytes);
            makerNoteDataInputStream.seek(0L);
            byte[] makerNoteHeader2Bytes = new byte[ORF_MAKER_NOTE_HEADER_2.length];
            makerNoteDataInputStream.readFully(makerNoteHeader2Bytes);
            if (Arrays.equals(makerNoteHeader1Bytes, ORF_MAKER_NOTE_HEADER_1)) {
                makerNoteDataInputStream.seek(8L);
            } else if (Arrays.equals(makerNoteHeader2Bytes, ORF_MAKER_NOTE_HEADER_2)) {
                makerNoteDataInputStream.seek(12L);
            }
            readImageFileDirectory(makerNoteDataInputStream, 6);
            ExifAttribute imageStartAttribute = this.mAttributes[7].get("PreviewImageStart");
            ExifAttribute imageLengthAttribute = this.mAttributes[7].get("PreviewImageLength");
            if (imageStartAttribute != null && imageLengthAttribute != null) {
                this.mAttributes[5].put("JPEGInterchangeFormat", imageStartAttribute);
                this.mAttributes[5].put("JPEGInterchangeFormatLength", imageLengthAttribute);
            }
            ExifAttribute aspectFrameAttribute = this.mAttributes[8].get("AspectFrame");
            if (aspectFrameAttribute != null) {
                int[] aspectFrameValues = (int[]) aspectFrameAttribute.getValue(this.mExifByteOrder);
                if (aspectFrameValues == null || aspectFrameValues.length != 4) {
                    Log.w(TAG, "Invalid aspect frame values. frame=" + Arrays.toString(aspectFrameValues));
                    return;
                }
                if (aspectFrameValues[2] > aspectFrameValues[0] && aspectFrameValues[3] > aspectFrameValues[1]) {
                    int primaryImageWidth = (aspectFrameValues[2] - aspectFrameValues[0]) + 1;
                    int primaryImageLength = (aspectFrameValues[3] - aspectFrameValues[1]) + 1;
                    if (primaryImageWidth < primaryImageLength) {
                        int primaryImageWidth2 = primaryImageWidth + primaryImageLength;
                        primaryImageLength = primaryImageWidth2 - primaryImageLength;
                        primaryImageWidth = primaryImageWidth2 - primaryImageLength;
                    }
                    ExifAttribute primaryImageWidthAttribute = ExifAttribute.createUShort(primaryImageWidth, this.mExifByteOrder);
                    ExifAttribute primaryImageLengthAttribute = ExifAttribute.createUShort(primaryImageLength, this.mExifByteOrder);
                    this.mAttributes[0].put("ImageWidth", primaryImageWidthAttribute);
                    this.mAttributes[0].put("ImageLength", primaryImageLengthAttribute);
                }
            }
        }
    }

    private void getRw2Attributes(ByteOrderedDataInputStream in) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "getRw2Attributes starting with: " + in);
        }
        getRawAttributes(in);
        ExifAttribute jpgFromRawAttribute = this.mAttributes[0].get("JpgFromRaw");
        if (jpgFromRawAttribute != null) {
            ByteOrderedDataInputStream jpegInputStream = new ByteOrderedDataInputStream(jpgFromRawAttribute.bytes);
            getJpegAttributes(jpegInputStream, (int) jpgFromRawAttribute.bytesOffset, 5);
        }
        ExifAttribute rw2IsoAttribute = this.mAttributes[0].get("ISO");
        ExifAttribute exifIsoAttribute = this.mAttributes[1].get("PhotographicSensitivity");
        if (rw2IsoAttribute != null && exifIsoAttribute == null) {
            this.mAttributes[1].put("PhotographicSensitivity", rw2IsoAttribute);
        }
    }

    private void getPngAttributes(ByteOrderedDataInputStream in) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "getPngAttributes starting with: " + in);
        }
        in.mark(0);
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        in.skipBytes(PNG_SIGNATURE.length);
        int bytesRead = 0 + PNG_SIGNATURE.length;
        while (true) {
            try {
                int length = in.readInt();
                int bytesRead2 = bytesRead + 4;
                byte[] type = new byte[4];
                if (in.read(type) != type.length) {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
                int bytesRead3 = bytesRead2 + 4;
                if (bytesRead3 == 16 && !Arrays.equals(type, PNG_CHUNK_TYPE_IHDR)) {
                    throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                }
                if (!Arrays.equals(type, PNG_CHUNK_TYPE_IEND)) {
                    if (Arrays.equals(type, PNG_CHUNK_TYPE_EXIF)) {
                        byte[] data = new byte[length];
                        if (in.read(data) != length) {
                            throw new IOException("Failed to read given length for given PNG chunk type: " + byteArrayToHexString(type));
                        }
                        int dataCrcValue = in.readInt();
                        CRC32 crc = new CRC32();
                        crc.update(type);
                        crc.update(data);
                        if (((int) crc.getValue()) != dataCrcValue) {
                            throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + dataCrcValue + ", calculated CRC value: " + crc.getValue());
                        }
                        this.mOffsetToExifData = bytesRead3;
                        readExifSegment(data, 0);
                        validateImages();
                        setThumbnailData(new ByteOrderedDataInputStream(data));
                        return;
                    }
                    in.skipBytes(length + 4);
                    bytesRead = bytesRead3 + length + 4;
                } else {
                    return;
                }
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    private void getWebpAttributes(ByteOrderedDataInputStream in) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "getWebpAttributes starting with: " + in);
        }
        in.mark(0);
        in.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        in.skipBytes(WEBP_SIGNATURE_1.length);
        int fileSize = in.readInt() + 8;
        int bytesRead = 8 + in.skipBytes(WEBP_SIGNATURE_2.length);
        while (true) {
            try {
                byte[] code = new byte[4];
                if (in.read(code) != code.length) {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
                int chunkSize = in.readInt();
                int bytesRead2 = bytesRead + 4 + 4;
                if (Arrays.equals(WEBP_CHUNK_TYPE_EXIF, code)) {
                    byte[] payload = new byte[chunkSize];
                    if (in.read(payload) != chunkSize) {
                        throw new IOException("Failed to read given length for given PNG chunk type: " + byteArrayToHexString(code));
                    }
                    this.mOffsetToExifData = bytesRead2;
                    readExifSegment(payload, 0);
                    setThumbnailData(new ByteOrderedDataInputStream(payload));
                    return;
                }
                int chunkSize2 = chunkSize % 2 == 1 ? chunkSize + 1 : chunkSize;
                if (bytesRead2 + chunkSize2 != fileSize) {
                    if (bytesRead2 + chunkSize2 > fileSize) {
                        throw new IOException("Encountered WebP file with invalid chunk size");
                    }
                    int skipped = in.skipBytes(chunkSize2);
                    if (skipped != chunkSize2) {
                        throw new IOException("Encountered WebP file with invalid chunk size");
                    }
                    bytesRead = bytesRead2 + skipped;
                } else {
                    return;
                }
            } catch (EOFException e) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    private void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "saveJpegAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        ByteOrderedDataOutputStream dataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        if (dataInputStream.readByte() != -1) {
            throw new IOException("Invalid marker");
        }
        dataOutputStream.writeByte(-1);
        if (dataInputStream.readByte() != -40) {
            throw new IOException("Invalid marker");
        }
        dataOutputStream.writeByte(-40);
        ExifAttribute xmpAttribute = null;
        if (getAttribute(TAG_XMP) != null && this.mXmpIsFromSeparateMarker) {
            xmpAttribute = this.mAttributes[0].remove(TAG_XMP);
        }
        dataOutputStream.writeByte(-1);
        dataOutputStream.writeByte(-31);
        writeExifSegment(dataOutputStream);
        if (xmpAttribute != null) {
            this.mAttributes[0].put(TAG_XMP, xmpAttribute);
        }
        byte[] bytes = new byte[4096];
        while (dataInputStream.readByte() == -1) {
            byte marker = dataInputStream.readByte();
            switch (marker) {
                case -39:
                case -38:
                    dataOutputStream.writeByte(-1);
                    dataOutputStream.writeByte(marker);
                    copy(dataInputStream, dataOutputStream);
                    return;
                case -31:
                    int length = dataInputStream.readUnsignedShort() - 2;
                    if (length < 0) {
                        throw new IOException("Invalid length");
                    }
                    byte[] identifier = new byte[6];
                    if (length >= 6) {
                        if (dataInputStream.read(identifier) != 6) {
                            throw new IOException("Invalid exif");
                        }
                        if (Arrays.equals(identifier, IDENTIFIER_EXIF_APP1)) {
                            if (dataInputStream.skipBytes(length - 6) != length - 6) {
                                throw new IOException("Invalid length");
                            }
                            break;
                        }
                    }
                    dataOutputStream.writeByte(-1);
                    dataOutputStream.writeByte(marker);
                    dataOutputStream.writeUnsignedShort(length + 2);
                    if (length >= 6) {
                        length -= 6;
                        dataOutputStream.write(identifier);
                    }
                    while (length > 0) {
                        int read = dataInputStream.read(bytes, 0, Math.min(length, bytes.length));
                        if (read >= 0) {
                            dataOutputStream.write(bytes, 0, read);
                            length -= read;
                        }
                    }
                    break;
                default:
                    dataOutputStream.writeByte(-1);
                    dataOutputStream.writeByte(marker);
                    int length2 = dataInputStream.readUnsignedShort();
                    dataOutputStream.writeUnsignedShort(length2);
                    int length3 = length2 - 2;
                    if (length3 < 0) {
                        throw new IOException("Invalid length");
                    }
                    while (length3 > 0) {
                        int read2 = dataInputStream.read(bytes, 0, Math.min(length3, bytes.length));
                        if (read2 >= 0) {
                            dataOutputStream.write(bytes, 0, read2);
                            length3 -= read2;
                        }
                    }
                    break;
            }
        }
        throw new IOException("Invalid marker");
    }

    private void savePngAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "savePngAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        ByteOrderedDataOutputStream dataOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.BIG_ENDIAN);
        copy(dataInputStream, dataOutputStream, PNG_SIGNATURE.length);
        if (this.mOffsetToExifData == 0) {
            int ihdrChunkLength = dataInputStream.readInt();
            dataOutputStream.writeInt(ihdrChunkLength);
            copy(dataInputStream, dataOutputStream, ihdrChunkLength + 4 + 4);
        } else {
            int copyLength = ((this.mOffsetToExifData - PNG_SIGNATURE.length) - 4) - 4;
            copy(dataInputStream, dataOutputStream, copyLength);
            int exifChunkLength = dataInputStream.readInt();
            dataInputStream.skipBytes(exifChunkLength + 4 + 4);
        }
        ByteArrayOutputStream exifByteArrayOutputStream = null;
        try {
            exifByteArrayOutputStream = new ByteArrayOutputStream();
            ByteOrderedDataOutputStream exifDataOutputStream = new ByteOrderedDataOutputStream(exifByteArrayOutputStream, ByteOrder.BIG_ENDIAN);
            writeExifSegment(exifDataOutputStream);
            byte[] exifBytes = ((ByteArrayOutputStream) exifDataOutputStream.mOutputStream).toByteArray();
            dataOutputStream.write(exifBytes);
            CRC32 crc = new CRC32();
            crc.update(exifBytes, 4, exifBytes.length - 4);
            dataOutputStream.writeInt((int) crc.getValue());
            closeQuietly(exifByteArrayOutputStream);
            copy(dataInputStream, dataOutputStream);
        } catch (Throwable th) {
            closeQuietly(exifByteArrayOutputStream);
            throw th;
        }
    }

    private void saveWebpAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "saveWebpAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrderedDataInputStream totalInputStream = new ByteOrderedDataInputStream(inputStream, ByteOrder.LITTLE_ENDIAN);
        ByteOrderedDataOutputStream totalOutputStream = new ByteOrderedDataOutputStream(outputStream, ByteOrder.LITTLE_ENDIAN);
        copy(totalInputStream, totalOutputStream, WEBP_SIGNATURE_1.length);
        totalInputStream.skipBytes(WEBP_SIGNATURE_2.length + 4);
        ByteArrayOutputStream nonHeaderByteArrayOutputStream = null;
        try {
            try {
                nonHeaderByteArrayOutputStream = new ByteArrayOutputStream();
                ByteOrderedDataOutputStream nonHeaderOutputStream = new ByteOrderedDataOutputStream(nonHeaderByteArrayOutputStream, ByteOrder.LITTLE_ENDIAN);
                if (this.mOffsetToExifData != 0) {
                    int bytesRead = WEBP_SIGNATURE_1.length + 4 + WEBP_SIGNATURE_2.length;
                    copy(totalInputStream, nonHeaderOutputStream, ((this.mOffsetToExifData - bytesRead) - 4) - 4);
                    totalInputStream.skipBytes(4);
                    int exifChunkLength = totalInputStream.readInt();
                    totalInputStream.skipBytes(exifChunkLength);
                    writeExifSegment(nonHeaderOutputStream);
                } else {
                    byte[] firstChunkType = new byte[4];
                    if (totalInputStream.read(firstChunkType) != firstChunkType.length) {
                        throw new IOException("Encountered invalid length while parsing WebP chunk type");
                    }
                    if (Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8X)) {
                        int size = totalInputStream.readInt();
                        byte[] data = new byte[size % 2 == 1 ? size + 1 : size];
                        totalInputStream.read(data);
                        data[0] = (byte) (8 | data[0]);
                        boolean containsAnimation = ((data[0] >> 1) & 1) == 1;
                        nonHeaderOutputStream.write(WEBP_CHUNK_TYPE_VP8X);
                        nonHeaderOutputStream.writeInt(size);
                        nonHeaderOutputStream.write(data);
                        if (containsAnimation) {
                            copyChunksUpToGivenChunkType(totalInputStream, nonHeaderOutputStream, WEBP_CHUNK_TYPE_ANIM, null);
                            while (true) {
                                byte[] type = new byte[4];
                                inputStream.read(type);
                                if (!Arrays.equals(type, WEBP_CHUNK_TYPE_ANMF)) {
                                    break;
                                } else {
                                    copyWebPChunk(totalInputStream, nonHeaderOutputStream, type);
                                }
                            }
                            writeExifSegment(nonHeaderOutputStream);
                        } else {
                            copyChunksUpToGivenChunkType(totalInputStream, nonHeaderOutputStream, WEBP_CHUNK_TYPE_VP8, WEBP_CHUNK_TYPE_VP8L);
                            writeExifSegment(nonHeaderOutputStream);
                        }
                    } else if (Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8) || Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8L)) {
                        int size2 = totalInputStream.readInt();
                        int bytesToRead = size2;
                        if (size2 % 2 == 1) {
                            bytesToRead++;
                        }
                        int widthAndHeight = 0;
                        int width = 0;
                        int height = 0;
                        int alpha = 0;
                        byte[] vp8Frame = new byte[3];
                        if (Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8)) {
                            totalInputStream.read(vp8Frame);
                            byte[] vp8Signature = new byte[3];
                            if (totalInputStream.read(vp8Signature) != vp8Signature.length || !Arrays.equals(WEBP_VP8_SIGNATURE, vp8Signature)) {
                                throw new IOException("Encountered error while checking VP8 signature");
                            }
                            widthAndHeight = totalInputStream.readInt();
                            width = (widthAndHeight << 18) >> 18;
                            height = (widthAndHeight << 2) >> 18;
                            bytesToRead -= (vp8Frame.length + vp8Signature.length) + 4;
                        } else if (Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8L)) {
                            byte vp8lSignature = totalInputStream.readByte();
                            if (vp8lSignature != 47) {
                                throw new IOException("Encountered error while checking VP8L signature");
                            }
                            widthAndHeight = totalInputStream.readInt();
                            width = ((widthAndHeight << 18) >> 18) + 1;
                            height = ((widthAndHeight << 4) >> 18) + 1;
                            alpha = widthAndHeight & 8;
                            bytesToRead -= 5;
                        }
                        nonHeaderOutputStream.write(WEBP_CHUNK_TYPE_VP8X);
                        nonHeaderOutputStream.writeInt(10);
                        byte[] data2 = new byte[10];
                        data2[0] = (byte) (data2[0] | 8);
                        data2[0] = (byte) (data2[0] | (alpha << 4));
                        int width2 = width - 1;
                        int height2 = height - 1;
                        data2[4] = (byte) width2;
                        data2[5] = (byte) (width2 >> 8);
                        data2[6] = (byte) (width2 >> 16);
                        data2[7] = (byte) height2;
                        data2[8] = (byte) (height2 >> 8);
                        data2[9] = (byte) (height2 >> 16);
                        nonHeaderOutputStream.write(data2);
                        nonHeaderOutputStream.write(firstChunkType);
                        nonHeaderOutputStream.writeInt(size2);
                        if (Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8)) {
                            nonHeaderOutputStream.write(vp8Frame);
                            nonHeaderOutputStream.write(WEBP_VP8_SIGNATURE);
                            nonHeaderOutputStream.writeInt(widthAndHeight);
                        } else if (Arrays.equals(firstChunkType, WEBP_CHUNK_TYPE_VP8L)) {
                            nonHeaderOutputStream.write(47);
                            nonHeaderOutputStream.writeInt(widthAndHeight);
                        }
                        copy(totalInputStream, nonHeaderOutputStream, bytesToRead);
                        writeExifSegment(nonHeaderOutputStream);
                    }
                }
                copy(totalInputStream, nonHeaderOutputStream);
                totalOutputStream.writeInt(nonHeaderByteArrayOutputStream.size() + WEBP_SIGNATURE_2.length);
                totalOutputStream.write(WEBP_SIGNATURE_2);
                nonHeaderByteArrayOutputStream.writeTo(totalOutputStream);
            } catch (Exception e) {
                throw new IOException("Failed to save WebP file", e);
            }
        } finally {
            closeQuietly(nonHeaderByteArrayOutputStream);
        }
    }

    private void copyChunksUpToGivenChunkType(ByteOrderedDataInputStream inputStream, ByteOrderedDataOutputStream outputStream, byte[] firstGivenType, byte[] secondGivenType) throws IOException {
        while (true) {
            byte[] type = new byte[4];
            if (inputStream.read(type) != type.length) {
                throw new IOException("Encountered invalid length while copying WebP chunks up tochunk type " + new String(firstGivenType, ASCII) + (secondGivenType == null ? "" : " or " + new String(secondGivenType, ASCII)));
            }
            copyWebPChunk(inputStream, outputStream, type);
            if (!Arrays.equals(type, firstGivenType)) {
                if (secondGivenType != null && Arrays.equals(type, secondGivenType)) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void copyWebPChunk(ByteOrderedDataInputStream inputStream, ByteOrderedDataOutputStream outputStream, byte[] type) throws IOException {
        int size = inputStream.readInt();
        outputStream.write(type);
        outputStream.writeInt(size);
        copy(inputStream, outputStream, size % 2 == 1 ? size + 1 : size);
    }

    private void readExifSegment(byte[] exifBytes, int imageType) throws IOException {
        ByteOrderedDataInputStream dataInputStream = new ByteOrderedDataInputStream(exifBytes);
        parseTiffHeaders(dataInputStream, exifBytes.length);
        readImageFileDirectory(dataInputStream, imageType);
    }

    private void addDefaultValuesForCompatibility() {
        String valueOfDateTimeOriginal = getAttribute("DateTimeOriginal");
        if (valueOfDateTimeOriginal != null && getAttribute("DateTime") == null) {
            this.mAttributes[0].put("DateTime", ExifAttribute.createString(valueOfDateTimeOriginal));
        }
        if (getAttribute("ImageWidth") == null) {
            this.mAttributes[0].put("ImageWidth", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("ImageLength") == null) {
            this.mAttributes[0].put("ImageLength", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("Orientation") == null) {
            this.mAttributes[0].put("Orientation", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (getAttribute("LightSource") == null) {
            this.mAttributes[1].put("LightSource", ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
    }

    private ByteOrder readByteOrder(ByteOrderedDataInputStream dataInputStream) throws IOException {
        short byteOrder = dataInputStream.readShort();
        switch (byteOrder) {
            case 18761:
                if (DEBUG) {
                    Log.d(TAG, "readExifSegment: Byte Align II");
                }
                return ByteOrder.LITTLE_ENDIAN;
            case 19789:
                if (DEBUG) {
                    Log.d(TAG, "readExifSegment: Byte Align MM");
                }
                return ByteOrder.BIG_ENDIAN;
            default:
                throw new IOException("Invalid byte order: " + Integer.toHexString(byteOrder));
        }
    }

    private void parseTiffHeaders(ByteOrderedDataInputStream dataInputStream, int exifBytesLength) throws IOException {
        this.mExifByteOrder = readByteOrder(dataInputStream);
        dataInputStream.setByteOrder(this.mExifByteOrder);
        int startCode = dataInputStream.readUnsignedShort();
        if (this.mMimeType != 7 && this.mMimeType != 10 && startCode != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(startCode));
        }
        int firstIfdOffset = dataInputStream.readInt();
        if (firstIfdOffset < 8 || firstIfdOffset >= exifBytesLength) {
            throw new IOException("Invalid first Ifd offset: " + firstIfdOffset);
        }
        int firstIfdOffset2 = firstIfdOffset - 8;
        if (firstIfdOffset2 > 0 && dataInputStream.skipBytes(firstIfdOffset2) != firstIfdOffset2) {
            throw new IOException("Couldn't jump to first Ifd: " + firstIfdOffset2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0167  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readImageFileDirectory(ByteOrderedDataInputStream dataInputStream, int ifdType) throws IOException {
        short numberOfDirectoryEntry;
        long byteCount;
        long byteCount2;
        short i;
        ExifTag tag;
        int tagNumber;
        ExifTag tag2;
        int i2 = ifdType;
        this.mAttributesOffsets.add(Integer.valueOf(dataInputStream.mPosition));
        if (dataInputStream.mPosition + 2 > dataInputStream.mLength) {
            return;
        }
        short numberOfDirectoryEntry2 = dataInputStream.readShort();
        if (DEBUG) {
            Log.d(TAG, "numberOfDirectoryEntry: " + ((int) numberOfDirectoryEntry2));
        }
        if (dataInputStream.mPosition + (numberOfDirectoryEntry2 * 12) <= dataInputStream.mLength && numberOfDirectoryEntry2 > 0) {
            short i3 = 0;
            while (i3 < numberOfDirectoryEntry2) {
                int tagNumber2 = dataInputStream.readUnsignedShort();
                int dataFormat = dataInputStream.readUnsignedShort();
                int numberOfComponents = dataInputStream.readInt();
                long nextEntryOffset = dataInputStream.peek() + 4;
                ExifTag tag3 = sExifTagMapsForReading[i2].get(Integer.valueOf(tagNumber2));
                if (!DEBUG) {
                    numberOfDirectoryEntry = numberOfDirectoryEntry2;
                } else {
                    numberOfDirectoryEntry = numberOfDirectoryEntry2;
                    Log.d(TAG, String.format("ifdType: %d, tagNumber: %d, tagName: %s, dataFormat: %d, numberOfComponents: %d", Integer.valueOf(ifdType), Integer.valueOf(tagNumber2), tag3 != null ? tag3.name : null, Integer.valueOf(dataFormat), Integer.valueOf(numberOfComponents)));
                }
                boolean valid = false;
                if (tag3 == null) {
                    if (!DEBUG) {
                        byteCount = 0;
                    } else {
                        Log.d(TAG, "Skip the tag entry since tag number is not defined: " + tagNumber2);
                        byteCount = 0;
                    }
                } else {
                    if (dataFormat <= 0) {
                        byteCount = 0;
                    } else if (dataFormat >= IFD_FORMAT_BYTES_PER_FORMAT.length) {
                        byteCount = 0;
                    } else if (!tag3.isFormatCompatible(dataFormat)) {
                        if (!DEBUG) {
                            byteCount = 0;
                        } else {
                            Log.d(TAG, "Skip the tag entry since data format (" + IFD_FORMAT_NAMES[dataFormat] + ") is unexpected for tag: " + tag3.name);
                            byteCount = 0;
                        }
                    } else {
                        if (dataFormat == 7) {
                            dataFormat = tag3.primaryFormat;
                        }
                        long byteCount3 = numberOfComponents;
                        int dataFormat2 = dataFormat;
                        byteCount2 = byteCount3 * IFD_FORMAT_BYTES_PER_FORMAT[dataFormat];
                        if (byteCount2 < 0 || byteCount2 > 2147483647L) {
                            if (DEBUG) {
                                Log.d(TAG, "Skip the tag entry since the number of components is invalid: " + numberOfComponents);
                            }
                            dataFormat = dataFormat2;
                        } else {
                            valid = true;
                            dataFormat = dataFormat2;
                        }
                        if (valid) {
                            dataInputStream.seek(nextEntryOffset);
                            i = i3;
                        } else {
                            if (byteCount2 > 4) {
                                int offset = dataInputStream.readInt();
                                if (DEBUG) {
                                    i = i3;
                                    Log.d(TAG, "seek to data offset: " + offset);
                                } else {
                                    i = i3;
                                }
                                if (this.mMimeType != 7) {
                                    tag = tag3;
                                    tagNumber = tagNumber2;
                                } else if ("MakerNote".equals(tag3.name)) {
                                    this.mOrfMakerNoteOffset = offset;
                                    tag = tag3;
                                    tagNumber = tagNumber2;
                                } else if (i2 != 6) {
                                    tag = tag3;
                                    tagNumber = tagNumber2;
                                } else if (!"ThumbnailImage".equals(tag3.name)) {
                                    tag = tag3;
                                    tagNumber = tagNumber2;
                                } else {
                                    this.mOrfThumbnailOffset = offset;
                                    this.mOrfThumbnailLength = numberOfComponents;
                                    ExifAttribute compressionAttribute = ExifAttribute.createUShort(6, this.mExifByteOrder);
                                    tag = tag3;
                                    tagNumber = tagNumber2;
                                    ExifAttribute jpegInterchangeFormatAttribute = ExifAttribute.createULong(this.mOrfThumbnailOffset, this.mExifByteOrder);
                                    ExifAttribute jpegInterchangeFormatLengthAttribute = ExifAttribute.createULong(this.mOrfThumbnailLength, this.mExifByteOrder);
                                    this.mAttributes[4].put("Compression", compressionAttribute);
                                    this.mAttributes[4].put("JPEGInterchangeFormat", jpegInterchangeFormatAttribute);
                                    this.mAttributes[4].put("JPEGInterchangeFormatLength", jpegInterchangeFormatLengthAttribute);
                                }
                                if (offset + byteCount2 <= dataInputStream.mLength) {
                                    dataInputStream.seek(offset);
                                } else {
                                    if (DEBUG) {
                                        Log.d(TAG, "Skip the tag entry since data offset is invalid: " + offset);
                                    }
                                    dataInputStream.seek(nextEntryOffset);
                                }
                            } else {
                                i = i3;
                                tag = tag3;
                                tagNumber = tagNumber2;
                            }
                            Integer nextIfdType = sExifPointerTagMap.get(Integer.valueOf(tagNumber));
                            if (DEBUG) {
                                Log.d(TAG, "nextIfdType: " + nextIfdType + " byteCount: " + byteCount2);
                            }
                            if (nextIfdType == null) {
                                int bytesOffset = dataInputStream.peek() + this.mOffsetToExifData;
                                byte[] bytes = new byte[(int) byteCount2];
                                dataInputStream.readFully(bytes);
                                long byteCount4 = bytesOffset;
                                ExifAttribute attribute = new ExifAttribute(dataFormat, numberOfComponents, byteCount4, bytes);
                                ExifTag tag4 = tag;
                                this.mAttributes[ifdType].put(tag4.name, attribute);
                                if ("DNGVersion".equals(tag4.name)) {
                                    this.mMimeType = 3;
                                }
                                if ((("Make".equals(tag4.name) || "Model".equals(tag4.name)) && attribute.getStringValue(this.mExifByteOrder).contains(PEF_SIGNATURE)) || ("Compression".equals(tag4.name) && attribute.getIntValue(this.mExifByteOrder) == 65535)) {
                                    this.mMimeType = 8;
                                }
                                if (dataInputStream.peek() != nextEntryOffset) {
                                    dataInputStream.seek(nextEntryOffset);
                                }
                            } else {
                                long offset2 = -1;
                                switch (dataFormat) {
                                    case 3:
                                        offset2 = dataInputStream.readUnsignedShort();
                                        break;
                                    case 4:
                                        offset2 = dataInputStream.readUnsignedInt();
                                        break;
                                    case 8:
                                        offset2 = dataInputStream.readShort();
                                        break;
                                    case 9:
                                    case 13:
                                        offset2 = dataInputStream.readInt();
                                        break;
                                }
                                if (!DEBUG) {
                                    tag2 = tag;
                                } else {
                                    tag2 = tag;
                                    Log.d(TAG, String.format("Offset: %d, tagName: %s", Long.valueOf(offset2), tag2.name));
                                }
                                if (offset2 > 0 && offset2 < dataInputStream.mLength) {
                                    if (!this.mAttributesOffsets.contains(Integer.valueOf((int) offset2))) {
                                        dataInputStream.seek(offset2);
                                        readImageFileDirectory(dataInputStream, nextIfdType.intValue());
                                    } else if (DEBUG) {
                                        Log.d(TAG, "Skip jump into the IFD since it has already been read: IfdType " + nextIfdType + " (at " + offset2 + ")");
                                    }
                                    dataInputStream.seek(nextEntryOffset);
                                }
                                if (DEBUG) {
                                    Log.d(TAG, "Skip jump into the IFD since its offset is invalid: " + offset2);
                                }
                                dataInputStream.seek(nextEntryOffset);
                            }
                        }
                        i3 = (short) (i + 1);
                        i2 = ifdType;
                        numberOfDirectoryEntry2 = numberOfDirectoryEntry;
                    }
                    if (DEBUG) {
                        Log.d(TAG, "Skip the tag entry since data format is invalid: " + dataFormat);
                    }
                }
                byteCount2 = byteCount;
                if (valid) {
                }
                i3 = (short) (i + 1);
                i2 = ifdType;
                numberOfDirectoryEntry2 = numberOfDirectoryEntry;
            }
            if (dataInputStream.peek() + 4 <= dataInputStream.mLength) {
                int nextIfdOffset = dataInputStream.readInt();
                if (DEBUG) {
                    Log.d(TAG, String.format("nextIfdOffset: %d", Integer.valueOf(nextIfdOffset)));
                }
                if (nextIfdOffset > 0 && nextIfdOffset < dataInputStream.mLength) {
                    if (!this.mAttributesOffsets.contains(Integer.valueOf(nextIfdOffset))) {
                        dataInputStream.seek(nextIfdOffset);
                        if (this.mAttributes[4].isEmpty()) {
                            readImageFileDirectory(dataInputStream, 4);
                            return;
                        } else {
                            if (this.mAttributes[5].isEmpty()) {
                                readImageFileDirectory(dataInputStream, 5);
                                return;
                            }
                            return;
                        }
                    }
                    if (DEBUG) {
                        Log.d(TAG, "Stop reading file since re-reading an IFD may cause an infinite loop: " + nextIfdOffset);
                        return;
                    }
                    return;
                }
                if (DEBUG) {
                    Log.d(TAG, "Stop reading file since a wrong offset may cause an infinite loop: " + nextIfdOffset);
                }
            }
        }
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream in, int imageType) throws IOException {
        ExifAttribute imageLengthAttribute = this.mAttributes[imageType].get("ImageLength");
        ExifAttribute imageWidthAttribute = this.mAttributes[imageType].get("ImageWidth");
        if (imageLengthAttribute == null || imageWidthAttribute == null) {
            ExifAttribute jpegInterchangeFormatAttribute = this.mAttributes[imageType].get("JPEGInterchangeFormat");
            ExifAttribute jpegInterchangeFormatLengthAttribute = this.mAttributes[imageType].get("JPEGInterchangeFormatLength");
            if (jpegInterchangeFormatAttribute != null && jpegInterchangeFormatLengthAttribute != null) {
                int jpegInterchangeFormat = jpegInterchangeFormatAttribute.getIntValue(this.mExifByteOrder);
                int jpegInterchangeFormatLength = jpegInterchangeFormatAttribute.getIntValue(this.mExifByteOrder);
                in.seek(jpegInterchangeFormat);
                byte[] jpegBytes = new byte[jpegInterchangeFormatLength];
                in.read(jpegBytes);
                getJpegAttributes(new ByteOrderedDataInputStream(jpegBytes), jpegInterchangeFormat, imageType);
            }
        }
    }

    private void setThumbnailData(ByteOrderedDataInputStream in) throws IOException {
        HashMap thumbnailData = this.mAttributes[4];
        ExifAttribute compressionAttribute = thumbnailData.get("Compression");
        if (compressionAttribute != null) {
            this.mThumbnailCompression = compressionAttribute.getIntValue(this.mExifByteOrder);
            switch (this.mThumbnailCompression) {
                case 1:
                case 7:
                    if (isSupportedDataType(thumbnailData)) {
                        handleThumbnailFromStrips(in, thumbnailData);
                        break;
                    }
                    break;
                case 6:
                    handleThumbnailFromJfif(in, thumbnailData);
                    break;
            }
        }
        this.mThumbnailCompression = 6;
        handleThumbnailFromJfif(in, thumbnailData);
    }

    private void handleThumbnailFromJfif(ByteOrderedDataInputStream in, HashMap thumbnailData) throws IOException {
        ExifAttribute jpegInterchangeFormatAttribute = (ExifAttribute) thumbnailData.get("JPEGInterchangeFormat");
        ExifAttribute jpegInterchangeFormatLengthAttribute = (ExifAttribute) thumbnailData.get("JPEGInterchangeFormatLength");
        if (jpegInterchangeFormatAttribute != null && jpegInterchangeFormatLengthAttribute != null) {
            int thumbnailOffset = jpegInterchangeFormatAttribute.getIntValue(this.mExifByteOrder);
            int thumbnailLength = jpegInterchangeFormatLengthAttribute.getIntValue(this.mExifByteOrder);
            if (this.mMimeType == 7) {
                thumbnailOffset += this.mOrfMakerNoteOffset;
            }
            int thumbnailLength2 = Math.min(thumbnailLength, in.getLength() - thumbnailOffset);
            if (thumbnailOffset > 0 && thumbnailLength2 > 0) {
                this.mHasThumbnail = true;
                if (this.mFilename == null && this.mAssetInputStream == null && this.mSeekableFileDescriptor == null) {
                    byte[] thumbnailBytes = new byte[thumbnailLength2];
                    in.skip(thumbnailOffset);
                    in.read(thumbnailBytes);
                    this.mThumbnailBytes = thumbnailBytes;
                }
                this.mThumbnailOffset = thumbnailOffset;
                this.mThumbnailLength = thumbnailLength2;
            }
            if (DEBUG) {
                Log.d(TAG, "Setting thumbnail attributes with offset: " + thumbnailOffset + ", length: " + thumbnailLength2);
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream in, HashMap thumbnailData) throws IOException {
        long totalStripByteCount;
        ExifAttribute stripOffsetsAttribute = (ExifAttribute) thumbnailData.get("StripOffsets");
        ExifAttribute stripByteCountsAttribute = (ExifAttribute) thumbnailData.get("StripByteCounts");
        if (stripOffsetsAttribute != null && stripByteCountsAttribute != null) {
            long[] stripOffsets = convertToLongArray(stripOffsetsAttribute.getValue(this.mExifByteOrder));
            long[] stripByteCounts = convertToLongArray(stripByteCountsAttribute.getValue(this.mExifByteOrder));
            if (stripOffsets != null && stripOffsets.length != 0) {
                if (stripByteCounts != null && stripByteCounts.length != 0) {
                    if (stripOffsets.length != stripByteCounts.length) {
                        Log.w(TAG, "stripOffsets and stripByteCounts should have same length.");
                        return;
                    }
                    long totalStripByteCount2 = 0;
                    for (long byteCount : stripByteCounts) {
                        totalStripByteCount2 += byteCount;
                    }
                    byte[] totalStripBytes = new byte[(int) totalStripByteCount2];
                    int bytesRead = 0;
                    int bytesAdded = 0;
                    int i = 1;
                    this.mAreThumbnailStripsConsecutive = true;
                    this.mHasThumbnailStrips = true;
                    this.mHasThumbnail = true;
                    int i2 = 0;
                    while (i2 < stripOffsets.length) {
                        ExifAttribute stripOffsetsAttribute2 = stripOffsetsAttribute;
                        int stripOffset = (int) stripOffsets[i2];
                        ExifAttribute stripByteCountsAttribute2 = stripByteCountsAttribute;
                        int stripByteCount = (int) stripByteCounts[i2];
                        if (i2 < stripOffsets.length - i) {
                            totalStripByteCount = totalStripByteCount2;
                            long totalStripByteCount3 = stripOffset + stripByteCount;
                            if (totalStripByteCount3 != stripOffsets[i2 + 1]) {
                                this.mAreThumbnailStripsConsecutive = false;
                            }
                        } else {
                            totalStripByteCount = totalStripByteCount2;
                        }
                        int bytesToSkip = stripOffset - bytesRead;
                        if (bytesToSkip < 0) {
                            Log.d(TAG, "Invalid strip offset value");
                            return;
                        }
                        int i3 = i2;
                        if (in.skip(bytesToSkip) != bytesToSkip) {
                            Log.d(TAG, "Failed to skip " + bytesToSkip + " bytes.");
                            return;
                        }
                        int bytesRead2 = bytesRead + bytesToSkip;
                        byte[] stripBytes = new byte[stripByteCount];
                        if (in.read(stripBytes) != stripByteCount) {
                            Log.d(TAG, "Failed to read " + stripByteCount + " bytes.");
                            return;
                        }
                        bytesRead = bytesRead2 + stripByteCount;
                        System.arraycopy(stripBytes, 0, totalStripBytes, bytesAdded, stripBytes.length);
                        bytesAdded += stripBytes.length;
                        i2 = i3 + 1;
                        stripOffsetsAttribute = stripOffsetsAttribute2;
                        stripByteCountsAttribute = stripByteCountsAttribute2;
                        totalStripByteCount2 = totalStripByteCount;
                        i = 1;
                    }
                    this.mThumbnailBytes = totalStripBytes;
                    if (this.mAreThumbnailStripsConsecutive) {
                        this.mThumbnailOffset = (int) stripOffsets[0];
                        this.mThumbnailLength = totalStripBytes.length;
                        return;
                    }
                    return;
                }
                Log.w(TAG, "stripByteCounts should not be null or have zero length.");
                return;
            }
            Log.w(TAG, "stripOffsets should not be null or have zero length.");
        }
    }

    private boolean isSupportedDataType(HashMap thumbnailData) throws IOException {
        ExifAttribute photometricInterpretationAttribute;
        int photometricInterpretationValue;
        ExifAttribute bitsPerSampleAttribute = (ExifAttribute) thumbnailData.get("BitsPerSample");
        if (bitsPerSampleAttribute != null) {
            int[] bitsPerSampleValue = (int[]) bitsPerSampleAttribute.getValue(this.mExifByteOrder);
            if (Arrays.equals(BITS_PER_SAMPLE_RGB, bitsPerSampleValue)) {
                return true;
            }
            if (this.mMimeType == 3 && (photometricInterpretationAttribute = (ExifAttribute) thumbnailData.get("PhotometricInterpretation")) != null && (((photometricInterpretationValue = photometricInterpretationAttribute.getIntValue(this.mExifByteOrder)) == 1 && Arrays.equals(bitsPerSampleValue, BITS_PER_SAMPLE_GREYSCALE_2)) || (photometricInterpretationValue == 6 && Arrays.equals(bitsPerSampleValue, BITS_PER_SAMPLE_RGB)))) {
                return true;
            }
        }
        if (DEBUG) {
            Log.d(TAG, "Unsupported data type value");
            return false;
        }
        return false;
    }

    private boolean isThumbnail(HashMap map) throws IOException {
        ExifAttribute imageLengthAttribute = (ExifAttribute) map.get("ImageLength");
        ExifAttribute imageWidthAttribute = (ExifAttribute) map.get("ImageWidth");
        if (imageLengthAttribute != null && imageWidthAttribute != null) {
            int imageLengthValue = imageLengthAttribute.getIntValue(this.mExifByteOrder);
            int imageWidthValue = imageWidthAttribute.getIntValue(this.mExifByteOrder);
            if (imageLengthValue <= 512 && imageWidthValue <= 512) {
                return true;
            }
            return false;
        }
        return false;
    }

    private void validateImages() throws IOException {
        swapBasedOnImageSize(0, 5);
        swapBasedOnImageSize(0, 4);
        swapBasedOnImageSize(5, 4);
        ExifAttribute pixelXDimAttribute = this.mAttributes[1].get("PixelXDimension");
        ExifAttribute pixelYDimAttribute = this.mAttributes[1].get("PixelYDimension");
        if (pixelXDimAttribute != null && pixelYDimAttribute != null) {
            this.mAttributes[0].put("ImageWidth", pixelXDimAttribute);
            this.mAttributes[0].put("ImageLength", pixelYDimAttribute);
        }
        if (this.mAttributes[4].isEmpty() && isThumbnail(this.mAttributes[5])) {
            this.mAttributes[4] = this.mAttributes[5];
            this.mAttributes[5] = new HashMap<>();
        }
        if (!isThumbnail(this.mAttributes[4])) {
            Log.d(TAG, "No image meets the size requirements of a thumbnail image.");
        }
    }

    private void updateImageSizeValues(ByteOrderedDataInputStream in, int imageType) throws IOException {
        ExifAttribute defaultCropSizeXAttribute;
        ExifAttribute defaultCropSizeYAttribute;
        ExifAttribute defaultCropSizeAttribute = this.mAttributes[imageType].get("DefaultCropSize");
        ExifAttribute topBorderAttribute = this.mAttributes[imageType].get("SensorTopBorder");
        ExifAttribute leftBorderAttribute = this.mAttributes[imageType].get("SensorLeftBorder");
        ExifAttribute bottomBorderAttribute = this.mAttributes[imageType].get("SensorBottomBorder");
        ExifAttribute rightBorderAttribute = this.mAttributes[imageType].get("SensorRightBorder");
        if (defaultCropSizeAttribute != null) {
            if (defaultCropSizeAttribute.format == 5) {
                Rational[] defaultCropSizeValue = (Rational[]) defaultCropSizeAttribute.getValue(this.mExifByteOrder);
                if (defaultCropSizeValue == null || defaultCropSizeValue.length != 2) {
                    Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(defaultCropSizeValue));
                    return;
                } else {
                    defaultCropSizeXAttribute = ExifAttribute.createURational(defaultCropSizeValue[0], this.mExifByteOrder);
                    defaultCropSizeYAttribute = ExifAttribute.createURational(defaultCropSizeValue[1], this.mExifByteOrder);
                }
            } else {
                int[] defaultCropSizeValue2 = (int[]) defaultCropSizeAttribute.getValue(this.mExifByteOrder);
                if (defaultCropSizeValue2 == null || defaultCropSizeValue2.length != 2) {
                    Log.w(TAG, "Invalid crop size values. cropSize=" + Arrays.toString(defaultCropSizeValue2));
                    return;
                } else {
                    defaultCropSizeXAttribute = ExifAttribute.createUShort(defaultCropSizeValue2[0], this.mExifByteOrder);
                    defaultCropSizeYAttribute = ExifAttribute.createUShort(defaultCropSizeValue2[1], this.mExifByteOrder);
                }
            }
            this.mAttributes[imageType].put("ImageWidth", defaultCropSizeXAttribute);
            this.mAttributes[imageType].put("ImageLength", defaultCropSizeYAttribute);
            return;
        }
        if (topBorderAttribute != null && leftBorderAttribute != null && bottomBorderAttribute != null && rightBorderAttribute != null) {
            int topBorderValue = topBorderAttribute.getIntValue(this.mExifByteOrder);
            int bottomBorderValue = bottomBorderAttribute.getIntValue(this.mExifByteOrder);
            int rightBorderValue = rightBorderAttribute.getIntValue(this.mExifByteOrder);
            int leftBorderValue = leftBorderAttribute.getIntValue(this.mExifByteOrder);
            if (bottomBorderValue > topBorderValue && rightBorderValue > leftBorderValue) {
                int length = bottomBorderValue - topBorderValue;
                int width = rightBorderValue - leftBorderValue;
                ExifAttribute imageLengthAttribute = ExifAttribute.createUShort(length, this.mExifByteOrder);
                ExifAttribute imageWidthAttribute = ExifAttribute.createUShort(width, this.mExifByteOrder);
                this.mAttributes[imageType].put("ImageLength", imageLengthAttribute);
                this.mAttributes[imageType].put("ImageWidth", imageWidthAttribute);
                return;
            }
            return;
        }
        retrieveJpegImageSize(in, imageType);
    }

    private int writeExifSegment(ByteOrderedDataOutputStream dataOutputStream) throws IOException {
        int totalSize;
        int totalSize2;
        int[] ifdOffsets = new int[EXIF_TAGS.length];
        int[] ifdDataSizes = new int[EXIF_TAGS.length];
        for (ExifTag tag : EXIF_POINTER_TAGS) {
            removeAttribute(tag.name);
        }
        removeAttribute(JPEG_INTERCHANGE_FORMAT_TAG.name);
        removeAttribute(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name);
        for (int ifdType = 0; ifdType < EXIF_TAGS.length; ifdType++) {
            for (Object obj : this.mAttributes[ifdType].entrySet().toArray()) {
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getValue() == null) {
                    this.mAttributes[ifdType].remove(entry.getKey());
                }
            }
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        int i = 2;
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(0L, this.mExifByteOrder));
        }
        if (this.mHasThumbnail) {
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(0L, this.mExifByteOrder));
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name, ExifAttribute.createULong(this.mThumbnailLength, this.mExifByteOrder));
        }
        for (int i2 = 0; i2 < EXIF_TAGS.length; i2++) {
            int sum = 0;
            Iterator<Map.Entry<String, ExifAttribute>> it = this.mAttributes[i2].entrySet().iterator();
            while (it.hasNext()) {
                ExifAttribute exifAttribute = it.next().getValue();
                int size = exifAttribute.size();
                if (size > 4) {
                    sum += size;
                }
            }
            ifdDataSizes[i2] = ifdDataSizes[i2] + sum;
        }
        int position = 8;
        for (int ifdType2 = 0; ifdType2 < EXIF_TAGS.length; ifdType2++) {
            if (!this.mAttributes[ifdType2].isEmpty()) {
                ifdOffsets[ifdType2] = position;
                position += (this.mAttributes[ifdType2].size() * 12) + 2 + 4 + ifdDataSizes[ifdType2];
            }
        }
        if (this.mHasThumbnail) {
            int thumbnailOffset = position;
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(thumbnailOffset, this.mExifByteOrder));
            this.mThumbnailOffset = thumbnailOffset;
            position += this.mThumbnailLength;
        }
        int totalSize3 = position;
        if (this.mMimeType == 4) {
            totalSize3 += 8;
        }
        if (DEBUG) {
            for (int i3 = 0; i3 < EXIF_TAGS.length; i3++) {
                Log.d(TAG, String.format("index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d", Integer.valueOf(i3), Integer.valueOf(ifdOffsets[i3]), Integer.valueOf(this.mAttributes[i3].size()), Integer.valueOf(ifdDataSizes[i3]), Integer.valueOf(totalSize3)));
            }
        }
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(ifdOffsets[1], this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(ifdOffsets[2], this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(ifdOffsets[3], this.mExifByteOrder));
        }
        switch (this.mMimeType) {
            case 4:
                dataOutputStream.writeUnsignedShort(totalSize3);
                dataOutputStream.write(IDENTIFIER_EXIF_APP1);
                break;
            case 13:
                dataOutputStream.writeInt(totalSize3);
                dataOutputStream.write(PNG_CHUNK_TYPE_EXIF);
                break;
            case 14:
                dataOutputStream.write(WEBP_CHUNK_TYPE_EXIF);
                dataOutputStream.writeInt(totalSize3);
                break;
        }
        dataOutputStream.writeShort(this.mExifByteOrder == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
        dataOutputStream.setByteOrder(this.mExifByteOrder);
        dataOutputStream.writeUnsignedShort(42);
        dataOutputStream.writeUnsignedInt(8L);
        int ifdType3 = 0;
        while (ifdType3 < EXIF_TAGS.length) {
            if (this.mAttributes[ifdType3].isEmpty()) {
                totalSize = totalSize3;
            } else {
                dataOutputStream.writeUnsignedShort(this.mAttributes[ifdType3].size());
                int dataOffset = ifdOffsets[ifdType3] + i + (this.mAttributes[ifdType3].size() * 12) + 4;
                for (Map.Entry<String, ExifAttribute> entry2 : this.mAttributes[ifdType3].entrySet()) {
                    ExifTag tag2 = sExifTagMapsForWriting[ifdType3].get(entry2.getKey());
                    int tagNumber = tag2.number;
                    ExifAttribute attribute = entry2.getValue();
                    int size2 = attribute.size();
                    dataOutputStream.writeUnsignedShort(tagNumber);
                    dataOutputStream.writeUnsignedShort(attribute.format);
                    dataOutputStream.writeInt(attribute.numberOfComponents);
                    if (size2 > 4) {
                        totalSize2 = totalSize3;
                        dataOutputStream.writeUnsignedInt(dataOffset);
                        dataOffset += size2;
                    } else {
                        totalSize2 = totalSize3;
                        dataOutputStream.write(attribute.bytes);
                        if (size2 < 4) {
                            int i4 = size2;
                            for (int i5 = 4; i4 < i5; i5 = 4) {
                                dataOutputStream.writeByte(0);
                                i4++;
                            }
                        }
                    }
                    totalSize3 = totalSize2;
                }
                totalSize = totalSize3;
                if (ifdType3 == 0 && !this.mAttributes[4].isEmpty()) {
                    dataOutputStream.writeUnsignedInt(ifdOffsets[4]);
                } else {
                    dataOutputStream.writeUnsignedInt(0L);
                }
                Iterator<Map.Entry<String, ExifAttribute>> it2 = this.mAttributes[ifdType3].entrySet().iterator();
                while (it2.hasNext()) {
                    ExifAttribute attribute2 = it2.next().getValue();
                    if (attribute2.bytes.length > 4) {
                        dataOutputStream.write(attribute2.bytes, 0, attribute2.bytes.length);
                    }
                }
            }
            ifdType3++;
            totalSize3 = totalSize;
            i = 2;
        }
        int totalSize4 = totalSize3;
        if (this.mHasThumbnail) {
            dataOutputStream.write(getThumbnailBytes());
        }
        if (this.mMimeType == 14 && totalSize4 % 2 == 1) {
            dataOutputStream.writeByte(0);
        }
        dataOutputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        return totalSize4;
    }

    private static Pair<Integer, Integer> guessDataFormat(String entryValue) {
        if (entryValue.contains(",")) {
            String[] entryValues = entryValue.split(",", -1);
            Pair<Integer, Integer> dataFormat = guessDataFormat(entryValues[0]);
            if (((Integer) dataFormat.first).intValue() == 2) {
                return dataFormat;
            }
            for (int i = 1; i < entryValues.length; i++) {
                Pair<Integer, Integer> guessDataFormat = guessDataFormat(entryValues[i]);
                int first = -1;
                int second = -1;
                if (((Integer) guessDataFormat.first).equals(dataFormat.first) || ((Integer) guessDataFormat.second).equals(dataFormat.first)) {
                    first = ((Integer) dataFormat.first).intValue();
                }
                if (((Integer) dataFormat.second).intValue() != -1 && (((Integer) guessDataFormat.first).equals(dataFormat.second) || ((Integer) guessDataFormat.second).equals(dataFormat.second))) {
                    second = ((Integer) dataFormat.second).intValue();
                }
                if (first == -1 && second == -1) {
                    return new Pair<>(2, -1);
                }
                if (first == -1) {
                    dataFormat = new Pair<>(Integer.valueOf(second), -1);
                } else if (second == -1) {
                    dataFormat = new Pair<>(Integer.valueOf(first), -1);
                }
            }
            return dataFormat;
        }
        if (entryValue.contains("/")) {
            String[] rationalNumber = entryValue.split("/", -1);
            if (rationalNumber.length == 2) {
                try {
                    long numerator = (long) Double.parseDouble(rationalNumber[0]);
                    long denominator = (long) Double.parseDouble(rationalNumber[1]);
                    if (numerator >= 0 && denominator >= 0) {
                        if (numerator <= 2147483647L && denominator <= 2147483647L) {
                            return new Pair<>(10, 5);
                        }
                        return new Pair<>(5, -1);
                    }
                    return new Pair<>(10, -1);
                } catch (NumberFormatException e) {
                }
            }
            return new Pair<>(2, -1);
        }
        try {
            Long longValue = Long.valueOf(Long.parseLong(entryValue));
            if (longValue.longValue() >= 0 && longValue.longValue() <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                return new Pair<>(3, 4);
            }
            if (longValue.longValue() < 0) {
                return new Pair<>(9, -1);
            }
            return new Pair<>(4, -1);
        } catch (NumberFormatException e2) {
            try {
                Double.parseDouble(entryValue);
                return new Pair<>(12, -1);
            } catch (NumberFormatException e3) {
                return new Pair<>(2, -1);
            }
        }
    }

    private static class ByteOrderedDataInputStream extends InputStream implements DataInput {
        private ByteOrder mByteOrder;
        private DataInputStream mDataInputStream;
        final int mLength;
        int mPosition;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;

        public ByteOrderedDataInputStream(InputStream in) throws IOException {
            this(in, ByteOrder.BIG_ENDIAN);
        }

        ByteOrderedDataInputStream(InputStream in, ByteOrder byteOrder) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            this.mDataInputStream = new DataInputStream(in);
            this.mLength = this.mDataInputStream.available();
            this.mPosition = 0;
            this.mDataInputStream.mark(this.mLength);
            this.mByteOrder = byteOrder;
        }

        public ByteOrderedDataInputStream(byte[] bytes) throws IOException {
            this(new ByteArrayInputStream(bytes));
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        public void seek(long byteCount) throws IOException {
            if (this.mPosition > byteCount) {
                this.mPosition = 0;
                this.mDataInputStream.reset();
                this.mDataInputStream.mark(this.mLength);
            } else {
                byteCount -= this.mPosition;
            }
            if (skipBytes((int) byteCount) != ((int) byteCount)) {
                throw new IOException("Couldn't seek up to the byteCount");
            }
        }

        public int peek() {
            return this.mPosition;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.mDataInputStream.available();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.read();
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            int bytesRead = this.mDataInputStream.read(b, off, len);
            this.mPosition += bytesRead;
            return bytesRead;
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            Log.d(ExifInterface.TAG, "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public boolean readBoolean() throws IOException {
            this.mPosition++;
            return this.mDataInputStream.readBoolean();
        }

        @Override // java.io.DataInput
        public char readChar() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readChar();
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            this.mPosition += 2;
            return this.mDataInputStream.readUTF();
        }

        @Override // java.io.DataInput
        public void readFully(byte[] buffer, int offset, int length) throws IOException {
            this.mPosition += length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            if (this.mDataInputStream.read(buffer, offset, length) != length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public void readFully(byte[] buffer) throws IOException {
            this.mPosition += buffer.length;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            if (this.mDataInputStream.read(buffer, 0, buffer.length) != buffer.length) {
                throw new IOException("Couldn't read up to the length of buffer");
            }
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            this.mPosition++;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch = this.mDataInputStream.read();
            if (ch < 0) {
                throw new EOFException();
            }
            return (byte) ch;
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            if ((ch1 | ch2) < 0) {
                throw new EOFException();
            }
            if (this.mByteOrder == LITTLE_ENDIAN) {
                return (short) ((ch2 << 8) + ch1);
            }
            if (this.mByteOrder == BIG_ENDIAN) {
                return (short) ((ch1 << 8) + ch2);
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            this.mPosition += 4;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            int ch3 = this.mDataInputStream.read();
            int ch4 = this.mDataInputStream.read();
            if ((ch1 | ch2 | ch3 | ch4) < 0) {
                throw new EOFException();
            }
            if (this.mByteOrder == LITTLE_ENDIAN) {
                return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + ch1;
            }
            if (this.mByteOrder == BIG_ENDIAN) {
                return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        @Override // java.io.DataInput
        public int skipBytes(int byteCount) throws IOException {
            int totalSkip = Math.min(byteCount, this.mLength - this.mPosition);
            int skipped = 0;
            while (skipped < totalSkip) {
                skipped += this.mDataInputStream.skipBytes(totalSkip - skipped);
            }
            this.mPosition += skipped;
            return skipped;
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            this.mPosition += 2;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            if ((ch1 | ch2) < 0) {
                throw new EOFException();
            }
            if (this.mByteOrder == LITTLE_ENDIAN) {
                return (ch2 << 8) + ch1;
            }
            if (this.mByteOrder == BIG_ENDIAN) {
                return (ch1 << 8) + ch2;
            }
            throw new IOException("Invalid byte order: " + this.mByteOrder);
        }

        public long readUnsignedInt() throws IOException {
            return readInt() & 4294967295L;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            this.mPosition += 8;
            if (this.mPosition > this.mLength) {
                throw new EOFException();
            }
            int ch1 = this.mDataInputStream.read();
            int ch2 = this.mDataInputStream.read();
            int ch3 = this.mDataInputStream.read();
            int ch4 = this.mDataInputStream.read();
            int ch5 = this.mDataInputStream.read();
            int ch6 = this.mDataInputStream.read();
            int ch7 = this.mDataInputStream.read();
            int ch8 = this.mDataInputStream.read();
            if ((ch1 | ch2 | ch3 | ch4 | ch5 | ch6 | ch7 | ch8) < 0) {
                throw new EOFException();
            }
            if (this.mByteOrder != LITTLE_ENDIAN) {
                if (this.mByteOrder == BIG_ENDIAN) {
                    return (ch1 << 56) + (ch2 << 48) + (ch3 << 40) + (ch4 << 32) + (ch5 << 24) + (ch6 << 16) + (ch7 << 8) + ch8;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            return (ch8 << 56) + (ch7 << 48) + (ch6 << 40) + (ch5 << 32) + (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + ch1;
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.InputStream
        public synchronized void mark(int readlimit) {
            this.mDataInputStream.mark(readlimit);
        }

        public int getLength() {
            return this.mLength;
        }
    }

    private static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        final OutputStream mOutputStream;

        public ByteOrderedDataOutputStream(OutputStream out, ByteOrder byteOrder) {
            super(out);
            this.mOutputStream = out;
            this.mByteOrder = byteOrder;
        }

        public void setByteOrder(ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bytes) throws IOException {
            this.mOutputStream.write(bytes);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bytes, int offset, int length) throws IOException {
            this.mOutputStream.write(bytes, offset, length);
        }

        public void writeByte(int val) throws IOException {
            this.mOutputStream.write(val);
        }

        public void writeShort(short val) throws IOException {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((val >>> 0) & 255);
                this.mOutputStream.write((val >>> 8) & 255);
            } else if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((val >>> 8) & 255);
                this.mOutputStream.write((val >>> 0) & 255);
            }
        }

        public void writeInt(int val) throws IOException {
            if (this.mByteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.mOutputStream.write((val >>> 0) & 255);
                this.mOutputStream.write((val >>> 8) & 255);
                this.mOutputStream.write((val >>> 16) & 255);
                this.mOutputStream.write((val >>> 24) & 255);
                return;
            }
            if (this.mByteOrder == ByteOrder.BIG_ENDIAN) {
                this.mOutputStream.write((val >>> 24) & 255);
                this.mOutputStream.write((val >>> 16) & 255);
                this.mOutputStream.write((val >>> 8) & 255);
                this.mOutputStream.write((val >>> 0) & 255);
            }
        }

        public void writeUnsignedShort(int val) throws IOException {
            writeShort((short) val);
        }

        public void writeUnsignedInt(long val) throws IOException {
            writeInt((int) val);
        }
    }

    private void swapBasedOnImageSize(int firstIfdType, int secondIfdType) throws IOException {
        if (this.mAttributes[firstIfdType].isEmpty() || this.mAttributes[secondIfdType].isEmpty()) {
            if (DEBUG) {
                Log.d(TAG, "Cannot perform swap since only one image data exists");
                return;
            }
            return;
        }
        ExifAttribute firstImageLengthAttribute = this.mAttributes[firstIfdType].get("ImageLength");
        ExifAttribute firstImageWidthAttribute = this.mAttributes[firstIfdType].get("ImageWidth");
        ExifAttribute secondImageLengthAttribute = this.mAttributes[secondIfdType].get("ImageLength");
        ExifAttribute secondImageWidthAttribute = this.mAttributes[secondIfdType].get("ImageWidth");
        if (firstImageLengthAttribute == null || firstImageWidthAttribute == null) {
            if (DEBUG) {
                Log.d(TAG, "First image does not contain valid size information");
                return;
            }
            return;
        }
        if (secondImageLengthAttribute == null || secondImageWidthAttribute == null) {
            if (DEBUG) {
                Log.d(TAG, "Second image does not contain valid size information");
                return;
            }
            return;
        }
        int firstImageLengthValue = firstImageLengthAttribute.getIntValue(this.mExifByteOrder);
        int firstImageWidthValue = firstImageWidthAttribute.getIntValue(this.mExifByteOrder);
        int secondImageLengthValue = secondImageLengthAttribute.getIntValue(this.mExifByteOrder);
        int secondImageWidthValue = secondImageWidthAttribute.getIntValue(this.mExifByteOrder);
        if (firstImageLengthValue < secondImageLengthValue && firstImageWidthValue < secondImageWidthValue) {
            HashMap<String, ExifAttribute> tempMap = this.mAttributes[firstIfdType];
            this.mAttributes[firstIfdType] = this.mAttributes[secondIfdType];
            this.mAttributes[secondIfdType] = tempMap;
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    private static void closeFileDescriptor(FileDescriptor fd) {
        try {
            Os.close(fd);
        } catch (Exception e) {
            Log.e(TAG, "Error closing fd.");
        }
    }

    private static int copy(InputStream in, OutputStream out) throws IOException {
        int total = 0;
        byte[] buffer = new byte[8192];
        while (true) {
            int c = in.read(buffer);
            if (c != -1) {
                total += c;
                out.write(buffer, 0, c);
            } else {
                return total;
            }
        }
    }

    private static void copy(InputStream in, OutputStream out, int numBytes) throws IOException {
        int remainder = numBytes;
        byte[] buffer = new byte[8192];
        while (remainder > 0) {
            int bytesToRead = Math.min(remainder, 8192);
            int bytesRead = in.read(buffer, 0, bytesToRead);
            if (bytesRead != bytesToRead) {
                throw new IOException("Failed to copy the given amount of bytes from the inputstream to the output stream.");
            }
            remainder -= bytesRead;
            out.write(buffer, 0, bytesRead);
        }
    }

    private static long[] convertToLongArray(Object inputObj) {
        if (inputObj instanceof int[]) {
            int[] input = (int[]) inputObj;
            long[] result = new long[input.length];
            for (int i = 0; i < input.length; i++) {
                result[i] = input[i];
            }
            return result;
        }
        if (inputObj instanceof long[]) {
            return (long[]) inputObj;
        }
        return null;
    }

    private static boolean startsWith(byte[] cur, byte[] val) {
        if (cur == null || val == null || cur.length < val.length) {
            return false;
        }
        for (int i = 0; i < val.length; i++) {
            if (cur[i] != val[i]) {
                return false;
            }
        }
        return true;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    private boolean isSupportedFormatForSavingAttributes() {
        if (this.mMimeType == 4 || this.mMimeType == 13 || this.mMimeType == 14) {
            return true;
        }
        return false;
    }
}
