import Vue from 'vue'
import VueRouter from 'vue-router'
import Cookies from "js-cookie";
import { Message } from 'element-ui'


Vue.use(VueRouter)

const routes = [
  /* 登录路由 */
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/Login')
  },
  /* 主界面路由 */
  {
    path: '/',
    name: 'Layout',
    component: () => import('@/views/Layout'),
    redirect: '/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/home/HomeView.vue'),
      },
      {
        path: 'count',
        name: 'Count',
        component: () => import('@/views/Count.vue'),
      },
      /* 用户模块路由 */
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/user/User')
      },
      {
        path: 'adduser',
        name: 'AddUser',
        component: () => import('@/views/user/AddUser')
      },
      {
        path: 'edituser',
        name: 'EditUser',
        component: () => import('@/views/user/EditUser')
      },

      /*  管理员模块路由 */
      {
        path: 'admin',
        name: 'Admin',
        component: () => import('@/views/admin/Admin')
      },
      {
        path: 'addadmin',
        name: 'AddAdmin',
        component: () => import('@/views/admin/AddAdmin')
      },
      {
        path: 'editadmin',
        name: 'EditAdmin',
        component: () => import('@/views/admin/EditAdmin')
      },
      /*  类目模块路由 */
      {
        path: 'category',
        name: 'Category',
        component: () => import('@/views/category/Category')
      },
      {
        path: 'addCategory',
        name: 'AddCategory',
        component: () => import('@/views/category/AddCategory')
      },
      {
        path: 'editCategory',
        name: 'EditCategory',
        component: () => import('@/views/category/EditCategory')
      },
      /*  图书模块路由 */
      {
        path: 'book',
        name: 'Book',
        component: () => import('@/views/book/Book')
      },
      {
        path: 'addBook',
        name: 'AddBook',
        component: () => import('@/views/book/AddBook')
      },
      {
        path: 'editBook',
        name: 'EditBook',
        component: () => import('@/views/book/EditBook')
      },
      /*  借书模块路由 */
      {
        path: 'borrow',
        name: 'Borrow',
        component: () => import('@/views/borrow/Borrow')
      },
      {
        path: 'addBorrow',
        name: 'AddBorrow',
        component: () => import('@/views/borrow/AddBorrow')
      },
      {
        path: 'editBorrow',
        name: 'EditBorrow',
        component: () => import('@/views/borrow/EditBorrow')
      },
      /*  还书模块路由 */
      {
        path: 'returnBook',
        name: 'ReturnBook',
        component: () => import('@/views/returnbook/ReturnBook')
      },
    ]
  },
  {
    path: "*",
    component: () => import('@/views/404')
  }
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach((to, from, next) => {
  if (to.path === '/login') next()
  const admin = Cookies.get("admin")
  if (!admin && to.path !== '/login') return next("/login")  // 强制退回到登录页面
  // 访问 /home 的时候，并且cookie里面存在数据，这个时候我就直接放行
  next()
})

export default router
