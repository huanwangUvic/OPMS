package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dao.TaskDAO;
import com.domain.Department;
import com.domain.Respro;
import com.domain.Task;
import com.util.DBConn;

public class TaskDAOImpl implements TaskDAO{
	
	
	public Task gettask(int id)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;
		//���������
		ResultSet res = null;
		
		try {
			String sql = "select * from task where taskid=? ";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			
			//ִ��sql
			res = pre.executeQuery();
			
			if(res.next()){
				Task task = new Task();
				task.setEndTime(res.getString("end_time"));
				task.setFileCon(res.getInt("file_con"));
				task.setPlanTime(res.getString("plan_time"));
				task.setProid(res.getInt("proid"));
				task.setStartTime(res.getString("start_time"));
				task.setSubFile(res.getString("sub_file"));
				task.setTaskCondition(res.getInt("task_condition"));
				task.setTaskid(res.getInt("taskid"));
				task.setTaskname(res.getString("taskname"));
				task.setTaskexplain(res.getString("taskexplain"));
				task.setFilePath(res.getInt("file_path"));
				return task;
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
	
	
	public boolean addtask(Task task)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "insert into task (proid,taskname,plan_time,start_time,end_time,task_condition,sub_file,file_con,taskexplain,file_path) values(?,?,?,?,?,?,?,?,?,?)";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, task.getProid());
			pre.setString(2, task.getTaskname());
			pre.setString(3, task.getPlanTime());
			pre.setString(4, task.getStartTime());
			pre.setString(5, task.getEndTime());
			pre.setInt(6, task.getTaskCondition());
			pre.setString(7, task.getSubFile());
			pre.setInt(8, task.getFileCon());
			pre.setString(9, task.getTaskexplain());
			pre.setInt(10, task.getFilePath());
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
	
	public boolean updatetask(Task task)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "update task set proid=?,taskname=?,plan_time=?,start_time=?,end_time=?,task_condition=?,sub_file=?,file_con=?,taskexplain=?,file_path=? where taskid=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			
			pre.setInt(1, task.getProid());
			pre.setString(2, task.getTaskname());
			pre.setString(3, task.getPlanTime());
			pre.setString(4, task.getStartTime());
			pre.setString(5, task.getEndTime());
			pre.setInt(6, task.getTaskCondition());
			pre.setString(7, task.getSubFile());
			pre.setInt(8, task.getFileCon());
			pre.setString(9, task.getTaskexplain());
			pre.setInt(10, task.getFilePath());
			pre.setInt(11, task.getTaskid());
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
	
	
	public boolean deltask2(int taskid)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "delete from task where taskid=?";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			pre.setInt(1, taskid);

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
	
	
	public boolean deltask(int id)
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "delete from task where proid=?";
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
	
	//public Task gettask(int id);
	
	public ArrayList<Task> getAlltask(String sql)
	{
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
			ArrayList<Task> list = new ArrayList<Task>();
			
			while(res.next()){
				Task task = new Task();
				task.setEndTime(res.getString("end_time"));
				task.setFileCon(res.getInt("file_con"));
				task.setPlanTime(res.getString("plan_time"));
				task.setProid(res.getInt("proid"));
				task.setStartTime(res.getString("start_time"));
				task.setSubFile(res.getString("sub_file"));
				task.setTaskCondition(res.getInt("task_condition"));
				task.setTaskid(res.getInt("taskid"));
				task.setTaskname(res.getString("taskname"));
				task.setTaskexplain(res.getString("taskexplain"));
				task.setFilePath(res.getInt("file_path"));
				list.add(task);
				
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
	
	public boolean updatetask2()
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "update task set file_path = DATEDIFF(end_time,now())";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			
			
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
	
	public boolean updatetask3()
	{
		// ������������
		Connection conn = null;
		// ����ʵ��
		PreparedStatement pre = null;

		try {
			String sql = "update task set task_condition=2 where task_condition=1";
			// ��ȡConnection
			conn = DBConn.getConn();
			// ����ʵ��
			pre = conn.prepareStatement(sql);
			
			
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
}
