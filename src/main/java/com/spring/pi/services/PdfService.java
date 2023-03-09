package com.spring.pi.services;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring.pi.entities.Actor_construction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;
@Service
@AllArgsConstructor
public class PdfService {

   public ByteArrayInputStream generatePdf(List<Actor_construction> data) throws DocumentException {
        //ByteArrayInputStream
        com.itextpdf.text.Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();
            String imagePath = "src/main/resources/images/img.png";

            Image image = Image.getInstance(imagePath);
            image.scalePercent(40);

            document.add(image);
            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph pgf = new Paragraph("your credit", font);
            pgf.setAlignment(Element.ALIGN_CENTER);
            document.add(pgf);
            document.add(Chunk.NEXTPAGE);
            PdfPTable table = new PdfPTable(3);
            Stream.of("montantRestant", "dateExpriration", "prix").forEach(x -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.BLUE);
                header.setHorizontalAlignment(Element.ALIGN_BASELINE);

                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(x, headFont));
                table.addCell(header);
            });
            for (Actor_construction m : data) {
                PdfPCell titlecell = new PdfPCell((new Phrase(String.valueOf(m.getMontantRestant()))));
                titlecell.setPaddingLeft(1);
                titlecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                titlecell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(titlecell);
                PdfPCell desccell = new PdfPCell((new Phrase(String.valueOf(m.getDate()))));
                desccell.setPaddingLeft(1);
                desccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                desccell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(desccell);
                PdfPCell mesccell = new PdfPCell((new Phrase(String.valueOf(m.getPrixC()))));
                mesccell.setPaddingLeft(1);
                mesccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mesccell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(mesccell);
            }
                document.add(table);
                document.close();


            } catch(BadElementException ex){
                throw new RuntimeException(ex);
            } catch(DocumentException ex){
                throw new RuntimeException(ex);
            } catch(IOException ex){
                throw new RuntimeException(ex);
            }

            /*byte[] pdfBytes = out.toByteArray();
            FileOutputStream fos = new FileOutputStream("C:/pdfs/maisons.pdf");
            fos.write(pdfBytes);
            fos.close();*/


            return new ByteArrayInputStream(out.toByteArray());


        }}
