package subway.bean;

public class Subway {
	String Sname;
	public Subway(int id, String sname, String snum, String change) 
	{
		// TODO �Զ����ɵĹ��캯�����
		this.id = id;
		this.Sname = sname;
		this.Snum = snum;
		this.Change = change;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSnum() {
		return Snum;
	}
	public void setSnum(String snum) {
		Snum = snum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChange() {
		return Change;
	}
	public void setChange(String change) {
		Change = change;
	}
	String Snum;
	int id;
	String Change;

}
