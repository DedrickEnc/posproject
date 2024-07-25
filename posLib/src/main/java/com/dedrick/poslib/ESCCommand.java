package com.dedrick.poslib;

import com.newland.nsdk.esc.common.utils.ISOUtils;

public class ESCCommand {
    public static final byte[] RESET = new byte[] {0x1b, 0x40};

    public static final byte[] LINE_SPACING_1 = new byte[] {0x1b, 0x33, 1};
    public static final byte[] LINE_SPACING_10 = new byte[] {0x1b, 0x33, 10};
    public static final byte[] LINE_SPACING_DEFAULT = new byte[] {0x1b, 0x32};

    public static final byte[] LEFT_SPACE_10 = new byte[] {0x1d, 0x4c, 10, 0};
    public static final byte[] LEFT_SPACE_DEFAULT = new byte[] {0x1d, 0x4c, 0, 0};

    public static final byte[] CHINESE_DOUBLE_WIDTH = new byte[] {0x1c, 0x21, 0x04};
    public static final byte[] CHINESE_DOUBLE_HEIGHT = new byte[] {0x1c, 0x21, 0x08};
    public static final byte[] CHINESE_DOUBLE_WIDTH_HEIGHT = new byte[] {0x1c, 0x57,0x01};
    public static final byte[] CHINESE_UNDERLINE = new byte[] {0x1c, 0x21, (byte) 0x80};
    public static final byte[] CHINESE_RESET = new byte[] {0x1c, 0x21, 0};
    public static final byte[] CHINESE_UNDERLINE_MODE = new byte[] {0x1c, 0x2d, 1};
    public static final byte[] CHINESE_UNDERLINE_MODE_CANCEL = new byte[] {0x1c, 0x2d, 0};
    public static final byte[] STANDARD_ASCII_FONT = new byte[] {0x1b, 0x21, 0};
    public static final byte[] COMPRESS_FONT = new byte[] {0x1b, 0x21, 1};
    public static final byte[] BOLD = new byte[] {0x1b, 0x21, 8};
    public static final byte[] DOUBLE_HEIGHT = new byte[] {0x1b, 0x21, 16};
    public static final byte[] DOUBLE_WIDTH = new byte[] {0x1b, 0x21, 32};
    public static final byte[] UNDERLINE = new byte[] {0x1b, 0x21, (byte) 0x80};
    public static final byte[] NORMAL_FONT_MODE = new byte[] {0x1b, 0x21, 0};
    public static final byte[] FONT_MIXA = new byte[] {0x1b, 0x21, (byte) 185};
    public static final byte[] FONT_MIXB = new byte[] {0x1b, 0x21, (byte) 184};
    public static final byte[] UNDERLINE_MODE = new byte[] {0x1b, 0x2d, 1};
    public static final byte[] UNDERLINE_MODE_OFF = new byte[] {0x1b, 0x2d, 0};
    public static final byte[] SELECT_FONT_STANDARD_ASCII = new byte[] {0x1b, 0x4d, 48};
    public static final byte[] SELECT_FONT_COMPRESS_ASCII = new byte[] {0x1b, 0x4d, 49};
    public static final byte[] SELECT_BOLD_MODE = new byte[] {0x1b, 0x45, 1};
    public static final byte[] BOLD_MODE_CANCEL = new byte[] {0x1b, 0x45, 0};
    public static final byte[] DOUBLE_PRINT_MODE = new byte[] {0x1b, 0x47, 1};
    public static final byte[] DOUBLE_PRINT_CANCEL = new byte[] {0x1b, 0x47, 0};

    public static final byte[] ALIGN_LEFT = new byte[] {0x1b, 0x61, 0};
    public static final byte[] ALIGN_CENTER = new byte[] {0x1b, 0x61, 1};
    public static final byte[] ALIGN_RIGHT = new byte[] {0x1b, 0x61, 2};
    public static final byte[] PRINT_AND_FEED_LINES = new byte[] {0x1b,0x64, 1};
    public static final byte[] BLACK_WRITE_REVERSE = new byte[] {0x1d, 0x42, 1};
    public static final byte[] BLACK_WRITE_REVERSE_CANCEL = new byte[] {0x1d, 0x42, 0};
    public static final byte[] HOR_VER_NORMAL = new byte[] {0x1d, 0x21, 0};
    public static final byte[] HOR_DOUBLE = new byte[] {0x1d, 0x21, 16};
    public static final byte[] HOR_TRIPLE = new byte[] {0x1d, 0x21, 32};
    public static final byte[] VER_DOUBLE = new byte[] {0x1d, 0x21, 1};
    public static final byte[] VER_TRIPLE = new byte[] {0x1d, 0x21, 2};

    public static final byte[] ABSOLUTE_POSITION_10 = new byte[] {0x1b, 0x24, (byte) 10, 0};
    public static final byte[] ABSOLUTE_POSITION_200 = new byte[] {0x1b, 0x24, (byte) 200, 0};
    public static final byte[] RELATION_POSITION_60 = new byte[] {0x1b, 0x5c, 60, 0};
    public static final byte[] RELATION_POSITION_0 = new byte[] {0x1b, 0x5c, 0, 0};

    public static final byte[] HIR_NONE = {0x1d, 0x48,0};
    public static final byte[] HIR_UP = {0x1d, 0x48,1};
    public static final byte[] HIR_BELOW = {0x1d, 0x48,2};
    public static final byte[] HIR_BOTH = {0x1d, 0x48,3};

    public static final byte[] BARCODEHEIGHT = {0x1d, 0x68,(byte) 60};
    public static final byte[] BARCODEHEIGHT_SISTY = {0x1d, 0x68,(byte) 120};
    public static final byte[] BARCODEHEIGHT_80 = {0x1d, 0x68, 0x50};

