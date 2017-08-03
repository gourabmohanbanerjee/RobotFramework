package POC.Gourab;


import java.io.FileNotFoundException;
import java.io.IOException;

import utilities.Utility;

public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
    		Utility.customLogWriter();
    		Utility.createExcelFile();
    		Utility.exceuteTest();
			Utility.sendMail();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
       
    }
    
    
    
}
