package es.mvm.runman.core;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.mvm.runman.beans.Category;
import es.mvm.runman.beans.Competitor;
import es.mvm.runman.beans.EndRace;
import es.mvm.runman.beans.Race;

import java.io.FileOutputStream;

import java.util.List;

/**
 * Esta clase imprime un listado en un fichero.
 * El fichero será un PDF (txt para pruebas)
 */
public class PrintManager {
    
    public PrintManager() {
        super();
    }
    
    
    /**
     * Imprime el final de una carrera.
     * 
     * Al inicio marca los datos de la carrera y el tiempo del primero.
     * Luego imprime los datos según vienen del EndRace
     * 
     * @param race Los datos de la carrera para imprimir
     */
    public void printEnd(Race race) {
        
        try {
            
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream("FinCarrera.pdf"));
            // step 3
            document.open();
            // step 4
            Paragraph paragraph = new Paragraph(getCabecera(race),
                            FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.RED));
            
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            Paragraph paragraph2 = new Paragraph(getEndData(race.getEndRace()),
                            FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY));
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph2);
            
            PdfPTable table = getNewTable();
            
            getHeadComps(true, true, table);
            
            table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            int i = 1;
            for (Competitor comp : race.getEndRace().getOrderList()) {
                
                getCompetitor(i, comp, true, table);
                i++;
                if (i % 2 == 0)
                    table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                else
                    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
            }
            
            document.add(table);
            
            // step 5
            document.close();
            
        } catch (Exception e) {
            System.out.println("Error " + e);
            e.printStackTrace();
        }
        
    } //printEnd
    
    /**
     * Imprime los resultados de una carrera separados por categorías
     * @param race
     * @param listadoFinal
     */
    public void printEndCat(Race race, List<List<Competitor>>listadoFinal) {
        
        try {
            
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream("FinCarreraCategorias.pdf"));
            // step 3
            document.open();
            // step 4
            Paragraph paragraph = new Paragraph(getCabecera(race),
                            FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.RED));
            
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            Paragraph paragraph2 = new Paragraph(getEndData(race.getEndRace()),
                            FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY));
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph2);
            
            for (List<Competitor> semiList : listadoFinal) {
                
                //cada listado es una categoría...
                Paragraph paraf = new Paragraph(getCategory(semiList.get(0).getCategory()),
                                FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
                document.add(paraf);
                
                PdfPTable table = getNewTable();
                
                getHeadComps(true, false, table);
                
                table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                
                int i = 1;
                
                for (Competitor comp : semiList) {
                    getCompetitor(i, comp, false, table);
                    i++;
                    
                    if (i % 2 == 0)
                        table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                    else
                        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                }
                
                document.add(table);
                
            }
             
            // step 5
            document.close();
            
            
        } catch (Exception e) {
            System.out.println("Error " + e);
            e.printStackTrace();
        }
        
    } //printEndCat
    
    /**
     * Imprime el listado de corredores que viene previamente ordenado.
     * 
     * @param race
     * @param global si es global, no separamos por categoría
     */
    public void printVector(Race race, boolean global) {
        
        try {
            
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream("DatosCarrera.pdf"));
            // step 3
            document.open();
            // step 4
            Paragraph paragraph = new Paragraph(getCabecera(race),
                            FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.RED));
            
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            
            PdfPTable table = null;
            
            Category cat = null;
            int i = 0;
            for (Competitor comp : race.getCompetitors()) {
                
                if (!global && (cat == null || !comp.getCategory().equals(cat))) {
                    
                    if (table != null)
                        document.add(table);
                    
                    table = getNewTable();
                    
                    Paragraph paraf = new Paragraph(getCategory(comp.getCategory()),
                                    FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
                    document.add(paraf);
                    
                    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cat = comp.getCategory();
                    
                    getHeadComps(false, global, table);
                    
                    i = 1;
                    
                    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                    
                }
                
                if (global && table == null) {
                    
                    document.add(new Paragraph("\n"));
                    table = getNewTable();
                    getHeadComps(false, global, table);
                    
                    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                    
                    i = 1;
                }
                
                getCompetitor(0, comp, global, table);
                
                i++;
                
                if (i % 2 == 0)
                    table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                else
                    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

            }
            
            if (table != null)
                document.add(table);
            
            // step 5
            document.close();   
            
        } catch (Exception e) {
            System.out.println("Error " + e);
            e.printStackTrace();
        }
        
    } //printVector
    
    //--------------------------------------------------------------
    // métodos privados
    //--------------------------------------------------------------
    
    private String getCabecera(Race race) {
        String line = race.getName() + " - " + race.getRaceDate() + " - " + race.getLocation() + "\n";
        return line;
        
    } //getCabecera
    
    private String getCategory(Category cat) {
        String line = "\n\t" + cat.getName() + "\n\n";
        
        return line;
    } //getCategory
    
    private String getEndData(EndRace endRace) {
        String line = "RESULTADOS\n";
        line += " *** Mejor tiempo: " + endRace.getFirsTime() + " *** \n\n";
        
        return line;
    } //getEndData
    
    private void getCompetitor(int pos, Competitor comp, boolean global, PdfPTable table) {
        
        if (pos != 0)
            table.addCell("" + pos);
        else
            table.addCell("");
        
        table.addCell("" + comp.getDorsal());
        
        table.addCell(comp.getSurname() + ", " + comp.getName());
        
        if (global)
            table.addCell(comp.getCategory().getName());
        else
            table.addCell("");
        
        if (comp.isLocal())
            table.addCell("LOCAL");
        else
            table.addCell("");
    } //getCompetitor
    
    private void getHeadComps (boolean pos, boolean global, PdfPTable table) {
        
        table.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
        
        if (pos)
            table.addCell("Pos");
        else
            table.addCell("");
        
        table.addCell("Dorsal");
        table.addCell("Apellidos, Nombre");
        if (global)
            table.addCell("Categoría");
        else
            table.addCell("");
        
        table.addCell("Local");
        
        table.setHeaderRows(1);
        
    } //getHeadComps
    
    private PdfPTable getNewTable() {
        PdfPTable table = new PdfPTable(new float[] {1, 2, 4, 4, 3});
        
        table.setWidthPercentage(100f);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        return table;
    }
}
