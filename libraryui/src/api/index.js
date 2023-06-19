import request from '@/utils/request'


/**
 * 登录接口
 * @returns Promise对象
 */
export const loginAPI = ({ username, password }) => {
  return request({
    method: "POST",
    url: '/admin/login',
    data: {
      username,
      password
    }
  })
}


/**
 * 用户接口
 * @returns Promise对象
 */

/* 添加用户信息 */
export const addUserInfoAPI = ({ name, phone, age, address, sex }) => {
  return request({
    method: "POST",
    url: '/user/add',
    data: {
      name,
      phone,
      age,
      address,
      sex,
    }
  })
}

export const getUserListAPI = () => {
  return request({
    method: "GET",
    url: '/user/list',
  })
}
/* 删除用户信息 */
export const DeleteUserByIdAPI = (id) => {
  return request({
    method: "DELETE",
    url: '/user/delete/' + id,
  })
}
/* 更新用户信息 */
export const updateUserInfoAPI = ({ id, name, phone, age, address, sex }) => {
  return request({
    method: "PUT",
    url: '/user/update',
    data: {
      id,
      name,
      phone,
      age,
      address,
      sex,
    }
  })
}
/* 获取用户信息 */
export const getUserInfoAPI = ({ name, phone, pageNum, pageSize }) => {
  return request({
    method: "GET",
    url: '/user/pager',
    params: {
      pageNum,
      pageSize,
      name,
      phone
    }
  })
}
/* 根据ID查找用户信息 */
export const getUserByIdAPI = (id) => {
  return request({
    method: "GET",
    url: '/user/' + id,
  })
}



/**
 * 管理员接口
 * @returns Promise对象
 */

/* 添加管理员信息 */
export const addAdminInfoAPI = ({ username, phone, email }) => {
  return request({
    method: "POST",
    url: '/admin/add',
    data: {
      username,
      phone,
      email
    }
  })
}
/* 更新管理员信息 */
export const updateAdminInfoAPI = ({ id, username, phone, email }) => {
  return request({
    method: "PUT",
    url: '/admin/update',
    data: {
      id,
      username,
      phone,
      email
    }
  })
}
/* 获取管理员信息 */
export const getAdminInfoAPI = ({ username, phone, pageNum, pageSize, email }) => {
  return request({
    method: "GET",
    url: '/admin/pager',
    params: {
      pageNum,
      pageSize,
      username,
      phone,
      email
    }
  })
}
/* 根据ID查找管理员信息 */
export const getAdminByIdAPI = (id) => {
  return request({
    method: "GET",
    url: '/admin/' + id,
  })
}
/* 修改管理员密码 */
export const updatePassAPI = ({ username, password, newPass }) => {
  return request({
    method: "PUT",
    url: '/admin/password',
    data: {
      username,
      password,
      newPass
    }
  })
}




/**
 * 类别接口
 * @returns Promise对象
 */

/* 获取类目信息 */
export const getCategoryInfoAPI = ({ name, remark, pageNum, pageSize }) => {
  return request({
    method: "GET",
    url: '/category/pager',
    params: {
      pageNum,
      pageSize,
      name,
      remark,
    }
  })
}

export const getCategoryListAPI = () => {
  return request({
    method: "GET",
    url: '/category/list',
  })
}

/* 删除类目信息 */
export const DeleteCategoryByIdAPI = (id) => {
  return request({
    method: "DELETE",
    url: '/category/delete/' + id,
  })
}

/* 更新类别信息 */
export const updateCategoryInfoAPI = ({ id, name, remark }) => {
  return request({
    method: "PUT",
    url: '/category/update',
    data: {
      id,
      name,
      remark
    }
  })
}

/* 根据ID查找类别信息 */
export const getCategoryByIdAPI = (id) => {
  return request({
    method: "GET",
    url: '/category/' + id,
  })
}

