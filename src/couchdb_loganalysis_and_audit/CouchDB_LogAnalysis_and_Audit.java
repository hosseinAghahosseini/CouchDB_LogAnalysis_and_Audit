/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package couchdb_loganalysis_and_audit;

/**
 *
 * @author Hossein Aghahosseini
 */
public class CouchDB_LogAnalysis_and_Audit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        couchLog cl = new couchLog();
        //cl.importFile("C:\\CouchDB\\couch.log");
        cl.importFileWithDateSelection("C:\\CouchDB\\couch.log","2018-05-10T07:39:24","*");
        
        cl.showWarning();
        //System.out.print(cl.grepLog(-1,5));
        
        //couchLog cl2 = new couchLog(cl.getLogArrayList(-1, 5));
        
        //cl2.showWarning();
        //System.out.print(cl2.grepLog(-1,5));
        
        System.out.println("\nAnalyzed That!");
        
    }
    
}