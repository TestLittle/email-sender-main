package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {
    private Socket socket;
    private String message;
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        this.socket = new Socket(host, port);
    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException {
        OutputStream out = socket.getOutputStream();
        InputStream inp = socket.getInputStream();
        final byte[] buffer = new byte[256];
        int len;
        if(inp.available() > 0){
            len = inp.read(buffer, 0, 256);
            System.out.write(buffer, 0, len);
        }

        message = "MAIL FROM:<" + from +">\r\n";
        out.write(message.getBytes());
        out.flush();

        if(inp.available() > 0){
            len = inp.read(buffer, 0, 256);
            System.out.write(buffer, 0, len);
        }

        message = "RCPT TO:<" + to +">\r\n";
        out.write(message.getBytes());
        out.flush();

        message = "DATA\r\n";
        out.write(message.getBytes());
        message = "Subject: "+ subject +"\r\n";
        out.write(message.getBytes());
        message = text + "\r\n";
        out.write(message.getBytes());
        message = ".\r\n";
        out.write(message.getBytes());
        out.flush();
    }

    /*
     * Sends QUIT and closes the socket
     */
    public void close() throws IOException {
        message = "QUIT\r\n";
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
        out.flush();
        socket.close();
    }
}
