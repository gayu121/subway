package subway.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import subway.bean.Subway;
import subway.util.BaseConnection;

public class Dao {
	
	public static List<Subway> list(String table) {
		String sql = "select * from " +table;
		List<Subway> list = new ArrayList<>();
		Connection conn = BaseConnection.getConnection();
		Statement state = null;
		ResultSet rs = null;

		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			Subway subway = null;
			while (rs.next()) {
				
				String Sname = rs.getString("Sname");
				String Snum = rs.getString("Snum");
				int Id = rs.getInt("Id");
				String Change = rs.getString("Change1");
				subway = new Subway(Id, Sname, Snum, Change);
				list.add(subway);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseConnection.close(rs, state, conn);
		}
		
		return list;
	}
	public static List<List<Subway>> Judge(List<Subway> Endlist,List<Subway> Startlist,List<Subway> listGather,List<Subway> listchange)
	{
		List<List<Subway>> result=new ArrayList();
		for(int k=0;k<Startlist.size();k++)
		{
			for(int h=0;h<Endlist.size();h++)
//		if(Startlist.size()==1&&Endlist.size()==1)
		{
			Subway waystart=Startlist.get(k);
			Subway wayend=Endlist.get(h);
			if(waystart.getSnum().equals(wayend.getSnum()))//不换站
			{
				List<Subway> num0=new ArrayList();
				if(waystart.getId()>=wayend.getId())
				{
					for(int n=listGather.size()-1;n>=0;n--)
					{
						if(listGather.get(n).getSnum().equals(waystart.getSnum()))
						{
							if(listGather.get(n).getId()<=waystart.getId()&&listGather.get(n).getId()>=wayend.getId())
							{
								num0.add(listGather.get(n));
							}
						}
					}
					result.add(num0);
				}
				if(waystart.getId()<wayend.getId())
				{
					for(int n=0;n<listGather.size();n++)
					{
						if(listGather.get(n).getSnum().equals(waystart.getSnum()))
						{
							if(listGather.get(n).getId()>=waystart.getId()&&listGather.get(n).getId()<=wayend.getId())
							{
								num0.add(listGather.get(n));
							}
						}
					}
					result.add(num0);
				}
			}
			for(int i=0;i<listchange.size();i++)
			{
				if(listchange.get(i).getSnum().equals(waystart.getSnum()))
				{
					if(listchange.get(i).getChange().equals(wayend.getSnum()))//换站一次
					{
						List<Subway> num1=new ArrayList();
						for(int e1=0;e1<listchange.size();e1++)
						{
							if(listchange.get(i).getSname().equals(listchange.get(e1).getSname())&&!listchange.get(i).getSnum().equals(listchange.get(e1).getSnum()))//获得换站点换站后的线路
							{
							    if(listchange.get(i).getId()>waystart.getId()&&listchange.get(e1).getId()<wayend.getId())
							    {
								    for(int n1=0;n1<listGather.size();n1++)
								    {
									    if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
									    {
										    if(listGather.get(n1).getId()>=waystart.getId()&&listGather.get(n1).getId()<=listchange.get(i).getId())
										    {
											    num1.add(listGather.get(n1));
										    }
									    }
								    }
								    for(int n1=0;n1<listGather.size();n1++)
								    {
								    	if(listGather.get(n1).getSnum().equals(wayend.getSnum()))
								    	{
								    		if(listGather.get(n1).getId()>listchange.get(e1).getId()&&listGather.get(n1).getId()<=wayend.getId())
								    		{
								    			num1.add(listGather.get(n1));
								    		}
								    	}
								    }
								    result.add(num1);
		
							    }
							    if(listchange.get(i).getId()<waystart.getId()&&listchange.get(e1).getId()<wayend.getId())
							    {
								    for(int n1=listGather.size()-1;n1>=0;n1--)
								    {
									    if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
									    {
										    if(listGather.get(n1).getId()<=waystart.getId()&&listGather.get(n1).getId()>=listchange.get(i).getId())
										    {
										    	num1.add(listGather.get(n1));
										    }
									    }
								    }
								    for(int n1=0;n1<listGather.size();n1++)
								    {
								    	if(listGather.get(n1).getSnum().equals(wayend.getSnum()))
								    	{
								    		if(listGather.get(n1).getId()>listchange.get(e1).getId()&&listGather.get(n1).getId()<=wayend.getId())
								    		{
								    			num1.add(listGather.get(n1));
								    		}
								    		
								    	}
								    }
								    result.add(num1);
							    }
							    if(listchange.get(i).getId()<waystart.getId()&&listchange.get(e1).getId()>wayend.getId())
							    {
								    for(int n1=listGather.size()-1;n1>=0;n1--)
								    {
									    if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
									    {
										    if(listGather.get(n1).getId()<=waystart.getId()&&listGather.get(n1).getId()>=listchange.get(i).getId())
										    {
										    	num1.add(listGather.get(n1));
										    }
									    }
								    }
								    for(int n1=listGather.size()-1;n1>=0;n1--)
								    {
								    	if(listGather.get(n1).getSnum().equals(wayend.getSnum()))
								    	{
								    		if(listGather.get(n1).getId()<listchange.get(e1).getId()&&listGather.get(n1).getId()>=wayend.getId())
								    		{
								    			num1.add(listGather.get(n1));
								    		}
								    		
								    	}
								    }
								    result.add(num1);
							    }
							    if(listchange.get(i).getId()>waystart.getId()&&listchange.get(e1).getId()>wayend.getId())
							    {
								    for(int n1=0;n1<listGather.size();n1++)
								    {
									    if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
									    {
										    if(listGather.get(n1).getId()>=waystart.getId()&&listGather.get(n1).getId()<=listchange.get(i).getId())
										    {
										    	num1.add(listGather.get(n1));
										    }
									    }
								    }
								    for(int n1=listGather.size()-1;n1>=0;n1--)
								    {
								    	if(listGather.get(n1).getSnum().equals(wayend.getSnum()))
								    	{
								    		if(listGather.get(n1).getId()<listchange.get(e1).getId()&&listGather.get(n1).getId()>=wayend.getId())
								    		{
								    			num1.add(listGather.get(n1));
								    		}
								    		
								    	}
								    }
								    result.add(num1);
							    }
							}
						}
					}
					if(!listchange.get(i).getChange().equals(wayend.getSnum()))//换两次
					{
						List<Subway> num2=new ArrayList();
						for(int e1=0;e1<listchange.size();e1++)
						{
							if(listchange.get(i).getSname().equals(listchange.get(e1).getSname())&&!listchange.get(i).getSnum().equals(listchange.get(e1).getSnum()))//获得交换站的另一条线路
							{
								for(int t1=0;t1<listchange.size();t1++)
								{
									if(listchange.get(e1).getSnum().equals(listchange.get(t1).getSnum())&&!listchange.get(e1).getSname().equals(listchange.get(t1).getSname()))//获得换线后的交换站
									{
										if(listchange.get(t1).getChange().equals(wayend.getSnum()))//确定交换站位置
										{
											for(int t2=0;t2<listchange.size();t2++)
											{
												if(listchange.get(t1).getSname().equals(listchange.get(t2).getSname())&&!listchange.get(t1).getSnum().equals(listchange.get(t2).getSnum()))
												{
													if(listchange.get(i).getId()>waystart.getId()&&listchange.get(e1).getId()>listchange.get(t1).getId()&&listchange.get(t2).getId()>wayend.getId())
														//> > >
													{
														num2=new ArrayList();
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()>=waystart.getId()&&listGather.get(n1).getId()<=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()>=listchange.get(t1).getId()&&listGather.get(n1).getId()<listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()>=wayend.getId()&&listGather.get(n1).getId()<listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()<waystart.getId()&&listchange.get(e1).getId()>listchange.get(t1).getId()&&listchange.get(t2).getId()>wayend.getId())
														//< > >
													{
														num2=new ArrayList();
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()<=waystart.getId()&&listGather.get(n1).getId()>=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()>=listchange.get(t1).getId()&&listGather.get(n1).getId()<listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()>=wayend.getId()&&listGather.get(n1).getId()<listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()>waystart.getId()&&listchange.get(e1).getId()<listchange.get(t1).getId()&&listchange.get(t2).getId()>wayend.getId())
														//> < >
													{
														num2=new ArrayList();
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()>=waystart.getId()&&listGather.get(n1).getId()<=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()<=listchange.get(t1).getId()&&listGather.get(n1).getId()>listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()>=wayend.getId()&&listGather.get(n1).getId()<listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()>waystart.getId()&&listchange.get(e1).getId()>listchange.get(t1).getId()&&listchange.get(t2).getId()<wayend.getId())
														//> > <
													{
														num2=new ArrayList();
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()>=waystart.getId()&&listGather.get(n1).getId()<=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()>=listchange.get(t1).getId()&&listGather.get(n1).getId()<listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()<=wayend.getId()&&listGather.get(n1).getId()>listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()<waystart.getId()&&listchange.get(e1).getId()<listchange.get(t1).getId()&&listchange.get(t2).getId()>wayend.getId())
														//< < >
													{
														num2=new ArrayList();
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()<=waystart.getId()&&listGather.get(n1).getId()>=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()<=listchange.get(t1).getId()&&listGather.get(n1).getId()>listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()>=wayend.getId()&&listGather.get(n1).getId()<listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()<waystart.getId()&&listchange.get(e1).getId()>listchange.get(t1).getId()&&listchange.get(t2).getId()<wayend.getId())
														//< > <
													{
														num2=new ArrayList();
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()<=waystart.getId()&&listGather.get(n1).getId()>=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()>=listchange.get(t1).getId()&&listGather.get(n1).getId()<listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()<=wayend.getId()&&listGather.get(n1).getId()>listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()>waystart.getId()&&listchange.get(e1).getId()<listchange.get(t1).getId()&&listchange.get(t2).getId()<wayend.getId())
														//> < <
													{
														num2=new ArrayList();
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()>=waystart.getId()&&listGather.get(n1).getId()<=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()<=listchange.get(t1).getId()&&listGather.get(n1).getId()>listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()<=wayend.getId()&&listGather.get(n1).getId()>listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);														System.out.println("");
													}
													if(listchange.get(i).getId()<waystart.getId()&&listchange.get(e1).getId()<listchange.get(t1).getId()&&listchange.get(t2).getId()<wayend.getId())
														//< < <
													{
														num2=new ArrayList();
														for(int n1=listGather.size()-1;n1>=0;n1--)
														{
															if(listGather.get(n1).getSnum().equals(waystart.getSnum()))
															{
																if(listGather.get(n1).getId()<=waystart.getId()&&listGather.get(n1).getId()>=listchange.get(i).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(e1).getSnum()))
															{
																if(listGather.get(n1).getId()<=listchange.get(t1).getId()&&listGather.get(n1).getId()>listchange.get(e1).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														for(int n1=0;n1<listGather.size();n1++)
														{
															if(listGather.get(n1).getSnum().equals(listchange.get(t2).getSnum()))
															{
																if(listGather.get(n1).getId()<wayend.getId()&&listGather.get(n1).getId()>listchange.get(t2).getId())
																{
																	num2.add(listGather.get(n1));																	System.out.print(listGather.get(n1).getSname()+"->");
																}
															}
														}
														result.add(num2);
													}
													
													
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		}
		return result;
	}
	public static List<Subway> CheckStartEnd(String startend,List<Subway> list)
	{
		Subway sub=null;
		List<Subway> StartEndlist=new ArrayList();
		for(int i=0;i<list.size();i++)
		{
			sub=list.get(i);
			if(sub.getSname().equals(startend))
			{
				StartEndlist.add(sub);
			}

			
		}
		return StartEndlist;
	}
	@SuppressWarnings("null")
	public static List<Subway> Gather(List<Subway> list1,List<Subway> list2,List<Subway> list3
			,List<Subway> list4,List<Subway> list5,List<Subway> list6)
	{
		List<Subway> list =new ArrayList();
		Subway sub=null;
		list.addAll(list1);
		list.addAll(list2);
		list.addAll(list3);
		list.addAll(list4);
		list.addAll(list5);
		list.addAll(list6);
		return list;
	}
	public static List<Subway> findChange(String table)
	{
		
		String sql = "select * from "+ table +" where Change1 is not null";
		List<Subway> list = new ArrayList<>();
		Connection conn = BaseConnection.getConnection();
		Statement state = null;
		ResultSet rs = null;

		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			Subway subway = null;
			while (rs.next()) {
				int Id = rs.getInt("Id");
				String Sname = rs.getString("Sname");
				String Snum = rs.getString("Snum");
				String Change1 = rs.getString("Change1");
				subway = new Subway(Id, Sname, Snum, Change1);
				list.add(subway);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseConnection.close(rs, state, conn);
		}
		return list;
	}

}
