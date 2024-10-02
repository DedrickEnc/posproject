package com.dedrick.poslib;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.dedrick.poslib.newland.VoucherPrintItemGenerator;
import com.newland.nsdk.core.api.common.ModuleType;
import com.newland.nsdk.core.api.common.exception.NSDKException;
import com.newland.nsdk.core.api.internal.NSDKModuleManager;
import com.newland.nsdk.core.api.internal.printer.Printer;
import com.newland.nsdk.core.api.internal.printer.PrinterStatus;
import com.newland.nsdk.core.api.internal.printer.PrintingResultListener;
import com.newland.nsdk.core.internal.NSDKModuleManagerImpl;

import java.io.ByteArrayOutputStream;

public class Newland950 extends POSPrinter {
    private NSDKModuleManager nsdkModuleManager = NSDKModuleManagerImpl.getInstance();
    private Context context = null;

    Newland950(Application a, Context c){
        super(a, c);
        this.app = a;
        this.context = c;
    }
    @Override
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId){
        try {
            nsdkModuleManager.init(this.app);
            Printer printer = (Printer)nsdkModuleManager.getModule(ModuleType.PRINTER);
            PrinterStatus status = printer.getStatus();

            Bitmap bitmap = VoucherPrintItemGenerator.getInstance().getTemplateVoucher(this.context, bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bitmap2 = baos.toByteArray();

            printer.printImage(bitmap2, 0, width, height, new PrintingResultListener() {
                @Override
                public void onEventRaised(int status) {}
            });

            nsdkModuleManager.destroy();

        } catch(NSDKException e) {
        }
    }
}
