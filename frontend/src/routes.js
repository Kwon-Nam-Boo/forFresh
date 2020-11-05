import Welcome from "./views/Welcome";
import Home from "./views/Home";
import Login from "./views/user/Login";
import Join from "./views/user/Join";
import Detail from "./views/Detail";
import Post from "./views/Post";
import FindPassword from "./views/user/FindPassWord";
import PageNotFound from "./views/error/PageNotFound";
import Error from "./views/error/Error";
export default [
  {
    path: "/",
    name: "Welcome",
    component: Welcome,
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/detail",
    name: "Detail",
    component: Detail,
  },
  {
    path: "/post",
    name: "Post",
    component: Post,
  },
  {
    path: "/findpassword",
    name: "FindPassword",
    component: FindPassword,
  },  
  {
    path: "/error",
    name: "Error",
    component: Error,
  },
  {
    path: "/*",
    name: "PageNotFound",
    component: PageNotFound,
  },
]



