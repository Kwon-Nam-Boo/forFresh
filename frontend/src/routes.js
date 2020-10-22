import Welcome from './views/Welcome'
import Home from './views/Home'
import Login from './views/user/Login'
import Join from './views/user/Join'

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
   
]
