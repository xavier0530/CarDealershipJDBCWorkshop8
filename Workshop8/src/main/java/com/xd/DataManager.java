package com.xd;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
public class DataManager {



    public class DealerShipDataManager {
        private BasicDataSource basicDataSource;

        public void Dealership(BasicDataSource basicDataSource) {
            this.basicDataSource = basicDataSource;
        }

        public List<Dealership> getAll() {
            List<Dealership> hotels = new ArrayList<>();

            String query = "SELECT * FROM Dealership;";

            try (
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("Address");
                    int phoneNumber = resultSet.getInt("Phone Number");

                    Dealership dealership = new Dealership(id, name, address, phoneNumber);

                    dealership.add(dealership);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return hotels;
        }

        public Dealership getById(int id){
            Dealership dealership = null;
            String query = "SELECT * FROM hotels WHERE id=?;";

            try (
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
                preparedStatement.setInt(1, id);
                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if(resultSet.next()){
                        int idFromDB = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int totalFloors = resultSet.getInt("totalFloors");
                        int totalOccupancy = resultSet.getInt("totalOccupancy");

                        dealership = new dealership(idFromDB, name, totalFloors, totalOccupancy);
                    } else {
                        System.out.println("No dealership found with that id");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return dealership;
        }

        public List<Hotel> searchByName(String nameToSearchBy){ // "fun"
            List<Hotel> hotelsFound = new ArrayList<>();

            String query = "SELECT * FROM hotels WHERE name LIKE ?;";

            // Create a connection / Make a prepared statement
            try(
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
                // Set parameters for prepared statement
                preparedStatement.setString(1, "%" + nameToSearchBy + "%");

                try(
                        // Execute prepared statement
                        ResultSet resultSet = preparedStatement.executeQuery();
                ) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int totalFloors = resultSet.getInt("totalFloors");
                        int totalOccupancy = resultSet.getInt("totalOccupancy");

                        Hotel hotel = new Hotel(id, name, totalFloors, totalOccupancy);

                        hotelsFound.add(hotel);
                    }
                }

            } catch (SQLException e){
                e.printStackTrace();
            }

            // return the result
            return hotelsFound;
        }

        public List<Hotel> filterByLargerTotalOccupancy(int totalOccupancyParam){
            List<Hotel> hotelsFound = new ArrayList<>();

            String query = "SELECT * FROM hotels WHERE totalOccupancy > ?;";

            // Create a connection / Make a prepared statement
            try(
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
                // Set parameters for prepared statement
                preparedStatement.setInt(1, totalOccupancyParam);

                try(
                        // Execute prepared statement
                        ResultSet resultSet = preparedStatement.executeQuery();
                ) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int totalFloors = resultSet.getInt("totalFloors");
                        int totalOccupancy = resultSet.getInt("totalOccupancy");

                        Hotel hotel = new Hotel(id, name, totalFloors, totalOccupancy);

                        hotelsFound.add(hotel);
                    }
                }

            } catch (SQLException e){
                e.printStackTrace();
            }

            // return the result
            return hotelsFound;
        }

        public void create(Hotel hotel){
            String query = "INSERT INTO hotels(name, totalFloors, totalOccupancy) VALUES(?, ?, ?);";

            try(
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ){
                preparedStatement.setString(1,  hotel.getName());
                preparedStatement.setInt(2, hotel.getTotalFloors());
                preparedStatement.setInt(3, hotel.getTotalOccupancy());

                int rows = preparedStatement.executeUpdate();
                System.out.printf("Rows updated %d\n", rows);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void update(int id, Hotel hotel){
            // Create a query
            String query = "UPDATE hotels SET name=?, totalFloors=?, totalOccupancy=? WHERE id=?;";

            // Make a connection / prepare statement
            try(
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ){
                // Set parameters
                preparedStatement.setString(1, hotel.getName());
                preparedStatement.setInt(2, hotel.getTotalFloors());
                preparedStatement.setInt(3, hotel.getTotalOccupancy());
                preparedStatement.setInt(4, id);

                // Execute prepared statement
                int rows = preparedStatement.executeUpdate();

                // Print confirmation
                System.out.printf("Rows updated %d\n", rows);

            }catch (SQLException e) {
                // Handle errors
                e.printStackTrace();
            }
        }

        public void delete(int id){
            // Create a query
            String query = "DELETE FROM hotels WHERE id=?;";
            // Make a connection / prepare statement
            try(
                    Connection connection = this.basicDataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ){
                // Set parameters
                preparedStatement.setInt(1, id);

                // Execute prepared statement
                int rows = preparedStatement.executeUpdate();

                // Print confirmation
                System.out.printf("Rows updated %d\n", rows);

            }catch (SQLException e) {
                // Handle errors
                e.printStackTrace();
            }
        }
    }
}
