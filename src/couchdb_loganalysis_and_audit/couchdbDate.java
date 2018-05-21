/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package couchdb_loganalysis_and_audit;

/**
 *
 * @author hosseinAghahosseini
 */
public class couchdbDate {
    //public String date;
    
    public int compareDate(String date1 , String date2)
    {  
//       2018-05-03T19:46:48
       int y1 = Integer.parseInt(date1.substring(0, 4));
       int y2 = Integer.parseInt(date2.substring(0, 4));
       
       //System.out.println(y1);
       
       if (y1 > y2) return 1;
       if (y1 < y2) return -1;
       
       int m1 = Integer.parseInt(date1.substring(5, 7));
       int m2 = Integer.parseInt(date2.substring(5, 7));
       
       if (m1 > m2) return 1;
       if (m1 < m2) return -1;
       
       int d1 = Integer.parseInt(date1.substring(8, 10));
       int d2 = Integer.parseInt(date2.substring(8, 10));
       
       if (d1 > d2) return 1;
       if (d1 < d2) return -1;
       
       int h1 = Integer.parseInt(date1.substring(11, 13));
       int h2 = Integer.parseInt(date2.substring(11, 13));
       
       if (h1 > h2) return 1;
       if (h1 < h2) return -1;
       
       int min1 = Integer.parseInt(date1.substring(14, 16));
       int min2 = Integer.parseInt(date2.substring(14, 16));
       
       if (min1 > min2) return 1;
       if (min1 < min2) return -1;
       
       int s1 = Integer.parseInt(date1.substring(17, 19));
       int s2 = Integer.parseInt(date2.substring(17, 19));
       
       if (s1 > s2) return 1;
       if (s1 < s2) return -1;
       
       return 0;
    }
}
