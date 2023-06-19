<template>
  <div>
    <!-- 搜索 -->
    <div style="margin-bottom: 15px">
      <span style="margin-left: 10px">姓名:</span>
      <el-input
        placeholder="请输入名称"
        style="width: 240px; margin-left: 10px"
        v-model="params.name"
      ></el-input>
      <span style="margin-left: 15px">联系方式:</span>
      <el-input
        style="width: 240px; margin-left: 10px"
        placeholder="请输入联系方式"
        v-model="params.phone"
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
      @click="$router.push('/adduser')"
      ><i class="el-icon-plus" style="margin-right: 5px"></i>新增</el-button
    >
    <!-- 表格 -->
    <div>
      <el-table :data="tableData" style="width: 100%" border>
        <el-table-column prop="id" label="编号" width="120" align="center">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="130"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="uid"
          label="人员编号"
          width="210"
          align="center"
        ></el-table-column>
        <el-table-column prop="age" label="年龄" width="130" align="center">
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="130" align="center">
        </el-table-column>
        <el-table-column prop="phone" label="电话" width="150" align="center">
        </el-table-column>
        <el-table-column prop="address" label="地址" width="260" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <el-button
              type="primary"
              plain
              style="width: 75px; margin-right: 10px"
              @click="$router.push('/edituser?id=' + scope.row.id)"
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
import { getUserInfoAPI, DeleteUserByIdAPI } from "@/api";
export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 5,
        name: "",
        phone: "",
      },
      pageSizes: [1, 3, 5, 10, 20],
    };
  },
  created() {
    this.load();
  },
  methods: {
    async load() {
      const res = await getUserInfoAPI(this.params);
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
        phone: "",
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
      const res = await DeleteUserByIdAPI(id);
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