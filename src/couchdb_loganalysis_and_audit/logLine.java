/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package couchdb_loganalysis_and_audit;

import java.util.ArrayList;

/**
 *
 * @author Hossein Aghahosseini
 */

public class logLine {
    
      String level = "";
//    String Date = "";
//    String couchDB_at = "";
//    String num_dot_num_dot_num = "";
//    String str_or_underscore = "";
    
    public ArrayList<String> lineContents = new ArrayList<>();
    
    public logLine (String logLevel)
    {
        level = logLevel;
    }
    
}
