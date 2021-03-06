/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple_ice_cream;

import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.text.BoxView;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.StyleContext;
import javax.swing.text.View;

/**
 *
 * 
 */
public class dlgPrint extends javax.swing.JDialog implements Printable {

    /**
     * Creates new form dlgPrint
     */
    protected PrintView m_printView;
    protected DefaultStyledDocument m_doc;
    StyleContext m_context;
     private Happycup.Item[] allItems;
    public dlgPrint(java.awt.Frame parent, boolean modal,Happycup.Item[] _allitems) {
        super(parent, modal);
        initComponents();
         m_context = new StyleContext();
        m_doc = new DefaultStyledDocument(m_context);
        txtOutput.setDocument(m_doc);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.allItems = _allitems;
        
        preparePrinting();
    }
    private void preparePrinting()
    {
        double orderTotal = 0;
        StringBuilder strBuilder = new StringBuilder("                      Current Sale\n\n");
        for (Happycup.Item allItem : allItems) {
            
            strBuilder.append("COFFE Type :"+ String.format("%50s", allItem._type)+"\n");
            strBuilder.append("Total Price   :"+ String.format("%50s",allItem._totalwithtax) +"\n");
            strBuilder.append("------------------------------------------------------------------------------\n");
            orderTotal += allItem._totalwithtax;
            
        }
        strBuilder.append("------------------------------------------------------------------------------\n");
        strBuilder.append("\nOrder Total   :"+ String.format("%50s",orderTotal));
        txtOutput.setText(strBuilder.toString());
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
     class PrintView extends BoxView {
        protected int m_firstOnPage = 0;
        protected int m_lastOnPage = 0;
        protected int m_pageIndex = 0;
         
        public PrintView(Element elem, View root, int w, int h) {
            super(elem, Y_AXIS);
            setParent(root);
            setSize(w, h);
            layout(w, h);
        }
         
        public boolean paintPage(Graphics g, int hPage,
        int pageIndex) {
            if (pageIndex > m_pageIndex) {
                m_firstOnPage = m_lastOnPage + 1;
                if (m_firstOnPage >= getViewCount())
                    return false;
                m_pageIndex = pageIndex;
            }
            int yMin = getOffset(Y_AXIS, m_firstOnPage);
            int yMax = yMin + hPage;
            Rectangle rc = new Rectangle();
             
            for (int k = m_firstOnPage; k < getViewCount(); k++) {
                rc.x = getOffset(X_AXIS, k);
                rc.y = getOffset(Y_AXIS, k);
                rc.width = getSpan(X_AXIS, k);
                rc.height = getSpan(Y_AXIS, k);
                if (rc.y+rc.height > yMax)
                    break;
                m_lastOnPage = k;
                rc.y -= yMin;
                paintChild(g, rc, k);
            }
            return true;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        btnClose = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtOutput.setEditable(false);
        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        jScrollPane1.setViewportView(txtOutput);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnClose))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this);
        if (pj.printDialog()) {
            try { pj.print(); }
            catch (PrinterException pe) {
                System.out.println(pe);
            }
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPrint;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics pg, PageFormat pageFormat, int pageIndex) throws PrinterException {
        pg.translate((int)pageFormat.getImageableX(),
        (int)pageFormat.getImageableY());
        int wPage = (int)pageFormat.getImageableWidth();
        int hPage = (int)pageFormat.getImageableHeight();
        pg.setClip(0, 0, wPage, hPage);
         
        // Only do this once per print
        if (m_printView == null) {
            BasicTextUI btui = (BasicTextUI)txtOutput.getUI();
            View root = btui.getRootView( txtOutput );
            m_printView = new PrintView(
            m_doc.getDefaultRootElement(),
            root, wPage, hPage);
        }
         
        boolean bContinue = m_printView.paintPage(pg,
        hPage, pageIndex);
        System.gc();
         
        if (bContinue)
            return PAGE_EXISTS;
        else {
            m_printView = null;
            return NO_SUCH_PAGE;
        }
    }
}
