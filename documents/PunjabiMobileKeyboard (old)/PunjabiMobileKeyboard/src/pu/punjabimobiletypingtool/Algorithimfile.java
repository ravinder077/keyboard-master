package pu.punjabimobiletypingtool;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Algorithimfile {

	Context mcontext;
	DicData[] ddData = new DicData[15000];
	
	DicData[] tChoiceList = new DicData[5];

	int itotalChoicesFound = -1;

	DicData[] ddNewList = new DicData[5000];
	int newListIndex = 0;
	int arr_count=0;
	public Algorithimfile(Context context) {
		// TODO Auto-generated constructor stub
	mcontext=context;
	}
	
	@SuppressLint("DefaultLocale")
	public DicData[]  AlgorithimfileHello(Context context,String searchvalue) {
		// TODO Auto-generated constructor stub
	
	
	mcontext=context;
	
	System.out.println("searchvalue"+searchvalue);
	InputStream ins = mcontext.getResources().openRawResource( mcontext.getResources().getIdentifier("raw/textfile","raw", mcontext.getPackageName()) );
	
	
	
	BufferedReader br = new BufferedReader( new InputStreamReader(ins) );
	
	String strLine;

			try	
			{
			
				while( (strLine = br.readLine()) != null)
				{
					System.out.println(strLine);
				String[] dic = strLine.split("\t");
				
				//ddData[arr_count].strWord = new String(dic[0]);
				ddData[arr_count] = new DicData();
				
				//ddData[arr_count].putData(dic[0]);
				ddData[arr_count].strWord = dic[0];
				ddData[arr_count].ifrequency = Long.valueOf(dic[1]);
				ddData[arr_count].index = arr_count;
				//System.out.println(ddData[arr_count].strWord+"\t\t\t"+ddData[arr_count].ifrequency);
				arr_count++;
				//strArray[arr_count++] = strLine.trim();
				//System.out.println(strLine);
				
				
				
				
				}
		
			
			
			}
			catch (Exception e) {
				// TODO: handle exception
				
				Toast.makeText(mcontext, ""+e.getMessage(), 1000).show();
				
			}
			
			SortArray(ddData, arr_count);
			
			
			for(int i=0;i<arr_count;i++)
			{
				
				System.out.println(" srted words "+ddData[i].strWord);
				
			}
//			System.out.println("index good");
			int index = binarySearch(ddData, searchvalue, arr_count);
			
			System.out.println("index"+index+" "+arr_count);
			
			
			Toast.makeText(mcontext, ""+index, 1000).show();
			
			
			
			
			
			
			
			
			
			for(int i=index, j =0; i<arr_count ; i++)
			{	
				System.out.println("indexx"+i);
				if( ddData[i].strWord.toUpperCase().indexOf(searchvalue.toUpperCase() ) == 0)
				{
					System.out.println("check loop");
					itotalChoicesFound++;
					tChoiceList[itotalChoicesFound] = new DicData();
					tChoiceList[itotalChoicesFound] = ddData[i];
					j++;					
					System.out.println("words "+ddData[i].strWord);
					
					if(j==5)
						break;
				}
			}//end of for loop
			
			
			//System.out.print("Total Choices Found " + (itotalChoicesFound+1) );
			//sort the tChoiceList
			for(int j=0;j< (itotalChoicesFound + 1);j++)
			{
				long freq  = tChoiceList[j].ifrequency;
				int minIndex = 0;
				for(int i=j;i< (itotalChoicesFound + 1);i++)
				{
					if(freq <= tChoiceList[i].ifrequency)
					{
						minIndex = i;
					}
				}
				
				//swap the elements
				DicData obj = new DicData();
				obj = tChoiceList[j];
				tChoiceList[j] = tChoiceList[minIndex];
				tChoiceList[minIndex] = obj;
				
				System.out.println( (j+1) + "\t" + tChoiceList[j].strWord);				
			}
	
			
		
			return tChoiceList;
			
			
			
			
			
			
			
	}
	
	int binarySearch(DicData[] search, String find, int end) 
	{
			
		//variable declaration
		int start, midPt = 0;
		start = 0;
		end = end - 1;
		
		//while loop for the iterations
		while (start <= end) {
		
			//calculate the mid point
			midPt = (start + end) / 2;
			
			//System.out.println("binary search");
			//check wheter string matches or not if matches then return midPt
			if ( find.toUpperCase().compareTo(search[midPt].strWord.toUpperCase()) == 0)
			{
				return midPt;
			} 
			
			//check wheter searchString is greater than or less than the current index string in array
			else if( find.toUpperCase().compareTo(search[midPt].strWord.toUpperCase()) > 0)
			{
				start = midPt + 1;
			} 
			
			else 
			{
				end = midPt - 1;
			}
		}//end of while loop
		//System.out.println("binary search1");
		return midPt;
	}
	public void SortArray(DicData[] ddData, int totalEntries)
	{
		try	
		{
			int liMin;
			for(int i=0;i < totalEntries; i++)
			{
				liMin = i;
				for(int j=i; j < totalEntries; j++)
				{
					if(ddData[liMin].strWord.toUpperCase().compareTo(ddData[j].strWord.toUpperCase()) > 0)
					{
						liMin = j;
					}
				}
				
				DicData ddTemp = new DicData();
				ddTemp = ddData[i];
				ddData[i] = ddData[liMin];
				ddData[liMin] = ddTemp;
			}//end of for loop
		}//end of try block
		catch(Exception ex)
		{
			
			
		}//end of catch function
	}//end of function SortArray

	
	
	
	class DicData
	{
		public String strWord;
		public Long ifrequency;
		public int index;

			public void putData(String str)
			{
				//System.out.println("Putdata ");
				strWord = new String(str);
			}
	}
	
	
}
