package couchdb_loganalysis_and_audit;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Hossein Aghahosseini
 */

//grepLog and getLogArrayList input help :

//threshold :
//           0 : emergency
//           1 : alert
//           2 : critical
//           3 : error
//           4 : warning
//           5 : notice (4XX | 5XX Error)
//           6 : notice
//           7 : info
//           8 : debug
//          -1 : selectLevel Mode Enabled
//
//selectLevel modes are also the same

//date time format : 
//                  yyyy-mm-ddThh:mm:ss

public class couchLog {
    File logFile;
    String Logtext;
    public ArrayList<logLine> allLines = new ArrayList<>();
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
     
    public couchLog(ArrayList<logLine> logLineList)
    {
        this.allLines = logLineList;
    }
    
    public couchLog()
    {
        
    }

    public void importFile(String address)
    {
        Scanner input = new Scanner("");
        try{
        input = new Scanner (new FileReader(address));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        while(input.hasNext())
        {
            String a = input.next();
            if (a.charAt(0) == '[')
            {
                //System.out.println(a);
                allLines.add(new logLine(a));
                //allLines.get(allLines.size()-1).lineContents.add(a);
            }
            //else
            //{
                //System.out.println(a);
                allLines.get(allLines.size()-1).lineContents.add(a);
            //}
        }
        
    }
    
    public void importFileWithDateSelection(String address , String startDate , String endDate)
    {
        if (startDate.equals("*") && endDate.equals("*"))
            importFile(address);
        else if (startDate.equals("*") == false && endDate.equals("*"))
        {
            Scanner input = new Scanner("");
            try{
            input = new Scanner (new FileReader(address));
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            int x = 5;
            while(input.hasNext())
            {
                String a = input.next();
                String b = "";
              
                if (a.charAt(0) == '[' && (a.charAt(1) == 'd' || a.charAt(1) == 'i' || a.charAt(1) == 'w' || a.charAt(1) == 'e' || (a.charAt(1) == 'n' && a.charAt(2) == 'o' ) || (a.charAt(1) == 'c' && a.charAt(2) == 'r' ) || (a.charAt(1) == 'a' && a.charAt(2) == 'l' ) ))
                {
                    b = input.next();

                    x = compareDate(b, startDate);
                    if (x == 1 || x == 0)
                    {
                        allLines.add(new logLine(a));
                        allLines.get(allLines.size()-1).lineContents.add(a);
                        allLines.get(allLines.size()-1).lineContents.add(b);
                    }
                }
                else if (x == 1 || x == 0)
                {
                    allLines.get(allLines.size()-1).lineContents.add(a);
                }
            }
        }
        else if (startDate.equals("*") && endDate.equals("*")  == false)
        {
            Scanner input = new Scanner("");
            try{
            input = new Scanner (new FileReader(address));
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            int x = 5;
            while(input.hasNext())
            {
                String a = input.next();
                String b = "";
              
                if (a.charAt(0) == '[' && (a.charAt(1) == 'd' || a.charAt(1) == 'i' || a.charAt(1) == 'w' || a.charAt(1) == 'e' || (a.charAt(1) == 'n' && a.charAt(2) == 'o' ) || (a.charAt(1) == 'c' && a.charAt(2) == 'r' ) || (a.charAt(1) == 'a' && a.charAt(2) == 'l' ) ))
                {
                    b = input.next();

                    x = compareDate(b, endDate);
                    if (x == -1 || x == 0)
                    {
                        allLines.add(new logLine(a));
                        allLines.get(allLines.size()-1).lineContents.add(a);
                        allLines.get(allLines.size()-1).lineContents.add(b);
                    }
                }
                else if (x == -1 || x == 0)
                {
                    allLines.get(allLines.size()-1).lineContents.add(a);
                }
            }
        }
        else if (startDate.equals("*") == false && endDate.equals("*")  == false)
        {
            Scanner input = new Scanner("");
            try{
            input = new Scanner (new FileReader(address));
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            int x = 5;
            int y = 5;
            while(input.hasNext())
            {
                String a = input.next();
                String b = "";
              
                if (a.charAt(0) == '[' && (a.charAt(1) == 'd' || a.charAt(1) == 'i' || a.charAt(1) == 'w' || a.charAt(1) == 'e' || (a.charAt(1) == 'n' && a.charAt(2) == 'o' ) || (a.charAt(1) == 'c' && a.charAt(2) == 'r' ) || (a.charAt(1) == 'a' && a.charAt(2) == 'l' ) ))
                {
                    b = input.next();

                    x = compareDate(b, startDate);
                    y = compareDate(b, endDate);
                    
                    if ((x == +1 || x == 0) && (y == -1 || y == 0))
                    {
                        allLines.add(new logLine(a));
                        allLines.get(allLines.size()-1).lineContents.add(a);
                        allLines.get(allLines.size()-1).lineContents.add(b);
                    }
                }
                else if ((x == +1 || x == 0) && (y == -1 || y == 0))
                {
                    allLines.get(allLines.size()-1).lineContents.add(a);
                }
            }
        }
        
    }
    
    public void showWarningInColor()
    {
        for (int i = 0 ; i < allLines.size(); i++)
        {
            if (allLines.get(i).level.equals("[warning]"))
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    System.out.print(ANSI_RED + allLines.get(i).lineContents.get(j) + " " + ANSI_RESET);
                }
                
                System.out.println();
            }

            if (allLines.get(i).level.equals("[error]"))
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    System.out.print(ANSI_BLUE + allLines.get(i).lineContents.get(j) + " " + ANSI_RESET);
                }
                
                System.out.println();
            }

            if (allLines.get(i).level.equals("[critical]"))
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    System.out.print(allLines.get(i).lineContents.get(j) + " ");
                }
                
                System.out.println();
            }
            
