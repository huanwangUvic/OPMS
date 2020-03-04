package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import com.domain.Respro;
import com.util.DBConn;
import com.dao.ResproDAO;

public class ResproDAOImpl implements ResproDAO {
	public boolean addpro(Respro pro) {
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "insert into respro (proname,protype,prodep,apptime,planmoney,prostate,proexplain,probatch,pronum,prokapp,resman,taskingid,plan_con,now_money,pro_con) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setString(1, pro.getProname());
			pre.setInt(2, pro.getProtype());
			pre.setInt(3, pro.getProdep());
			
			pre.setString(4, pro.getApptime());
			pre.setInt(5, pro.getPlanmoney());
			pre.setString(6, pro.getProstate());
			pre.setString(7, pro.getProexplain());
			pre.setString(8, pro.getProbatch());
			pre.setString(9,pro.getPronum());
			pre.setInt(10, pro.getProkapp());
			pre.setInt(11,pro.getResman());
			pre.setInt(12, pro.getTaskingid());
			pre.setInt(13, pro.getPlanCon());
			pre.setInt(14, pro.getNowMoney());
			pre.setInt(15, pro.getProCon());
			// ִ��sql
			int i = pre.executeUpdate();
			System.out.println("Ӱ��������"+i);
			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}

		return false;
	}

	public boolean delpro(int id) {
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "delete from respro where proid=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);

			// ִ��sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return false;
	}
	
	public boolean updateprocon(int id ,int con)
	{
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;
		
		try {
			String sql = "update respro set pro_con=? where proid=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			
			
			pre.setInt(1, con);
			pre.setInt(2, id);
			// ִ��sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		return false;
	}
	
	public boolean updateonly(int id ,int con)
	{
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;
		
		try {
			String sql = "update respro set prokapp=? where proid=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			
			
			pre.setInt(1, con);
			pre.setInt(2, id);
			// ִ��sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		return false;
	}
	
	public boolean updatepro(Respro pro) {
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "update respro set proname=?,protype=?,prodep=?,apptime=?,planmoney=?,prostate=?,proexplain=?,probatch=?,pronum=?,prokapp=?,resman=?,taskingid=?,plan_con=?,now_money=?,pro_con=? where proid=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setString(1, pro.getProname());
			pre.setInt(2, pro.getProtype());
			pre.setInt(3, pro.getProdep());
			
			pre.setString(4, pro.getApptime());
			pre.setInt(5, pro.getPlanmoney());
			pre.setString(6, pro.getProstate());
			pre.setString(7, pro.getProexplain());
			pre.setString(8, pro.getProbatch());
			pre.setString(9,pro.getPronum());
			pre.setInt(10, pro.getProkapp());
			pre.setInt(11, pro.getResman());
			pre.setInt(12, pro.getTaskingid());
			pre.setInt(13, pro.getPlanCon());
			pre.setInt(14, pro.getNowMoney());
			pre.setInt(15, pro.getProCon());
			
			pre.setInt(16, pro.getProid());
			// ִ��sql
			int i = pre.executeUpdate();

			if (i > 0) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		return false;
	}
	public Respro getpro(int id) {
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;
		//���������
		ResultSet res = null;
		
		try {
			String sql = "select * from respro where proid=? ";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			
			//ִ��sql
			res = pre.executeQuery();
			
			if(res.next()){
				Respro pro = new Respro();
				pro.setProid(res.getInt("proid"));
				pro.setProname(res.getString("proname"));
				pro.setProtype(res.getInt("protype"));
				pro.setProdep(res.getInt("prodep"));
				
				pro.setApptime(res.getString("apptime"));
				pro.setPlanmoney(res.getInt("planmoney"));
				pro.setProstate(res.getString("prostate"));
				pro.setProexplain(res.getString("proexplain"));
				pro.setProbatch(res.getString("probatch"));
				pro.setPronum(res.getString("pronum"));
				pro.setProkapp(res.getInt("prokapp"));
				pro.setResman(res.getInt("resman"));
				pro.setTaskingid(res.getInt("taskingid"));
				pro.setPlanCon(res.getInt("plan_con"));
				pro.setNowMoney(res.getInt("now_money"));
				pro.setProCon(res.getInt("pro_con"));
				return pro;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeResultSet(res);
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return null;
	}
	
	public ArrayList<Respro> getAllpro(String sql)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;
		//���������
		ResultSet res = null;
		
		
		try {
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			
			pre = conn.prepareStatement(sql);
			//ִ��sql
			res = pre.executeQuery();
			//��������
			ArrayList<Respro> list = new ArrayList<Respro>();
			
			while(res.next()){
				Respro pro = new Respro();
				pro.setProid(res.getInt("proid"));
				
				//System.out.println(pro.getProid());
				
				pro.setProname(res.getString("proname"));
				pro.setProtype(res.getInt("protype"));
				pro.setProdep(res.getInt("prodep"));
				
				pro.setApptime(res.getString("apptime"));
				pro.setPlanmoney(res.getInt("planmoney"));
				pro.setProstate(res.getString("prostate"));
				pro.setProexplain(res.getString("proexplain"));
				pro.setProbatch(res.getString("probatch"));
				pro.setPronum(res.getString("pronum"));
				pro.setProkapp(res.getInt("prokapp"));
				pro.setResman(res.getInt("resman"));
				pro.setTaskingid(res.getInt("taskingid"));
				pro.setPlanCon(res.getInt("plan_con"));
				pro.setNowMoney(res.getInt("now_money"));
				pro.setProCon(res.getInt("pro_con"));
				list.add(pro);
				
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر�
			DBConn.closeResultSet(res);
			DBConn.closeStatement(pre);
			DBConn.closeConn(conn);
			
		}
		
		return null;
	}
	

}
