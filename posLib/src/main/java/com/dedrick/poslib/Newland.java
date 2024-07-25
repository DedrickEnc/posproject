package com.dedrick.poslib;

import android.app.Application;

import com.newland.nsdk.core.api.internal.NSDKModuleManager;
import com.newland.nsdk.core.api.common.utils.ISOUtils;
import com.newland.nsdk.esc.api.ESCPrinter;
import com.newland.nsdk.esc.common.ESCPrinterException;
import com.newland.nsdk.esc.internal.ESCPrinterImpl;
import java.nio.charset.StandardCharsets;

public class Newland extends POSPrinter {
    private ESCPrinter escPrinter = ESCPrinterImpl.getInstance();

    Newland(Application a){
        super(a);
        this.app = a;
    }
    @Override
    protected void print(String bank, String bankingAgent, String activityPoint, String address, String transactionType, String account_number, String stan, String created_at, String currency, String main_amount, String description, String terminalId) {
        super.print(bank, bankingAgent, activityPoint, address, transactionType, account_number, stan, created_at, currency, main_amount, description, terminalId);
        setPrintFont();
    }

    private void setPrintFont() {
        try {
            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.CHINESE_DOUBLE_WIDTH);
            escPrinter.exec("(1C 21)Chinese Double Width: 设置汉字倍宽\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.CHINESE_DOUBLE_HEIGHT);
            escPrinter.exec("(1C 21)Chinese Double Height: 设置汉字倍高\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.CHINESE_UNDERLINE);
            escPrinter.exec("(1C 21)Chinese underline: 设置汉字下划线\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.RESET);
            escPrinter.exec(ESCCommand.CHINESE_DOUBLE_HEIGHT);
            escPrinter.exec(ESCCommand.CHINESE_DOUBLE_WIDTH_HEIGHT);
            escPrinter.exec(ESCCommand.CHINESE_UNDERLINE);
            escPrinter.exec("(1C 21)Select print mode: Chinese, double width and height, underline: 设置汉字倍宽倍宽有下划线\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.CHINESE_RESET);
            escPrinter.exec("(1C 21)Cancel print mode: 取消设置汉字倍宽倍宽下划线\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec("------------------------------\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.CHINESE_DOUBLE_WIDTH);
            escPrinter.exec(ESCCommand.CHINESE_DOUBLE_HEIGHT);
            escPrinter.exec("(1C 57)Chinese double height and width: 设置汉字倍宽倍宽\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.CHINESE_RESET);
            escPrinter.exec("(1C 57)Cancel chinese height and width: 取消设置汉字倍宽倍宽\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec("------------------------------\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.CHINESE_UNDERLINE_MODE);
            escPrinter.exec("(1C 2D)Select Chinese underline mode: 设置汉字下划线\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec(ESCCommand.CHINESE_UNDERLINE_MODE_CANCEL);
            escPrinter.exec("(1C 2D)Cancel Chinese underline mode: 设置汉字下划线\n".getBytes(StandardCharsets.UTF_8));
            escPrinter.exec("------------------------------\n".getBytes(StandardCharsets.UTF_8));
        } catch (ESCPrinterException e) {
            e.printStackTrace();
        }

    }
}
