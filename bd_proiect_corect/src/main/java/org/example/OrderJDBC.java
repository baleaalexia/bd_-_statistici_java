package org.example;

import java.sql.*;
import java.io.*;
import java.util.List;

public class OrderJDBC {
    private String url = "jdbc:mysql://localhost:3306/bd?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String uid = "root";
    private String pw = "pasw123";
    private BufferedReader reader;
    private Connection con;

//    public static void main(String[] args) {
//        OrderJDBC app = new OrderJDBC();
//        app.init();
//        app.run();
//    }

//    public static void main(String[] args) {
//        OrderJDBC app = new OrderJDBC();
//        app.init();
//
//        // Pornim GUI-ul
//        javax.swing.SwingUtilities.invokeLater(() -> new OrderGUI(app));
//    }

    public static void main(String[] args) {
        new OrderLoginGUI();
    }

    void init() {
        // Înregistrează driverul MySQL și realizează conexiunea
        try {
            // Încarcă driverul MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e);
        }

        // Inițializează conexiunea
        con = null;
        try {
            con = DriverManager.getConnection(url, uid, pw);
            System.out.println("Conexiune reușită la baza de date!");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
            System.exit(1);
        }

        // Setează reader-ul pentru input
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void run() {
        String choice, sqlSt;
        choice = "1";
        while (!choice.equalsIgnoreCase("X")) {
            printMenu();
            choice = getLine();

            switch (choice) {
                case "1": // List all customers
                    sqlSt = "SELECT * FROM Livrari;";
                    doQuery(sqlSt);
                    break;
                case "2": // List all orders for a customer
//                    System.out.print("ELECT * FROM Furnizori;");
//                    String cid = getLine();
//                    sqlSt = "SELECT * FROM bd WHERE idf = '" + cid + "';";
//                    doQuery(sqlSt);
//                    break;
                      sqlSt = "SELECT * FROM Livrari;";
                      doQuery(sqlSt);
                      break;
                case "3": // List all lineitems for an order
//                    System.out.print("Enter order id: ");
//                    String onum = getLine();
//                    sqlSt = "SELECT * FROM OrderedProduct WHERE orderId = '" + onum + "';";
//                    doQuery(sqlSt);
//                    break;
                     sqlSt = "SELECT * FROM Componente;";
                     doQuery(sqlSt);
                     break;
                case "4": // List all customers
                    sqlSt = "SELECT * FROM Proiecte;";
                    doQuery(sqlSt);
                    break;
                case "5":  // idf,idc,idp ordered between 100 and 1000
                    cantitati100to1000();
                    break;
                case "6":  // * from furnizori where numef like S.A
                    numeS_Aordered();
                    break;
                case "7":  //04a
                    compLivratataOrasLaFel();
                    break;
                case "8":  //04b
                    perechiCoduriComp();
                    break;
                case "9":  //05a
                    numecGherla();
                    break;
                case "10":  //04
                    numefC001();
                    break;
                case "11":  //04
                    nrCompFurnProiecteAnyOras();
                    break;
                case "12":  //04
                    MinAvgMaxC001();
                    break;
                case "13":  //04
                    callIdentificaExceptii();
                    break;
                case "A1": // Add customer
                    addLivrari();
                    break;
                case "A2": // Add customer
                    addFurnizori();
                    break;
                case "A3": // Add customer
                    addComponente();
                    break;
                case "A4": // Add customer
                    addProiecte();
                    break;
                case "D1": // Delete customer
                    deleteLivrari();
                    break;
                case "D2": // Delete customer
                    deleteFurnizori();
                    break;
                case "D3": // Delete customer
                    deleteComponenta();
                    break;
                case "D4": // Delete customer
                    deleteProiecte();
                    break;
                case "X":
                    System.out.println("Exiting!");
                    closeConnection();
                    return;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }
    }

    private void addLivrari() {
        try {
            System.out.print("Enter Furnizor id: ");
            String idf = getLine();
            System.out.print("Enter Componenta id: ");
            String idc = getLine();
            System.out.print("Enter Proiect id: ");
            String idp = getLine();
            System.out.print("Enter cantitate: ");
            String cnt = getLine();
            System.out.print("Enter Unitatea de masura: ");
            String um = getLine();

            String sqlSt = "INSERT INTO Livrari (idf, idc, idp, cantitate, um) VALUES ('" + idf + "', '" + idc +  "', '"
                    + idc +  "', '" + idp +  "', '"  + cnt +  "', '" + um +"');";
            doUpdate(sqlSt);
        } catch (Exception e) {
            System.out.println("Failed to add Livrare: " + e);
        }
    }

    private void addFurnizori() {
        try {
            System.out.print("Enter Furnizor id: ");
            String idf = getLine();
            System.out.print("Enter name: ");
            String numef = getLine();
            numef = convertSQLString(numef);
            System.out.print("Enter stare: ");
            String stare = getLine();
            System.out.print("Enter oras: ");
            String oras = getLine();
            oras = convertSQLString(oras);

            String sqlSt = "INSERT INTO Furnizori (idf, numef, stare, oras) VALUES ('" + idf + "', '" + numef +  "', '"
                    + stare +  "', '" + oras +"');";
            doUpdate(sqlSt);
        } catch (Exception e) {
            System.out.println("Failed to add Furnizor: " + e);
        }
    }

    private void addComponente() {
        try {
            System.out.print("Enter Componenta id: ");
            String idc = getLine();
            System.out.print("Enter name: ");
            String numec = getLine();
            numec = convertSQLString(numec);
            System.out.print("Enter culoare: ");
            String culoare = getLine();
            culoare = convertSQLString(culoare);
            System.out.print("Enter masa: ");
            String masa = getLine();
            System.out.print("Enter oras: ");
            String oras = getLine();
            oras = convertSQLString(oras);

            String sqlSt = "INSERT INTO Componente (idc, numec, culoare, masa, oras) VALUES ('" + idc + "', '" + numec +  "', '"
                    + culoare +  "', '" + culoare +  "', '" + masa +  "', '" + oras +"');";
            doUpdate(sqlSt);
        } catch (Exception e) {
            System.out.println("Failed to add Componenta: " + e);
        }
    }

    private void addProiecte() {
        try {
            System.out.print("Enter Proiect id: ");
            String idp = getLine();
            System.out.print("Enter name: ");
            String numep = getLine();
            numep = convertSQLString(numep);
            System.out.print("Enter oras: ");
            String oras = getLine();
            oras = convertSQLString(oras);

            String sqlSt = "INSERT INTO Proiecte (idp, numep, oras) VALUES ('" + idp + "', '" + numep +  "', '"
                    + oras +"');";
            doUpdate(sqlSt);
        } catch (Exception e) {
            System.out.println("Failed to add Proiect: " + e);
        }
    }

    private void deleteLivrari() {
        System.out.print("Enter Furnizor id to delete: ");
        String idf = getLine();
        System.out.print("Enter Componenta id to delete: ");
        String idc = getLine();
        System.out.print("Enter Proiect id to delete: ");
        String idp = getLine();
        String sqlSt = "DELETE FROM Livrari WHERE idf = '" + idf + "' and idc = ' " + idc + "' and idp= ' " + idp + "' ";
        doUpdate(sqlSt);
    }

    private void deleteFurnizori() {
        System.out.print("Enter Furnizor id to delete: ");
        String idf = getLine();
        String sqlSt = "DELETE FROM furnizori WHERE idf = '" + idf + "' ";
        doUpdate(sqlSt);
    }

    private void deleteComponenta() {
        System.out.print("Enter Componenta id to delete: ");
        String idc = getLine();
        String sqlSt = "DELETE FROM componente WHERE idc = '" + idc + "' ";
        doUpdate(sqlSt);
    }

    private void deleteProiecte() {
        System.out.print("Enter Proiect id to delete: ");
        String idp = getLine();
        String sqlSt = "DELETE FROM proiecte WHERE idp = '" + idp + "' ";
        doUpdate(sqlSt);
    }

    List<List<String>> cantitati100to1000() {
        String sqlSt = "SELECT idf, idc, idp, cantitate FROM livrari " + "WHERE cantitate between 100 and 1000 " + "order by cantitate;";
        doQuery(sqlSt);
        return null;
    }

    List<List<String>> numeS_Aordered() {
        String sqlSt = "SELECT * FROM furnizori " + "WHERE numef like '%S.A.%' " + "order by oras asc, numef desc;";
        doQuery(sqlSt);
        return null;
    }

    void compLivratataOrasLaFel() {
        String sqlSt = "select f.numef, c.numec, p.oras from livrari l " +
                       "join Furnizori f on l.idf = f.idf " +
                       "join componente c on l.idc = c.idc " +
                       "join proiecte p on l.idp = p.idp " +
                       "where f.oras = p.oras";
        doQuery(sqlSt);
    }

    List<List<String>> perechiCoduriComp() {
        String sqlSt = "select l1.idc as idc1, l2.idc as idc2 " +
                        "from livrari l1 " +
                        "join livrari l2 on l1.idf = l2.idf and l1.idp = l2.idp " +
                        "where l1.idc < l2.idc ";
        doQuery(sqlSt);
        return null;
    }

    private void numecGherla() {
        String sqlSt = "select c.numec " +
                "from componente c " +
                "join livrari l on c.idc = l.idc " +
                "where l.cantitate > all ( " +
                "    select l2.cantitate " +
                "    from livrari l2 " +
                "    where l2.idp in ( " +
                "        select idp " +
                "        from proiecte " +
                "        where oras = 'Gherla' " +
                "    )" +
                ")";
        doQuery(sqlSt);
    }

    private void numefC001() {
        String sqlSt = "select numef " +
                "from Furnizori " +
                "where oras in ( " +
                "    select oras " +
                "    from Componente " +
                "    where idc = 'C001' " +
                "    and idc in( " +
                "        select idc " +
                "        from livrari " +
                "        where idp in( " +
                "            select idp " +
                "            from Proiecte " +
                "        )" +
                "    )" +
                ")";
        doQuery(sqlSt);
    }

    private void nrCompFurnProiecteAnyOras() {
        String sqlSt = "select oras, " +
                "    count(distinct idp) as numar_proiecte, " +
                "    count(distinct idc) as numar_componente, " +
                "    count(distinct idf) as numar_furnizori " +
                "from " +
                "    (select oras, idp, null as idc, null as idf from Proiecte " +
                "     union all " +
                "     select oras, null as idp, idc, null as idf from Componente " +
                "     union all " +
                "     select oras, null as idp, null as idc, idf from Furnizori) combined " +
                "group by oras; ";
        doQuery(sqlSt);
    }

    private void MinAvgMaxC001() {
        String sqlSt = "select um, min(cantitate) as \"Cantitate_Minima\", avg(cantitate) as \"Cantitate_Medie\", max(cantitate) as \"Cantitate_Maxima\" " +
                "from Livrari " +
                "where idc = 'C001' " +
                "group by um; ";
        doQuery(sqlSt);
    }

    public void callIdentificaExceptii() {
        String sql = "{CALL IdentificaExceptii()}";

        try (CallableStatement stmt = con.prepareCall(sql)) {
            stmt.execute();
            System.out.println("Procedura IdentificaExceptii a fost executata!");
        } catch (SQLException e) {
            System.out.println("Eroare la apel procedura: " + e.getMessage());
        }
    }


    void doUpdate(String updateStr) {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(updateStr);
            System.out.println("Operation successful!");
        } catch (SQLException e) {
            System.out.println("Operation failed: " + e);
        }
    }

    List<List<String>> doQuery(String queryStr) {
        try (Statement stmt = con.createStatement();
             ResultSet rst = stmt.executeQuery(queryStr)) {
            ResultSetMetaData rsmd = rst.getMetaData();
            int colCount = rsmd.getColumnCount();

            // Print header
            for (int i = 1; i <= colCount; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows
            while (rst.next()) {
                for (int i = 1; i <= colCount; i++) {
                    System.out.print(rst.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
        return null;
    }

    public List<List<String>> doQueryReturn(String queryStr) {
        List<List<String>> table = new java.util.ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rst = stmt.executeQuery(queryStr)) {

            ResultSetMetaData md = rst.getMetaData();
            int colCount = md.getColumnCount();

            List<String> header = new java.util.ArrayList<>();
            for (int i = 1; i <= colCount; i++)
                header.add(md.getColumnName(i));

            table.add(header);

            while (rst.next()) {
                List<String> row = new java.util.ArrayList<>();
                for (int i = 1; i <= colCount; i++)
                    row.add(rst.getString(i));
                table.add(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return table;
    }

    private String getLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
            return null;
        }
    }

    private String convertSQLString(String st) {
        return st.replaceAll("'", "''");
    }

    private void printMenu() {
        System.out.println("\n\nSelect one of these options: ");
        System.out.println("  1 - List all Livrari");
        System.out.println("  2 - List all Furnizori");
        System.out.println("  3 - List all Componente");
        System.out.println("  4 - List all Proiecte");
        System.out.println("  5 - List all Livrari by cantitate between 100 and 1000 ordered");
        System.out.println("  6 - List all from Furnizori where numef like S.A.");
        System.out.println("  7 - List numef, numec, oras where componenta livrata > 1 si orasf = orasp");
        System.out.println("  8 - List all perechi coduri comp where both comp livrate by same furnizor");
        System.out.println("  9 - List name for comp livrata with biggest cantitate in Gherla");
        System.out.println("  10 - List all numef in the same city as C001");
        System.out.println("  11 - List all componente, proiecte, furnizori for each city");
        System.out.println("  12 - List min, avg, max cantiate for C001");
        System.out.println("  13 - Procedura");
        System.out.println("  A1 - Add a Livrare");
        System.out.println("  A2 - Add a Furnizor");
        System.out.println("  A3 - Add a Componenta");
        System.out.println("  A4 - Add a Proiect");
        System.out.println("  D1 - Delete a Livrare");
        System.out.println("  D2 - Delete a Furnizor");
        System.out.println("  D3 - Delete a Componenta");
        System.out.println("  D4 - Delete a Proiect");
        System.out.println("  X - Exit application");
        System.out.print("Your choice: ");
    }

    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException ex) {
            System.err.println("Exception during connection close: " + ex);
        }
    }
}
