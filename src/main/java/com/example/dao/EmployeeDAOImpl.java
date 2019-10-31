package com.example.dao;

import com.example.dto.Employee;
import com.example.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@PropertySource("classpath:/EmployeeDao.properties")

@Component
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {


    @Autowired
    private NamedParameterJdbcTemplate template;

    static final String EMPLOYEE_ID="employee_id";
    static final String FIRST_NAME = "first_name";
    static final String LAST_NAME = "last_name";
    static final String DEPARTMENT_ID = "department_id";
    static final String JOB_TITLE = "job_title";
    static final String GENDER = "gender";
    static final String DATE_OF_BIRTH = "date_of_birth";

    @Value("${empl.select}")
    private String getAllEmplSql;
    @Value("${empl.selectbyid}")
    private String getEmployeeByIdSql;
    @Value("${empl.delete}")
    private String deleteEmplSql;
    @Value("${empl.add}")
    private String addEmplSql;
    @Value("${empl.update}")
    private String updateEmplSql;

    @Override
    public List<Employee> getAllEmployee() {
        return template.query(getAllEmplSql, new MyMapper());
    }

    @Override
    public Employee getEmployeeById(long id) {
        SqlParameterSource parameter = new MapSqlParameterSource(EMPLOYEE_ID, id);
        return template.query(getEmployeeByIdSql, parameter, new MyExtractor());
    }

    @Override
    public int deleteEmployeeById(long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource(EMPLOYEE_ID, id);
        return template.update(deleteEmplSql, parameterSource);
    }

    @Override
    public long addEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource =  getParameters(employee);
        template.update(addEmplSql, parameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int updateEmployee(Employee employee) {
        MapSqlParameterSource parameterSource =  getParameters(employee);
        parameterSource.addValue(EMPLOYEE_ID, employee.getEmployee_id());
        return template.update(updateEmplSql, parameterSource);
    }

    private class MyMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {


            return buildEmpl(resultSet);
        }
    }

    private class MyExtractor  implements ResultSetExtractor<Employee>{

        @Override
        public Employee extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            while (resultSet.next()) {
                return buildEmpl(resultSet);
            }
            return null;
        }
    }

    private Employee buildEmpl(ResultSet resultSet) throws SQLException, DataAccessException{
        return Employee.builder().
                department_id(resultSet.getLong(EMPLOYEE_ID)).
                first_name(resultSet.getString(FIRST_NAME)).
                last_name(resultSet.getString(LAST_NAME)).
                department_id(resultSet.getLong(DEPARTMENT_ID)).
                job_title(resultSet.getString(JOB_TITLE)).
                gender(Gender.valueOf(resultSet.getString(GENDER))).
                date_of_birth(resultSet.getDate(DATE_OF_BIRTH)).
                build();
    }

    private MapSqlParameterSource getParameters(Employee employee){
        MapSqlParameterSource parameterSource =  new MapSqlParameterSource();
        parameterSource.addValue(FIRST_NAME, employee.getFirst_name());
        parameterSource.addValue(LAST_NAME, employee.getLast_name());
        parameterSource.addValue(DEPARTMENT_ID, employee.getDepartment_id());
        parameterSource.addValue(JOB_TITLE, employee.getJob_title());
        parameterSource.addValue(GENDER, employee.getGender().toString());
        parameterSource.addValue(DATE_OF_BIRTH, employee.getDate_of_birth());
        return parameterSource;
    }
}
