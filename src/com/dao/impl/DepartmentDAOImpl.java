package com.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dao.*;
import com.domain.Department;
import com.domain.Respro;
import com.util.DBConn;
public class DepartmentDAOImpl implements DepartmentDAO{
	

	public boolean adddep(Department dep)
	{
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "insert into department (depnum,depname,deprole,deppwd) values(?,?,?,?)";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, dep.getDepnum());
			pre.setString(2,dep.getDepname());
			pre.setInt(3, dep.getDeprole());
			pre.setString(4, dep.getDeppwd());
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
	
	public boolean deldep(int num)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "delete from department where depnum=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, num);

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
	
	public boolean updatedep(Department dep)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "update department set depname=?,deprole=?,deppwd=? where depnum=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			
			pre.setString(1, dep.getDepname());
			pre.setInt(2, dep.getDeprole());
			pre.setString(3, dep.getDeppwd());
			pre.setInt(4, dep.getDepnum());

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
	
	public Department getdep(int num)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;
		//���������
		ResultSet res = null;
		
		try {
			String sql = "select * from department where depnum=? ";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, num);
			
			//ִ��sql
			res = pre.executeQuery();
			
			if(res.next()){
				Department dep = new Department();
				dep.setDepnum(num);
				dep.setDepname(res.getString("depname"));
				dep.setDeprole(res.getInt("deprole"));
				dep.setDeppwd(res.getString("deppwd"));
				return dep;
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
	
	public ArrayList<Department> getAlldep(String sql)
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
			ArrayList<Department> list = new ArrayList<Department>();
			
			while(res.next()){
				Department dep = new Department();
				dep.setDepnum(res.getInt("depnum"));
				dep.setDepname(res.getString("depname"));
				dep.setDeprole(res.getInt("deprole"));
				dep.setDeppwd(res.getString("deppwd"));
				list.add(dep);
				
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
