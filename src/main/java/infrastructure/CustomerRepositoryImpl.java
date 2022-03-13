package infrastructure;

import application.dto.CustomerDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository{
    public List<CustomerDTO> getCustomersByName(String name){

        List<CustomerDTO> customerDTOs = new LinkedList<>();

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://10.0.40.163:5432/postgres";
            String user = "postgres";
            String password = "dbadmin!2020";

            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();

            String fetchquery = "select * from customer where \"givenName\" like '" +name+ "'";

            ResultSet rs = st.executeQuery(fetchquery);


            while (rs.next()) {

                customerDTOs.add(new CustomerDTO(rs.getString("givenName"), rs.getString("familyName")));
            }


            st.close();
            con.close();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerDTOs;
    }
}