package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���ݿ����Ӷ���
 * 
 * @author liu
 * 
 */
public class DBConn {

	/**
	 * ��ȡ���ݿ�����
	 * @return ��������
	 */
	public static Connection getConn() {

		try {
			// ע��һ������
			Class.forName("com.mysql.jdbc.Driver");
			// �����ݿ⽨��һ������
			Connection con = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/epb?user=root&password=52123");

			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * �ر�����
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * �ر����ݿ��������
	 * @param pst
	 */
	public static void closeStatement(java.sql.PreparedStatement pst){
		if(pst != null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �رս����
	 * @param res
	 */
	public static void closeResultSet(ResultSet res){
		if(res != null){
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(DBConn.getConn()); 
	}*/
}
