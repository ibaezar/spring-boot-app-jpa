package com.ibaezar.springboot.jpa.app.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.ibaezar.springboot.jpa.app.models.entities.Invoice;
import com.ibaezar.springboot.jpa.app.models.entities.InvoiceItem;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("invoices/detail")
public class InvoicePdfView extends AbstractPdfView{

    @SuppressWarnings("null")
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {        
        
        Invoice invoice = (Invoice) model.get("invoice");

        MessageSourceAccessor messages = getMessageSourceAccessor();

        PdfPCell cell = null;

        PdfPTable table = new PdfPTable(1);
        table.setSpacingAfter(20);
        cell = new PdfPCell(new Phrase(messages.getMessage("text.global.clientData")));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        table.addCell(cell);
        table.addCell(invoice.getClient().getName() + " " + invoice.getClient().getLastname());
        table.addCell(invoice.getClient().getEmail());

        PdfPTable table2 = new PdfPTable(1);
        table2.setSpacingAfter(20);
        cell = new PdfPCell(new Phrase(messages.getMessage("text.global.invoiceData")));
        cell.setBackgroundColor(new Color(195, 230, 203));
        cell.setPadding(8f);
        table2.addCell(cell);
        table2.addCell(messages.getMessage("text.global.invoice") + ": " + invoice.getId());
        table2.addCell(messages.getMessage("text.global.description") + ": " + invoice.getDescription());
        table2.addCell(messages.getMessage("text.global.date") + ": " + invoice.getCreatedAt());

        document.add(table);
        document.add(table2);

        PdfPTable table3 = new PdfPTable(4);
        table3.setSpacingAfter(20);
        table3.setWidths(new float [] {3.5f, 1, 1, 1.1f});
        table3.addCell(messages.getMessage("text.global.product"));
        table3.addCell(messages.getMessage("text.global.amount"));
        table3.addCell(messages.getMessage("text.global.quantity"));
        table3.addCell(messages.getMessage("text.global.total"));

        for(InvoiceItem item: invoice.getItems()){
            table3.addCell(item.getProduct().getName());
            table3.addCell(item.getProduct().getAmount().toString());
            cell = new PdfPCell(new Phrase(item.getQuantity().toString()));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table3.addCell(cell);
            table3.addCell(item.calculateAmount().toString());
        }

        cell = new PdfPCell(new Phrase(messages.getMessage("text.global.total") + ": "));
        cell.setColspan(3);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table3.addCell(cell);
        table3.addCell(Double.toString(invoice.getTotal()));

        document.add(table3);

        PdfPTable table4 = new PdfPTable(1);
        cell = new PdfPCell(new Phrase(messages.getMessage("text.global.observations")));
        cell.setBackgroundColor(new Color(247, 247, 247));
        cell.setPadding(8f);
        table4.addCell(cell);
        table4.addCell(invoice.getObservation() != null ? invoice.getObservation() : messages.getMessage("text.global.noObservations"));

        document.add(table4);
    }
    
}
