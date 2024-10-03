package com.dedrick.poslib.newland;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;

import com.dedrick.poslib.R;

public class VoucherPrintItemGenerator {
    private static VoucherPrintItemGenerator voucherPrintItemGenerator;

    public static VoucherPrintItemGenerator getInstance() {
        //DCL
        if (voucherPrintItemGenerator == null) {
            synchronized (VoucherPrintItemGenerator.class) {
                if (voucherPrintItemGenerator == null) {
                    voucherPrintItemGenerator = new VoucherPrintItemGenerator();
                }
            }

        }
        return voucherPrintItemGenerator;
    }

    /**
     * To get the template voucher.
     * @param mContext  Activity context.
     * @return TemplateVoucher bitmap data.
     */
    public Bitmap getTemplateVoucher(Context mContext, String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId) {
            BitmapDraw bitmapDraw = new BitmapDraw(mContext);
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ubapng);
            bitmapDraw.image(bitmap);
            bitmapDraw.text("----------------------------------------------", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text(bank, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text(bankingAgent + "|"+ activityPoint, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text(address, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text(created_at, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("----------------------------------------------", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text(transactionType, BitmapDraw.LARGE_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("\n", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text("Trx num: "+stan, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("Cmp num: "+account_number, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("Trm id:"+terminalId, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("Amount: "+main_amount+currency, BitmapDraw.LARGE_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("\n", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text(description, BitmapDraw.SMALL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("----------------------------------------------", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text("Signature", BitmapDraw.SMALL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("\n \n", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);

            Bitmap bitmapCopy = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ubapng);
            bitmapDraw.image(bitmapCopy);
            bitmapDraw.text("----------------------------------------------", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text(bank, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text(bankingAgent + "|"+ activityPoint, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text(address, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text(created_at, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("----------------------------------------------", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text(transactionType, BitmapDraw.LARGE_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("\n", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text("Trx num: "+stan, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("Cmp num: "+account_number, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("Trm id:"+terminalId, BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("Amount: "+main_amount+currency, BitmapDraw.LARGE_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("\n", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text(description, BitmapDraw.SMALL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("----------------------------------------------", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            bitmapDraw.text("Signature", BitmapDraw.SMALL_FONT_SIZE, false, Paint.Align.LEFT);
            bitmapDraw.text("\n \n", BitmapDraw.NORMAL_FONT_SIZE, false, Paint.Align.CENTER);
            return bitmapDraw.getBitmap();
    }

}
