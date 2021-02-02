package com.apps.merge.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import lombok.extern.slf4j.Slf4j;

/**
 * 合并pdf文件的案例
 */
@Slf4j
public class MergeFile {
    /**
     * 合并PDF文件主入口方法.
     *
     * @param args 参数.
     */
    public static void main(String[] args) {
        String[] handlePdfPath = {"F:\\workspace-pdf-2021\\PdfMergeV1\\src\\main\\resources\\1.pdf", "F:\\workspace-pdf-2021\\PdfMergeV1\\src\\main\\resources\\2.pdf", "e:\\3.pdf"};
        String newPdfPath = "F:\\workspace-pdf-2021\\PdfMergeV1\\src\\main\\resources\\newPdf.pdf";
        Boolean bool = mergePdfFiles(handlePdfPath, newPdfPath);
        log.info("合并结果:{}", bool);
    }

    /**
     * 合并PDF文件.
     *
     * @param pdfFiles    需要合并的pdf文件路径.
     * @param mergeNewPdf 合并之后的pdf文件.
     * @return boolean false true.
     */
    public static boolean mergePdfFiles(String[] pdfFiles, String mergeNewPdf) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(pdfFiles[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(mergeNewPdf));
            document.open();
            for (int i = 0; i < pdfFiles.length; i++) {
                PdfReader reader = new PdfReader(pdfFiles[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("执行结束");
            document.close();
        }
        return retValue;
    }
}
