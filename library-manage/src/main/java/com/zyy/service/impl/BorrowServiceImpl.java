package com.zyy.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyy.dto.PageDTO;
import com.zyy.exception.ServiceException;
import com.zyy.mapper.BookMapper;
import com.zyy.mapper.BorrowMapper;
import com.zyy.mapper.po.BorrowReturCountPO;
import com.zyy.pojo.Book;
import com.zyy.pojo.Borrow;
import com.zyy.pojo.ReturnBook;
import com.zyy.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService{
    
    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> pager(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Borrow> borrows = borrowMapper.pager(pageDTO);
        for (Borrow borrow : borrows) {
            LocalDate returnTime = borrow.getReturnTime();
            LocalDate localDate = LocalDate.now();
            if (localDate.plusDays(1).isEqual(returnTime)) {  // 当前日期比归还的日期小一天
                borrow.setWarn("即将到期");
            } else if (localDate.isEqual(returnTime)) {
                borrow.setWarn("已到期");
            } else if (localDate.isAfter(returnTime)) {
                borrow.setWarn("已过期");
            } else {
                borrow.setWarn("正常");
            }
        }
        PageInfo<Borrow> borrowPageInfo = new PageInfo<>(borrows);
        return borrowPageInfo;
    }

    @Override
    @Transactional
    public void insert(Borrow borrow) {
        //  校验是否有图书
        Book book = bookMapper.getBookId(borrow.getBookId());
        if (Objects.isNull(book)) {
            throw new ServiceException("所借图书不存在");
        }
        //  校验图书数量
        if (book.getAmount() < 1) {
            throw new ServiceException("图书数量不足");
        }
        //  更新图书的数量
        book.setAmount(book.getAmount() - 1);
        bookMapper.update(book);
        borrow.setReturnTime(LocalDate.now().plus(borrow.getDays(), ChronoUnit.DAYS));  // 当前的日期加天数得到归还的日期
        borrowMapper.insert(borrow);
    }





    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow borrow) {
        borrow.setUpdatetime(LocalDate.now());
        borrowMapper.update(borrow);
    }

    @Override
    public void delete(Integer id) {
        borrowMapper.delete(id);
    }



    @Override
    public Map<String, Object> getCountByTimeRange(String timeRange) {
        Map<String, Object> map = new HashMap<>();
        Date today = new Date();
        List<DateTime> dateRange;
        switch (timeRange) {
            case "week":
                // offsetDay 计算时间的一个工具方法
                // rangeToList 返回从开始时间到结束时间的一个时间范围
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -6), today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -29), today, DateField.DAY_OF_MONTH);
                break;
            case "month2":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -59), today, DateField.DAY_OF_MONTH);
                break;
            case "month3":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -89), today, DateField.DAY_OF_MONTH);
                break;
            default:
                dateRange = new ArrayList<>();
        }
        //  datetimeToDateStr() 就是一个处理的方法， 把 DateTime类型的List转换成一个 String的List
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date", dateStrRange);  // x轴的日期数据生产完毕

        //  timeRange = week  month
        // getCountByTimeRange 不会统计数据库没有的日期，比如 数据库 11.4 这一天数据没有，他不会返回 date=2022-11-04,count=0 这个数据
        List<BorrowReturCountPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange, 1);   // 1 borrow  2 return
        List<BorrowReturCountPO> returnCount = borrowMapper.getCountByTimeRange(timeRange, 2);
        map.put("borrow", countList(borrowCount, dateStrRange));
        map.put("retur", countList(returnCount, dateStrRange));
        return map;
    }

    // 把 DateTime类型的List转换成一个 String的List
    private List<String> datetimeToDateStr(List<DateTime> dateTimeList) {
        List<String> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(dateTimeList)) {
            return list;
        }
        for (DateTime dateTime : dateTimeList) {
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

    // 对数据库未统计的时间进行处理
    private List<Integer> countList(List<BorrowReturCountPO> countPOList, List<String> dateRange) {
        List<Integer> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(countPOList)) {
            return list;
        }
        for (String date : dateRange) {
            // .map(BorrowReturCountPO::getCount) 取出 对象里的 count值
            // orElse(0) 对没匹配的数据返回0
            // "2022-11-04" 没有的话 就返回0
            Integer count = countPOList.stream().filter(countPO -> date.equals(countPO.getDate()))
                    .map(BorrowReturCountPO::getCount).findFirst().orElse(0);
            list.add(count);
        }
        // 最后返回的list的元素个数会跟 dateRange 的元素个数完全一样
        // dateRange: [
        //            "2022-10-30",
        //            "2022-10-31",
        //            "2022-11-01",
        //            "2022-11-02",
        //            "2022-11-03",
        //            "2022-11-04",
        //            "2022-11-05"
        //        ],
        // borrow: [
        //            0,
        //            0,
        //            2,
        //            1,
        //            0,
        //            1,
        //            3
        //        ]
        return list;
    }

}
