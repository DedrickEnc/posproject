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

    public static void printTransaction(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId){

        iPrinter = ServiceHelper.getInstance().getPrinter();
            try {
                iPrinter.addTextInLine(formatBold(), "", bank, "", 0);
                iPrinter.addText(formatNormal(), "------------------------------------------------");
                iPrinter.feedLine(1);

                iPrinter.addTextInLine(formatBold(), "",bankingAgent,"", 0);
                iPrinter.addTextInLine(formatBold(), "",activityPoint,"", 0);
                iPrinter.addTextInLine(formatNormal(), "",address,"", 0);
                iPrinter.addText(formatNormal(), "------------------------------------------------");
                iPrinter.feedLine(1);

                iPrinter.addTextInLine(formatNormal(), "",created_at,"",  0);
                iPrinter.addTextInLine(formatBold(), "",transactionType,"",  0);
                iPrinter.addTextInLine(formatNormal(), "Transaction Num","", stan,  0);
                iPrinter.addTextInLine(formatNormal(), "Compte Num","", account_number,  0);
                iPrinter.addTextInLine(formatNormal(), "Terminal ID","", terminalId,  0);

                iPrinter.addTextInLine(formatBold(), "",main_amount + currency, "",  0);
                iPrinter.feedLine(3);

                iPrinter.addTextInLine(formatBold(), "",description, "",  0);

                iPrinter.addText(formatNormal(), "------------------------------------------------");
                iPrinter.addTextInLine(formatNormal(), "Signature","", "",  0);
                iPrinter.feedLine(3);
                iPrinter.addText(formatNormal(), "---------X-----------X--------------X-----------");
                iPrinter.feedLine(4);


                iPrinter.addTextInLine(formatBold(), "", bank, "", 0);
                iPrinter.addTextInLine(formatBold(), "", "--COPY--", "", 0);
                iPrinter.addText(formatNormal(), "------------------------------------------------");
                iPrinter.feedLine(1);

                iPrinter.addTextInLine(formatBold(), "",bankingAgent,"", 0);
                iPrinter.addTextInLine(formatBold(), "",activityPoint,"", 0);
                iPrinter.addTextInLine(formatNormal(), "",address,"", 0);
                iPrinter.addText(formatNormal(), "------------------------------------------------");
                iPrinter.feedLine(1);

                iPrinter.addTextInLine(formatNormal(), "",created_at,"",  0);
                iPrinter.addTextInLine(formatBold(), "",transactionType,"",  0);
                iPrinter.addTextInLine(formatNormal(), "Transaction Num","", stan,  0);
                iPrinter.addTextInLine(formatNormal(), "Compte Num","", account_number,  0);
                iPrinter.addTextInLine(formatNormal(), "Terminal ID","", terminalId,  0);

                iPrinter.addTextInLine(formatBold(), "",main_amount + currency, "",  0);
                iPrinter.feedLine(3);

                iPrinter.addTextInLine(formatBold(), "",description, "",  0);

                iPrinter.addText(formatNormal(), "------------------------------------------------");
                iPrinter.addTextInLine(formatNormal(), "Signature","", "",  0);
                iPrinter.feedLine(2);

                iPrinter.addText(formatNormal(), "---------X-----------X--------------X-----------");

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
        try {
            iPrinter.startPrint(printerListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.i(TAG, "Exception :"+e.getMessage());
        }
    }

   static PrinterListener printerListener = new PrinterListener.Stub() {
        @Override
        public void onFinish() throws RemoteException {
            Log.i(TAG, "printed successfully");
        }

        @Override
        public void onError(int error) throws RemoteException {
            Log.i(TAG, "Error" + error);
        }
    };

}