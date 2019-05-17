package com.app.proguard;

import android.app.*;
import android.os.*;
import java.io.*;
import java.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import java.io.OutputStreamWriter; 
import com.app.proguard.App;

public class MainActivity extends Activity 
{
	static HashSet<String> set = new HashSet<>();
	public static EditText textDict,textName, textCount ;
	Button generate;
	static Context context = App.getContext();
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	    textName = (EditText) findViewById(R.id.dict_name);
		textDict= (EditText) findViewById(R.id.text_dict);
		textCount = (EditText) findViewById(R.id.dict_count);
		generate = (Button) findViewById(R.id.btn_denerate); 
		generate.setOnClickListener(new View.OnClickListener() { 
				@Override 
				public void onClick(View v)
				{
					Generator();
				} 
			}); 
    }

	public static void Generator()
	{
		String count = textCount.getText().toString();
		String filename = textName.getText().toString();
		if (count.isEmpty() || filename.isEmpty())
		{
			Toast.makeText(context, "Please, enter text in text field!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			int c = Integer.valueOf(count);
			while (true)
			{
				String text = get();
				if (!set.contains(text))
				{
					if (set.size() == c)
					{
						break;
					}
					set.add(text);
				}
			}
			//Toast.makeText(context, "result set size: " + set.size(), Toast.LENGTH_SHORT).show();
			try
			{
				File sdcard = Environment.getExternalStorageDirectory();
				File dir = new File(sdcard.getAbsolutePath() + "/Proguard Directory/");
				dir.mkdir();
				File myExternalFile = new File(dir , filename + ".txt"); 
				FileWriter writer = new FileWriter(myExternalFile);
				for (String s:set)
				{
					writer.write(s + "\n");
				}
				writer.close();
				Toast.makeText(context, "Writed finish!", Toast.LENGTH_SHORT).show();
			}
			catch (Exception e)
			{
				Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private static String get()
	{
		String s = textDict.getText().toString();
		return generateString(s, new Random().nextInt(14) + 2);
	}

	private static String generateString(String str, int i)
	{
		Random rand = new Random();
		char[] ch = new char[i];
		for (int j = 0; j < i; j++)
		{
			ch[j] = str.charAt(rand.nextInt(str.length()));
		}
		return new String(ch);
	}
}
