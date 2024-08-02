package com.dedrick.poslib;

import android.app.Application;

import com.newland.nsdk.esc.api.ESCPrinter;
import com.newland.nsdk.esc.common.ESCPrinterException;
import com.newland.nsdk.esc.internal.ESCPrinterImpl;

import java.nio.charset.StandardCharsets;

public class POSPrinter {
    Application app = null;
    POSPrinter(Application a){
        this.app = a;

    }
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number,String stan, String created_at, String currency, String main_amount, String description, String terminalId){
        ESCPrinter escPrinter = ESCPrinterImpl.getInstance();
        try {
            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec(ESCCommand.FONT_MIXB);
            escPrinter.exec(ESCCommand.UNDERLINE_MODE_OFF);
            escPrinter.exec((bank + "\n").getBytes(StandardCharsets.UTF_8));

            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec((bankingAgent + "\n").getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec((activityPoint + "\n").getBytes(StandardCharsets.UTF_8));

            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.COMPRESS_FONT);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec((address + "\n").getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.COMPRESS_FONT);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec((created_at + "\n").getBytes(StandardCharsets.UTF_8));

            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec(ESCCommand.DOUBLE_HEIGHT);
            escPrinter.exec(ESCCommand.LINE_SPACING_10);
            escPrinter.exec(ESCCommand.FONT_MIXB);
            escPrinter.exec(ESCCommand.UNDERLINE_MODE_OFF);
            escPrinter.exec((transactionType + "\n").getBytes(StandardCharsets.UTF_8));

            escPrinter.exec("\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec("Transaction Num\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec((stan + "\n").getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec("Compte Num\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec((account_number + "\n").getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec("Terminal ID\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec((terminalId + "\n").getBytes(StandardCharsets.UTF_8));

            escPrinter.exec(ESCCommand.ALIGN_CENTER);
            escPrinter.exec(ESCCommand.BOLD);
            escPrinter.exec(ESCCommand.DOUBLE_HEIGHT);
            escPrinter.exec(ESCCommand.LINE_SPACING_10);
            escPrinter.exec(ESCCommand.FONT_MIXB);
            escPrinter.exec(ESCCommand.UNDERLINE_MODE_OFF);
            escPrinter.exec((main_amount + currency + "\n").getBytes(StandardCharsets.UTF_8));

            escPrinter.exec(new byte[] {0x1B, 0x45, 0x01});
            escPrinter.exec(ESCCommand.DIVIDER);

            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec("Signature\n".getBytes(StandardCharsets.UTF_8));

            escPrinter.exec(new byte[] {0x1B, 0x45, 0x01});
            escPrinter.exec(ESCCommand.DIVIDER);
        } catch (ESCPrinterException e) {
            e.printStackTrace();
        }
    }
}
