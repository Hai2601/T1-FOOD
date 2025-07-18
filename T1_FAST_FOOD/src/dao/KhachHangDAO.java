/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.KhachHangEntity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectDB;
/**
 *
 * @author DELL
 */
public class KhachHangDAO {
    public List<KhachHangEntity> getAll() {
        List<KhachHangEntity> list = new ArrayList();
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "select * from KhachHang";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                KhachHangEntity kh = new KhachHangEntity(result.getInt("MaKhachHang"), result.getString("TenKhachHang"),
                        result.getString("SDT"),
                        result.getString("SoDienThoai"), result.getString("NgaySinh"),
                        result.getString("GioiTinh"));
                list.add(kh);
            }
        } catch (Exception e) {

            System.out.println("Lỗi get all KH" + e.getMessage());
        }
        return list;
    }
    public void insert(KhachHangEntity kh) {
        try {
            Connection con = ConnectDB.getConnect();
            String sql = "insert into KhachHang (TenKH, SDT, GioiTinh, NgaySinh) "
                    + "values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, kh.getTenKH());
            statement.setString(2, kh.getSdt());
            statement.setString(3, kh.getGioiTinh());
            System.out.println(kh.getNgaySinh());
            statement.setDate(4, Date.valueOf(kh.getNgaySinh()));
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi insert khách hàng: " + e.getMessage());
        }
    }
    
}