    //设置条码宽度
    public static final byte[] BARCODEWIDTH= {0x1d, 0x77,1};
    public static final byte[] BARCODEWIDTH_2= {0x1d, 0x77,1,2};
    //设置条码宽度
    public static final byte[] BARCODEWIDTH_5= {0x1d, 0x77,5};

    //UPC-A
    public static final byte[] UPCA = {0x1d, 0x6b,0,49,50,51,52,53,54,55,56,57,48,49,50,0x00};
    //UPC-E 012345
    public static final byte[] UPCE = {0x1d, 0x6b,66,12,48,49,50,51,52,53};
    //EAN13
    public static final byte[] EAN13 = {0x1d, 0x6b,2,48,49,50,51,52,53,54,55,56,57,57,57,57,0x00};
    //EAN8 01234567
    public static final byte[] EAN8 = {0x1d, 0x6b,3,48,49,50,51,52,53,54,55,0x00};
    //CODE39 打印不出来
    public static final byte[] CODE39  = {0x1d, 0x6b,4,42,48,49,50,51,52,53,54,55,56,57,42,0x00};
    //ITF
    public static final byte[] ITF  = {0x1d, 0x6b,5,48,49,50,51,52,51,52,53,54,55,56,57,0x00};
    //CODEBAR
    public static final byte[] CODEBAR  = {0x1d, 0x6b,6,65,48,49,50,51,52,51,52,68,0x00};
    //CODE93
    public static final byte[] CODE93 = {0x1d, 0x6b,72,7,48,49,50,51,52,51,52};
    //CODE128
    public static final byte[] CODE128  = {0x1d, 0x6b,73,10,78, 79, 46, 48, 49, 50,51,52,53,54};
    public static final byte[] CODE128A  = {0x1d, 0x6b,73,10,78, 79, 46, 48, 49, 50,51,52,55,55};
    public static final byte[] CODE128B  = {0x1d, 0x6b,73,0x12,78, 79, 46, 48, 49, 50,51,52,55,55,55,55,55,55,55,55,55,55,0x0A};



    //选择HIR字体 标准ASCII字体
    public static final byte[] HIR_STANDARD_FONT = {0x1d, 0x66,0};
    //选择HIR字体 压缩ASCII字体
    public static final byte[] HIR_COMPRESSED_FONT = {0x1d, 0x66,1};

    public static final byte[] QR_WIDTH2 = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x43, 0x02};
    public static final byte[] QR_WIDTH4 = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x43, 0x04};
    public static final byte[] QR_WIDTH7 = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x43, 0x07};
    public static final byte[] QR_WIDTH10 = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x43, 0x0a};
    public static final byte[] QR_WIDTH13 = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x43, 0x0d};
    public static final byte[] QR_WIDTH16 = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x43, 0x10};

    public static final byte[] QR_INFO = {0x1d, 0x28, 0x6b, 0x0b, 0x00, 0x31, 0x50, 0x30, (byte)0xC5,(byte)0xA9,(byte)0xD2,(byte)0xB5,(byte)0xD2,(byte)0xF8,(byte)0xD0,(byte)0xD0};

    // 打印符号存储区中的数据
    public static final byte[] QR_PRINT = {0x1d, 0x28, 0x6b, 0x03, 0x00, 0x31, 0x51, 0x30};


    public static final byte[] FRENCH = {0x1b, 0x52, 1};
    public static final byte[] GERMANY = {0x1b, 0x52, 2};
    public static final byte[] ENGLISH = {0x1b, 0x52, 3};
    public static final byte[] DANISH = {0x1b, 0x52, 4};
    public static final byte[] SWEDISH = {0x1b, 0x52, 5};
    public static final byte[] ITALIAN = {0x1b, 0x52, 6};
    public static final byte[] SPANISH = {0x1b, 0x52, 7};
    public static final byte[] JAPANESE = {0x1b, 0x52, 8};
    public static final byte[] NORWEGIAN = {0x1b, 0x52, 9};
    public static final byte[] DANISH_II = {0x1b, 0x52, 10};
    public static final byte[] SPANISH_II = {0x1b, 0x52, 11};
    public static final byte[] PORTUGUESE = {0x1b, 0x52, 12};
    public static final byte[] KOREAN = {0x1b, 0x52, 13};
    public static final byte[] SLOVENIAN = {0x1b, 0x52, 14};
    public static final byte[] CHINESE = {0x1b, 0x52, 15};

    public static final byte[] LINE_HEAD = new byte[] {0x1B, 0x24, 0, 0};

    public static final byte[] DIVIDER = ISOUtils.hex2byte("2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0A");

    public static final byte[] HRI_NONE = new byte[] {0x1D, 0x48, 0x00};
    public static final byte[] HRI_UP = new byte[] {0x1D, 0x48, 0x01};
    public static final byte[] HRI_BELOW = new byte[] {0x1D, 0x48, 0x02};
    public static final byte[] HRI_BOTH = new byte[] {0x1D, 0x48, 0x03};

    public static final byte[] BARCODE_DATA = new byte[] {0x1D, 0x6B, 0x49, 25, 0x7B, 0x42, 0x54, 0x49, 0x31, 0x36, 0x39, 0x34, 0x34,
            0x31, 0x37, 0x38, 0x38, 0x37, 0x39, 0x35, 0x33, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x33, 0x32};

    public static final byte[] CUT_PAPER = new byte[] {0x1D, 0x56, 1};

    public static final byte[] DOUBLE_HEIGHT_WIDTH = new byte[] {0x1D, 0x21, 0x11};

    public static final byte[] WPC1252 = new byte[] {0x1B, 0x74, 0x10};

    public static final byte[] LINE_FEED = new byte[] {0x1B,0x64, 1};
}



