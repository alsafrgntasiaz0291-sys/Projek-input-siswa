package tampilan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public final class LaporanSupport {

    private LaporanSupport() {
    }

    public static void susunLaporan(JFrame frame, String judul, JTextField keyword, JTable table,
            JButton home, JButton guru, JButton siswa, JButton jadwal, JButton nilai,
            JButton cari, JButton refresh, JButton cetak) {

        UiHelper.styleButton(home);
        UiHelper.styleButton(guru);
        UiHelper.styleButton(siswa);
        UiHelper.styleButton(jadwal);
        UiHelper.styleButton(nilai);
        UiHelper.styleButton(cari);
        UiHelper.styleButton(refresh);
        UiHelper.styleButton(cetak);
        cetak.setText("Cetak");

        rapikanTabel(table);
        table.addPropertyChangeListener("model", evt -> rapikanTabel(table));

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        nav.setOpaque(false);
        nav.add(guru);
        nav.add(siswa);
        nav.add(jadwal);
        nav.add(nilai);

        JPanel filter = new JPanel(new BorderLayout(12, 0));
        filter.setBackground(UiHelper.PALE_YELLOW);
        filter.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 20, 16, 20));
        JLabel label = new JLabel("Cari");
        label.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 18));
        filter.add(label, BorderLayout.WEST);
        filter.add(keyword, BorderLayout.CENTER);

        JPanel filterButtons = new JPanel(new GridLayout(1, 3, 10, 0));
        filterButtons.setOpaque(false);
        filterButtons.add(cari);
        filterButtons.add(refresh);
        filterButtons.add(cetak);
        filter.add(filterButtons, BorderLayout.EAST);

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(UiHelper.SOFT_GREEN);
        top.add(UiHelper.pageHeader(frame.getClass(), judul, home), BorderLayout.NORTH);
        top.add(nav, BorderLayout.CENTER);
        top.add(filter, BorderLayout.SOUTH);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(UiHelper.SOFT_GREEN);
        body.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 28, 28, 28));
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        body.add(tableScroll, BorderLayout.CENTER);

        JPanel root = new JPanel(new BorderLayout());
        root.add(top, BorderLayout.NORTH);
        root.add(body, BorderLayout.CENTER);

        frame.setTitle(judul);
        frame.setContentPane(root);
        UiHelper.prepareFrame(frame);
    }

    public static void buka(JFrame sekarang, JFrame tujuan) {
        tujuan.setVisible(true);
        sekarang.dispose();
    }

    public static void rapikanTabel(JTable table) {
        table.setRowHeight(28);
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        int columnCount = table.getColumnModel().getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            int width = Math.max(90, table.getColumnName(i).length() * 12);
            table.getColumnModel().getColumn(i).setMinWidth(Math.min(width, 110));
            table.getColumnModel().getColumn(i).setPreferredWidth(width);
        }
    }

    public static void unduhCsv(JFrame frame, JTable table, String namaLaporan) {
        try {
            File downloads = new File(System.getProperty("user.home"), "Downloads");
            if (!downloads.exists()) {
                downloads.mkdirs();
            }

            String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File file = new File(downloads, namaLaporan.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + waktu + ".csv");

            try (FileWriter writer = new FileWriter(file)) {
                for (int col = 0; col < table.getColumnCount(); col++) {
                    if (col > 0) {
                        writer.append(',');
                    }
                    writer.append(csv(table.getColumnName(col)));
                }
                writer.append('\n');

                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        if (col > 0) {
                            writer.append(',');
                        }
                        Object value = table.getValueAt(row, col);
                        writer.append(csv(value == null ? "" : value.toString()));
                    }
                    writer.append('\n');
                }
            }

            JOptionPane.showMessageDialog(frame, "Laporan berhasil diunduh:\n" + file.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    public static void cetakLaporan(JFrame frame, JTable table, String judul) {
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "Data laporan masih kosong.");
            return;
        }

        JFrame preview = new JFrame("Preview " + judul);
        JEditorPane halaman = new JEditorPane("text/html", htmlLaporan(frame, table, judul));
        halaman.setEditable(false);

        JButton cetak = new JButton("Print");
        JButton csv = new JButton("Download CSV");
        UiHelper.styleButton(cetak);
        UiHelper.styleButton(csv);

        cetak.addActionListener(evt -> {
            try {
                halaman.print();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(preview, e.getMessage());
            }
        });
        csv.addActionListener(evt -> unduhCsv(preview, table, judul));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        actions.add(csv);
        actions.add(cetak);

        preview.setContentPane(new JPanel(new BorderLayout()));
        preview.getContentPane().add(new JScrollPane(halaman), BorderLayout.CENTER);
        preview.getContentPane().add(actions, BorderLayout.SOUTH);
        preview.setSize(950, 720);
        preview.setLocationRelativeTo(frame);
        preview.setVisible(true);
    }

    private static String htmlLaporan(JFrame frame, JTable table, String judul) {
        URL logo = frame.getClass().getResource("/images/logo (1).png");
        String logoTag = logo == null ? "" : "<img src='" + logo + "' style='width:82px;height:82px;object-fit:contain;'>";
        String tanggal = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());

        StringBuilder html = new StringBuilder();
        html.append("<html><head><style>");
        html.append("body{font-family:'Times New Roman',serif;color:#111;margin:24px;}");
        html.append(".kop{width:100%;border-bottom:3px solid #111;padding-bottom:12px;margin-bottom:18px;}");
        html.append(".logo{width:90px;text-align:center;}");
        html.append(".school{font-size:22px;font-weight:bold;text-align:center;}");
        html.append(".addr{font-size:13px;font-weight:normal;margin-top:4px;}");
        html.append("h2{text-align:center;margin:16px 0 6px 0;font-size:20px;}");
        html.append(".meta{text-align:right;font-size:12px;margin-bottom:12px;}");
        html.append("table{border-collapse:collapse;width:100%;font-size:11px;}");
        html.append("th,td{border:1px solid #333;padding:5px 7px;vertical-align:top;}");
        html.append("th{background:#e6e6e6;text-align:center;}");
        html.append("</style></head><body>");
        html.append("<table class='kop'><tr><td class='logo'>").append(logoTag).append("</td>");
        html.append("<td class='school'>SMK AL HIDAYAH<br>");
        html.append("<div class='addr'>Laporan Sistem Informasi Sekolah</div></td></tr></table>");
        html.append("<h2>").append(escape(judul)).append("</h2>");
        html.append("<div class='meta'>Tanggal cetak: ").append(tanggal).append("</div>");
        html.append("<table><thead><tr>");
        for (int col = 0; col < table.getColumnCount(); col++) {
            html.append("<th>").append(escape(table.getColumnName(col))).append("</th>");
        }
        html.append("</tr></thead><tbody>");
        for (int row = 0; row < table.getRowCount(); row++) {
            html.append("<tr>");
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object value = table.getValueAt(row, col);
                html.append("<td>").append(escape(value == null ? "" : value.toString())).append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</tbody></table></body></html>");
        return html.toString();
    }

    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static String csv(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
