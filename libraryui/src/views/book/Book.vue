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
      @click="$router.push('/addBook')"
      ><i class="el-icon-plus" style="margin-right: 5px"></i>新增</el-button
    >
    <!-- 表格 -->
    <div>
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="编号" width="80" align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="图书名称"
          width="110"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="bookId"
          label="图书编号"
          width="130"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="description"
          label="描述"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="author" label="作者" width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="publishDate"
          label="出版日期"
          width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="press" label="出版社" width="130" align="center">
        </el-table-column>
        <el-table-column
          prop="category"
          label="类别"
          width="100"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="amount" label="数量" width="100" align="center">
        </el-table-column>
        <!--  <el-table-column prop="createtime" label="创建时间" width="200">
        </el-table-column>
        <el-table-column prop="updatetime" label="更新时间" width="200">
        </el-table-column> -->
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <el-button
              type="primary"
              plain
              style="width: 75px; margin-right: 10px"
              @click="$router.push('/editBook?id=' + scope.row.id)"
              >编辑</el-button
            >
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
import { getBookInfoAPI, DeleteBookByIdAPI } from "@/api";
import Cookies from "js-cookie";
export default {
  name: "Book",
  data() {
    return {
      admin: Cookies.get("admin") ? JSON.parse(Cookies.get("admin")) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 5,
        name: "",
        bookId: "",
      },
      pageSizes: [1, 3, 5, 10, 20],
    };
  },
  created() {
    this.load();
    console.log(JSON.parse(Cookies.get("admin")));
  },
  methods: {
    async load() {
      const res = await getBookInfoAPI(this.params);
      console.log(res);
      if (res.code === "200") {
        this.total = res.data.total;
        this.tableData = res.data.list;
      }
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
        name: "",
        bookId: "",
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
      const res = await DeleteBookByIdAPI(id);
      if (res.code === "200") {
        this.$message.success("删除成功！");
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