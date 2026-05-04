/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project01;

/**
 *
 * @author rafi
 */
import javax.swing.table.DefaultTableModel;
public class DataStore {
    public static DefaultTableModel model = new DefaultTableModel(
            new Object[]{"No", "NIP", "Nama", "No. Telp", "Email", "Agama", "Jenis Kelamin"}, 0
    );
}
