package example.sql.resultset.group;

import example.database.ConnectionDB;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ReportGruop {

    public ResultSet rs = null;
    private String[] colGroup = {};
    private PrintWriter out = null;
    private int rowPrint = 0;

    public void setColumnGroup(String[] col) {
        this.colGroup = col;
    }

    public String[] getColumnGroup() {
        return this.colGroup;
    }

    private String[] intitialToNull(String[] val) {
        for (int i = 0; i < val.length; i++) {
            val[i] = null;
        }
        return val;
    }

    private String nullToBlank(String val) {
        return (val == null) ? "" : val;
    }

    private void setupWriteFile(String fileName) throws IOException {
        fileName = "./output/" + fileName;
        FileWriter fw = new FileWriter(fileName, false);
        out = new PrintWriter(fw);
        //out.printf("%s" + "%n", "1+1=");
    }

    private void printRow(String val) {
        this.rowPrint++;
        if (this.rowPrint == 1) {
            header();
        } 
        out.printf("%s" + "%n", val);
    }
    
    public void header() {
        
    }

    public void beforeGroup(ResultSet rs, String fieldGroup) throws SQLException {
        System.out.println("Before Group " + fieldGroup);
    }

    public void afterGroup(ResultSet rs, String fieldGroup) throws SQLException {
        System.out.println("After Group  " + fieldGroup);
    }

    public void everyRow(ResultSet rs) throws SQLException {
        System.out.println("[" + rs.getString(1) + "][" + rs.getString(2) + "][" + rs.getString(3) + "][" + rs.getString(4) + "][" + rs.getString(5) + "]");
    }

    public void reportRun(ResultSet rs) {
        ResultSetMetaData rsmd;
        int nRecord = 0;
        int[] indexBefore;
        String[] oldVal;
        String[] columnBefore;

        boolean isRowGroup = false;
        boolean isFirstColGroup = false;
        Vector<Integer> nRowGroup = new Vector();
        Vector<Integer> nColGroup = new Vector();

        try {
            rsmd = rs.getMetaData();
            columnBefore = getColumnGroup();
            indexBefore = new int[columnBefore.length + 1];
            oldVal = new String[columnBefore.length + 1];

            intitialToNull(oldVal);

            System.out.println("columnBefore.length = " + columnBefore.length);

            //เทียบ columnBefore กับ field resultset หา indexBefore ออกมา
            //จะได้รู้ตำแหน่ง field ที่จะเทียบ before Group
            for (int i = 1, k = 1; i <= rsmd.getColumnCount(); i++) {
                for (int j = 0; j < columnBefore.length; j++) {
                    if (rsmd.getColumnName(i).equals(columnBefore[j])) {
                        indexBefore[k++] = i;
                    }
                }
            }
//            System.out.println("start");
//            System.out.println("bval = [" + oldVal[1] + "][" + oldVal[2] + "][" + oldVal[3] + "]");

            while (rs.next()) {
                nRecord++;

                //เปรียบเทียบค่าไว้ group
                for (int i = 1; i < indexBefore.length; i++) {
                    //ตำแหน่ง field ที่จะเทียบ indexBefore ไปดึงค่ามา
                    String bval = nullToBlank(rs.getString(indexBefore[i]));
                    //ค่าเดิมใน oldVal ไม่เท่ากับค่าใน resultset ก็ให้เอาค่าใหม่มาเก็บ
                    //และเก็บ Row ที่เปลี่ยนค่าไว้ จะรู้ว่า Row เท่านี้ต้อง before group
                    if (isRowGroup || !bval.equals(oldVal[i])) {
                        oldVal[i] = bval;
                        //จะเข้าแค่ครั้งแรกที่เจอค่าไม่เหมือนเดิม คือ Col แรกที่ต้อง Group
                        if (!isRowGroup) {
                            isFirstColGroup = true;
                        }
                        //หลังจากเก็บ Col แรกที่ต้อง Group ต้องเก็บทุก Col ที่อยู่ลำดับถัดไปตาม flow group
                        isRowGroup = true;
                    }
                    //เก็บค่า Row และ Col แรกที่ต้อง Group
                    if (isFirstColGroup) {
                        nRowGroup.add(rs.getRow());
                        nColGroup.add(i);
                        isFirstColGroup = false;
                    }
                }
                isRowGroup = false;

//                System.out.println("Row  = " + nRow);
//                System.out.println("bval = [" + oldVal[1] + "][" + oldVal[2] + "][" + oldVal[3] + "]);
            }

//            System.out.print("nRowGroup = ");
//            for (int i : nRowGroup) {
//                System.out.print(i + " , ");
//            }
//            System.out.println("");
//            System.out.print("nColGroup = ");
//            for (int i : nColGroup) {
//                System.out.print(i + " , ");
//                //System.out.print(rsmd.getColumnName(i) + " , ");
//            }
//            System.out.println("nRecord = "+nRecord);

            rs.beforeFirst();

            System.out.println("nRowGroup.size() = " + nRowGroup.size());

            for (int i = 1, j = 0, k = 0; rs.next(); i++) {
                // i = record Current , j = index number Row Group, k = index number before row group
                //record นี้ต้อง Group หรือไม่
                if (j < nRowGroup.size() && i == nRowGroup.get(j)) {
                    //record แรกยังไม่ต้องทำ after group 
                    if (i != 1) {
                        for (int l = columnBefore.length; l >= nColGroup.get(k); l--) {
                            afterGroup(rs, rsmd.getColumnName(indexBefore[l]));
                        }
                        k = j;
                    }
                    for (int l = nColGroup.get(j); l <= columnBefore.length; l++) {
                        beforeGroup(rs, rsmd.getColumnName(indexBefore[l]));
                    }
                    j++;
                }
                everyRow(rs);
            }
            //column สุดท้ายต้องทำ after group ทุกตัว
            if (rs.isAfterLast()) {
                for (int l = columnBefore.length; l >= 1; l--) {
                    afterGroup(rs, rsmd.getColumnName(indexBefore[l]));
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error [reportRun] rs.next() : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error [reportRun] : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        PreparedStatement statement = null;
        ConnectionDB connectionDB = new ConnectionDB();

        Connection conn = connectionDB.getConnection();

        StringBuilder str = new StringBuilder();
        str.append("select * ");
        str.append("  from pwwtest ");
        str.append(" order by school,room,idcode");
        try {
            statement = conn.prepareStatement(str.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            System.out.println("Error [main] conn.prepareStatement : " + ex.getMessage());
        }

        ReportGruop rep = new ReportGruop();

        try {
            ResultSet rs2 = statement.executeQuery();

            if (rs2 != null) {
                System.out.println("HAVE DATA");
                String[] columnGroup = {"school", "room", "sex"};
                rep.setColumnGroup(columnGroup);
                rep.reportRun(rs2);
                //System.out.println(rs.getString(1));
            } else {
                System.out.println("NO DATA");
            }

        } catch (Exception ex) {
            System.out.println("Error [main] statement.executeQuery : " + ex.getMessage());
        } finally {
            connectionDB.closeConnection();
        }
    }
}
/*    
 create table pwwtest (
 school  char(10),
 room    char(10),
 idcode  char(10),
 sex     char(10),
 name    char(10)
 );

 insert into pwwtest values('STN','1/1','12301','M','kong');
 insert into pwwtest values('STN','1/1','12302','M','bank');
 insert into pwwtest values('STN','1/1','12303','F','joy');
 insert into pwwtest values('STN','1/1','12304','F','nueng');
 insert into pwwtest values('STN','1/2','12305','M','bank');
 insert into pwwtest values('STN','1/2','12306','M','bom');
 insert into pwwtest values('STN','1/2','12307','F','tu');
 insert into pwwtest values('STN','1/2','12308','F','ja');
 insert into pwwtest values('STN','1/2','12309','F','bell');
 insert into pwwtest values('STN','1/3','12310','M','boom');
 insert into pwwtest values('STN','1/3','12311','F','bee');
 insert into pwwtest values('KMITL','2/1','45601','M','tee');
 insert into pwwtest values('KMITL','2/1','45602','M','big');
 insert into pwwtest values('KMITL','2/1','45603','F','am');
 insert into pwwtest values('KMITL','2/1','45604','F','ky');
 insert into pwwtest values('KMITL','2/2','45605','M','ball');
 insert into pwwtest values('KMITL','2/2','45606','M','boss');
 insert into pwwtest values('KMITL','2/2','45607','F','peung');
 insert into pwwtest values('KMITL','2/2','45608','F','mo');
 insert into pwwtest values('KMITL','2/2','45609','F','pair');
 insert into pwwtest values('KMITL','2/3','45610','M','tum');
 insert into pwwtest values('KMITL','2/3','45611','F','eii');
 insert into pwwtest values('KMITL','2/3','45612','F','jean');

 ตัวอย่างการทำงาน Before & After Group
    
 select * from pwwtest 
 order by school,room,idcode
 
 before group of school
 after group of school

 before group of room
 after group of room

 before group of idcode
 after group of idcode
    
 จะมีการทำงานดังนี้
    
 Before Group school
 Before Group room
 Before Group sex
 [1-STN][1/1][12301][M][kong]
 [1-STN][1/1][12302][M][bank]
 After Group  sex
 After Group  room
 After Group  school

 Before Group sex
 [1-STN][1/1][12303][F][joy]
 [1-STN][1/1][12304][F][nueng]
 After Group  sex

 Before Group room
 Before Group sex
 [1-STN][1/2][12305][M][bank]
 [1-STN][1/2][12306][M][bom]
 After Group  sex
 After Group  room

 Before Group sex
 [1-STN][1/2][12307][F][tu]
 [1-STN][1/2][12308][F][ja]
 [1-STN][1/2][12309][F][bell]
 After Group  sex

 Before Group room
 Before Group sex
 [1-STN][1/3][12310][M][boom]
 After Group  sex
 After Group  room

 Before Group sex
 [1-STN][1/3][12311][F][bee]
 After Group  sex

 Before Group school
 Before Group room
 Before Group sex
 [KMITL][2/1][45601][M][tee]
 [KMITL][2/1][45602][M][big]
 After Group  sex
 After Group  room
 After Group  school

 Before Group sex
 [KMITL][2/1][45603][F][am]
 [KMITL][2/1][45604][F][ky]
 After Group  sex

 Before Group room
 Before Group sex
 [KMITL][2/2][45605][M][ball]
 [KMITL][2/2][45606][M][boss]
 After Group  sex
 After Group  room

 Before Group sex
 [KMITL][2/2][45607][F][peung]
 [KMITL][2/2][45608][F][mo]
 [KMITL][2/2][45609][F][pair]
 After Group  sex

 Before Group room
 Before Group sex
 [KMITL][2/3][45610][M][tum]
 After Group  sex
 After Group  room

 Before Group sex
 [KMITL][2/3][45611][F][eii]
 After Group  sex
 After Group  room
 After Group  school
     
 */
