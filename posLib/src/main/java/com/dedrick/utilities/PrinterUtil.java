package com.dedrick.utilities;

import android.content.Context;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.vfi.smartpos.deviceservice.aidl.IPrinter;
import com.vfi.smartpos.deviceservice.aidl.PrinterConfig;
import com.vfi.smartpos.deviceservice.aidl.PrinterListener;

//import cd.infoset.smaprtpay.merchant.network.TransactionResponse;
//import timber.log.Timber;

/**
 * Created by Simon on 2019/2/22.
 */

public class PrinterUtil {
    static final String TAG = "PrinterUtil";
    public Context context;
    public static IPrinter iPrinter;
    private ServiceConnection serviceConnection;

    public PrinterUtil(Context context) {
        this.context = context;
    }

    private static Bundle formatInfo(){
        Bundle bundle = new Bundle();
        bundle.putInt(PrinterConfig.addTextInLine.FontSize.BundleName,24);
        bundle.putString(PrinterConfig.addTextInLine.GlobalFont.BundleName,PrinterDefine.Font_Bold);
        return bundle;
    }

    private static Bundle format(){
        Bundle bundle = new Bundle();
        bundle.putInt(PrinterConfig.addTextInLine.FontSize.BundleName,22);
        bundle.putString(PrinterConfig.addTextInLine.GlobalFont.BundleName,PrinterDefine.Font_Bold);
        return bundle;
    }

    private static Bundle formatNormal(){
        Bundle bundle = new Bundle();
        bundle.putInt(PrinterConfig.addTextInLine.FontSize.BundleName,PrinterConfig.addTextInLine.FontSize.NORMAL_24_24);
        bundle.putString(PrinterConfig.addTextInLine.GlobalFont.BundleName, PrinterFonts.path + PrinterFonts.FONT_AGENCYB);
        return bundle;
    }

    private static Bundle formatDefault(){
        Bundle bundle = new Bundle();
        bundle.putInt(PrinterConfig.addTextInLine.FontSize.BundleName,24);
        bundle.putString(PrinterConfig.addTextInLine.GlobalFont.BundleName, PrinterDefine.Font_default);
        return bundle;
    }

    private static Bundle formatBold(){
        Bundle bundle = new Bundle();
        bundle.putInt(PrinterConfig.addTextInLine.FontSize.BundleName,32);
        bundle.putString(PrinterConfig.addTextInLine.GlobalFont.BundleName, PrinterDefine.Font_Bold);
        return bundle;
    }

    public static void printTransaction(){
        Log.i(TAG,"testtest");
        iPrinter = ServiceHelper.getInstance().getPrinter();

            try {

                iPrinter.addTextInLine(formatBold(), "", "UBA", "", 0);
                iPrinter.addText(formatNormal(), "------------------------------------------------"); //CUT
                iPrinter.feedLine(1);

                iPrinter.addTextInLine(formatBold(), "MARCHAND","", "NOM MARCHAND", 0);  // TERMINAL NO
                iPrinter.addTextInLine(formatBold(), "CLIENT","", "NOM CLIENT",  0);  // MERCHANT NO.
                iPrinter.feedLine(2);

                iPrinter.addTextInLine(formatBold(), "", "Montant", "", 0); /// SIGNATURE
                iPrinter.addTextInLine(formatBold(), "", "", "", 0); /// SIGNATURE
                iPrinter.addTextInLine(formatBold(), "", "20.000", "", 0); /// SIGNATURE
                iPrinter.feedLine(2);

                iPrinter.addTextInLine(formatBold(), "REFERENCE",  "NUMERO REFERENCE", "", 0);  // OPERATION NAME.
                iPrinter.feedLine(2);

                iPrinter.addTextInLine(formatBold(), "DATE", "", "DATE", 0); /// SIGNATURE
                iPrinter.feedLine(2);

                iPrinter.addText(formatNormal(), "---------X-----------X--------------X-----------"); //CUT
                iPrinter.feedLine(4);

                print();

            } catch (Exception e) {
                Log.i(TAG,"Exception :"+e.getMessage());

                for (StackTraceElement m : e.getStackTrace()
                ) {
                    Log.i(TAG,"Exception :"+m);

                }
            }
    }

    public Resources getResources(){ return context.getResources(); }

    public static void print() {
        Log.i(TAG, "print");
        try {
            iPrinter.startPrint(printerListener);
            Log.i(TAG, "Show print");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.i(TAG, "Exception :"+e.getMessage());
            for (StackTraceElement m : e.getStackTrace()
            ) {
                Log.i(TAG, "Exception :"+m);

            }
        }
    }

   static PrinterListener printerListener = new PrinterListener.Stub() {
        @Override
        public void onFinish() throws RemoteException {
            Log.i(TAG, "Fin impression");
        }

        @Override
        public void onError(int error) throws RemoteException {
            Log.i(TAG, "Error"+error);
        }
    };

}