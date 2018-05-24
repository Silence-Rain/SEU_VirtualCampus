package seu.vCampus.bz;
import common.CourseInfo;
import common.MessageTypes;
import common.Student;
import common.StudentRollInfo;
import seu.vCampus.util.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;


public class IStudentImpl implements IStudent, MessageTypes{
	SocketHelper socketHelper = new SocketHelper();
	
	
	ObjectInputStream is;
	ObjectOutputStream os;
	
	public IStudentImpl(SocketHelper sockethelper){
		this.is = sockethelper.getIs();
		this.os = sockethelper.getOs();
	}
	
	public List EnquiryStuById(StudentRollInfo stu){
		//按学生ID查询
		try{
			this.os.writeInt(301);
			this.os.flush();
			this.os.writeObject(stu);
			this.os.flush();
			try{
				if(this.is.readInt()==3011)
				return Arrays.asList((StudentRollInfo)this.is.readObject());
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List EnquiryAllStu(){
		//查找所有学生
		try{
			this.os.writeInt(305);
			this.os.flush();
			try{
				if(this.is.readInt() == 3051)
				return Arrays.asList((StudentRollInfo[])this.is.readObject());
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean AddStu(StudentRollInfo stu){
		//添加学生信息
		try{
			this.os.writeInt(302);
			this.os.flush();
			this.os.writeObject(stu);
			this.os.flush();
			if(this.is.readInt() == 3021)
				return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean DeleteStu(StudentRollInfo stu){
		//删除学生
		try{
			this.os.writeInt(303);
			this.os.flush();
			this.os.writeObject(stu);
			this.os.flush();
			if(this.is.readInt() == 3031)
				return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean ModifyStu(StudentRollInfo stu){
		//修改学生信息
		try{
			this.os.writeInt(304);
			this.os.flush();
			this.os.writeObject(stu);
			this.os.flush();
			if(this.is.readInt() == 3041)
				return true;
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
}
