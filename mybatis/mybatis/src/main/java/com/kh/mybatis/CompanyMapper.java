package com.kh.mybatis;

import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CompanyMapper {
    @Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.name}, #{company.address})")
    // 새로운 데이터가 삽입될 때 키 값을 자동 생성, 생성된 키 값은 id 속성에 할당
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("company") Company company);  // 입력된 데이터의 갯수를 반환

    @Select("SELECT * FROM company")
    @Results(id="CompanyMap",value = {
            @Result(property = "name",column = "company_name"),
            @Result(property = "address",column = "company_address"),
            @Result(property = "employeeList",column = "id",
                    many = @Many(select = "com.kh.mybatis.EmployeeMapper.getByCompanyId"))
    })
    List<Company> getAll();
}