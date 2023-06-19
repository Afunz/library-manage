<template>
  <div style="padding: 20px">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="图书名:" prop="name">
        <el-input v-model="form.name" placeholder="请输入图书名"></el-input>
      </el-form-item>
      <el-form-item label="图书编号:" prop="bookId">
        <el-input v-model="form.bookId" placeholder="请输入图书编号"></el-input>
      </el-form-item>
      <el-form-item label="描述:" prop="description">
        <el-input
          type="textarea"
          v-model="form.description"
          placeholder="请输入图书描述"
        ></el-input>
      </el-form-item>
      <el-form-item label="作者:" prop="author">
        <el-input v-model="form.author" placeholder="请输入作者"></el-input>
      </el-form-item>
      <el-form-item label="出版社:" prop="press">
        <el-input v-model="form.press" placeholder="请输入出版社"></el-input>
      </el-form-item>
      <el-form-item label="出版日期:" prop="publishDate">
        <el-date-picker
          v-model="form.publishDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出版日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="类别:" prop="category">
        <el-select
          v-model="form.category"
          placeholder="请选择分类"
          style="width: 20%"
          filterable
          clearable
        >
          <!-- 循环渲染分类的可选项 -->
          <el-option
            :label="item.name"
            :value="item.name"
            v-for="item in cateList"
            :key="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="数量:" prop="amount">
        <el-input v-model="form.amount" placeholder="请输入数量"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" size="medium" @click="addBookInfo"
        >提交</el-button
      >
      <el-button type="warning" size="medium" @click="$router.push('/book')"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
import { addBookInfoAPI, getCategoryListAPI } from "@/api";
import Cookies from "js-cookie";
export default {
  name: "AddBook",
  data() {
    return {
      admin: Cookies.get("admin") ? JSON.parse(Cookies.get("admin")) : {},
      cateList: [],
      form: {
        name: "",
        bookId: "",
        description: "",
        author: "",
        press: "",
        publishDate: "",
        category: "",
        amount: "",
      },
      rules: {
        name: [{ required: true, message: "请输入图书名称", trigger: "blur" }],
        bookId: [
          { required: true, message: "请输入图书编号", trigger: "blur" },
        ],
      },
    };
  },
  async created() {
    const res = await getCategoryListAPI();
    console.log(res);
    this.cateList = res.data;
  },
  methods: {
    async addBookInfo() {
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          const res = await addBookInfoAPI(this.form);
          if (res.code === "200") {
            this.$message.success("新增成功！");
            this.$refs["ruleForm"].resetFields();
            this.$router.push("/book");
          } else {
            this.$message.error(res.msg);
          }
        }
      });
    },
  },
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>