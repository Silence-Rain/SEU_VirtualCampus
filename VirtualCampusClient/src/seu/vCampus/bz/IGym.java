
package seu.vCampus.bz;
import java.util.List;
import common.AppointStatusInfo;
import common.AppointInfo;
public abstract interface IGym {
	public abstract List EnquiryRecord(AppointStatusInfo info);//����ԤԼ��¼
	public abstract AppointInfo[] EnquiryItem();//������Ŀʣ�ೡ��
	public abstract boolean AppointItem(AppointStatusInfo item);//ԤԼ��Ŀ
}
