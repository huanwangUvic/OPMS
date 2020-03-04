package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dao.OperecordDAO;
import com.domain.Operecord;
import com.domain.Respro;
import com.util.DBConn;

public class OperecordDAOImpl  implements OperecordDAO{
	
	public boolean addope(Operecord ope)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "insert into operecord (opetime,opecontent,opeman,opetype) values(?,?,?,?)";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setString(1, ope.getOpetime());
			pre.setString(2, ope.getOpecontent());
			pre.setInt(3, ope.getOpeman());
			pre.setInt(4, ope.getOpetype());
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
	
	public ArrayList<Operecord> getAllope(String sql)
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
			ArrayList<Operecord> list = new ArrayList<Operecord>();
			
			while(res.next()){
				Operecord ope= new Operecord();
				ope.setOpeid(res.getInt("opeid"));
				ope.setOpecontent(res.getString("opecontent"));
				ope.setOpeman(res.getInt("opeman"));
				ope.setOpetime(res.getString("opetime"));
				ope.setOpetype(res.getInt("opetype"));
				list.add(ope);
				
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
