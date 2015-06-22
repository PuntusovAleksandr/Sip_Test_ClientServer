/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package upf;

import javax.sip.*;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.header.*;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.InetAddress;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Alex
 */
public class SipServer extends JFrame implements SipListener {
        
    private SipFactory sipFactory;
    private SipStack sipStack;
    private SipProvider sipProvider;
    private MessageFactory messageFactory;
    private HeaderFactory headerFactory;
    private AddressFactory addressFactory;
    private ListeningPoint listeningPoint;
    private Properties properties;

    private String ip;
    private int port = 5060;
    private String protocol = "udp";
    private int tag = (new Random()).nextInt();
    private Address contactAddress;
    private ContactHeader contactHeader;
    
    /**
     * Creates new form SipRegistrar
     */
    public SipServer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneTable = new JScrollPane();
        jTable = new JTable();
        jScrollPaneText = new JScrollPane();
        jTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIP Server");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                onOpen(evt);
            }
        });

        jTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "URI", "From", "To", "Call-ID", "CSeq", "Dialog", "Transaction", "Type", "Request/Response"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneTable.setViewportView(jTable);

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPaneText.setViewportView(jTextArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTable, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(jScrollPaneText)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPaneTable, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneText, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onOpen(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onOpen
        try {
//            this.ip = InetAddress.getLocalHost().getHostAddress();
//            this.ip = "127.0.0.1";
            this.ip = "127.10.0.1";

            this.sipFactory = SipFactory.getInstance();
            this.sipFactory.setPathName("gov.nist");
            this.properties = new Properties();
            this.properties.setProperty("javax.sip.STACK_NAME", "stack");
            this.sipStack = this.sipFactory.createSipStack(this.properties);
            this.messageFactory = this.sipFactory.createMessageFactory();
            this.headerFactory = this.sipFactory.createHeaderFactory();
            this.addressFactory = this.sipFactory.createAddressFactory();
            this.listeningPoint = this.sipStack.createListeningPoint(this.ip, this.port, this.protocol);
            this.sipProvider = this.sipStack.createSipProvider(this.listeningPoint);
            this.sipProvider.addSipListener(this);

            this.contactAddress = this.addressFactory.createAddress("sip:" + this.ip + ":" + this.port);
            this.contactHeader = this.headerFactory.createContactHeader(contactAddress);
            
            this.jTextArea.append("Local address: " + this.ip + ":" + this.port + "\n");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_onOpen

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SipServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SipServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SipServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SipServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SipServer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JScrollPane jScrollPaneTable;
    private JScrollPane jScrollPaneText;
    private JTable jTable;
    private JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void processRequest(RequestEvent requestEvent) {
        // Get the request.
        Request request = requestEvent.getRequest();

        this.jTextArea.append("\nRECV " + request.getMethod() + " " + request.getRequestURI().toString());        
        
        try {
            // Get or create the server transaction.
            ServerTransaction transaction = requestEvent.getServerTransaction();
            if(null == transaction) {
                transaction = this.sipProvider.getNewServerTransaction(request);
            }
            
            // Update the SIP message table.
            this.updateTable(requestEvent, request, transaction);

            // Process the request and send a response.
            Response response;
            if(request.getMethod().equals("REGISTER")) {
                // If the request is a REGISTER.
                response = this.messageFactory.createResponse(200, request);
                ((ToHeader)response.getHeader("To")).setTag(String.valueOf(this.tag));
                response.addHeader(this.contactHeader);
                transaction.sendResponse(response);
                this.jTextArea.append(" / SENT " + response.getStatusCode() + " " + response.getReasonPhrase());
            }
            else if(request.getMethod().equals("INVITE")) {
                // If the request is an INVITE.
                response = this.messageFactory.createResponse(200, request);
                ((ToHeader)response.getHeader("To")).setTag(String.valueOf(this.tag));
                response.addHeader(this.contactHeader);
                transaction.sendResponse(response);
                this.jTextArea.append(" / SENT " + response.getStatusCode() + " " + response.getReasonPhrase());
            }
            else if(request.getMethod().equals("ACK")) {
                // If the request is an ACK.
            }
            else if(request.getMethod().equals("BYE")) {
                // If the request is a BYE.
                response = this.messageFactory.createResponse(200, request);
                ((ToHeader)response.getHeader("To")).setTag(String.valueOf(this.tag));
                response.addHeader(this.contactHeader);
                transaction.sendResponse(response);
                this.jTextArea.append(" / SENT " + response.getStatusCode() + " " + response.getReasonPhrase());
            }
        }
        catch(SipException e) {            
            this.jTextArea.append("\nERROR (SIP): " + e.getMessage());
        }
        catch(Exception e) {
            this.jTextArea.append("\nERROR: " + e.getMessage());
        }
    }

    @Override
    public void processResponse(ResponseEvent responseEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processTimeout(TimeoutEvent timeoutEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processIOException(IOExceptionEvent exceptionEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void updateTable(RequestEvent requestEvent, Request request, ServerTransaction transaction) {
        // Get the table model.
        DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
        // Get the headers.
        FromHeader from = (FromHeader)request.getHeader("From");
        ToHeader to = (ToHeader)request.getHeader("To");
        CallIdHeader callId = (CallIdHeader)request.getHeader("Call-Id");
        CSeqHeader cSeq = (CSeqHeader)request.getHeader("CSeq");
        // Get the SIP dialog.
        Dialog dialog = transaction.getDialog();
        // Add a new line to the table.
        tableModel.addRow(new Object[] {
            (new Date()).toString(),
            request.getRequestURI() != null ? request.getRequestURI().toString() : "(unknown)",
            from != null ? from.getAddress() : "(unknown)",
            to != null ? to.getAddress() : "(unknown)",
            callId != null ? callId.getCallId() : "(unknown)",
            cSeq != null ? cSeq.getSeqNumber() + " " + cSeq.getMethod() : "(unknown)",
            dialog != null ? dialog.getDialogId() : "",
            transaction.getBranchId(),
            "Request",
            request.getMethod() });
    }
}
