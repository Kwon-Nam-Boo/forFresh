import Welcome from "./views/Welcome";
import Home from "./views/Home";
import Login from "./views/user/Login";
import Join from "./views/user/Join";
import Detail from "./views/Detail";
import Post from "./views/Post";
import FindPassword from "./views/user/FindPassWord";
import AddProduct from "./components/product/AddProduct";
import ProductMain from "./views/product/ProductMain";
import ProductCategory from "./views/product/ProductCategory";
import ProductSearch from "./views/product/ProductSearch";

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
    path: "/addproduct",
    name: "AddProduct",
    component: AddProduct,
  },
  {
    path: "/productmain",
    name: "ProductMain",
    component: ProductMain,
  },
  {
    path: "/productcategory/:categroy",
    name: "ProductCategory",
    component: ProductCategory,
  },
  {
    path: "/ProductSearch/:item",
    name: "ProductSearch",
    component: ProductSearch,
  },
];
