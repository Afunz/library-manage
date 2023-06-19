<template>
  <div>
    <!-- 搜索 -->
    <div style="margin-bottom: 15px">
      <span style="margin-left: 10px">图书名称:</span>
      <el-input
        placeholder="请输入图书名称"
        style="width: 240px; margin-left: 10px"
        v-model="params.name"
      ></el-input>
      <span style="margin-left: 10px">图书编号:</span>
      <el-input
        placeholder="请输入图书编号"
        style="width: 240px; margin-left: 10px"
        v-model="params.bookId"
      ></el-input>
      <span style="margin-left: 10px">用户名称:</span>
      <el-input
        placeholder="请输入用户名称"
        style="width: 240px; margin-left: 10px"
        v-model="params.username"
      ></el-input>
      <el-button style="margin-left: 15px" type="primary" @click="load"
        ><i class="el-icon-search"></i> 搜索</el-button
      >
      <el-button style="margin-left: 10px" @click="reset"
        ><i class="el-icon-refresh"></i> 重置</el-button
      >
    </div>
    <el-button
      type="primary"
      style="margin-bottom: 8px"
      @click="$router.push('/addBorrow')"
      ><i class="el-icon-plus" style="margin-right: 5px"></i>新增</el-button
    >
    <!-- 表格 -->
    <div>
      <el-table :data="tableData" style="width: 100%" max-width="100" border>
        <el-table-column prop="id" label="编号" width="70" align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="图书名称"
          width="120"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="bookId"
          label="图书编号"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="uid" label="用户编号" width="150" align="center">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="100"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="phone"
          label="联系方式"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="status"
          label="借出状态"
          width="90"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="days" label="借出天数" width="90" align="center">
        </el-table-column>
        <el-table-column
          prop="createtime"
          label="借出日期"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="returnTime"
          label="归还日期"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="warn"
          label="过期提醒"
          width="120"
          align="center"
        >
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.warn === '正常'">{{
              scope.row.warn
            }}</el-tag>
            <el-tag type="primary" v-if="scope.row.warn === '即将到期'">{{
              scope.row.warn
            }}</el-tag>
            <el-tag type="warning" v-if="scope.row.warn === '已到期'">{{
              scope.row.warn
            }}</el-tag>
            <el-tag type="danger" v-if="scope.row.warn === '已过期'">{{
              scope.row.warn
            }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="还书管理" width="110" align="center">
          <template v-slot="scope">
            <el-popconfirm
              title="确定还书吗？"
              @confirm="returnBooks(scope.row)"
              v-if="scope.row.status === '已借出'"
            >
              <el-button
                slot="reference"
                type="primary"
                style="width: 75px"
                plain
                >还书</el-button
              >
            </el-popconfirm>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <!-- <el-button
              type="primary"
              plain
              style="width: 75px; margin-right: 10px"
              @click="$router.push('/editBorrow?id=' + scope.row.id)"
              >编辑</el-button
            > -->
            <el-popconfirm
              title="确定删除此行数据吗？"
              @confirm="delInfo(scope.row.id)"
            >
              <el-button
                slot="reference"
                type="danger"
                style="width: 75px"
                plain
                >删除</el-button
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <div style="margin-top: 15px">
      <el-pagination
        background
        :current-page="params.pageNum"
        :page-size="params.pageSize"
        :page-sizes.sync="pageSizes"
        @size-change="handleSizeChange"
        layout="total, sizes, prev, pager, next, jumper"
        @current-change="handleCurrentChange"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import { getBorrowInfoAPI, DeleteBorrowByIdAPI, addReturnInfoAPI } from "@/api";
import Cookies from "js-cookie";
export default {
  name: "Borrow",
  data() {
    return {
      admin: Cookies.get("admin") ? JSON.parse(Cookies.get("admin")) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: "",
        bookId: "",
        username: "",
      },
      pageSizes: [1, 3, 5, 10, 20],
    };
  },
  created() {
    this.load();
  },
  methods: {
    async load() {
      const res = await getBorrowInfoAPI(this.params);
      console.log(res);
      if (res.code === "200") {
        this.total = res.data.total;
        this.tableData = res.data.list;
      }
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 10,
        name: "",
        bookId: "",
        username: "",
      };
      this.load();
    },
    handleSizeChange(newPageSize) {
      this.params.pageSize = newPageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum;
      this.load();
    },
    async delInfo(id) {
      const res = await DeleteBorrowByIdAPI(id);
      if (res.code === "200") {
        this.$message.success("删除成功！");
        this.load();
      } else {
        this.$message.error(res.msg);
      }
    },
    async returnBooks(row) {
      const res = await addReturnInfoAPI(row);
      if (res.code === "200") {
        this.$message.success("还书成功");
        this.load();
      } else {
        this.$message.error(res.msg);
      }
    },
  },
};
</script>

<style>
</style>