/* 添加管理员信息 */
export const addCategoryInfoAPI = ({ name, remark }) => {
  return request({
    method: "POST",
    url: '/category/add',
    data: {
      name,
      remark
    }
  })
}




/**
 * 图书接口
 * @returns Promise对象
 */

/* 获取图书信息 */
export const getBookInfoAPI = ({ name, bookId, pageNum, pageSize }) => {
  return request({
    method: "GET",
    url: '/book/pager',
    params: {
      pageNum,
      pageSize,
      name,
      bookId,
    }
  })
}

export const getBookListAPI = () => {
  return request({
    method: "GET",
    url: '/book/list',
  })
}

/* 删除图书信息 */
export const DeleteBookByIdAPI = (id) => {
  return request({
    method: "DELETE",
    url: '/book/delete/' + id,
  })
}

/* 更新图书信息 */
export const updateBookInfoAPI = ({ id, name, bookId, description, author, press, publishDate, category, amount }) => {
  return request({
    method: "PUT",
    url: '/book/update',
    data: {
      id,
      name,
      bookId,
      description,
      author,
      press,
      publishDate,
      category,
      amount
    }
  })
}

/* 根据ID查找图书信息 */
export const getBookByIdAPI = (id) => {
  return request({
    method: "GET",
    url: '/book/' + id,
  })
}

/* 添加图书信息 */
export const addBookInfoAPI = ({ name, bookId, description, author, press, publishDate, category, amount }) => {
  return request({
    method: "POST",
    url: '/book/add',
    data: {
      name,
      bookId,
      description,
      author,
      press,
      publishDate,
      category,
      amount
    }
  })
}


/**
 * 借书接口
 * @returns Promise对象
 */

/* 获取借书信息 */
export const getBorrowInfoAPI = ({ name, bookId, username, pageNum, pageSize }) => {
  return request({
    method: "GET",
    url: '/borrow/pager',
    params: {
      pageNum,
      pageSize,
      name,
      bookId,
      username
    }
  })
}

export const getBorrowListAPI = () => {
  return request({
    method: "GET",
    url: '/borrow/list',
  })
}

/* 删除借书信息 */
export const DeleteBorrowByIdAPI = (id) => {
  return request({
    method: "DELETE",
    url: '/borrow/delete/' + id,
  })
}

/* 更新借书信息 */
export const updateBorrowInfoAPI = ({ id, name, bookId, uid, username, phone }) => {
  return request({
    method: "PUT",
    url: '/borrow/update',
    data: {
      id,
      name,
      bookId,
      uid,
      username,
      phone
    }
  })
}

/* 根据ID查找借书信息 */
export const getBorrowByIdAPI = (id) => {
  return request({
    method: "GET",
    url: '/borrow/' + id,
  })
}

/* 添加借书信息 */
export const addBorrowInfoAPI = ({ name, bookId, uid, username, phone, days }) => {
  return request({
    method: "POST",
    url: '/borrow/add',
    data: {
      name,
      bookId,
      uid,
      username,
      phone,
      days
    }
  })
}


/**
 * 还书接口
 * @returns Promise对象
 */



/* 获取还书信息 */
export const getReturnInfoAPI = ({ name, bookId, username, pageNum, pageSize }) => {
  return request({
    method: "GET",
    url: '/returnBook/pager',
    params: {
      pageNum,
      pageSize,
      name,
      bookId,
      username
    }
  })
}


/* 删除还书信息 */
export const DeleteReturnByIdAPI = (id) => {
  return request({
    method: "DELETE",
    url: '/returnBook/delete/' + id,
  })
}



export const addReturnInfoAPI = (row) => {
  return request({
    method: "POST",
    url: '/returnBook/insert',
    data: row
  })
}

/* 图表统计 */

export const lineChartsAPI = (timeRange) =>{
  return request({
    method: "GET",
    url: '/borrow/lineCharts/' + timeRange,
  })
}
