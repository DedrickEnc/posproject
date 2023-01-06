package com.dedrick.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
//import otherpackege.OtherClass;

import android.util.Log;

/**
 * Created by Simon on 2019/1/21.
 */

public class SSLClient extends Thread {
    private static final String TAG = "SSLClient";
    static SSLContext ssl_ctx;
    InputStream keyStream = null;  // for cert
    SSLComm sslComm;
    String host;
    int port;

    public enum ACTION{
        connect,
        send,
        receive,
        close,
    };

    ACTION action = ACTION.connect;

    public SSLClient( SSLComm sslComm, ACTION action ){
        this.sslComm = sslComm;
        this.action = action;
    }

    @Override
    public void run() {
        switch ( action ){
            case connect:
                sslComm.connect();
                break;
            case send:
                sslComm.send();
                break;
            case receive:
                sslComm.receive();
                break;
            case close:
                sslComm.close();
                break;
        }
    }

}

//class Handler implements Runnable
class Handler extends Thread
{
    private SSLSocket socket;
    private BufferedReader input;
    static public PrintWriter output;


    private String serverUrl = "10.172.24.90";
    private String serverPort = "5001";

    Handler(SSLSocket socket) throws IOException
    {

    }
    Handler() throws IOException
    {

    }

    public void sendMessagameInfoge(String message)
    {
        Handler.output.println(message);
    }

    @Override
    public void run()
    {
        String line;

        try
        {
//            InputStream keyStream;
////            keyStream = context.getAssets().open(DEBUG_CER_NAME);
//
//            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLClient.getSSLContext(40000, ).getSocketFactory();
//            socket = (SSLSocket) socketFactory.createSocket(serverUrl, Integer.parseInt(serverPort));
//            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            Handler.output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            Log.d("SSL", "Created the socket, input, and output!!");
//
//            output.write("POST /sandboxnew/pay/orderquery HTTP/1.1\n" +
//                    "HOST:api.mch.weixin.qq.com\n" +
//                    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,/;q=0.8\n" +
//                    "Accept-Language: zh-cn,zh;q=0.5\n" +
//                    "Accept-Encoding: gzip, deflate\n" +
//                    "Connection: keep-alive\n" +
//                    "Referer: http://api.mch.weixin.qq.com/\n" +
//                    "Content-Length：322\n" +
//                    "Content-Type：application/x-www-form-urlencoded\r\n\r\n" +
//                    "   <appid>wx2421b1c4370ec43b</appid>\n" +
//                    "   <mch_id>10000100</mch_id>\n" +
//                    "   <nonce_str>b7ffb16a7150cf08639db472c5f5bdae</nonce_str>\n" +
//                    "   <out_trade_no>1415717424</out_trade_no>\n" +
//                    "   <sign>9B2EA16C05A5CEF8E53B14D53932D012</sign>\n" +
//                    "</xml>");


            if( input == null ){
                Log.e("SSL", "input is null");
            }

            do
            {
                line = input.readLine();
                while (line == null)
                {
                    line = input.readLine();
                }
                Log.d("SSL read:", line);

                // Parse the message and do something with it
                // Done in a different class
//                OtherClass.parseMessageString(line);
            }
            while ( !line.equals("exit|") );
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
        finally
        {
            try
            {
                if( null != input ){
                    input.close();

                }
                if( null != output ){
                    output.close();

                }
                socket.close();
            }
            catch(IOException ioe)
            {
            }
            finally
            {

            }
        }
    }
}