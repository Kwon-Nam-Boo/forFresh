import Welcome from "./views/Welcome";
import Home from "./views/Home";
import Login from "./views/user/Login";
import Join from "./views/user/Join";
import Detail from "./views/Detail";
import Post from "./views/Post";
import FindPassword from "./views/user/FindPassWord";
import PageNotFound from "./views/error/PageNotFound";
import Error from "./views/error/Error";
import AddProduct from "./components/product/AddProduct";
import ProductMain from "./views/product/ProductMain";
import ProductDetail from "./views/product/ProductDetail";
import ProductSearch from "./views/product/ProductSearch";
import ProductCategory from "./views/product/ProductCategory";
import ShoppingList from "./views/product/ShoppingList";
import PaymentSuccess from "./views/payment/PaymentSuccess";
import BuyPage from "./views/product/BuyPage";

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
    path: "/productsearch/:keyword",
    name: "ProductSearch",
    component: ProductSearch,
  },
  {
    path: "/productdetail/:productno",
    name: "ProductDetail",
    component: ProductDetail,
  },

  {
    path: "/productcategory/:categoryno",
    name: "ProductCategory",
    component: ProductCategory,
  },
  {
    path: "/shoppinglist",
    name: "ShoppingList",
    component: ShoppingList,
  },
  {
    path: "/paymentsuccess",
    name: "PaymentSuccess",
    component: PaymentSuccess,
  },
  {
    path: "/buypage/:productno",
    name: "BuyPage",
    component: BuyPage,
  },
  {
    path: "/*",
    name: "PageNotFound",
    component: PageNotFound,
  },
];
