<template>
  <div style="padding: 20px">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="图书编号:" prop="bookId">
        <el-select
          v-model="form.bookId"
          filterable
          placeholder="请选择"
          @change="seekbook"
        >
          <el-option
            v-for="item in books"
            :key="item.id"
            :label="item.bookId"
            :value="item.bookId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="图书名称:" prop="name">
        <el-input
          v-model="form.name"
          placeholder="图书名称"
          disabled
        ></el-input>
      </el-form-item>
      <el-form-item label="用户编号:" prop="uid">
        <el-select
          v-model="form.uid"
          filterable
          placeholder="请选择"
          @change="seekuser"
        >
          <el-option
            v-for="item in users"
            :key="item.id"
            :label="item.uid + '(' + item.name + ')'"
            :value="item.uid"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户名称:" prop="username">
        <el-input
          v-model="form.username"
          placeholder="用户名称"
          disabled
        ></el-input>
      </el-form-item>
      <el-form-item label="联系方式:" prop="phone">
        <el-input
          v-model="form.phone"
          placeholder="联系方式"
          disabled
        ></el-input>
      </el-form-item>
      <el-form-item label="数量:" prop="amount">
        <el-input v-model="form.amount" disabled></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" size="medium" @click="updateBorrowInfo"
        >修改</el-button
      >
      <el-button type="warning" size="medium" @click="$router.push('/borrow')"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
import {
  getBorrowByIdAPI,
  updateBorrowInfoAPI,
  getBookListAPI,
  getUserListAPI,
} from "@/api";
export default {
  name: "EditBorrow",
  data() {
    return {
      users: [],
      books: [],
      form: {
        id: "",
        name: "",
        bookId: "",
        uid: "",
        userName: "",
        phone: "",
      },
      rules: {
        bookId: [
          { required: true, message: "请输入图书编码", trigger: "blur" },
        ],
        uid: [{ required: true, message: "请输入人员编号", trigger: "blur" }],
      },
    };
  },
  async created() {
    const id = this.$route.query.id;
    const res = await getBorrowByIdAPI(id);
    this.form = res.data;
    const res1 = await getBookListAPI();
    this.books = res1.data;
    const res2 = await getUserListAPI();
    this.users = res2.data;
  },
  methods: {
    async updateBorrowInfo() {
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          const res = await updateBorrowInfoAPI(this.form);
          if (res.code === "200") {
            this.$message.success("更新成功！");
            this.$router.push("/borrow");
          } else {
            this.$message.error(res.msg);
          }
        }
      });
    },
    seekbook() {
      const book = this.books.find((r) => r.bookId === this.form.bookId);
      console.log(book);
      this.form.name = book.name;
      this.form.amount = book.amount;
    },
    seekuser() {
      const user = this.users.find((r) => r.uid === this.form.uid);
      console.log(user);
      this.form.username = user.name;
      this.form.phone = user.phone;
    },
  },
};
</script>

<style>
</style>