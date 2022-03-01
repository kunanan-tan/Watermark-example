package com.example.watermark.serviceImpl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * @author kunanan.t
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class MasterServiceImpl {

    //change ttf path
    @Value("${font.file}")
    private String fontPath;

    public byte[] getWaterMark(byte[] documentBytes, String codeName, String userName) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // pdf
        PdfReader reader = new PdfReader(documentBytes);
        int n = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, outputStream);
        // text watermark
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font font = new Font(baseFont, 70, Font.NORMAL);

        Phrase phrase1 = new Phrase(codeName, font);
        Phrase phrase2 = new Phrase(userName, font);

        // transparency
        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.06f);
        // properties
        PdfContentByte over;
        float a = 0, b = 0, x = 0, y = 0;
        // loop over every page (in case more than one page)
        for (int i = 1; i <= n; i++) {

            a = 300;
            b = 500;
            x = 400;
            y = 400;

            over = stamper.getOverContent(i);
            over.saveState();
            over.setGState(gs1);
            // add text
            ColumnText.showTextAligned(over, Element.ALIGN_RIGHT, phrase1, a, b, 45f);
            ColumnText.showTextAligned(over, Element.ALIGN_RIGHT, phrase2, x, y, 45f);

            over.restoreState();
        }
        stamper.close();
        reader.close();
        return outputStream.toByteArray();
    }
}
