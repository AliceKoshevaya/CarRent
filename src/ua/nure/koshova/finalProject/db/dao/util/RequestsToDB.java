package ua.nure.koshova.finalProject.db.dao.util;

/**
 * Stores database queries
 */

public final class RequestsToDB {

    /**
     * Request to table cars
     */

    public static final String INSERT_CAR = "insert into cars values (DEFAULT ,?,?,?,?,?,?)";
    public static final String DELETE_CAR = "DELETE FROM cars WHERE id=?";
    public static final String UPDATE_CAR = "UPDATE cars SET name=?, price=?, " +
            "gov_number=?, status=?, id_brand=?, id_class=? WHERE id=?";
    public static final String SELECT_ALL_CAR = "select c.id, c.name as name, c.price*cl.coeff as price, c.gov_number, c.status, b.id, b.name, cl.id, cl.name " +
            "from cars AS c " +
            "LEFT JOIN brand AS b ON c.id_brand = b.id " +
            "LEFT JOIN classes AS cl ON c.id_class = cl.id " +
            "ORDER BY c.id";
    public static final String SELECT_CAR = "select c.id, c.name, c.price, " +
            "c.gov_number, c.status from cars AS c";
    public static final String SELECT_GET_CAR_BY_BRAND = "select c.id, c.name as name, c.price*cl.coeff as price, c.gov_number, c.status, b.name, cl.name " +
            "from cars AS c " +
            "left join brand b on c.id_brand = b.id " +
            "left join classes as cl on c.id_class = cl.id " +
            "WHERE id_brand =?";
    public static final String SELECT_GET_CAR_BY_CLASS = "select c.id, c.name as name, c.price*cl.coeff as total, c.gov_number, c.status, b.name, cl.name " +
            "from cars AS c " +
            "left join classes cl on c.id_class = cl.id " +
            "left join brand as b on c.id_brand = b.id " +
            "where id_class=?";
    public static final String SELECT_BY_CLASS_AND_BRAND = "select c.id, c.name, c.price*cl.coeff, c.gov_number, c.status,b.name, cl.name  " +
            "FROM cars as c " +
            "left join brand as b on c.id_brand = b.id " +
            "left join classes as cl on c.id_class = cl.id " +
            "WHERE id_brand = ? AND id_class = ?";
    public static final String SELECT_CAR_BY_ID = "select c.id, c.name as name, c.price*cl.coeff as total, c.gov_number, c.status, b.name, cl.name " +
            "from cars AS c " +
            "left join classes cl on c.id_class = cl.id " +
            "left join brand as b on c.id_brand = b.id " +
            "where c.id =?";

    /**
     * Request to table roles
     */

    public static final String SELECT_ALL_ROLES = "select * from roles";
    public static final String SELECT_ROLE_BY_NAME = "select id, role_name from roles where role_name =";

    /**
     * Request to table users
     */

    public static final String INSERT_USER = "insert into users values (DEFAULT ,?,?,?,?,?,?,?,?);";
    public static final String SELECT_ALL_USERS = "select u.id, u.user_login, u.password, u.first_name, u.last_name, u.third_name, u.pass_seria,u.data_pass, u.id_role, r.role_name, u.block " +
            "from users AS u " +
            "left join roles AS r ON u.id_role = r.id";
    public static final String SELECT_USER_BY_ROLE = "select u.id, u.user_login,u.password, u.first_name, u.last_name, u.third_name, u.pass_seria, u.data_pass " +
            "FROM users as u " +
            "WHERE id_role = 3";
    public static final String SELECT_GET_USER = "select u.id, u.first_name, u.last_name, u.third_name, u.pass_seria, u.data_pass " +
            "FROM users as u " +
            "WHERE u.user_login=? and u.password=?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    public static final String UPDATE_USER = "UPDATE users SET third_name=?, pass_seria=?, data_pass =? WHERE id=?";
    public static final String SET_A_MANAGER = "update users set id_role = 2 where id = ?";
    public static final String SELECT_USER_BY_LOGIN = "select  u.id,u.user_login, u.first_name, u.last_name, u.third_name, u.pass_seria, u.data_pass, id_role " +
            "FROM users as u WHERE u.user_login=?";
    public static final String SELECT_USER_ROLE = "select r.id, r.role_name " +
            "FROM users AS u " +
            "LEFT JOIN roles AS r " +
            "ON u.id_role = r.id where u.id =?";
    public static final String BLOCK_USER = "update users SET block = true where id =?";
    public static final String UNBLOCK_USER = "update users SET block = false where id =?";
    public static final String CHECKBLOCK = "select id, block from users where id = ?";

    /**
     * Request to table orders
     */

    public static final String INSERT_ORDER = "insert into orders values (DEFAULT ,?,?,?,?,?,?)";
    public static final String SELECT_ALL_ORDERS = "select o.id, o.driver, o.status, o.start_rent, o.end_rent,  u.id, u.user_login " +
            "from orders AS o " +
            "left join users AS u ON o.id_user = u.id " +
            "left join cars AS c ON o.id_car = c.id order by o.id";
    public static final String CONFIRM_ORDER = "UPDATE orders SET status = 'IN_PROGRESS' WHERE id =?";
    public static final String CLOSE_ORDER ="UPDATE orders SET status = 'CLOSED' WHERE id =?";
    public static final String CRASH_ORDER ="UPDATE orders SET status = 'CRASH' WHERE id =?";
    public static final String UPDATE_REASON = "update orders SET reason = ? WHERE id = ?";

    /**
     * Request to table bill
     */

    public static final String INSERT_BILL = "insert into bill values (DEFAULT ,?,?,?,?,?)";
    public static final String SELECT_BILL_BY_ID = "select id, status, type, summa, data, id_order from bill where id =?";
    public static final String UPDATE_BILL = "UPDATE bill SET status = TRUE WHERE id = ?";
    public static final String SELECT_BILL_BY_ORDER_ID = "select id, status,type,summa,data  from bill where id_order =?";

    /**
     * Request to table brand
     */

    public static final String SELECT_BRAND_BY_NAME = "select id from brand where name=?";
    public static final String SELECT_BRAND_BY_ID = "select name from brand where id=?";
    public static final String SELECT_ALL_BRAND = "select * from brand";

    /**
     * Request to table classes
     */

    public static final String SELECT_CLASS_BY_NAME = "select id from classes where name=?";
    public static final String SELECT_CLASS_BY_ID = "select name from classes where id=?";
    public static final String SELECT_ALL_CLASSES = "select * from classes";

}
