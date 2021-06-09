package com.ict.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO2 {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	
	// 싱글턴
	private static DAO2 dao2 = new DAO2();
	
	public static DAO2 getInstance() {
		return dao2;
	}
	
	// DB접속
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.18:1521:xe";
			String user = "c##hjh";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 메소드
	public List<VO2> getSelectAll() {
		try {
			List<VO2> list = new ArrayList<VO2>();
			
			conn = getConnection();
			String sql = "select * from gestbook order by idx";
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				VO2 vo = new VO2();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));				
				vo.setTitle(rs.getString("title"));				
				vo.setEmail(rs.getString("email"));
				vo.setContent(rs.getString("content"));
				vo.setPw(rs.getString("pw"));
				vo.setReg(rs.getString("reg"));
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return null;
	}
	
	public int getInsert(VO2 vo) {
		try {
			int result = 0;
			conn = getConnection();
			String sql = "insert into gestbook values(gestbook_seq.nextval, ?, ?, ?, ?, ?, sysdate)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, vo.getName());
			pstm.setString(2, vo.getTitle());
			pstm.setString(3, vo.getEmail());
			pstm.setString(4, vo.getContent());
			pstm.setString(5, vo.getPw());
			result = pstm.executeUpdate();
			
			return result;			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return 0;
	}
	
	// 상세 보기
	public VO2 selectOne(String idx) {
		try {
			VO2 vo = null;
			conn = getConnection();
			String sql = "select * from gestbook where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);

			rs = pstm.executeQuery();
			while (rs.next()) {
				vo = new VO2();
				vo.setIdx(rs.getString("idx"));
				vo.setName(rs.getString("name"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setEmail(rs.getString("email"));
				vo.setPw(rs.getString("pw"));
				vo.setReg(rs.getString("reg"));
			}
			return vo;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
				conn.close();
			} catch (Exception e2) {
			}
		}
		return null;
	}
	
	public int getUpdate(VO2 vo) {
		try {
			int result = 0;
			conn = getConnection();
			String sql = "update gestbook set name=?, title=?, content=?, email=? where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, vo.getName());
			pstm.setString(2, vo.getTitle());
			pstm.setString(3, vo.getContent());
			pstm.setString(4, vo.getEmail());
			pstm.setString(5, vo.getIdx());
			result = pstm.executeUpdate();
			
			return result;			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return 0;
	}
	
	public int getDelete(String idx) {
		try {
			int result = 0;
			conn = getConnection();
			String sql = "delete from gestbook where idx=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, idx);
			result = pstm.executeUpdate();
			
			return result;			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		return 0;
	}
}
