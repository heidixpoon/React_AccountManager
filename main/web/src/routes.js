import React from 'react'
import { Route, IndexRoute } from 'react-router'
import App from './components/App'
import SignIn from './pages/SignInPage'
import AccountsList from './pages/AccountsListPage'
import AccountDetail from './pages/AccountDetailPage'


const routes = (
  <Route path="/" component={App}>
    <IndexRoute component={SignIn} />
    <Route path="signIn" component={SignIn} />
    <Route path="accountsList" component={AccountsList} />
    <Route path="accountDetail/:userId" component={AccountDetail} />

  </Route>
)

export default routes
