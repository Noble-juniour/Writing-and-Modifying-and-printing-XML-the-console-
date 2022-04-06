/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package writing.and.modifying.and.printing.xml.the.console.in.java;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class WritingAndModifyingAndPrintingXMLTheConsoleInJAVA {

    /**
     * @param args the command line arguments
     * The following program reads from and writes to the same XML file
     */
    
    public static void main(String[] args) {
        /**
          *takes  a file and it reads XML from that file
          *and it stores it in a Document tree object
          *it later calls the XML print method to write the XML to the same file 
         */
        File xmlFile = new File("cars.xml");
        Document dom = LoadXML(xmlFile);
        //get all the owner elements
        NodeList owners = dom.getElementsByTagName("owner");
        for(int i = 0; i < owners.getLength(); i++)
        {
            //access the current item being iterated
            Element owner = (Element)owners.item(i);
            owner.setAttribute("name","kevin indangasi");
        }
        
        //gets all the car elements
        NodeList carsList = dom.getElementsByTagName("car");
        for(int i = 0; i < carsList.getLength();i++)
        {   
          Element car = (Element)carsList.item(i);
          car.setAttribute("vin", "LLOKAJSS55548574");
        }
        
        
        printxml(dom, xmlFile);
        
        loadxmlandprint();
    }
    /**
     * The Method to print/write out the XML
     * @param doc the current storage of the document before conversion
     * @param Destination is the target where the document is to be stored
     */
    public static void printxml(Document doc , File Destination)
    {
        try
        {
            //specific class for XML that is the transformer class created using the factory
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       // Transformer transform = TransformerFactory.newInstance().newTransformer();
        StreamResult result   = new StreamResult(Destination);
        DOMSource source      = new DOMSource(doc);
        transformer.transform(source, result);
        }
        catch(TransformerConfigurationException tce)
        {
            System.out.println("ERROR");
        }
        catch(TransformerException te)
        {
            System.out.println("ERROR");
        }
        
    }
    /**
     * The method that reads in  XML 
     * @param source the file that stored the XML file
     * @return Document
     */
    public static Document LoadXML(File source)
    {
        Document dom = null;
        DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
     try
     {
         DocumentBuilder builder = factory.newDocumentBuilder();
         dom = builder.parse(source);
    
     }
      catch(ParserConfigurationException pce)
      {
          System.out.println(pce.getMessage());
      }
     
     catch(SAXException se)
        {
            System.out.println(se.getMessage());
        }
     
      catch(IOException ioe)
     {
         System.out.println(ioe.getMessage());
     }
        
        return dom;
    }
    /**
     * this method loads the XML and later prints it to a file by calling the print method
     */
    public static void loadxmlandprint()
    {
      Document dom;
      DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
      try
      {
          DocumentBuilder build = fact.newDocumentBuilder();
          dom = build.parse("cars.xml");
          printingThexml(dom);
      }
      catch(ParserConfigurationException pce)
      {
          System.out.println(pce.getMessage());
      }
     catch(SAXException se)
        {
            System.out.println(se.getMessage());
        }
      catch(IOException ioe)
     {
         System.out.println(ioe.getMessage());
     }
    }
    
    /**
     * 
     * @param xml the storage of the XML document
     * prints the XML
     */
    public static void printingThexml(Document xml)
    {
        try{
        Transformer transf = TransformerFactory.newInstance().newTransformer();
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(xml);
        transf.transform(source, result);
        System.out.println(result.getWriter());
        }
        catch(TransformerConfigurationException tce)
        {
            System.err.println(1);
        }
        catch(TransformerException te)
        {
            System.err.println(1);
        }
        }
}

