<template>
  <div style="padding: 20px">
    <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="用户编号" prop="name">
        <el-input v-model="form.uid" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名:" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="年龄:" prop="age">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别:">
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="联系方式:" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址:" prop="address">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" size="medium" @click="updateUserInfo"
        >修改</el-button
      >
      <el-button type="warning" size="medium" @click="$router.push('/user')"
        >取消</el-button
      >
    </div>
  </div>
</template>

<script>
import { updateUserInfoAPI, getUserByIdAPI } from "@/api";
export default {
  name: "EditUser",
  data() {
    const checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("年龄不能为空"));
      }
      if (!/^[0-9]+$/.test(value)) {
        callback(new Error("请输入数字值"));
      }
      if (parseInt(value) > 120 || parseInt(value) <= 0) {
        callback(new Error("请输入合理的年龄"));
      }
      callback();
    };
    const checkPhone = (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        callback(new Error("请输入合法的手机号"));
      }
      callback();
    };
    return {
      form: {
        id: "",
        name: "",
        age: "",
        sex: "男",
        phone: "",
        address: "",
      },
      rules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        age: [{ validator: checkAge, trigger: "blur" }],
        phone: [{ validator: checkPhone, trigger: "blur" }],
      },
    };
  },
  async created() {
    const id = this.$route.query.id;
    const res = await getUserByIdAPI(id);
    this.form = res.data;
  },
  methods: {
    async updateUserInfo() {
      this.$refs["ruleForm"].validate(async (valid) => {
        if (valid) {
          const res = await updateUserInfoAPI(this.form);
          if (res.code === "200") {
            this.$message.success("更新成功！");
            this.$router.push("/user");
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