package ua.nure.koshova.finalProject.db.dao.util;

public final class Requests {

    //////////////////////
    // Requests to cars //
    //////////////////////

    public static final String INSERT_CAR = "insert into cars values (DEFAULT ,?,?,?,?,?,?)";
    public static final String DELETE_CAR = "DELETE FROM cars WHERE id=?";
    public static final String UPDATE_CAR = "UPDATE cars SET name=?, price=?, " +
            "gov_number=?, status=?, id_brand=?, id_class=? WHERE id=?";
    public static final String SELECT_ALL_CAR = "select c.id, c.name, c.price, c.gov_number," +
            " c.status, b.id, b.name, cl.id, cl.name " +
            " from cars  AS c LEFT JOIN brand AS b ON c.id_brand = b.id " +
            " LEFT JOIN classes AS cl ON c.id_class = cl.id \n" +
            "ORDER BY c.id;";
    public static final String SELECT_GET_CAR_BY_BRAND = "select c.id, c.name, c.price, c.gov_number, " +
            "c.status, cl.name from cars AS c " +
            "left join classes  cl on c.id_class = cl.id WHERE id_brand = ?;";
    public static final String SELECT_GET_CAR_BY_CLASS = "select c.id, c.name, c.price, " +
            "c.gov_number, c.status from cars AS c" +
            " WHERE id_class=?;";
    public static final String SELECT_SORT_BY_PRICE = "select c.id, c.name, c.price, c.gov_number, " +
            "c.status, b.id, b.name,cl.id, " +
            "cl.name,cl.coeff, cl.coeff*c.price as total from cars  " +
            "AS c LEFT JOIN brand AS b ON c.id_brand = b.id  " +
            "LEFT JOIN classes AS cl ON c.id_class = cl.id order by total ";
    public static final String SELECT_SORT_BY_NAME = "select c.id, c.name, c.price, " +
            "c.gov_number, c.status, b.id, b.name, cl.id, cl.name " +
            " from cars  AS c LEFT JOIN brand AS b ON c.id_brand = b.id  " +
            "LEFT JOIN classes AS cl ON c.id_class = cl.id \n" +
            "ORDER BY c.name ";

    ///////////////////////
    // Requests to roles //
    ///////////////////////

    public static final String SELECT_ALL_ROLES = "select * from roles";
    public static final String SELECT_ROLE_BY_NAME = "select id, role_name from roles where role_name =";

    ///////////////////////
    // Requests to users //
    ///////////////////////

    public static final String INSERT_USER = "insert into users values (DEFAULT ,?,?,?,?,?,?,?,?);";
    public static final String SELECT_ALL_USERS = "select u.id, u.user_login, u.password, u.first_name, u.last_name, " +
            "u.third_name, u.pass_seria,\n" +
            "u.data_pass, u.id_role, r.role_name from users AS u left join roles AS r ON u.id_role = r.id;";
    public static final String SELECT_USER_BY_ROLE = "select u.id, u.user_login,u.password, u.first_name, " +
            "u.last_name, u.third_name, u.pass_seria, u.data_pass FROM\n" +
            "users as u WHERE id_role = 3;";
    public static final String SELECT_GET_USER =  "select u.first_name, u.last_name, u.third_name, u.pass_seria, u.data_pass FROM\n" +
            "users as u WHERE u.user_login=? and u.password=?; ";

    ////////////////////////
    // Requests to orders //
    ////////////////////////

    public static final String INSERT_ORDER = "insert into users values (DEFAULT ,?,?,?,?,?,?)";
    public static final String SELECT_ALL_ORDERS = "select o.id, o.driver, o.start_rent, o.end_rent,  u.id, u.user_login, b.type, c.id, c.name \n" +
            "from orders AS o left join users AS u ON o.id_user = u.id left join bill  AS b ON o.id_bill= b.id\n" +
            "left join cars AS c ON o.id_car = c.id order by o.id;";

}
