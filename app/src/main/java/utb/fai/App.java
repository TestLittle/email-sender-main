package utb.fai;

public class App {

    public static void main(String[] args) {
        // TODO: Implement input parameter processing
        String addr = (String) args[0];
        int port = Integer.parseInt(args[1]);
        String senderEmail = (String) args[2];
        String recipientEmail = (String) args[3];
        String subjectEmail = (String) args[4];
        String contentEmail = (String) args[5];
        
        try {
            EmailSender sender = new EmailSender(addr, port);
            sender.send(senderEmail, recipientEmail, subjectEmail, contentEmail);
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
