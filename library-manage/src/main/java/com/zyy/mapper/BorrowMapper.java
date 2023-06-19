package com.zyy.mapper;

import com.zyy.dto.PageDTO;
import com.zyy.mapper.po.BorrowReturCountPO;
import com.zyy.pojo.Borrow;
import com.zyy.pojo.ReturnBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> list();

    List<Borrow> pager(PageDTO pageDTO);

    void insert(Borrow borrow);

    Borrow getById(Integer id);

    void update(Borrow borrow);

    void delete(Integer id);

    void updateStatus(String status, Integer id);

    List<BorrowReturCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);  // 1 borrow  2 return
}