            if (allLines.get(i).level.equals("[alert]"))
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    System.out.print(allLines.get(i).lineContents.get(j) + " ");
                }
                
                System.out.println();
            }
            
            if (allLines.get(i).level.equals("[emergency]"))
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    System.out.print(allLines.get(i).lineContents.get(j) + " ");
                }
                
                System.out.println();
            }
            
            if (allLines.get(i).level.equals("[notice]"))
            {
                try
                {
                    //if(!allLines.get(i).lineContents.get(10).equals("200"))
                    if(allLines.get(i).lineContents.get(10).charAt(0) == '4' | allLines.get(i).lineContents.get(10).charAt(0) == '5')
                    {    
                        for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                        {
                            System.out.print(allLines.get(i).lineContents.get(j) + " ");
                        }
                        System.out.println();
                    }
                }
                catch(Exception e3) {}
                
            }
        }
        //System.out.println(allLines.get(52).lineContents.get(10));
        //System.out.println(allLines.size());
    }
    
    public String grepLog(int threshold , int selectLevel)
    {   
        String temp = "";
        
        for (int i = 0 ; i < allLines.size(); i++)
        {
            if (allLines.get(i).level.equals("[debug]") && ( threshold == 8 || ( threshold == -1 && selectLevel == 8 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }
            
            if (allLines.get(i).level.equals("[info]") && ( threshold >= 7 || ( threshold == -1 && selectLevel == 7 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }
            
            if (allLines.get(i).level.equals("[notice]"))
            {   
                if (( threshold >= 6 || ( threshold == -1 && selectLevel == 6 )) )
                {
                    for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                    {
                        temp += allLines.get(i).lineContents.get(j) + " ";
                    }

                    temp += "\n";
                }
                else if (( threshold == 5 || ( threshold == -1 && selectLevel == 5 )) ) 
                {
                   try
                   {   
                        if(allLines.get(i).lineContents.get(10).charAt(0) == '4' | allLines.get(i).lineContents.get(10).charAt(0) == '5')
                        {    
                            for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                            {
                                temp += allLines.get(i).lineContents.get(j) + " ";
                            }
                            temp += "\n";
                        }
                    }
                    catch(Exception e3) {}   
                }
                
                 
            }
            
            if (allLines.get(i).level.equals("[warning]") && ( threshold >= 4 || ( threshold == -1 && selectLevel == 4 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }

            if (allLines.get(i).level.equals("[error]") && ( threshold >= 3 || ( threshold == -1 && selectLevel == 3 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }

            if (allLines.get(i).level.equals("[critical]") && ( threshold >= 2 || ( threshold == -1 && selectLevel == 2 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }
            
            if (allLines.get(i).level.equals("[alert]") && ( threshold >= 1 || ( threshold == -1 && selectLevel == 1 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }
            
            if (allLines.get(i).level.equals("[emergency]") && ( threshold >= 0 || ( threshold == -1 && selectLevel == 0 )) )
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    temp += allLines.get(i).lineContents.get(j) + " ";
                }
                
                temp += "\n";
            }
 
        }
        //System.out.println(allLines.size());
        return temp;
    }
    
    public ArrayList<logLine> getLogArrayList(int threshold , int selectLevel)
    {
        ArrayList<logLine> selectedLines = new ArrayList<>();
        
        for (int i = 0 ; i < allLines.size(); i++)
        {
            if (allLines.get(i).level.equals("[debug]") && ( threshold == 8 || ( threshold == -1 && selectLevel == 8 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }
            
            if (allLines.get(i).level.equals("[info]") && ( threshold >= 7 || ( threshold == -1 && selectLevel == 7 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }

            if (allLines.get(i).level.equals("[notice]"))
            {   
                if (( threshold >= 6 || ( threshold == -1 && selectLevel == 6 )) )
                {
                    selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                    
                    for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                    {
                        selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                    }
                }
                else if (( threshold >= 5 || ( threshold == -1 && selectLevel == 5 )) ) 
                {
                   try
                   {   
                        //if(allLines.get(i).lineContents.get(10).charAt(0) == '4' | allLines.get(i).lineContents.get(10).charAt(0) == '5')
                        if(allLines.get(i).lineContents.get(4).charAt(0) != '-' && allLines.get(i).lineContents.get(10).charAt(0) <= '5' && allLines.get(i).lineContents.get(10).charAt(0) >= '1')
                        {    
                            selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                            selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                            
                            for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                            {
                                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                            }
                        }
                    }
                    catch(Exception e3) {}   
                }
                
                 
            }
            
            
            if (allLines.get(i).level.equals("[warning]") && ( threshold >= 4 || ( threshold == -1 && selectLevel == 4 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }

            if (allLines.get(i).level.equals("[error]") && ( threshold >= 3 || ( threshold == -1 && selectLevel == 3 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }

            if (allLines.get(i).level.equals("[critical]") && ( threshold >= 2 || ( threshold == -1 && selectLevel == 2 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }
            
            if (allLines.get(i).level.equals("[alert]") && ( threshold >= 1 || ( threshold == -1 && selectLevel == 1 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }
            
            if (allLines.get(i).level.equals("[emergency]") && ( threshold >= 0 || ( threshold == -1 && selectLevel == 0 )) )
            {
                selectedLines.add(new logLine(allLines.get(i).lineContents.get(0)));
                selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(0));
                
                for (int j = 1 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    selectedLines.get(selectedLines.size()-1).lineContents.add(allLines.get(i).lineContents.get(j));
                }
            }
 
        }

        return selectedLines;
    }
    
    public Boolean exportLogToFile (String fileAddress)
    {
        try
        {
            PrintWriter writer = new PrintWriter(fileAddress, "UTF-8");
            for (int i = 0 ; i < allLines.size(); i++)
            {
                for (int j = 0 ; j < allLines.get(i).lineContents.size() ; j++)
                {
                    System.out.print(allLines.get(i).lineContents.get(j) + " ");
                    writer.print(allLines.get(i).lineContents.get(j) + " ");
                }
                System.out.println();
                writer.print("\n");
            }
            writer.close();
            return true;
        }
        catch (Exception ew)
        {
            System.out.println(ew.getMessage());
            return false;
        }
    }
    
    int compareDate(String date1 , String date2)
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
