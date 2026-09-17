package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

enum TableType {
    FURNIZORI,
    COMPONENTE,
    PROIECTE,
    LIVRARI
}

public class OrderGUI extends JFrame {
    private OrderJDBC db;
    private JTable table;
    private DefaultTableModel model;

    public OrderGUI(OrderJDBC db) {
        this.db = db;

        setTitle("Proiect BD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        //BANNER gol (spatiul ala gol)
        JLabel topImage = new JLabel();
        topImage.setPreferredSize(new Dimension(1200, 150));
        topImage.setHorizontalAlignment(SwingConstants.CENTER);

        // BANNER UL CU TAM.BOR
        ImageIcon OgIcon = new ImageIcon("C:\\Users\\Alexia\\Downloads\\TAMBOR_SRL.jpg");
        int width = 1200;
        int height = 150;

        Image scaledImage = OgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel scaledImageLabel = new JLabel(scaledIcon);
        topImage.setHorizontalAlignment(SwingConstants.CENTER);
        topImage.setVerticalAlignment(SwingConstants.CENTER);

        add(scaledImageLabel, BorderLayout.NORTH);


        //MENIU STANGA BUTOANE
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(15, 1, 10, 10));
        leftPanel.setBackground(new Color(68, 40, 40));

        JButton btnFurnizori = new JButton("Furnizori");
        btnFurnizori.setBackground(new Color(134, 30, 30));
        btnFurnizori.setForeground(Color.WHITE);

        JButton btnComponente = new JButton("Componente");
        btnComponente.setBackground(new Color(134, 30, 30));
        btnComponente.setForeground(Color.WHITE);

        JButton btnProiecte  = new JButton("Proiecte");
        btnProiecte.setBackground(new Color(134, 30, 30));
        btnProiecte.setForeground(Color.WHITE);

        JButton btnLivrari   = new JButton("Livrari");
        btnLivrari.setBackground(new Color(134, 30, 30));
        btnLivrari.setForeground(Color.WHITE);

        JButton btnAdd = new JButton("Adauga");
        btnAdd.setBackground(new Color(155, 130, 130));
        btnAdd.setForeground(Color.WHITE);

        JButton btnDelete = new JButton("Sterge");
        btnDelete.setBackground(new Color(155, 130, 130));
        btnDelete.setForeground(Color.WHITE);

        JButton btnCantiati = new JButton("Livrarile intr-un anumit interval de cantitate");
        btnCantiati.setBackground(new Color(81, 19, 19));
        btnCantiati.setForeground(Color.WHITE);

        JButton btnFurnizoriSA = new JButton("Furnizori cu S.A.");
        btnFurnizoriSA.setBackground(new Color(81, 19, 19));
        btnFurnizoriSA.setForeground(Color.WHITE);

        JButton btnFurnizoriLocali = new JButton("Livrari in acelasi oras cu Proiectul");
        btnFurnizoriLocali.setBackground(new Color(81, 19, 19));
        btnFurnizoriLocali.setForeground(Color.WHITE);

        JButton btnPerechiCoduri = new JButton("Perechi de coduri de componente");
        btnPerechiCoduri.setBackground(new Color(81, 19, 19));
        btnPerechiCoduri.setForeground(Color.WHITE);

        JButton btnCantitateGherla = new JButton("Cantitatea cea mai mare livrata intr-un oras");
        btnCantitateGherla.setBackground(new Color(81, 19, 19));
        btnCantitateGherla.setForeground(Color.WHITE);

        JButton btnComponentaC001 = new JButton("Furnizori locali cu o componenta");
        btnComponentaC001.setBackground(new Color(81, 19, 19));
        btnComponentaC001.setForeground(Color.WHITE);

        JButton btnTotulOrase = new JButton("Furnizori, Componente si Proiecte in orase");
        btnTotulOrase.setBackground(new Color(81, 19, 19));
        btnTotulOrase.setForeground(Color.WHITE);

        JButton btnMinAvgMax = new JButton("Min, Avg, Max pentru o componenta");
        btnMinAvgMax.setBackground(new Color(81, 19, 19));
        btnMinAvgMax.setForeground(Color.WHITE);

        JButton btnExceptii = new JButton("Identifica Exceptii");
        btnExceptii.setBackground(new Color(81, 19, 19));
        btnExceptii.setForeground(Color.WHITE);

        leftPanel.add(btnFurnizori);
        leftPanel.add(btnComponente);
        leftPanel.add(btnProiecte);
        leftPanel.add(btnLivrari);
        leftPanel.add(btnAdd);
        leftPanel.add(btnDelete);
        leftPanel.add(btnCantiati);
        leftPanel.add(btnFurnizoriSA);
        leftPanel.add(btnFurnizoriLocali);
        leftPanel.add(btnPerechiCoduri);
        leftPanel.add(btnCantitateGherla);
        leftPanel.add(btnComponentaC001);
        leftPanel.add(btnTotulOrase);
        leftPanel.add(btnMinAvgMax);
        leftPanel.add(btnExceptii);

        add(leftPanel, BorderLayout.WEST);

        // PARTEA DIN DR
        JPanel rightPanel = new JPanel(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
     //   table.setBackground(new Color(166, 112, 78));
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(table);

        rightPanel.add(scroll, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.setGridColor(new Color(120, 120, 120));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(134, 30, 30));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setReorderingAllowed(false);
        header.setBorder(BorderFactory.createEmptyBorder());

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    c.setBackground(new Color(155, 130, 130));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0
                            ? new Color(245, 235, 230)
                            : Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        table.setFillsViewportHeight(true);

        //ACTIUNILE
        btnFurnizori.addActionListener(e -> loadTable("SELECT * FROM Furnizori"));
        btnComponente.addActionListener(e -> loadTable("SELECT * FROM Componente"));
        btnProiecte.addActionListener(e -> loadTable("SELECT * FROM Proiecte"));
        btnLivrari.addActionListener(e -> loadTable("SELECT * FROM Livrari"));
        btnAdd.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            String[] options = {"Furnizori", "Componente", "Proiecte", "Livrari"};
            int choice = JOptionPane.showOptionDialog(
                    this, "Ce doriti sa adaugati?",
                    "Add", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (choice == -1) return;

            switch (choice) {
                case 0 -> {
                    addEntry(TableType.FURNIZORI,
                            JOptionPane.showInputDialog("ID Furnizor"),
                            JOptionPane.showInputDialog("Nume"),
                            JOptionPane.showInputDialog("Stare"),
                            JOptionPane.showInputDialog("Oras"));
                    loadTable("SELECT * FROM Furnizori");
                }

                case 1 -> {
                    addEntry(TableType.COMPONENTE,
                            JOptionPane.showInputDialog("ID Componenta"),
                            JOptionPane.showInputDialog("Nume"),
                            JOptionPane.showInputDialog("Culoare"),
                            JOptionPane.showInputDialog("Masa"),
                            JOptionPane.showInputDialog("Oras"),
                            JOptionPane.showInputDialog("Pret"));
                    loadTable("SELECT * FROM Componente");
                }

                case 2 -> {
                    addEntry(TableType.PROIECTE,
                            JOptionPane.showInputDialog("ID Proiect"),
                            JOptionPane.showInputDialog("Nume Proiect"),
                            JOptionPane.showInputDialog("Oras"));
                    loadTable("SELECT * FROM Proiecte");
                }

                case 3 -> {
                    addEntry(TableType.LIVRARI,
                            JOptionPane.showInputDialog("ID Furnizor"),
                            JOptionPane.showInputDialog("ID Componenta"),
                            JOptionPane.showInputDialog("ID Proiect"),
                            JOptionPane.showInputDialog("Cantitate"),
                            JOptionPane.showInputDialog("um"));
                    loadTable("SELECT * FROM Livrari");
                }
            }
        });
        btnDelete.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            String[] options = {"Furnizori", "Componente", "Proiecte", "Livrari"};
            int choice = JOptionPane.showOptionDialog(
                    this, "Ce doriti sa stergeti?",
                    "Delete",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);

            if (choice == -1) return;

            switch (choice) {
                case 0 -> {
                    deleteEntry(TableType.FURNIZORI,
                            JOptionPane.showInputDialog("ID Furnizor"));
                    loadTable("SELECT * FROM Furnizori");
                }

                case 1 -> {
                    deleteEntry(TableType.COMPONENTE,
                            JOptionPane.showInputDialog("ID Componenta"));
                    loadTable("SELECT * FROM Componente");
                }

                case 2 -> {
                    deleteEntry(TableType.PROIECTE,
                            JOptionPane.showInputDialog("ID Proiect"));
                    loadTable("SELECT * FROM Proiecte");
                }

                case 3 -> {
                    deleteEntry(TableType.LIVRARI,
                            JOptionPane.showInputDialog("ID Furnizor"),
                            JOptionPane.showInputDialog("ID Componenta"),
                            JOptionPane.showInputDialog("ID Proiect"));
                    loadTable("SELECT * FROM Livrari");
                }
            }
        });
        btnCantiati.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            String minStr = JOptionPane.showInputDialog(
                    this, "Introduceti minimul pentru cantitate:", "0");

            if (minStr == null) return; // apăsat Cancel

            String maxStr = JOptionPane.showInputDialog(
                    this, "Introduceti maximul pentru cantitate:", "1000");

            if (maxStr == null) return;

            try {
                int min = Integer.parseInt(minStr);
                int max = Integer.parseInt(maxStr);

                String query =
                        "SELECT idf, idc, idp, cantitate FROM Livrari " +
                                "WHERE cantitate BETWEEN " + min + " AND " + max + " " +
                                "ORDER BY cantitate;";

                loadTable(query);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Valorile sunt incorecte.",
                        "Eroare",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        btnFurnizoriSA.addActionListener(e -> loadTable("SELECT * FROM Furnizori " + "WHERE numef like '%S.A.%'" + "order by oras asc, numef desc;"));
        btnFurnizoriLocali.addActionListener(e-> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            List<String> orase = getOraseFurnizori();
            if (orase.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nu exista orase in baza de date");
                return;
            }

            JDialog dialog = new JDialog(this, "Alege orasul", true);
            dialog.setSize(300, 300);
            dialog.setLayout(new BorderLayout());
            dialog.setBackground(new Color(81, 19, 19));
            dialog.setForeground(Color.WHITE);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            ButtonGroup group = new ButtonGroup();
            java.util.ArrayList<JRadioButton> radioButtons = new java.util.ArrayList<>();

            for (String oras : orase) {
                JRadioButton rb = new JRadioButton(oras);
            //    rb.setForeground(Color.BLACK);
                rb.setBackground(new Color(68, 40, 40));
                rb.setForeground(Color.WHITE);
                group.add(rb);
                panel.add(rb);
                radioButtons.add(rb);
            }

            JButton okBtn = new JButton("OK");
            okBtn.setBackground(new Color(134, 30, 30));
            okBtn.setForeground(Color.WHITE);

            okBtn.addActionListener(ev -> {
                String orasAles = null;

                for (JRadioButton rb : radioButtons) {
                    if (rb.isSelected()) {
                        orasAles = rb.getText();
                        break;
                    }
                }

                if (orasAles == null) {
                    JOptionPane.showMessageDialog(dialog, "Selecteaza un oras.");
                    return;
                }
                dialog.dispose();

                String query = "SELECT f.numef, c.numec, p.oras " + "FROM livrari l " +
                                "JOIN Furnizori f ON l.idf = f.idf " + "JOIN Componente c ON l.idc = c.idc " +
                                "JOIN Proiecte p ON l.idp = p.idp " +
                                "WHERE f.oras = '" + orasAles + "' AND p.oras = '" + orasAles + "' " +
                                "ORDER BY f.numef;";

                loadTable(query);
            });

            dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
            dialog.add(okBtn, BorderLayout.SOUTH);

            dialog.setVisible(true);
        });
        btnPerechiCoduri.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            String cod1 = JOptionPane.showInputDialog(this, "Introduceti primul cod de componenta:");
            if (cod1 == null) return;
            String cod2 = JOptionPane.showInputDialog(this, "Introduceti al doilea cod de componenta:");
            if (cod2 == null) return;

            String query =
                    "SELECT l.idf, l.idp, '" + cod1 + "' AS Cod1, '" + cod2 + "' AS Cod2 " +
                            "FROM livrari l " + "WHERE l.idc IN ('" + cod1 + "', '" + cod2 + "') " +
                            "GROUP BY l.idf, l.idp " + "HAVING COUNT(DISTINCT l.idc) = 2;";

            loadTable(query);
        });
        btnCantitateGherla.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            List<String> orase = getOraseProiecte();
            if (orase.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nu exista orase in Proiecte.");
                return;
            }

            JDialog dialog = new JDialog(this, "Alege orasul", true);
            dialog.setSize(300, 300);
            dialog.setLayout(new BorderLayout());
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            ButtonGroup group = new ButtonGroup();
            java.util.ArrayList<JRadioButton> radioButtons = new java.util.ArrayList<>();

            for (String oras : orase) {
                JRadioButton rb = new JRadioButton(oras);
                rb.setBackground(new Color(68, 40, 40));
                rb.setForeground(Color.WHITE);
                group.add(rb);
                panel.add(rb);
                radioButtons.add(rb);
            }

            JButton okBtn = new JButton("OK");
            okBtn.setBackground(new Color(134, 30, 30));
            okBtn.setForeground(Color.WHITE);

            okBtn.addActionListener(ev -> {
                String orasAles = null;

                for (JRadioButton rb : radioButtons) {
                    if (rb.isSelected()) {
                        orasAles = rb.getText();
                        break;
                    }
                }

                if (orasAles == null) {
                    JOptionPane.showMessageDialog(dialog, "Selecteaza un oras.");
                    return;
                }
                dialog.dispose();

                String query =
                        "SELECT c.numec " + "FROM componente c " + "JOIN livrari l ON c.idc = l.idc " +
                                "JOIN proiecte p ON l.idp = p.idp " + "WHERE p.oras = '" + orasAles + "' " +
                                "AND l.cantitate = ( " + "SELECT MAX(l2.cantitate) " + "FROM livrari l2 " +
                                "JOIN proiecte p2 ON l2.idp = p2.idp " + "WHERE p2.oras = '" + orasAles + "'" + ");";

                loadTable(query);
            });

            dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
            dialog.add(okBtn, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });
        btnComponentaC001.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            List<String> componente = getListaComponente();
            if (componente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nu exista componente in baza de date.");
                return;
            }

            JDialog dialog = new JDialog(this, "Alege componenta", true);
            dialog.setSize(350, 350);
            dialog.setLayout(new BorderLayout());
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            ButtonGroup group = new ButtonGroup();
            java.util.ArrayList<JRadioButton> radioButtons = new java.util.ArrayList<>();

            for (String c : componente) {
                JRadioButton rb = new JRadioButton(c);
                rb.setBackground(new Color(68, 40, 40));
                rb.setForeground(Color.WHITE);
                group.add(rb);
                panel.add(rb);
                radioButtons.add(rb);
            }

            JButton okBtn = new JButton("OK");
            okBtn.setBackground(new Color(134, 30, 30));
            okBtn.setForeground(Color.WHITE);

            okBtn.addActionListener(ev -> {
                String componentaAleasa = null;

                for (JRadioButton rb : radioButtons) {
                    if (rb.isSelected()) {
                        componentaAleasa = rb.getText();
                        break;
                    }
                }

                if (componentaAleasa == null) {
                    JOptionPane.showMessageDialog(dialog, "Selecteaza o componenta.");
                    return;
                }

                dialog.dispose();

                String idc = componentaAleasa.split(" - ")[0].trim();

                String query =
                        "SELECT numef FROM Furnizori " +
                                "WHERE oras IN ( " +
                                "SELECT oras FROM Componente WHERE idc = '" + idc + "' " +
                                "AND idc IN ( " +
                                "SELECT idc FROM livrari WHERE idp IN (SELECT idp FROM Proiecte)" +
                                ")" +
                                ")";

                loadTable(query);
            });

            dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
            dialog.add(okBtn, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });
        btnTotulOrase.addActionListener(e -> loadTable("SELECT oras, " + "COUNT(distinct idp) AS numar_proiecte, " +
                "COUNT(distinct idc) AS numar_componente, " + "COUNT(distinct idf) AS numar_furnizori " + "FROM " +
                "(SELECT oras, idp, null AS idc, null AS idf FROM Proiecte " + "UNION ALL " + "SELECT oras, null AS idp, idc, null AS idf FROM Componente " +
                "UNION ALL " + "SELECT oras, null AS idp, null AS idc, idf FROM Furnizori) combined " + "GROUP BY oras; "));
        btnMinAvgMax.addActionListener(e -> {
            UIManager.put("OptionPane.background", new Color(68, 40, 40));
            UIManager.put("Panel.background", new Color(68, 40, 40));
            UIManager.put("OptionPane.messageForeground", Color.WHITE);
            UIManager.put("Button.background", new Color(134, 30, 30));
            UIManager.put("Button.foreground", Color.WHITE);

            List<String> componente = getListaComponente();
            if (componente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nu exista componente in baza de date.");
                return;
            }

            JDialog dialog = new JDialog(this, "Alege componenta", true);
            dialog.setSize(350, 350);
            dialog.setLayout(new BorderLayout());
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            ButtonGroup group = new ButtonGroup();
            java.util.ArrayList<JRadioButton> radioButtons = new java.util.ArrayList<>();

            for (String comp : componente) {
                JRadioButton rb = new JRadioButton(comp);
                rb.setBackground(new Color(68, 40, 40));
                rb.setForeground(Color.WHITE);
                group.add(rb);
                panel.add(rb);
                radioButtons.add(rb);
            }

            JButton okBtn = new JButton("OK");
            okBtn.setBackground(new Color(134, 30, 30));
            okBtn.setForeground(Color.WHITE);

            okBtn.addActionListener(ev -> {
                String componentaAleasa = null;

                for (JRadioButton rb : radioButtons) {
                    if (rb.isSelected()) {
                        componentaAleasa = rb.getText();
                        break;
                    }
                }

                if (componentaAleasa == null) {
                    JOptionPane.showMessageDialog(dialog, "Selecteaza o componenta.");
                    return;
                }

                dialog.dispose();

                String idc = componentaAleasa.split(" - ")[0].trim();

                String query =
                        "SELECT um, MIN(cantitate) AS \"Cantitate_Minima\", " +
                                "AVG(cantitate) AS \"Cantitate_Medie\", " +
                                "MAX(cantitate) AS \"Cantitate_Maxima\" " +
                                "FROM Livrari WHERE idc = '" + idc + "' GROUP BY um;";

                loadTable(query);
            });

            dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
            dialog.add(okBtn, BorderLayout.SOUTH);
            dialog.setVisible(true);
        });
        btnExceptii.addActionListener(e -> {
            db.callIdentificaExceptii();
            loadTable("SELECT * FROM Exceptii");
        });

        setVisible(true);
    }

    //FUNCTIILE
    private void loadTable(String query) {
        List<List<String>> data = db.doQueryReturn(query);

        if (data == null || data.isEmpty())
            return;

        // Prima linie = header
        List<String> header = data.get(0);
        model.setColumnIdentifiers(header.toArray());

        model.setRowCount(0);

        for (int i = 1; i < data.size(); i++) {
            model.addRow(data.get(i).toArray());
        }
    }
    private void addEntry(TableType type, String... values) {
        String sql = "";

        switch (type) {
            case FURNIZORI:
                sql = "INSERT INTO Furnizori (idf, numef, stare, oras) VALUES ('"
                        + values[0] + "', '" + values[1] + "', '" + values[2] + "', '" + values[3] + "')";
                break;

            case COMPONENTE:
                sql = "INSERT INTO Componente (idc, numec, culoare, masa, oras) VALUES ('"
                        + values[0] + "', '" + values[1] + values[2] + "', '" + values[3] + "', '" + values[4] + "')";
                break;

            case PROIECTE:
                sql = "INSERT INTO Proiecte (idp, numep, oras) VALUES ('"
                        + values[0] + "', '" + values[1] + "', '" + values[2] + "')";
                break;

            case LIVRARI:
                sql = "INSERT INTO Livrari (idf, idc, idp, cantitate, um) VALUES ('"
                        + values[0] + "', '" + values[1] + "', '" + values[2] + "', '" + values[3] +  "', '" + values[4] + "')";
                break;
        }

        db.doUpdate(sql);
    }
    private void deleteEntry(TableType type, String... values) {
        String sql = "";

        switch (type) {
            case FURNIZORI:
                sql = "DELETE FROM Furnizori WHERE idf = '" + values[0] + "'";
                break;

            case COMPONENTE:
                sql = "DELETE FROM Componente WHERE idc = '" + values[0] + "'";
                break;

            case PROIECTE:
                sql = "DELETE FROM Proiecte WHERE idp = '" + values[0] + "'";
                break;

            case LIVRARI:
                sql = "DELETE FROM Livrari WHERE idf = '" + values[0] +
                        "' AND idc = '" + values[1] +
                        "' AND idp = '" + values[2] + "'";
                break;
        }

        db.doUpdate(sql);
    }
    private List<String> getOraseFurnizori() {
        String query = "SELECT DISTINCT oras FROM Furnizori ORDER BY oras;";
        List<List<String>> data = db.doQueryReturn(query);

        java.util.ArrayList<String> orase = new java.util.ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            orase.add(data.get(i).get(0));
        }
        return orase;
    }
    private List<String> getOraseProiecte() {
        String query = "SELECT DISTINCT oras FROM Proiecte ORDER BY oras;";
        List<List<String>> data = db.doQueryReturn(query);

        java.util.ArrayList<String> orase = new java.util.ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            orase.add(data.get(i).get(0));
        }
        return orase;
    }
    private List<String> getListaComponente() {
        String query = "SELECT idc, numec FROM Componente ORDER BY idc;";
        List<List<String>> data = db.doQueryReturn(query);

        java.util.ArrayList<String> comp = new java.util.ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            comp.add(data.get(i).get(0) + " - " + data.get(i).get(1));  // ex: "C001 - Senzor"
        }
        return comp;
    }

    public static void main(String[] args) {
        OrderJDBC db = new OrderJDBC();
        db.init();

        SwingUtilities.invokeLater(() -> new OrderGUI(db));
    }
}

