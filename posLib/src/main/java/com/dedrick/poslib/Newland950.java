package com.dedrick.poslib;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;


import com.newland.nsdk.esc.api.ESCPrinter;
import com.newland.nsdk.esc.common.ESCPrinterException;
import com.newland.nsdk.esc.internal.ESCPrinterImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Newland950 extends POSPrinter {
    private ESCPrinter escPrinter = ESCPrinterImpl.getInstance();
    private int myOtsu = 128;

    Newland950(Application a){
        super(a);
        this.app = a;
    }
    @Override
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId){
        printInvoice(bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
        printInvoice(bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
    }

    private void printInvoice(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId) {
        try {
            printLogo();
            escPrinter.exec((
                    bank + "\n" +
                            bankingAgent + "|" +
                            activityPoint + "\n" +
                            address + "\n" +
                            created_at + "\n \n" +
                            transactionType.toUpperCase() + "\n" +
                            "Trx num: " + stan +
                            "\nCmp num: "+ account_number +
                            "\nTrm id: "+ terminalId + "\n \n" +
                            "Amount: "+ main_amount + currency + "\n" +
                            description + "\n \n Signature  \n").getBytes(StandardCharsets.UTF_8));

        } catch (ESCPrinterException e) {
            e.printStackTrace();
        }
    }


    private void printLogo() {
        try {
            copyFilesFromRaw(this.app,R.drawable.uba,"uba.bmp");
            String storagePath = this.app.getFilesDir().getAbsolutePath() + "/";
            Bitmap bit = BitmapFactory.decodeFile(storagePath + "uba.bmp");
            bit = compressPic(bit);
            myOtsu = myOtsu(bit) + 50;
            byte[] imgBuf = draw2PxPoint(bit);
            //escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.ABSOLUTE_POSITION_200);
            escPrinter.exec(imgBuf);

        } catch (ESCPrinterException e) {
            e.printStackTrace();
        }

    }

    private void copyFilesFromRaw(Context context, int id, String fileName) {
        InputStream inputStream = context.getResources().openRawResource(id);
        String storagePath = context.getFilesDir().getAbsolutePath() + "/";
        File file = new File(storagePath);
        if (file.exists()) {
            readInputStream(storagePath + fileName, inputStream);
        }else {
            Log.d("copyFilesFromRaw", "file not exist");
        }
    }

    private void readInputStream(String storagePath, InputStream inputStream) {
        File file = new File(storagePath );
        try {
            if (!file.exists()) {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[inputStream.available()];
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, lenght);
                }
                fos.flush();
                fos.close();
                inputStream.close();
            }else {
                file.delete();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[inputStream.available()];
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, lenght);
                }
                fos.flush();
                fos.close();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap compressPic(Bitmap bitmapOrg) {
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
        Bitmap targetBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(targetBmp);
        targetCanvas.drawColor(0xffffffff);
        targetCanvas.drawBitmap(bitmapOrg, new Rect(0, 0, width, height), new Rect(0, 0, width, height), null);
        return targetBmp;
    }

    private int myOtsu(Bitmap graymap) {
        int y;
        int width = graymap.getWidth();
        int height = graymap.getHeight();
        int pixelCount[] = new int[256];
        float pixelPro[] = new float[256];
        int i, j, pixelSum = width * height, threshold = 0;
        int[] pixels = new int[width * height];
        graymap.getPixels(pixels, 0, width, 0, 0, width, height);
        int data[] = new int[width * height * 3];
        int kk = 0;
        for (j = 0; j < width; j++) {
            for (i = 0; i < height; i++) {
                int col = graymap.getPixel(j, i);
                int r = (col & 0x00FF0000) >> 16;
                int g = (col & 0x0000FF00) >> 8;
                int b = (col & 0x000000FF);
                y = ((66 * r + 129 * g + 25 * b + 128) >> 8) + 16;
                y = y < 255 ? y : 255;
                y = y > 0 ? y : 0;
                data[kk] = y;
                kk++;
            }
        }
        // 统计每个灰度级中像素的个数
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                pixelCount[data[i * width + j]]++;
            }
        }

        // 计算每个灰度级的像素数目占整幅图像的比例
        for (i = 0; i < 256; i++) {
            pixelPro[i] = (float) pixelCount[i] / pixelSum;
        }

        // 遍历灰度级[0,255],寻找合适的threshold
        float w0, w1, u0tmp, u1tmp, u0, u1, deltaTmp, deltaMax = 0;
        for (i = 0; i < 256; i++) {
            w0 = w1 = u0tmp = u1tmp = u0 = u1 = deltaTmp = 0;
            for (j = 0; j < 256; j++) {
                if (j <= i)
                {
                    w0 += pixelPro[j];
                    u0tmp += j * pixelPro[j];
                } else
                {
                    w1 += pixelPro[j];
                    u1tmp += j * pixelPro[j];
                }
            }
            u0 = u0tmp / w0;
            u1 = u1tmp / w1;
            deltaTmp = (float) (w0 * w1 * (u0 - u1) * (u0 - u1));
            if (deltaTmp > deltaMax) {
                deltaMax = deltaTmp;
                threshold = i;
            }
        }
        return threshold;
    }

    private byte[] draw2PxPoint(Bitmap bmp) {
        int size = bmp.getWidth() * bmp.getHeight() / 8 + 1000;
        byte[] data = new byte[size];
        int k = 0;

        data[k++] = 0x1B;
        data[k++] = 0x33;
        data[k++] = 0x00;

        for (int j = 0; j < bmp.getHeight() / 24f; j++) {
            data[k++] = 0x1B;
            data[k++] = 0x2A;
            data[k++] = 33;
            data[k++] = (byte) (bmp.getWidth() % 256); //nL
            data[k++] = (byte) (bmp.getWidth() / 256); //nH

            for (int i = 0; i < bmp.getWidth(); i++) {
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 8; n++) {
                        byte b = px2Byte(i, j * 24 + m * 8 + n, bmp);
                        data[k] += data[k] + b;
                    }
                    k++;
                }
            }
            data[k++] = 10;
        }
        byte[] data2 = processBitmapData(data);
        return data2;
    }

    private byte px2Byte(int x, int y, Bitmap bit) {
        if (x < bit.getWidth() && y < bit.getHeight()) {
            byte b;
            int pixel = bit.getPixel(x, y);
            int red = (pixel & 0x00ff0000) >> 16;
            int green = (pixel & 0x0000ff00) >> 8;
            int blue = pixel & 0x000000ff;
            int gray = RGB2Gray(red, green, blue);
            if (gray < myOtsu) {
                b = 1;
            } else {
                b = 0;
            }
            return b;
        }
        return 0;
    }

    private int RGB2Gray(int r, int g, int b) {
        int gray = (int) (0.29900 * r + 0.58700 * g + 0.11400 * b);
        return gray;
    }

    private byte[] processBitmapData(byte[] data) {
        byte[] processedBitmapData = null;
        int position = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == (byte) 0x0A) {
                position = i;
            }
        }
        int length = new String(data).substring(0, position).length();
        processedBitmapData = new byte[length];
        System.arraycopy(data, 0, processedBitmapData, 0, length);
        return processedBitmapData;
    }
}
