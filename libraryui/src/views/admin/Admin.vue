<template>
  <div>
    <!-- 搜索 -->
    <div style="margin-bottom: 15px">
      <span style="margin-left: 10px">用户名:</span>
      <el-input
        placeholder="请输入用户名"
        style="width: 240px; margin-left: 10px"
        v-model="params.username"
      ></el-input>
      <span style="margin-left: 15px">联系方式:</span>
      <el-input
        style="width: 240px; margin-left: 10px"
        placeholder="请输入联系方式"
        v-model="params.phone"
      ></el-input>
      <span style="margin-left: 15px">邮箱:</span>
      <el-input
        style="width: 240px; margin-left: 10px"
        placeholder="请输入邮箱"
        v-model="params.email"
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
      @click="$router.push('/addadmin')"
      v-if="this.admin.id === 1"
      ><i class="el-icon-plus" style="margin-right: 5px"></i>新增</el-button
    >
    <!-- 表格 -->
    <div>
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="编号" align="center">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          align="center"
        ></el-table-column>
        <el-table-column prop="email" label="邮箱" align="center">
        </el-table-column>
        <el-table-column prop="phone" label="电话" align="center">
        </el-table-column>
        <el-table-column prop="createtime" label="创建时间" align="center">
        </el-table-column>
        <el-table-column prop="updatetime" label="更新时间" align="center">
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
import { getAdminInfoAPI } from "@/api";
import Cookies from "js-cookie";
export default {
  name: "Admin",
  data() {
    return {
      admin: Cookies.get("admin") ? JSON.parse(Cookies.get("admin")) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 5,
        username: "",
        phone: "",
        email: "",
      },
      pageSizes: [1, 3, 5, 10, 20],
    };
  },
  created() {
    this.load();
  },
  methods: {
    async load() {
      const res = await getAdminInfoAPI(this.params);
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
        username: "",
        phone: "",
        email: "",
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
  },
};
</script>

<style>
</style>