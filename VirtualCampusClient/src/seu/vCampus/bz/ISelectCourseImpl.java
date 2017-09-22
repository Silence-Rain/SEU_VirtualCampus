package seu.vCampus.bz;
import common.CourseInfo;
import common.CourseInfo;
import common.CourseStatusInfo;
import common.MessageTypes;
import seu.vCampus.util.SocketHelper;
import seu.vCampus.view.stu.mainView;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class ISelectCourseImpl implements ISelectCourse,MessageTypes{


	
		//SocketHelper socketHelper = new SocketHelper();
		//PrintWriter pw = socketHelper.getpw();
		
		ObjectInputStream is;
		ObjectOutputStream os;
		String id = mainView.getStudentId();
		
		public ISelectCourseImpl(SocketHelper sockethelper){
			this.is = sockethelper.getIs();
			this.os = sockethelper.getOs();
			//id = sockethelper.getStuId();
			System.out.println("id is "+ id);
		}
		public boolean addCourse(CourseInfo mCourse){//����Ա ��ӿγ�
			try{
				this.os.writeInt(602);
				this.os.flush();
				
				this.os.writeObject(mCourse);
				this.os.flush();
				if(this.is.readInt() == 6021){
					return true;
				}
				}catch(IOException e){
					e.printStackTrace();
			}
			
			return false;
		}
		public boolean modifyCourse(CourseInfo mCourse){//����Ա �޸Ŀγ�
			try{
				this.os.writeInt(604);
				this.os.flush();
				this.os.writeObject(mCourse);
				this.os.flush();
				if(this.is.readInt() == 6041)
					return true;
			}catch(IOException e){
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean deleteCourse(CourseInfo mCourse){//����Ա ɾ���γ�
			
			try{
				this.os.writeInt(603);
				this.os.flush();
				this.os.writeObject(mCourse);
				this.os.flush();
				if(this.is.readInt() == 6031){
					return true;
				}
				}catch(IOException e){
					e.printStackTrace();
			}
			return false;
		}
		
		public boolean selectCourse(String subNum){//ѧ�� ѡ��γ�
			
			//CourseStatusInfo temp = new CourseStatusInfo(subNum, "",User.getuId());
			CourseStatusInfo temp = new CourseStatusInfo(subNum,id);
			try{
				this.os.writeInt(611);
				this.os.flush();
				this.os.writeObject(temp);
				this.os.flush();
				if(this.is.readInt() == 6111){
					return true;
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
			return false;
		}
		
		public boolean cancelCourse(String subNum){//ѧ�� ��ѡ�γ�
			CourseStatusInfo temp = new CourseStatusInfo(subNum,id);
			try{
				this.os.writeInt(612);
				this.os.flush();
				
				this.os.writeObject(temp);
				this.os.flush();
				if(this.is.readInt() == 6121)
					return true;
			}catch(IOException e){
				e.printStackTrace();
			}
			return false;
		}
		
		public List EnquirySelectCourse(String stuId){//�鿴ѡ��Ŀγ�,ѧ��
			CourseStatusInfo temp = new CourseStatusInfo("",stuId);
			System.out.println(temp.getSelector());
			try{
				this.os.writeInt(613);
				this.os.flush();
				this.os.writeObject(temp);
				this.os.flush();
				try{
					if(this.is.readInt() == 6131)
					return Arrays.asList((CourseInfo[])this.is.readObject());//����CourseInfo���͵�List
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		
		public List EnquiryTeacherCourse() {//�鿴��ʦ�α�
			try{
				this.os.writeInt(641);
				this.os.flush();
				try{
					return (List)this.is.readObject();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			
			}
			return null;
		}
		//û��дcheckCourse,�᲻�ᱨ��?
		public List EnquiryAllCourse(){//�鿴���пγ�
			try{
				this.os.writeInt(601);
				this.os.flush();
				try{
					if(this.is.readInt() == 6011)
					return Arrays.asList((CourseInfo[])this.is.readObject());//����CourseInfo���͵�List
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		
		public List EnquirySelectCourseCredit(){//�鿴��ѡ�γ� ѧ��
			CourseStatusInfo temp = new CourseStatusInfo("",id);
			try{
				this.os.writeInt(613);
				this.os.flush();
				this.os.writeObject(temp);
				this.os.flush();
				try{
					if(this.is.readInt() == 6131)
					return Arrays.asList((CourseInfo[])this.is.readObject());//����CourseInfo���͵�List
					
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		
		public List EnquirySelectStudent(CourseInfo teacher){//�鿴��ѡ�γ̵�ѧ��
			try{
				this.os.writeInt(614);
				this.os.flush();
				this.os.writeObject(teacher);
				this.os.flush();
				try{
					if(this.is.readInt() == 6141)
					 return Arrays.asList((CourseStatusInfo[])this.is.readObject());
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		
		public List getStuCourse(String mString) {//�õ�ѧ���Ŀγ�
			
			try{
				this.os.writeInt(635);
				this.os.flush();
				this.os.writeObject(mString);
				this.os.flush();
				try {
					return (List)this.is.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		public List EnquiryCourseById(String mString){//���ݿγ�ID���Ҷ�Ӧ�Ŀγ���Ϣ
			try{
				this.os.writeInt(602);
				this.os.flush();
				this.os.writeObject(mString);
				this.os.flush();
				try{
					return (List)this.is.readObject();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
		
		return null;
		}
	}


