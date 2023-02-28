package com.app.apisvtes.pdf_report;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class TemplatePDF {
    private static String[] bytenumber;
    private Context context;
    private File pdfFile;
    private Document document;
    PdfWriter pdfWriter;
    private Paragraph paragraph;
    //here you can change fonts,fonts size and fonts color

    private Font fTitle=new Font(Font.FontFamily.TIMES_ROMAN,6,Font.NORMAL,BaseColor.GRAY);
    private Font fSubTitle=new Font(Font.FontFamily.TIMES_ROMAN,4,Font.ITALIC,BaseColor.GRAY);
    private Font fText=new Font(Font.FontFamily.TIMES_ROMAN,4,Font.ITALIC,BaseColor.GRAY);
    private Font fHighText=new Font(Font.FontFamily.TIMES_ROMAN,4,Font.ITALIC, BaseColor.GRAY);
    private Font fRowText=new Font(Font.FontFamily.TIMES_ROMAN,4,Font.ITALIC, BaseColor.GRAY);
    public TemplatePDF(Context context)
    {
        this.context=context;
    }


    public void openDocument()
    {
        createFile();
        try{

            //adjust your page size here
            Rectangle pageSize = new Rectangle(164.41f, 500.41f); //14400 //for 58 mm pos printer
            //document=new Document(PageSize.A4);
            document=new Document(pageSize);
            pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e)
        {
            Log.e("createFile",e.toString());
        }
    }

    private void createFile()
    {
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "PDF");
        if (!folder.exists())
            folder.mkdir();

        //your file name
        pdfFile = new File(folder, "order_receipt.pdf");


    }

    public void closeDocument()
    {
        document.close();
    }

    public void addMetaData(String title, String subject, String author)
    {
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
        
    }



    public void addTitle(String title, String subTitle, String date)
    {


        try {


            paragraph = new Paragraph();
            addChildP(new Paragraph(title, fTitle));
            addChildP(new Paragraph(subTitle, fSubTitle));
            addChildP(new Paragraph("Order Date:"+ date, fHighText));
           // paragraph.setSpacingAfter(30);
            document.add(paragraph);
        }
        catch (Exception e)
        {
            Log.e("addTitle",e.toString());
        }
    }




    public void addImage(Bitmap bm) {

        try {

            Bitmap bmp = bm;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] byteArray = stream.toByteArray();
            // PdfImage img = new PdfImage(arg0, arg1, arg2)

            // Converting byte array into image Image
            Image img = Image.getInstance(byteArray);

            img.setAlignment(Image.ALIGN_BOTTOM);
            img.setAlignment(Image.ALIGN_CENTER);
            img.scaleAbsolute(80f, 20f);
            //img.setAbsolutePosition(imageStartX, imageStartY); // Adding Image

            document.add(img);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }


    }



    public void addImageBody(Bitmap bm) {

        try {

            Bitmap bmp = bm;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] byteArray = stream.toByteArray();
            // PdfImage img = new PdfImage(arg0, arg1, arg2)

            // Converting byte array into image Image
            Image img = Image.getInstance(byteArray);

            img.setAlignment(Image.ALIGN_BOTTOM);
            img.setAlignment(Image.ALIGN_CENTER);
            img.scaleAbsolute(150f, 320f);
            //img.setAbsolutePosition(imageStartX, imageStartY); // Adding Image

            document.add(img);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }


    }


    public void addImageLogo(Bitmap bm) {

        try {

            Bitmap bmp = bm;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] byteArray = stream.toByteArray();
            // PdfImage img = new PdfImage(arg0, arg1, arg2)

            // Converting byte array into image Image
            Image img = Image.getInstance(byteArray);

            img.setAlignment(Image.ALIGN_BOTTOM);
            img.setAlignment(Image.ALIGN_CENTER);

            //    img.setAbsolutePosition(10f, 30f);
            img.scaleAbsolute(20f, 20f);
            //  img.setAbsolutePosition(1, 1);
            //img.setAbsolutePosition(imageStartX, imageStartY); // Adding Image

            //   Image image = Image.getInstance(path+"/"+"images/abi.png");
            //   image.setAbsolutePosition(40f, 770f);
            //    image.scaleAbsolute(70f, 50f);
            //    document.add(image);
            //    response.flushBuffer();


            document.add(img);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }
    }


        public void addImageLogo2(Bitmap bm,Bitmap bm1,String text) {

            try {
                PdfPCell pdfPCell;
                Bitmap bmp = bm;
                Bitmap bmp1 = bm1;

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                bmp1.compress(Bitmap.CompressFormat.JPEG, 50, stream1);

                byte[] byteArray = stream.toByteArray();
                byte[] byteArray1 = stream1.toByteArray();
                // PdfImage img = new PdfImage(arg0, arg1, arg2)

                // Converting byte array into image Image
                Image img = Image.getInstance(byteArray);
                Image img1 = Image.getInstance(byteArray1);

                img.setAbsolutePosition(35f, 450f);
                img.scaleAbsolute(20f, 4);
                 document.add(img);

                img1.setAbsolutePosition(85f, 450f);
                img1.scaleAbsolute(20f, 4f);
                document.add(img1);

                paragraph=new Paragraph(text,fText);
                paragraph.setFirstLineIndent(450);
                paragraph.setIndentationLeft(20);
                paragraph.setSpacingBefore(1);
                paragraph.setSpacingAfter(1);
                paragraph.setAlignment(Element.ALIGN_MIDDLE);

                document.add(paragraph);


            } catch (Exception e) {
                Log.e("addParagraph", e.toString());
            }


    }

    public void addText(String text) {

        try {
            paragraph=new Paragraph(text,fText);
         //   paragraph.setFirstLineIndent(1500);
            paragraph.setIndentationLeft(20);
            paragraph.setSpacingBefore(1);
            paragraph.setSpacingAfter(1);
            paragraph.setFont(fTitle);
            paragraph.setAlignment(Element.ALIGN_MIDDLE);

            document.add(paragraph);


        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }


    }


    public void addImageTitle(Bitmap bm) {

        try {

            Bitmap bmp = bm;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] byteArray = stream.toByteArray();
            // PdfImage img = new PdfImage(arg0, arg1, arg2)

            // Converting byte array into image Image
            Image img = Image.getInstance(byteArray);

            img.setAlignment(Image.ALIGN_BOTTOM);
            img.setAlignment(Image.ALIGN_CENTER);
            img.scaleAbsolute(50f, 17f);
            //img.setAbsolutePosition(imageStartX, imageStartY); // Adding Image

            document.add(img);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }


    }

    public void addImageCompany(Bitmap bm) {

        try {

            Bitmap bmp = bm;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);

            byte[] byteArray = stream.toByteArray();
            // PdfImage img = new PdfImage(arg0, arg1, arg2)

            // Converting byte array into image Image
            Image img = Image.getInstance(byteArray);

            img.setAlignment(Image.ALIGN_BOTTOM);
            img.setAlignment(Image.ALIGN_CENTER);
            img.scaleAbsolute(120f, 40f);
            //img.setAbsolutePosition(imageStartX, imageStartY); // Adding Image
          //  img.setAbsolutePosition(1, 50);

           // image.ScaleAbsolute(75f, 75f);  // Set image size.
         //   img.setAbsolutePosition(50, 50);

            document.add(img);
        } catch (Exception e) {
            Log.e("addParagraph", e.toString());
        }


    }




    public void addChildP(Paragraph childParagraph)
    {

        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }

    public void addParagraph(String text)
    {

        try{

        paragraph=new Paragraph(text,fText);
      //  paragraph.setSpacingAfter(1);
       // paragraph.setSpacingBefore(1);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        }
        catch (Exception e)
        {
            Log.e("addParagraph",e.toString());
        }



    }



    public void addRightParagraph(String text)
    {

        try{

            paragraph=new Paragraph(text,fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
        }
        catch (Exception e)
        {
            Log.e("addParagraph",e.toString());
        }



    }

    public void createTable(String[] header, ArrayList<String[]> clients)
    {

        try {


            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            pdfPTable.setSpacingBefore(5);

            PdfPCell pdfPCell;

            int indexC = 0;
            while (indexC < header.length) {
                pdfPCell = new PdfPCell(new Phrase(header[indexC++], fSubTitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                pdfPCell.setBackgroundColor(BaseColor.WHITE);
                pdfPCell.setBorderColor(BaseColor.GRAY);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexR = 0; indexR < clients.size(); indexR++) {
                String[] row = clients.get(indexR);

                for (indexC = 0; indexC < header.length; indexC++) {
                    pdfPCell = new PdfPCell(new Phrase(row[indexC],fRowText));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                   // pdfPCell.setFixedHeight(40);
                    pdfPCell.setBorder(Rectangle.NO_BORDER);
                    pdfPTable.addCell(pdfPCell);
                }
            }

            paragraph.add(pdfPTable);
            document.add(paragraph);

        }catch (Exception e)
        {
            Log.e("createTable",e.toString());
        }
    }


    public void viewPDF()
    {
        Intent intent=new Intent(context,ViewPDFActivity.class);
        intent.putExtra("path",pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }






}
