package android.support.media;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Log;
import android.util.Pair;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import androidx.work.WorkRequest;
import com.budiyev.android.codescanner.BarcodeUtils;
import com.github.mikephil.charting.utils.Utils;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
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
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.CharEncoding;

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
    private static final boolean DEBUG = false;
    private static final ExifTag[] EXIF_POINTER_TAGS;
    static final ExifTag[][] EXIF_TAGS;
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
    private static final ExifTag[] IFD_EXIF_TAGS;
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
    private static final ExifTag[] IFD_GPS_TAGS;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS;
    private static final int IFD_OFFSET = 8;
    private static final ExifTag[] IFD_THUMBNAIL_TAGS;
    private static final ExifTag[] IFD_TIFF_TAGS;
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
    private static final int IMAGE_TYPE_JPEG = 4;
    private static final int IMAGE_TYPE_NEF = 5;
    private static final int IMAGE_TYPE_NRW = 6;
    private static final int IMAGE_TYPE_ORF = 7;
    private static final int IMAGE_TYPE_PEF = 8;
    private static final int IMAGE_TYPE_RAF = 9;
    private static final int IMAGE_TYPE_RW2 = 10;
    private static final int IMAGE_TYPE_SRW = 11;
    private static final int IMAGE_TYPE_UNKNOWN = 0;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_LENGTH_TAG;
    private static final ExifTag JPEG_INTERCHANGE_FORMAT_TAG;
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
    private static final ExifTag[] ORF_CAMERA_SETTINGS_TAGS;
    private static final ExifTag[] ORF_IMAGE_PROCESSING_TAGS;
    private static final int ORF_MAKER_NOTE_HEADER_1_SIZE = 8;
    private static final int ORF_MAKER_NOTE_HEADER_2_SIZE = 12;
    private static final ExifTag[] ORF_MAKER_NOTE_TAGS;
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
    private static final ExifTag[] PEF_TAGS;
    public static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    private static final int RAF_INFO_SIZE = 160;
    private static final int RAF_JPEG_LENGTH_VALUE_SIZE = 4;
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
    static final byte START_CODE = 42;
    public static final short SUBJECT_DISTANCE_RANGE_CLOSE_VIEW = 2;
    public static final short SUBJECT_DISTANCE_RANGE_DISTANT_VIEW = 3;
    public static final short SUBJECT_DISTANCE_RANGE_MACRO = 1;
    public static final short SUBJECT_DISTANCE_RANGE_UNKNOWN = 0;
    private static final String TAG = "ExifInterface";
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BODY_SERIAL_NUMBER = "BodySerialNumber";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
    public static final String TAG_CAMARA_OWNER_NAME = "CameraOwnerName";
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
    private static final String TAG_EXIF_IFD_POINTER = "ExifIFDPointer";
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
    private static final String TAG_GPS_INFO_IFD_POINTER = "GPSInfoIFDPointer";
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
    private static final String TAG_HAS_THUMBNAIL = "HasThumbnail";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    private static final String TAG_INTEROPERABILITY_IFD_POINTER = "InteroperabilityIFDPointer";
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
    private static final String TAG_ORF_CAMERA_SETTINGS_IFD_POINTER = "CameraSettingsIFDPointer";
    private static final String TAG_ORF_IMAGE_PROCESSING_IFD_POINTER = "ImageProcessingIFDPointer";
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
    private static final ExifTag TAG_RAF_IMAGE_SIZE;
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
    private static final String TAG_SUB_IFD_POINTER = "SubIFDPointer";
    private static final String TAG_THUMBNAIL_DATA = "ThumbnailData";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    private static final String TAG_THUMBNAIL_LENGTH = "ThumbnailLength";
    private static final String TAG_THUMBNAIL_OFFSET = "ThumbnailOffset";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";

    @Deprecated
    public static final int WHITEBALANCE_AUTO = 0;

    @Deprecated
    public static final int WHITEBALANCE_MANUAL = 1;
    public static final short WHITE_BALANCE_AUTO = 0;
    public static final short WHITE_BALANCE_MANUAL = 1;
    public static final short Y_CB_CR_POSITIONING_CENTERED = 1;
    public static final short Y_CB_CR_POSITIONING_CO_SITED = 2;
    private static final HashMap<Integer, ExifTag>[] sExifTagMapsForReading;
    private static final HashMap<String, ExifTag>[] sExifTagMapsForWriting;
    private static final Pattern sGpsTimestampPattern;
    private static final Pattern sNonZeroTimePattern;
    private final AssetManager.AssetInputStream mAssetInputStream;
    private final HashMap<String, ExifAttribute>[] mAttributes;
    private ByteOrder mExifByteOrder;
    private int mExifOffset;
    private final String mFilename;
    private boolean mHasThumbnail;
    private boolean mIsSupportedFile;
    private int mMimeType;
    private int mOrfMakerNoteOffset;
    private int mOrfThumbnailLength;
    private int mOrfThumbnailOffset;
    private int mRw2JpgFromRawOffset;
    private byte[] mThumbnailBytes;
    private int mThumbnailCompression;
    private int mThumbnailLength;
    private int mThumbnailOffset;
    private static final List<Integer> ROTATION_ORDER = Arrays.asList(1, 6, 3, 8);
    private static final List<Integer> FLIPPED_ROTATION_ORDER = Arrays.asList(2, 7, 4, 5);
    public static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    private static final byte MARKER_SOI = -40;
    static final byte[] JPEG_SIGNATURE = {-1, MARKER_SOI, -1};
    private static final byte[] ORF_MAKER_NOTE_HEADER_1 = {79, 76, 89, 77, 80, 0};
    private static final byte[] ORF_MAKER_NOTE_HEADER_2 = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    static final String[] IFD_FORMAT_NAMES = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE"};
    static final int[] IFD_FORMAT_BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    private static final byte[] EXIF_ASCII_PREFIX = {65, 83, 67, 73, 73, 0, 0, 0};
    private static final HashSet<String> sTagSetForCompatibility = new HashSet<>(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
    private static final HashMap<Integer, Integer> sExifPointerTagMap = new HashMap<>();
    private static final Charset ASCII = Charset.forName(CharEncoding.US_ASCII);
    static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ASCII);
    private static SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

    @Retention(RetentionPolicy.SOURCE)
    public @interface IfdType {
    }

    static {
        int i = 4;
        int i2 = 3;
        int i3 = 2;
        int i4 = 4;
        int i5 = 3;
        int i6 = 5;
        int i7 = 5;
        int i8 = 23;
        int i9 = 7;
        IFD_TIFF_TAGS = new ExifTag[]{new ExifTag("NewSubfileType", 254, i), new ExifTag("SubfileType", 255, i), new ExifTag("ImageWidth", 256, i5, i4), new ExifTag("ImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, i2), new ExifTag("Compression", 259, i2), new ExifTag("PhotometricInterpretation", 262, i2), new ExifTag("ImageDescription", BarcodeUtils.ROTATION_270, i3), new ExifTag("Make", 271, i3), new ExifTag("Model", 272, i3), new ExifTag("StripOffsets", 273, i5, i4), new ExifTag("Orientation", 274, i2), new ExifTag("SamplesPerPixel", 277, i2), new ExifTag("RowsPerStrip", 278, i5, i4), new ExifTag("StripByteCounts", 279, i5, i4), new ExifTag("XResolution", 282, i6), new ExifTag("YResolution", 283, i6), new ExifTag("PlanarConfiguration", 284, i2), new ExifTag("ResolutionUnit", 296, i2), new ExifTag("TransferFunction", 301, i2), new ExifTag("Software", 305, i3), new ExifTag("DateTime", 306, i3), new ExifTag("Artist", 315, i3), new ExifTag("WhitePoint", TypedValues.AttributesType.TYPE_PIVOT_TARGET, i7), new ExifTag("PrimaryChromaticities", 319, i7), new ExifTag(TAG_SUB_IFD_POINTER, 330, i), new ExifTag("JPEGInterchangeFormat", InputDeviceCompat.SOURCE_DPAD, i), new ExifTag("JPEGInterchangeFormatLength", 514, i), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, i2), new ExifTag("YCbCrPositioning", 531, i2), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, i3), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, i), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, i), new ExifTag("SensorTopBorder", i, i), new ExifTag("SensorLeftBorder", 5, i), new ExifTag("SensorBottomBorder", 6, i), new ExifTag("SensorRightBorder", i9, i), new ExifTag("ISO", i8, i2), new ExifTag("JpgFromRaw", 46, i9)};
        int i10 = 5;
        int i11 = 7;
        int i12 = 5;
        int i13 = 10;
        int i14 = 5;
        int i15 = 7;
        int i16 = 4;
        int i17 = 3;
        int i18 = 5;
        int i19 = 7;
        IFD_EXIF_TAGS = new ExifTag[]{new ExifTag("ExposureTime", 33434, i10), new ExifTag("FNumber", 33437, i10), new ExifTag("ExposureProgram", 34850, i2), new ExifTag("SpectralSensitivity", 34852, i3), new ExifTag("PhotographicSensitivity", 34855, i2), new ExifTag("OECF", 34856, i11), new ExifTag("ExifVersion", 36864, i3), new ExifTag("DateTimeOriginal", 36867, i3), new ExifTag("DateTimeDigitized", 36868, i3), new ExifTag("ComponentsConfiguration", 37121, i11), new ExifTag("CompressedBitsPerPixel", 37122, i12), new ExifTag("ShutterSpeedValue", 37377, i13), new ExifTag("ApertureValue", 37378, i12), new ExifTag("BrightnessValue", 37379, i13), new ExifTag("ExposureBiasValue", 37380, i13), new ExifTag("MaxApertureValue", 37381, i14), new ExifTag("SubjectDistance", 37382, i14), new ExifTag("MeteringMode", 37383, i2), new ExifTag("LightSource", 37384, i2), new ExifTag("Flash", 37385, i2), new ExifTag("FocalLength", 37386, 5), new ExifTag("SubjectArea", 37396, i2), new ExifTag("MakerNote", 37500, i15), new ExifTag("UserComment", 37510, i15), new ExifTag("SubSecTime", 37520, i3), new ExifTag("SubSecTimeOriginal", 37521, i3), new ExifTag("SubSecTimeDigitized", 37522, i3), new ExifTag("FlashpixVersion", 40960, 7), new ExifTag("ColorSpace", 40961, i2), new ExifTag("PixelXDimension", 40962, i17, i16), new ExifTag("PixelYDimension", 40963, i17, i16), new ExifTag("RelatedSoundFile", 40964, i3), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, 4), new ExifTag("FlashEnergy", 41483, 5), new ExifTag("SpatialFrequencyResponse", 41484, 7), new ExifTag("FocalPlaneXResolution", 41486, i18), new ExifTag("FocalPlaneYResolution", 41487, i18), new ExifTag("FocalPlaneResolutionUnit", 41488, i2), new ExifTag("SubjectLocation", 41492, i2), new ExifTag("ExposureIndex", 41493, 5), new ExifTag("SensingMethod", 41495, i2), new ExifTag("FileSource", 41728, i19), new ExifTag("SceneType", 41729, i19), new ExifTag("CFAPattern", 41730, i19), new ExifTag("CustomRendered", 41985, i2), new ExifTag("ExposureMode", 41986, i2), new ExifTag("WhiteBalance", 41987, i2), new ExifTag("DigitalZoomRatio", 41988, 5), new ExifTag("FocalLengthIn35mmFilm", 41989, i2), new ExifTag("SceneCaptureType", 41990, i2), new ExifTag("GainControl", 41991, i2), new ExifTag("Contrast", 41992, i2), new ExifTag("Saturation", 41993, i2), new ExifTag("Sharpness", 41994, i2), new ExifTag("DeviceSettingDescription", 41995, 7), new ExifTag("SubjectDistanceRange", 41996, i2), new ExifTag("ImageUniqueID", 42016, i3), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, i17, i16)};
        int i20 = 1;
        int i21 = 5;
        int i22 = 5;
        int i23 = 5;
        int i24 = 7;
        IFD_GPS_TAGS = new ExifTag[]{new ExifTag("GPSVersionID", 0, i20), new ExifTag("GPSLatitudeRef", i20, i3), new ExifTag("GPSLatitude", i3, i21), new ExifTag("GPSLongitudeRef", i2, i3), new ExifTag("GPSLongitude", 4, i21), new ExifTag("GPSAltitudeRef", i21, 1), new ExifTag("GPSAltitude", 6, i21), new ExifTag("GPSTimeStamp", 7, i21), new ExifTag("GPSSatellites", 8, i3), new ExifTag("GPSStatus", 9, i3), new ExifTag("GPSMeasureMode", 10, i3), new ExifTag("GPSDOP", 11, i22), new ExifTag("GPSSpeedRef", 12, i3), new ExifTag("GPSSpeed", 13, i22), new ExifTag("GPSTrackRef", 14, i3), new ExifTag("GPSTrack", 15, i22), new ExifTag("GPSImgDirectionRef", 16, i3), new ExifTag("GPSImgDirection", 17, i22), new ExifTag("GPSMapDatum", 18, i3), new ExifTag("GPSDestLatitudeRef", 19, i3), new ExifTag("GPSDestLatitude", 20, 5), new ExifTag("GPSDestLongitudeRef", 21, i3), new ExifTag("GPSDestLongitude", 22, i23), new ExifTag("GPSDestBearingRef", i8, i3), new ExifTag("GPSDestBearing", 24, i23), new ExifTag("GPSDestDistanceRef", 25, i3), new ExifTag("GPSDestDistance", 26, 5), new ExifTag("GPSProcessingMethod", 27, i24), new ExifTag("GPSAreaInformation", 28, i24), new ExifTag("GPSDateStamp", 29, i3), new ExifTag("GPSDifferential", 30, i2)};
        IFD_INTEROPERABILITY_TAGS = new ExifTag[]{new ExifTag("InteroperabilityIndex", 1, i3)};
        int i25 = 4;
        int i26 = 5;
        int i27 = 5;
        int i28 = 4;
        int i29 = 4;
        IFD_THUMBNAIL_TAGS = new ExifTag[]{new ExifTag("NewSubfileType", 254, i25), new ExifTag("SubfileType", 255, i25), new ExifTag("ThumbnailImageWidth", 256, i17, i16), new ExifTag("ThumbnailImageLength", 257, 3, 4), new ExifTag("BitsPerSample", 258, i2), new ExifTag("Compression", 259, i2), new ExifTag("PhotometricInterpretation", 262, i2), new ExifTag("ImageDescription", BarcodeUtils.ROTATION_270, i3), new ExifTag("Make", 271, i3), new ExifTag("Model", 272, i3), new ExifTag("StripOffsets", 273, i17, i16), new ExifTag("Orientation", 274, i2), new ExifTag("SamplesPerPixel", 277, i2), new ExifTag("RowsPerStrip", 278, i17, i16), new ExifTag("StripByteCounts", 279, i17, i16), new ExifTag("XResolution", 282, i26), new ExifTag("YResolution", 283, i26), new ExifTag("PlanarConfiguration", 284, i2), new ExifTag("ResolutionUnit", 296, i2), new ExifTag("TransferFunction", 301, i2), new ExifTag("Software", 305, i3), new ExifTag("DateTime", 306, i3), new ExifTag("Artist", 315, i3), new ExifTag("WhitePoint", TypedValues.AttributesType.TYPE_PIVOT_TARGET, i27), new ExifTag("PrimaryChromaticities", 319, i27), new ExifTag(TAG_SUB_IFD_POINTER, 330, i28), new ExifTag("JPEGInterchangeFormat", InputDeviceCompat.SOURCE_DPAD, i28), new ExifTag("JPEGInterchangeFormatLength", 514, i28), new ExifTag("YCbCrCoefficients", 529, 5), new ExifTag("YCbCrSubSampling", 530, i2), new ExifTag("YCbCrPositioning", 531, i2), new ExifTag("ReferenceBlackWhite", 532, 5), new ExifTag("Copyright", 33432, i3), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, i29), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, i29), new ExifTag("DNGVersion", 50706, 1), new ExifTag("DefaultCropSize", 50720, i17, i16)};
        TAG_RAF_IMAGE_SIZE = new ExifTag("StripOffsets", 273, i2);
        int i30 = 4;
        ORF_MAKER_NOTE_TAGS = new ExifTag[]{new ExifTag("ThumbnailImage", 256, 7), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, i30), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, i30)};
        ORF_CAMERA_SETTINGS_TAGS = new ExifTag[]{new ExifTag("PreviewImageStart", 257, i30), new ExifTag("PreviewImageLength", 258, i30)};
        ORF_IMAGE_PROCESSING_TAGS = new ExifTag[]{new ExifTag("AspectFrame", 4371, i2)};
        PEF_TAGS = new ExifTag[]{new ExifTag("ColorSpace", 55, i2)};
        EXIF_TAGS = new ExifTag[][]{IFD_TIFF_TAGS, IFD_EXIF_TAGS, IFD_GPS_TAGS, IFD_INTEROPERABILITY_TAGS, IFD_THUMBNAIL_TAGS, IFD_TIFF_TAGS, ORF_MAKER_NOTE_TAGS, ORF_CAMERA_SETTINGS_TAGS, ORF_IMAGE_PROCESSING_TAGS, PEF_TAGS};
        int i31 = 4;
        int i32 = 1;
        EXIF_POINTER_TAGS = new ExifTag[]{new ExifTag(TAG_SUB_IFD_POINTER, 330, i31), new ExifTag(TAG_EXIF_IFD_POINTER, 34665, i31), new ExifTag(TAG_GPS_INFO_IFD_POINTER, 34853, i31), new ExifTag(TAG_INTEROPERABILITY_IFD_POINTER, 40965, i31), new ExifTag(TAG_ORF_CAMERA_SETTINGS_IFD_POINTER, 8224, i32), new ExifTag(TAG_ORF_IMAGE_PROCESSING_IFD_POINTER, 8256, i32)};
        JPEG_INTERCHANGE_FORMAT_TAG = new ExifTag("JPEGInterchangeFormat", InputDeviceCompat.SOURCE_DPAD, i31);
        JPEG_INTERCHANGE_FORMAT_LENGTH_TAG = new ExifTag("JPEGInterchangeFormatLength", 514, i31);
        sExifTagMapsForReading = new HashMap[EXIF_TAGS.length];
        sExifTagMapsForWriting = new HashMap[EXIF_TAGS.length];
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
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
        sNonZeroTimePattern = Pattern.compile(".*[1-9].*");
        sGpsTimestampPattern = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    }

    private static class Rational {
        public final long denominator;
        public final long numerator;

        private Rational(double value) {
            this((long) (10000.0d * value), WorkRequest.MIN_BACKOFF_MILLIS);
        }

        private Rational(long numerator, long denominator) {
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
        public final byte[] bytes;
        public final int format;
        public final int numberOfComponents;

        private ExifAttribute(int format, int numberOfComponents, byte[] bytes) {
            this.format = format;
            this.numberOfComponents = numberOfComponents;
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
            if (value.length() != 1 || value.charAt(0) < '0' || value.charAt(0) > '1') {
                byte[] ascii = value.getBytes(ExifInterface.ASCII);
                return new ExifAttribute(1, ascii.length, ascii);
            }
            byte[] bytes = {(byte) (value.charAt(0) - '0')};
            return new ExifAttribute(1, bytes.length, bytes);
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

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:174:0x0211 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:181:? A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object getValue(ByteOrder byteOrder) {
            int ch;
            ByteOrderedDataInputStream inputStream = null;
            try {
                try {
                    inputStream = new ByteOrderedDataInputStream(this.bytes);
                    try {
                        inputStream.setByteOrder(byteOrder);
                        switch (this.format) {
                            case 1:
                            case 6:
                                if (this.bytes.length != 1 || this.bytes[0] < 0 || this.bytes[0] > 1) {
                                    String str = new String(this.bytes, ExifInterface.ASCII);
                                    try {
                                        inputStream.close();
                                    } catch (IOException e) {
                                        Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e);
                                    }
                                    return str;
                                }
                                String str2 = new String(new char[]{(char) (this.bytes[0] + 48)});
                                try {
                                    inputStream.close();
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
                                for (int index2 = index; index2 < this.numberOfComponents && (ch = this.bytes[index2]) != 0; index2++) {
                                    if (ch >= 32) {
                                        stringBuilder.append((char) ch);
                                    } else {
                                        stringBuilder.append('?');
                                    }
                                }
                                String sb = stringBuilder.toString();
                                try {
                                    inputStream.close();
                                } catch (IOException e3) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e3);
                                }
                                return sb;
                            case 3:
                                int[] values = new int[this.numberOfComponents];
                                for (int i2 = 0; i2 < this.numberOfComponents; i2++) {
                                    values[i2] = inputStream.readUnsignedShort();
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e4) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e4);
                                }
                                return values;
                            case 4:
                                long[] values2 = new long[this.numberOfComponents];
                                for (int i3 = 0; i3 < this.numberOfComponents; i3++) {
                                    values2[i3] = inputStream.readUnsignedInt();
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e5);
                                }
                                return values2;
                            case 5:
                                Rational[] values3 = new Rational[this.numberOfComponents];
                                for (int i4 = 0; i4 < this.numberOfComponents; i4++) {
                                    long numerator = inputStream.readUnsignedInt();
                                    long denominator = inputStream.readUnsignedInt();
                                    values3[i4] = new Rational(numerator, denominator);
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e6) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e6);
                                }
                                return values3;
                            case 8:
                                int[] values4 = new int[this.numberOfComponents];
                                for (int i5 = 0; i5 < this.numberOfComponents; i5++) {
                                    values4[i5] = inputStream.readShort();
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e7) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e7);
                                }
                                return values4;
                            case 9:
                                int[] values5 = new int[this.numberOfComponents];
                                for (int i6 = 0; i6 < this.numberOfComponents; i6++) {
                                    values5[i6] = inputStream.readInt();
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e8) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e8);
                                }
                                return values5;
                            case 10:
                                Rational[] values6 = new Rational[this.numberOfComponents];
                                for (int i7 = 0; i7 < this.numberOfComponents; i7++) {
                                    long numerator2 = inputStream.readInt();
                                    values6[i7] = new Rational(numerator2, inputStream.readInt());
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e9) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e9);
                                }
                                return values6;
                            case 11:
                                double[] values7 = new double[this.numberOfComponents];
                                for (int i8 = 0; i8 < this.numberOfComponents; i8++) {
                                    values7[i8] = inputStream.readFloat();
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e10) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e10);
                                }
                                return values7;
                            case 12:
                                double[] values8 = new double[this.numberOfComponents];
                                for (int i9 = 0; i9 < this.numberOfComponents; i9++) {
                                    values8[i9] = inputStream.readDouble();
                                }
                                try {
                                    inputStream.close();
                                } catch (IOException e11) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e11);
                                }
                                return values8;
                            default:
                                try {
                                    inputStream.close();
                                } catch (IOException e12) {
                                    Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e12);
                                }
                                return null;
                        }
                    } catch (IOException e13) {
                        e = e13;
                        Log.w(ExifInterface.TAG, "IOException occurred during reading a value", e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e14) {
                                Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e14);
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    if (0 != 0) {
                        throw th2;
                    }
                    try {
                        inputStream.close();
                        throw th2;
                    } catch (IOException e15) {
                        Log.e(ExifInterface.TAG, "IOException occurred while closing InputStream", e15);
                        throw th2;
                    }
                }
            } catch (IOException e16) {
                e = e16;
            } catch (Throwable th3) {
                th = th3;
                Throwable th22 = th;
                if (0 != 0) {
                }
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

        private ExifTag(String name, int number, int format) {
            this.name = name;
            this.number = number;
            this.primaryFormat = format;
            this.secondaryFormat = -1;
        }

        private ExifTag(String name, int number, int primaryFormat, int secondaryFormat) {
            this.name = name;
            this.number = number;
            this.primaryFormat = primaryFormat;
            this.secondaryFormat = secondaryFormat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFormatCompatible(int format) {
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

    public ExifInterface(String filename) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (filename == null) {
            throw new IllegalArgumentException("filename cannot be null");
        }
        FileInputStream in = null;
        this.mAssetInputStream = null;
        this.mFilename = filename;
        try {
            in = new FileInputStream(filename);
            loadAttributes(in);
        } finally {
            closeQuietly(in);
        }
    }

    public ExifInterface(InputStream inputStream) throws IOException {
        this.mAttributes = new HashMap[EXIF_TAGS.length];
        this.mExifByteOrder = ByteOrder.BIG_ENDIAN;
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream cannot be null");
        }
        this.mFilename = null;
        if (inputStream instanceof AssetManager.AssetInputStream) {
            this.mAssetInputStream = (AssetManager.AssetInputStream) inputStream;
        } else {
            this.mAssetInputStream = null;
        }
        loadAttributes(inputStream);
    }

    private ExifAttribute getExifAttribute(String tag) {
        if ("ISOSpeedRatings".equals(tag)) {
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
        ExifAttribute attribute = getExifAttribute(tag);
        if (attribute == null) {
            return null;
        }
        if (!sTagSetForCompatibility.contains(tag)) {
            return attribute.getStringValue(this.mExifByteOrder);
        }
        if (tag.equals("GPSTimeStamp")) {
            if (attribute.format == 5 || attribute.format == 10) {
                Rational[] array = (Rational[]) attribute.getValue(this.mExifByteOrder);
                if (array == null || array.length != 3) {
                    Log.w(TAG, "Invalid GPS Timestamp array. array=" + Arrays.toString(array));
                    return null;
                }
                return String.format("%02d:%02d:%02d", Integer.valueOf((int) (array[0].numerator / array[0].denominator)), Integer.valueOf((int) (array[1].numerator / array[1].denominator)), Integer.valueOf((int) (array[2].numerator / array[2].denominator)));
            }
            Log.w(TAG, "GPS Timestamp format is not rational. format=" + attribute.format);
            return null;
        }
        try {
            return Double.toString(attribute.getDoubleValue(this.mExifByteOrder));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int getAttributeInt(String tag, int defaultValue) {
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
        int i;
        int i2;
        int dataFormat;
        ExifInterface exifInterface = this;
        String value2 = value;
        String tag2 = tag;
        if ("ISOSpeedRatings".equals(tag2)) {
            tag2 = "PhotographicSensitivity";
        }
        int i3 = 2;
        int i4 = 1;
        if (value2 != null && sTagSetForCompatibility.contains(tag2)) {
            if (tag2.equals("GPSTimeStamp")) {
                Matcher m = sGpsTimestampPattern.matcher(value2);
                if (!m.find()) {
                    Log.w(TAG, "Invalid value for " + tag2 + " : " + value2);
                    return;
                }
                value2 = Integer.parseInt(m.group(1)) + "/1," + Integer.parseInt(m.group(2)) + "/1," + Integer.parseInt(m.group(3)) + "/1";
            } else {
                try {
                    double doubleValue = Double.parseDouble(value);
                    value2 = new Rational(doubleValue).toString();
                } catch (NumberFormatException e) {
                    Log.w(TAG, "Invalid value for " + tag2 + " : " + value2);
                    return;
                }
            }
        }
        int i5 = 0;
        while (i5 < EXIF_TAGS.length) {
            if (i5 == 4 && !exifInterface.mHasThumbnail) {
                i = i4;
                i2 = i5;
            } else {
                ExifTag exifTag = sExifTagMapsForWriting[i5].get(tag2);
                if (exifTag == null) {
                    i = i4;
                    i2 = i5;
                } else if (value2 == null) {
                    exifInterface.mAttributes[i5].remove(tag2);
                    i = i4;
                    i2 = i5;
                } else {
                    Pair<Integer, Integer> guess = guessDataFormat(value2);
                    if (exifTag.primaryFormat == ((Integer) guess.first).intValue() || exifTag.primaryFormat == ((Integer) guess.second).intValue()) {
                        dataFormat = exifTag.primaryFormat;
                    } else if (exifTag.secondaryFormat != -1 && (exifTag.secondaryFormat == ((Integer) guess.first).intValue() || exifTag.secondaryFormat == ((Integer) guess.second).intValue())) {
                        dataFormat = exifTag.secondaryFormat;
                    } else {
                        int dataFormat2 = exifTag.primaryFormat;
                        if (dataFormat2 == i4 || exifTag.primaryFormat == 7 || exifTag.primaryFormat == i3) {
                            dataFormat = exifTag.primaryFormat;
                        } else {
                            Log.w(TAG, "Given tag (" + tag2 + ") value didn't match with one of expected formats: " + IFD_FORMAT_NAMES[exifTag.primaryFormat] + (exifTag.secondaryFormat == -1 ? "" : ", " + IFD_FORMAT_NAMES[exifTag.secondaryFormat]) + " (guess: " + IFD_FORMAT_NAMES[((Integer) guess.first).intValue()] + (((Integer) guess.second).intValue() != -1 ? ", " + IFD_FORMAT_NAMES[((Integer) guess.second).intValue()] : "") + ")");
                            i = i4;
                            i2 = i5;
                        }
                    }
                    char c = 0;
                    String str = "/";
                    switch (dataFormat) {
                        case 1:
                            i = i4;
                            i2 = i5;
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createByte(value2));
                            break;
                        case 2:
                        case 7:
                            i = i4;
                            i2 = i5;
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createString(value2));
                            break;
                        case 3:
                            i = i4;
                            i2 = i5;
                            String[] values = value2.split(",");
                            int[] intArray = new int[values.length];
                            for (int j = 0; j < values.length; j++) {
                                intArray[j] = Integer.parseInt(values[j]);
                            }
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createUShort(intArray, exifInterface.mExifByteOrder));
                            break;
                        case 4:
                            i = i4;
                            i2 = i5;
                            String[] values2 = value2.split(",");
                            long[] longArray = new long[values2.length];
                            for (int j2 = 0; j2 < values2.length; j2++) {
                                longArray[j2] = Long.parseLong(values2[j2]);
                            }
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createULong(longArray, exifInterface.mExifByteOrder));
                            break;
                        case 5:
                            i2 = i5;
                            String[] values3 = value2.split(",");
                            Rational[] rationalArray = new Rational[values3.length];
                            for (int j3 = 0; j3 < values3.length; j3++) {
                                String[] numbers = values3[j3].split("/");
                                rationalArray[j3] = new Rational((long) Double.parseDouble(numbers[0]), (long) Double.parseDouble(numbers[1]));
                            }
                            i = 1;
                            exifInterface = this;
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createURational(rationalArray, exifInterface.mExifByteOrder));
                            break;
                        case 6:
                        case 8:
                        case 11:
                        default:
                            i = i4;
                            i2 = i5;
                            Log.w(TAG, "Data format isn't one of expected formats: " + dataFormat);
                            break;
                        case 9:
                            i2 = i5;
                            String[] values4 = value2.split(",");
                            int[] intArray2 = new int[values4.length];
                            for (int j4 = 0; j4 < values4.length; j4++) {
                                intArray2[j4] = Integer.parseInt(values4[j4]);
                            }
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createSLong(intArray2, exifInterface.mExifByteOrder));
                            i = 1;
                            break;
                        case 10:
                            String[] values5 = value2.split(",");
                            Rational[] rationalArray2 = new Rational[values5.length];
                            int j5 = 0;
                            while (j5 < values5.length) {
                                String[] numbers2 = values5[j5].split(str);
                                rationalArray2[j5] = new Rational((long) Double.parseDouble(numbers2[c]), (long) Double.parseDouble(numbers2[i4]));
                                j5++;
                                str = str;
                                i5 = i5;
                                i4 = 1;
                                c = 0;
                            }
                            i2 = i5;
                            exifInterface.mAttributes[i2].put(tag2, ExifAttribute.createSRational(rationalArray2, exifInterface.mExifByteOrder));
                            i = 1;
                            break;
                        case 12:
                            String[] values6 = value2.split(",");
                            double[] doubleArray = new double[values6.length];
                            for (int j6 = 0; j6 < values6.length; j6++) {
                                doubleArray[j6] = Double.parseDouble(values6[j6]);
                            }
                            exifInterface.mAttributes[i5].put(tag2, ExifAttribute.createDouble(doubleArray, exifInterface.mExifByteOrder));
                            i = i4;
                            i2 = i5;
                            break;
                    }
                }
            }
            i5 = i2 + 1;
            i4 = i;
            i3 = 2;
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

    private boolean updateAttribute(String tag, ExifAttribute value) {
        boolean updated = false;
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            if (this.mAttributes[i].containsKey(tag)) {
                this.mAttributes[i].put(tag, value);
                updated = true;
            }
        }
        return updated;
    }

    private void removeAttribute(String tag) {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            this.mAttributes[i].remove(tag);
        }
    }

    private void loadAttributes(InputStream in) throws IOException {
        for (int i = 0; i < EXIF_TAGS.length; i++) {
            try {
                try {
                    this.mAttributes[i] = new HashMap<>();
                } catch (IOException e) {
                    this.mIsSupportedFile = false;
                }
            } finally {
                addDefaultValuesForCompatibility();
            }
        }
        InputStream in2 = new BufferedInputStream(in, SIGNATURE_CHECK_SIZE);
        this.mMimeType = getMimeType((BufferedInputStream) in2);
        ByteOrderedDataInputStream inputStream = new ByteOrderedDataInputStream(in2);
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
                break;
            case 7:
                getOrfAttributes(inputStream);
                break;
            case 9:
                getRafAttributes(inputStream);
                break;
            case 10:
                getRw2Attributes(inputStream);
                break;
        }
        setThumbnailData(inputStream);
        this.mIsSupportedFile = true;
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
        if (!this.mIsSupportedFile || this.mMimeType != 4) {
            throw new IOException("ExifInterface only supports saving attributes on JPEG formats.");
        }
        if (this.mFilename == null) {
            throw new IOException("ExifInterface does not support saving attributes for the current input.");
        }
        this.mThumbnailBytes = getThumbnail();
        File tempFile = new File(this.mFilename + ".tmp");
        File originalFile = new File(this.mFilename);
        if (!originalFile.renameTo(tempFile)) {
            throw new IOException("Could not rename to " + tempFile.getAbsolutePath());
        }
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(tempFile);
            out = new FileOutputStream(this.mFilename);
            saveJpegAttributes(in, out);
            closeQuietly(in);
            closeQuietly(out);
            tempFile.delete();
            this.mThumbnailBytes = null;
        } catch (Throwable th) {
            closeQuietly(in);
            closeQuietly(out);
            tempFile.delete();
            throw th;
        }
    }

    public boolean hasThumbnail() {
        return this.mHasThumbnail;
    }

    public byte[] getThumbnail() {
        if (this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7) {
            return getThumbnailBytes();
        }
        return null;
    }

    public byte[] getThumbnailBytes() {
        if (!this.mHasThumbnail) {
            return null;
        }
        if (this.mThumbnailBytes != null) {
            return this.mThumbnailBytes;
        }
        InputStream in = null;
        try {
            if (this.mAssetInputStream != null) {
                in = this.mAssetInputStream;
                if (!in.markSupported()) {
                    Log.d(TAG, "Cannot read thumbnail from inputstream without mark/reset support");
                    return null;
                }
                in.reset();
            } else if (this.mFilename != null) {
                in = new FileInputStream(this.mFilename);
            }
            if (in == null) {
                throw new FileNotFoundException();
            }
            if (in.skip(this.mThumbnailOffset) != this.mThumbnailOffset) {
                throw new IOException("Corrupted image");
            }
            byte[] buffer = new byte[this.mThumbnailLength];
            if (in.read(buffer) != this.mThumbnailLength) {
                throw new IOException("Corrupted image");
            }
            this.mThumbnailBytes = buffer;
            return buffer;
        } catch (IOException e) {
            Log.d(TAG, "Encountered exception while getting thumbnail", e);
            return null;
        } finally {
            closeQuietly(null);
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
        return this.mThumbnailCompression == 6 || this.mThumbnailCompression == 7;
    }

    public long[] getThumbnailRange() {
        if (!this.mHasThumbnail) {
            return null;
        }
        long[] range = {this.mThumbnailOffset, this.mThumbnailLength};
        return range;
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
                Log.w(TAG, "Latitude/longitude values are not parseable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", latValue, latRef, lngValue, lngRef));
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
        String[] dateTime = sFormatter.format(new Date(location.getTime())).split("\\s+");
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

    public void setDateTime(long timeStamp) {
        long sub = timeStamp % 1000;
        setAttribute("DateTime", sFormatter.format(new Date(timeStamp)));
        setAttribute("SubSecTime", Long.toString(sub));
    }

    public long getDateTime() {
        String dateTimeString = getAttribute("DateTime");
        if (dateTimeString == null || !sNonZeroTimePattern.matcher(dateTimeString).matches()) {
            return -1L;
        }
        ParsePosition pos = new ParsePosition(0);
        try {
            Date datetime = sFormatter.parse(dateTimeString, pos);
            if (datetime == null) {
                return -1L;
            }
            long msecs = datetime.getTime();
            String subSecs = getAttribute("SubSecTime");
            if (subSecs != null) {
                try {
                    long sub = Long.parseLong(subSecs);
                    while (sub > 1000) {
                        sub /= 10;
                    }
                    return msecs + sub;
                } catch (NumberFormatException e) {
                    return msecs;
                }
            }
            return msecs;
        } catch (IllegalArgumentException e2) {
            return -1L;
        }
    }

    public long getGpsDateTime() {
        String date = getAttribute("GPSDateStamp");
        String time = getAttribute("GPSTimeStamp");
        if (date == null || time == null || (!sNonZeroTimePattern.matcher(date).matches() && !sNonZeroTimePattern.matcher(time).matches())) {
            return -1L;
        }
        String dateTimeString = date + ' ' + time;
        ParsePosition pos = new ParsePosition(0);
        try {
            Date datetime = sFormatter.parse(dateTimeString, pos);
            if (datetime == null) {
                return -1L;
            }
            return datetime.getTime();
        } catch (IllegalArgumentException e) {
            return -1L;
        }
    }

    private static double convertRationalLatLonToDouble(String rationalString, String ref) {
        try {
            String[] parts = rationalString.split(",");
            String[] pair = parts[0].split("/");
            double degrees = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());
            String[] pair2 = parts[1].split("/");
            double minutes = Double.parseDouble(pair2[0].trim()) / Double.parseDouble(pair2[1].trim());
            String[] pair3 = parts[2].split("/");
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
        if (isOrfFormat(signatureCheckBytes)) {
            return 7;
        }
        if (isRw2Format(signatureCheckBytes)) {
            return 10;
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

    private boolean isOrfFormat(byte[] signatureCheckBytes) throws IOException {
        ByteOrderedDataInputStream signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
        this.mExifByteOrder = readByteOrder(signatureInputStream);
        signatureInputStream.setByteOrder(this.mExifByteOrder);
        short orfSignature = signatureInputStream.readShort();
        signatureInputStream.close();
        return orfSignature == 20306 || orfSignature == 21330;
    }

    private boolean isRw2Format(byte[] signatureCheckBytes) throws IOException {
        ByteOrderedDataInputStream signatureInputStream = new ByteOrderedDataInputStream(signatureCheckBytes);
        this.mExifByteOrder = readByteOrder(signatureInputStream);
        signatureInputStream.setByteOrder(this.mExifByteOrder);
        short signatureByte = signatureInputStream.readShort();
        signatureInputStream.close();
        return signatureByte == 85;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x010a, code lost:
    
        r12.setByteOrder(r11.mExifByteOrder);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x010f, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void getJpegAttributes(ByteOrderedDataInputStream in, int jpegOffset, int imageType) throws IOException {
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        in.seek(jpegOffset);
        byte marker = in.readByte();
        if (marker == -1) {
            int bytesRead = jpegOffset + 1;
            if (in.readByte() != -40) {
                throw new IOException("Invalid marker: " + Integer.toHexString(marker & 255));
            }
            int bytesRead2 = bytesRead + 1;
            while (true) {
                byte marker2 = in.readByte();
                if (marker2 != -1) {
                    throw new IOException("Invalid marker:" + Integer.toHexString(marker2 & 255));
                }
                byte marker3 = in.readByte();
                int bytesRead3 = bytesRead2 + 1 + 1;
                if (marker3 != -39 && marker3 != -38) {
                    int length = in.readUnsignedShort() - 2;
                    int bytesRead4 = bytesRead3 + 2;
                    if (length >= 0) {
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
                                if (in.skipBytes(1) != 1) {
                                    throw new IOException("Invalid SOFx");
                                }
                                this.mAttributes[imageType].put("ImageLength", ExifAttribute.createULong(in.readUnsignedShort(), this.mExifByteOrder));
                                this.mAttributes[imageType].put("ImageWidth", ExifAttribute.createULong(in.readUnsignedShort(), this.mExifByteOrder));
                                length -= 5;
                                break;
                            case -31:
                                if (length >= 6) {
                                    byte[] identifier = new byte[6];
                                    if (in.read(identifier) != 6) {
                                        throw new IOException("Invalid exif");
                                    }
                                    bytesRead4 += 6;
                                    length -= 6;
                                    if (Arrays.equals(identifier, IDENTIFIER_EXIF_APP1)) {
                                        if (length <= 0) {
                                            throw new IOException("Invalid exif");
                                        }
                                        this.mExifOffset = bytesRead4;
                                        byte[] bytes = new byte[length];
                                        if (in.read(bytes) != length) {
                                            throw new IOException("Invalid exif");
                                        }
                                        bytesRead4 += length;
                                        length = 0;
                                        readExifSegment(bytes, imageType);
                                        break;
                                    }
                                }
                                break;
                            case -2:
                                byte[] bytes2 = new byte[length];
                                if (in.read(bytes2) != length) {
                                    throw new IOException("Invalid exif");
                                }
                                length = 0;
                                if (getAttribute("UserComment") == null) {
                                    this.mAttributes[1].put("UserComment", ExifAttribute.createString(new String(bytes2, ASCII)));
                                    break;
                                }
                                break;
                        }
                        if (length < 0) {
                            throw new IOException("Invalid length");
                        }
                        if (in.skipBytes(length) != length) {
                            throw new IOException("Invalid JPEG segment");
                        }
                        bytesRead2 = bytesRead4 + length;
                    } else {
                        throw new IOException("Invalid length");
                    }
                }
            }
        } else {
            throw new IOException("Invalid marker: " + Integer.toHexString(marker & 255));
        }
    }

    private void getRawAttributes(ByteOrderedDataInputStream in) throws IOException {
        ExifAttribute makerNoteAttribute;
        parseTiffHeaders(in, in.available());
        readImageFileDirectory(in, 0);
        updateImageSizeValues(in, 0);
        updateImageSizeValues(in, 5);
        updateImageSizeValues(in, 4);
        validateImages(in);
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
        in.skipBytes(RAF_OFFSET_TO_JPEG_IMAGE_OFFSET);
        byte[] jpegOffsetBytes = new byte[4];
        byte[] cfaHeaderOffsetBytes = new byte[4];
        in.read(jpegOffsetBytes);
        in.skipBytes(4);
        in.read(cfaHeaderOffsetBytes);
        int rafJpegOffset = ByteBuffer.wrap(jpegOffsetBytes).getInt();
        int rafCfaHeaderOffset = ByteBuffer.wrap(cfaHeaderOffsetBytes).getInt();
        getJpegAttributes(in, rafJpegOffset, 5);
        in.seek(rafCfaHeaderOffset);
        in.setByteOrder(ByteOrder.BIG_ENDIAN);
        int numberOfDirectoryEntry = in.readInt();
        for (int i = 0; i < numberOfDirectoryEntry; i++) {
            int tagNumber = in.readUnsignedShort();
            int numberOfBytes = in.readUnsignedShort();
            if (tagNumber == TAG_RAF_IMAGE_SIZE.number) {
                int imageLength = in.readShort();
                int imageWidth = in.readShort();
                ExifAttribute imageLengthAttribute = ExifAttribute.createUShort(imageLength, this.mExifByteOrder);
                ExifAttribute imageWidthAttribute = ExifAttribute.createUShort(imageWidth, this.mExifByteOrder);
                this.mAttributes[0].put("ImageLength", imageLengthAttribute);
                this.mAttributes[0].put("ImageWidth", imageWidthAttribute);
                return;
            }
            in.skipBytes(numberOfBytes);
        }
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
        getRawAttributes(in);
        ExifAttribute jpgFromRawAttribute = this.mAttributes[0].get("JpgFromRaw");
        if (jpgFromRawAttribute != null) {
            getJpegAttributes(in, this.mRw2JpgFromRawOffset, 5);
        }
        ExifAttribute rw2IsoAttribute = this.mAttributes[0].get("ISO");
        ExifAttribute exifIsoAttribute = this.mAttributes[1].get("PhotographicSensitivity");
        if (rw2IsoAttribute != null && exifIsoAttribute == null) {
            this.mAttributes[1].put("PhotographicSensitivity", rw2IsoAttribute);
        }
    }

    private void saveJpegAttributes(InputStream inputStream, OutputStream outputStream) throws IOException {
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
        dataOutputStream.writeByte(-1);
        dataOutputStream.writeByte(-31);
        writeExifSegment(dataOutputStream, 6);
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
                return ByteOrder.LITTLE_ENDIAN;
            case 19789:
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

    /* JADX WARN: Removed duplicated region for block: B:15:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readImageFileDirectory(ByteOrderedDataInputStream dataInputStream, int ifdType) throws IOException {
        int nextIfdOffset;
        short numberOfDirectoryEntry;
        short i;
        long byteCount;
        int dataFormat;
        int numberOfComponents;
        long nextEntryOffset;
        int i2 = ifdType;
        if (dataInputStream.mPosition + 2 > dataInputStream.mLength) {
            return;
        }
        short numberOfDirectoryEntry2 = dataInputStream.readShort();
        if (dataInputStream.mPosition + (numberOfDirectoryEntry2 * 12) > dataInputStream.mLength) {
            return;
        }
        short i3 = 0;
        while (i3 < numberOfDirectoryEntry2) {
            int tagNumber = dataInputStream.readUnsignedShort();
            int dataFormat2 = dataInputStream.readUnsignedShort();
            int numberOfComponents2 = dataInputStream.readInt();
            long nextEntryOffset2 = dataInputStream.peek() + 4;
            ExifTag tag = sExifTagMapsForReading[i2].get(Integer.valueOf(tagNumber));
            boolean valid = false;
            if (tag == null) {
                Log.w(TAG, "Skip the tag entry since tag number is not defined: " + tagNumber);
                numberOfDirectoryEntry = numberOfDirectoryEntry2;
                i = i3;
            } else {
                if (dataFormat2 <= 0) {
                    numberOfDirectoryEntry = numberOfDirectoryEntry2;
                    i = i3;
                } else if (dataFormat2 >= IFD_FORMAT_BYTES_PER_FORMAT.length) {
                    numberOfDirectoryEntry = numberOfDirectoryEntry2;
                    i = i3;
                } else if (!tag.isFormatCompatible(dataFormat2)) {
                    Log.w(TAG, "Skip the tag entry since data format (" + IFD_FORMAT_NAMES[dataFormat2] + ") is unexpected for tag: " + tag.name);
                    numberOfDirectoryEntry = numberOfDirectoryEntry2;
                    i = i3;
                } else {
                    if (dataFormat2 == 7) {
                        dataFormat2 = tag.primaryFormat;
                    }
                    numberOfDirectoryEntry = numberOfDirectoryEntry2;
                    i = i3;
                    long byteCount2 = numberOfComponents2 * IFD_FORMAT_BYTES_PER_FORMAT[dataFormat2];
                    if (byteCount2 < 0 || byteCount2 > 2147483647L) {
                        Log.w(TAG, "Skip the tag entry since the number of components is invalid: " + numberOfComponents2);
                        byteCount = byteCount2;
                    } else {
                        valid = true;
                        byteCount = byteCount2;
                    }
                    if (valid) {
                        dataInputStream.seek(nextEntryOffset2);
                    } else {
                        if (byteCount <= 4) {
                            dataFormat = dataFormat2;
                            numberOfComponents = numberOfComponents2;
                        } else {
                            int offset = dataInputStream.readInt();
                            if (this.mMimeType == 7) {
                                if ("MakerNote".equals(tag.name)) {
                                    this.mOrfMakerNoteOffset = offset;
                                    dataFormat = dataFormat2;
                                    numberOfComponents = numberOfComponents2;
                                    nextEntryOffset = nextEntryOffset2;
                                } else if (i2 != 6) {
                                    dataFormat = dataFormat2;
                                    numberOfComponents = numberOfComponents2;
                                    nextEntryOffset = nextEntryOffset2;
                                } else if (!"ThumbnailImage".equals(tag.name)) {
                                    dataFormat = dataFormat2;
                                    numberOfComponents = numberOfComponents2;
                                    nextEntryOffset = nextEntryOffset2;
                                } else {
                                    this.mOrfThumbnailOffset = offset;
                                    this.mOrfThumbnailLength = numberOfComponents2;
                                    ExifAttribute compressionAttribute = ExifAttribute.createUShort(6, this.mExifByteOrder);
                                    dataFormat = dataFormat2;
                                    numberOfComponents = numberOfComponents2;
                                    ExifAttribute jpegInterchangeFormatAttribute = ExifAttribute.createULong(this.mOrfThumbnailOffset, this.mExifByteOrder);
                                    nextEntryOffset = nextEntryOffset2;
                                    ExifAttribute jpegInterchangeFormatLengthAttribute = ExifAttribute.createULong(this.mOrfThumbnailLength, this.mExifByteOrder);
                                    this.mAttributes[4].put("Compression", compressionAttribute);
                                    this.mAttributes[4].put("JPEGInterchangeFormat", jpegInterchangeFormatAttribute);
                                    this.mAttributes[4].put("JPEGInterchangeFormatLength", jpegInterchangeFormatLengthAttribute);
                                }
                            } else {
                                dataFormat = dataFormat2;
                                numberOfComponents = numberOfComponents2;
                                nextEntryOffset = nextEntryOffset2;
                                if (this.mMimeType == 10 && "JpgFromRaw".equals(tag.name)) {
                                    this.mRw2JpgFromRawOffset = offset;
                                }
                            }
                            if (offset + byteCount <= dataInputStream.mLength) {
                                dataInputStream.seek(offset);
                                nextEntryOffset2 = nextEntryOffset;
                            } else {
                                Log.w(TAG, "Skip the tag entry since data offset is invalid: " + offset);
                                dataInputStream.seek(nextEntryOffset);
                            }
                        }
                        Integer nextIfdType = sExifPointerTagMap.get(Integer.valueOf(tagNumber));
                        if (nextIfdType != null) {
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
                            if (offset2 > 0 && offset2 < dataInputStream.mLength) {
                                dataInputStream.seek(offset2);
                                readImageFileDirectory(dataInputStream, nextIfdType.intValue());
                            } else {
                                Log.w(TAG, "Skip jump into the IFD since its offset is invalid: " + offset2);
                            }
                            dataInputStream.seek(nextEntryOffset2);
                        } else {
                            byte[] bytes = new byte[(int) byteCount];
                            dataInputStream.readFully(bytes);
                            ExifAttribute attribute = new ExifAttribute(dataFormat, numberOfComponents, bytes);
                            this.mAttributes[i2].put(tag.name, attribute);
                            if ("DNGVersion".equals(tag.name)) {
                                this.mMimeType = 3;
                            }
                            if ((("Make".equals(tag.name) || "Model".equals(tag.name)) && attribute.getStringValue(this.mExifByteOrder).contains(PEF_SIGNATURE)) || ("Compression".equals(tag.name) && attribute.getIntValue(this.mExifByteOrder) == 65535)) {
                                this.mMimeType = 8;
                            }
                            if (dataInputStream.peek() != nextEntryOffset2) {
                                dataInputStream.seek(nextEntryOffset2);
                            }
                        }
                    }
                    i3 = (short) (i + 1);
                    i2 = ifdType;
                    numberOfDirectoryEntry2 = numberOfDirectoryEntry;
                }
                Log.w(TAG, "Skip the tag entry since data format is invalid: " + dataFormat2);
            }
            byteCount = 0;
            if (valid) {
            }
            i3 = (short) (i + 1);
            i2 = ifdType;
            numberOfDirectoryEntry2 = numberOfDirectoryEntry;
        }
        if (dataInputStream.peek() + 4 <= dataInputStream.mLength && (nextIfdOffset = dataInputStream.readInt()) > 8 && nextIfdOffset < dataInputStream.mLength) {
            dataInputStream.seek(nextIfdOffset);
            if (this.mAttributes[4].isEmpty()) {
                readImageFileDirectory(dataInputStream, 4);
            } else if (this.mAttributes[5].isEmpty()) {
                readImageFileDirectory(dataInputStream, 5);
            }
        }
    }

    private void retrieveJpegImageSize(ByteOrderedDataInputStream in, int imageType) throws IOException {
        ExifAttribute jpegInterchangeFormatAttribute;
        ExifAttribute imageLengthAttribute = this.mAttributes[imageType].get("ImageLength");
        ExifAttribute imageWidthAttribute = this.mAttributes[imageType].get("ImageWidth");
        if ((imageLengthAttribute == null || imageWidthAttribute == null) && (jpegInterchangeFormatAttribute = this.mAttributes[imageType].get("JPEGInterchangeFormat")) != null) {
            int jpegInterchangeFormat = jpegInterchangeFormatAttribute.getIntValue(this.mExifByteOrder);
            getJpegAttributes(in, jpegInterchangeFormat, imageType);
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
            int thumbnailLength = Math.min(jpegInterchangeFormatLengthAttribute.getIntValue(this.mExifByteOrder), in.available() - thumbnailOffset);
            if (this.mMimeType == 4 || this.mMimeType == 9 || this.mMimeType == 10) {
                thumbnailOffset += this.mExifOffset;
            } else if (this.mMimeType == 7) {
                thumbnailOffset += this.mOrfMakerNoteOffset;
            }
            if (thumbnailOffset > 0 && thumbnailLength > 0) {
                this.mHasThumbnail = true;
                this.mThumbnailOffset = thumbnailOffset;
                this.mThumbnailLength = thumbnailLength;
                if (this.mFilename == null && this.mAssetInputStream == null) {
                    byte[] thumbnailBytes = new byte[thumbnailLength];
                    in.seek(thumbnailOffset);
                    in.readFully(thumbnailBytes);
                    this.mThumbnailBytes = thumbnailBytes;
                }
            }
        }
    }

    private void handleThumbnailFromStrips(ByteOrderedDataInputStream in, HashMap thumbnailData) throws IOException {
        ByteOrderedDataInputStream byteOrderedDataInputStream = in;
        ExifAttribute stripOffsetsAttribute = (ExifAttribute) thumbnailData.get("StripOffsets");
        ExifAttribute stripByteCountsAttribute = (ExifAttribute) thumbnailData.get("StripByteCounts");
        if (stripOffsetsAttribute != null && stripByteCountsAttribute != null) {
            long[] stripOffsets = convertToLongArray(stripOffsetsAttribute.getValue(this.mExifByteOrder));
            long[] stripByteCounts = convertToLongArray(stripByteCountsAttribute.getValue(this.mExifByteOrder));
            if (stripOffsets == null) {
                Log.w(TAG, "stripOffsets should not be null.");
                return;
            }
            if (stripByteCounts == null) {
                Log.w(TAG, "stripByteCounts should not be null.");
                return;
            }
            long totalStripByteCount = 0;
            for (long byteCount : stripByteCounts) {
                totalStripByteCount += byteCount;
            }
            byte[] totalStripBytes = new byte[(int) totalStripByteCount];
            int bytesRead = 0;
            int bytesAdded = 0;
            int i = 0;
            while (i < stripOffsets.length) {
                int bytesRead2 = bytesRead;
                int stripOffset = (int) stripOffsets[i];
                ExifAttribute stripOffsetsAttribute2 = stripOffsetsAttribute;
                int stripByteCount = (int) stripByteCounts[i];
                int skipBytes = stripOffset - bytesRead2;
                if (skipBytes < 0) {
                    Log.d(TAG, "Invalid strip offset value");
                }
                ExifAttribute stripByteCountsAttribute2 = stripByteCountsAttribute;
                long[] stripOffsets2 = stripOffsets;
                byteOrderedDataInputStream.seek(skipBytes);
                byte[] stripBytes = new byte[stripByteCount];
                byteOrderedDataInputStream.read(stripBytes);
                int bytesRead3 = bytesRead2 + skipBytes + stripByteCount;
                System.arraycopy(stripBytes, 0, totalStripBytes, bytesAdded, stripBytes.length);
                bytesAdded += stripBytes.length;
                i++;
                byteOrderedDataInputStream = in;
                stripOffsetsAttribute = stripOffsetsAttribute2;
                stripOffsets = stripOffsets2;
                bytesRead = bytesRead3;
                stripByteCountsAttribute = stripByteCountsAttribute2;
            }
            this.mHasThumbnail = true;
            this.mThumbnailBytes = totalStripBytes;
            this.mThumbnailLength = totalStripBytes.length;
        }
    }

    private boolean isSupportedDataType(HashMap thumbnailData) throws IOException {
        ExifAttribute photometricInterpretationAttribute;
        ExifAttribute bitsPerSampleAttribute = (ExifAttribute) thumbnailData.get("BitsPerSample");
        if (bitsPerSampleAttribute != null) {
            int[] bitsPerSampleValue = (int[]) bitsPerSampleAttribute.getValue(this.mExifByteOrder);
            if (Arrays.equals(BITS_PER_SAMPLE_RGB, bitsPerSampleValue)) {
                return true;
            }
            if (this.mMimeType == 3 && (photometricInterpretationAttribute = (ExifAttribute) thumbnailData.get("PhotometricInterpretation")) != null) {
                int photometricInterpretationValue = photometricInterpretationAttribute.getIntValue(this.mExifByteOrder);
                return (photometricInterpretationValue == 1 && Arrays.equals(bitsPerSampleValue, BITS_PER_SAMPLE_GREYSCALE_2)) || (photometricInterpretationValue == 6 && Arrays.equals(bitsPerSampleValue, BITS_PER_SAMPLE_RGB));
            }
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

    private void validateImages(InputStream in) throws IOException {
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

    private int writeExifSegment(ByteOrderedDataOutputStream dataOutputStream, int exifOffsetFromBeginning) throws IOException {
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
        int i2 = 4;
        if (this.mHasThumbnail) {
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_TAG.name, ExifAttribute.createULong(0L, this.mExifByteOrder));
            this.mAttributes[4].put(JPEG_INTERCHANGE_FORMAT_LENGTH_TAG.name, ExifAttribute.createULong(this.mThumbnailLength, this.mExifByteOrder));
        }
        for (int i3 = 0; i3 < EXIF_TAGS.length; i3++) {
            int sum = 0;
            Iterator<Map.Entry<String, ExifAttribute>> it = this.mAttributes[i3].entrySet().iterator();
            while (it.hasNext()) {
                ExifAttribute exifAttribute = it.next().getValue();
                int size = exifAttribute.size();
                if (size > 4) {
                    sum += size;
                }
            }
            ifdDataSizes[i3] = ifdDataSizes[i3] + sum;
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
            this.mThumbnailOffset = exifOffsetFromBeginning + thumbnailOffset;
            position += this.mThumbnailLength;
        }
        int thumbnailOffset2 = position + 8;
        if (!this.mAttributes[1].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[1].name, ExifAttribute.createULong(ifdOffsets[1], this.mExifByteOrder));
        }
        if (!this.mAttributes[2].isEmpty()) {
            this.mAttributes[0].put(EXIF_POINTER_TAGS[2].name, ExifAttribute.createULong(ifdOffsets[2], this.mExifByteOrder));
        }
        if (!this.mAttributes[3].isEmpty()) {
            this.mAttributes[1].put(EXIF_POINTER_TAGS[3].name, ExifAttribute.createULong(ifdOffsets[3], this.mExifByteOrder));
        }
        dataOutputStream.writeUnsignedShort(thumbnailOffset2);
        dataOutputStream.write(IDENTIFIER_EXIF_APP1);
        dataOutputStream.writeShort(this.mExifByteOrder == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
        dataOutputStream.setByteOrder(this.mExifByteOrder);
        dataOutputStream.writeUnsignedShort(42);
        dataOutputStream.writeUnsignedInt(8L);
        int ifdType3 = 0;
        while (ifdType3 < EXIF_TAGS.length) {
            if (!this.mAttributes[ifdType3].isEmpty()) {
                dataOutputStream.writeUnsignedShort(this.mAttributes[ifdType3].size());
                int dataOffset = ifdOffsets[ifdType3] + i + (this.mAttributes[ifdType3].size() * 12) + i2;
                for (Map.Entry<String, ExifAttribute> entry2 : this.mAttributes[ifdType3].entrySet()) {
                    ExifTag tag2 = sExifTagMapsForWriting[ifdType3].get(entry2.getKey());
                    int tagNumber = tag2.number;
                    ExifAttribute attribute = entry2.getValue();
                    int size2 = attribute.size();
                    dataOutputStream.writeUnsignedShort(tagNumber);
                    dataOutputStream.writeUnsignedShort(attribute.format);
                    dataOutputStream.writeInt(attribute.numberOfComponents);
                    if (size2 > i2) {
                        dataOutputStream.writeUnsignedInt(dataOffset);
                        dataOffset += size2;
                    } else {
                        dataOutputStream.write(attribute.bytes);
                        if (size2 < 4) {
                            int i4 = size2;
                            for (int i5 = 4; i4 < i5; i5 = 4) {
                                dataOutputStream.writeByte(0);
                                i4++;
                            }
                        }
                    }
                    i2 = 4;
                }
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
            i = 2;
            i2 = 4;
        }
        if (this.mHasThumbnail) {
            dataOutputStream.write(getThumbnailBytes());
        }
        dataOutputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
        return thumbnailOffset2;
    }

    private static Pair<Integer, Integer> guessDataFormat(String entryValue) {
        if (entryValue.contains(",")) {
            String[] entryValues = entryValue.split(",");
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
            String[] rationalNumber = entryValue.split("/");
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
        private final int mLength;
        private int mPosition;
        private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;

        public ByteOrderedDataInputStream(InputStream in) throws IOException {
            this.mByteOrder = ByteOrder.BIG_ENDIAN;
            this.mDataInputStream = new DataInputStream(in);
            this.mLength = this.mDataInputStream.available();
            this.mPosition = 0;
            this.mDataInputStream.mark(this.mLength);
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
    }

    private static class ByteOrderedDataOutputStream extends FilterOutputStream {
        private ByteOrder mByteOrder;
        private final OutputStream mOutputStream;

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
            return;
        }
        ExifAttribute firstImageLengthAttribute = this.mAttributes[firstIfdType].get("ImageLength");
        ExifAttribute firstImageWidthAttribute = this.mAttributes[firstIfdType].get("ImageWidth");
        ExifAttribute secondImageLengthAttribute = this.mAttributes[secondIfdType].get("ImageLength");
        ExifAttribute secondImageWidthAttribute = this.mAttributes[secondIfdType].get("ImageWidth");
        if (firstImageLengthAttribute != null && firstImageWidthAttribute != null && secondImageLengthAttribute != null && secondImageWidthAttribute != null) {
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
}
