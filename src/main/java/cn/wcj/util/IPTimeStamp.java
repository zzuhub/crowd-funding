package cn.wcj.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IPTimeStamp {
        private SimpleDateFormat sdf=null  ;     //日期格式类
        private String  ip=null   ;                    //IP地址
        
    
		
		public IPTimeStamp(){}  
        public IPTimeStamp(String ip){     //设置IP地址
        	    this.ip=ip  ;          
        }
        
        public String getIp() {
    			return ip;
    	}
        
    	public void setIp(String ip) {
    			this.ip = ip;
    	}
        
        
        public String getIPTimeStampRand(){      //上传文件重命名操作
        	      StringBuffer buf=new StringBuffer()  ;
        	      if(this.ip!=null){
        	    	         String []s=this.ip.split("\\.");   //拆分IP地址
        	    	         for(int x=0;x<s.length;x++){    //补零操作 
        	    	        	    buf.append(this.addZero(s[x], 3)) ;  
        	    	         }
        	    	         this.sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS") ;  //时间戳格式
        	    	         buf.append(this.sdf.format(new Date()))   ;     //得到格式化的时间戳 
        	    	         Random rand=new Random()  ;     //随机数生成类 
        	    	         for(int x=0;x<3;x++){
        	    	        	       buf.append(rand.nextInt(10))  ;    //增加生成的随机数 
        	    	         }
        	      }
        	      return buf.toString()   ;
        }
        private String addZero(String str,int len){   //字符串,位数
        	    StringBuffer buf=new StringBuffer(str)   ;
        	    while(buf.length()<len){
        	    	   buf.insert(0, 0);   //补零操作 
        	    }
        	    return buf.toString()  ;
        }
//       测试成功
/*        public static void main(String []args){
        	    System.out.println(new IPTimeStamp("127.0.0.1").getIPTimeStampRand());
        	    System.out.println(new IPTimeStamp("192.168.1.1").getIPTimeStampRand());
        } */
     
}