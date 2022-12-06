package com.ibaezar.springboot.jpa.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.ibaezar.springboot.jpa.app.models.entities.Invoice;
import com.ibaezar.springboot.jpa.app.models.entities.InvoiceItem;

@Component("invoices/detail.xlsx")
public class InvoiceXlsxView extends AbstractXlsxView{

    @SuppressWarnings("null")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        MessageSourceAccessor messages = getMessageSourceAccessor();
        
        Invoice invoice = (Invoice) model.get("invoice");

        //Nombrar archivo de salida
        String nameInvoice = "attachment; filename="+'"'+messages.getMessage("text.global.invoice")+"_"+invoice.getClient().getName().concat("-").concat(invoice.getClient().getLastname())+".xlsx"+'"';
        response.setHeader("Content-Disposition", nameInvoice);
        
        //Nombrar pestaña
        Sheet sheet = workbook.createSheet(messages.getMessage("text.global.invoice"));

        //Datos del cliente
        //Estilos
        CellStyle tbody = workbook.createCellStyle();
        tbody.setBorderBottom(BorderStyle.THIN);
        tbody.setBorderLeft(BorderStyle.THIN);
        tbody.setBorderRight(BorderStyle.THIN);
        tbody.setBorderTop(BorderStyle.THIN);

        CellStyle theaderClient = workbook.createCellStyle();
        theaderClient.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.index);
        theaderClient.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle theaderInvoice = workbook.createCellStyle();
        theaderInvoice.setFillForegroundColor(IndexedColors.BLUE.index);
        theaderInvoice.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle theaderItems = workbook.createCellStyle();
        theaderItems.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        theaderItems.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue(messages.getMessage("text.global.clientData"));
        cell.setCellStyle(theaderClient); //style

        row = sheet.createRow(2);
        cell = row.createCell(1);
        cell.setCellValue(invoice.getClient().getName() + " " + invoice.getClient().getLastname());
        cell.setCellStyle(tbody);

        row = sheet.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue(invoice.getClient().getEmail());
        cell.setCellStyle(tbody);

        //Forma encadenando los metodos para agregar datos al documento xlsx
        sheet.createRow(5).createCell(1).setCellValue(messages.getMessage("text.global.invoiceData"));
        sheet.createRow(6).createCell(1).setCellValue(messages.getMessage("text.global.invoice")+": " + invoice.getId());
        sheet.createRow(7).createCell(1).setCellValue(messages.getMessage("text.global.description")+": " + invoice.getDescription());
        sheet.createRow(8).createCell(1).setCellValue(messages.getMessage("text.global.date")+": " + invoice.getCreatedAt());

        sheet.getRow(5).getCell(1).setCellStyle(theaderInvoice);
        sheet.getRow(6).getCell(1).setCellStyle(tbody);
        sheet.getRow(7).getCell(1).setCellStyle(tbody);
        sheet.getRow(8).getCell(1).setCellStyle(tbody);

        Row header = sheet.createRow(10);
        header.createCell(1).setCellValue(messages.getMessage("text.global.product"));
        header.createCell(2).setCellValue(messages.getMessage("text.global.amount"));
        header.createCell(3).setCellValue(messages.getMessage("text.global.quantity"));
        header.createCell(4).setCellValue(messages.getMessage("text.global.total"));

        header.getCell(1).setCellStyle(theaderItems);
        header.getCell(2).setCellStyle(theaderItems);
        header.getCell(3).setCellStyle(theaderItems);
        header.getCell(4).setCellStyle(theaderItems);

        int rownum = 11;

        for(InvoiceItem item: invoice.getItems()){
            Row rowItem = sheet.createRow(rownum ++);
            rowItem.createCell(1).setCellValue(item.getProduct().getName());
            rowItem.createCell(2).setCellValue(item.getProduct().getAmount());
            rowItem.createCell(3).setCellValue(item.getQuantity());
            rowItem.createCell(4).setCellValue(item.calculateAmount());

            rowItem.getCell(1).setCellStyle(tbody);
            rowItem.getCell(2).setCellStyle(tbody);
            rowItem.getCell(3).setCellStyle(tbody);
            rowItem.getCell(4).setCellStyle(tbody);

        }

        Row totalRow = sheet.createRow(rownum ++);
        totalRow.createCell(3).setCellValue(messages.getMessage("text.global.grandTotal")+": ");
        totalRow.createCell(4).setCellValue(invoice.getTotal());

        totalRow.getCell(3).setCellStyle(theaderClient);
        totalRow.getCell(4).setCellStyle(tbody);

        rownum ++;

        Row obsRow = sheet.createRow(rownum ++);
        obsRow.createCell(1).setCellValue(messages.getMessage("text.global.observations"));
        obsRow.getCell(1).setCellStyle(theaderItems);
        Row obsRowData = sheet.createRow(rownum);
        obsRowData.createCell(1).setCellValue(invoice.getObservation() != null ? invoice.getObservation() : messages.getMessage("text.global.noObservations"));
        obsRowData.getCell(1).setCellStyle(tbody);

    }
    

}
