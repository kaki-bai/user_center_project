export default [
  {
    path: '/user',
    layout: false,
    routes: [
      { name: 'Login', path: '/user/login', component: './User/Login' },
      { name: 'Register', path: '/user/register', component: './User/Register' },
    ],
  },
  { path: '/welcome', name: 'Welcome', icon: 'smile', component: './Welcome' },
  {
    path: '/admin',
    name: 'User Management',
    icon: 'crown',
    access: 'canAdmin',
    // component: './Admin',
    routes: [
      { path: '/admin', redirect: './Admin/UserManage' },
      { path: '/admin/user-manage', name: 'user manage', component: './Admin/UserManage' },
    ],
  },
  { name: 'Table List', icon: 'table', path: '/list', component: './TableList' },
  { path: '/', redirect: '/welcome' },
  { path: '*', layout: false, component: './404' },
];
