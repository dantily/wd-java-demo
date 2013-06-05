import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CleanFile {
	public static void main(String[] args) throws IOException {
		FileReader in = new FileReader("E:/结果2/结果2原始.txt");
		BufferedReader re1 = new BufferedReader(in);
		FileWriter fw = new FileWriter("E:/结果2/结果2原始-clean.txt");
		
		String s = "";
		String target = "";
		int i = 0;
		while((s=re1.readLine()) != null){
			i = 0;
			byte[] bytes = s.getBytes();
			for(byte b : bytes){
				int x = (int)b;
				if(x<0){
					i = 1;
					System.out.println(s);
					break;
				}
			}
			if(i == 0){
				fw.write(s+"\n");
			}
		}
		
		
//		String str = "2013-04-08 05:17:05,705 [INFO] - GM_9415|10.6.36.23|http://10.127.64.126:8080/csweb/newcstool/upPlayerNew.act?cn=�ύip��ƥ�䲻��ȷ";
//		byte[] bytes = str.getBytes();
//		for(byte b : bytes){
//			int x = (int)b;
//			System.out.print(x+" ");
//		}
		
	}
}
