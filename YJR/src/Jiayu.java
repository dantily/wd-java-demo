import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Jiayu {
	 static int num = 0;//行数
	 static int numall = 0;//行数
	 static Map cnmap=new HashMap();
	 static Map map = new HashMap();
	 static Map map2 = new HashMap();
	
	 
	 public static void main(String[] args)
	 {
//		 initvipmap();
		 File file=new File("E:/结果1/结果1原始.txt");//源文件位置
		 File fileWtiter=new File("E:/结果1/结果1原始__明细.txt");
		 FileReader fr;
		 FileWriter wr;

		 BufferedReader in1=null;//包装文件输入流，可整行读取
		 BufferedWriter out1=null;//包装文件输入流，可整行读取
		 if(fileWtiter.exists()){
			    System.out.print("文件存在");
			   }else{
			    System.out.print("文件不存在");
			    try {
					fileWtiter.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//不存在则创建
		 }
		
		try {
			fr = new FileReader(file);
			wr = new FileWriter(fileWtiter);
			in1=new BufferedReader(fr);//包装文件输入流，可整行读取
		    out1=new BufferedWriter(wr);//包装文件输入流，可整行读取
			String line;
			HashMap gmMap = new HashMap<String,HashMap>();
			HashMap ipMap = new HashMap<String,HashMap>();
			HashMap cnMap = new HashMap<String,String>();
			
			while((line=in1.readLine()) != null) {
				if (line.contains("cn"))
				{
					numall+=1;//行数
					String cn  = line.substring(line.indexOf("cn=")+3);
					cnMap.put(cn, cn);
					String gm="";
					if (!line.contains("GM_"))
					{
						gm = line.substring(34,line.indexOf("|")-1);
					}
					else
					{
						gm = line.substring(line.indexOf("GM_"),line.indexOf("GM_")+7);
					}
					String ip = line.substring(line.indexOf("|")+1,line.lastIndexOf("|"));
					HashMap tmpMap;
					if (gmMap.get(gm)!=null)
					{
						tmpMap = (HashMap) gmMap.get(gm);
						tmpMap.put(cn,cn);
						gmMap.put(gm, tmpMap);
					}else
					{
						tmpMap = new HashMap();
						tmpMap.put(cn,cn);
						gmMap.put(gm, tmpMap);
					}
					
					if (ipMap.get(ip)!=null)
					{
						tmpMap = (HashMap) ipMap.get(ip);
						tmpMap.put(cn,cn);
						ipMap.put(ip, tmpMap);
					}else
					{
						tmpMap = new HashMap();
						tmpMap.put(cn,cn);
						ipMap.put(ip, tmpMap);
					}
				
				
				
				}
			}
			
			Iterator it = gmMap.keySet().iterator();
			while (it.hasNext())
			{
				String gmnum = (String) it.next();
				HashMap cns = (HashMap) gmMap.get(gmnum);
				if (cns.size()>20)
				{
					out1.write("工号为："+gmnum+" 得员工"+" 涉嫌非法操作或查询以下账号["+cns.size()+"] 个\r\n");
					Iterator it2 = cns.keySet().iterator();
					//start---注释此while可以不输出具体账号
//					while (it2.hasNext())
//					{
//						String key = (String) it2.next();
//						out1.write(cns.get(key)+",");
//					}
//					end---注释此while可以不输出具体账号
					out1.write("\r\n");
				}
			}
			it = ipMap.keySet().iterator();
			while (it.hasNext())
			{
				String ipnum = (String) it.next();
				HashMap cns = (HashMap) ipMap.get(ipnum);
				if (cns.size()>20)
				{
					out1.write("Ip为："+ipnum+" 得员工"+" 涉嫌非法操作或查询以下账号["+cns.size()+"] 个\r\n");
					Iterator it2 = cns.keySet().iterator();
					//start---注释此while可以不输出具体账号
//					while (it2.hasNext())
//					{
//						String key = (String) it2.next();
//						out1.write(cns.get(key)+",");
//					}
					//end---注释此while可以不输出具体账号
					out1.write("\r\n");
				}
			}
				
			out1.write("GM号 共计"+gmMap.size()+"\r\n");
			out1.write("ip 共计"+ipMap.size()+"\r\n");
			out1.write("账号 共计"+cnMap.size()+"\r\n");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//创建文件输入流f
		finally
		{
			try {
				in1.close();
				out1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//		System.out.println("扫描结束，共扫描"+numall+"行,含有可以信息行数为"+num+"行");
		// 结果二次分析
//		Iterator it = cnmap.keySet().iterator();
//		while(it.hasNext())
//		{
//			String key = (String)it.next();
//			String value = cnmap.get(key).toString();
//			System.out.println(value+"\r\n");
//		}
	 }
	 
	 
	 
	
}
