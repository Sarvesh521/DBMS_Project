import java.sql.*;
import java.util.ArrayList;
public class imt2022521_sports {
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/sports?useSSL=false";
   static final String USER = "root";
   static final String PASSWORD = "root";

   public static void InsertPlayer(Statement stmt, String player_id, String player_name, String player_age) {
      String addPlayer = "INSERT INTO players (player_id, player_name, player_age) VALUES ('" + player_id + "', '" + player_name
            + "', '" + player_age + "')";
      System.out.println(addPlayer);
      try {
         stmt.executeUpdate(addPlayer);
         // Insert into sports_per_player table
         String addPlayer_Sport = "INSERT INTO sports_per_player (player_id, count) VALUES ('" + player_id + "', 0)";
         stmt.executeUpdate(addPlayer_Sport);
         System.out.println(addPlayer_Sport);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }

   public static void InsertSport(Statement stmt, String sport_id, String sport_name,int participation_points) {
      String addSport = "INSERT INTO sports (sport_id, sport_name, participation_points) VALUES ('" + sport_id + "', '" + sport_name
            + "', " + participation_points + ")";
      System.out.println(addSport);
      try {
         stmt.executeUpdate(addSport);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }

   public static void InsertCoach(Statement stmt, String coach_id, String coach_name, String sport_id) {
      String addCoach = "INSERT INTO coaches (coach_id, coach_name, sport_id) VALUES ('" + coach_id + "', '" + coach_name
            + "', '" + sport_id + "')";
      System.out.println(addCoach);
      try {
         stmt.executeUpdate(addCoach);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }

   public static void InsertPlayer_Sport(Statement stmt, String player_id, String sport_id) {
      String addPlayer_Sport = "INSERT INTO player_sports (player_id, sport_id) VALUES ('" + player_id + "', '" + sport_id + "')";
      System.out.println(addPlayer_Sport);
      try {
         stmt.executeUpdate(addPlayer_Sport);
         // Run through sports_per_player and update the count of sports they are participating in
         String query = "SELECT COUNT(*) FROM player_sports WHERE player_id = '" + player_id + "'";
         System.out.println(query);
         ResultSet rs = stmt.executeQuery(query);
         int count = 0;
         while (rs.next()) {
            count = rs.getInt(1);
         }
         String updatePlayer = "UPDATE sports_per_player SET count = " + count + " WHERE player_id = '" + player_id + "'";
         stmt.executeUpdate(updatePlayer);
         System.out.println(updatePlayer);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void UpdatePlayer(Statement stmt, String player_id, String player_name, String player_age) {
      String updatePlayer = "UPDATE players SET player_name = '" + player_name + "', player_age = '" + player_age + "' WHERE player_id = '" + player_id + "'";
      System.out.println(updatePlayer);
      try {
         stmt.executeUpdate(updatePlayer);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void DeletePlayer(Statement stmt, String player_id) {
      String deletePlayer = "DELETE FROM players WHERE player_id = '" + player_id + "'";
      System.out.println(deletePlayer);
      try {
         stmt.executeUpdate(deletePlayer);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void UpdateSport(Statement stmt, String sport_id, String sport_name, int participation_points) {
      String updateSport = "UPDATE sports SET sport_name = '" + sport_name + "', participation_points = " + participation_points + " WHERE sport_id = '" + sport_id + "'";
      System.out.println(updateSport);
      try {
         stmt.executeUpdate(updateSport);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void DeleteSport(Statement stmt, String sport_id) {
      String deleteSport = "DELETE FROM sports WHERE sport_id = '" + sport_id + "'";
      System.out.println(deleteSport);
      try {
         // update sports_per_player table for all players who are participating in this sport
         String query = "SELECT player_id FROM player_sports WHERE sport_id = '" + sport_id + "'";
         System.out.println(query);
         ResultSet rs = stmt.executeQuery(query);

         // create an ArrayList of Player IDs
         ArrayList<String> player_ids = new ArrayList<String>();
         while (rs.next()) {
            player_ids.add(rs.getString(1));
         }
         stmt.executeUpdate(deleteSport);
         for (String player_id : player_ids) {
            System.out.println(player_id);
            String query1 = "SELECT COUNT(*) FROM player_sports WHERE player_id = '" + player_id + "'";
            System.out.println(query1);
            ResultSet rs1 = stmt.executeQuery(query1);
            int count = 0;
            while (rs1.next()) {
               count = rs1.getInt(1);
            }
            String updatePlayer = "UPDATE sports_per_player SET count = " + count + " WHERE player_id = '" + player_id + "'";
            stmt.executeUpdate(updatePlayer);
            System.out.println(updatePlayer);
         }

      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void UpdateCoach(Statement stmt, String coach_id, String coach_name, String sport_id) {
      String updateCoach = "UPDATE coaches SET coach_name = '" + coach_name + "', sport_id = '" + sport_id + "' WHERE coach_id = '" + coach_id + "'";
      System.out.println(updateCoach);
      try {
         stmt.executeUpdate(updateCoach);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void DeleteCoach(Statement stmt, String coach_id) {
      String deleteCoach = "DELETE FROM coaches WHERE coach_id = '" + coach_id + "'";
      System.out.println(deleteCoach);
      try {
         stmt.executeUpdate(deleteCoach);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   // public static void UpdatePlayer_Sport(Statement stmt, String player_id, String sport_id) {
   //    String updatePlayer_Sport = "UPDATE player_sports SET sport_id = '" + sport_id + "' WHERE player_id = '" + player_id + "'";
   //    System.out.println(updatePlayer_Sport);
   //    try {
   //       stmt.executeUpdate(updatePlayer_Sport);
   //    } catch (SQLException se) {
   //       se.printStackTrace();
   //    }
   // }
   public static void DeletePlayer_Sport(Statement stmt, String player_id,String sport_id) {
      String deletePlayer_Sport = "DELETE FROM player_sports WHERE player_id = '" + player_id + "' AND sport_id = '" + sport_id + "'";
      System.out.println(deletePlayer_Sport);
      try {
         stmt.executeUpdate(deletePlayer_Sport);
         String query = "SELECT COUNT(*) FROM player_sports WHERE player_id = '" + player_id + "'";
         System.out.println(query);
         ResultSet rs = stmt.executeQuery(query);
         int count = 0;
         while (rs.next()) {
            count = rs.getInt(1);
         }
         String updatePlayer = "UPDATE sports_per_player SET count = " + count + " WHERE player_id = '" + player_id + "'";
         stmt.executeUpdate(updatePlayer);
         System.out.println(updatePlayer);
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void Number_Of_Sports_Participated_By_A_Player(Statement stmt, String player_id) {
      String query = "SELECT COUNT(*) FROM player_sports WHERE player_id = '" + player_id + "'";
      System.out.println(query);
      try {
         ResultSet rs = stmt.executeQuery(query);
         while (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Number of sports participated by player with id " + player_id + " is " + count);
         }
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void Number_Of_Players_Participating_In_A_Sport(Statement stmt, String sport_id) {
      String query = "SELECT COUNT(*) FROM player_sports WHERE sport_id = '" + sport_id + "'";
      System.out.println(query);
      try {
         ResultSet rs = stmt.executeQuery(query);
         while (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Number of players participating in sport with id " + sport_id + " is " + count);
         }
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void Number_Of_Coaches_For_A_Sport(Statement stmt, String sport_id) {
      String query = "SELECT COUNT(*) FROM coaches WHERE sport_id = '" + sport_id + "'";
      System.out.println(query);
      try {
         ResultSet rs = stmt.executeQuery(query);
         while (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Number of coaches for sport with id " + sport_id + " is " + count);
         }
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   public static void Number_Of_Participation_Points_For_A_Player(Statement stmt, String player_id) {
      String query = "SELECT SUM(s.participation_points) FROM player_sports ps, sports s WHERE ps.player_id = '" + player_id + "' AND ps.sport_id = s.sport_id";
      System.out.println(query);
      try {
         ResultSet rs = stmt.executeQuery(query);
         while (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Number of participation points for player with id " + player_id + " is " + count);
         }
      } catch (SQLException se) {
         se.printStackTrace();
      }
   }
   // create a function using inner join to get the sport name and coach name
   public static void Get_Sport_And_Coach(Statement stmt, String player_id) {
      String query = "SELECT s.sport_name, c.coach_name " +
                     "FROM player_sports ps " +
                     "INNER JOIN sports s ON ps.sport_id = s.sport_id " +
                     "INNER JOIN coaches c ON c.sport_id = s.sport_id " +
                     "WHERE ps.player_id = '" + player_id + "'";
      System.out.println(query);
      try {
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
              String sport_name = rs.getString(1);
              String coach_name = rs.getString(2);
              System.out.println("Player with id " + player_id + " is participating in sport " + sport_name + " with coach " + coach_name);
          }
      } catch (SQLException se) {
          se.printStackTrace();
      }
  }

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      try {
         Class.forName(JDBC_DRIVER);
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         System.out.println("Creating statement...");
         conn.setAutoCommit(false);
         stmt = conn.createStatement();
         // Take input from user and call the respective function
         int choice = 0;
         while(choice !=19){
            System.out.println("Enter the operation you want to perform: ");
            System.out.println("1. Insert a player");
            System.out.println("2. Insert a sport");
            System.out.println("3. Insert a coach");
            System.out.println("4. Insert a player-sport relationship");
            System.out.println("5. Update a player");
            System.out.println("6. Delete a player");
            System.out.println("7. Update a sport");
            System.out.println("8. Delete a sport");
            System.out.println("9. Update a coach");
            System.out.println("10. Delete a coach");
            System.out.println("11. Delete a player-sport relationship");
            System.out.println("12. Number of sports participated by a player");
            System.out.println("13. Number of players participating in a sport");
            System.out.println("14. Number of coaches for a sport");
            System.out.println("15. Number of participation points for a player");
            System.out.println("16. Get sport and coach for a player");
            System.out.println("17. Commit");
            System.out.println("18. Rollback");
            System.out.println("19. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(System.console().readLine());
            switch(choice){
               case 1:
                  System.out.println("Enter player_id: ");
                  String player_id = System.console().readLine();
                  System.out.println("Enter player_name: ");
                  String player_name = System.console().readLine();
                  System.out.println("Enter player_age: ");
                  String player_age = System.console().readLine();
                  InsertPlayer(stmt, player_id, player_name, player_age);
                  break;
               case 2:
                  System.out.println("Enter sport_id: ");
                  String sport_id = System.console().readLine();
                  System.out.println("Enter sport_name: ");
                  String sport_name = System.console().readLine();
                  System.out.println("Enter participation_points: ");
                  int participation_points = Integer.parseInt(System.console().readLine());
                  InsertSport(stmt, sport_id, sport_name, participation_points);
                  break;
               case 3:
                  System.out.println("Enter coach_id: ");
                  String coach_id = System.console().readLine();
                  System.out.println("Enter coach_name: ");
                  String coach_name = System.console().readLine();
                  System.out.println("Enter sport_id: ");
                  String sport_id1 = System.console().readLine();
                  InsertCoach(stmt, coach_id, coach_name, sport_id1);
                  break;
               case 4:
                  System.out.println("Enter player_id: ");
                  String player_id1 = System.console().readLine();
                  System.out.println("Enter sport_id: ");
                  String sport_id2 = System.console().readLine();
                  InsertPlayer_Sport(stmt, player_id1, sport_id2);
                  break;
               case 5:
                  System.out.println("Enter player_id: ");
                  String player_id2 = System.console().readLine();
                  System.out.println("Enter player_name: ");
                  String player_name1 = System.console().readLine();
                  System.out.println("Enter player_age: ");
                  String player_age1 = System.console().readLine();
                  UpdatePlayer(stmt, player_id2, player_name1, player_age1);
                  break;
               case 6:
                  System.out.println("Enter player_id: ");
                  String player_id3 = System.console().readLine();
                  DeletePlayer(stmt, player_id3);
                  break;
               case 7:
                  System.out.println("Enter sport_id: ");
                  String sport_id3 = System.console().readLine();
                  System.out.println("Enter sport_name: ");
                  String sport_name1 = System.console().readLine();
                  System.out.println("Enter participation_points: ");
                  int participation_points1 = Integer.parseInt(System.console().readLine());
                  UpdateSport(stmt, sport_id3, sport_name1, participation_points1);
                  break;
               case 8:
                  System.out.println("Enter sport_id: ");
                  String sport_id4 = System.console().readLine();
                  DeleteSport(stmt, sport_id4);
                  break;
               case 9:
                  System.out.println("Enter coach_id: ");
                  String coach_id1 = System.console().readLine();
                  System.out.println("Enter coach_name: ");
                  String coach_name1 = System.console().readLine();
                  System.out.println("Enter sport_id: ");
                  String sport_id5 = System.console().readLine();
                  UpdateCoach(stmt, coach_id1, coach_name1, sport_id5);
                  break;
               case 10:
                  System.out.println("Enter coach_id: ");
                  String coach_id2 = System.console().readLine();
                  DeleteCoach(stmt, coach_id2);
                  break;
               case 11:
                  System.out.println("Enter player_id: ");
                  String player_id5 = System.console().readLine();
                  System.out.println("Enter sport_id: ");
                  String sport_id7 = System.console().readLine();
                  DeletePlayer_Sport(stmt, player_id5, sport_id7);
                  break;
               case 12:
                  System.out.println("Enter player_id: ");
                  String player_id6 = System.console().readLine();
                  Number_Of_Sports_Participated_By_A_Player(stmt, player_id6);
                  break;
               case 13:
                  System.out.println("Enter sport_id: ");
                  String sport_id8 = System.console().readLine();
                  Number_Of_Players_Participating_In_A_Sport(stmt, sport_id8);
                  break;
               case 14:
                  System.out.println("Enter sport_id: ");
                  String sport_id9 = System.console().readLine();
                  Number_Of_Coaches_For_A_Sport(stmt, sport_id9);
                  break;
               case 15:
                  System.out.println("Enter player_id: ");
                  String player_id7 = System.console().readLine();
                  Number_Of_Participation_Points_For_A_Player(stmt, player_id7);
                  break;
               case 16:
                  System.out.println("Enter player_id: ");
                  String player_id8 = System.console().readLine();
                  Get_Sport_And_Coach(stmt, player_id8);
                  break;
               case 17:
                  try {
                     conn.commit();
                     System.out.println("Transaction committed");
                  } catch (SQLException se) {
                     se.printStackTrace();
                  }
                  break;
               case 18:
                  try {
                     conn.rollback();
                     System.out.println("Transaction rolled back");
                  } catch (SQLException se) {
                     se.printStackTrace();
                  }
                  break;
               case 19:
                  break;
               default:
                  System.out.println("Invalid choice");
            }
         }
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
      System.out.println("End of Code");
   }
}