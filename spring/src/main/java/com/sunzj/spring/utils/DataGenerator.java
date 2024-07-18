package com.sunzj.spring.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataGenerator {
    private static final String URL = "jdbc:mysql://localhost:3306/mustproject";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static final String[] MAJORS = {"计算机", "数学", "物理", "化学", "生物", "历史", "文学", "哲学", "艺术", "音乐"};
    private static final String[] DEPARTMENTS = {"润园", "泽园", "橙园", "医学院", "商学院", "法学院"};

    private static final String[] NAMES = {"王", "李", "张", "刘", "陈", "杨", "黄", "赵", "周", "吴","孙","韩"};

    private static final int BATCH_SIZE = 1000;

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        Random random = new Random();
        Set<String> enrollmentSet = new HashSet<>();

        try {
            // 填充学生信息表
            String studentSql = "INSERT IGNORE INTO student (sid, name, sex, age, address, hometown, phone, xueyuan, shuyuan, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement studentStmt = conn.prepareStatement(studentSql);
            for (int i = 1; i <= 100000; i++) {
                studentStmt.setLong(1, i);
                studentStmt.setString(2, NAMES[random.nextInt(NAMES.length)] + "同学" + i);
                studentStmt.setString(3, random.nextBoolean() ? "男" : "女");
                studentStmt.setInt(4, 18 + random.nextInt(8)); // 年龄在18到25之间
                studentStmt.setString(5, "地址" + i);
                studentStmt.setString(6, "地址" + (i+1));
                studentStmt.setString(7, "123456789" + random.nextInt(10)); // 随机电话号码
                studentStmt.setString(8, MAJORS[random.nextInt(MAJORS.length)] +"学院" ); // 随机xueyuan
                studentStmt.setString(9, "学院"+i);//随即书院
                studentStmt.setInt(10,5+random.nextInt(4) );

                studentStmt.addBatch();

                if (i % BATCH_SIZE == 0) {
                    studentStmt.executeBatch();
                    conn.commit();
                }
            }
            studentStmt.executeBatch();
            conn.commit();

            // 填充课程信息表
            String courseSql = "INSERT IGNORE INTO course (cid, coursename, grade,coursescore,descriptions) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement courseStmt = conn.prepareStatement(courseSql);
            for (int i = 1; i <= 5000; i++) {
                courseStmt.setLong(1, i);
                courseStmt.setString(2, "课程" + i);
                courseStmt.setString(5, "课程描述" + i);
                courseStmt.setInt(3, 2 + random.nextInt(3)); // 学分在2到5之间
                courseStmt.setInt(4, 50+random.nextInt(50)); // 随机总分
                courseStmt.addBatch();

                if (i % BATCH_SIZE == 0) {
                    courseStmt.executeBatch();
                    conn.commit();
                }
            }
            courseStmt.executeBatch();
            conn.commit();

            // 填充选课和成绩表
            String enrollmentSql = "INSERT IGNORE INTO grade (sid, cid, score) VALUES (?, ?, ?)";
            PreparedStatement enrollmentStmt = conn.prepareStatement(enrollmentSql);
            for (int i = 1; i <= 100000; i++) {
                long sid = 1 + random.nextInt(100000); // 随机学生ID
                long cid = 1 + random.nextInt(5000); // 随机课程ID
                String enrollmentKey = sid + "-" + cid;

                // 检查是否已存在该组合
                while (enrollmentSet.contains(enrollmentKey)) {
                    sid = 1 + random.nextInt(100000);
                    cid = 1 + random.nextInt(5000);
                    enrollmentKey = sid + "-" + cid;
                }

                int score=random.nextInt(100);

                // 添加到集合以追踪
                enrollmentSet.add(enrollmentKey);

                // 输出日志以调试
                System.out.println("Student ID: " + sid + ", Course ID: " + cid + ", Grade: " + score);

                enrollmentStmt.setLong(1, sid);
                enrollmentStmt.setLong(2, cid);
                enrollmentStmt.setInt(3, score);
                enrollmentStmt.addBatch();

                if (i % BATCH_SIZE == 0) {
                    enrollmentStmt.executeBatch();
                    conn.commit();
                }
            }
            enrollmentStmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}
