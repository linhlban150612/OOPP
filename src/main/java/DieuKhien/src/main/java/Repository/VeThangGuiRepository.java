/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Database.DatabaseConnect;
import Entity.VeThangGui;
import Entity.VeThangGui;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class VeThangGuiRepository {
    public static int NhanXe(VeThangGui veThangGui) {
        String query = "INSERT INTO `vethanggui` (`ThoiGianGui`, `IDChoDe`, `IDVe`, `BienSoXe`, `ThoiGianTra`) VALUES ('"+veThangGui.getThoiGianGui()+"', '"+veThangGui.getIDChoDe()+"', '"+veThangGui.getIDVe()+"', '"+veThangGui.getBienSoXe()+"', NULL);";
        try {
            Statement stmt = DatabaseConnect.getConnection().createStatement();
            int i = stmt.executeUpdate(query);
            if (i > 0) 
                return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static ArrayList<VeThangGui> getVeThangGuiByIDVeAndThoiGianTraIsNULL(String s) {
        String query = "SELECT * FROM `vethanggui` WHERE `IDVe` LIKE '"+s+"%' AND `ThoiGianTra` IS NULL;";
        ArrayList<VeThangGui> list = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        LocalDateTime ThoiGianGui;
        String IDChoDe;
        String IDVe;
        String BienSoXe;
        LocalDateTime ThoiGianTra;
        try {
            stmt = DatabaseConnect.getConnection().createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ThoiGianGui = rs.getObject(1, LocalDateTime.class);
                IDChoDe = rs.getString(2);
                IDVe = rs.getString(3);
                BienSoXe = rs.getString(4);
                ThoiGianTra = null;
                VeThangGui veThangGui = new VeThangGui(ThoiGianGui, IDChoDe, IDVe, BienSoXe, ThoiGianTra);
                list.add(veThangGui);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static int SetThoiGianTra(VeThangGui veThangGui) {
        String query = "UPDATE `vethanggui` SET `ThoiGianTra` = '"+veThangGui.getThoiGianTra()+"' WHERE `ThoiGianGui` = '"+veThangGui.getThoiGianGui()+"' AND `IDChoDe` = '"+veThangGui.getIDChoDe()+"' AND `IDVe` = '"+veThangGui.getIDVe()+"';";
        try {
            Statement stmt = DatabaseConnect.getConnection().createStatement();
            int i = stmt.executeUpdate(query);
            if (i > 0) 
                return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
