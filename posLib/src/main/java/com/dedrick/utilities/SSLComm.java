package com.dedrick.utilities;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Simon on 2019/4/29.
 */

public class SSLComm {

    private static final String TAG = "SSLComm";
    Context context;
    static SSLContext ssl_ctx;
    InputStream keyStream = null;  // for cert
    SSLSocket socket = null;
    String host;
    int port;

    private static final String CLIENT_KEY_PASSWORD = "verifone";
    private static final String TRUST_KEY_PASSWORD = "verifone";

    enum ACTION {
        connect,
        send,
        receive
    }

    ;

    SSLClient.ACTION action = SSLClient.ACTION.connect;

    public SSLComm(Context context, String assetsCert, String host, int port) {
        try {
            this.context = context;
            this.host = host;
            this.port = port;
            keyStream = context.getAssets().open(assetsCert);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SSLContext getSSLContext(int timeOutMillisecond, InputStream... certificates) {
//        long timeout = timeOutMillisecond - 1;
//        try {
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(null);
//            int index = 0;
//            for (InputStream certificate : certificates) {
//                String certificateAlias = Integer.toString(index++);
//                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
//                try {
//                    if (certificate != null)
//                        certificate.close();
//                } catch (IOException e) {
//                }
//            }
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
//                    .getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
//            return sslContext;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        //取得TLS协议的SSLContext实例
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");

            KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            clientKeyStore.load(context.getResources().getAssets().open("keystore/kclient.bks"), CLIENT_KEY_PASSWORD.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(clientKeyStore, CLIENT_KEY_PASSWORD.toCharArray());

            //取得BKS类型的密钥库实例，这里特别注意：手机只支持BKS密钥库，不支持Java默认的JKS密钥库 Get keystore instance of BKS type, if you just
            KeyStore trustKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustKeyStore.load(context.getResources().getAssets().open("keystore/tclient.bks"), TRUST_KEY_PASSWORD.toCharArray());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(trustKeyStore);

            //初始化SSLContext实例
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            return sslContext;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connect() {
        Log.d(TAG, "call connect");
        SSLSocketFactory socketFactory = (SSLSocketFactory) getSSLContext(20000, keyStream).getSocketFactory();

        try {
            socket = (SSLSocket) socketFactory.createSocket(host, port);
//            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            Handler.output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            socket.startHandshake();
            Log.d("SSL", "Created the socket, input, and output!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "connect return");

    }

    public int send() {
        Log.d(TAG, "call send");
        PrintWriter output = null;
        try {
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            output.write("This is a SSL test\n");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("SSL", "Created the socket, input, and output!!");

        try {
            if (null != output) {
                output.close();
            }
        } finally {

        }

        Log.d(TAG, "send return");

        return 0;

    }

    public int receive() {
        Log.d(TAG, "call receive");
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            do {
                line = input.readLine();
                while (line == null) {
                    line = input.readLine();
                }
                Log.d("SSL receive:", line);

                // Parse the message and do something with it
                // Done in a different class
//                OtherClass.parseMessageString(line);
            }
            while (!line.equals("exit|"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (null != input) {
                input.close();
            }
        } catch (IOException ioe) {
        } finally {

        }

        Log.d(TAG, "receive return");

        return 0;

    }

    public int close() {
        Log.d(TAG, "call close");
        try {
            socket.close();
        } catch (IOException ioe) {
        } finally {

        }

        Log.d(TAG, "close return");
        return 0;

    }

}
