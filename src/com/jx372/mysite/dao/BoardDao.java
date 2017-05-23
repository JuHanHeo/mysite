package com.jx372.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.mysite.vo.BoardVo;

public class BoardDao {
	private Connection getConnection() throws SQLException{

		Connection conn = null;


		//1. 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2. Connection 하기
			String url ="jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 

	}

	public List<BoardVo> getList(String start, String kwd){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		int count=0;
		int st;

		if(kwd==null){
			kwd="%";
		} else{
			kwd= "%" + kwd + "%";
		}

		try{
			conn = getConnection();

			String sql = "select b.no, b.title, b.content, b.reg_date, b.hit, b.group_no, b.order_no, b.depth, b.user_no, m.name from board b, member m where m.no = b.user_no and b.title like ? and b.content like ? order by b.group_no desc, order_no limit ?,5 ";
			String sql2 = "select ceil(count(*)/5) from board b, member m where m.no = b.user_no and b.title like ? and b.content like ? order by b.group_no desc, order_no  ";

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, kwd);
			pstmt.setString(2, kwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
				System.out.println(count);
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kwd);
			pstmt.setString(2, kwd);
			if(start==null){
				st=0;
				pstmt.setInt(3, st);
			} else{
				pstmt.setInt(3, (Integer.parseInt(start)-1)*5);
			}
			rs = pstmt.executeQuery();

			while(rs.next()){
				int no = rs.getInt(1);	
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				int hit = rs.getInt(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				int userNo = rs.getInt(9);
				String userName = rs.getString(10);


				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				vo.setCount(count);
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public BoardVo get(int no){

		BoardVo vo = new BoardVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select no, title, content, user_no, group_no, order_no, depth from board where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			if(rs.next()){
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setUserNo(rs.getInt(4));
				vo.setGroupNo(rs.getInt(5));
				vo.setOrderNo(rs.getInt(6));
				vo.setDepth(rs.getInt(7));
			}


			return vo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return vo;
	}

	public boolean update(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set title = ?, content = ? where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			int count = pstmt.executeUpdate();



			return (count==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addHit(int no){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set hit = hit+1 where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			int count = pstmt.executeUpdate();



			return (count==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();

			if(vo.getGroupNo()==-1){
				sql = "insert into board values(null, ?, ?, now(), 0, (select max(b.group_no) from board b)+1, 1, 0, ?)";
			} else{
				updateOrderNo(vo.getOrderNo()+1, vo.getGroupNo());
				sql = "insert into board values(null, ?, ?, now(), 0, ?, ?, ?, ?)";
			}

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());

			if(vo.getGroupNo()==-1){
				pstmt.setInt(3, vo.getUserNo());
			} else{
				pstmt.setInt(3, vo.getGroupNo());
				pstmt.setInt(4, vo.getOrderNo()+1);
				pstmt.setInt(5, vo.getDepth()+1);
				pstmt.setInt(6, vo.getUserNo());
			}


			int count = pstmt.executeUpdate();



			return (count==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOrderNo(int ono, int gno){

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();

			sql = "update board set order_no = order_no + 1 where group_no = ? and order_no >= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, gno);
			pstmt.setInt(2, ono);

			int count = pstmt.executeUpdate();
			return (count==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();

			sql = "delete from board where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			int count = pstmt.executeUpdate();
			return (count==1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<BoardVo> search(String kwd){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = getConnection();

			sql = "select b.no, b.title, b.content, b.reg_date, b.hit, b.group_no, b.order_no, b.depth, b.user_no, m.name from board b, member m where m.no = b.user_no and ( b.title like ? or b.content like ? )  order by b.group_no desc, order_no  ";

			pstmt = conn.prepareStatement(sql);

			kwd = "%" + kwd + "%";
			pstmt.setString(1, kwd);
			pstmt.setString(2, kwd);
			rs = pstmt.executeQuery();

			while(rs.next()){
				int no = rs.getInt(1);	
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				int hit = rs.getInt(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				int userNo = rs.getInt(9);
				String userName = rs.getString(10);


				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				list.add(vo);
			}

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
