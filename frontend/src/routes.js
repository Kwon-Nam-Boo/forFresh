import Welcome from './views/Welcome'
import Home from './views/Home'
import Login from './views/user/Login'
import Join from './views/user/Join'
import Detail from './views/Detail'
import Post from './views/Post'
export default [
    {
        path : '/',
        name : 'Welcome',
        component : Welcome
    },
    {
        path : '/home',
        name : 'Home',
        component : Home
    },
    {
        path : '/login',
        name : 'Login',
        component : Login
    },
    {
        path : '/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/detail',
        name : 'Detail',
        component : Detail
    },
   {
        path : '/post',
        name : 'Post',
        component : Post
    },
]
