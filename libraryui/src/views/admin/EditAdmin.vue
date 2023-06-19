<template>
  <div style="padding: 20px">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="用户名:" prop="username">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱:" prop="email">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="联系方式:" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" size="medium" @click="updateAdminInfo"
        >修改</el-button
      >
      <el-button type="warning" size="medium" @click="$router.push('/')"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
import { getAdminByIdAPI, updateAdminInfoAPI } from "@/api";
export default {
  name: "EditAdmin",
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        callback(new Error("请输入合法的手机号"));
      }
      callback();
    };
    return {
      form: {
        id: "",
        username: "",
        email: "",
        phone: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 10, message: "长度在3-10个字符", trigger: "blur" },
        ],
        phone: [{ validator: checkPhone, trigger: "blur" }],
      },
    };
  },
  async created() {
    const id = this.$route.query.id;
    const res = await getAdminByIdAPI(id);
    this.form = res.data;
  },
  methods: {
    async updateAdminInfo() {
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          const res = await updateAdminInfoAPI(this.form);
          if (res.code === "200") {
            this.$message.success("更新成功！");
            this.$router.push("/admin");
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
</style>